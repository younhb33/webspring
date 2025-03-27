package spring_learning;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

//@Mapper : mapper.xml과 연동하는 interface입니다.
//**mapper.xml에서 사용하는 id기준으로 메소드 이름을 설정하게 됨
@Mapper
public interface macbook_mapper {
	public int macbook_update(macbook_DTO dto); // 데이터 수정 DAO

	public int macbook_insert(macbook_DTO dto); // 신규 데이터 입력

	List<macbook_DTO> macbook_select();

	macbook_DTO macbook_one(String midx); // 하나의 데이터만 가져옴

	public int macbook_delete(int midx); // 삭제 처리(숫자 값으로 처리)
	/*
	 * database값을 가져옴 > dto에 던짐 mapper.xml에 있는 아이디를 만들어야(중복 나면 난리남) 이 아이디값을 인터페이스로
	 * 사용할 수 있음
	 */
}
