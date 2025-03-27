package spring_learning;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class banner_controller {
	List<String> listdata = null;
	Map<String, String> mapdata = null;
	PrintWriter pw = null;
	String result = null;
	int callback = 0;
	ModelAndView mv = null;
	
	@Resource(name = "banner_DTO")
	banner_DTO dto;
	
	@Resource(name = "banner_DAO")
	banner_DAO dao;
	
	@Resource(name = "file_rename") // 파일명을 개발자가 원하는 형태로 변경
	file_rename fname;
	
	//Field에 있는 dto와 매개변수에 있는 dto 다른 형태를 가지고 있습니다.
	//this.dto => Field에 있는 dto 지칭, dto => 매개변수에 있는 dto
	
	//@ModelAttribute : 1:1매칭을 해서 name과 DTO자료형 변수 중에 같은 이름이 있으면 무조건 값을 setter발동
	@PostMapping("/banner/bannerok")
	public String bannerok(@ModelAttribute(name = "dto") banner_DTO dto,
			MultipartFile bfile, HttpServletRequest req
			) throws Exception {
		
		String file_new = null; 
		if(bfile.getSize() > 0) { //용량으로 체크
			
			
			file_new = this.fname.rename(bfile.getOriginalFilename()); // file_name.java로 값 보냄
			//웹 디렉토리 개발자가 생성한 파일명으로 저장하는 코드
			String url = req.getServletContext().getRealPath("/upload/");
			//System.out.println(url); //C:\webspring\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\spring_learning\
			FileCopyUtils.copy(bfile.getBytes(), new File(url + file_new)); //새로운 파일명으로 저장
			
			
			dto.setFile_url("/upload/" + file_new); //웹 디렉토리 경로 및 파일명
			dto.setFile_new(file_new);	//개발자가 원하는 방식으로 파일명을 변경값
			dto.setFile_ori(bfile.getOriginalFilename()); //사용자가 적용한 파일명
		}
		this.callback = this.dao.new_banner(dto); //this.때리면 안됨 위에 필드 dto데려옴
		System.out.println(this.callback);
		return null;
	}
	
	@GetMapping("/banner/bannerlist")
	public String bannerlist(Model m) {
		List<banner_DTO> all = this.dao.all_banner();
		m.addAttribute("all",all);
		
		return null;
	}	
}
