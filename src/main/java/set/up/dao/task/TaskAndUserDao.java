package set.up.dao.task;

import set.up.dao.auth.UserDao;
import set.up.interfaces.BaseDao;
import set.up.model.task.TaskAndUser;
import set.up.util.Color;
import set.up.util.Print;

import java.util.ArrayList;

public class TaskAndUserDao implements BaseDao<TaskAndUser> {
    private static final ArrayList<TaskAndUser> TASK_AND_USERS = new ArrayList<>();

    @Override
    public void create(TaskAndUser taskAndUser) {
        TASK_AND_USERS.add(taskAndUser);
    }

    @Override
    public void delete(TaskAndUser taskAndUser) {
        TASK_AND_USERS.remove(taskAndUser);
    }

    @Override
    public void list() {
        TaskDao taskDao = new TaskDao();
        UserDao userDao = new UserDao();
        for (TaskAndUser taskAndUser : TASK_AND_USERS) {
            Print.println(Color.GREEN, taskDao.getTaskById(taskAndUser.getTaskId()) + "user = " + userDao.getUserById(taskAndUser.getUserId()).getUsername());
        }
    }

    public void list(String userId) {
        TaskDao taskDao = new TaskDao();
        UserDao userDao = new UserDao();
        for (TaskAndUser taskAndUser : TASK_AND_USERS) {
            if (taskAndUser.getUserId().equals(userId))
                Print.println(Color.GREEN, taskDao.getTaskById(taskAndUser.getTaskId()) + "user = " + userDao.getUserById(taskAndUser.getUserId()).getUsername());
        }
    }

    public TaskAndUser getTaskAndUser(String taskId, String userId) {
        for (TaskAndUser taskAndUser : TASK_AND_USERS) {
            if (taskAndUser.getUserId().equals(userId) && taskAndUser.getTaskId().equals(taskId)) {
                return taskAndUser;
            }
        }
        return null;
    }

    public TaskAndUser getTaskAndUser(String taskId) {
        for (TaskAndUser taskAndUser : TASK_AND_USERS) {
            if (taskAndUser.getTaskId().equals(taskId)) {
                return taskAndUser;
            }
        }
        return null;
    }
}
