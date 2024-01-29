package business;

import dao.PensionDao;
import entity.Pension;

import java.util.ArrayList;

public class PensionManager {
    public final PensionDao pensionDao = new PensionDao();
    public Pension getById(int id){
        return this.pensionDao.getById(id);
    }
    public ArrayList<Pension> findAll(){
        return this.pensionDao.findAll();
    }
}
