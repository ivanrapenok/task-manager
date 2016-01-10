package ivanchik.task.manager.form;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.*;
import ivanchik.task.manager.api.model.NotEditableTableModel;
import ivanchik.task.manager.api.db.Storage;
import ivanchik.task.manager.api.pojo.User;
import ivanchik.task.manager.core.db.H2Storage;

public class MainForm extends JFrame {

    private JButton createButton;
    private JButton editButton;
    private JButton deleteButton;
    private JScrollPane scrollPane1;
    private JTable userTable;
    private JButton selectButton;
    private JTextField username;
    private JLabel errorLabel;

    private final Storage storage = H2Storage.getInstance();

    public MainForm() {
        initComponents();
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fillUserTable();
    }

    private void initComponents() {
        selectButton = new JButton();
        createButton = new JButton();
        editButton = new JButton();
        deleteButton = new JButton();
        scrollPane1 = new JScrollPane();
        userTable = new JTable();
        username = new JTextField();
        errorLabel = new JLabel("Hello");

        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "default, 125px",
            "default, default, default, default, default, 200px"));

        username.setPreferredSize(new Dimension(0, 30));
        contentPane.add(username, new CellConstraints(2, 1));

        selectButton.setText("Sign in");
        contentPane.add(selectButton, new CellConstraints(2, 2));

        createButton.setText("Add");
        contentPane.add(createButton, new CellConstraints(2, 3));

        editButton.setText("Edit");
        contentPane.add(editButton, new CellConstraints(2, 4));

        deleteButton.setText("Delete");
        contentPane.add(deleteButton, new CellConstraints(2, 5));

        scrollPane1.setViewportView(userTable);
        contentPane.add(scrollPane1, new CellConstraints(1, 1, 1, 6));

        errorLabel.setForeground(Color.red);
        errorLabel.setVisible(false);
        contentPane.add(errorLabel, new CellConstraints(2, 6));

        deleteButton.addActionListener(this::deleteUser);
        createButton.addActionListener(this::createUser);
        editButton.addActionListener(this::editUser);
        selectButton.addActionListener(this::selectUser);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(getOwner());
        setVisible(true);
    }

    private void editUser(ActionEvent actionEvent) {
        errorLabel.setVisible(false);
        int selected = userTable.getSelectedRow();
        if (selected < 0) {
            errorLabel.setText("Choose user");
            errorLabel.setVisible(true);
            return;
        }
        if (username.getText().isEmpty()) {
            errorLabel.setText("Username is empty");
            errorLabel.setVisible(true);
            return;
        }
        if (isUserExist(username.getText())) {
            errorLabel.setText("User is exist");
            errorLabel.setVisible(true);
            return;
        }
        int id = (int) userTable.getModel().getValueAt(selected, 0);
        storage.updateUser(new User().setId(id).setName(username.getText()));
        fillUserTable();
    }

    private void selectUser(ActionEvent actionEvent) {
        errorLabel.setVisible(false);
        int selected = userTable.getSelectedRow();
        if (selected < 0) {
            errorLabel.setText("Choose user");
            errorLabel.setVisible(true);
            return;
        }
        int id = (int) userTable.getModel().getValueAt(selected, 0);
        setVisible(false);
        dispose();
        SwingUtilities.invokeLater(new TaskForm(id));
    }

    private void createUser(ActionEvent actionEvent) {
        errorLabel.setVisible(false);
        if(username.getText().isEmpty()) {
            errorLabel.setText("Username is empty");
            errorLabel.setVisible(true);
            return;
        }
        if (isUserExist(username.getText())) {
            errorLabel.setText("User is exist");
            errorLabel.setVisible(true);
            return;
        }
        storage.addUser(new User().setName(username.getText()));
        fillUserTable();
    }

    private boolean isUserExist(String name) {
        for (User user: storage.getUserList()) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private void deleteUser(ActionEvent e) {
        errorLabel.setVisible(false);
        int selected = userTable.getSelectedRow();
        if(selected < 0) {
            errorLabel.setText("Choose user");
            errorLabel.setVisible(true);
            return;
        }
        int id = (int) userTable.getModel().getValueAt(selected, 0);
        System.out.println();
        storage.deleteUser(new User().setId(id));
        fillUserTable();
    }

    private void fillUserTable() {
        Object[] columnNames = {"id", "name"};
        DefaultTableModel model = new NotEditableTableModel(columnNames, 0);
        storage.getUserList().forEach((user -> model.addRow(new Object[]{user.getId(), user.getName()})));
        userTable.setModel(model);
        userTable.removeColumn(userTable.getColumnModel().getColumn(0));
    }
}
