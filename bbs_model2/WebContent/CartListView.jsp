<%@page contentType="text/html: charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<H4>장바구니 관리</H4>
<c:choose>
	<c:when test="${CART_LIST==null || CART_LIST.size <=0 }">장바구니가 비어있습니다.</c:when>	
	<c:otherwise>
	<TABLE border = 1>
		<TR>
			<TD width = 70>상품코드</TD>
			<TD width = 250>제목 </TD>
			<TD width = 80>단가</TD>
			<TD width = 50>수량</TD>
			<TD width = 100>금액 </TD>
		</TR>
		<c:forEach var="cnt" begin="0" end ="${CART_LIST.size -1}">
		
			<TR>
				<TD>${CART_LIST.code[cnt]}</TD>
				<TD>${CART_LIST.title[cnt]}</TD>
				<TD>${CART_LIST.price[cnt]}</TD>
				<TD>${CART_LIST.number[cnt]}</TD>
				<TD>${CART_LIST.price[cnt] * CART_LIST.number[cnt]}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<FORM ACTION=/bbs_model2/pay METHOD=POST>
		총계: ${CART_LIST.totalAmount}원
		<INPUT TYPE =HIDDEN NAME=TOTAL_AMOUNT VALUE=${CART_LIST.totalAmount }>
		<INPUT TYPE=SUBMIT VALUE='카드결제'>
			
	</FORM>
	</c:otherwise>
</c:choose>
