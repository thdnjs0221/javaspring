package com.e7e.rest2.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


// 시작은 테이블 1개당 VO 1개지만, 필요에 의해, 추가 확장은 자유의지롱
@Getter
@Setter
@ToString       // 디버깅을 편리하겡
public class SujinVO {
	private int sujinNum;
	private String sujinName;
	private String sujinCont;
	private String sujinFile;	
}
