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
}
