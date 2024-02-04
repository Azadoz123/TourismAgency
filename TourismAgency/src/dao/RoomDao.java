package dao;

import business.HotelManager;
import business.PensionManager;
import business.SeasonManager;
import core.Db;
import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    private final Connection connection;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    public RoomDao() {
        this.connection = Db.getInstance();
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
    }
    //save room
    public boolean save(Room room){
        String query = "INSERT INTO public.room" +
                "(" +
                "number_of_bed," +
                "area_of_number," +
                "tv," +
                "bar," +
                "game_console," +
                "money_case," +
                "projection," +
                "stock," +
                "child_price," +
                "adult_price," +
                "hotel_id," +
                "season_id," +
                "pension_id," +
                "type" +
                ")" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,room.getNumberOfBed());
            preparedStatement.setInt(2,room.getAreaOfRoom());
            preparedStatement.setBoolean(3,room.isTV());
            preparedStatement.setBoolean(4,room.isBar());
            preparedStatement.setBoolean(5,room.isGameConsole());
            preparedStatement.setBoolean(6,room.isMoneyCase());
            preparedStatement.setBoolean(7,room.isProjection());
            preparedStatement.setInt(8,room.getStock());
            preparedStatement.setDouble(9,room.getChildPrice());
            preparedStatement.setDouble(10,room.getAdultPrice());
            preparedStatement.setInt(11,room.getHotelId());
            preparedStatement.setInt(12,room.getSeasonId());
            preparedStatement.setInt(13,room.getPensionId());
            preparedStatement.setString(14,String.valueOf(room.getType()));
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //update room
    public boolean update(Room room){
        String query = "UPDATE public.room SET " +

                "number_of_bed = ?," +
                "area_of_number = ?," +
                "tv = ?," +
                "bar = ?," +
                "game_console = ?," +
                "money_case = ?," +
                "projection = ?," +
                "stock = ?," +
                "child_price = ?," +
                "adult_price = ?," +
                "hotel_id = ?," +
                "season_id = ?," +
                "pension_id = ?," +
                "type = ?" +
                " WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,room.getNumberOfBed());
            preparedStatement.setInt(2,room.getAreaOfRoom());
            preparedStatement.setBoolean(3,room.isTV());
            preparedStatement.setBoolean(4,room.isBar());
            preparedStatement.setBoolean(5,room.isGameConsole());
            preparedStatement.setBoolean(6,room.isMoneyCase());
            preparedStatement.setBoolean(7,room.isProjection());
            preparedStatement.setInt(8,room.getStock());
            preparedStatement.setDouble(9,room.getChildPrice());
            preparedStatement.setDouble(10,room.getAdultPrice());
            preparedStatement.setInt(11,room.getHotelId());
            preparedStatement.setInt(12,room.getSeasonId());
            preparedStatement.setInt(13,room.getPensionId());
            preparedStatement.setString(14,String.valueOf(room.getType()));
            preparedStatement.setInt(15,room.getId());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //get room by Id
    public Room getById(int id){
        Room room = null;
        String query = "SELECT * FROM public.room WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) room = this.match(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return room;
    }
    //get all room
    public ArrayList<Room> findAll(){
        ArrayList<Room> roomList = new ArrayList<>();
        Room room = null;
        String sql = "SELECT * FROM public.room ORDER BY id ASC";

        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(sql);
            while (resultSet.next()){
                roomList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }
    //query for database
    public ArrayList<Room> selectByQuery(String query){
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                roomList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }
    //match room database
    public Room match(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getInt("id"));
        room.setStock(resultSet.getInt("stock"));
        room.setChildPrice(resultSet.getDouble("child_price"));
        room.setAdultPrice(resultSet.getDouble("adult_price"));
        room.setNumberOfBed(resultSet.getInt("number_of_bed"));
        room.setAreaOfRoom(resultSet.getInt("area_of_number"));
        room.setTV(resultSet.getBoolean("tv"));
        room.setBar(resultSet.getBoolean("bar"));
        room.setGameConsole(resultSet.getBoolean("game_console"));
        room.setMoneyCase(resultSet.getBoolean("money_case"));
        room.setProjection(resultSet.getBoolean("projection"));
        room.setHotelId(resultSet.getInt("hotel_id"));
        room.setHotel(this.hotelManager.getById(resultSet.getInt("hotel_id")));
        room.setSeasonId(resultSet.getInt("season_id"));
        room.setSeason(this.seasonManager.getById(resultSet.getInt("season_id")));
        room.setPensionId(resultSet.getInt("pension_id"));
        room.setPension(this.pensionManager.getById(resultSet.getInt("pension_id")));
        room.setType(Room.Type.valueOf(resultSet.getString("type")));
        return room;
    }
}
