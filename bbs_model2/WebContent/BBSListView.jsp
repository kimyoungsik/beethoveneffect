<%@page contentType="text/html; charset=euc-kr"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
	
		<H4>게시판 목록 </H4>
		<TABLE border=1>
			<TR>
				<TD width=40>순번 </TD>
				<TD width=300>제목 </TD>
				<TD width=80>작성자</TD>
				<TD width=90>작성일자</TD>
				<TD width=70>작성시각</TD>
				
			</TR>
			<c:forEach var="cnt" begin="0" end ="${BBS_LIST.listSize-1}">
			
				<TR>
					<TD>${BBS_LIST.seqNo[cnt] }</TD>
					<TD><A href='WebTemplate.jsp?BODY_PATH=bbs.jsp?SEQ_NO=${BBS_LIST.seqNo[cnt]}'>${BBS_LIST.title[cnt]}</A></TD>
					<TD>${BBS_LIST.writer[cnt] }</TD>
				</TR>
			
			</c:forEach>
		
		</TABLE>
		<c:if test="${!BBS_LIST.lastPage}">
			<A href='bbs-list?LAST_SEQ_NO=${BBS_LIST.seqNo[BBS_LIST.listSize-1] }'>다음 페이지</A>
		</c:if>
	
</HTML>
