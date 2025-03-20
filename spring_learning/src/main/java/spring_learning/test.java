package spring_learning;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
public class test implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		String search = request.getParameter("search");
		System.out.println(search);
		System.out.println("연습 spring Controller");
		
		//Controller는 html 못 불러옴
		//mv.setViewName("test.html");
		mv.setViewName("search.jsp");
		
		return mv;
	}
}
