package spring_learning;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

// I/O Controller
//원래 서블릿할땐 파일업로드용 어노테이션 썼었는데 지금은 commons-fileupload 라이브러리 불러와서 안써도 가능 
@Controller
public class mainpage3 {
	//MultipartFile : Spring I/O = xml 환경설정과 연결
	@PostMapping("/fileok.do")
	public String fileupload(MultipartFile mfile) {
		if(mfile.getSize() > 2097152) {
			System.out.println("test");
		}
		System.out.println(mfile.getOriginalFilename());
		return "load";
	}
	
	//여러개의 첨부파일을 받는 메소드
	/*
	 MultipartFile[] : Interface로 파일을 Front-end에서 받을 경우
	 반복문으로 처리시 multiple로 전송할 경우에는 별도의 조건문 없이 저장이 가능
	 단, 같은 name으로 여러개의 파일전송 속성을 사용하였을 경우 반복문 사용시
	 조건문이 없을 경우 500 에러로 인하여 코드가 문제가 발생할 수 있습니다.
	 FileCopyUtils.copy : 파일전송 관련된 I/O 이며 Spring, Spring-boot
	 */
	@PostMapping("/fileok2.do")
	public String fileupok(MultipartFile[] mfile, HttpServletRequest req)throws Exception {
		String url = req.getServletContext().getRealPath("/upload/"); //경로
		
		int w= 0;
		while(w <mfile.length) {
			FileCopyUtils.copy(mfile[w].getBytes(),new File(url + mfile[w].getOriginalFilename())); 
			w++;
		}
		return "load";
	}
	
	//웹 디렉토리에 있는 파일 리스트를 출력하는 Controller
	@GetMapping("/filelist.do")
	public String filelist(HttpServletRequest req) throws Exception {
		//웹 디렉토리
		String url = req.getServletContext().getRealPath("/upload/"); //경로
		//웹 디렉토리에 저장되어 있는 모든 파일명을 담는 클래스배열
		File f = new File(url);
		String f_list[] = f.list(); // 원시배열 만들어짐(파일을 다 가져옴)
		//System.out.println(f_list.length);
		ArrayList<String> filenm = new ArrayList<String>(Arrays.asList(f_list));
		//System.out.println(filenm); //클래스 배열로 담아서 배열출력
		req.setAttribute("filenm", filenm); //filenm값 탑재
		return null;
	}
	
	
	//@RequestParam : Front-end 전달된 값 request.getParameter()
	//@RequestParam("fnm") String filenm 는(=) String filenm = RequestParam.getParameter("fnm");랑 같음
	@PostMapping("/filedel.do")
	public String filedel(@RequestParam("fnm") String filenm, HttpServletRequest req, Model m) throws Exception {
		String url = req.getServletContext().getRealPath("/upload/");// 지워야 할 파일 있는 경로 
		//System.out.println(filenm); //파일 이름 확인
		File f = new File(url + filenm); //업로드란 디렉토리 안에 파일명을 가져옴
		f.delete(); 	// 파일 삭제 메소드
		
		//JS메세지를 작성 후 Model로 JSP로 전달을 하게 됨
		String msg = "alert('정상적으로 삭제 되었습니다.'); "
				+ "location.href='./filelist.do';";
		
		m.addAttribute("msg",msg); //모델에 msg값 태움
		return "load";
	}
	
	
	
	//jstl로 로드 후 값 전달
	@GetMapping("/jstl/jstl6.do")
	public String jstl6(Model m) {
		//Model을 이용하여 jstl6.jsp로 값을 전달
		//출력 top.jsp에서 ${} 변수를 출력함
		String level = "일반수강생";
		
		String corp="(주)중앙정보처리학원";
		String tel="02-1234-5678";
		
		
		m.addAttribute("level",level);
		m.addAttribute("corp",corp);
		m.addAttribute("tel",tel);
		
		return null;
	}
	
	

}