<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="com.bookshop.to.User, java.util.List"%>
<%@ page import="com.bookshop.to.Book,java.util.List"%>

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
  //Book book = (Book)request.getAttribute("bookData");
  List<Book> books = (List<Book>) session.getAttribute("books");
%>

<table class="table">
  <thead>
  <tr>
    <th scope="col">#</th>
    <th scope="col">BookName</th>
    <th scope="col">BookId</th>
    <th scope="col">Author</th>
    <th scope="col">Publication</th>
    <th scope="col">Delete</th>
    <th scope="col">Update</th>
  </tr>
  </thead>
  <tbody>
  <%
    for(int i = 0; i < books.size(); i++) {
  %>
  <tr>
    <th scope="row"><%= i+1 %></th>
    <td><%= books.get(i).getname() %></td>
    <td><%= books.get(i).getId() %></td>
    <td><%= books.get(i).getAuthor()%></td>
    <td><%= books.get(i).getPublication()%></td>
    <td><a href="${pageContext.request.contextPath}/book?action=delete&bookName=<%=books.get(i).getname()%>">delete</a></td>
    <td><a href="${pageContext.request.contextPath}/book?action=update&bookName=<%=books.get(i).getname()%>">update</a></td>


  </tr>
  <%} %>

  </tbody>
</table>

</body>
</html>