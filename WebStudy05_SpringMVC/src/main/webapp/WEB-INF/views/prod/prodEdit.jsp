<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4>수정 양식</h4>
<form method="post" enctype="multipart/form-data">
<input type="hidden" name="prodId" value="${prod.prodId }">

	<table class="table table-border">

				<tr>
					<th>상품명</th>
					<td><input type="text" name="prodName" value="${prod.prodName }"></td>
				</tr>
				<tr>

					<th>상품분류</th>
					<td><select name="prodLgu" value="${prod.prodLgu }">
							<option value>상품분류</option>
							<c:if test="${not empty lprodList}">
								<c:forEach items="${lprodList }" var="lprod">
									<option label="${lprod.lprodNm }" value="${lprod.lprodGu  }" />
								</c:forEach>
							</c:if>
					</select></td>


				</tr>
				<tr>
					<th>제조사</th>
					<td><select name="prodBuyer" value="${prod.prodBuyer }">
							<option value>제조사</option>
							<c:if test="${not empty buyerList}">
								<c:forEach items="${buyerList }" var="buyer">
									<option class="${buyer.buyerLgu }" label="${buyer.buyerName }"
										value="${buyer.buyerId  }" />
								</c:forEach>
							</c:if>
					</select></td>
				</tr>
				<tr>
					<th>구매가</th>
					<td><input type="text" name="prodCost" value="${prod.prodCost }"></td>
				</tr>
				<tr>
					<th>판매가</th>
					<td><input type="text" name="prodPrice" value="${prod.prodPrice }"></td>
				</tr>
				<tr>
					<th>세일가</th>
					<td><input type="text" name="prodSale" value="${prod.prodSale }"></td>
				</tr>
				<tr>
					<th>요약정보</th>
					<td><input type="text" name="prodOutline" value="${prod.prodOutline }"></td>
				</tr>
				<tr>
					<th>상세정보</th>
					<td><input type="text" name="prodDetail" value="${prod.prodDetail}" ></td>
				</tr>
				<tr>
					<th><label for="prodImage">이미지</label></th>
					<td><input type="file" name="prodImage" id="prodImage"
						class="form-controller" required /> <span class="error">${errors.prodImg}
					</span></td>
				</tr>
				<tr>
					<th>총재고</th>
					<td><input type="text" name="prodTotalstock" value="${prod.prodTotalstock }"></td>
				</tr>
				<tr>
					<th>입고일</th>
					<td><input type="date" name="prodInsdate" value="${prod.prodInsdate }"></td>
				</tr>
				<tr>
					<th>적정재고</th>
					<td><input type="text" name="prodProperstock" value="${prod.prodProperstock }"></td>
				</tr>
				<tr>
					<th>크기</th>
					<td><input type="text" name="prodSize" value="${prod.prodSize }"></td>
				</tr>
				<tr>
					<th>색상</th>
					<td><input type="text" name="prodColor" value="${prod.prodColor }"></td>
				</tr>
				<tr>
					<th>배송방법</th>
					<td><input type="text" name="prodDelivery" value="${prod.prodDelivery }"></td>
				</tr>
				<tr>
					<th>단위</th>
					<td><input type="text" name="prodUnit" value="${prod.prodUnit }"></td>
				</tr>
				<tr>
					<th>입고량</th>
					<td><input type="text" name="prodQtyin" value="${prod.prodQtyin }"></td>
				</tr>
				<tr>
					<th>판매량</th>
					<td><input type="text" name="prodQtysale" value="${prod.prodQtysale }"></td>
				</tr>
				<tr>
					<th>마일리지</th>
					<td><input type="text" name="prodMileage" value="${prod.prodMileage }"></td>
				</tr>
				<tr>
					<th>색깔</th>
					<td><input type="text" name="prodName" value="${prod.prodName }"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="전송"
						class="btn btn-primary" /> <input type="reset" value="취소"
						class="btn btn-warning" /></td>
				</tr>
	</table>
</form>
<script type="text/javascript">

let $prodBuyer = $("select[name=prodBuyer").val("${prod.prodBuyer}")
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
}).val("${prod.prodLgu}").change();
</script>
