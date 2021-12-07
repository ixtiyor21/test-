package set.up.dao.task;

import set.up.interfaces.BaseDao;
import set.up.model.task.SubTask;
import set.up.util.Color;
import set.up.util.Print;

import java.util.ArrayList;

public class SubTaskDao implements BaseDao<SubTask> {
    private static final ArrayList<SubTask> SUB_TASK = new ArrayList<>();

    @Override
    public void create(SubTask subTask) {
        SUB_TASK.add(subTask);
    }

    @Override
    public void delete(SubTask subTask) {
        SUB_TASK.remove(subTask);
    }

    @Override
    public void list() {
        for (SubTask subTask : SUB_TASK) {
            Print.println(Color.GREEN, subTask);
        }
    }

    public SubTask getTask(String title) {
        for (SubTask subTask : SUB_TASK) {
            if (subTask.getTitle().equals(title)) {
                return subTask;
            }
        }
        return null;
    }

    public SubTask getTaskById(String id) {
        for (SubTask subTask : SUB_TASK) {
            if (subTask.getId().equals(id)) {
                return subTask;
            }
        }
        return null;
    }
}
