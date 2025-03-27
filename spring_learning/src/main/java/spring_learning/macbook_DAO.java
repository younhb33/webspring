package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
//DAO(mybatis), DTO(setter,getter), VO(getter)가 뭔지 알아햐 함
//DAO : 데이터를 Access하는 역할

@Repository("macbook_DAO") // @Repository : Model을 Controller에서 호출될 수 있게 하는 어너테이션/
public class macbook_DAO implements macbook_mapper { // @mapper을 interface로드 하여 dao작성

	// mybatis로 db연결
	@Resource(name = "template")
	public SqlSessionTemplate st;

	// 전체 리스트 출력 메소드
	@Override
	public List<macbook_DTO> macbook_select() {
		// selectOne : 데이터 한개만 가져올 때 (dto, List배열, ArrayList, Map 등등 다 가능)
		// selectList : 데이터 여러개를 가져올 때 (List배열로 가져옴)
		List<macbook_DTO> classList = this.st.selectList("macbook_select");
		return classList;
	}

	// 과정 생성 메소드
	@Override
	public int macbook_insert(macbook_DTO dto) {
		int result = this.st.insert("macbook_insert", dto); // st. 컨스페 > sqlsesstion template용 선택
		return result;
	}

	// 하나의 데이터만 가져오는 메소드
	@Override
	public macbook_DTO macbook_one(String midx) {
		// System.out.println(midx);
		// setter형태로 db에 있는 데이터를 이관
		// selectOne("mapper.xml에서 사용하는 id명",매개변수)
		// 매개변수를 하나 이상 사용할 경우 클래스 배열로 값 가져오기
		macbook_DTO onedata = this.st.selectOne("macbook_one", midx); // selectOne 때리면 안됨
		return onedata;

	}

	// 데이터 수정 메소드
	@Override
	public int macbook_update(macbook_DTO dto) {
		int result = this.st.update("macbook_update", dto);
		return result;
	}

	// 데이터 삭제 메소드
	@Override
	public int macbook_delete(int midx) {
		int result = this.st.delete("macbook_delete", midx);
		return result;
	}

}
