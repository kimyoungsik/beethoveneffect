package web;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class BBSListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String strUpperSeqNo = request.getParameter("LAST_SEQ_NO");
		int upperSeqNo;
		
		if(strUpperSeqNo == null)
		{
			
			upperSeqNo = Integer.MAX_VALUE;
		}
		else
		{
			upperSeqNo = Integer.parseInt(strUpperSeqNo);
			
			
		}
		
		BBSList list = readDB(upperSeqNo);
		request.setAttribute("BBS_LIST", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WebTemplate.jsp?BODY_PATH=BBSListView.jsp"); 
		dispatcher.forward(request,response);
		
	}
	
	private BBSList readDB(int upperSeqNo) throws ServletException{
		
		BBSList list = new BBSList();
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
				
				throw new Exception("������ ���̽� ���� �� �� �����ϴ�. ");
			}
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from bbs where seqNo<"+upperSeqNo+" order by seqno desc;");
			
			for(int cnt = 0; cnt< 5; cnt++){
				
				if(!rs.next())
				{
					break;
				}
				list.setSeqNo(cnt,rs.getInt("seqNo"));
				list.setTitle(cnt, toUnicode(rs.getString("title")));
				list.setWriter(cnt, toUnicode(rs.getString("writer")));
			//	list.setDate(cnt, toUnicode(rs.getString("wdate")));
			//	list.setTime(cnt, toUnicode(rs.getString("wtime")));
				
			}
			if(!rs.next())
			{
				list.setLastPage(true);
				
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
			catch(Exception ignored)
			{
				
			}
		}
		return list;
	}
	
	private String toUnicode(String str){
		
		if(str == null)
		{
			return null;
		}
		try {
			
			byte[] b = str.getBytes("UTF-8");
			return new String(b);
		}
		catch(java.io.UnsupportedEncodingException uee){
			System.out.println(uee.getMessage());
			return null;
		}
	}

}
