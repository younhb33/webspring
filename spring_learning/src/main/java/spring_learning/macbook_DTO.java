package spring_learning;

import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Repository("macbook_DTO")
public class macbook_DTO {
	int midx, class_day, class_price, class_sales;
	String class_part, class_cate, class_name, class_info, class_teacher, class_object, class_use, today;
}