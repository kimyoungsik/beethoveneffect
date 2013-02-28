<%@page contentType="text/html: charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<HTML>
	
	
	
	<H4>책정보</H4>
	<TABLE border = 1>
		<TR>
			<TD width = 40>상품코드</TD>
			<TD width = 300>제목 </TD>
			<TD width = 80>저자</TD>
			<TD width = 70>가격 </TD>
		</TR>
		<c:forEach var="cnt" begin="0" end ="${BOOK_INFO.size -1}">
		
			<TR>
				<TD>${BOOK_INFO.code[cnt]}</TD>
				<TD>${BOOK_INFO.title[cnt]}</TD>
				<TD>${BOOK_INFO.writer[cnt]}</TD>
				<TD>${BOOK_INFO.price[cnt]}</TD>
				<TD><A href='#' onClick='window.open("add-item-to-cart?CODE=${BOOK_INFO.code[cnt]}","cart_result","width=400,height=150").focus()'>장바구니 담기 </A></TD>
			</TR>
		</c:forEach>
	</TABLE>
	
	
	<c:if test="${!BOOK_INFO.firstPage}">
	
		<A href='books-info?FIRST_CODE=${BOOK_INFO.code[0] }'>이전페이지</A>
	</c:if>
	
	<c:if test="${!BOOK_INFO.lastPage}">
	
		<A href='books-info?LAST_CODE=${BOOK_INFO.code[BOOK_INFO.size-1] }'>다음페이지</A>
	</c:if>
	

</HTML>