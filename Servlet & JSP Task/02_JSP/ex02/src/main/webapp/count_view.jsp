
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<h1>방문자 수 조회하기 화면</h1>
    <%
Integer countObj = (Integer)application.getAttribute("countValue");
int count = (countObj != null) ? countObj : 0;

// +1 증가
count++;

// 다시 저장
application.setAttribute("countValue", count);
%>
현재까지 총 방문자 수: <%= count %>
</html>
