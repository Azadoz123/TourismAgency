import business.UserManager;
import entity.User;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;

public class App {
    public static void main(String[] args) {
  //     Connection connection = Db.getInstance();
//        Helper.setTheme();
       // LoginView loginView = new LoginView();
        /*UserManager userManager = new UserManager();
        AdminView adminView =new AdminView(userManager.findByLogin("admin","1234"));*/
        /*User user = new User();
        user.setUsername("admin");
        AdminView adminView = new AdminView(user);*/
        UserManager userManager = new UserManager();
        EmployeeView hotelView = new EmployeeView(userManager.findByLogin("employee","123456"));
    }
}
