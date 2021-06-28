package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;


/**
 * Servlet implementation class RequestScopeSample
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) 
			throws ServletException, IOException {
		//ログインページを表示
		RequestDispatcher dispatcher = request.getRequestDispatcher("/loginForm.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		User user = new User(name,pass);
		
		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin = loginLogic.execute(user);
		
		//ログイン成功
		if(isLogin) {
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser",user);
			
			//ログイン成功画面にフォワード
			RequestDispatcher dispatcher=
					request.getRequestDispatcher(
							"/loginOK.jsp");
			dispatcher.forward(request,response);
		
		}else {
			response.sendRedirect("/AppDevelop/Login");
			
		}
		
		
		
	}
	
}
