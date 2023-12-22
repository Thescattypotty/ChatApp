package Utils.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import Controllers.Main.MessagerieController;
import Repository.UserRepository;
import Utils.Listeners.Listener;
import Utils.Listeners.UserListener;
import Utils.Services.UserService;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class UserProvider {
    protected UserRepository userRepository;
    protected UserService userService;
    protected Map<String, PasswordAuthenticatedUserInterface> authenticatedUsers;

    public UserProvider() {
        this.userRepository = new UserRepository();
        this.authenticatedUsers = new ConcurrentHashMap<>();
        this.userService = UserService.getInstance();
    }

    public static UserProvider instance = new UserProvider();

    public static UserProvider getInstance()
    {
        return instance;
    }

    protected Boolean authenticate(String username, String plainPassword) 
    {
        try {
            PasswordAuthenticatedUserInterface user = userRepository.getUser(username);
            
            if (user != null && BCrypt.verifyer().verify(plainPassword.toCharArray(), userRepository.getPassword(username)).verified ) {
                authenticatedUsers.put(username, user);
                userService.setUser(user);
                return true;
            }

            return false; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    protected void logout(String username) {
        userService.setUser(null);
        authenticatedUsers.remove(username);

    }

    public Map<String, PasswordAuthenticatedUserInterface> getAuthenticatedUsers() {
        return new HashMap<>(authenticatedUsers);
  
    }
}