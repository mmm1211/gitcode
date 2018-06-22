package com.oes.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oes.bean.RoleInfo;
import com.oes.biz.IRoleInfoBiz;
import com.oes.biz.impl.RoleInfoBizImpl;

/**
 * Servlet implementation class RoleInfoServlet
 */
@WebServlet("/RoleInfoServlet")
public class RoleInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoleInfoServlet() {
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
		if ("addRoleInfo".equals(op)) {
			addRoleInfo(request, response);
		} else if ("updateRoleInfo".equals(op)) {
			updateRoleInfo(request, response);
		} else if ("updateRoleInfoStatus".equals(op)) {
			updateRoleInfoStatus(request, response);
		} else if ("findAll".equals(op)) {
			findAll(request, response);
		} else if ("findByPage".equals(op)) {
			findByPage(request, response);
		} else if ("findByRid".equals(op)) {
			findByRid(request, response);
		}
	}

	/**
	 * 根据编号查询
	 * 
	 * @param request
	 * @param response
	 */
	private void findByRid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String rid = request.getParameter("rid");
		IRoleInfoBiz roleInfoBiz = new RoleInfoBizImpl();
		this.send(response, roleInfoBiz.findByRid(rid));

	}

	/**
	 * 修改用户状态
	 * 
	 * @param request
	 * @param response
	 */
	private void updateRoleInfoStatus(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String rids = request.getParameter("rids");
		String status = request.getParameter("status");
		IRoleInfoBiz roleInfoBiz = new RoleInfoBizImpl();
		this.send(response, roleInfoBiz.updateRoleInfo(rids, status));

	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param response
	 */
	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IRoleInfoBiz roleInfoBiz = new RoleInfoBizImpl();
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		Map<String, Object> map = roleInfoBiz.findByPages(pageNo, pageSize);
		this.send(response, map);
	}

	/**
	 * 查找所有角色信息
	 * 
	 * @param request
	 * @param response
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IRoleInfoBiz roleInfoBiz = new RoleInfoBizImpl();
		List<RoleInfo> list = roleInfoBiz.finds();
		this.send(response, list);
	}

	/**
	 * 修改角色
	 * 
	 * @param request
	 * @param response
	 */
	private void updateRoleInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IRoleInfoBiz roleInfoBiz = new RoleInfoBizImpl();
		int rid = Integer.parseInt(request.getParameter("rid"));
		String rname = request.getParameter("rname");

		RoleInfo roleInfo = new RoleInfo();
		roleInfo.setRid(rid);
		roleInfo.setRname(rname);

		int result = roleInfoBiz.updateRoleInfo(roleInfo);
		this.send(response, result);
	}

	/**
	 * 添加角色
	 * 
	 * @param request
	 * @param response
	 */
	private void addRoleInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IRoleInfoBiz roleInfoBiz = new RoleInfoBizImpl();
		String rname = request.getParameter("rname");

		RoleInfo roleInfo = new RoleInfo();
		roleInfo.setRname(rname);

		int result = roleInfoBiz.addRoleInfo(roleInfo);
		this.send(response, result);
	}

}
