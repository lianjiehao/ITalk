package jdbcmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import dataclass.UserData;



public class JDBCManager{
	//获取jadbc连接
	public Connection getConnection(){
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/italk?user=root&useUnicode=true&characterEncoding=utf8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//验证用户合法性
	public boolean testuser(String uid,String password){
		Connection conn=getConnection();
		ResultSet rset=null;
		boolean b=false;
		try {
			Statement stmt=conn.createStatement();
			rset=stmt.executeQuery("select * from UserServer where uid='"+uid+"'"+"and "+"password='"+password+"'");
			if(rset.next()){
				b = true;
			}
			else
				b = false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally{
			
			try {
				if(conn != null){
					conn.close();
				}
				if(rset != null){
				rset.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return b;
	}
	
	
	public boolean addUser(String LoginName,String LoginPassword,String name,String ganqing){
		Connection conn=getConnection();
		PreparedStatement stmt=null;
		int x=0;
		
		Statement stmtm = null;
		try {
			stmtm = conn.createStatement();
			ResultSet rset = stmtm.executeQuery("select name from userinfo");
			while(rset.next()){
				if(rset.getString(1).equals(name)){
					x=1;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
		
		if(x==1){
			return false;
		}
		else{
			try {
				stmt=conn.prepareStatement("insert into userinfo(uid,password,name,ganqing) values(?,?,?,?)");
				stmt.setString(1, LoginName);
				stmt.setString(2, LoginPassword);
				stmt.setString(3, name);
				stmt.setString(4, ganqing);
				stmt.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				if(stmt != null){
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(conn !=null){
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return true;
		}
	}
	
	
	//将数据转为JSON数据
	public String getJSONString(String loginName){
		Connection conn=getConnection();
		List <UserData> list = new ArrayList <UserData>();
		ResultSet rset=null;
		String str=null;
		JSONArray mJSONArray = new JSONArray();
		try {
			Statement stmt=conn.createStatement();
			rset = stmt.executeQuery("select * from userinfo");
			while(rset.next()){
				if(!rset.getString(2).equals(loginName)){
					list.add(new UserData(rset.getString(2),rset.getString(4),rset.getString(5),rset.getString(6),rset.getString(7),rset.getString(8)));
				}
			}
			str = mJSONArray.fromObject(list).toString();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(rset !=null){
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return str;
		
	}
		
	
}
