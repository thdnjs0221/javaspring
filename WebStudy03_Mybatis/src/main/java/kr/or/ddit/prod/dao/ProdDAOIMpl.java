package kr.or.ddit.prod.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
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
	

	

}
