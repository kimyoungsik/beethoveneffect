package web;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;


public class NewLoginServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		String id = request.getParameter("ID");
		String password = request.getParameter("PASSWORD");
		String currentURL = request.getParameter("CURRENT_URL");
		String result;
		
		if(checkLoginInfo(id,password)){
			HttpSession session = request.getSession();
			session.setAttribute("LOGIN_ID", id);
			
		}
		
		response.sendRedirect(currentURL);
		//response.sendRedirect("WebTemplate.jsp?BODY_PATH=LoginResult.jsp?LOGIN_RESULT="+result);
		
	}
	
	private boolean checkLoginInfo(String id, String password)throws ServletException{
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb","root","gpem4162");
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/webdb_pool");
			
			if(conn==null)
			{
				throw new Exception ("데이터 베이스에 연결할 수 없습니다.");
			}
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select password from userinfo where id='"+id+"';");
			if(!rs.next())
			{
				
				return false;
				
			}
			String correctPassword = rs.getString("password");
			
			if(password.equals(correctPassword))
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
		}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
		
		finally{
			
			try{
				stmt.close();
			}
			catch(Exception ignored){
				
			}
			try{
				conn.close();
			}
			catch(Exception ignored){
				
			}
		}
	}
}
