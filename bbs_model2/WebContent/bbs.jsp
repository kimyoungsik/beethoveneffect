<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="bbsItem" class="web.BBSItem" />
<jsp:setProperty name="bbsItem" property="seqNo"  value="${param.SEQ_NO}"/>

<fmt:requestEncoding value="euc-kr"/>
<% bbsItem.readDB();%>

<HTML>
	
		<H4>�Խñ� �б�</H4>
		[����] ${bbsItem.title}<BR>
	
		[�ۼ���] ${bbsItem.writer} %>
	
		-----------------------------------------<BR>
		${bbsItem.getContent} %>
	
</HTML>