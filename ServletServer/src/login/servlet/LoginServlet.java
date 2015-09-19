package login.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcmanager.JDBCManager;

@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JDBCManager manager=null;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--post--");
		request.setCharacterEncoding("UTF-8");
		String loginName=request.getParameter("LoginName");
		String loginPassword=request.getParameter("LoginPassword");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(loginName);
		System.out.println(loginPassword);
		manager=new JDBCManager();
		PrintWriter out=null;
		try{
		out=response.getWriter();
		if(manager.testuser(loginName, loginPassword)){
		    out.print("true");}
		else{
			out.print("false");
		}}
		finally{
			if(out!=null)
			out.close();
		}
	}

}
