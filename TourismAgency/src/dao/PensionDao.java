package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {
    private final Connection connection;
    private final HotelDao hotelDao = new HotelDao();
    public PensionDao() {
        this.connection = Db.getInstance();
    }
    public Pension getById(int id){
        Pension pension = null;
        String query = "SELECT * FROM public.pension WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) pension = this.match(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pension;
    }
    public ArrayList<Pension> findAll(){
        return selectByQuery("SELECT * FROM public.pension ORDER BY id ASC");
    }
    public ArrayList<Pension> selectByQuery(String query){
        ArrayList<Pension> pensionList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                pensionList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pensionList;
    }

    /*public boolean save(Pension pension){
        String query = "INSERT INTO public.pension" +
                "(" +
                "ultra_all_include," +
                "all_include," +
                "room_breakfast," +
                "full_pension," +
                "half_pension," +
                "only_bed," +
                "full_credit_not_including_alcohol," +
                "hotel_id" +
                ")" +
                " VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setBoolean(1,pension.isUltraAllInclude());
            preparedStatement.setBoolean(2,pension.isAllIncule());
            preparedStatement.setBoolean(3,pension.isRoomBreakfast());
            preparedStatement.setBoolean(4,pension.isFullPension());
            preparedStatement.setBoolean(5,pension.isHalfPension());
            preparedStatement.setBoolean(6,pension.isOnlyBad());
            preparedStatement.setBoolean(7,pension.isFullCreditNotIncludingAlcohol());
            preparedStatement.setInt(8,pension.getHotel_id());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
    public boolean save(Pension pension){
        String query = "INSERT INTO public.pension" +
                "(" +
                "type," +
                "hotel_id" +
                ")" +
                " VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1,pension.getType().toString());
            preparedStatement.setInt(2,pension.getHotel_id());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.pension WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*public Pension match(ResultSet resultSet) throws SQLException {
        Pension pension =new Pension();
        pension.setId(resultSet.getInt("id"));
        pension.setUltraAllInclude(resultSet.getBoolean("ultra_all_include"));
        pension.setAllIncule(resultSet.getBoolean("all_include"));
        pension.setRoomBreakfast(resultSet.getBoolean("room_breakfast"));
        pension.setFullPension(resultSet.getBoolean("full_pension"));
        pension.setHalfPension(resultSet.getBoolean("half_pension"));
        pension.setOnlyBad(resultSet.getBoolean("only_bed"));
        pension.setFullCreditNotIncludingAlcohol(resultSet.getBoolean("full_credit_not_including_alcohol"));
        pension.setHotel_id(resultSet.getInt("hotel_id"));
        pension.setHotel(this.hotelDao.getById(resultSet.getInt("hotel_id")));
        return pension;
    }*/
    public Pension match(ResultSet resultSet) throws SQLException {
        Pension pension =new Pension();
        pension.setId(resultSet.getInt("id"));
        pension.setType(Pension.Type.valueOf(resultSet.getString("type")));
        pension.setHotel_id(resultSet.getInt("hotel_id"));
        pension.setHotel(this.hotelDao.getById(resultSet.getInt("hotel_id")));
        return pension;
    }
}
