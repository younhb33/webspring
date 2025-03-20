package spring_learning;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;



//Spring Controller + View기초

// Controller란 해당 일반 class를 web에서 작동할 수 있도록 변경하도록 함
@Controller
public class mainpage {
	
	PrintWriter pw = null;
   //@GetMapping : doGet, @PostMapping : doPost
   //@RequestMapping : doService
   
   //throws + HttpServletRequest + HttpServletResponse(View 사용하지 않음)
   @GetMapping("/abc.do") //HttpServletRequest(Response) throws Exception입력한 상태 > veiw따로 필요 없이 즉시 작동할 수 있음
   public void abc(HttpServletRequest req, HttpServletResponse res) throws Exception {
      res.setContentType("text/html;charset=utf-8");
	   
	   this.pw = res.getWriter();
      this.pw.write("<script>"
      		+ "alert('테스트 페이지 입니다.');"
      		+ "</script>");
      this.pw.close();
	   System.out.println("abc페이지");
   }
   
   //View 무조건 사용하는 메소드를 말함
   @PostMapping("/bbb.do")
   //@GetMapping("/bbb.do")>입력하자마자 bbb.jsp를 찾음
   public void bbb(HttpServletRequest req) { // Controller 작동하면서 뷰 찾아 MVC라서 모델이 작동해서 뷰를 찾음
	      // Front-end 값을 이관
	      String pdnm = req.getParameter("pdnm");
	      // View(bbb.jsp)로 이관
	      req.setAttribute("pdnm", pdnm);   
	   }

   
   //return형태의 메소드는 view파일명을 다르게 사용할 수 있습니다.
   //기본은 return null(do와 이름이 같은 jsp를 찾게됨)
   //return ""; (do와 다른 이름의 jsp를  찾게됨)
   @PostMapping("/ccc.do")
   public String ccc(HttpServletRequest req) { // bbb.do와 메소드 형태의 구조만 다름 (void/String)
      String pdnm = req.getParameter("pdnm");
      req.setAttribute("pdnm", pdnm);   
      return "/product_list";
   }

   //request로 view(jsp)로 전달방식 아님
   @PostMapping("ddd.do")
   public ModelAndView ddd(HttpServletRequest req) {
      // ModelAndView(object) : 배열
      String pdnm = req.getParameter("pdnm");
      String pcode = req.getParameter("pcode");
      String pmoney = req.getParameter("pmoney");
      // ModelAndView(object) : 배열
      ModelAndView mv = new ModelAndView();
      mv.addObject("pdnm",pdnm); 	//addObject : 키 배열 형태로 값을 저장
      mv.addObject("pcode",pcode);
      mv.addObject("pmoney",pmoney);
      
      //setView : null 은 Mapping이름과 동일한 jsp를 찾게 됩니다.
      //mv.setView(null);
      
      //Mapping과 다른 이름을 사용하고 싶을 경우
      mv.setViewName("bbb");
      
      return mv; //ModelAndView를 선언시 ModelAndView 객체명을 사용해서 return해야 함
   }
   
   //
   @PostMapping("/eee.do")
   public String eee(HttpServletRequest req, Model m) {
	   String pdnm = req.getParameter("pdnm");
	   String pcode = req.getParameter("pcode");
	   String pmoney = req.getParameter("pmoney");
	   //Model(interface)를 이용하여 JSP로 값을 전달(JSTL형태로 값 출력)
	   m.addAttribute("pdnm",pdnm);
	   m.addAttribute("pcode",pcode);
	   m.addAttribute("pmoney",pmoney);
	   
	   return "ddd";
   }
   
   
}
