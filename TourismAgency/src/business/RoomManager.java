package business;

import core.Helper;
import dao.RoomDao;
import entity.Pension;
import entity.Room;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RoomManager {
    public final RoomDao roomDao;
    public final HotelManager hotelManager;
    public final PensionManager pensionManager;
    public RoomManager() {
        this.roomDao = new RoomDao();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
    }
    //get room list as object
    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> roomList){
        ArrayList<Object[]> roomObjList = new ArrayList<>();
        for (Room room : roomList){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = room.getId();
            rowObject[i++] = hotelManager.getById(room.getHotelId()).getName();
            rowObject[i++] = pensionManager.getById(room.getPensionId()).getType();
            rowObject[i++] = room.getType();
            rowObject[i++] = room.getStock();
            rowObject[i++] = room.getChildPrice();
            rowObject[i++] = room.getAdultPrice();
            rowObject[i++] = room.getNumberOfBed();
            rowObject[i++] = room.getAreaOfRoom();
            rowObject[i++] = room.isTV();
            rowObject[i++] = room.isBar();
            rowObject[i++] = room.isGameConsole();
            rowObject[i++] = room.isMoneyCase();
            rowObject[i++] = room.isProjection();
            roomObjList.add(rowObject);
        }
        return  roomObjList;
    }
    //save room
    public boolean save(Room room){
        if (this.getById(room.getId())  != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.roomDao.save(room);
    }
    //update room
    public boolean update(Room room){
        if(this.getById(room.getId()) == null){
            Helper.showMessage("notFound");
            return false;
        }
        return this.roomDao.update(room);
    }
    //get room by Id
    public Room getById(int id){
        return this.roomDao.getById(id);
    }
    //get all room
    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }
    //search room for reservation
    public ArrayList<Room> SearchForReservation(String hotelName, String hotelCity, String checkInDate, String checkOutDate, String countOfChild, String counOfAdult){
         String query = "Select * FROM public.room as r LEFT JOIN public.hotel as h ON r.hotel_id = h.id " +
                 "LEFT JOIN public.season as s ON r.season_id = s.id " +
                 "LEFT JOIN public.pension as p ON r.pension_id = s.id";

         ArrayList<String> where = new ArrayList<>();
         ArrayList<String> joinWhere = new ArrayList<>();


         /*joinWhere.add("r.hotel_id = h.id");
         joinWhere.add("r.season_id = s.id");
         joinWhere.add("r.pension_id = s.id");*/

        if (!hotelName.equals(null)) where.add("h.name = '" + hotelName+ "'");
     //  if (!hotelCity.equals(null)) where.add("h.city = '" + hotelCity +"'");
      //  if (checkInDate != null) where.add("s.")

        String whereStr = String.join(" AND ",where);
        String joinStr = String.join(" AND ", joinWhere);

        /*if(joinStr.length() > 0){
            query += " ON " +joinStr;
        }*/
        if(whereStr.length() > 0){
            query += " WHERE " + whereStr;
        }

        System.out.println(query);
        return this.roomDao.selectByQuery(query);
    }
    public int totalPriceForRoom(Room room){
        return 0;
    }
}
