package web;
import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import java.sql.*;

public class BooksInfoServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		
		String firstCode = request.getParameter("FIRST_CODE");
		String lastCode = request.getParameter("LAST_CODE");
		BooksInfo booksInfo;
		if(firstCode != null){
			
			booksInfo = readPrevPage(firstCode);
			
		}
		else if(lastCode != null)
		{
			
			booksInfo = readNextPage(lastCode);
		}
		else
		{
			
			booksInfo = readNextPage("00000");
			booksInfo.setFirstPage(true);
			
			
		}
		
	
		
		request.setAttribute("BOOK_INFO", booksInfo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WebTemplate.jsp?BODY_PATH=BooksInfoView.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private BooksInfo readPrevPage(String firstCode) throws ServletException
	{
		
		BooksInfo booksInfo = new BooksInfo();
		Connection conn = null;
		Statement stmt = null;
		
		try{
			
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb","root","gpem4162");
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/webdb_pool");
			
			if(conn == null)
			{
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
				
			}
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from goodsinfo where code < '"+firstCode+"' order by code asc;");
			
			for(int cnt = 0; cnt < 5; cnt++)
			{
				
				if(!rs.next())
				{
					break;
				}
				
				booksInfo.setCode(cnt, rs.getInt("code"));
				booksInfo.setTitle(cnt, toUnicode(rs.getString("title")));
				booksInfo.setWriter(cnt, toUnicode(rs.getString("writer")));
				booksInfo.setPrice(cnt, rs.getInt("price"));
			
				
			}
			if(!rs.next())
			{
				booksInfo.setFirstPage(true);
			}
		}
		catch(Exception e){
			
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
		return booksInfo;
	}
	
	
	private BooksInfo readNextPage(String lastCode) throws ServletException
	{
		
		BooksInfo booksInfo = new BooksInfo();
		Connection conn = null;
		Statement stmt = null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb","root","gpem4162");
		
			if(conn == null)
			{
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
				
			}
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from goodsinfo where code > '"+lastCode+"' order by code asc;");
			
			for(int cnt = 0; cnt < 5; cnt++)
			{
				
				if(!rs.next())
				{
					break;
				}
				
				booksInfo.setCode(cnt, rs.getInt("code"));
				booksInfo.setTitle(cnt, toUnicode(rs.getString("title")));
				booksInfo.setWriter(cnt, toUnicode(rs.getString("writer")));
				booksInfo.setPrice(cnt, rs.getInt("price"));
			
				
			}
			if(!rs.next())
			{
				booksInfo.setLastPage(true);
			}
		}
		catch(Exception e){
			
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
		return booksInfo;
	}
	
	private String toUnicode(String str){
		
		
		if(str == null){
			return null;
			
		}
		
		try{
			
			byte[] b = str.getBytes("UTF-8");
			return new String(b);
		}
		catch(java.io.UnsupportedEncodingException uee){
			System.out.println(uee.getMessage());
			return null;
		}
		
	}
	
	
}
