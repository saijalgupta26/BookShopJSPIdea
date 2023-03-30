package com.bookshop.service;
import com.bookshop.to.Book;
import com.bookshop.repository.BookRepository;
import java.util.List;
public class BookService {
    BookRepository repository1 = new BookRepository();
    public void insert(Book book) {
        repository1.insert(book);
    }
    public List<Book> findAllBook() {
        return repository1.findAllBook();
    }
    public Book findUserByUsername(String loggedInUser) {
        // TODO Auto-generated method stub
        return null;
    }
    public static void delete(String username) {
        // TODO Auto-generated method stub

    }
}
