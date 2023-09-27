package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.adrs.dao.AddressDAO;
import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); // 싱글톤

	@Override
	public MemberVO selectMemberForAuth(MemberVO inputData) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(); // try블럭 벗어나면 자동종료
		) {
			return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMemberForAuth", inputData);
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession(true); // try블럭 벗어나면 자동종료
			) {
				
				MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
				return mapperProxy.insertMember(member);
		}
	}
	@Override
	public MemberVO selectMember(String memId) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(); // try블럭 벗어나면 자동종료
				) {
					return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMember", memId);
				}
			}
	// @@
	@Override
	public List<MemberVO> selectMemberList() {

		try (
			SqlSession sqlSession = sqlSessionFactory.openSession(); // try블럭 벗어나면 자동종료
		) {
			
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			return mapperProxy.selectMemberList();

		}
	}

	//수정
	@Override
	public int updateMember(MemberVO member) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession(true); // try블럭 벗어나면 자동종료
			) {
				
				MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
				return mapperProxy.updateMember(member);

			}
	}

	@Override
	public int deleteMember(String memId) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession(true); // try블럭 벗어나면 자동종료
			) {
				
				MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
				return mapperProxy.deleteMember(memId);

			}
	}

}
