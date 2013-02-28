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
			throw new ServletException("�Խñ��� ����Ϸ��� ���� �α����� �ؾ��մϴ�.");
			
		}
		
		String title = request.getParameter("TITLE");
		String content = request.getParameter("CONTENT");
		if(title==null||content == null)
		{
			
			throw new ServletException("�����͸� �Է��Ͻʽÿ�.");
			
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
				
				throw new ServletException("������ ���̽� ���� �� �� �����ϴ�. ");
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
					throw new ServletException("�����͸� db�� �Է��� �� �����ϴ�.");
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
