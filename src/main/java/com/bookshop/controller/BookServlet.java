package com.bookshop.controller;

import com.bookshop.service.BookService;
import com.bookshop.to.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    BookService bookService=new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action) {

            case "insert": {
                String name = request.getParameter("BookName");
                int id1 = Integer.parseInt(request.getParameter("BookId"));
                String author = request.getParameter("aName");
                String publication = request.getParameter("pub");
                Book book1 = new Book(name, id1, author, publication);
                bookService.insert(book1);
                PrintWriter writer1 = response.getWriter();
                writer1.println("<html><body>Thanks for entering with us...</body></html>");
            }
        }

    }
}
