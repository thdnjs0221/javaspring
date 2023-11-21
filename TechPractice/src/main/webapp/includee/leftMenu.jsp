<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
  <nav id="sidebarMenu" class="col-md-2 col-lg-2 d-md-block bg-light sidebar collapse">
     <div class="sidebar-sticky pt-3">
       <ul class="nav flex-column">
       	<c:forEach items="${menuList }" var="menu">
			<li class="nav-item">
				<a class="nav-link" href="<c:url value='${menu.menuURL }'/>">${menu.menuText }</a>
			</li>
		</c:forEach>
       </ul>
    <hr />
    <h6>중단시키려면 클릭</h6>	
	<a class="btn btn-primary disabled" data-role="playBtn" onclick="return false;" href="<c:url value='/scheduling/resume'/>">▶</a>
	<a class="btn btn-danger" data-role="playBtn"  onclick="return false;" href="<c:url value='/scheduling/pause'/>">||</a>
     </div>
  </nav>
