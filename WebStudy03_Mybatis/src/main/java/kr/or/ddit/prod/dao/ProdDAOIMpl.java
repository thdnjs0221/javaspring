package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOIMpl implements ProdDAO {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); // 싱글톤

	@Override
	public ProdVO selectProd(String prodId) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession(true); // try블럭 벗어나면 자동종료
			) {
				
				ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
				return mapperProxy.selectProd(prodId);

			}
	}

	@Override
	public int selectTotalRecord(PaginationInfo<ProdVO> paging) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession(true); // try블럭 벗어나면 자동종료
			) {
				
				ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
				return mapperProxy.selectTotalRecord(paging);

			}
	}

	@Override
	public List<ProdVO> selectProdList(PaginationInfo<ProdVO> paging) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession(true); // try블럭 벗어나면 자동종료
			) {
				
				ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
				return mapperProxy.selectProdList(paging);

			}
	}

	@Override
	public int insertProd(ProdVO vo) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession(true); // try블럭 벗어나면 자동종료
			) {
				
				ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
				int cnt = mapperProxy.insertProd(vo);
				sqlSession.commit();
				return cnt;
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession(true); // try블럭 벗어나면 자동종료
			) {
				
				ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
				int cnt = mapperProxy.updateProd(prod);
				sqlSession.commit();  //or true
				return cnt;
		}
	}
	

	

}
