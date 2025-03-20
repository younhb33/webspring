<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초2(if문)</title>
</head>
<body>
<!-- test="" == if() -->
<cr:if test="${5 < 10}" var="result"> <!-- result : true,false(boolean) -->
</cr:if>
${result}<br>
<%-- 
jstl에 버그 중 숫자 변수 형태로는 if문을 사용시 버그발생 할 수 있다 but 
<fmt:formatNumber> : 숫자를 양식에 맞춰 문자열로 변환
<fmt:parseNumber> : 문자열을 숫자(Number 타입)로 변환
parseNumber쓰면 됨
 --%>

<!-- jstl if문 (lt(<), gt(>), le(<=), ge(>=) -->
<cr:set var="a" value="56"/>
<fmt:parseNumber value="${a}" type="number" var="aa" />
<cr:set var="b" value="410"/>
<fmt:parseNumber value="${b}" type="number" var="bb" />
${aa}<br>
${bb}<br>

<cr:if test="${aa > bb}">
aa값이 큽니다.
</cr:if>
<cr:if test="${aa < bb}">
bb값이 큽니다.
</cr:if>
<cr:if test="${aa == bb}">
동일한 값입니다.
</cr:if>
<br><br><br>
<!-- eq(==), ne(!=) or(||) and(&&)-->
<cr:set var="product" value="그래픽카드"/>
<cr:set var="product2" value="모니터"/>
<cr:if test="${product == '그래픽카드' && product2 == '모니터'}">
가격미정
</cr:if>


<br><br><br>


<%-- 
choose, when, otherwise
--%>
<!-- 조건문 시작 설정 -->
<cr:set var="agree" value="Y"/>
<cr:choose> 
<cr:when test="${agree eq 'Y'}"> <!-- if -->
약관에 동의함  
</cr:when>
<cr:when test="${agree eq 'N'}"> <!-- else if -->
약관에 동의 안함 
</cr:when>
<cr:otherwise> <!-- else -->
해당 약관정보를 확인못함 
</cr:otherwise>
</cr:choose>
</body>
</html>