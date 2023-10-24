package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "buyerId")
public class BuyerVO implements Serializable{
	
	private int rnum;
	@NotBlank(groups = {UpdateGroup.class, DeleteGroup.class})
	private String buyerId;
	@NotBlank
	private String buyerName;
	@NotBlank
	private String buyerLgu;
	
	private LprodVO lprod; // has a (1:1)
	
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	@NotBlank
	private String buyerComtel;
	@NotBlank
	private String buyerFax;
	@NotBlank
	@Email
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	
	private List<ProdVO> prodList; // has many(1:N)
	
	private String buyerImg; //db랑 대화할때 
	
	private MultipartFile buyerImgae; //클라이언트랑 대화할때
	
	public void setBuyerImgae(MultipartFile buyerImgae) {
		if(buyerImgae!=null && !buyerImgae.isEmpty()) {
			this.buyerImgae = buyerImgae;
			buyerImg = UUID.randomUUID().toString();  //랜덤으로 문자열 ! 저장명인 메타데이터만들어 놓기
			
		}
	}
	
	//여기서 저장해주기
	public void saveTo(File saveFolder) throws IllegalStateException, IOException {
		if(buyerImgae!=null) {
			buyerImgae.transferTo(new File(saveFolder,buyerImg));
		}
		
	}
	
}











