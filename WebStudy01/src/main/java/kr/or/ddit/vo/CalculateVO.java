package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import kr.or.ddit.calculate.NumericOpertorType;

/**
 * 한번의 사칙연산과 관련된 모든 속성을 가진 객체
 *
 */
public class CalculateVO implements Serializable{
	private int leftOp;
	private int rightOp;
	
	private NumericOpertorType operator;

	public CalculateVO() {
		super();
	}

	public CalculateVO(int leftOp, int rightOp, NumericOpertorType operator) {
		super();
		this.leftOp = leftOp;
		this.rightOp = rightOp;
		this.operator = operator;
	}

	public int getleftOp() {
		return leftOp;
	}

	public void setleftOp(int leftOp) {
		this.leftOp = leftOp;
	}

	public int getRightOp() {
		return rightOp;
	}

	public void setRightOp(int rightOp) {
		this.rightOp = rightOp;
	}

	public NumericOpertorType getOperator() {
		return operator;
	}

	public void setOperator(NumericOpertorType operator) {
		this.operator = operator;
	}
	
	public int getResult() {
		//null check
		return Optional.ofNullable(operator)
				.map((o)->o.operate(leftOp, rightOp)) //갖고 있던 타입변환하기 위해 map사용
				.orElse(0); //없으면 0으로 리턴
			
	}
	
	public String getExpression() {
	      return Optional.ofNullable(operator)
	                  .map((o)->o.getExpression(leftOp, rightOp))
	                  .orElse("");
	   }

	@Override
	public int hashCode() {
		return Objects.hash(leftOp, operator, rightOp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalculateVO other = (CalculateVO) obj;
		return leftOp == other.leftOp && operator == other.operator && rightOp == other.rightOp;
	}

	@Override
	public String toString() {
		return String.format("CalculateVO [%s]", getExpression()); //toString 표현식으로 바꾸기
	}
	
	
	
}
