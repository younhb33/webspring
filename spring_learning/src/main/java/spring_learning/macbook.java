package spring_learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class macbook {
	
	@PostMapping("/macbook_ok.do")
	public String macbook_ok(macbook_DTO dto) throws Exception {
		
		//int result = this.ys.macbook_insert(dto);
		//System.out.println(result);
		
		return "load";
	}

}