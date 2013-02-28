package web;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.*;

public class BooksInfo {

	private ArrayList<Integer> codeList = new ArrayList<Integer>();
	
	private ArrayList<String> titleList = new ArrayList<String>();
	
	private ArrayList<String> writerList = new ArrayList<String>();
	
	private ArrayList<Integer> priceList = new ArrayList<Integer>();
	
	private boolean lastPage = false;
	private boolean firstPage = false;
	public BooksInfo(){
		
		
	}
	
	
	public void setCode(int index, Integer code){
		
		this.codeList.add(index,code);
	}
	
	
	public void setTitle(int index, String title){
		
		this.titleList.add(index,title);
	}
	

	public void setWriter(int index, String writer){
	
		this.writerList.add(index,writer);
	}
	public void setPrice(int index, Integer price){
		
		this.priceList.add(index,price);
	}
	public void setFirstPage(boolean firstPage){
		this.firstPage = firstPage;
	}
	
	public void setLastPage(boolean lastPage){
		this.lastPage = lastPage;
	}
	
	public Integer[] getCode(){
		
		return codeList.toArray(new Integer[codeList.size()]);	
	}
	
	public String[] getTitle(){
		
		return titleList.toArray(new String[titleList.size()]);	
	}

	public String[] getWriter(){
	
	return writerList.toArray(new String[writerList.size()]);	
	}
	
	public Integer[] getPrice(){
		
		return priceList.toArray(new Integer[priceList.size()]);	
	}
	
	public boolean isLastPage(){
		
		return lastPage;
	}
	public boolean isFirstPage(){
		
		return firstPage;
	}
	
	public int getSize(){
		
		return codeList.size();
	}
	
	
	
	
	
	
//		private int codeNo;
//		private String title;
//		private String author;
//		private int price;
//		
//		public BooksInfo()
//		{
//			
//		}
//		
//		public void setCodeNo(int codeNo)
//		{
//			this.codeNo = codeNo;
//			
//		}
//		public String getTitle()
//		{
//			return toUnicode(title);
//		}
//		public String getAuthor()
//		{
//			return toUnicode(author);
//		}
//		public int getPrice()
//		{
//			return price;
//		}
//	
//		
//		public void readDB() throws ServletException
//		{
//			Connection conn = null;
//			Statement stmt = null;
//			 
//			try{
//				
//				Class.forName("com.mysql.jdbc.Driver");
//				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb","root","gpem4162");
//				if(conn==null)
//					throw new Exception("데이터베이스에 연결할 수 없습니다.");
//				
//				stmt = conn.createStatement();
//				
//				ResultSet rs = stmt.executeQuery("select * from book where codeNo = '"+this.codeNo+"';");
//				
//				if(rs.next())
//				{
//					
//					title = rs.getString("title");
//					author= rs.getString("author");
//					price = Integer.parseInt(rs.getString("price"));
//					
//				}
//						
//			
//			}
//			catch(Exception e)
//			{
//				
//				throw new ServletException(e);
//				
//			}
//			finally{
//				try{
//					stmt.close();
//				}
//				catch(Exception ignored){
//					
//				}
//				try{
//					
//					conn.close();
//				}
//				catch(Exception ignored){
//					
//				}
//			}
//		}
//		private String toUnicode(String str)
//		{
//			
//			if(str==null)
//			{
//				return null;
//			}
//			try{
//				byte[] b = str.getBytes("UTF-8");
//				return new String(b);
//			}
//			catch(java.io.UnsupportedEncodingException uee){
//				System.out.println("err");
//
//				return null;
//			}
//		}
	
	
	
	
}