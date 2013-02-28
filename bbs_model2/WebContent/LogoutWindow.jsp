<%@page contentType="text/html charset=UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>


<FORM ACTION = new-logout METHOD=GET onSubmit="CURRENT_URL.value = window.location.href">
	안녕하세요,${sessionScope.LOGIN_ID}님
	<INPUT TYPE=HIDDEN NAME=CURRENT_URL>
	<INPUT TYPE=SUBMIT VALUE='로그아웃'>
</FORM>