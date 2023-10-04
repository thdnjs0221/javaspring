package kr.or.ddit.vo;

import java.util.List;

import kr.or.ddit.paging.BootstrapPaginationRender;
import kr.or.ddit.paging.DefaultPaginationRender;
import kr.or.ddit.paging.PagingnationRenderer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *  페이징 처리와 관련된 모든 속성을 가진 자바빈
 * 
 * 
 */

@Getter
@ToString
@NoArgsConstructor
public class PaginationInfo<T> {
	
	
	public PaginationInfo(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int totalRecord;  //select 쿼리 필요
	private int currentPage;  //request prameter로 확보
	
	private int screenSize = 10; //개발자 임의 결정 필요하다면 변경 가능해야함
	private int blockSize = 5;
	
	
	//연산식 필요
	//CurrentPage 가결졍되면
	private int totalPage; 
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	private List<T> dataList;
	
	private SearchVO simpleCondition;
	
	private PagingnationRenderer renderer = new DefaultPaginationRender();
	
	public void setSimpleCondition(SearchVO simpleCondition) {
		this.simpleCondition = simpleCondition;
	}
	
	public void setRenderer(PagingnationRenderer renderer) {
		this.renderer = renderer;
	}
	
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (totalRecord + (screenSize - 1)) / screenSize;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		
		endRow = currentPage *screenSize;
		startRow = endRow -(screenSize-1);
		endPage = blockSize *((currentPage +(blockSize-1) ) / blockSize);
		startPage = endPage - (blockSize-1);
		
	}
	
	public int getEndPage() {
		return endPage < totalPage ? endPage :totalPage ;
	}
	
	public String getPagingHTML() {
		return renderer.renderPagination(this);
	}
	
	
	

}
