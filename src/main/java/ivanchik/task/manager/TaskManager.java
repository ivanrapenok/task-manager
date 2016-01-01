package ivanchik.task.manager;

import ivanchik.task.manager.core.db.H2Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TaskManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskManager.class);

    public static void main(String[] args) {
        initDb();
    }

    private static void initDb() {
        Connection conn;
        try {
            conn = DriverManager.
                    getConnection("jdbc:h2:./db/db");
            conn.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        H2Storage.init();
    }
}
