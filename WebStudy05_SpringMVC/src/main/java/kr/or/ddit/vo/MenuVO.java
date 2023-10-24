package kr.or.ddit.vo;

import lombok.Data;

/**
 * 한쌍의 메뉴 정보를 표현할 Domain
 *
 */

@Data
public class MenuVO {

	private String MenuText;
	private String MenuUrl;
	
	private String MenuColor;
}
