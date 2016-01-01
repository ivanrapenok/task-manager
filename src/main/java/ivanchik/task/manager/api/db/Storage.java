package ivanchik.task.manager.api.db;

import ivanchik.task.manager.api.pojo.Task;
import ivanchik.task.manager.api.pojo.User;

import java.util.List;

public interface Storage {

    List<User> getUserList();

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    List<Task> getTaskList();

    void addTask(Task task);

    void updateTask(Task task);
}
