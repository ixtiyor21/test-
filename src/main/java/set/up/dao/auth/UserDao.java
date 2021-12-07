package set.up.dao.auth;

import set.up.enums.Role;
import set.up.interfaces.BaseDao;
import set.up.model.auth.User;
import set.up.util.Color;
import set.up.util.Print;

import java.util.ArrayList;

public class UserDao implements BaseDao<User> {
    private static final ArrayList<User> USERS = new ArrayList<>();

    @Override
    public void create(User user) {
        USERS.add(user);
    }

    @Override
    public void delete(User user) {
        USERS.remove(user);
    }

    @Override
    public void list() {
        for (User user : USERS) {
            Print.println(Color.GREEN, user);
        }
    }

    public void list(Role role) {
        for (User user : USERS) {
            if (user.getRole().equals(role))
                Print.println(Color.GREEN, user);
        }
    }

    public User getUser(String username) {
        for (User user : USERS) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User getUser(String username, String password) {
        for (User user : USERS) {
            if (user.getUsername().equals(username) && user.getUsername().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public User getUserById(String id) {
        for (User user : USERS) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
