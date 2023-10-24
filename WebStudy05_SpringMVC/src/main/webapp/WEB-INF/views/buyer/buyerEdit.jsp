<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<c:url value="/buyer/${buyerId }" var="updateUrl"/>
<form:form modelAttribute="buyer" action="${updateUrl }" method="post" enctype="multipart/form-data">
<h4>hidden method parameter 전송</h4>
<input type="text" name="_method" value="put" />

<table class="table table-bordered">
		<tr>
			<th>사업자등록증사본</th>
			<td>
				<input type="file" name="buyerImgae" class="form-control" accept="image/*"/>
			</td>
		</tr>
		<tr>
			<th><label for="buyerName"><spring:message code="buyer.buyerName" /></label></th>
			<td>
				<form:input type="text" path="buyerName"
					class="form-control"  />
				<form:errors path="buyerName" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="buyerLgu"><spring:message code="buyer.buyerLgu" /></label></th>
			<td>
				<form:select path="buyerLgu" items="${lprodList }" itemLabel="lprodNm" itemValue="lprodGu" 
					class="form-select" required="required"/>
				<form:errors path="buyerLgu" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="buyerBank"><spring:message code="buyer.buyerBank" /></label></th>
			<td>
				<form:input type="text" path="buyerBank"
					class="form-control" />
				<form:errors path="buyerBank" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="buyerBankno"><spring:message code="buyer.buyerBankno" /></label></th>
			<td>
				<form:input type="text" path="buyerBankno"
					class="form-control" />
				<form:errors path="buyerBankno" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="buyerBankname"><spring:message code="buyer.buyerBankname" /></label></th>
			<td>
				<form:input type="text" path="buyerBankname"
					class="form-control" />
				<form:errors path="buyerBankname" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="buyerZip"><spring:message code="buyer.buyerZip" /></label></th>
			<td>
				<form:input type="text" path="buyerZip" class="form-control" />
				<form:errors path="buyerZip" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="buyerAdd1"><spring:message code="buyer.buyerAdd1" /></label></th>
			<td>
				<form:input type="text" path="buyerAdd1"
					class="form-control" />
				<form:errors path="buyerAdd1" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="buyerAdd2"><spring:message code="buyer.buyerAdd2" /></label></th>
			<td>
				<form:input type="text" path="buyerAdd2"
					class="form-control" />
				<form:errors path="buyerAdd2" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="buyerComtel"><spring:message code="buyer.buyerComtel" /></label></th>
			<td>
				<form:input type="text" path="buyerComtel"
					class="form-control"  />
				<form:errors path="buyerComtel" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="buyerFax"><spring:message code="buyer.buyerFax" /></label></th>
			<td>
				<form:input type="text" path="buyerFax" class="form-control"
					 />
				<form:errors path="buyerFax" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="buyerMail"><spring:message code="buyer.buyerMail" /></label></th>
			<td>
				<form:input type="email" path="buyerMail"
					class="form-control"  />
				<form:errors path="buyerMail" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="buyerCharger"><spring:message code="buyer.buyerCharger" /></label></th>
			<td>
				<form:input type="text" path="buyerCharger"
					class="form-control" />
				<form:errors path="buyerCharger" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="buyerTelext"><spring:message code="buyer.buyerTelext" /></label></th>
			<td>
				<form:input type="text" path="buyerTelext"
					class="form-control" />
				<form:errors path="buyerTelext" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="PUT 요청 전송" class="btn btn-primary"/>
				<input type="reset" value="취소" class="btn btn-warning"/>
			</td>
		</tr>
	</table>
</form:form>