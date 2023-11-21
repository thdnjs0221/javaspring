package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="buyerId")
public class BuyerVO implements Serializable{
	private int rnum;
	private String buyerId;
	private String buyerName;
	private String buyerLgu;
	private String lprodNm;
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	private String buyerComtel;
	private String buyerFax;
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	
	private int prodCount;
	
	private Set<ProdVO> prodList;
	
	public boolean hasProds() {
		return prodCount > 0;
	}
}
