package kr.or.ddit.prod;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.stereotype.Preparer;
import kr.or.ddit.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Preparer
public class ProdViewPreparer implements ViewPreparer {

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		log.info("==================view prearer====================");
//		<a href='contextPath/buyer/form'>제조사등록</a>
		
		MenuVO menu1 = new MenuVO();
		menu1.setMenuText("상품 등록");
		menu1.setMenuUrl("/prod/prodInsert.do");
		
		MenuVO menu2 = new MenuVO();
		menu2.setMenuText("상품 목록");
		menu2.setMenuUrl("/prod/prodList.do");
		
		List<MenuVO> menuList = Arrays.asList(menu1,menu2);
		
		Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menuList", menuList);
	}

}
