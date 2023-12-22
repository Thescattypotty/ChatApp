package Models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Profile {
    private int id;
    private int userId;
    private String firstname;
    private String lastname;
    private int age;
    private byte[] profileImage;
    public Status status;


    public Profile(int id, int userId, String firstname, String lastname, int age, byte[] profileImage) {
        this.id = id;
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.profileImage = profileImage;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public byte[] getProfileImage() {
        return profileImage;
    }


    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
    
    public void saveProfileImageToFile(String FilePath)
    {
        try(OutputStream outputStream = new FileOutputStream(FilePath))
        {
            outputStream.write(profileImage);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
}