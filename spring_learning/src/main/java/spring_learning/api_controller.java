package spring_learning;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//API 전용 Controller

//CORS 해결 어너테이션(Spring, Spring-boot 방식)
@CrossOrigin(origins="*", allowedHeaders = "*") 

@RestController
public class api_controller {
	
	PrintWriter pw = null; //Front-end가 값을 가져갈 수 있도록 함
	
	
	@Resource(name = "macbook_member_DTO")
	macbook_member_DTO dto;
	
	@Resource(name = "user_DAO")
	user_DAO dao;
	
	@GetMapping("/api_data6.do")
	public String api_data6(HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		List<macbook_member_DTO> result = this.dao.all_list();
		
		JSONObject alldata = new JSONObject(); //{}
		JSONArray ja = new JSONArray(); //[]
		int w = 0;
		while(w < result.size()) { //db에 있는 정보를 하나하나 불러옴
			//System.out.println(result.get(w).mid);
			JSONObject jo = new JSONObject(); //{}
			jo.put("midx", result.get(w).midx);
			jo.put("mid", result.get(w).mid);
			jo.put("mname", result.get(w).mname);
			jo.put("memail", result.get(w).memail);
			ja.put(jo);
			//System.out.println(jo);
			//this.pw.print(jo);
			w++;
		}
		alldata.put("member", ja);
		this.pw.print(alldata);
		this.pw.close();
		
		return null;
	}
	
	@GetMapping("/api_data5.do") //선생님 풀이
	public String api_data5(HttpServletResponse res) throws Exception {
		/*//CORS 해결 (servlet 형식) 단점 : 메소드마다 써줘야함(구닥다리)
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Credentials", "true");
		*/
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		String data[][] = {
			{"모니터","키보드","마우스"},
			{"NEW","BEST","NEW"}
		};
		//반복문 사용
		int w = 0;
		String keyname = ""; //서브키
		
		JSONObject alldata = new JSONObject(); //대표키
		JSONObject jb = new JSONObject(); //서브키
		while(w < data.length) {
			
			JSONArray ja = new JSONArray();  //데이터 배열[]
			for(int f=0; f<data[w].length; f++) {
				ja.put(data[w][f]); //데이터 배열을 생성
			}
			//System.out.println(ja);
			/*데이터 배열에 맞는 보조키를 적용하기 위함*/
			if(w==0) {
				keyname = "product_name";
			}else {
				keyname = "product_ico";
			}
			jb.put(keyname, ja);
			//System.out.println(jb);
			jb.put(keyname, ja);
			w++;
		}
		alldata.put("product", jb); //대표키 생성은 최종 반복문 다음에 코드를 작성
		System.out.println(alldata);
		this.pw.print(alldata); //Front-end가 출력
		
		
		
		return null;
	}
	
	
	
	@GetMapping("/api_data4.do")
	public String api_data4(HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		String db[][] = {
			{"모니터","NEW"},
			{"키보드","BEST"},
			{"마우스","NEW"}
		};
		JSONArray ja = new JSONArray();
		ja.put(db[0][0]);
		ja.put(db[1][0]);
		ja.put(db[2][0]);
		
		JSONArray ja2 = new JSONArray();
		ja2.put(db[0][1]);
		ja2.put(db[1][1]);
		ja2.put(db[2][1]);

		
		
		JSONObject jo = new JSONObject();
		jo.put("product_name", ja);
		jo.put("product_ico", ja2);
		
		JSONObject jo2 = new JSONObject();
		jo2.put("products", jo);
		System.out.println(jo2);
		this.pw.print(jo2);
		this.pw.close();
		return null;
	}
	
	
	@GetMapping("/api_data3.do")
	public String api_data3(HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		String db[] = {"hong","홍길동","hong@nate.com","서울","01012345678"};
		JSONObject jo = new JSONObject();
		jo.put("id", db[0]);
		jo.put("name", db[1]);
		jo.put("email", db[2]);
		jo.put("area", db[3]);
		jo.put("phone", db[4]);
		//JSON 키배열 순서는 수시로 변경될 수 있음
		//System.out.println(jo); //키배열 생성 확인 완료
		//JSONArray ja = new JSONArray(jo); 코드 줄이면 이렇게 사용 가능
		JSONArray ja = new JSONArray();
		ja.put(jo);
		JSONObject jo2 = new JSONObject();
		jo2.put("myinfo", ja);
		this.pw.print(jo2);
		this.pw.close();
		return null;
	}
	
		
	
	@GetMapping("/api_data2.do")
	public String api_data2(HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		/*//2차 일반 배열
		JSONArray ja = new JSONArray();
		ja.put("홍길동");
		ja.put("강감찬");
		JSONArray ja2 = new JSONArray();
		ja2.put(ja);
		this.pw.print(ja2);
		*/
		JSONArray ja = new JSONArray();
		ja.put("홍길동");
		ja.put("강감찬");
		JSONObject jo = new JSONObject();
		jo.put("member", ja);
		JSONArray ja2 = new JSONArray();
		ja2.put(jo);
		this.pw.print(ja2); //맨 마지막 JSON 객체를 출력함
		
		this.pw.close();
		return null;
	}
	
	
	
	
	
	@GetMapping("/api_data.do")
	public String api_data(HttpServletResponse res) throws Exception { //실무에선 try~catch로 때려야 함
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		/*
		 JSONArray : []
		 JSONObject : {} //키를 생성
		 
		 org.json.simple : add사용
		 org.json : put 사용
		 
		 */
		
		/*//[”a”,”b”,”c”,”d”]
		//simple아니고 org.json
		JSONArray ja = new JSONArray();
		ja.put("a");
		ja.put("b");
		ja.put("c");
		ja.put("d");
		//printWriter로 출력함 System.out이거 안 씀
		this.pw.print(ja);
		*/
		
		//{”data” :  [”a”,”b”,”c”,”d”] } 
		//simple아니고 org.json
		JSONArray ja = new JSONArray();
		ja.put("a");
		ja.put("b");
		ja.put("c");
		ja.put("d");
		JSONObject jo = new JSONObject();
		jo.put("data", ja);
		
		this.pw.print(jo);
		
		this.pw.close();
		
		
		System.out.println("test");
		return null;
	}
}
