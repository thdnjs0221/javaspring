package kr.or.ddit.calculate;

public enum NumericOpertorType {
	//enum 특성: 상수와 똑같은 name값을 가지게 된다, 배열의 형태로 관리(선언된 순서대로 인덱스가 존재), getter만 사용
	
	//함수형 인터페이스 람다 적용 가능
	PLUS('+', (l,r)->l+r),
	MINUS('-', (l,r)->l-r), 
	MULTIPLY('*', (l,r)->l*r), 
	DIVIDE('/', (l,r)->{return l/ r;});
	
	
	private NumericOpertorType(char sign, BiOperandOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	
	private char sign;
	private BiOperandOperator realOperator;
	public char getSign() {
		return sign;
	}
	
	public int operate(int leftOp, int rightOp) {
		return realOperator.operate(leftOp, rightOp);
	}
	public String getExpression(int leftOp, int rightOp) {
		return String.format("%d %c %d = %d",leftOp, sign ,rightOp, operate(leftOp, rightOp));
		
	}
}
