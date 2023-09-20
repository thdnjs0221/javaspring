package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.adrs.dao.AddressDAO;
import kr.or.ddit.common.exception.PersistenceException;
import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.utils.SampleDataMapperUtils;
import kr.or.ddit.vo.AddressVO;
import kr.or.ddit.vo.MemberVO2;

/**
 * @author PC-14
 *
 */
public class AddressDAOImpl implements AddressDAO {

	@Override
	public int insertAddress(AddressVO adrsVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AddressVO> selectAddressList(String memId) {
		List<AddressVO> adrsList = new ArrayList<AddressVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT  ADRS_NO, MEM_ID, ADRS_NAME, ADRS_HP, ADRS_ADD  ");
		sql.append("FROM ADDRESSBOOK                                          ");
		sql.append("WHERE MEM_ID = ? ");
//		ADRS_NO, MEM_ID, ADRS_NO_NAME , ADRS_HP, ADRS_ADD ADDRESSBOOK

		try (
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString()); // 선컴파일된 쿼리 객체

		) {

			pstmt.setString(1, memId); 

			ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			AddressVO vo =  SampleDataMapperUtils.recordToVO(rs, AddressVO.class);
				adrsList.add(vo);
			
				
			}

			return adrsList;

		} catch (SQLException e) {
			throw new PersistenceException(e);

		}
	}

	@Override
	public int updateAddress(AddressVO adrsVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAddress(int adrsNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
