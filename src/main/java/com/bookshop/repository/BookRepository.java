package com.bookshop.repository;
import com.bookshop.db.DBConnection;
import com.bookshop.to.Book;
import com.bookshop.to.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    Connection conn = DBConnection.getConnection();
    public void insert(Book book) {
        try {
            System.out.println("sas");

            PreparedStatement statement = conn.prepareStatement("insert into book values(?, ?, ?, ?)");
            statement.setString(1, book.getName());
            statement.setInt(2, book.getId());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getPublication());
            System.out.println(book.getAuthor());
            System.out.println(book.getAuthor());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("inside save of BookRepository");
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
            System.out.println("inside catch of findAll of BookRepository");
            e.printStackTrace();
        }
        return books;
    }

    public void deleteBook(String name) {
        try {
            System.out.println("book");


            PreparedStatement statement = conn.prepareStatement("delete from book where name = ?");
            System.out.println(name);

            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("dsd");
        }
        catch (Exception e) {
            System.out.println("inside catch of delete of BookRepository");
            e.printStackTrace();
        }
    }

    public Book findBookByBookName(String bookName) {
        User user = null;

        Book book = null;
        try {

            PreparedStatement statement = conn.prepareStatement("select * from book where name = ?");
            statement.setString(1, bookName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(1);
                int id = resultSet.getInt(2);
                String author = resultSet.getString(3);
                String publication = resultSet.getString(4);
                book = new Book(name, id, author, publication);
            }
        } catch (Exception e) {
            System.out.println("inside catch of find() of Repository");
            e.printStackTrace();
        }
        return book;
    }

    public void updateBook(Book book) {
        try {
            System.out.println("update "+book.getId()+" "+book.getAuthor()+", "+book.getPublication());
            PreparedStatement statement = conn.prepareStatement("update book set id = ?, author = ? where name = ?");
            statement.setInt(1, book.getId());
            statement.setString(2, book.getAuthor());

            statement.setString(3,book.getName());
            statement.executeUpdate();
        }

        catch (Exception e) {
            System.out.println("inside catch of update of BookServlet...");
            e.printStackTrace();
        }

    }
}

