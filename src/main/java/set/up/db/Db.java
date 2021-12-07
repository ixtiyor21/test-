package set.up.db;

import set.up.dao.auth.UserDao;
import set.up.enums.Role;
import set.up.model.auth.User;

public class Db {
    public static void init() {
        UserDao userDao = new UserDao();
        User admin = new User("admin", "admin", Role.ADMIN);
        userDao.create(admin);
    }
}
