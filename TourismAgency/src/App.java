import business.UserManager;
import core.Db;
import core.Helper;
import entity.User;
import view.AdminView;
import view.LoginView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class App {
    public static void main(String[] args) {
  //     Connection connection = Db.getInstance();
//        Helper.setTheme();
//        LoginView loginView = new LoginView();
        UserManager userManager = new UserManager();
        AdminView adminView =new AdminView(userManager.findByLogin("admin","1234"));
    }


}
