package kr.or.ddit.vo;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 하나의 상품에 대한 정보를 캡슐호 하기 위한 도메인 레이어
 */
@Data
@EqualsAndHashCode(of="prodId")
public class ProdVO {
	private int rnum;
	
	@NotBlank(groups = UpdateGroup.class) //문자열대상으로
	private String prodId;
	@NotBlank(groups = InsertGroup.class)
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
	private String prodImg;
	@NotNull
	@Min(0)
	private Integer prodTotalstock;
	private String prodInsdate;
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
