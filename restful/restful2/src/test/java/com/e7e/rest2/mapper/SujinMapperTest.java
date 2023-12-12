package com.e7e.rest2.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.e7e.rest2.vo.SujinVO;

import lombok.extern.slf4j.Slf4j;

// 일반 개발자는 맵퍼를 만들고 바로 테스트 해보는 것이 정신 건강에 좋음
// 컨트롤러까지 다 맹글공 , 서버 리스타트하다가 지치면 눈물 남!

@Slf4j
@SpringBootTest  // 그냥 스프링에선 설정이 쪼메 다름, 자동으로 JUnit 실행 환경 설정!
public class SujinMapperTest {

	@Autowired  
	private SujinMapper sujinMapper;

	// 데이터가 없으닝 먼저 insert 테스트
	@Test
	@DisplayName("수진테스트 그냥 보이는 이름")
	@Disabled  // 안 할꺼얌!
	public void insTest() {
		
		SujinVO sujinVO = new SujinVO();
		sujinVO.setSujinName("전수진");
		sujinVO.setSujinCont("욜씨밍 욜씨밍");
		sujinVO.setSujinFile("/upload/roze01.png");
		
		assertEquals(1, sujinMapper.insSujin(sujinVO));
	}

	// 수정 테스트
	@Test
	@DisplayName("수정테스통")
	@Disabled
	public void updateTest() {
		SujinVO sujinVO = new SujinVO();
		sujinVO.setSujinNum(3);
		sujinVO.setSujinName("전수진아님");
		sujinVO.setSujinCont("안 욜씨밍");
		sujinVO.setSujinFile("/upload/roze02.jpg");
		
		assertEquals(1, sujinMapper.updateSujin(sujinVO));
		
	}
	
	@Test
	@DisplayName("삭 테스통")
	@Disabled
	public void delTest() {
		SujinVO sujinVO = new SujinVO();
		sujinVO.setSujinNum(3); // 다른 건 필요없음
		
		assertEquals(1, sujinMapper.delSujin(sujinVO));		
	}
	
	@Test
	@DisplayName("리스트 테스통통")
	@Disabled
	public void selListTest() {
		assertEquals(3, sujinMapper.getSujins().size());
	}
	
	@Test
	@DisplayName("1개 row 가져오깅")
	@Disabled
	public void selSujinTest() {
		SujinVO sujinVO = new SujinVO();
		sujinVO.setSujinNum(4); // 다른 건 필요없음		
		
		SujinVO merong = sujinMapper.getSujin(sujinVO);

		log.info("누느로 체킁:{}",merong);
		assertEquals("전수진", merong.getSujinName());
		
	}
	
	// 더미 데이터 생성도 테스트를 이용하면 아주 편하고 효율적임!
	
	@Test
	@DisplayName("테스트를 이용 더미 넣깅")
	public void insDummy() {
		
		SujinVO sujinVO = null;
		//108번뇌 더미 넣깅
		for(int i=1; i<=108; i++) {
			sujinVO = new SujinVO();
			sujinVO.setSujinName("안수진"+i);
			sujinVO.setSujinCont("안수진 내용"+i);
			sujinVO.setSujinFile("사자와판다"+i +"jpg");
			sujinMapper.insSujin(sujinVO);
		}
		
		assertEquals(108, sujinMapper.getSujins().size());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
