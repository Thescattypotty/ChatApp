package Models;

import Utils.User.PasswordAuthenticatedUserInterface;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class User implements PasswordAuthenticatedUserInterface
{
    private int id;
    private String username;
    private String password;


    private String plainPassword;

    public User()
    {
        
    }
    //for new instance
    public User(String username, String plainPassword) {
        this.username = username;
        this.plainPassword = plainPassword;
        this.password = this.encryptPassword();
    }

    //for fetch from database
    public User(int id, String username, String Plainpassword) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    @Override
    public String getPlainPassword() {
        return plainPassword;
    }
    @Override
    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }


    @Override
    public String encryptPassword() {
        return BCrypt.withDefaults().hashToString(12, this.plainPassword.toCharArray());
    }

    @Override
    public boolean matches(String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
    }


}
