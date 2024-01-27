package business;

import dao.HotelDao;
import dao.UserDao;
import entity.Hotel;
import view.AdminView;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager() {

        this.hotelDao = new HotelDao();
    }
    public ArrayList<Hotel> findAll(){
        return this.hotelDao.findAll();
    }
}
