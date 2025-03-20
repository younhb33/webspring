package spring_learning;

import java.util.List;

public interface macbook_Service {
	public int macbook_insert(macbook_DTO dto);
	List<macbook_DTO> macbook_select(); 
	public int macbook_delete(int midx);
	/*macbook_mapper에 있는 인터페이스를 그대로 가져오면 됨*/
}
