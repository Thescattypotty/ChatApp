package Utils.Security;

import at.favre.lib.crypto.bcrypt.BCrypt;

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
        return BCrypt.withDefaults().hashToString(12, plainPassword.toCharArray());
    }

    public boolean matches(String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(plainPassword.toCharArray(), hashedPassword);
        return result.verified;
    }
}
