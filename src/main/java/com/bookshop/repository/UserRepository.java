package com.bookshop.repository;
import com.bookshop.db.DBConnection;
import com.bookshop.to.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class UserRepository {
    Connection conn = DBConnection.getConnection();
    public void save(User user) {
        try {
            PreparedStatement statement = conn.prepareStatement("insert into user values(?, ?, ?, ?,?,?,?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getCompleteName());
            statement.setString(4, user.getEmail());
            statement.setString(5,user.getisAdmin());
            statement.setBoolean(6,false);
            statement.setBoolean(7,true);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("inside save of UserRepository");
        }
    }
    public User find(String username, String password) {
        User user = null;
        try {
            PreparedStatement statement = conn.prepareStatement("select * from user where username = ? and password = ? and blocked is false and unblocked is true");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String name = resultSet.getString(3);
                String email = resultSet.getString(4);
                String isAdmin=resultSet.getString(5);
                Boolean blocked=resultSet.getBoolean(6);
                Boolean unBlocked= resultSet.getBoolean(7);
                user = new User(username, password, name, email,isAdmin,blocked,unBlocked);
            }
        }
        catch (Exception e) {
            System.out.println("inside catch of find() of UserRepository");
            e.printStackTrace();
        }
        return user;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        try {
            PreparedStatement statement =  conn.prepareStatement("select * from user");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String username = resultSet.getString(1);
                String password = resultSet.getString(2);
                String name = resultSet.getString(3);
                String email = resultSet.getString(4);
                User user = new User(username, password, name, email,null,null, null);
                users.add(user);
            }
        }
        catch (Exception e) {
            System.out.println("inside catch of findAll of UserRepository");
            e.printStackTrace();
        }
        return users;
    }
    public void delete(String username) {
        try {

            PreparedStatement statement = conn.prepareStatement("delete from user where username = ?");

            statement.setString(1, username);
            statement.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("inside catch of delete of UserRepository");
            e.printStackTrace();
        }
    }

    public User findUserByUsername(String username) {
        User user = null;

        try {

            PreparedStatement statement = conn.prepareStatement("select * from user where username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String name = resultSet.getString(3);
                String email = resultSet.getString(4);
                String password = resultSet.getString(2);
                Boolean blocked=resultSet.getBoolean(6);
                Boolean unBlocked=resultSet.getBoolean(7);
                user = new User(username, password, name, email,null,blocked, unBlocked);
            }
        }
        catch (Exception e) {
            System.out.println("inside catch of find() of UserRepository");
            e.printStackTrace();
        }
        return user;
    }
    public void update(User user) {
        try {
            System.out.println("update "+user.getCompleteName()+" "+user.getEmail()+", "+user.getUsername());
            PreparedStatement statement = conn.prepareStatement("update user set complete_name = ?, email = ? where username = ?");
            statement.setString(1, user.getCompleteName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getUsername());
            statement.executeUpdate();
        }

        catch (Exception e) {
            System.out.println("inside catch of update of UserServlet...");
            e.printStackTrace();
        }
    }

    public void block(String username) {
        try {
            PreparedStatement statement = conn.prepareStatement("update user set blocked=true where username = ?");
            statement.setString(1, username);
            statement.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("inside catch of delete of UserRepository");
            e.printStackTrace();
        }
    }

    public void unblock(String username) {
        try {
            PreparedStatement statement = conn.prepareStatement("update user set blocked=false where username = ?");
            statement.setString(1, username);
            statement.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("inside catch of delete of UserRepository");
            e.printStackTrace();
        }

    }
}

