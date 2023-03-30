package com.bookshop.controller;
import com.bookshop.service.BookService;
import com.bookshop.service.UserService;
import com.bookshop.to.Book;
import com.bookshop.to.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    UserService userService = new UserService();
    BookService bookService=new BookService();
    public UserServlet() {
        System.out.println("Inside User servlet constructor");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String loggedInUser = request.getParameter("loggedIn");

        User user = userService.findUserByUsername(loggedInUser);
        request.setAttribute("userData", user);

        switch (action) {
            case "delete":
//                System.out.println("inside delete " + username + ", " + loggedInUser);
//                HttpSession session = request.getSession(false);
//                userService.delete(username);
//                List<User> users = userService.findAll();
//                request.setAttribute("users", users);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
//                dispatcher.include(request, response);
//                break;
                HttpSession session = request.getSession(false);
                RequestDispatcher dispatcher = null;
                if( session != null) {
                    userService.delete(username);
                    List<User> users = userService.findAll();
                    session.setAttribute("users", users);
                    dispatcher = request.getRequestDispatcher("welcome.jsp");
                    dispatcher.include(request, response);
                }
                else {
                    dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.include(request, response);
                }

                break;

            case "block":
            {
                System.out.println("inside block"+ username+" ,"+loggedInUser);
                userService.block(username);
                List<User> users1 = userService.findAll();
                request.setAttribute("users", users1);
                RequestDispatcher dispatcher2 = request.getRequestDispatcher("welcome.jsp");
                dispatcher2.include(request, response);
//			PrintWriter writer1 = response.getWriter();
//			writer1.println("<html><body>Contact to admin session</body></html>");
                break;

            }
            case "unblock":
            {
                userService.unblock(username);
                break;
            }


            case "update":

                System.out.println("inside update " + username + ", " + loggedInUser);

                String updatingUser = request.getParameter("username");

                User user1 = userService.findUserByUsername(updatingUser);

                request.setAttribute("updatingUser", user1);

                String loggedInUser1 = request.getParameter("loggedIn");

                User user4 = userService.findUserByUsername(loggedInUser);
                request.setAttribute("userData", user4);

                RequestDispatcher dispatcher1 = request.getRequestDispatcher("update-user.jsp");
                dispatcher1.include(request, response);
                break;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "register":
            {
                System.out.println("register case");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String completeName = request.getParameter("cName");
                String email = request.getParameter("email");
                String isAdmin=(request.getParameter("isAdmin"));
                System.out.println(username + ", " + password + ", " + completeName + ", " + email);
                User user = new User(username, password, completeName, email,isAdmin,false, true);
                userService.register(user);
                PrintWriter writer = response.getWriter();
                System.out.println(isAdmin);

                break;
            }
            case "login": {
                System.out.println("login case");
                String username1 = request.getParameter("username");
                String password1 = request.getParameter("pwd");
                System.out.println(username1 + ", " + password1);
                User user1 = userService.login(username1, password1);
                List<Book> book2 = bookService.findAllBook();
                if (user1 != null) {
                    if (user1.getisAdmin() == null) {
                        //request.setAttribute("bookData",book2);
                        HttpSession session =  request.getSession();


                        List<Book> books = bookService.findAllBook();
                        //request.setAttribute("users", users);
                        session.setAttribute("books",books);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                        dispatcher.include(request, response);



//                        List<Book> books = bookService.findAllBook();
//                        request.setAttribute("books", books);
//                        RequestDispatcher dispatcher = request.getRequestDispatcher("book.jsp");
//                        dispatcher.include(request, response);
                    } else {
//					System.out.println("this is for admin purpose");

//                        request.setAttribute("userData", user1);
//                        List<User> users = UserService.findAll();
//                        //request.setAttribute("users", users);
//                        request.setAttribute("users", users);
//                        RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
//                        dispatcher.include(request, response);
                        HttpSession session =  request.getSession();
                        session.setAttribute("userData", user1);

                        List<User> users = UserService.findAll();
                        //request.setAttribute("users", users);
                        session.setAttribute("users", users);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                        dispatcher.include(request, response);
                    }
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    request.setAttribute("errorMessage", "Wrong Credentials, please try again!!");
                    dispatcher.forward(request, response);
                }
                break;

            }
            case "update": {
                String username2 = request.getParameter("username");
                String completeName2 = request.getParameter("cName");
                String email2 = request.getParameter("email");
                String loggedInUser = request.getParameter("loggedIn");
                System.out.println(completeName2 + ", " + email2);
                User user3 = userService.findUserByUsername(loggedInUser);
                request.setAttribute("userData", user3);
                User user2 = new User(username2, null, completeName2, email2, null, null, null);
                System.out.println("inside controller " + username2 + ", " + user2.getUsername());
                userService.update(user2);
                List<User> users = userService.findAll();
                request.setAttribute("users", users);
//                List<User> users1 = userService.findAll();
//                request.setAttribute("users", users1);
                RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                dispatcher.include(request, response);
            }
            case "insert":{
                String name=request.getParameter("BookName");
                int id1=Integer.parseInt(request.getParameter("BookId"));
                String author=request.getParameter("aName");
                String publication=request.getParameter("pub");
                Book book1 = new Book(name,id1,author,publication);
                bookService.insert(book1);
                PrintWriter writer1 = response.getWriter();
                writer1.println("<html><body>Thanks for entering with us...</body></html>");
            }
        }
    }
}
//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//
//
//
//System.out.println("inside post of FirstServlet and I am updating....");
//
//String username = request.getParameter("username");
//String password = request.getParameter("password");
//String completeName = request.getParameter("cName");
//String email = request.getParameter("email");
//
//System.out.println(username + ", " + password + ", " + completeName + ", " + email);
//
//User user = new User(username, password, completeName, email);
//
////UserRepository com.bookshop.repository = new UserRepository();
////com.bookshop.repository.save(user);
//
//service.register(user);
//
//PrintWriter writer = response.getWriter();
//
//writer.println("<html><body>Thanks for Registering with us...</body></html>");
//}

