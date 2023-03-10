package com.cruds.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cruds.entity.User;
import com.cruds.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		
		session.setAttribute("USERNAME", userName);
		RequestDispatcher rd = null;
		
		User user = UserService.authenticate(userName, pwd);
		
		if (user !=null)
		{
			session.setAttribute("USER", user);
			rd = request.getRequestDispatcher("WEB-INF/views/checkout.jsp");
		}
		else
		{
			request.setAttribute("ERROR", "Invalid User Credentials");
			rd = request.getRequestDispatcher("WEB-INF/views/login.jsp");
		}
		rd.forward(request, response);
		
	}

}
