package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import kr.or.ddit.common.exception.PersistenceException;
import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public MemberVO selectMemberForAuth(MemberVO inputData) {
		StringBuffer sql = new StringBuffer();
		 sql.append("SELECT MEM_ID, MEM_PASS, MEM_NAME, MEM_HP, MEM_MAIL  ");
        sql.append("FROM MEMBER                                          ");
        sql.append("WHERE MEM_ID = ? AND MEM_PASS=? ");

		try (
				Connection conn = ConnectionFactory.getConnection(); 
				//Statement stmt = conn.createStatement(); // 4. 쿼리객체 생성
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());  //선컴파일된 쿼리 객체
				
		) {
			
			
	        System.out.println(sql);
	         
	        pstmt.setString(1, inputData.getMemId()); //? 1 
	        pstmt.setString(2, inputData.getMemPass()); 
	         
	         
	        ResultSet rs = pstmt.executeQuery(); 
	        
			MemberVO saved = null;
			if (rs.next()) {
				saved = new MemberVO();
				saved.setMemId(rs.getString("MEM_ID"));
				saved.setMemPass(rs.getString("MEM_PASS"));
				saved.setMemName(rs.getString("MEM_NAME"));
				saved.setMemHp(rs.getString("MEM_HP"));
				saved.setMemMail(rs.getString("MEM_MAIL"));
			}

			return saved;

		} catch (SQLException e) {
			throw new PersistenceException(e);

		}
	}

}
