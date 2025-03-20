function wordck(){
	//var w = "010123-45678";
	var w = "01012345678";
	
	//let ck = /[0-9]/;  //0~9
	//let ck = /[a-zA-Z]/; //a~z까지
	//console.log(ck.test(w)); //test : 정규식 코드에 값을 대입해서 테스트
	
	//let ck = /[0-9]/; //0~9까지 숫자 외에 있을경우
	//console.log(w.match(ck)); //배열형태의 값을 출력
	
	//let ck = /[0-9]/g;
	
	//$: 해당 패턴에 문자열을 대입하여 체크하는 방식
	let ck = /^\d{2,3}\d{3,4}\d{4}$/; //true(010-1234-5678), false(01012345678)
	//let ck = /^[0-9]/g;
	console.log(ck.test(w));
}


function eventok(){
	if(f.ename.value == ""){
		alert("고객명을 입력하세요!");
	}else if(f.etel.value ==""){
		alert("연락처를 입력하세요!");
	}else if(f.email.value ==""){
		alert("이메일을 입력하세요!");
	}else if(f.ememo.value ==""){
		alert("이벤트 참여이유를 입력하세요!");
	}else if(f.info1.value.checked == false){
		alert("개인정보활용에 동의하셔야만 이벤트에 참여가 됩니다!");
	}else if(f.info2.value.checked == false){
		alert("제3자의 정보제공에 동의하셔야만 이벤트에 참여가 됩니다!");
	}else{
		//정규식 코드사용 (연락처 확인)
		let ck = /^\d{2,3}\d{3,4}\d{4}$/; //숫자 외에 다른 문자일 경우 false
		if(ck.test(f.etel.value) == false){
			alert("전화번호를 정상적으로 입력하세요");
		}else{ //정규식 코드(true)
			f.submit();
		}
	}
	
	
}