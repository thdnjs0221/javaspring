<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-bordered">
  <tr>
    <th>상품명</th>
    <td>${prod.prodName }</td>
  </tr>
  <tr>
  	<th>상품분류명</th>
  	<td>${prod.lprod.lprodNm }</td>
  </tr>
  <tr>
  	<th>제조사</th>
  	<td>${prod.buyer.buyerName }</td>
  	
  </tr>
  		<table class="table table-bordered">
  			<thead>
  				<tr>
  					<th>제조사명</th>
  					<td>${prod.buyer.buyerName }</td>
  				</tr>
  				<tr>
  					<th>소재지</th>
  					<td>${prod.buyer.buyerAdd1 }</td>
  				</tr>
  				<tr>
  					<th>담당자명</th>
  					<td>${prod.buyer.buyerCharger }</td>
  				</tr>
  				<tr>
  					<th>거래은행명</th>
  					<td>${prod.buyer.buyerBank }</td>
  				</tr>
  			</thead>
  			<tbody> 
  			
  				
  			</tbody>
  		</table>
  		
  		<h4>구매자 정보</h4>
  		<table class="table table-bordered">
  			<thead>
  				<tr>
  					<th>구매자이름</th>
  					
  					
  					<th>휴대폰</th>
  					
  					<th>이메일</th>
  					
  					<th>거주지</th>
  					
  					<th>마일리지</th>
  					
  				</tr>
  			</thead>
  			<tbody>
  			<c:set var="memberSet" value="${prod.memberSet }"/>
  			<c:forEach items="${memberSet }" var="member"> 
  				<tr>				
					<td>${member.memName}</td>
					<td>${member.memHp}</td>
					<td>${member.memMail}</td>
					<td>${member.memAdd1}</td>
					<td>${member.memAdd2}</td>
					<td>${member.memMileage}</td>
				</tr>
  			
  			</c:forEach>
  			
  			
  			</tbody>
  		</table>
  	</td>
  </tr>
</table>
