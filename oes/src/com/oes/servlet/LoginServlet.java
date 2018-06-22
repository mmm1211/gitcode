package com.oes.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oes.bean.AdminInfo;
import com.oes.bean.StuInfo;
import com.oes.biz.IAdminInfoBiz;
import com.oes.biz.IStuInfoBiz;
import com.oes.biz.impl.AdminInfoBizImpl;
import com.oes.biz.impl.StuInfoBizImpl;
import com.oes.util.SessionAttributeKey;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int role = Integer.parseInt(request.getParameter("role"));
		String account = request.getParameter("account");
		String pwd = request.getParameter("pwd");
		int result = 0;
		if (role == 1021) { // 说明是学生
			IStuInfoBiz studentBiz = new StuInfoBizImpl();
			StuInfo stuInfo = studentBiz.login(account, pwd);
			if (stuInfo != null) { // 说明登陆成功
				result = 1;
				HttpSession session = request.getSession();
				session.setAttribute(SessionAttributeKey.CURRENTLOGINUSER, stuInfo);
			}
			this.send(response, result);
		} else if (role == 1022) {// 说明是教师
			IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
			AdminInfo adminInfo = adminInfoBiz.login(account, pwd);
			if (adminInfo != null) { // 说明登陆成功
				result = 1;
				HttpSession session = request.getSession();
				session.setAttribute(SessionAttributeKey.CURRENTLOGINADMIN, adminInfo);
			}
			this.send(response, result);
		} else if (1023 == 1) {// 说明是管理员

		}

	}

}
