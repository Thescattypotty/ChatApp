package Utils.Services;

import Utils.User.PasswordAuthenticatedUserInterface;

public class UserService {

    private static UserService instance;
    private PasswordAuthenticatedUserInterface currentUser;

    private UserService() {

    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public PasswordAuthenticatedUserInterface getUser() {
        return currentUser;
    }

    public void setUser(PasswordAuthenticatedUserInterface user) {
        this.currentUser = user;
    }
}
