package kr.or.ddit.utils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.lang3.StringUtils;

public class PopulateUtils {
	
	public static void populate(Object bean,Map<String, ? extends Object> properties) {
		
	Converter converter = new AbstractConverter() {
			
			@Override
			protected Class<?> getDefaultType() {
				return Temporal.class;
			}
			
			@Override
			protected <T> T convertToType(Class<T> type, Object paramValue) throws Throwable {
				String text =(String)paramValue;
				if(StringUtils.isNotBlank(text)) {
					//파싱할 부분
//					return LocalDate.parse((String)paramValue);
					return (T) type.getDeclaredMethod("parse", CharSequence.class).invoke(null, text);
				}else {
					//입력받은 날짜 데이터가 없는
					return null;
					
				}
			}
		};
		
		ConvertUtils.register(converter, LocalDate.class);
		ConvertUtils.register(converter, LocalDateTime.class);

		try {
			
			BeanUtils.populate(bean, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e); // unchecked 라 throw없어도 넘겨짐
		}

		

		
		
		
		
		
		
		
		
		
	}
	
	
	

}
