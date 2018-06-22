package com.oes.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oes.bean.ExamPaper;
import com.oes.biz.IExamPaperBiz;
import com.oes.biz.IQuestionsBiz;
import com.oes.biz.impl.ExamPaperBizImpl;
import com.oes.biz.impl.QuestionsBizImpl;
import com.oes.util.StringUtil;

/**
 * Servlet implementation class ExamPaperServlet
 */
@WebServlet("/ExamPaperServlet")
public class ExamPaperServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExamPaperServlet() {
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
		if ("getInfos".equals(op)) {
			getInfos(request, response);
		} else if ("findByPage".equals(op)) {
			findByPage(request, response);
		} else if ("addExamPaper".equals(op)) {
			addExamPaper(request, response);
		} else if ("updateExamPaper".equals(op)) {
			updateExamPaper(request, response);
		} else if ("findByCid".equals(op)) {
			findByCid(request, response);
		} else if ("findByEid".equals(op)) {
			findByEid(request, response);
		} else if ("findByCondition".equals(op)) {
			findByCondition(request, response);
		} else if ("findQuestionNumByCid".equals(op)) {
			findQuestionNumByCid(request, response);
		} else if ("deleteExamPaper".equals(op)) {
			deleteExamPaper(request, response);
		} else if ("changeExamPaperStatus".equals(op)) {
			changeExamPaperStatus(request, response);
		} else if ("findAll".equals(op)) {
			findAll(request, response);
		}
	}

	/**
	 * 所有试卷信息
	 * 
	 * @param request
	 * @param response
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IExamPaperBiz examPaperBiz = new ExamPaperBizImpl();
		this.send(response, examPaperBiz.finds());
	}

	/**
	 * 修改试卷状态
	 * 
	 * @param request
	 * @param response
	 */
	private void changeExamPaperStatus(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String eid = request.getParameter("eid");
		String status = request.getParameter("status");
		IExamPaperBiz examPaperBiz = new ExamPaperBizImpl();
		int result = examPaperBiz.updateExamPaper(eid, status);
		this.send(response, result);
	}

	/**
	 * 删除试卷
	 * 
	 * @param request
	 * @param response
	 */
	private void deleteExamPaper(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String eid = request.getParameter("eid");
		IExamPaperBiz examPaperBiz = new ExamPaperBizImpl();
		int result = examPaperBiz.deleteExamPaper(eid);
		this.send(response, result);
	}

	/**
	 * 根据编号获取该课程每种题型的数量
	 * 
	 * @param request
	 * @param response
	 */
	private void findQuestionNumByCid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cid = request.getParameter("cid");
		IQuestionsBiz questionsBiz = new QuestionsBizImpl();
		List<Map<String, String>> list = questionsBiz.getTotals(cid);
		this.send(response, list);
	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param response
	 */
	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IExamPaperBiz examPaperBiz = new ExamPaperBizImpl();
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		Map<String, Object> map = examPaperBiz.findByPage(pageNo, pageSize);
		this.send(response, map);
	}

	/**
	 * 根据条件分页查询
	 * 
	 * @param request
	 * @param response
	 */
	private void findByCondition(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cid = request.getParameter("courseId");
		String cids = request.getParameter("cid");
		String status = request.getParameter("status");
		IExamPaperBiz examPaperBiz = new ExamPaperBizImpl();
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		Map<String, Object> map = examPaperBiz.findByCondition(cid, cids, status, pageNo, pageSize);
		this.send(response, map);
	}

	/**
	 * 添加试卷信息
	 * 
	 * @param request
	 * @param response
	 */
	private void addExamPaper(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IExamPaperBiz examPaperBiz = new ExamPaperBizImpl();

		String ename = request.getParameter("ename"); //考试名称
		String cid = request.getParameter("cid"); //考试课程
		String examTime = request.getParameter("examTime"); //考试开始时间
		String conTime = request.getParameter("conTime"); //考试持续时间
		String cids = request.getParameter("cids"); //考试班级
		String score = request.getParameter("score"); //每种题型的分数

		// 获取各个题型的数量
		String rnum = request.getParameter("rnum"); 
		String cnum = request.getParameter("cnum");
		String jnum = request.getParameter("jnum");
		String fnum = request.getParameter("fnum");
		// 根据课程编号获取该课程对应的所有题目信息
		List<Map<String, String>> list = examPaperBiz.findQuestionByCid(cid);
		List<String> radioList = new ArrayList<String>();  //存单选题的集合
		List<String> checkBoxList = new ArrayList<String>();//存多选题的集合
		List<String> judgeList = new ArrayList<String>();//存判断题的集合
		List<String> fillList = new ArrayList<String>();//存填空题的集合
		if (list != null && !list.isEmpty()) {
			for (Map<String, String> map : list) {
				if ("1".equals(map.get("tid"))) { // 单选
					radioList.add(map.get("qid"));
				} else if ("2".equals(map.get("tid"))) { // 多选
					checkBoxList.add(map.get("qid"));
				} else if ("3".equals(map.get("tid"))) { // 判断
					judgeList.add(map.get("qid"));
				} else if ("4".equals(map.get("tid"))) { // 填空
					fillList.add(map.get("qid"));
				}
			}
			String subjects = "";
			if (!StringUtil.isNull(rnum) && Integer.parseInt(rnum) <= radioList.size()) { // 判断数据库指定题目类型数量是否满足所需题目数量
				Collections.shuffle(radioList);
				for (int i = 0, len = Integer.parseInt(rnum); i < len; i++) { // 从给定题目类型数量选择对应类型题目
																				// -单选
					subjects += radioList.get(i) + ",";
				}
			}
			if (!StringUtil.isNull(cnum) && Integer.parseInt(cnum) <= checkBoxList.size()) {// 判断数据库指定题目类型数量是否满足所需题目数量
				Collections.shuffle(checkBoxList);
				for (int i = 0, len = Integer.parseInt(cnum); i < len; i++) {// 从给定题目类型数量选择对应类型题目
																				// -多选
					subjects += checkBoxList.get(i) + ",";
				}
			}
			if (!StringUtil.isNull(jnum) && Integer.parseInt(jnum) <= judgeList.size()) {
				Collections.shuffle(judgeList);
				for (int i = 0, len = Integer.parseInt(jnum); i < len; i++) { // 从给定题目类型数量选择对应类型题目
																				// -判断
					subjects += judgeList.get(i) + ",";
				}
			}
			if (!StringUtil.isNull(fnum) && Integer.parseInt(fnum) <= fillList.size()) {// 判断数据库指定题目类型数量是否满足所需题目数量
				Collections.shuffle(fillList);
				for (int i = 0, len = Integer.parseInt(fnum); i < len; i++) {// 从给定题目类型数量选择对应类型题目
																				// -填空
					subjects += fillList.get(i) + ",";
				}
			}
			if ("".equals(subjects)) {
				this.send(response, -1);
			} else {
				subjects = subjects.substring(0, subjects.length() - 1);
			}
			ExamPaper examPaper = new ExamPaper();
			examPaper.setEname(ename);
			examPaper.setCid(Integer.parseInt(cid));
			examPaper.setExamTime(examTime);
			examPaper.setConTime(conTime);
			examPaper.setCids(cids);
			examPaper.setSubjects(subjects);
			examPaper.setSubjects(subjects);
			examPaper.setScore(score);
			this.send(response, examPaperBiz.addExamPaper(examPaper));
		} else {
			this.send(response, -1); // 题量不足
		}

	}

	/**
	 * 修改试卷信息
	 * 
	 * @param request
	 * @param response
	 */
	private void updateExamPaper(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String eid = request.getParameter("eid");

		String ename = request.getParameter("ename");
		int cid = Integer.parseInt(request.getParameter("cid"));
		String examTime = request.getParameter("examTime");
		String conTime = request.getParameter("conTime");
		String cids = request.getParameter("cids");
		String subjects = request.getParameter("subjects");
		String score = request.getParameter("score");

		ExamPaper examPaper = new ExamPaper();
		examPaper.setEid(Integer.parseInt(eid));
		examPaper.setEname(ename);
		examPaper.setCid(cid);
		examPaper.setExamTime(examTime);
		examPaper.setConTime(conTime);
		examPaper.setCids(cids);
		examPaper.setSubjects(subjects);
		examPaper.setScore(score);
		IExamPaperBiz examPaperBiz = new ExamPaperBizImpl();

		this.send(response, examPaperBiz.addExamPaper(examPaper));
	}

	/**
	 * 根据课程编号查询该课程下考试信息
	 * 
	 * @param request
	 * @param response
	 */
	private void findByCid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cid = request.getParameter("cid");
		IExamPaperBiz examPaperBiz = new ExamPaperBizImpl();
		this.send(response, examPaperBiz.findByCid(cid));

	}

	/**
	 * 通过试卷编号查询试卷信息
	 * 
	 * @param request
	 * @param response
	 */
	private void findByEid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String eid = request.getParameter("eid");
		IExamPaperBiz examPaperBiz = new ExamPaperBizImpl();
		this.send(response, examPaperBiz.findExamPaperByEid(eid));
	}

	/**
	 * 获取专业，班级,课程信息
	 * 
	 * @param request
	 * @param response
	 */
	private void getInfos(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IExamPaperBiz examPaperBiz = new ExamPaperBizImpl();
		this.send(response, examPaperBiz.getInfos());
	}

}
