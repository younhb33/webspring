package spring_learning;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

//Spring Session 사용방법
@Controller
/*
 @SessionAttributes : controller에서 세팅된 값, DTO가 있어야 정상적으로 핸들링이 
 DTO형태가 Session 형태의 DTO, SessionAttributes => API Server때 많이 사용
*/
//@SessionAttributes("mid") //해당 세션이 생성되었을 경우 모든 메소드에 세션 값을 Model로 전송가능
public class session_controller {
	
	//session을 의존성 주입형태로 interface를 필드에 선언하여 모든 세션에 적용 가능
	@Autowired
	HttpSession hs;
	
	@GetMapping("/session1.do")
	public String session1(HttpSession se) { //HttpSession(Spring) = HttpServletRequest를 이용하여 session 동일
		String userid = "kim";
		se.setAttribute("mid", userid); //고전
		return null;
	}
	
	//HttpSession : Controller, DAO, DTO, VO
	//해당 세션을 생성 후 문자열 변수로 변환하여 Model로 전달 => JSP에 출력
	@GetMapping("/session2.do")
	public String session2(HttpSession se, Model m) {
		String id = (String)se.getAttribute("mid");
		/*
		System.out.println(id);
		*/
		m.addAttribute("mid", id);
		return "session";
	}
	/*
	 @SessionAttribute = session.getAttribute 동일한 성능을 가진 어너테이션
	 해당 어너테이션 사용시 주의사항은 null일 경우 400 error가 발생할 수 있으므로 
	 name속성 그리고 required 속성을 핸들링하는 것이 중요한 포인트
	 */
	@GetMapping("/session3.do")
	public String session3(@SessionAttribute(name = "mid", required = false) String mid) {
		System.out.println(mid);
		String test = (String)this.hs.getAttribute("mid");
		System.out.println(test);
		return null;
	}
	
	@GetMapping("/session4.do")
	public String session4() {
		//this.hs.getAttribute(""); //필드에 올려놨을 시 바로 업로드 할 수도 있음
		this.hs.invalidate();	//필드에 올려놓은 Session을 로드하여 세션 초기화
		this.hs.removeAttribute("mid"); // 특정 등록된 session키만 삭제
		System.out.println(this.hs.getAttribute("mid"));
		return null;
	}
}
