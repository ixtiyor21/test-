package set.up.dao.task;

import set.up.dao.auth.UserDao;
import set.up.interfaces.BaseDao;
import set.up.model.task.SubTaskAndUser;
import set.up.service.auth.SessionService;
import set.up.util.Color;
import set.up.util.Print;

import java.util.ArrayList;

public class SubTaskAndUserDao implements BaseDao<SubTaskAndUser> {
    private static final ArrayList<SubTaskAndUser> SUB_TASK_AND_USERS = new ArrayList<>();

    @Override
    public void create(SubTaskAndUser taskAndUser) {
        SUB_TASK_AND_USERS.add(taskAndUser);
    }

    @Override
    public void delete(SubTaskAndUser taskAndUser) {
        SUB_TASK_AND_USERS.remove(taskAndUser);
    }

    @Override
    public void list() {
        SubTaskDao subTaskDao = new SubTaskDao();
        UserDao userDao = new UserDao();
        for (SubTaskAndUser subTaskAndUser : SUB_TASK_AND_USERS) {
            Print.println(Color.GREEN, subTaskDao.getTaskById(subTaskAndUser.getSubTaskId()) + "user = " + userDao.getUserById(subTaskAndUser.getUserId()));
        }
    }

    public void getList() {
        SubTaskDao subTaskDao = new SubTaskDao();
        UserDao userDao = new UserDao();
        for (SubTaskAndUser subTaskAndUser : SUB_TASK_AND_USERS) {
            if (subTaskAndUser.getUserId().equals(SessionService.getSession().getId()))
                Print.println(Color.GREEN, subTaskDao.getTaskById(subTaskAndUser.getSubTaskId()) + "user = " + userDao.getUserById(subTaskAndUser.getUserId()));
        }
    }

    public SubTaskAndUser getSubTask(String taskId) {
        for (SubTaskAndUser subTaskAndUser : SUB_TASK_AND_USERS) {
            if (subTaskAndUser.getSubTaskId().equals(taskId)) {
                return subTaskAndUser;
            }
        }
        return null;
    }

    public SubTaskAndUser getTaskAndUser(String taskId, String userId) {
        for (SubTaskAndUser taskAndUser : SUB_TASK_AND_USERS) {
            if (taskAndUser.getUserId().equals(userId) && taskAndUser.getSubTaskId().equals(taskId)) {
                return taskAndUser;
            }
        }
        return null;
    }
}
