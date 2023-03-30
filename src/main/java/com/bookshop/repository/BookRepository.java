package com.bookshop.repository;
import com.bookshop.db.DBConnection;
import com.bookshop.to.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    Connection conn = DBConnection.getConnection();
    public void insert(Book book) {
        try {

            PreparedStatement statement = conn.prepareStatement("insert into book values(?, ?, ?, ?)");
            statement.setString(1, book.getname());
            statement.setInt(2, book.getId());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getPublication());
            System.out.println(book.getAuthor());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("inside save of UserRepository");
        }
    }
    public List<Book> findAllBook() {
        List<Book> books = new ArrayList<Book>();
        try {
            PreparedStatement statement =  conn.prepareStatement("select * from book");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String name = resultSet.getString(1);
                int id = resultSet.getInt(2);
                String author = resultSet.getString(3);
                String publication = resultSet.getString(4);
                Book book = new Book(name, id, author, publication);
                books.add(book);
            }
        }
        catch (Exception e) {
            System.out.println("inside catch of findAll of UserRepository");
            e.printStackTrace();
        }
        return books;
    }
}

