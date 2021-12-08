package set.up.service.auth;

import set.up.service.task.SubTaskService;
import set.up.service.task.TaskService;
import set.up.util.Utils;

public class AdminService {
    public static void adminService() {
        run();
    }

    private static void run() {
        int choice = Utils.menu("Create Task/Delete Task/Update Task/List Task/Create SubTask/Delete SubTask/Update SubTask/List SubTask/LogOut", 1, 9);

        switch (choice) {
            case 1:
                createTask();
                break;
            case 2:
                deleteTask();
                break;
            case 3:
                updateTask();
                System.out.println("adfwegfwegf");
                break;
            case 4:
                listTask();
                return;
            case 5:
                createSubTask();
                break;
            case 6:
                deleteSubTask();
                break;
            case 7:
                updateSubTask();
                break;
            case 8:
                listSubTask();
                break;
            default:
                AuthService.logOut();
                return;
        }
        run();
    }

    private static void createTask() {
        TaskService.create();
    }

    private static void deleteTask() {
        TaskService.delete();
    }

    private static void updateTask() {
        TaskService.update();
    }

    private static void listTask() {
        TaskService.list();
    }

    private static void createSubTask() {
        SubTaskService.create();
    }

    private static void deleteSubTask() {
        SubTaskService.delete();
    }

    private static void updateSubTask() {
        SubTaskService.update();
    }

    private static void listSubTask() {
        SubTaskService.list();
    }
}
