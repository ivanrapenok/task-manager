package ivanchik.task.manager.api.db;

import ivanchik.task.manager.api.pojo.User;

import java.util.List;

public interface UserMapper {

    void createTableIfNeeded();

    List<User> getUserList();

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);
}
