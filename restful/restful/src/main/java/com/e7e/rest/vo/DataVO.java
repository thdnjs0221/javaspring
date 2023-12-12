package com.e7e.rest.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString   // 디버깅용 값을 편하게 찍어보겡!
public class DataVO {
	private String name;
	private String age;
	private String alias;
}
