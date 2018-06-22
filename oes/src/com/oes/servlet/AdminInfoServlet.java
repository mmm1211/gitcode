package com.oes.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.oes.bean.AdminInfo;
import com.oes.biz.IAdminInfoBiz;
import com.oes.biz.impl.AdminInfoBizImpl;
import com.oes.util.MD5Encryption;
import com.oes.util.UploadUtil;

/**
 * Servlet implementation class AdminInfoServlet
 */
@WebServlet("/AdminInfoServlet")
public class AdminInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter("op");
		if ("addAdminInfo".equals(op)) {
			addAdminInfo(request, response);
		} else if ("updateAdminInfo".equals(op)) {
			updateAdminInfo(request, response);
		} else if ("updateAdminInfoStatus".equals(op)) {
			updateAdminInfoStatus(request, response);
		} else if ("findAll".equals(op)) {
			findAll(request, response);
		} else if ("findByPage".equals(op)) {
			findByPage(request, response);
		} else if ("findByAid".equals(op)) {
			findByAid(request, response);
		} else if ("findByCondition".equals(op)) {
			findByCondition(request, response);
		}
	}

	/**
	 * 根据条件查找管理员
	 * 
	 * @param request
	 * @param response
	 */
	private void findByCondition(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
		String rid = request.getParameter("rid");
		String aname = request.getParameter("aname");
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		Map<String, Object> map = adminInfoBiz.findByPage(pageNo, pageSize, rid, aname);
		this.send(response, map);
	}

	/**
	 * 根据编号查找管理员
	 * 
	 * @param request
	 * @param response
	 */
	private void findByAid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String aid = request.getParameter("aid");
		IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
		AdminInfo adminInfo = adminInfoBiz.findByAid(aid);
		this.send(response, adminInfo);
	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param response
	 */
	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IAdminInfoBiz AdminInfoBiz = new AdminInfoBizImpl();
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		Map<String, Object> map = AdminInfoBiz.findByPage(pageNo, pageSize);
		this.send(response, map);
	}

	/**
	 * 查找所有管理员信息
	 * 
	 * @param request
	 * @param response
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IAdminInfoBiz AdminInfoBiz = new AdminInfoBizImpl();
		List<AdminInfo> list = AdminInfoBiz.finds();
		this.send(response, list);
	}

	/**
	 * 修改管理员状态
	 * 
	 * @param request
	 * @param response
	 */
	private void updateAdminInfoStatus(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
		String aids = request.getParameter("aids");
		String status = request.getParameter("status");
		int result = adminInfoBiz.updateAdminInfo(aids, status);
		this.send(response, result);
	}

	/**
	 * 修改管理员
	 * 
	 * @param request
	 * @param response
	 */
	private void updateAdminInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		UploadUtil upLoadUtil = new UploadUtil();
		IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true,
				1024, true);
		Map<String, String> map = upLoadUtil.upload(pageContext);
		int aid = Integer.parseInt(map.get("aid"));
		int rid = Integer.parseInt(map.get("rid"));
		String aname = map.get("aname");
		String pwd = map.get("pwd");
		String email = map.get("email");
		String photo = map.get("photo");

		AdminInfo adminInfo = new AdminInfo();
		adminInfo.setAid(aid);
		adminInfo.setRid(rid);
		adminInfo.setAname(aname);
		adminInfo.setPwd(pwd);
		adminInfo.setEmail(email);
		adminInfo.setPhoto(photo);

		int result = adminInfoBiz.updateAdminInfo(adminInfo);
		this.send(response, result);
	}

	/**
	 * 添加管理员
	 * 
	 * @param request
	 * @param response
	 */
	private void addAdminInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		UploadUtil upLoadUtil = new UploadUtil();
		IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true,
				1024, true);
		Map<String, String> map = upLoadUtil.upload(pageContext);
		int rid = Integer.parseInt(map.get("rid"));
		String aname = map.get("aname");
		String pwd = map.get("pwd");
		pwd = MD5Encryption.createPassword(pwd);
		String email = map.get("email");
		String photo = map.get("photo");

		AdminInfo adminInfo = new AdminInfo();
		adminInfo.setRid(rid);
		adminInfo.setAname(aname);
		adminInfo.setPwd(pwd);
		adminInfo.setEmail(email);
		adminInfo.setPhoto(photo);

		int result = adminInfoBiz.addAdminInfo(adminInfo);
		this.send(response, result);
	}

}
