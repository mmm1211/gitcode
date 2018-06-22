package com.oes.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oes.bean.MajorInfo;
import com.oes.biz.IMajorInfoBiz;
import com.oes.biz.impl.MajorInfoBizImpl;

/**
 * Servlet implementation class MajorInfoServlet
 */
@WebServlet("/MajorInfoServlet")
public class MajorInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MajorInfoServlet() {
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
		if ("addMajorInfo".equals(op)) {
			addMajorInfo(request, response);
		} else if ("updateMajorInfo".equals(op)) {
			updateMajorInfo(request, response);
		} else if ("updateMajor".equals(op)) {
			updateMajor(request, response);
		} else if ("findByPage".equals(op)) {
			findByPage(request, response);
		} else if ("findAll".equals(op)) {
			findAll(request, response);
		}
	}

	/**
	 * 查询所有专业
	 * 
	 * @param request
	 * @param response
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IMajorInfoBiz majorInfoBiz = new MajorInfoBizImpl();
		List<MajorInfo> list = majorInfoBiz.finds();
		this.send(response, list);

	}

	/**
	 * 修改专业状态
	 * 
	 * @param request
	 * @param response
	 */
	private void updateMajor(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IMajorInfoBiz majorInfoBiz = new MajorInfoBizImpl();
		String mids = request.getParameter("mids");
		int status = Integer.parseInt(request.getParameter("status"));

		int result = majorInfoBiz.updateMajorInfo(mids, status);
		this.send(response, result);
	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param response
	 */
	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IMajorInfoBiz majorInfoBiz = new MajorInfoBizImpl();
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		Map<String, Object> map = majorInfoBiz.findByPage(pageNo, pageSize);
		this.send(response, map);
	}

	/**
	 * 修改专业
	 * 
	 * @param request
	 * @param response
	 */
	private void updateMajorInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IMajorInfoBiz majorInfoBiz = new MajorInfoBizImpl();
		int mid = Integer.parseInt(request.getParameter("mid"));
		String mname = request.getParameter("mname");

		MajorInfo MajorInfo = new MajorInfo();
		MajorInfo.setMid(mid);
		MajorInfo.setMname(mname);

		int result = majorInfoBiz.updateMajorInfo(MajorInfo);
		this.send(response, result);
	}

	/**
	 * 添加专业
	 * 
	 * @param request
	 * @param response
	 */
	private void addMajorInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IMajorInfoBiz majorInfoBiz = new MajorInfoBizImpl();
		String mname = request.getParameter("mname");

		MajorInfo MajorInfo = new MajorInfo();
		MajorInfo.setMname(mname);

		int result = majorInfoBiz.addMajorInfo(MajorInfo);
		this.send(response, result);
	}

}
