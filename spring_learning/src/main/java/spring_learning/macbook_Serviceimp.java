package spring_learning;

import java.util.List;

import org.springframework.stereotype.Service;

//@Mapper에 interface와 macbook_Service의 interface를 연결해주는 역할을 함

@Service
public class macbook_Serviceimp implements macbook_Service {

	@Override
	public int macbook_insert(macbook_DTO dto) {
		
		return 0;
	}

	@Override
	public List<macbook_DTO> macbook_select() {
		
		return null;
	}

	@Override
	public int macbook_delete(int midx) {
		
		return 0;
	}

}
