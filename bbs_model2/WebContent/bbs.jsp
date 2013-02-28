<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="bbsItem" class="web.BBSItem" />
<jsp:setProperty name="bbsItem" property="seqNo"  value="${param.SEQ_NO}"/>

<fmt:requestEncoding value="euc-kr"/>
<% bbsItem.readDB();%>

<HTML>
	
		<H4>게시글 읽기</H4>
		[제목] ${bbsItem.title}<BR>
	
		[작성자] ${bbsItem.writer} %>
	
		-----------------------------------------<BR>
		${bbsItem.getContent} %>
	
</HTML>