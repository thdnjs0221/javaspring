package kr.or.ddit.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.calculate.NumericOpertorType;

class CalculateVOTest {

	@Test
	void test1() {
		CalculateVO dummmy  = new CalculateVO(3,5,NumericOpertorType.PLUS);
		System.out.println(dummmy);
	}
	
	@Test
	void test2() {
		CalculateVO dummmy  = new CalculateVO();
		System.out.println(dummmy);
	}

}
