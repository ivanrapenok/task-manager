package ivanchik.task.manager.core.db;

import ivanchik.task.manager.api.db.TaskMapper;
import ivanchik.task.manager.api.db.UserMapper;
import ivanchik.task.manager.api.db.Storage;
import ivanchik.task.manager.api.pojo.Task;
import ivanchik.task.manager.api.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class H2Storage implements Storage {

    private static final Logger LOGGER = LoggerFactory.getLogger(H2Storage.class);

    private static H2Storage instance;
    private SqlSessionFactory sessionFactory;

    public static void init() {
        instance = new H2Storage();
    }

    public static H2Storage getInstance() {
        return instance;
    }

    private H2Storage() {
        String resource = "my-batis-config.xml";
        InputStream inputStream = getResourceAsStream(resource);
        this.sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try(SqlSession sqlSession = sessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
            userMapper.createTableIfNeeded();
            taskMapper.createTableIfNeeded();
        }
    }

    private InputStream getResourceAsStream(String resource) {
        try {
            return Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            LOGGER.error("Something went too wrong..", e);
        }
        return null;
    }

    @Override
    public List<User> getUserList() {
        try(SqlSession sqlSession = sessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.getUserList();
        }
    }

    @Override
    public void addUser(User user) {
        try(SqlSession sqlSession = sessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.addUser(user);
            LOGGER.info("Inserted " + user.toString());
        }
    }

    @Override
    public void deleteUser(User user) {
        try(SqlSession sqlSession = sessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.deleteUser(user);
            LOGGER.info("Deleted " + user.toString());
        }
    }

    @Override
    public void updateUser(User user) {
        try(SqlSession sqlSession = sessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.updateUser(user);
            LOGGER.info("Updated " + user.toString());
        }
    }

    @Override
    public List<Task> getTaskList() {
        try(SqlSession sqlSession = sessionFactory.openSession()) {
            TaskMapper mapper = sqlSession.getMapper(TaskMapper.class);
            return mapper.getTaskList();
        }
    }

    @Override
    public void addTask(Task task) {
        try(SqlSession sqlSession = sessionFactory.openSession()) {
            TaskMapper mapper = sqlSession.getMapper(TaskMapper.class);
            mapper.addTask(task);
            LOGGER.info("Inserted " + task.toString());
        }
    }

    @Override
    public void updateTask(Task task) {
        try(SqlSession sqlSession = sessionFactory.openSession()) {
            TaskMapper mapper = sqlSession.getMapper(TaskMapper.class);
            mapper.updateTask(task);
            LOGGER.info("Updated " + task.toString());
        }
    }
}
