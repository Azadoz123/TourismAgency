package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;
import entity.Season;

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
    //get pension by Id
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
    //get all pension
    public ArrayList<Pension> findAll(){
        return selectByQuery("SELECT * FROM public.pension ORDER BY id ASC");
    }
    //get pension list by Hotel Id
    public ArrayList<Pension> getPensionListByHotelId(int id){
        String query= "SELECT * FROM public.pension WHERE hotel_id = " + id + " ORDER BY id ASC";
        return selectByQuery(query);
        /*ArrayList<Season> seasonList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                seasonList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonList;*/
    }
    // query for database
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
    //save pension
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
    //delete pension
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
    //match pension database
    public Pension match(ResultSet resultSet) throws SQLException {
        Pension pension =new Pension();
        pension.setId(resultSet.getInt("id"));
        pension.setType(Pension.Type.valueOf(resultSet.getString("type")));
        pension.setHotel_id(resultSet.getInt("hotel_id"));
        pension.setHotel(this.hotelDao.getById(resultSet.getInt("hotel_id")));
        return pension;
    }
}
