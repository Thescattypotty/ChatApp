package Utils.User;

public interface PasswordAuthenticatedUserInterface extends UserInterface
{
    String getPlainPassword();
    void setPlainPassword(String plainPassword);
    String encryptPassword();
    boolean matches(String hashedPassword);
    int getId();
}
