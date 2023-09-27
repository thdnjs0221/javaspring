package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 하나의 상품에 대한 정보를 캡슐호 하기 위한 도메인 레이어
 */
@Data
@EqualsAndHashCode(of="prodId")
public class ProdVO {
	private String prodId;
	private String prodName;
	private String prodLgu;
	private String prodBuyer;
	private Integer prodCost;
	private Integer prodPrice;
	private Integer prodSale;
	private String prodOutline;
	private String prodDetail;
	private String prodImg;
	private Integer prodTotalstock;
	private String prodInsdate;
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private LprodVO lprod;//has a (1:1관계)
	
}
