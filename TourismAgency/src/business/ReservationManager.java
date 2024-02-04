package business;

import core.Helper;
import dao.ReservationDao;
import entity.Reservation;
import entity.Room;
import entity.User;

import java.util.ArrayList;

public class ReservationManager {
    public final ReservationDao reservationDao;
    public final HotelManager hotelManager;
    public final RoomManager roomManager;
    public ReservationManager() {
        this.reservationDao = new ReservationDao();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
    }
    //get reservation list as object
    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservationList){
        ArrayList<Object[]> reservationObjList = new ArrayList<>();
        for (Reservation reservation : reservationList){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = reservation.getId();
            rowObject[i++] = this.hotelManager.getById(this.roomManager.getById(reservation.getRoom_id()).getHotelId()).getName();
            rowObject[i++] = String.valueOf(reservation.getCheckInDate());
            rowObject[i++] = String.valueOf(reservation.getCheckOutDate());
            rowObject[i++] = String.valueOf(reservation.getTotalPrice());
            rowObject[i++] = String.valueOf(reservation.getGuestCount());
            rowObject[i++] = reservation.getGuestName();
            rowObject[i++] = reservation.getGuestCitizenId();
            rowObject[i++] = reservation.getGuestMail();
            rowObject[i++] = reservation.getGueastPhone();
            reservationObjList.add(rowObject);
        }
        return  reservationObjList;
    }
    //save reservation
    public boolean save(Reservation reservation){
        if(this.getById(reservation.getId()) != null){
            Helper.showMessage("error");
            return false;
        }
        return this.reservationDao.save(reservation);
    }
    //delete reservation
    public boolean delete(int id){
        if(this.reservationDao.getById(id) == null){
            Helper.showMessage(id + " ID kayıtlı marka bulunamadı");
            return false;
        }
        return this.reservationDao.delete(id);
    }
    //update reservation
    public boolean update(Reservation reservation){
        if(this.getById(reservation.getId()) == null){
            Helper.showMessage("notFound");
            return false;
        }
        return this.reservationDao.update(reservation);
    }
    //get reservation by Id
    public Reservation getById(int id){
        return this.reservationDao.getById(id);
    }
    //get all reservation
    public ArrayList<Reservation> findAll(){
        return this.reservationDao.findAll();
    }
}
