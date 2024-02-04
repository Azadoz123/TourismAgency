package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;
import entity.Season;

import java.util.ArrayList;

public class PensionManager {
    public final PensionDao pensionDao = new PensionDao();
    //get pension by Id
    public Pension getById(int id){
        return this.pensionDao.getById(id);
    }
    //get all pension
    public ArrayList<Pension> findAll(){
        return this.pensionDao.findAll();
    }
   // get pension list by hotel Id
    public ArrayList<Pension> getPensionListByHotelId(int id){
        return this.pensionDao.getPensionListByHotelId(id);
    }
    //get pension list as object
    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pensionList){
        ArrayList<Object[]> pensionObjList = new ArrayList<>();
        for (Pension pension : pensionList){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = pension.getId();
            rowObject[i++] = pension.getHotel().getName();
            rowObject[i++] = pension.getType();
            pensionObjList.add(rowObject);
        }
        return  pensionObjList;
    }
    //save pension
    public boolean save(Pension pension){
        if (this.getById(pension.getId())  != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.pensionDao.save(pension);
    }
}
