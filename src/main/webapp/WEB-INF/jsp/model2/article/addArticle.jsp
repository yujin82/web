<%@ page import="kr.mjc.yujin.web.dao.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<html>
<body>
<h3>게시글 작성</h3>
<form action="addArticle" method="post">
    <p><input type="email" name="email" placeholder="이메일" required autofocus/></p>
    <p><input type="password" name="password" placeholder="비밀번호" required/></p>
    <p><input type="text" name="name" placeholder="이름" required/></p>
    <p>
        <button type="submit">저장</button>
    </p>
</form>
<p style="color:red;"><%= Optional.ofNullable(request.getParameter("msg"))
        .orElse("")%>
</p>
</body>
</html>