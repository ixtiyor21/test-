package set.up.service.auth;

import set.up.dao.auth.UserDao;
import set.up.enums.Role;
import set.up.model.auth.User;
import set.up.service.MainService;
import set.up.util.Color;
import set.up.util.Input;
import set.up.util.Print;

import java.util.Objects;

public class AuthService {
    public static void login() {
        String username = Input.getString("username = ");
        String password = Input.getString("password = ");

        UserDao userDao = new UserDao();
        User user = userDao.getUser(username, password);

        if (Objects.isNull(user)) {
            Print.println(Color.RED, "User not found !");
            return;
        }

        SessionService.setSession(user);
        if (user.getRole().equals(Role.ADMIN)) {
            AdminService.adminService();
            return;
        }
        if (user.getRole().equals(Role.USER)) {
            UserService.userService();
        }
    }

    public static void register() {
        String username = Input.getString("username = ");
        String password = Input.getString("password = ");

        UserDao userDao = new UserDao();
        User user = userDao.getUser(username);

        if (Objects.nonNull(user)) {
            Print.println(Color.RED, "Username is available !");
            return;
        }

        User newUser = new User(username, password, Role.USER);
        userDao.create(newUser);
        Print.println(Color.GREEN, "Successfully Register, Please Login Now !");
    }

    public static void logOut() {
        Print.println(Color.YELLOW, "Good bye " + SessionService.getSession().getUsername());
        SessionService.setSession(null);
        MainService.mainService();
    }
}
