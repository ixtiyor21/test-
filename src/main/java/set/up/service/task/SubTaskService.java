package set.up.service.task;

import set.up.dao.auth.UserDao;
import set.up.dao.task.SubTaskAndUserDao;
import set.up.dao.task.SubTaskDao;
import set.up.dao.task.TaskDao;
import set.up.enums.Role;
import set.up.enums.Status;
import set.up.model.auth.User;
import set.up.model.task.SubTask;
import set.up.model.task.SubTaskAndUser;
import set.up.model.task.Task;
import set.up.util.Color;
import set.up.util.Input;
import set.up.util.Print;
import set.up.util.Utils;

import java.util.Objects;

public class SubTaskService {
    public static void create() {
        TaskDao taskDao = new TaskDao();
        SubTaskDao subTaskDao = new SubTaskDao();
        UserDao userDao = new UserDao();
        SubTaskAndUserDao taskAndUserDao = new SubTaskAndUserDao();
        taskDao.list();
        String taskTitle = Input.getString("Task Title = ");

        Task task = taskDao.getTask(taskTitle);

        if (Objects.isNull(task)) {
            Print.println(Color.RED, "Task not found !");
            return;
        }

        String subTaskTitle = Input.getString("SubTask title = ");
        String description = Input.getString("Description = ");
        userDao.list(Role.USER);
        String username = Input.getString("Username = ");
        User user = userDao.getUser(username);
        if (Objects.isNull(user)) {
            Print.println(Color.RED, "User not found !");
            return;
        }

        SubTask subTask = new SubTask(subTaskTitle, description, Status.NEW, task.getId());
        subTaskDao.create(subTask);
        SubTaskAndUser taskAndUser = new SubTaskAndUser(user.getId(), subTask.getId());
        taskAndUserDao.create(taskAndUser);
        Print.println(Color.GREEN, "Successfully created SubTask !");
    }

    public static void delete() {
        SubTaskDao subTaskDao = new SubTaskDao();
        SubTaskAndUserDao taskAndUserDao = new SubTaskAndUserDao();
        String taskTitle = Input.getString("Task Title = ");

        SubTask task = subTaskDao.getTask(taskTitle);

        if (Objects.isNull(task)) {
            Print.println(Color.RED, "Task not found !");
            return;
        }

        SubTaskAndUser subTask = taskAndUserDao.getSubTask(task.getId());
        subTaskDao.delete(task);
        taskAndUserDao.delete(subTask);
        Print.println(Color.GREEN, "Successfully Deleted !");
    }

    public static void update() {
        SubTaskAndUserDao taskAndUserDao = new SubTaskAndUserDao();
        taskAndUserDao.list();
        String title = Input.getString("Sub Task title = ");

        SubTaskDao subTask = new SubTaskDao();
        SubTask task = subTask.getTask(title);

        if (Objects.isNull(task)) {
            Print.println(Color.GREEN, "Task not Found !");
            return;
        }

        updateMenu(task);
    }

    public static void list() {
        SubTaskAndUserDao taskAndUserDao = new SubTaskAndUserDao();
        taskAndUserDao.list();
    }

    public static void updateMenu(SubTask subTask) {
        int choice = Utils.menu("INPROGRESS/DONE/COMPLATED", 1, 3);
        switch (choice) {
            case 1:
                inprogress(subTask);
                return;
            case 2:
                done(subTask);
                return;
            default:
                complated(subTask);
        }
    }

    private static void inprogress(SubTask task) {
        task.setStatus(Status.INPROGRESS);
        Print.println(Color.GREEN, "Successfully update !");
    }

    private static void done(SubTask task) {
        task.setStatus(Status.DONE);
        Print.println(Color.GREEN, "Successfully update !");
    }

    private static void complated(SubTask task) {
        task.setStatus(Status.COMPLATED);
        Print.println(Color.GREEN, "Successfully update !");
    }
}
