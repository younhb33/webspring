package spring_learning;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mail_controller {
	@PostMapping("/contactusok.do")
	public String contactusok(
			@RequestParam String subject,
			@RequestParam String mname,
			@RequestParam String memail,
			@RequestParam String mtext
			) throws Exception {
		
		//Map => Properties (.ini) => 환경설정 파일 형태의 배열 
		//SMTP, IMAP, POP3 => 알아보기 
		Properties props = new Properties();	//Map이 조금 발전한 느낌의 성격 
		props.put("mail.smtp.host","smtp.mail.nate.com");		//메일 발송 서버
		props.put("mail.smtp.port", "587");					//메일 발송 서버의 포트 
		props.put("mail.smtp.auth", "true");				//메일 발송 서버의 인증 (아이디, 패스워드)
		props.put("mail.smtp.starttls.enable", "true");		//메일서버의 TLS를 연결 
		props.put("mail.smtp.ssl.trust","smtp.mail.nate.com");	//메일서버의 SSL 기능 사용  
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");	//메일서버의 SSL 프로토콜 버전 설정
		
		//메일 발송에 대한 로그인(메일서버 로그인 정보) 사항을 Session으로 등록 시킴 => SMTP 서버에 자동로그인
		//import javax.mail.Session; 주의 
		Session session = Session.getInstance(props, new Authenticator() {
			
			//컨스페로 오버라이드
			//로그인 할 id와 패스워드 정보를 입력 
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				//실제 로그인 아이디, 패스워드 입력 
				return new PasswordAuthentication("younhb33@nate.com", "13572468yo!");
			}
		});	
		
		//어디 에러났나 잘 봐야하니깐 예외처리 => naver/nate에서 어디가 문제인지 오류코드를 보내줌  
		try {	//메일 내용을 발송 
			//import javax.mail.Message; 주의 
			Message msg = new MimeMessage(session);	//Mime : 이메일 전송을 위한 인터넷 표준 포멧 
			
			//보내는 사람 메일주소 + 보내는 이 
			msg.setFrom(new InternetAddress(memail,mname,"utf-8"));	//어디로 발송할지 보내는쪽
			
			//받는 사람 
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("younhb33@nate.com"));
			msg.setSubject(subject);	//메일 제목 
			msg.setContent(mtext,"text/html;charset=utf-8");	//메일 내용 
			
			Transport.send(msg);	//메일 발송 메소드 
			
		}catch (Exception e) {	//메일 발송에 대한 서버접근오류 발생시 출력 코드 
			e.printStackTrace();
		}

		
		
		
		return null;
	}
}
