package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  //기본생성자
@AllArgsConstructor
public class SearchVO implements Serializable {

	//키워드 조건
	private String searchType;
	private String searchWord;
}
