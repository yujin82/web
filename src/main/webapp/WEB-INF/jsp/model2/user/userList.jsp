<%@ page import="kr.mjc.yujin.web.dao.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<body>
<nav><a href="./loginForm">로그인</a> <a href="./userForm">회원가입</a></nav>
<h3>사용자 목록</h3>
<%
  List<User> userList = (List<User>) request.getAttribute("userList");
  for (User user : userList) {%>
<p><%= user %>
</p>
<%
  }
%>
</body>
</html>
