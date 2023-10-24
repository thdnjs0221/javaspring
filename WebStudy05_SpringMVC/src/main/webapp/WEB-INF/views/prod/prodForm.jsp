<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<!--
실패해서 다시 돌아올때 modelAttribute-> prod로 받을수 있다
prodName은 modelAttribute="prod"에서 받아온,,

  -->

<form:form method="post" enctype="multipart/form-data"  modelAttribute="prod">
	<table class="table table-border">

		<tr>
			<th>상품명</th>
			<td>
			<form:input path="prodName" value="${prod.prodName}" class="form-control" required="true" />
			<form:errors path="prodName" element="span" cssClass="error"></form:errors>
			</td>
		</tr>
		<tr>
			<th>상품분류</th>	
			<td>
			<form:select path="prodLgu" items="${lprodList }" itemLabel="lprodNm" itemValue="lprodGu" />
			<form:errors path="prodLgu" element="span" cssClass="error"></form:errors>
			</td>
		</tr>
		<tr>
			<th>제조사</th>
			<td>
			<form:select path="prodBuyer">
				<option value>제조사</option>
				<c:forEach items="${buyerList }" var="buyer">
				<form:option class="${buyer.buyerLgu }"  label="${buyer.buyerName }"  value="${buyer.buyerId  }" />
				</c:forEach>
			</form:select>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><form:input path="prodCost" value="${prod.prodCost }" class="form-control" required="true" /></td>
			
		</tr>
		<tr>
			<th>판매가</th>
			<td>
			<form:input path="prodPrice" value="${prod.prodPrice }" class="form-control" required="true" />	
			</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><form:input path="prodSale" value="${prod.prodSale }" class="form-control" required="true" /></td>
		
		</tr>
		<tr>
			<th>요약정보</th>
			<td>
				<form:input path="prodOutline" value="${prod.prodOutline }" class="form-control" required="true" />
			</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>
				<form:input path="prodDetail" value="${prod.prodDetail}" class="form-control" required="true" />
			</td>
		</tr>
		<tr>
			<th><label for="prodImage">이미지</label></th>
			<td>
			<input type="file" name="prodImage" id="prodImage" class="form-controller" required />  
			<span class="error">${errors.prodImg}</span>
			</td>
		</tr>
		<tr>
			<th>총재고</th>
			<td>
				<form:input path="prodTotalstock" value="${prod.prodTotalstock}" class="form-control" required="true" />
			</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>
			<form:input type="date" path="prodInsdate" value="${prod.prodInsdate}" class="form-control" required="true" />
			</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>
			<form:input path="prodProperstock" value="${prod.prodProperstock}" class="form-control" required="true" />
			</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>
			<form:input path="prodSize" value="${prod.prodSize}" class="form-control" required="true" />
			</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>
				<form:input path="prodColor" value="${prod.prodColor}" class="form-control" required="true" />
			</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>
				<form:input path="prodDelivery" value="${prod.prodDelivery}" class="form-control" required="true" />
			</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>
				<form:input path="prodUnit" value="${prod.prodUnit}" class="form-control" required="true" />
			</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>
				<form:input path="prodQtyin" value="${prod.prodQtyin}" class="form-control" required="true" />
			</td>
		</tr>
		<tr>
			<th>판매량</th>
			<td>
				<form:input path="prodQtysale" value="${prod.prodQtysale}" class="form-control" required="true" />	
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>
				<form:input path="prodMileage" value="${prod.prodMileage}" class="form-control" required="true" />	
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="전송"
				class="btn btn-primary" /> <input type="reset" value="취소"
				class="btn btn-warning" /></td>
		</tr>
	</table>
</form:form>
<script type="text/javascript">

let $prodBuyer = $("select[name=prodBuyer");
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
}).change();
</script>
