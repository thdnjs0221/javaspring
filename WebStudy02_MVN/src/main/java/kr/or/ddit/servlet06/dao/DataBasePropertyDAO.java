package kr.or.ddit.servlet06.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

//POJO(Plain Old Java Object)
public class DataBasePropertyDAO {
   public List<DataBasePropertyVO> selectDBPropertyList(){
      List<DataBasePropertyVO> list = new ArrayList<>();
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
   }
}