package set.up.dao.task;

import set.up.interfaces.BaseDao;
import set.up.model.task.Task;
import set.up.util.Color;
import set.up.util.Print;

import java.util.ArrayList;

public class TaskDao implements BaseDao<Task> {
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    @Override
    public void create(Task task) {
        TASKS.add(task);
    }

    @Override
    public void delete(Task task) {
        TASKS.remove(task);
    }

    @Override
    public void list() {
        for (Task task : TASKS) {
            Print.println(Color.GREEN, task);
        }
    }

    public Task getTask(String title) {
        for (Task task : TASKS) {
            if (task.getTitle().equals(title)) {
                return task;
            }
        }
        return null;
    }

    public Task getTaskById(String id) {
        for (Task task : TASKS) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }
}
