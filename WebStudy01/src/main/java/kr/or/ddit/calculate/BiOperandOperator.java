package kr.or.ddit.calculate;

@FunctionalInterface
public interface BiOperandOperator {
	//함수형 인터페이스 람다 적용 가능!!
	//인터페이스가 가지고 있는 구현가능한 메소드가 '하나'일때 함수형 인터페이스라고한다
	//자바에서는 함수를 객체라고 하지 않는다. 그걸 동일시할때는 FunctionalInterface으로 한다 

	public int operate(int leftOp ,int rightOp);

}
