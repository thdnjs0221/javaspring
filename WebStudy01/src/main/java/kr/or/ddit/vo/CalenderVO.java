package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.Locale;
import java.util.Objects;

/**
 * 
 * VO(Value Object), DTO(Data Transfer Object), Model, Bean(java bean규약에 따른 객체)
 * ==: reference 비교, equals: 상태 비교
 */
public class CalenderVO implements Serializable{
	private Locale locale;
	private YearMonth tagertMonth;
	private YearMonth beforeMonth;
	private YearMonth nextMonth;
	
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public YearMonth getTagertMonth() {
		return tagertMonth;
	}
	public void setTagertMonth(YearMonth tagertMonth) {
		this.tagertMonth = tagertMonth;
	}
	public YearMonth getBeforeMonth() {
		return beforeMonth;
	}
	public void setBeforeMonth(YearMonth beforeMonth) {
		this.beforeMonth = beforeMonth;
	}
	public YearMonth getNextMonth() {
		return nextMonth;
	}
	public void setNextMonth(YearMonth nextMonth) {
		this.nextMonth = nextMonth;
	}
	@Override
	public String toString() {
		return "CalenderVO [locale=" + locale + ", tagertMonth=" + tagertMonth + ", beforeMonth=" + beforeMonth
				+ ", nextMonth=" + nextMonth + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(beforeMonth, locale, nextMonth, tagertMonth);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalenderVO other = (CalenderVO) obj;
		return Objects.equals(beforeMonth, other.beforeMonth) && Objects.equals(locale, other.locale)
				&& Objects.equals(nextMonth, other.nextMonth) && Objects.equals(tagertMonth, other.tagertMonth);
	}
	
	
}
