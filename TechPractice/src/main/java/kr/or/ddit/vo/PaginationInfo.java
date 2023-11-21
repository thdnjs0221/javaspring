package kr.or.ddit.vo;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.ui.DefaultPaginationRenderer;
import kr.or.ddit.ui.PaginationRenderer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 페이징과 관련한 모든 데이터를 가진 객체
 *
 */
@Getter
@NoArgsConstructor
@ToString
public class PaginationInfo<T> {
	
	public PaginationInfo(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int totalRecord; // DB 조회
	private int screenSize=10; // 임의 설정
	private int blockSize=5; // 임의 설정
	
	private int currentPage; // 클라이언트 파라미터
	
	private int totalPage;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	private List<T> dataList;
	
	private SearchVO simpleCondition; // 단순 키워드 검색용
	private T detailCondition; // 상세 검색용.
	private Map<String, Object> randomSearch; // 임의 검색용.
	
	@JsonIgnore
	private transient PaginationRenderer renderer = new DefaultPaginationRenderer();
	
	
	public void setDetailCondition(T detailCondition) {
		this.detailCondition = detailCondition;
	}

	public void setSimpleCondition(SearchVO simpleCondition) {
		this.simpleCondition = simpleCondition;
	}
	
	public void setRandomSearch(Map<String, Object> randomSearch) {
		this.randomSearch = randomSearch;
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
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize - 1);
		endPage = ((currentPage + (blockSize-1)) / blockSize) * blockSize;
		startPage = endPage - (blockSize - 1);
	}
	
	public int getEndPage() {
		return endPage > totalPage ? totalPage : endPage;
	}
	
	public void setRenderer(PaginationRenderer renderer) {
		this.renderer = renderer;
	}
	
	public String getPagingHTML() {
		return renderer.renderPagination(this);
	}
}















