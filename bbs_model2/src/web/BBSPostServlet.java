package web;
import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import java.sql.*;
import java.util.GregorianCalendar;

public class BBSPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
			// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		String id=(String) session.getAttribute("LOGIN_ID");
		if( id == null)
		{
			throw new ServletException("게시글을 등록하려면 먼저 로그인을 해야합니다.");
			
		}
		
		String title = request.getParameter("TITLE");
		String content = request.getParameter("CONTENT");
		if(title==null||content == null)
		{
			
			throw new ServletException("데이터를 입력하십시오.");
			
		}
		int seqNo = 1;
		GregorianCalendar now = new GregorianCalendar();
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb","root","gpem4162");
//			
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/webdb_pool");
			
			if(conn == null)
			{
				
				throw new ServletException("데이터 베이스 연결 할 수 없습니다. ");
			}
			
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select max(seqno) as max_seqno from bbs");
			
			
			
			if(rs.next())
			{
				seqNo = rs.getInt("max_seqno")+1;
				String command = String.format("insert into bbs "+
						"(seqNo,title,content,writer,wdate,wtime) values"+
						"(%d, '%s','%s','%s','%TF','%TT');",
						seqNo,title,content,id,now,now);
				int rowNum = stmt.executeUpdate(command);
				if(rowNum<1)
				{
					throw new ServletException("데이터를 db에 입력할 수 없습니다.");
				}
						
			}
		}
		catch(ClassNotFoundException cnfe){
			
			throw new ServletException(cnfe);
		}
		catch(SQLException se){
			
			throw new ServletException(se);
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
			catch(Exception ignored)
			{
				
			}
		}
		response.sendRedirect("bbs-list");
	}
	
}
