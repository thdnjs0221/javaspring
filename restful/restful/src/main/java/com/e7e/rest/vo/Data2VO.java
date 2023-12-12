package com.e7e.rest.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Data2VO {
	private String name;
	private String alias;
	private List<MultipartFile> files; // 웬만하면 배열말공 List롱
}
