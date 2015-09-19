package myfragment.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbcmanager.JDBCManager;

@WebServlet("/SchoolFri.do")
public class SchoolFriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JDBCManager manager=null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//封装JSON数据发送至Client端
		System.out.println("--post--");
		request.setCharacterEncoding("UTF-8");
		String loginName=request.getParameter("LoginName");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		manager=new JDBCManager();
		PrintWriter out=null;
		out=response.getWriter();
	    out.print(manager.getJSONString(loginName));
	    if(out != null){
	    	out.close();
	    }
	}

}
