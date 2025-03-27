package spring_learning;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository("file_rename")
public class file_rename {
	//홍길동.jpg => 2025032755.jpg
	public String rename(String filenm) {
		//속성 .jpg .png ...
		int com = filenm.lastIndexOf(".");
		String fnm = filenm.substring(com);
		System.out.println(fnm);
		
		//날짜
		Date day = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String today = sf.format(day);	//년월일
		
		//랜덤값
		int no = (int)Math.ceil(Math.random()*1000); //1~1000 실무에선 10000까지 때림
		String makefile = today + no + fnm; //파일명 예시) 2025032715
		
		
		return makefile;
	}
}
