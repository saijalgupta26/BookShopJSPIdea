<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="com.bookshop.to.Book"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Insert title here</title>
</head>
<body>

<%
  Book updatingBook = (Book) session.getAttribute("updatingBook");

%>

<h1>Update your details here</h1>
<H2>
  Welcome
</H2>
<form action="book?action=update&updatingBook=<%=updatingBook.getname()%>" method="post">
  <label>Bookname</label>
  <input type="text" name="BookName" readonly="readonly" value="<%= updatingBook.getname()%>"><br><br>
  <label>BookId</label>
  <input type="number" name="BookId" value="<%= updatingBook.getId()%>"><br><br>
  <label>Author</label>
  <input type="text" name="aName" value="<%= updatingBook.getAuthor()%>"><br><br>
  <button>Update</button>

</form>
</body>
</html>
