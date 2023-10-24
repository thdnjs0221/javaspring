<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 	
<table class="table table-bordered">
	 <tr>
      <th>이미지</th>
      <td>
      	<c:if test="${not empty buyer.buyerImg }">	
      		<a href='<c:url value="/buyer/${buyer.buyerId }/buyerImage" />'>사업자등록증사본 다운로드</a>
         
        </c:if>
      </td>
   </tr>
	<tr>
		<th>제조사아이디</th>
		<td>${buyer.buyerId }</td>
	</tr>
	<tr>
		<th>제조사명</th>
		<td>${buyer.buyerName }</td>
	</tr>
	<tr>
		<th>분류</th>
		<td>${buyer.lprod.lprodNm }</td>
	</tr>
	<tr>
		<th>은행</th>
		<td>${buyer.buyerBank }</td>
	</tr>
	<tr>
		<th>계좌</th>
		<td>${buyer.buyerBankno }</td>
	</tr>
	<tr>
		<th>계좌주</th>
		<td>${buyer.buyerBankname }</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>${buyer.buyerZip }</td>
	</tr>
	<tr>
		<th>주소1</th>
		<td>${buyer.buyerAdd1 }</td>
	</tr>
	<tr>
		<th>주소2</th>
		<td>${buyer.buyerAdd2 }</td>
	</tr>
	<tr>
		<th>전번</th>
		<td>${buyer.buyerComtel }</td>
	</tr>
	<tr>
		<th>팩스</th>
		<td>${buyer.buyerFax }</td>
	</tr>
	<tr>
		<th>메일</th>
		<td>${buyer.buyerMail }</td>
	</tr>
	<tr>
		<th>담당자</th>
		<td>${buyer.buyerCharger }</td>
	</tr>
	<tr>
		<th>내선번호</th>
		<td>${buyer.buyerTelext }</td>
	</tr>
	<tr>
		
		<td>
		<a href='<c:url value="/buyer/${buyer.buyerId }/form" />'>수정</a>
		</td>
	</tr>
</table>
<h4>생산품목</h4>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>상품명</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty buyer.prodList }">
			<tr>
				<td colspan="4">제조 품목 없음.</td>
			</tr>
		</c:if>
		<c:if test="${not empty buyer.prodList }">
			<c:forEach items="${buyer.prodList }" var="prod">
				<tr>
					<td>
						<c:url value="/prod/prodView.do" var="prodViewUrl">
							<c:param name="what" value="${prod.prodId }" />
						</c:url>
						<a href="${prodViewUrl }">${prod.prodName }</a>
					</td>
					<td>${prod.prodCost }</td>
					<td>${prod.prodPrice }</td>
					<td>${prod.prodMileage }</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
