package ivanchik.task.manager.api.db;

import ivanchik.task.manager.api.pojo.Task;

import java.util.List;

public interface TaskMapper {

    void createTableIfNeeded();

    List<Task> getTaskList();

    void addTask(Task task);

    void updateTask(Task task);

    void deleteTask(Task task);

    Task getTask(Task task);
}
