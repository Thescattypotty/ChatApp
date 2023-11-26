package Models;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class User {}
/* 

@Entity
@Table(name = "User")
public class User {
    
    @Id
    @Column(name="User_Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Column(name="username",length=30, nullable=false)
    private String username;

    @Column(name="password",length=100, nullable=false)
    private String Password;


    private String PlainPassword;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="CreatedAt",updatable=false)
    private Calendar CreatedAt;


    @Temporal(TemporalType.DATE)
    @Column(name ="UpdatedAt")
    private Calendar UpdatedAt;

    public User() {
    }


    public Long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPlainPassword() {
        return PlainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        PlainPassword = plainPassword;
    }


    
}
*/