package set.up.util;

import set.up.dao.auth.UserDao;
import set.up.enums.Role;
import set.up.model.auth.User;

import java.util.Objects;

import static set.up.util.Color.RED;

public class Utils {
    public static int menu(String text, int begin, int end) {
        genMenu(text);
        String choice = Input.getString("\n>>>");
        Integer integer = toInt(choice);

        if (integer == null) {
            Print.println(RED, "Wrong menu !");
            return menu(text, begin, end);
        }

        if (integer < begin || integer > end) {
            Print.println(RED, "Wrong menu !");
            return menu(text, begin, end);
        }
        return integer;
    }

    private static void genMenu(String text) {
        String[] split = text.split("/");
        int length = getMaxLength(split);
        int counter = 1;
        for (int i = 0; i < split.length; i++) {
            if (i > 0 && i % 2 == 0) {
                System.out.println();
            }
            Print.print(counter + ") " + split[i] + " ".repeat((length - split[i].length()) + 1));
            counter++;
        }
    }

    private static int getMaxLength(String[] split) {
        int max = 0;
        for (String s : split) {
            if (max < s.length()) {
                max = s.length();
            }
        }
        return max;
    }

    public static Integer toInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            return null;
        }
    }

    public static void createUser(Role role, String message) {
        String username = Input.getString("username = ");
        String password = Input.getString("password = ");

        UserDao userDao = new UserDao();
        User user = userDao.getUser(username);

        if (Objects.nonNull(user)) {
            Print.println(Color.RED, "Username is available !");
            return;
        }

        User newUser = new User(username, password, role);
        userDao.create(newUser);
        Print.println(Color.GREEN, message);
    }
}
