package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;
import entity.User;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;
    private  PensionManager pensionManager;
    private  SeasonManager seasonManager;
    private RoomManager roomManager;
    public HotelManager() {

        this.hotelDao = new HotelDao();
    }
    //get hotel list as object
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> brandRowList = new ArrayList<>();
        for (Hotel hotel: this.findAll()) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = hotel.getId();
            rowObject[i++] = hotel.getName();
            rowObject[i++] = hotel.getAddress();
            rowObject[i++] = hotel.getPhoneNumber();
            rowObject[i++] = hotel.getStar();
            rowObject[i++] = hotel.isCar_parking();
            rowObject[i++] = hotel.isWifi();
            rowObject[i++] = hotel.isPool();
            rowObject[i++] = hotel.isFitness_center();
            rowObject[i++] = hotel.isConcierge();
            rowObject[i++] = hotel.isSpa();
            rowObject[i++] = hotel.isRoom_service();
            brandRowList.add(rowObject);
        }
        return brandRowList;
    }
    //get all hotel
    public ArrayList<Hotel> findAll(){
        return this.hotelDao.findAll();
    }
    //save hotel
    public boolean save(Hotel hotel){
        if(hotel.getId() != 0){
            Helper.showMessage("error");
        }
        return this.hotelDao.save(hotel);
    }
    //delete hotel
    public boolean delete(int id){
        if(this.hotelDao.getById(id) == null){
            Helper.showMessage(id + " ID kayıtlı marka bulunamadı");
            return false;
        }

        return this.hotelDao.delete(id);
    }
    //get hotel by Id
    public Hotel getById(int id){
        return this.hotelDao.getById(id);
    }
}
