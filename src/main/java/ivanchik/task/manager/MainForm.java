package ivanchik.task.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import ivanchik.task.manager.api.NotEditableTableModel;
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

        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "default, default",
            "default, default, default, default, default, 200px"));

        username.setPreferredSize(new Dimension(0, 30));
        contentPane.add(username, new CellConstraints(2, 1));

        selectButton.setText("Выбрать");
        contentPane.add(selectButton, new CellConstraints(2, 2));

        createButton.setText("Создать");
        contentPane.add(createButton, new CellConstraints(2, 3));

        editButton.setText("Изменить");
        contentPane.add(editButton, new CellConstraints(2, 4));

        deleteButton.setText("Удалить");
        contentPane.add(deleteButton, new CellConstraints(2, 5));

        scrollPane1.setViewportView(userTable);
        contentPane.add(scrollPane1, new CellConstraints(1, 1, 1, 6));

        deleteButton.addActionListener(this::deleteUser);
        createButton.addActionListener(this::createUser);
        editButton.addActionListener(this::editUser);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(getOwner());
        setVisible(true);
    }

    private void editUser(ActionEvent actionEvent) {
        int selected = userTable.getSelectedRow();
        if(selected < 0 || username.getText().isEmpty()) {
            return;
        }
        int id = (int) userTable.getModel().getValueAt(selected, 0);
        storage.updateUser(new User().setId(id).setName(username.getText()));
        fillUserTable();
    }

    private void createUser(ActionEvent actionEvent) {
        if(username.getText().isEmpty()) {
            return;
        }
        storage.addUser(new User().setName(username.getText()));
        fillUserTable();
    }

    private void deleteUser(ActionEvent e) {
        int selected = userTable.getSelectedRow();
        if(selected < 0) {
            return;
        }
        int id = (int) userTable.getModel().getValueAt(selected, 0);
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
