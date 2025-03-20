package spring_learning;

import lombok.Getter;
import lombok.Setter;

//lombok => pom.xml에 라이브러리 @Setter, @Getter 사용가능
//단, lombok.jar 미설치 시 메소드 안 만들어짐 

@Setter
@Getter
public class user_DTO {
	String mid, mpass;
	String mname, memail;
	
}
