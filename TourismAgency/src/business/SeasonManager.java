package business;

import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao = new SeasonDao();
    public Season getById(int id){
        return this.seasonDao.getById(id);
    }
    public ArrayList<Season> findALL(){
        return this.seasonDao.findAll();
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasonList){
        ArrayList<Object[]> seasonObjList = new ArrayList<>();
        for (Season season : seasons){
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
}
