package kr.or.ddit.servlet06.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.DataBasePropertyVO;

//POJO(Plain Old Java Object)
public class DataBasePropertyDAO {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); // 싱글톤
	
	//마이바티스 프레임뭐크는 필요한 객체를 대신 만들고, 어플리케이션 내부로 주입하는 역할을 함  
	//Ioc(Inversion Of Controller, DI :Dependency Injection) 패턴 활용
	
   public List<DataBasePropertyVO> selectDBPropertyList(){
	   
	   try(SqlSession sqlSession = sqlSessionFactory.openSession();
			   
			){
		   	return sqlSession.selectList("kr.or.ddit.servlet06.dao.DataBasePropertyDAO.selectDBPropertyList");
	   }
	   
   }
      /*List<DataBasePropertyVO> list = new ArrayList<>();
      try(
         Connection conn = ConnectionFactory.getConnection();
         Statement stmt = conn.createStatement();
      ){
         StringBuffer sql = new StringBuffer();
         sql.append(" select property_name, property_value, description ");
         sql.append(" from database_properties                          ");
         ResultSet rs = stmt.executeQuery(sql.toString());
         
         while(rs.next()){
            DataBasePropertyVO vo = new DataBasePropertyVO();
            list.add(vo);
            vo.setPropertyName(rs.getString("property_name"));
            vo.setPropertyValue(rs.getString("property_value"));
            vo.setDescription(rs.getString("description"));
            
         }
         return list;
      } catch (SQLException e) {
         throw new RuntimeException(e);
         
      }
   }*/
}