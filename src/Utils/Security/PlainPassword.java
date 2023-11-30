package Utils.Security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PlainPassword {
    private String plainPassword;

    public PlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public String encryptPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(plainPassword);
    }

    public boolean matches(String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}
