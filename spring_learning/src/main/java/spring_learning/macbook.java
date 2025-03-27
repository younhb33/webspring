package spring_learning;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class macbook {
	
	//@Autowird, @Inject : 의존성 주입 xml > Java, Java => XML(특수함수나 아이디 값을 자바에서 쓸 수있게 하는 어너테이션)
	//ibatis로 db연결
	/*
	@Inject
	SqlSessionFactory sqlfact;
	*/
	/*
	 SqlSessionFactoryBean 타입의 객체가 필요하다는 걸 스프링에게 알리는 것.
	 스프링이 SqlSessionFactoryBean을 찾아서 sqlfact에 자동으로 할당 
	 SqlSessionFactory: sql정보를 session에 올려놓음 session에 올려놔서 정보를 불러옴
	 */
	//@Resource : new 클래스명 호출과 동일하게 작동을 하며, 
	//@Repository의 이름을 가져오는 역할
	@Resource(name = "macbook_DAO")
	private macbook_DAO dao;
	
	@Resource(name = "macbook_DTO") //dto파일에 @Repository로 태그해야 함(@Repository("macbook_DTO")
	public macbook_DTO macbook_DTO;
	
	//과정 리스트 출력
	@GetMapping("/macbook_list.do")
	public String macbook_list(Model m) {
		
		//List<macbook_DTO> : DTO형태의 배열로 생성하여, JSP로 전달 
		List<macbook_DTO> classList = this.dao.macbook_select();
		//System.out.println(classList.get(0).class_name);
		m.addAttribute("ea",classList.size());//db에 저장된 데이터 수 나옴
		m.addAttribute("classList",classList);
		return null;
	}
	
	
	//과정 수정 페이지(출력)
	@PostMapping("/macbook_modify.do")
	//public String macbook_modify(String midx) {	//아래 코드와 같은 속성 단, 배열을 사용시는 무조건 아래 형태로 사용하기
	public String macbook_modify(@RequestParam("midx") String midx, Model m) {	//dto로 할 필요도 없음
		//System.out.println(midx);
		
		//DTO의 setter에 값을 이관한 상황
		macbook_DTO onedata = this.dao.macbook_one(midx);
		//System.out.println(onedata.class_name);
		m.addAttribute("onedata",onedata); //jstl로 값을 이관함
		return null;
	}
	
	
	//과정 수정 업데이트
	
	@PostMapping("/macbook_modifyok.do")
	public String macbook_modifyok(macbook_DTO dto, Model m) {
		//insert, update, delete 무조건 결과를 int로 받음
		int result = this.dao.macbook_update(dto); //DAO로 값을 전송
		System.out.println(result);
		
		String msg = "";
		
		if(result > 0) {
			msg = "alert('정상적으로 데이터가 수정 되었습니다.');"
					+ "location.href='./macbook_list.do';";
		}
		m.addAttribute("msg", msg);
		return "load";
	}
	

	PrintWriter pw = null;
	// 개설된 과정을 삭제하는 메소드
	//Modle과  HttpServletResponse 은 함께 사용하지 못합니다. 두개의 interface역할이같으므로 하나만 사용 가능
	@PostMapping("/macbook_delete.do")
	public String macbook_delete(@RequestParam("midx") String midx, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=utf-8");
		this.pw = res.getWriter();
		//this.pw.write("test");
		int result = this.dao.macbook_delete(Integer.parseInt(midx));
		if(result > 0) {
			this.pw.print("<script>"
					+ "alert('올바르게 해당 과정을 삭제하였습니다.');"
					+ "location.href='./macbook_list.do';"
					+ "</script>");
		}
		this.pw.close();
		return null;
	}
	
	//과정개설 메소드
	@PostMapping("/macbook_ok.do")
	public String macbook_ok(macbook_DTO dto, Model m) throws Exception {
		
		//db연결하기
		try {
			//SqlSession session = this.sqlfact.openSession(); // ibatis로 db연결
			//System.out.println("성공 : " + session); //성공 : org.apache.ibatis.session.defaults.DefaultSqlSession@60304449
			//String result = this.dao.selectnow();
			//System.out.println(result);
			int result = this.dao.macbook_insert(dto);
			//System.out.println(result);
			String msg = "";
			if(result > 0) {
				msg = "alert('과정개설이 올바르게 생성되었습니다.');"
						+ "location.href='./macbook_list.do';";
			}
			m.addAttribute("msg", msg);
		} catch (Exception e) {
			
		}finally {
			
		}
		
		//System.out.println(this.sqlfact); //org.mybatis.spring.SqlSessionFactoryBean@47a1ac62 뜸

		
		return "load";
	}

}