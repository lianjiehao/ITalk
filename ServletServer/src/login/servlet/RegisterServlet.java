package login.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcmanager.JDBCManager;

@WebServlet("/Register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JDBCManager manager=null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--post--");
		request.setCharacterEncoding("UTF-8");
		String loginName=request.getParameter("userName");
		String loginPassword=request.getParameter("LoginPassword");
		String name=request.getParameter("name");
		String ganQing=request.getParameter("ganQing");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(loginName);
		System.out.println(loginPassword);
		System.out.println(name);
		System.out.println(ganQing);
		manager=new JDBCManager();
		PrintWriter out=null;
		try{
		out=response.getWriter();
		if(manager.addUser(loginName, loginPassword,name,ganQing)){
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
