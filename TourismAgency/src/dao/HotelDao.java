package dao;

import core.Db;
import entity.Hotel;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {
    private final Connection connection;

    public HotelDao() {
        this.connection = Db.getInstance();
    }
    // get all hotel
    public ArrayList<Hotel> findAll(){
        ArrayList<Hotel> otelList = new ArrayList<>();
        Hotel hotel = null;
        String sql = "SELECT * FROM public.hotel ORDER BY id ASC";

        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(sql);
            while (resultSet.next()){
                otelList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return otelList;
    }
    //save hotel
    public boolean save(Hotel hotel){
        String query = "INSERT INTO public.hotel (name,address,phone,star,car_parking,wifi,pool,fitness,concierge,spa,room_service) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1,hotel.getName());
            preparedStatement.setString(2,hotel.getAddress());
            preparedStatement.setString(3,hotel.getPhoneNumber());
            preparedStatement.setString(4,hotel.getStar());
            preparedStatement.setBoolean(5,hotel.isCar_parking());
            preparedStatement.setBoolean(6,hotel.isWifi());
            preparedStatement.setBoolean(7,hotel.isPool());
            preparedStatement.setBoolean(8,hotel.isFitness_center());
            preparedStatement.setBoolean(9,hotel.isConcierge());
            preparedStatement.setBoolean(10,hotel.isSpa());
            preparedStatement.setBoolean(11,hotel.isRoom_service());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //get hotel by Id
    public Hotel getById(int id){
        Hotel hotel = null;
        String query = "SELECT * FROM public.hotel WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                hotel = this.match(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotel;
    }
    //delete hotel
    public boolean delete(int id){
        String query = "DELETE FROM public.hotel WHERE id = ? ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //match database hotel
    public Hotel match(ResultSet resultSet) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(resultSet.getInt("id"));
        hotel.setName(resultSet.getString("name"));
        hotel.setAddress(resultSet.getString("address"));
        hotel.setPhoneNumber(resultSet.getString("phone"));
        hotel.setStar(resultSet.getString("star"));
        hotel.setCar_parking(resultSet.getBoolean("car_parking"));
        hotel.setWifi(resultSet.getBoolean("wifi"));
        hotel.setPool(resultSet.getBoolean("pool"));
        hotel.setFitness_center(resultSet.getBoolean("fitness"));
        hotel.setConcierge(resultSet.getBoolean("concierge"));
        hotel.setSpa(resultSet.getBoolean("spa"));
        hotel.setRoom_service(resultSet.getBoolean("room_service"));

        return hotel;
    }
}
