package Models;

import Utils.Security.PlainPassword;

public class User {
    private int id;
    private String username;
    private String password;


    private PlainPassword plainPassword;

    public User()
    {
        
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public PlainPassword getPlainPassword() {
        return plainPassword;
    }
    
    public void setPassword(String password) throws Exception
    {
        if(password == null || password.isEmpty() || password.isBlank())
        {
            throw new Exception("Password should not be empty");
        }
        else
        {
            this.plainPassword = new PlainPassword(password);
            this.password = this.plainPassword.getPlainPassword();
        }
    }

    public void setPlainPassword(PlainPassword plainPassword) {
        this.plainPassword = plainPassword;
        this.password = plainPassword.getPlainPassword();
    }

}
