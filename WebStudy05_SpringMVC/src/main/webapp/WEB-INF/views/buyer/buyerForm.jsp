<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<c:if test="${not empty message }">
<script type="text/javascript">
	 alert("${message}");
</script>

</c:if>

<!--코어태그 따로 만들어서 하기  -->
<c:url value="/buyer" var="buyerUri" />
<form:form method="post" modelAttribute="buyer" action="${ buyerUri}" enctype="multipart/form-data" >
<table class="table table-border" >
	 
	<tr>
		<th>사업자등록증사본</th>
		<td>
			<input type="file" name="buyerImgae" id="buyerImgae" class="form-control" accept="image/*" />
		</td>
	</tr>
	<tr>
		<th>제조사명</th>
		<td>
			<form:input path="buyerName" value="${buyer.buyerName }" class="form-control" required="true"/>
			<form:errors path="buyerName" element="span" cssClass="error"/>
		</td>
	</tr>	
	<tr>
<!-- 	itemLabel="lprodNm"-> lprodvo 프로퍼티 -->
			<th>상품분류</th>	
			<td>
			<form:select path="buyerLgu" items="${lprodList }" itemLabel="lprodNm" itemValue="lprodGu" />
			<form:errors path="buyerLgu" element="span" cssClass="error"></form:errors>
			</td>
		</tr>
	<tr>
		<th>은행</th>
		<td>
			<form:input path="buyerBank" value="${buyer.buyerBank }" class="form-control" required="true"/>
			<form:errors path="buyerBank" element="span" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>계좌</th>
		<td>
			<form:input path="buyerBankno" value="${buyer.buyerBankno }" class="form-control" required="true"/>
			<form:errors path="buyerBankno" element="span" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>계좌주</th>
		<td>
			<form:input path="buyerBankname" value="${buyer.buyerBankname }" class="form-control" required="true"/>
			<form:errors path="buyerBankname" element="span" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>
			<form:input path="buyerZip" value="${buyer.buyerZip }" class="form-control" required="true"/>
			<form:errors path="buyerZip" element="span" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>주소1</th>
		<td>
			<form:input path="buyerAdd1" value="${buyer.buyerAdd1 }" class="form-control" required="true"/>
			<form:errors path="buyerAdd1" element="span" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>주소2</th>
		<td>
			<form:input path="buyerAdd2" value="${buyer.buyerAdd2 }" class="form-control" required="true"/>
			<form:errors path="buyerAdd2" element="span" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>주소2</th>
		<td>
			<form:input path="buyerComtel" value="${buyer.buyerComtel }" class="form-control" required="true"/>
			<form:errors path="buyerComtel" element="span" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>팩스</th>
		<td>
			<form:input path="buyerFax" value="${buyer.buyerFax }" class="form-control" required="true"/>
			<form:errors path="buyerFax" element="span" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>메일</th>
		<td>
			<form:input path="buyerMail" value="${buyer.buyerMail }" class="form-control" required="true"/>
			<form:errors path="buyerMail" element="span" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>담당자</th>
		<td>
			<form:input path="buyerCharger" value="${buyer.buyerCharger }" class="form-control" required="true"/>
			<form:errors path="buyerCharger" element="span" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>내선번호</th>
		<td>
			<form:input path="buyerTelext" value="${buyer.buyerTelext }" class="form-control" required="true"/>
			<form:errors path="buyerTelext" element="span" cssClass="error"/>
		</td>
	</tr>
	<tr>
			<td colspan="2"><input type="submit" value="전송"
				class="btn btn-primary" /> 
				<input type="reset" value="취소"
				class="btn btn-warning" /></td>
		</tr>

</table>
</form:form>

<script type="text/javascript">
$("select[name=prodLgu]").on("change",function(event){
	let lgu = $(this).val();
	let options = $("select[name=prodBuyer]").find("option");
	$(options).hide();
	$(options).filter((i,e)=>i==0).show();
	if(lgu){
		$(options).filter(`.\${lgu}`).show();
	}else{
		$(options).show();
	}	
});
</script>