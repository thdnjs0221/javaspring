package kr.or.ddit.buyer;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;


import kr.or.ddit.common.stereotype.Preparer;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;

//모호할때 Component
//bean으로 등록하는 방법 (xml 직접등록, 어노테이션 커스텀해서 등록)
@Slf4j
@Preparer
public class BuyerViewPreparer implements ViewPreparer {

	@Inject
	private MemberDAO menuDAO;
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		log.info("==================view prearer====================");
//		<a href='contextPath/buyer/form'>제조사등록</a>
		
		MenuVO menu1 = new MenuVO();
		menu1.setMenuText("제조사 등록");
		menu1.setMenuUrl("/buyer/form");
		
		MenuVO menu2 = new MenuVO();
		menu2.setMenuText("제조사 목록");
		menu2.setMenuUrl("/buyer");
		
		List<MenuVO> menuList = Arrays.asList(menu1,menu2);
		
		Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menuList", menuList);
	}

}
