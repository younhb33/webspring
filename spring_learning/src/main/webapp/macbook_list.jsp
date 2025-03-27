<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과정 개설 리스트</title>
</head>
<body>
<p>개설된 과목 갯수 : ${ea}</p>
<!-- DTO에 있는 변수명으로 JSTL로 출력하는 형태 -->
<cr:forEach var="cdata" items="${classList}" varStatus="idx">
과정명 : ${cdata['class_name']}, 
강사명 : ${cdata.class_teacher}, 
수강료 : ${cdata.class_price}
<input type="button" value="수정" onclick="macbook_modify('${cdata.midx}')">
<input type="button" value="삭제" onclick="macbook_del('${cdata.midx}')"> <br>
</cr:forEach>
<!-- 해당 게시물의 고유값을 POST Back-edn로 전송 -->


<form id="frm" method="post">
<input type="hidden" name="midx" value="">
</form>

<!-- 
<form id="frm" method="get" action="./macbook_modify.do">
<input type="hidden" name="midx" value="">
<input type="hidden" name="data1">
<input type="hidden" name="data2">
<input type="hidden" name="data3">
</form>
 -->





<!-- 
<div id="msg">

</div>
 -->



<!-- html에 직접 때려넣으면 보안 뚫릴 가능성 높음
form태그 사용시 주의사항: 
- 반복문 안에 절대로 <form>태그 넣지 말기
- form태그 안에 form태그는 존재하지 않는 코드
 -->
<script>
function macbook_modify(n) {
	//html폼이 없는 상태
	
	/*
	var m = document.getElementById("msg");
	m.innerHTML = `<form id="frm" method="post" action="./macbook_modify.do">
	<input type="hidden" name="midx" value="`+n+`">
	</form>`;
	frm.submit();
	*/
	
	//POST 통신(form)
	frm.midx.value = n; //html폼이 있는 상태
	frm.action = "./macbook_modify.do";
	frm.submit();
	
	//GET통신
	//location.href='./macbook_modify.do?midx='+n;
	
	
}
function macbook_del(n) {
	if(confirm('해당과정을 삭제 하시겠습니까? \n삭제시 데이터는 복구되지 않습니다')){
	frm.midx.value = n;
	frm.action = "./macbook_delete.do";
	frm.submit();
	}
	
}


</script>

</body>
</html>