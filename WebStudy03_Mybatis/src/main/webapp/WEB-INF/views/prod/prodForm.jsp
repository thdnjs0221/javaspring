<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		<table class="table table-border">

				<tr>
					<th>상품명</th>
					<td><input type="text" name="prodName"></td>
				</tr>
				<tr>

					<th>상품분류</th>
					<td><select name="prodLgu">
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
					<td><select name="prodBuyer">
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
					<td><input type="text" name="prodCost"></td>
				</tr>
				<tr>
					<th>판매가</th>
					<td><input type="text" name="prodPrice"></td>
				</tr>
				<tr>
					<th>세일가</th>
					<td><input type="text" name="prodSale"></td>
				</tr>
				<tr>
					<th>요약정보</th>
					<td><input type="text" name="prodOutline"></td>
				</tr>
				<tr>
					<th>상세정보</th>
					<td><input type="text" name="prodDetail"></td>
				</tr>
				<tr>
					<th><label for="prodImage">이미지</label></th>
					<td>
						<input type="file" name="prodImage" id="prodImage" class="form-controller" required/>
						<span class="error">${errors.prodImg} </span>
					</td>
				</tr>
				<tr>
					<th>총재고</th>
					<td><input type="text" name="prodTotalstock"></td>
				</tr>
				<tr>
					<th>입고일</th>
					<td><input type="date" name="prodInsdate"></td>
				</tr>
				<tr>
					<th>적정재고</th>
					<td><input type="text" name="prodProperstock"></td>
				</tr>
				<tr>
					<th>크기</th>
					<td><input type="text" name="prodSize"></td>
				</tr>
				<tr>
					<th>색상</th>
					<td><input type="text" name="prodColor"></td>
				</tr>
				<tr>
					<th>배송방법</th>
					<td><input type="text" name="prodDelivery"></td>
				</tr>
				<tr>
					<th>단위</th>
					<td><input type="text" name="prodUnit"></td>
				</tr>
				<tr>
					<th>입고량</th>
					<td><input type="text" name="prodQtyin"></td>
				</tr>
				<tr>
					<th>판매량</th>
					<td><input type="text" name="prodQtysale"></td>
				</tr>
				<tr>
					<th>마일리지</th>
					<td><input type="text" name="prodMileage"></td>
				</tr>
				<tr>
					<th>색깔</th>
					<td><input type="text" name="prodName"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="전송"
						class="btn btn-primary" /> <input type="reset" value="취소"
						class="btn btn-warning" /></td>
				</tr>


			



		</table>
	</form>
</body>
</html>