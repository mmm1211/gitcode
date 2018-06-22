package com.oes.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oes.bean.ClassInfo;
import com.oes.biz.IClassInfoBiz;
import com.oes.biz.impl.ClassInfoBizImpl;

/**
 * Servlet implementation class ClassInfoServlet
 */
@WebServlet("/ClassInfoServlet")
public class ClassInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassInfoServlet() {
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
		// TODO Auto-generated method stub
		String op = request.getParameter("op");
		if ("addClassInfo".equals(op)) {
			addClassInfo(request, response);
		} else if ("updateClassInfo".equals(op)) {
			updateClassInfo(request, response);
		} else if ("updateClass".equals(op)) {
			updateClass(request, response);
		} else if ("findByPage".equals(op)) {
			findByPage(request, response);
		} else if ("findByCondition".equals(op)) {
			findByCondition(request, response);
		} else if (("findAll").equals(op)) {
			findAll(request, response);
		} else if (("findByCid").equals(op)) {
			findByCid(request, response);
		}else if (("findByMid").equals(op)) {
			findByMid(request, response);
		}
	}

	/**
	 * 通过专业编号查询班级信息
	 * @param request
	 * @param response
	 */
	private void findByMid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String mid = request.getParameter("mid");
		IClassInfoBiz classInfoBiz = new ClassInfoBizImpl();
		this.send(response, classInfoBiz.findByMid(mid));

	}

	/**
	 * 通过班级编号查找班级信息
	 * 
	 * @param request
	 * @param response
	 */
	private void findByCid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cid = request.getParameter("cid");
		IClassInfoBiz classInfoBiz = new ClassInfoBizImpl();
		this.send(response, classInfoBiz.findByCid(cid));

	}

	/**
	 * 查询所有班级
	 * 
	 * @param request
	 * @param response
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IClassInfoBiz classInfoBiz = new ClassInfoBizImpl();
		List<ClassInfo> list = classInfoBiz.finds();
		this.send(response, list);
	}

	/**
	 * 启停课程
	 * 
	 * @param request
	 * @param response
	 */
	private void updateClass(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cids = request.getParameter("cids");
		int status = Integer.parseInt(request.getParameter("status"));
		IClassInfoBiz classInfoBiz = new ClassInfoBizImpl();
		int result = classInfoBiz.updateClassInfo(cids, status);
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
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		IClassInfoBiz classInfoBiz = new ClassInfoBizImpl();
		Map<String, Object> map = classInfoBiz.findByPage(pageNo, pageSize);
		this.send(response, map);
	}

	/**
	 * 条件查询
	 * 
	 * @param request
	 * @param response
	 */
	private void findByCondition(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		String grade = request.getParameter("grade");
		String mid = request.getParameter("mid");
		String length = request.getParameter("length");
		IClassInfoBiz classInfoBiz = new ClassInfoBizImpl();
		Map<String, Object> map = classInfoBiz.findByPage(pageNo, pageSize, grade, mid, length);
		this.send(response, map);
	}

	/**
	 * 修改课程
	 * 
	 * @param request
	 * @param response
	 */
	private void updateClassInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IClassInfoBiz classInfoBiz = new ClassInfoBizImpl();
		int cid = Integer.parseInt(request.getParameter("cid"));
		String cname = request.getParameter("cname");
		int mid = Integer.parseInt(request.getParameter("mid"));
		int grade = Integer.parseInt(request.getParameter("grade"));
		int length = Integer.parseInt(request.getParameter("length"));

		ClassInfo classInfo = new ClassInfo();
		classInfo.setCid(cid);
		classInfo.setCname(cname);
		classInfo.setGrade(grade);
		classInfo.setMid(mid);
		classInfo.setLength(length);
		int result = classInfoBiz.updateClassInfo(classInfo);
		this.send(response, result);
	}

	/**
	 * 添加专业
	 * 
	 * @param request
	 * @param response
	 */
	private void addClassInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IClassInfoBiz classInfoBiz = new ClassInfoBizImpl();
		String cname = request.getParameter("cname");
		int mid = Integer.parseInt(request.getParameter("mid"));
		int grade = Integer.parseInt(request.getParameter("grade"));
		int length = Integer.parseInt(request.getParameter("length"));

		ClassInfo classInfo = new ClassInfo();
		classInfo.setCname(cname);
		classInfo.setGrade(grade);
		classInfo.setMid(mid);
		classInfo.setLength(length);
		int result = classInfoBiz.addClassInfo(classInfo);
		this.send(response, result);
	}
}