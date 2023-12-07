package Utils.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import at.favre.lib.crypto.bcrypt.BCrypt;
import Repository.UserRepository;

public class UserProvider {
    protected UserRepository userRepository;
    protected Map<String, PasswordAuthenticatedUserInterface> authenticatedUsers;

    public UserProvider() {
        this.userRepository = new UserRepository();
        this.authenticatedUsers = new HashMap<>();
    }

    protected PasswordAuthenticatedUserInterface authenticate(String username, String plainPassword) {
        try {
            PasswordAuthenticatedUserInterface user = userRepository.getUser(username);

            if (user != null && BCrypt.verifyer().verify(plainPassword.toCharArray(), user.getPassword()).verified) {
                authenticatedUsers.put(username, user);
                return user;
            }

            return null; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    protected void logout(String username) {
        authenticatedUsers.remove(username);
    }

    protected Map<String, PasswordAuthenticatedUserInterface> getAuthenticatedUsers() {
        return new HashMap<>(authenticatedUsers);
  
    }
}