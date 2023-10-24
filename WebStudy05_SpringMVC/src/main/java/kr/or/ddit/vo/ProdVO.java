package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 하나의 상품에 대한 정보를 캡슐호 하기 위한 도메인 레이어
 */
@Data
@EqualsAndHashCode(of="prodId")
public class ProdVO implements Serializable {
	private int rnum;
	
	@NotBlank(groups = UpdateGroup.class) //문자열대상으로
	private String prodId;
	@NotBlank()
	private String prodName;
	@NotBlank(groups = InsertGroup.class)
	private String prodLgu;
	@NotBlank(groups = InsertGroup.class)
	private String prodBuyer;
	@NotNull //integer 대상으로
	private Integer prodCost;
	@NotNull
	private Integer prodPrice;
	private Integer prodSale;
	private String prodOutline;
	private String prodDetail;

	@NotBlank(groups = InsertGroup.class)
	private String prodImg;	//데이터베이스와 주고 받는 용도로
	private MultipartFile prodImage;  //클라이언트와 대화할때 쓰는
	
	//@data 주석으로 하고 setter 만들어주기 만든 후 주석 풀기
	public void setProdImage(MultipartFile prodImage) {
		if(prodImage!=null && !prodImage.isEmpty()) {
			this.prodImage = prodImage;			
			prodImg = UUID.randomUUID().toString();
		}
	}
	
	public void saveTo(File saveFolder) throws IllegalStateException, IOException {
		if(prodImage!=null)
		prodImage.transferTo(new File (saveFolder, prodImg));
	}
	
	@NotNull
	@Min(0)
	private Integer prodTotalstock;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate prodInsdate;
	
	@NotNull
	@Min(0)
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	//@
	private int prodCount;
	//private int memCount; //sem
	
	//private Set<ProdVO>prodSet;
	private LprodVO lprod;//has a (1:1관계) 상품하나가 분류하나에 속하기때문에
	private BuyerVO buyer; // has a
	
	private List<CartVO> cartList; //중복제거를없애서 List, has many
	
}
