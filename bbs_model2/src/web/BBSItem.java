package web;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.*;

public class BBSItem {

		private int seqNo;
		private String content;
		private String title;
		private String writer;
		private Date date;
		private Time time;
		
		public BBSItem()
		{
			
		}
		
		public void setSeqNo(int seqNo)
		{
			this.seqNo = seqNo;
			
		}
		public String getTitle()
		{
			return (title);
		}
		public String getContent()
		{
			return toUnicode(content);
		}
		public String getWriter()
		{
			return toUnicode(writer);
		}
		public Date getDate()
		{
			return date;
		}
		public Time getTime()
		{
			return time;
			
		}
		
		public void readDB() throws ServletException
		{
			Connection conn = null;
			Statement stmt = null;
			 DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
			 DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
			 
			try{
				Class.forName("org.apache.commons.dbcp.PoolingDriver");
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/webdb_pool");
				
			//	Class.forName("com.mysql.jdbc.Driver");
			//	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb","root","gpem4162");
				if(conn==null)
					throw new Exception("데이터베이스에 연결할 수 없습니다.");
				
				stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery("select * from bbs where seqNo = '"+seqNo+"';");
				
				if(rs.next())
				{
					
					title = rs.getString("title");
					content = rs.getString("content");
					writer = rs.getString("writer");
				//	date =  (Date)dateFormat.parse(rs.getString("wdate"));
				//w	time =  (Time)timeFormat.parse(rs.getString("wtime"));
					
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
		private String toUnicode(String str)
		{
			
			if(str==null)
			{
				return null;
			}
			try{
				byte[] b = str.getBytes("UTF-8");
				return new String(b);
			}
			catch(java.io.UnsupportedEncodingException uee){
				System.out.println("err");

				return null;
			}
		}
}