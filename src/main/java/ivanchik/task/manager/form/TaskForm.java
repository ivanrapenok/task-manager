package ivanchik.task.manager.form;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import ivanchik.task.manager.api.model.NotEditableTableModel;
import ivanchik.task.manager.api.db.Storage;
import ivanchik.task.manager.api.pojo.Task;
import ivanchik.task.manager.core.db.H2Storage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TaskForm extends JFrame implements Runnable {

    private JButton createSubTaskButton;
    private JButton editButton;
    private JButton historyButton;
    private JScrollPane scrollPane1;
    private JTable taskTable;
    private JButton createTaskButton;
    private JButton signOutButton;
    private JLabel errorLabel;

    private final Storage storage = H2Storage.getInstance();
    private long userId;
    private boolean showHistory = false;

    public TaskForm(long userId) {
        this.userId = userId;
        showHistory = false;
        initComponents(userId);
        taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fillUserTable();
    }

    private void initComponents(long userId) {

        createTaskButton = new JButton();
        createSubTaskButton = new JButton();
        editButton = new JButton();
        historyButton = new JButton();
        signOutButton = new JButton();
        scrollPane1 = new JScrollPane();
        taskTable = new JTable();
        errorLabel = new JLabel();

        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "default, 125px",
                "default, default, default, default, default, 200px"));

        createTaskButton.setText("Add task");
        contentPane.add(createTaskButton, new CellConstraints(2, 1));

        createSubTaskButton.setText("Add subtask");
        contentPane.add(createSubTaskButton, new CellConstraints(2, 2));

        editButton.setText("Edit");
        contentPane.add(editButton, new CellConstraints(2, 3));

        historyButton.setText("Show history");
        contentPane.add(historyButton, new CellConstraints(2, 4));

        signOutButton.setText("Sign out");
        contentPane.add(signOutButton, new CellConstraints(2, 5));

        scrollPane1.setViewportView(taskTable);
        contentPane.add(scrollPane1, new CellConstraints(1, 1, 1, 6));

        errorLabel.setForeground(Color.red);
        errorLabel.setVisible(false);
        contentPane.add(errorLabel, new CellConstraints(2, 6));

        historyButton.addActionListener(this::showHideHistory);
        createTaskButton.addActionListener(this::addTask);
        createSubTaskButton.addActionListener(this::addSubTask);
        editButton.addActionListener(this::editTask);
        signOutButton.addActionListener(this::signOut);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(getOwner());
        setVisible(true);
    }

    private void addTask(ActionEvent actionEvent) {
        setVisible(false);
        dispose();
        SwingUtilities.invokeLater(new EditTaskForm(false, userId, new Task()));
    }

    private void editTask(ActionEvent actionEvent) {
        errorLabel.setVisible(false);
        errorLabel.setVisible(false);
        int selected = taskTable.getSelectedRow();
        if (selected < 0) {
            errorLabel.setText("Choose task");
            errorLabel.setVisible(true);
            return;
        }
        long id = (long) taskTable.getModel().getValueAt(selected, 0);
        Task task = storage.getTask(new Task().setId(id));
        setVisible(false);
        dispose();
        SwingUtilities.invokeLater(new EditTaskForm(true, userId, task));
    }

    private void addSubTask(ActionEvent actionEvent) {
        errorLabel.setVisible(false);
        int selected = taskTable.getSelectedRow();
        if (selected < 0) {
            errorLabel.setText("Choose task");
            errorLabel.setVisible(true);
            return;
        }
        long id = (long) taskTable.getModel().getValueAt(selected, 0);
        setVisible(false);
        dispose();
        SwingUtilities.invokeLater(new EditTaskForm(false, userId, new Task().setParentId(id)));
    }

    private void showHideHistory(ActionEvent e) {
        errorLabel.setVisible(false);
        if (showHistory) {
            historyButton.setText("Show history");
        } else {
            historyButton.setText("Hide history");
        }
        showHistory = !showHistory;
        fillUserTable();
    }

    private void signOut(ActionEvent actionEvent) {
        setVisible(false);
        dispose();
        SwingUtilities.invokeLater(MainForm::new);
    }

    private void fillUserTable() {
        Object[] columnNames = {"id", "name", "description", "end date", "progress", "time spent"};
        DefaultTableModel model = new NotEditableTableModel(columnNames, 0);
        for (Task task : storage.getTaskList()) {
            if ((task.getUserId() != null) && (task.getUserId() == userId) && (!task.isClosed() || showHistory)) {
                    model.addRow(new Object[]{task.getId(),
                            task.getName(),
                            task.getDescription(),
                            task.getEndDate(),
                            task.getProgress(),
                            task.getTimeSpent(),
                            task.getParentId(),
                            task.getStartDate(),
                            task.getUserId()});
            }

        }
        taskTable.setModel(model);
        taskTable.removeColumn(taskTable.getColumnModel().getColumn(0));
        taskTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        taskTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        taskTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        taskTable.getColumnModel().getColumn(3).setPreferredWidth(40);
        taskTable.getColumnModel().getColumn(4).setPreferredWidth(40);
    }

    @Override
    public void run() {

    }
}
