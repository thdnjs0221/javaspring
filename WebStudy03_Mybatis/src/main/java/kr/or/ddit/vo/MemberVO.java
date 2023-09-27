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
 */
@Getter	
@Setter
@EqualsAndHashCode(of = "memId")
@ToString
public class MemberVO implements Serializable{
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
	
	private Set<ProdVO>prodSet;  //set 중복제거 has many관계(1:N 관계)
	
	
	
	

}
