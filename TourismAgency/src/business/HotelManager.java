package business;

import core.Helper;
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
    public boolean save(Hotel hotel){
        if(hotel.getId() != 0){
            Helper.showMessage("error");
        }
        return this.hotelDao.save(hotel);
    }
}
