package ivanchik.task.manager;

import ivanchik.task.manager.api.pojo.Task;
import ivanchik.task.manager.core.db.H2Storage;
import ivanchik.task.manager.form.EditTaskForm;
import ivanchik.task.manager.form.MainForm;
import ivanchik.task.manager.form.TaskForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.sql.*;
import java.util.Calendar;

public class TaskManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskManager.class);

    public static void main(String[] args) {
        initDb();
        System.out.println(new Timestamp(Calendar.getInstance().getTime().getTime()));
//        H2Storage.getInstance().addTask(new Task().
//                setName("first").
//                setEndDate(new Timestamp(Calendar.getInstance().getTime().getTime())).
//                setStartDate(new Timestamp(Calendar.getInstance().getTime().getTime())).
//                setClosed(true).
//                setDaily(true).
//                setDescription("descr").
//                setUserId((long) 1).
//                setParentId((long) 4).
//                setProgress(100).
//                setTimeSpent(100));
//        H2Storage.getInstance().updateTask(new Task().setId((long) 19).setName("chai").setDescription("ds").setStartDate(new Timestamp(Calendar.getInstance().getTime().getTime())).
//                setStartDate(new Timestamp(Calendar.getInstance().getTime().getTime())).setTimeSpent(12).setProgress(12).setDaily(true).setClosed(true));
        System.out.println(H2Storage.getInstance().getTaskList());
        System.out.println(H2Storage.getInstance().getUserList());
        SwingUtilities.invokeLater(MainForm::new);
    }

    private static void initDb() {
        Connection conn;
        try {
            conn = DriverManager.
                    getConnection("jdbc:h2:./db/db");
//            Statement statement = conn.createStatement();
//            statement.execute("INSERT INTO users (name) VALUES('egor2')");
//
//            statement = conn.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT * FROM users");
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                System.out.println(id);
//            }
            conn.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        H2Storage.init();
    }
}
