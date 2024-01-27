package dao;

import core.Db;
import entity.Hotel;
import entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {
    private final Connection connection;

    public HotelDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Hotel> findAll(){
        ArrayList<Hotel> otelList = new ArrayList<>();
        Hotel hotel = null;
        String sql = "SELECT * FROM public.hotel";

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
