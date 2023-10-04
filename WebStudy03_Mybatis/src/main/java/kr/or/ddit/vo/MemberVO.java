package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.validate.grouphint.InsertGroup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 한 사람의 회원 정보를 캡슐화하기 위한 도메인 레이어.
 * 
 * 
 * 
 *  **** Data Mapper를 이용해 다중 엔터티를 조회하는 단계
 * 1. 주엔터티를 기준으로 엔터티간 관계 파악
 *  	1:1 ex) 하나의 상품과 그 상품의 제조사 정보 PROD(1) : BUYER(1)
 *  	1:N ex) 한사람의 구매정보 MEMBER(1) : PROD(N)
 * 2. 각 엔터테의 조회 결과를 바인딩할 VO에 엔터티간의 관계를 has 관계로 반영
 * 		1:1 - has a ex) ProdVO has a BuyerVO
 * 		1:N - has many ex) MemeberVO has many ProVO
 * 		ex) 하나의 제조사(BUYER)와 그 제조사의 제조 상품(PROD) 목록을 조회.
 * 			BUYER(1) : PROD(N) -> BuyerVO has many ProdVO
 * 
 *		ex) 하나의 제조사(BUYER)와 그 제조사의 분류명(LPROD)을 조회
 *			BUYER(1) : LPROD(1) -> BuyerVO has a LpordVO
 * 3. 조회결과 바인딩시, resultType 대신 resultMap을 이용해 수동 바인딩 설정.
 * 		has many : collection (ofType)
 * 		has a  : association (javaType)
 *
 */
@Getter	
@Setter
@EqualsAndHashCode(of = "memId")
@ToString
public class MemberVO implements Serializable{
	private int rnum;
	@NotBlank(groups = {Default.class, DeleteGroup.class})
	private String memId;
	@NotBlank(groups = {Default.class, DeleteGroup.class})
	@Size(min = 4, max= 12, groups = {Default.class, DeleteGroup.class})
	@ToString.Exclude //toString 제외
	@JsonIgnore  //마샬링 제외
	private transient String memPass;  //transient 직렬화 제외
	@NotBlank(groups = InsertGroup.class)
	@Size(max= 10, groups = InsertGroup.class)
	private String memName;
	@Size(min = 6, max = 6, groups = InsertGroup.class)
	@ToString.Exclude
	@JsonIgnore
	private transient String memRegno1;
	@Size(min = 7, max = 7,	groups = InsertGroup.class)
	@ToString.Exclude
	@JsonIgnore
	private transient String memRegno2;
	private LocalDate memBir;
	
	@NotBlank
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	@Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}")
	private String memHometel;
	@Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}")
	private String memComtel;
	@Pattern(regexp = "010-\\d{3,4}-\\d{4}")
	private String memHp;
	@NotBlank
	@Email
	@Size(min = 8)
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private LocalDate memMemorialday;
	
	@Min(0) //음수설정 못함
	private Integer memMileage;
	private boolean memDelete;
	private int prodCount;
	
	//private Set<ProdVO>prodSet;  //set 중복제거 has many관계(1:N 관계)
	private Set<CartVO>cartSet;    //has many관계(1:N 관계) 구매기록
}
