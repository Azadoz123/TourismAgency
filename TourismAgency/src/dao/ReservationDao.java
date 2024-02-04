package dao;

import core.Db;
import entity.Reservation;
import entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {
    private Connection connection;
    private final RoomDao roomDao;

    public ReservationDao() {
        connection = Db.getInstance();
        roomDao =new RoomDao();
    }
    //get all reservation
    public ArrayList<Reservation> findAll(){
        return selectByQuery("SELECT * FROM public.reservation ORDER BY id ASC");
    }
    //get reservation by Id
    public Reservation getById(int id){
        Reservation reservation = null;
        String query = "SELECT * FROM public.reservation WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) reservation = this.match(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservation;
    }
    //query for database
    public ArrayList<Reservation> selectByQuery(String query){
        ArrayList<Reservation> reservationList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                reservationList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationList;
    }
    //save reservation
    public boolean save(Reservation reservation){
        String query = "INSERT INTO public.reservation" +
                "(" +
                "room_id," +
                "check_in_date," +
                "check_out_date," +
                "total_price," +
                "guest_count," +
                "guest_name," +
                "guest_citizen_id," +
                "guest_mail," +
                "guest_phone," +
                "number_of_child," +
                "number_of_adult" +
                ")" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,reservation.getRoom_id());
            preparedStatement.setDate(2,Date.valueOf(reservation.getCheckInDate()) );
            preparedStatement.setDate(3,Date.valueOf(reservation.getCheckOutDate()));
            preparedStatement.setDouble(4,reservation.getTotalPrice());
            preparedStatement.setInt(5, reservation.getGuestCount());
            preparedStatement.setString(6,reservation.getGuestName());
            preparedStatement.setString(7,reservation.getGuestCitizenId());
            preparedStatement.setString(8,reservation.getGuestMail());
            preparedStatement.setString(9,reservation.getGueastPhone());
            preparedStatement.setInt(10,reservation.getNumberOfChild());
            preparedStatement.setInt(11,reservation.getNumberOfAdult());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //delete reservation
    public boolean delete(int id){
        String query = "DELETE FROM public.reservation WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //update reservation
    public boolean update(Reservation reservation){
        String query = "UPDATE public.reservation SET " +
                "room_id = ?," +
                "check_in_date = ?," +
                "check_out_date = ?," +
                "total_price = ?," +
                "guest_count = ?," +
                "guest_name = ?," +
                "guest_citizen_id = ?," +
                "guest_mail = ?," +
                "guest_phone = ?," +
                "number_of_child = ?," +
                "number_of_adult = ?" +
                " WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,reservation.getRoom_id());
            preparedStatement.setDate(2,Date.valueOf(reservation.getCheckInDate()) );
            preparedStatement.setDate(3,Date.valueOf(reservation.getCheckOutDate()));
            preparedStatement.setDouble(4,reservation.getTotalPrice());
            preparedStatement.setInt(5, reservation.getGuestCount());
            preparedStatement.setString(6,reservation.getGuestName());
            preparedStatement.setString(7,reservation.getGuestCitizenId());
            preparedStatement.setString(8,reservation.getGuestMail());
            preparedStatement.setString(9,reservation.getGueastPhone());
            preparedStatement.setInt(10,reservation.getNumberOfChild());
            preparedStatement.setInt(11,reservation.getNumberOfAdult());
            preparedStatement.setInt(12,reservation.getId());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //match database reservation
    public Reservation match(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getInt("id"));
        reservation .setRoom_id(resultSet.getInt("room_id"));
        reservation .setRoom(this.roomDao.getById(resultSet.getInt("room_id")));
        reservation.setCheckInDate(LocalDate.parse(resultSet.getString("check_in_date")) );
        reservation.setCheckOutDate(LocalDate.parse(resultSet.getString("check_out_date")) );
        reservation.setTotalPrice(resultSet.getDouble("total_price"));
        reservation.setGuestCount((resultSet.getInt("guest_count")));
        reservation.setGuestName(resultSet.getString("guest_name"));
        reservation.setGuestCitizenId(resultSet.getString("guest_citizen_id"));
        reservation.setGuestMail(resultSet.getString("guest_mail"));
        reservation.setGueastPhone(resultSet.getString("guest_phone"));
        reservation.setNumberOfChild(Integer.parseInt(resultSet.getString("number_of_child")) );
        reservation.setNumberOfAdult(Integer.parseInt(resultSet.getString("number_of_adult")) );
        return reservation;
    }
}
