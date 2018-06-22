package com.oes.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oes.bean.CourseInfo;
import com.oes.biz.ICourseInfoBiz;
import com.oes.biz.impl.CourseInfoBizImpl;

/**
 * Servlet implementation class CourserInfoServlet
 */
@WebServlet("/CourseInfoServlet")
public class CourseInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseInfoServlet() {
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
		if ("addCourseInfo".equals(op)) {
			addCourseInfo(request, response);
		} else if ("updateCourseInfo".equals(op)) {
			updateCourseInfo(request, response);
		} else if ("updateCourse".equals(op)) {
			updateCourse(request, response);
		} else if ("findByPage".equals(op)) {
			findByPage(request, response);
		} else if ("findByCondition".equals(op)) {
			findByCondition(request, response);
		} else if ("findAll".equals(op)) {
			findAll(request, response);
		}
	}

	/**
	 * 所有课程信息
	 * 
	 * @param request
	 * @param response
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ICourseInfoBiz courseInfoBiz = new CourseInfoBizImpl();
		this.send(response, courseInfoBiz.finds());
	}

	/**
	 * 启停课程
	 * 
	 * @param request
	 * @param response
	 */
	private void updateCourse(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cids = request.getParameter("cids");
		int status = Integer.parseInt(request.getParameter("status"));
		ICourseInfoBiz courseInfoBiz = new CourseInfoBizImpl();
		int result = courseInfoBiz.updateCourseInfo(cids, status);
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
		ICourseInfoBiz courseInfoBiz = new CourseInfoBizImpl();
		Map<String, Object> map = courseInfoBiz.findByPage(pageNo, pageSize);
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
		String cname = request.getParameter("cname");
		int semester = Integer.parseInt(request.getParameter("semester"));
		int status = Integer.parseInt(request.getParameter("status"));
		ICourseInfoBiz courseInfoBiz = new CourseInfoBizImpl();
		Map<String, Object> map = courseInfoBiz.findByPage(pageNo, pageSize, cname, semester, status);
		this.send(response, map);
	}

	/**
	 * 修改课程
	 * 
	 * @param request
	 * @param response
	 */
	private void updateCourseInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ICourseInfoBiz courseInfoBiz = new CourseInfoBizImpl();
		int cid = Integer.parseInt(request.getParameter("cid"));
		String cname = request.getParameter("cname");
		int semester = Integer.parseInt(request.getParameter("semester"));

		CourseInfo courseInfo = new CourseInfo();
		courseInfo.setCid(cid);
		courseInfo.setCname(cname);
		courseInfo.setSemester(semester);

		int result = courseInfoBiz.updateCourseInfo(courseInfo);
		this.send(response, result);
	}

	/**
	 * 添加专业
	 * 
	 * @param request
	 * @param response
	 */
	private void addCourseInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ICourseInfoBiz courseInfoBiz = new CourseInfoBizImpl();
		String cname = request.getParameter("cname");
		int semester = Integer.parseInt(request.getParameter("semester"));

		CourseInfo courseInfo = new CourseInfo();
		courseInfo.setCname(cname);
		courseInfo.setSemester(semester);

		int result = courseInfoBiz.addCourseInfo(courseInfo);
		this.send(response, result);
	}

}
