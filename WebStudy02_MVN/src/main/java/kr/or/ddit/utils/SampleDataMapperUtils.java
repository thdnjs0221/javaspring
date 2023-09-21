package kr.or.ddit.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import kr.or.ddit.vo.AddressVO;

public class SampleDataMapperUtils {

	public static String snakeToCamel(String columnName) {

		String propName = WordUtils.capitalizeFully(columnName, '_');
		
		propName = StringUtils.replace(propName, "_", "");
		
		propName = WordUtils.uncapitalize(propName);
		
		return propName;
	}

	public static <T>T recordToVO(ResultSet rs, Class<T> resultType) throws SQLException {
		try {
		
		//		AddressVO vo new aAddressVO();
		Object vo = resultType.newInstance();
		ResultSetMetaData rsmd = rs.getMetaData();
		int colCnt = rsmd.getColumnCount();
		for(int idx =1; idx <= colCnt; idx++) {
			String columnName = rsmd.getColumnName(idx);
			String propName = SampleDataMapperUtils.snakeToCamel(columnName);
			PropertyDescriptor pd = new PropertyDescriptor(propName, resultType);
			Method setter = pd.getWriteMethod();
			Field property = resultType.getDeclaredField(propName);
			if(property.getType().equals(int.class)) {
				setter.invoke(vo, rs.getInt(columnName));
//				vo.setAdrsNo(rs.getInt("ADRS_NO"));
			}else {
				setter.invoke(vo, rs.getString(columnName));
//			    vo.setMemId(rs.getString("MEM_ID"));
//			    vo.setAdrsName(rs.getString("ADRS_NAME"));
//			    vo.setAdrsHp(rs.getString("ADRS_HP"));
//			    vo.setAdrsAdd(rs.getString("ADRS_ADD")
			}
			
		}
		return (T)vo;
		}catch (Exception e) {
			throw new SQLException(e);
		}

	}

}
