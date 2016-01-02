package ivanchik.task.manager;

import ivanchik.task.manager.core.db.H2Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

public class TaskManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskManager.class);

    public static void main(String[] args) {
        initDb();
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
