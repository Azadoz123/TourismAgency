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
    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pensionList){
        ArrayList<Object[]> pensionObjList = new ArrayList<>();
        for (Pension pension : pensionList){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = pension.getId();
            rowObject[i++] = pension.getHotel().getName();
            if(pension.isUltraAllInclude()){
                rowObject[i++] = "ULTRA ALL INCLUDE";
            }
            if(pension.isAllIncule()){
                rowObject[i++] = "ALL INCLUDE";
            }
            if(pension.isRoomBreakfast()){
                rowObject[i++] = "ROOM BREAKFAST";
            }
            if(pension.isFullPension()){
                rowObject[i++] = "FULL PENSION";
            }
            if(pension.isHalfPension()){
                rowObject[i++] = "HALF PENSION";
            }
            if(pension.isOnlyBad()){
                rowObject[i++] = "ONLY BAD";
            }
            if(pension.isFullCreditNotIncludingAlcohol()){
                rowObject[i++] = "FULL CREDIT NOT INCLUDING ALCOHOL";
            }
            pensionObjList.add(rowObject);
        }
        return  pensionObjList;
    }
}
