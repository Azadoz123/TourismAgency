package business;

import core.Helper;
import dao.SeasonDao;
import entity.Pension;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;
    public SeasonManager() {
         seasonDao = new SeasonDao();
    }
    //get season by Id
    public Season getById(int id){
        return this.seasonDao.getById(id);
    }
    //get all season
    public ArrayList<Season> findAll(){
        return this.seasonDao.findAll();
    }
    //get season list by hotel Id
    public ArrayList<Season> getSeasonListByHotelId(int id){
        return this.seasonDao.getSeasonListByHotelId(id);
    }
    //get season list as object
    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasonList){
        ArrayList<Object[]> seasonObjList = new ArrayList<>();
        for (Season season : seasonList){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = season.getId();
            rowObject[i++] = season.getHotel().getName();
            rowObject[i++] = season.getStartTime();
            rowObject[i++] = season.getFinishTime();
            seasonObjList.add(rowObject);
        }
        return seasonObjList;
    }
    //save season
    public boolean save(Season season){
        if (this.getById(season.getId())  != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.seasonDao.save(season);
    }
}
