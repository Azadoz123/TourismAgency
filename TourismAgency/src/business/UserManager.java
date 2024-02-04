package business;

import core.Helper;
import dao.UserDao;
import entity.Hotel;
import entity.Pension;
import entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }
    //save user
    public boolean save(User user){
        if (user.getId() != 0) {
            Helper.showMessage("error");
            return false;
        }
        return this.userDao.save(user);
    }
    //get user by Id
    public User getById(int id){
        return this.userDao.getById(id);
    }
    public boolean update(User user){
        if(this.getById(user.getId()) == null){
            Helper.showMessage("notFound");
            return false;
        }
        return this.userDao.update(user);
    }
    //delete user
    public boolean delete(int id){
        if(this.userDao.getById(id) == null){
            Helper.showMessage(id + " ID kayıtlı marka bulunamadı");
            return false;
        }
        return this.userDao.delete(id);
    }
    //get user list as object
    public ArrayList<Object[]> getForTable(int size, ArrayList<User> userList){
        ArrayList<Object[]> brandRowList = new ArrayList<>();
        for (User user: userList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getPassword();
            rowObject[i++] = user.getRole();
            brandRowList.add(rowObject);
        }
        return brandRowList;
    }
    //get all user
    public ArrayList<User> findAll(){
        return this.userDao.findAll();
    }
    // find user

    public User findByLogin(String username, String password){
        return this.userDao.findByLogin(username,password);
    }
    //search user
    public ArrayList<User> searchForTable(User.Role userRole){
        String select = "SELECT * FROM public.user";
        ArrayList<String> whereList = new ArrayList<>();

        if (userRole != null){
            whereList.add(" role = " + "'"+ userRole + "'");
        }
        
        // System.out.println(whereList);
        String whereStr = String.join(" AND ", whereList );
      //  System.out.println(whereStr);
        String query = select;
        if (whereStr.length() > 0){
            query += " WHERE " + whereStr;
        }
        System.out.println(query);
        return this.userDao.selectByQuery(query);
    }
}
