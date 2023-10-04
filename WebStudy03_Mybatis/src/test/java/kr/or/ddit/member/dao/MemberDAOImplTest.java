package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.MemberVO;

class MemberDAOImplTest {
	MemberDAO dao = new MemberDAOImpl();
	
	@Test
	void testSelectMember() {
		MemberVO member = dao.selectMember("a001");
		assertNotNull(member);
		member.getProdSet()
		.forEach((pv)->{			
			System.out.println(pv.getProdName()+", "+pv.getLprod().getLprodNm());
		});
	}
	

	@Test
	void testSelectMemberForAuth() {
		MemberVO inputData = new MemberVO();
		inputData.setMemId("a001");
		inputData.setMemPass("4");
		MemberVO saved = dao.selectMemberForAuth(inputData);
		assertNotNull(saved);
	}
	
	@Test
	   void testSelectMemberList() {
	      List<MemberVO> memList =  dao.selectMemberList(paging);
	      assertNotNull(memList);
	      assertNotEquals(0, memList);
	   }
	   
	
}
