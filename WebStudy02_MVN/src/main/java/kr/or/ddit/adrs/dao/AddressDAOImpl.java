package kr.or.ddit.adrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.common.exception.PersistenceException;
import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.utils.SampleDataMapperUtils;
import kr.or.ddit.vo.AddressVO;

public class AddressDAOImpl implements AddressDAO {
   
   private int generateAdrsNo(Connection conn) throws SQLException{
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT NVL(MAX(ADRS_NO),0) + 1 ");
      sql.append(" FROM ADDRESSBOOK            ");
      
      try(
         PreparedStatement pstmt = conn.prepareStatement(sql.toString());
      ){
         ResultSet rs = pstmt.executeQuery();
         rs.next();
         return rs.getInt(1);            
      }      
   }

   @Override
   public int insertAddress(AddressVO adrsVO) {
      StringBuffer sql = new StringBuffer();
      sql.append("	INSERT INTO addressbook (            ");
      sql.append("       adrs_no, mem_id, adrs_name,  ");
      sql.append("       adrs_hp, adrs_add            ");
      sql.append("   ) VALUES (                       ");
      sql.append("       ?,                           ");
      sql.append("       ?,                           ");
      sql.append("       ?,                           ");
      sql.append("       ?,                           ");
      sql.append("       ?                            ");
      sql.append("   )                                ");
      
      try(
         Connection conn = ConnectionFactory.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql.toString());
      ){
         int adrsNo = generateAdrsNo(conn);
         adrsVO.setAdrsNo(adrsNo);
         int idx = 0;
         pstmt.setInt(++idx, adrsVO.getAdrsNo());
         pstmt.setString(++idx, adrsVO.getMemId());
         pstmt.setString(++idx, adrsVO.getAdrsName());
         pstmt.setString(++idx, adrsVO.getAdrsHp());
         pstmt.setString(++idx, adrsVO.getAdrsAdd());
         
         return pstmt.executeUpdate();
         
      }catch (SQLException e) {
         throw new PersistenceException(e);
      }
      
   }

   @Override
   public List<AddressVO> selectAddressList(String memId) {
      List<AddressVO> adrsList = new ArrayList<AddressVO>();
      StringBuffer sql = new StringBuffer();
      
      sql.append("SELECT ADRS_NO, MEM_ID, ADRS_NAME, ADRS_HP, ADRS_ADD  ");
      sql.append("FROM ADDRESSBOOK                                      ");
      sql.append("WHERE MEM_ID = ?                                      ");
      
      try(
         Connection conn = ConnectionFactory.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql.toString());
      ){
         pstmt.setString(1, memId);
         
         ResultSet rs = pstmt.executeQuery();
         while(rs.next()) {
            AddressVO vo = SampleDataMapperUtils.recordToVO(rs, AddressVO.class);
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
	   StringBuffer sql = new StringBuffer();
	   
	      sql.append("	DELETE FROM ADDRESSBOOK       ");
	      sql.append("     WHERE ADRS_NO = ?  ");
	    
	  
	      try(
	         Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	      ){
	       
	         
	         pstmt.setInt(1, adrsNo);
	        
	         return pstmt.executeUpdate();
	         
	      }catch (SQLException e) {
	         throw new PersistenceException(e);
	      }
	      
	   }

}