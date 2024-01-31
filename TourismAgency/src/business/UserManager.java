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
    public boolean save(User user){
        if (user.getId() != 0) {
            Helper.showMessage("error");
            return false;
        }
        return this.userDao.save(user);
    }
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
    public boolean delete(int id){
        if(this.userDao.getById(id) == null){
            Helper.showMessage(id + " ID kayıtlı marka bulunamadı");
            return false;
        }
        return this.userDao.delete(id);
    }
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> brandRowList = new ArrayList<>();
        for (User user: this.findAll()) {
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
    public ArrayList<User> findAll(){
        return this.userDao.findAll();
    }

    public User findByLogin(String username, String password){
        return this.userDao.findByLogin(username,password);
    }
}
