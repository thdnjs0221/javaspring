package kr.or.ddit.adrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;

import kr.or.ddit.vo.AddressVO;

public class AddressDAOImpl implements AddressDAO {
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();  //싱글톤
	
   
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
	   
	   
	   try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);  //try블럭 벗어나면 자동종료
				){
		   		AddressDAO mapperProxy = sqlSession.getMapper(AddressDAO.class);
		   		return mapperProxy.insertAddress(adrsVO);
		   	
						
				
			}
		}


   @Override
   public List<AddressVO> selectAddressList(String memId) {
     
	   try(
				SqlSession sqlSession = sqlSessionFactory.openSession();  //try블럭 벗어나면 자동종료
				){
				//return sqlSession.selectList("kr.or.ddit.adrs.dao.AddressDAO.selectAddressList", memId);
				AddressDAO mapperProxy = sqlSession.getMapper(AddressDAO.class);
				return mapperProxy.selectAddressList(memId);
				
	   }
		}

   @Override
   public int updateAddress(AddressVO adrsVO) {
     try(
    		 SqlSession sqlSession = sqlSessionFactory.openSession(true);  //true 업데이트 즉시 커밋 
    		 ){
    	 AddressDAO mapperProxy = sqlSession.getMapper(AddressDAO.class);
    	 return mapperProxy.updateAddress(adrsVO);
    	 
     }
   }

   @Override
   public int deleteAddress(int adrsNo) {
	   try(
	    	  SqlSession sqlSession = sqlSessionFactory.openSession();  
	    		 ){
	    	 AddressDAO mapperProxy = sqlSession.getMapper(AddressDAO.class);
	    	 int rowcnt = mapperProxy.deleteAddress(adrsNo);
	    	 sqlSession.commit();
	    	 return rowcnt;
	    	 
	     }
		}
}

