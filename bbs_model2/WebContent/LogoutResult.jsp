<%@page contentType="text/html; charset=euc-kr"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<H4>로그인</H4>

<c:choose>
	<c:when test="${param.LOGOUT_RESULT=='SUCCESS'}">
		로그아웃이 되었습니다.<BR>
	</c:when>
	<c:otherwise>
		로그아웃에 실패했습니다. <BR>
	</c:otherwise>


</c:choose>