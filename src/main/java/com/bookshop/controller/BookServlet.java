package com.bookshop.controller;
import com.bookshop.service.BookService;
import com.bookshop.service.UserService;
import com.bookshop.to.Book;
import com.bookshop.to.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    BookService bookService=new BookService();
    UserService userService=new UserService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String bookName = request.getParameter("bookName");

        HttpSession session = request.getSession(false);
        RequestDispatcher dispatcher = null;

//        String loggedInUser = request.getParameter("loggedIn");
//        User user = userService.findUserByUsername(loggedInUser);
       // request.setAttribute("bookData", bookName);
        switch (action) {
            case "delete":
            {

                if( session != null) {
                    bookService.delete(bookName);
                    System.out.println("delete");
                    List<Book> books = bookService.findAllBook();
                    session.setAttribute("books", books);
                    System.out.println("fsdghfghds");
                    dispatcher = request.getRequestDispatcher("book.jsp");
                    dispatcher.include(request, response);
                }
                else {
                    dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.include(request, response);
                }

                break;
            }
            case "update":
                if(session!=null)
                {
                    System.out.println("update the book details");

                    System.out.println(bookName);

                    String updatingBook= (String) session.getAttribute("bookName");
                    System.out.println(updatingBook);
                    Book book1=bookService.findBookByBookName(bookName);
                    session.setAttribute("updatingBook",book1);
                    List<Book> book2= BookService.findAllBook();
                    session.setAttribute("updatingData",book2);
                    dispatcher = request.getRequestDispatcher("update-book.jsp");
                    dispatcher.include(request, response);
                    break;

                }

        }

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
                writer1.println("<html><body>Thanks for insert the book</body></html>");
                break;
            }
            case "update":
            {
                String BookName= request.getParameter("BookName");

                int BookId = Integer.parseInt(request.getParameter("BookId"));
                String aName=request.getParameter("aName");
//                String publication =request.getParameter("publication");
//                String loggedInUser = request.getParameter("loggedIn");
                System.out.println(BookName + ", " + BookId);
                Book book3 = bookService.findBookByBookName(BookName);
                request.setAttribute("bookData", book3);

                Book book2 =new Book(BookName,BookId,aName,null);
                System.out.println("inside controller " + book2 + ", " + book2.getName());
                bookService.update(book2);


                HttpSession session =  request.getSession();

                session.setAttribute("bookData", book2);

                List<Book> books = BookService.findAllBook();
                //request.setAttribute("users", users);
                session.setAttribute("books", books);
                RequestDispatcher dispatcher = request.getRequestDispatcher("book.jsp");
                dispatcher.include(request, response);

            }

            case "buy":
            {

            }
            case "wishlist":
            {

            }
            case "add to cart":
            {

            }


        }

    }
}
