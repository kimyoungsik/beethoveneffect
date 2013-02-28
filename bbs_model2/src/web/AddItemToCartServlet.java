package web;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class AddItemToCartServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,
					HttpServletResponse response)throws IOException, ServletException{
		
		String code = request.getParameter("CODE");
		if( code == null )
		{
			throw new ServletException("상품코드가 입력되지 않았ㅎ습니다.");
			
		}
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("CART");
		
		if(cart == null)
		{
			
			cart = new Cart();
			
		}
		cart.addItem(code, 1);
		
		session.setAttribute("CART", cart);
		response.sendRedirect("AddItemToCartResult.jsp?ITEM_NUM="+1);
		
	}
	
	
}