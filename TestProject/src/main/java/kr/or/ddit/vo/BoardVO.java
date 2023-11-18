package kr.or.ddit.vo;

import lombok.Data;

@Data
public class BoardVO {
	private int boNo;
	private String boTitle;
	private String boWriter;
	private String boContent;
	private String boDate;
	private int boHit;
	
//	private Integer[] delBoardNo;
//	private MultipartFile[] boFile;
//	private List<BoardFileVo> boardFileList;

	
}
