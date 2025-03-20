<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    ArrayList<String> filenm = (ArrayList)request.getAttribute("filenm");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첨부파일 리스트</title>
</head>
<body>
<table border="1">
<tr>
	<th>파일명</th>
	<th>삭제</th>
</tr>
<% 
int w=0;
while(w < filenm.size()){
%>
<%-- 
만약에 반복문 안에 아이디를 꼭 사용원할시  id(name)="abc<%=w%>"이런식으로 사용
반복문 안에 네임은 dto사용할 때 꼭 배열로 입력
*웬만하면 name/id는 반복문 안에 넣으면 안됨 
--%>
<tr>
	<td><%= filenm.get(w) %></td> <!-- 반복문 안에 name/아이디 넣으면 절단남 -->
	<td><input type="button" value="삭제" onclick="file_del('<%=filenm.get(w)%>')"></td>
</tr>
<% 
w++;
}
%>
</table>
<!-- 선택한 파일명만 전송 -->

<form id="fm" method="post" action="./filedel.do">
<input type="hidden" name="fnm" value="">
</form>
<script>
function file_del(fnm) {
	fm.fnm.value=fnm;
	fm.submit();
}

</script>
</body>
</html>