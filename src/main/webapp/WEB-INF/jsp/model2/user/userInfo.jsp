<%@ page import="kr.mjc.yujin.web.dao.User" %>
<!DOCTYPE html>
<% User user = (User) session.getAttribute("USER"); %>
<html>
<body>
<h3>사용자 정보</h3>
<p><%= user %>
</p>
</body>
</html>
