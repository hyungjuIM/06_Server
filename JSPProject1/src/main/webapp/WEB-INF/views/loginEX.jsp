<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
   
    </style>
</head>
<body>
   <%
    String id = request.getParameter("inputId");
	String[] pw = request.getParameterValues("inputPw");
	
	String name = request.getParameter("inputName");
	String email = request.getParameter("inputEmail");
	String[] hobby = request.getParameterValues("inputhobby");
    %>
    <%if(!pw[0].equals(pw[1])){%>
    <h3>비밀번호가 일치하지 않습니다.</h3>
    <%} else{ %>
    
    <ul>
    <li>아이디 : <%= id%></li>
    <li>비밀번호 : <%= pw[0]%></li>
    <li>이름 : <%= name%></li>
    <li>이메일 : <%= email%></li>
    <li>취미 : 
    <% for(String h: hobby){ %>
    <%=h %>
    <%} %>
    </li>    
    </ul>
    <%} %>
   <%=name %>님의 회원가입이 완료되었습니다.
 
    <button type="button" onclick="history.back()">돌아가기</button>
    
</body>
</html>