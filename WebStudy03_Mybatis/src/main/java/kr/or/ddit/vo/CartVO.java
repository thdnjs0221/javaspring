package kr.or.ddit.vo;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter	
@Setter
@EqualsAndHashCode(of = "cartNo")
@ToString
public class CartVO {
	private String cartMember;
	private String cartNo;
	private String cartProd;
	private Integer cartQty;  //물리 컬럼
	private LocalDate cartDate; //논리컬럼

	private ProdVO prod; //has a 
	private MemberVO member; // has a 한건의 분류된 사람 
	

}
