package kr.or.ddit.service.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.mapper.INoticeMapper;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;



@Service
public class NoticeServiceImpl implements INoticeService {

	@Inject
	private INoticeMapper mapper;

	@Override
	public NoticeVO selectNotice(int noNo) {
		//mapper.incremenHit(noNo);  //조회수
		
		return mapper.selectNotice(noNo);
	}

	@Override
	public List<NoticeVO> selectNoticeList() {
		
		return mapper.selectNoticeList_();
	}

	@Override
	public ServiceResult insertNotice(NoticeVO noticeVO) {
		ServiceResult result = null;

		int status = mapper.insertNotice(noticeVO);
		if (status > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult updateNotice(NoticeVO noticeVO) {
		ServiceResult result = null;
		int status = mapper.updateNotice(noticeVO);

		if (status > 0) {
			result = ServiceResult.OK;

		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteNotice(int noNo) {
		ServiceResult result = null;
		int status = mapper.deleteNotice(noNo);

		if (status > 0) {
			result = ServiceResult.OK;

		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {
		
		return mapper.selectNoticeCount(pagingVO);
	}

	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		
		return mapper.selectNoticeList(pagingVO);
	}

}
