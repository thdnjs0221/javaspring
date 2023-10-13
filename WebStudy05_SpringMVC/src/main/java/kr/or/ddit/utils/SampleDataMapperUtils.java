package kr.or.ddit.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public static <T> T recordToVO(ResultSet rs, Class<T> resultType) throws SQLException {
		try {

			// AddressVO vo new aAddressVO();
			Object vo = resultType.newInstance();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCnt = rsmd.getColumnCount();
			for (int idx = 1; idx <= colCnt; idx++) {
				String columnName = rsmd.getColumnName(idx);
				String propName = SampleDataMapperUtils.snakeToCamel(columnName);
				PropertyDescriptor pd = new PropertyDescriptor(propName, resultType);
				Method setter = pd.getWriteMethod();
				Field property = resultType.getDeclaredField(propName);
				if (property.getType().equals(int.class)) {
					setter.invoke(vo, rs.getInt(columnName));
//				vo.setAdrsNo(rs.getInt("ADRS_NO"));
				} else {
					setter.invoke(vo, rs.getString(columnName));
//			    vo.setMemId(rs.getString("MEM_ID"));
//			    vo.setAdrsName(rs.getString("ADRS_NAME"));
//			    vo.setAdrsHp(rs.getString("ADRS_HP"));
//			    vo.setAdrsAdd(rs.getString("ADRS_ADD")
				}

			}
			return (T) vo;
		} catch (Exception e) {
			throw new SQLException(e);
		}

	}
//	 StringBuffer sql = new StringBuffer();
//     sql.append("	INSERT INTO addressbook (            ");
//     sql.append("       ADRS_NO, MEM_ID, ADRS_NAME,  ");
//     sql.append("       ADRS_HP, ADRS_ADD            ");
//     sql.append("   ) VALUES (                       ");
//     sql.append("       #{adrsNo},                           ");
//     sql.append("        #{memId},                           ");
//     sql.append("        #{adrsName},                           ");
//     sql.append("        #{adrsHp},                           ");
//     sql.append("        #{adrsAdd}                            ");
//     sql.append("   )                                ");

	public static PreparedStatement generatePreparedStatement(Connection conn, String beforeSql, Object paramVO)
			throws SQLException {
		Pattern parttern = Pattern.compile("#\\{(\\w+)\\}"); // #{adrsNo}를 ->? / {}반복이라 정규 이스케이프 \\두번이면 문자열, 영문자나 소문자
																// 반복되는것만 묶음
		Matcher matcher = parttern.matcher(beforeSql);
		List<String> propNames = new ArrayList<>();
		StringBuffer afterSql = new StringBuffer();
		while (matcher.find()) { // 정규식을 찾았다 못찾았다
			propNames.add(matcher.group(1)); // (\\w+)그룹은 인덱스 1부터 시작
			matcher.appendReplacement(afterSql, "?");

		}
		matcher.appendTail(afterSql);
		PreparedStatement pstmt = conn.prepareStatement(afterSql.toString());

		Class<?> paramType = paramVO.getClass(); // 어떤 클래스 인지 확인

		try {
			for (int i = 0; i < propNames.size(); i++) {
				String proName = propNames.get(i);
				PropertyDescriptor pd = new PropertyDescriptor(proName, paramType);
				Method getter = pd.getReadMethod(); 
//				adrsVO.getAdrsNo() 를 리플렉션으로 만드는 작업
				Object propValue = getter.invoke(paramVO);
				if(pd.getPropertyType().equals(int.class)) {  //타입에따라 int String인지
					pstmt.setInt(i+1, (Integer)propValue); 
				}else {
					pstmt.setString(i + 1, (String)propValue);
				}
	
			}
			return pstmt;
		} catch (Exception e) {
				throw new SQLException();
		}
	}

}
