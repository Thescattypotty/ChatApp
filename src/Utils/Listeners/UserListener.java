package Utils.Listeners;

import Controllers.Main.MessagerieController;
import Utils.User.PasswordAuthenticatedUserInterface;

public class UserListener {

    public static void OnLogin(PasswordAuthenticatedUserInterface user, MessagerieController controller) {
        //
    }

    public static void OnLogout(PasswordAuthenticatedUserInterface user) {
        // here eliminate User From ClientOnServer
    }

    public static void OnRegister(PasswordAuthenticatedUserInterface user) {
        // here i will do event on Register to modifie Password
    }

    public static void OnUserUpdate(PasswordAuthenticatedUserInterface user) {
        // here i will do event on Register to modifie Password
    }

}
