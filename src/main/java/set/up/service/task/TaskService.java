package set.up.service.task;

import set.up.dao.auth.UserDao;
import set.up.dao.task.TaskAndUserDao;
import set.up.dao.task.TaskDao;
import set.up.enums.Role;
import set.up.enums.Status;
import set.up.model.auth.User;
import set.up.model.task.Task;
import set.up.model.task.TaskAndUser;
import set.up.service.auth.SessionService;
import set.up.util.Color;
import set.up.util.Input;
import set.up.util.Print;
import set.up.util.Utils;

import java.util.Objects;

public class TaskService {
    public static void create() {
        UserDao userDao = new UserDao();
        TaskDao taskDao = new TaskDao();
        TaskAndUserDao taskAndUserDao = new TaskAndUserDao();
        userDao.list(Role.USER);

        String title = Input.getString("Task title = ");
        String description = Input.getString("Task Description = ");
        String username = Input.getString("Username = ");

        Task task = taskDao.getTask(title);
        User user = userDao.getUser(username);

        if (Objects.isNull(user)) {
            Print.println(Color.RED, "User not found !");
            return;
        }

        if (Objects.nonNull(task)) {
            TaskAndUser taskAndUser = taskAndUserDao.getTaskAndUser(task.getId(), user.getId());
            if (Objects.nonNull(taskAndUser)) {
                Print.println(Color.RED, "Task title is available !");
                return;
            }
            Task newTask = new Task(title, description, Status.NEW);
            taskDao.create(newTask);
            TaskAndUser newTaskAndUser = new TaskAndUser(user.getId(), newTask.getId());
            taskAndUserDao.create(newTaskAndUser);
            Print.println(Color.RED, "Successfully created Task !");
            return;
        }

        Task task1 = new Task(title, description, Status.NEW);
        taskDao.create(task1);
        TaskAndUser taskAndUser = new TaskAndUser(user.getId(), task1.getId());
        taskAndUserDao.create(taskAndUser);
        Print.println(Color.GREEN, "Successfully created Task !");
    }

    public static void delete() {
        TaskDao taskDao = new TaskDao();
        TaskAndUserDao taskAndUserDao = new TaskAndUserDao();

        String title = Input.getString("Title = ");
        Task task = taskDao.getTask(title);

        if (Objects.isNull(task)) {
            Print.println(Color.RED, "Task not found !");
            return;
        }

        TaskAndUser taskAndUser = taskAndUserDao.getTaskAndUser(task.getId());
        taskDao.delete(task);
        taskAndUserDao.delete(taskAndUser);
        Print.println(Color.GREEN, "Successfully deleted Task !");
    }

    public static void update() {
        TaskAndUserDao taskAndUserDao = new TaskAndUserDao();
        taskAndUserDao.list(SessionService.getSession().getId());
        String title = Input.getString("Task title = ");

        TaskDao taskDao = new TaskDao();
        Task task = taskDao.getTask(title);

        if (Objects.isNull(task)) {
            Print.println(Color.GREEN, "Task not Found !");
            return;
        }

        updateMenu(task);
    }

    public static void list() {
        TaskAndUserDao taskAndUserDao = new TaskAndUserDao();
        taskAndUserDao.list();
    }

    public static void updateMenu(Task task) {
        int choice = Utils.menu("INPROGRESS/DONE/COMPLATED", 1, 3);
        switch (choice) {
            case 1:
                inprogress(task);
                return;
            case 2:
                done(task);
                return;
            default:
                complated(task);
        }
    }

    private static void inprogress(Task task) {
        task.setStatus(Status.INPROGRESS);
        Print.println(Color.GREEN, "Successfully update !");
    }

    private static void done(Task task) {
        task.setStatus(Status.DONE);
        Print.println(Color.GREEN, "Successfully update !");
    }

    private static void complated(Task task) {
        task.setStatus(Status.COMPLATED);
        Print.println(Color.GREEN, "Successfully update !");
    }
}
