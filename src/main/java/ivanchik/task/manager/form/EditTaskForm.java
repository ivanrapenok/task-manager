package ivanchik.task.manager.form;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.sun.org.apache.xpath.internal.SourceTree;
import ivanchik.task.manager.api.db.Storage;
import ivanchik.task.manager.api.pojo.Task;
import ivanchik.task.manager.core.db.H2Storage;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class EditTaskForm extends JFrame implements Runnable{

    private JLabel parentTask;
    private JLabel taskName;
    private JLabel description;
    private JLabel comment;
    private JLabel startDate;
    private JLabel endDate;
    private JLabel progress;
    private JLabel timeSpent;
    private JLabel daly;
    private JLabel closed;

    private JTextField parentTaskField;
    private JTextField taskNameField;
    private JTextField descriptionField;
    //private JDatePickerImpl commentField;
    private JDatePickerImpl startDateField;
    private JDatePickerImpl endDateField;
    private JTextField progressField;
    private JTextField timeSpentField;
    private JCheckBox dalyField;
    private JCheckBox closedField;

    private JPanel addButtonPanel;
    private JPanel editButtonPannel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton cancelButton;

    private final Storage storage = H2Storage.getInstance();
    private long userId;
    private Task task;

    public EditTaskForm(boolean editMode, long userId, Task task) {
        this.userId = userId;
        this.task = task;
        initComponents(editMode);
    }

    private void initComponents(boolean editMode) {

        System.out.println(task.getParentId());

        parentTask = new JLabel("Parent task");
        taskName = new JLabel("Task name");
        description = new JLabel("Description");
        comment = new JLabel("Comment");
        startDate = new JLabel("Start date");
        endDate = new JLabel("End date");
        progress = new JLabel("Progress");
        timeSpent = new JLabel("Spent time");
        daly = new JLabel("Daly");
        closed = new JLabel("Closed");

        parentTaskField = new JTextField();
        taskNameField = new JTextField();
        descriptionField = new JTextField();

        UtilDateModel endDateModel = new UtilDateModel();
        UtilDateModel startDateModel = new UtilDateModel();
        endDateModel.setSelected(true);
        startDateModel.setSelected(true);
        Calendar calendar = Calendar.getInstance();
        endDateModel.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        startDateModel.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endDateModel);
        JDatePanelImpl startDatePanel = new JDatePanelImpl(startDateModel);
        //commentField = new JDatePickerImpl(datePanel);
        startDateField = new JDatePickerImpl(startDatePanel);
        endDateField = new JDatePickerImpl(endDatePanel);
        progressField = new JTextField();
        timeSpentField = new JTextField();
        dalyField = new JCheckBox();
        closedField = new JCheckBox();

        addButtonPanel = new JPanel();
        editButtonPannel = new JPanel();
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        cancelButton = new JButton("Cancel");

        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
                "50px, default, 400, 50px",
                "default, default, default, default, default, " +
                        "default, default, default, default, " +
                        "default, default, default, default, " +
                        "default, default"));


        parentTask.setPreferredSize(new Dimension(100, 30));
        contentPane.add(parentTask, new CellConstraints(2, 1));

        parentTaskField.enable(false);
        parentTaskField.setPreferredSize(new Dimension(100, 30));
        contentPane.add(parentTaskField, new CellConstraints(3, 1));

        taskName.setPreferredSize(new Dimension(100, 30));
        contentPane.add(taskName, new CellConstraints(2, 2));

        taskNameField.setPreferredSize(new Dimension(100, 30));
        contentPane.add(taskNameField, new CellConstraints(3, 2));

        description.setPreferredSize(new Dimension(100, 30));
        contentPane.add(description, new CellConstraints(2, 3));

        descriptionField.setPreferredSize(new Dimension(100, 100));
        contentPane.add(descriptionField, new CellConstraints(3, 3));

//        comment.setPreferredSize(new Dimension(100, 30));
//        contentPane.add(comment, new CellConstraints(2, 4));

