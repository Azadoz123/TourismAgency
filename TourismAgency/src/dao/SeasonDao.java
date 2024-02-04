package dao;

import core.Db;
import entity.Pension;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection connection;
    private final HotelDao hotelDao = new HotelDao();
    public SeasonDao() {
        this.connection = Db.getInstance();
    }
    public Season getById(int id){
        Season season = null;
        String query = "SELECT * FROM public.season WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) season = this.match(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return season;
    }
    //get all seasom
    public ArrayList<Season> findAll(){
        return selectByQuery("SELECT * FROM public.season ORDER BY id ASC");
    }
    //get season list by hotel Id
    public ArrayList<Season> getSeasonListByHotelId(int id){
        String query= "SELECT * FROM public.season WHERE hotel_id = " + id + " ORDER BY id ASC";
        return selectByQuery(query);
    }
    //query for database
    public ArrayList<Season> selectByQuery(String query){
        ArrayList<Season> seasonList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                seasonList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonList;
    }
    //save season
    public boolean save(Season season){
        String query = "INSERT INTO public.season" +
                "(" +
                "start_time," +
                "finish_time," +
                "hotel_id)" +
                " VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(season.getStartTime()));
            preparedStatement.setDate(2,Date.valueOf(season.getFinishTime()));
            preparedStatement.setInt(3,season.getHotel_id());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //delete season
    public boolean delete(int id){
        String query = "DELETE FROM public.season WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //match season database
    public Season match(ResultSet resultSet) throws SQLException {
        Season season = new Season();
        season.setId(resultSet.getInt("id"));
        season.setStartTime(resultSet.getDate("start_time").toLocalDate());
        season.setFinishTime(LocalDate.parse(resultSet.getString("finish_time")));
        season.setHotel_id(resultSet.getInt("hotel_id"));
        season.setHotel(this.hotelDao.getById(resultSet.getInt("hotel_id")));
        return season;
    }
}
