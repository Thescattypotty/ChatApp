package Utils.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import at.favre.lib.crypto.bcrypt.BCrypt;
import Repository.UserRepository;

public class UserProvider {
    private UserRepository userRepository;
    private Map<String, PasswordAuthenticatedUserInterface> authenticatedUsers;

    public UserProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.authenticatedUsers = new HashMap<>();
    }

    public PasswordAuthenticatedUserInterface authenticate(String username, String plainPassword) {
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

    public void logout(String username) {
        authenticatedUsers.remove(username);
    }

    public Map<String, PasswordAuthenticatedUserInterface> getAuthenticatedUsers() {
        return new HashMap<>(authenticatedUsers);
    }
}