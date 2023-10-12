package kr.or.ddit.dummy;

public class Foo {

	//결합력 존재 
//	private Bar bar = new Bar();
//	private Baz baz = new Baz();
	
	
	//Foo Bar 사이에 결합력 없음 -> BarFactory로..
	//private Bar bar = BarFactory.getBar(); 
	
	
	//Foo 누구와도 결합력 없음, 누군가는 setter 호출해줘야함 
	// 전략 패턴
	
	
	private Baz baz;
	public Foo(Baz baz, Bar bar) {
		super();
		this.baz = baz;
		this.bar = bar;
	}
	
	private Bar bar;	
	public void setBar(Bar bar) {
		this.bar = bar;
	}
	@Override
	public String toString() {
		return "Foo [baz=" + baz + ", bar=" + bar + "]";
	}

	

	
}
