package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;

public class OthersDAOImpl implements OthersDAO {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); 
	

	@Override
	public List<LprodVO> selectLprodList() {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession(true); // try블럭 벗어나면 자동종료
			) {
				
			OthersDAO mapperProxy = sqlSession.getMapper(OthersDAO.class);
				return mapperProxy.selectLprodList();

			}
	}

	@Override
	public List<BuyerVO> selectBuyerList(String lprodGu) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession(true); // try블럭 벗어나면 자동종료
			) {
				
			OthersDAO mapperProxy = sqlSession.getMapper(OthersDAO.class);
				return mapperProxy.selectBuyerList(lprodGu);

			}
	}

}
