package set.up.service.auth;

import set.up.model.auth.User;

public class SessionService {
    private static User SESSION_USER = null;

    public static User getSession() {
        return SESSION_USER;
    }

    public static void setSession(User user) {
        SESSION_USER = user;
    }
}
