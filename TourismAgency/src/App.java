import core.Db;
import core.Helper;
import entity.User;
import view.LoginView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class App {
    public static void main(String[] args) {
  //     Connection connection = Db.getInstance();
        Helper.setTheme();
        LoginView loginView = new LoginView();
    }


}
