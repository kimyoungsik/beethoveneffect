<%@page contentType="text/html; charset=euc-kr"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<H4>로그인</H4>

<c:choose>
	<c:when test="${param.LOGIN_RESULT=='SUCCESS'}">
		로그인이 되었습니다.<BR>안녕하세요 ${sessionScope.LOGIN_ID}님.
	</c:when>
	<c:otherwise>
		로그인에 실패했습니다. <BR>아이디와 패스워드를 체크하세요.
	</c:otherwise>


</c:choose>