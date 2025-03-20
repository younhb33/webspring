<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--
Servlet => jsp
Spring => jstl(엔진)
Spring-boot => tyymeleaf(엔진)
 -->    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%-- 
 Contoller(ModelAndView) => jstl출력 ${} 
 --%> 
상품명 : ${pdnm}<br>
상품코드 : ${pcode}<br>
상품가격 : ${pmoney}<br>
</body>
</html>