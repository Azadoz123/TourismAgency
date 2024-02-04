package dao;

import core.Db;
import core.Helper;
import entity.Season;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private final Connection connection;

    public UserDao() {
        this.connection = Db.getInstance();
    }
    //save user
    public boolean save(User user){
        String query = "INSERT INTO public.user" +
                "(" +
                "name," +
                "password," +
                "role)" +
                " VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,String.valueOf(user.getRole()) );
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //get user by Id
    public User getById(int id){
        User user = null;
        String query = "SELECT * FROM public.user WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) user = this.match(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    //update user
    public boolean update(User user){
        String query = "UPDATE public.user SET " +
                "name = ?," +
                "password = ?," +
                "role = ?" +
                " WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,String.valueOf(user.getRole()) );
            preparedStatement.setInt(4,user.getId());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //delete user
    public boolean delete(int id){
        String query = "DELETE FROM public.user WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //get all user
    public ArrayList<User> findAll(){
        ArrayList<User> userList = new ArrayList<>();
        User user = null;
        String sql = "SELECT * FROM public.user";

        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(sql);
            while (resultSet.next()){
                userList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    /*public ArrayList<User> searchForTable(User user){
        String s= null;
    }*/
    //find user
    public User findByLogin(String username, String password){
        User user = null;
        String query = "SELECT * FROM public.user WHERE name = ? AND password = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet resultSet = pr.executeQuery();
            if (resultSet.next()){
                user = this.match(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    //query for database
    public ArrayList<User> selectByQuery(String query){
        ArrayList<User> userList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                userList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
    //match user database
    public User match(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(User.Role.valueOf(resultSet.getString("role")) );

        return user;
    }
}