//        commentField.setPreferredSize(new Dimension(100, 30));
//        contentPane.add(commentField, new CellConstraints(3, 4));

        startDate.setPreferredSize(new Dimension(100, 30));
        contentPane.add(startDate, new CellConstraints(2, 5));

        startDateField.setPreferredSize(new Dimension(100, 30));
        contentPane.add(startDateField, new CellConstraints(3, 5));

        endDate.setPreferredSize(new Dimension(100, 30));
        contentPane.add(endDate, new CellConstraints(2, 6));

        endDateField.setPreferredSize(new Dimension(100, 30));
        contentPane.add(endDateField, new CellConstraints(3, 6));

        endDate.setPreferredSize(new Dimension(100, 30));
        contentPane.add(endDate, new CellConstraints(2, 7));

        endDateField.setPreferredSize(new Dimension(100, 30));
        contentPane.add(endDateField, new CellConstraints(3, 7));

        progress.setPreferredSize(new Dimension(100, 30));
        contentPane.add(progress, new CellConstraints(2, 8));

        progressField.setPreferredSize(new Dimension(100, 30));
        contentPane.add(progressField, new CellConstraints(3, 8));

        timeSpent.setPreferredSize(new Dimension(100, 30));
        contentPane.add(timeSpent, new CellConstraints(2, 9));

        timeSpentField.setPreferredSize(new Dimension(100, 30));
        contentPane.add(timeSpentField, new CellConstraints(3, 9));

        daly.setPreferredSize(new Dimension(100, 30));
        contentPane.add(daly, new CellConstraints(2, 10));

        dalyField.setPreferredSize(new Dimension(100, 30));
        contentPane.add(dalyField, new CellConstraints(3, 10));

        closed.setPreferredSize(new Dimension(100, 30));
        contentPane.add(closed, new CellConstraints(2, 11));

        closedField.setPreferredSize(new Dimension(100, 30));
        contentPane.add(closedField, new CellConstraints(3, 11));


        editButton.setPreferredSize(new Dimension(75, 25));
        deleteButton.setPreferredSize(new Dimension(75, 25));
        addButton.setPreferredSize(new Dimension(75, 25));
        cancelButton.setPreferredSize(new Dimension(75, 25));

        if (editMode) {
            if (task.getDescription() != null) {
                descriptionField.setText(task.getDescription());
            }
            if (task.getEndDate() != null) {
                endDateField = setDateJDatePicker(endDateField, task.getEndDate());
            }
            if (task.getName() != null) {
                taskNameField.setText(task.getName());
            }
            if (task.getParentId() != null) {
                parentTaskField.setText(task.getParentId().toString());
            }
            if (task.getStartDate() != null) {
                startDateField = setDateJDatePicker(startDateField, task.getStartDate());
            }
            if (task.getParentId() != null) {
                parentTaskField.setText(task.getParentId().toString());
            }
            progressField.setText(String.valueOf(task.getProgress()));
            timeSpentField.setText(String.valueOf(task.getTimeSpent()));
            dalyField.setSelected(task.isDaily());
            closedField.setSelected(task.isClosed());

            editButtonPannel.add(editButton);
            editButtonPannel.add(deleteButton);
            editButtonPannel.add(cancelButton);
            contentPane.add(editButtonPannel, new CellConstraints(1, 13, 4, 1));
        } else {
            if (task.getParentId() != null) {
                parentTaskField.setText(task.getParentId().toString());
            }
            addButtonPanel.add(addButton);
            addButtonPanel.add(cancelButton);
            contentPane.add(addButtonPanel, new CellConstraints(2, 12, 2, 1));
        }


        addButton.addActionListener(this::addTask);
        cancelButton.addActionListener(this::cancel);
        deleteButton.addActionListener(this::deleteTask);
        editButton.addActionListener(this::editTask);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(getOwner());
        setVisible(true);
    }

    public void cancel(ActionEvent actionEvent) {
        setVisible(false);
        dispose();
        SwingUtilities.invokeLater(new TaskForm(userId));
    }

    public void addTask(ActionEvent actionEvent) {
        if (!checkFields()) {
            return;
        }
        storage.addTask(fillTask());
        setVisible(false);
        dispose();
        SwingUtilities.invokeLater(new TaskForm(userId));
    }

    public void editTask(ActionEvent actionEvent) {
        if (!checkFields()) {
            return;
        }
        storage.updateTask(fillTask());
        setVisible(false);
        dispose();
        SwingUtilities.invokeLater(new TaskForm(userId));
    }

    public void deleteTask(ActionEvent actionEvent) {
        storage.deleteTask(task);
        setVisible(false);
        dispose();
        SwingUtilities.invokeLater(new TaskForm(userId));
    }

    public JDatePickerImpl setDateJDatePicker(JDatePickerImpl jDatePicker, Timestamp timestamp) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp.getTime());
            jDatePicker.getModel().setYear(calendar.get(Calendar.YEAR));
            jDatePicker.getModel().setMonth(calendar.get(Calendar.MONTH));
            jDatePicker.getModel().setDay(calendar.get(Calendar.DAY_OF_MONTH));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return jDatePicker;
    }

    public Timestamp dateToTimeStamp(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public Task fillTask() {
        Task filledTask = new Task().setName(taskNameField.getText()).
                setClosed(closedField.isSelected()).
                setDaily(dalyField.isSelected()).
                setDescription(descriptionField.getText()).
                setUserId(userId).
                setId(task.getId()).
                setEndDate(dateToTimeStamp((Date) endDateField.getModel().getValue())).
                setStartDate(dateToTimeStamp((Date) startDateField.getModel().getValue())).
                setProgress(Integer.parseInt(progressField.getText())).
                setTimeSpent(Integer.parseInt(timeSpentField.getText()));
        if (!parentTaskField.getText().isEmpty()) {
            filledTask.setParentId(Long.valueOf(parentTaskField.getText()));
        }
        return filledTask;
    }

    public boolean checkFields() {
        boolean check = true;
        taskName.setForeground(Color.black);
        progress.setForeground(Color.black);
        timeSpent.setForeground(Color.black);
        endDate.setForeground(Color.black);
        startDate.setForeground(Color.black);

        if (taskNameField.getText().isEmpty()) {
            taskName.setForeground(Color.red);
            check = false;
        }
        if (!isIntStrInRange(progressField.getText(), 0 , 100)) {
            progress.setForeground(Color.red);
            check = false;
        }
        if (!isInteger(timeSpentField.getText(), 10) || timeSpentField.getText().isEmpty()) {
            timeSpent.setForeground(Color.red);
            check = false;
        }
        if (((Date) endDateField.getModel().getValue()).before((Date) startDateField.getModel().getValue())) {
            endDate.setForeground(Color.red);
            startDate.setForeground(Color.red);
            check = false;
        }
        return check;
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    public static boolean isIntStrInRange(String num, int min, int max) {
        try {
            int n = Integer.parseInt(num);
            return !((n < min) || (n > max));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void run() {

    }
}
