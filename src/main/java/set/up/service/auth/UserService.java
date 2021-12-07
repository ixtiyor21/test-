package set.up.service.auth;

import set.up.dao.task.SubTaskAndUserDao;
import set.up.dao.task.SubTaskDao;
import set.up.dao.task.TaskAndUserDao;
import set.up.dao.task.TaskDao;
import set.up.enums.Status;
import set.up.model.task.SubTask;
import set.up.model.task.SubTaskAndUser;
import set.up.model.task.Task;
import set.up.service.task.SubTaskService;
import set.up.service.task.TaskService;
import set.up.util.Color;
import set.up.util.Input;
import set.up.util.Print;
import set.up.util.Utils;

import java.util.Objects;

public class UserService {
    public static void userService() {
        run();
    }

    private static void run() {
        int choice = Utils.menu("My Task/Update Status/create SubTask/List SubTask/LogOut", 1, 5);

        switch (choice) {
            case 1:
                myTask();
                break;
            case 2:
                updateStatus();
                break;
            case 3:
                createSubTask();
                break;
            case 4:
                listSubTask();
                break;
            default:
                AuthService.logOut();
                return;
        }
        run();
    }

    private static void myTask() {
        TaskAndUserDao taskAndUserDao = new TaskAndUserDao();
        taskAndUserDao.list(SessionService.getSession().getId());
    }

    private static void updateStatus() {
        int menu = Utils.menu("Task/SubTask", 1, 2);

        if (menu == 1) {
            task();
        } else {
            subTask();
        }
    }

    private static void createSubTask() {
        TaskDao taskDao = new TaskDao();
        SubTaskDao subTaskDao = new SubTaskDao();
        SubTaskAndUserDao taskAndUserDao = new SubTaskAndUserDao();
        TaskAndUserDao taskAndUserDao1 = new TaskAndUserDao();
        taskAndUserDao1.list(SessionService.getSession().getId());

        String taskTitle = Input.getString("Task Title = ");
        Task task = taskDao.getTask(taskTitle);

        if (Objects.isNull(task)) {
            Print.println(Color.RED, "Task not found !");
            return;
        }

        String subTaskTitle = Input.getString("SubTask title = ");
        String description = Input.getString("Description = ");

        SubTask subTask = new SubTask(subTaskTitle, description, Status.NEW, task.getId());
        subTaskDao.create(subTask);
        SubTaskAndUser taskAndUser = new SubTaskAndUser(SessionService.getSession().getId(), subTask.getId());
        taskAndUserDao.create(taskAndUser);
        Print.println(Color.GREEN, "Successfully created SubTask !");
    }

    private static void listSubTask() {
        TaskDao taskDao = new TaskDao();
        SubTaskAndUserDao taskAndUserDao = new SubTaskAndUserDao();
        TaskAndUserDao taskAndUserDao1 = new TaskAndUserDao();
        taskAndUserDao1.list(SessionService.getSession().getId());

        String taskTitle = Input.getString("Task Title = ");
        Task task = taskDao.getTask(taskTitle);

        if (Objects.isNull(task)) {
            Print.println(Color.RED, "Task not found !");
            return;
        }

        taskAndUserDao.getList();
    }

    private static void task() {
        TaskService.update();
    }

    private static void subTask() {
        SubTaskService.update();
    }
}
