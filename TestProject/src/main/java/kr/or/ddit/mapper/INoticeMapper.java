package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeMapper {
	
	//조회수 
	public void incremenHit(int noNo);
	
	public int insertNotice (NoticeVO noticeVO);
	
	public int updateNotice (NoticeVO noticeVO);
	
	public int deleteNotice(int noNo);
	
	public List<NoticeVO>selectNoticeList_ ();
	
	public NoticeVO selectNotice(int noNo);

	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);

	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);
	
	

}
