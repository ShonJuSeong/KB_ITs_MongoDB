<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>로그인 입력 파라미터 출력</h1>
<%
    String userid = request.getParameter("userid");
    String password = request.getParameter("password");
%>
아이디값 : <%=userid%><br>
비밀번호 : <%=password%><br>
</body>
</html>
