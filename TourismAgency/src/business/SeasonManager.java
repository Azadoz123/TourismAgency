package business;

import core.Helper;
import dao.SeasonDao;
import entity.Pension;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao = new SeasonDao();
    public Season getById(int id){
        return this.seasonDao.getById(id);
    }
    public ArrayList<Season> findAll(){
        return this.seasonDao.findAll();
    }
    public ArrayList<Season> getSeasonListByHotelId(int id){
        return this.seasonDao.getSeasonListByHotelId(id);
    }
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
    public boolean save(Season season){
        if (this.getById(season.getId())  != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.seasonDao.save(season);
    }
}
