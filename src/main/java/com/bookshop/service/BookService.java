package com.bookshop.service;
import com.bookshop.to.Book;
import com.bookshop.repository.BookRepository;
import com.bookshop.to.Product;

import java.util.List;
public class BookService {
    static BookRepository repository1 = new BookRepository();
    public void insert(Book book) {
        System.out.println("sasa");
        repository1.insert(book);
    }
    public static List<Book> findAllBook() {
        return repository1.findAllBook();
    }

    public  void delete(String name) {

        repository1.deleteBook(name);

    }

   // public Book findBookByBookName(String bookName) {
        //return null;
    //}

    public void update(Book book2) {
        repository1.updateBook(book2);

    }

    public Book findBookByBookName(String bookName) {
        return repository1.findBookByBookName(bookName);
    }
}
