package set.up.service;

import set.up.service.auth.AuthService;
import set.up.util.Color;
import set.up.util.Print;
import set.up.util.Utils;

public class MainService {
    public static void mainService() {
        run();
    }

    private static void run() {
        int choice = Utils.menu("Login/Register/Exit", 1, 3);

        if (choice == 1) {
            AuthService.login();
        } else if (choice == 2) {
            AuthService.register();
        } else {
            Print.println(Color.YELLOW, "See you !");
            return;
        }
        run();
    }
}
