<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="com.bookshop.to.User, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Insert title here</title>
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>
<body>

<%
//  User user = (User) request.getAttribute("userData");
//  List<User> users = (List<User>) request.getAttribute("users");
  User user = (User) session.getAttribute("userData");
  List<User> users = (List<User>) session.getAttribute("users");
%>


<H2>
  Welcome
  <%= user.getCompleteName() %></H2>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Navbar</a>


  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="">Home </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="">logout</a>
      </li>



    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" style="float: right; margin-right: 0px">Search</button>
    </form>
  </div>
</nav>

<table class="table">
  <thead>
  <tr>
    <th scope="col">#</th>
    <th scope="col">Username</th>
    <th scope="col">Complete Name</th>
    <th scope="col">Email</th>
    <th scope="col">Delete</th>
    <th scope="col">Update</th>
    <th scope="col">Block</th>
  </tr>
  </thead>
  <tbody>
  <%
    for(int i = 0; i < users.size(); i++) {
  %>
  <tr>
    <th scope="row"><%= i+1 %></th>
    <td><%= users.get(i).getUsername() %></td>
    <td><%= users.get(i).getCompleteName() %></td>
    <td><%= users.get(i).getEmail() %></td>
    <td><a href="user?action=delete&username=<%= users.get(i).getUsername()%>&loggedIn=<%= user.getUsername() %>">delete</a></td>
    <td><a href="user?action=update&username=<%= users.get(i).getUsername()%>&loggedIn=<%= user.getUsername() %>">update</a></td>
    <% if(user.getBlocked() == false)
    {%>
    <td><a href="user?action=block&username=<%= users.get(i).getUsername()%>&loggedIn=<%= user.getUsername() %>">block</a></td>
    <%}
    else{%>
    <td><a href="user?action=unblock&username=<%= users.get(i).getUsername()%>&loggedIn=<%= user.getUsername() %>">unblock</a></td>
    <%} %>

  </tr>
  <%} %>

  </tbody>
</table>
<form action="book?action=insert" method="post">
  <label>BookName</label>
  <input type="text" name="BookName"><br><br>
  <label>BookId</label>
  <input type="number" name="BookId"><br><br>
  <label>Author</label>
  <input type="text" name="aName"><br><br>
  <label>PublicationName</label>
  <input type="text" name="pub"><br><br>

  <button>insert</button>
  </form>

</body>
</html>