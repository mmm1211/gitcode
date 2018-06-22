package com.oes.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oes.bean.AnsPaper;
import com.oes.bean.ExamPaper;
import com.oes.bean.Questions;
import com.oes.bean.StuInfo;
import com.oes.biz.IAnsPaperBiz;
import com.oes.biz.IQuestionsBiz;
import com.oes.biz.impl.AnsPaperBizImpl;
import com.oes.biz.impl.QuestionsBizImpl;
import com.oes.util.SessionAttributeKey;

/**
 * Servlet implementation class AnsPaperServlet
 */
@WebServlet("/AnsPaperServlet")
public class AnsPaperServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter("op");
		if ("addAnsPaper".equals(op)) {
			addAnsPaper(request, response);
		} else if ("updateAnsPaper".equals(op)) {
		} else if ("findByPage".equals(op)) {
			findByPage(request, response);
		} else if ("findByCondition".equals(op)) {
			findByCondition(request, response);
		} else if ("findBySid".equals(op)) {
			findBySid(request, response);
		} else if (("findByAid").equals(op)) {
			findByAid(request, response);
		} else if (("deleteAnsPaper").equals(op)) {
			deleteAnsPaper(request, response);
		} else if (("findGrades").equals(op)) {
			findGrades(request, response);
		} else if (("checkExam").equals(op)) {
			checkExam(request, response);
		} else if (("findAll").equals(op)) {
			findAll(request, response);
		} else if (("findBySidAndEid").equals(op)) {
			findBySidAndEid(request, response);
		} else if (("findByEid").equals(op)) {
			findByEid(request, response);
		}else if (("findByCidAndEid").equals(op)) { 
			findByCidAndEid(request, response);
		}else if (("findGradesByCid").equals(op)) { 
			findGradesByCid(request, response);
		}
	}

	/**
	 * 查询某个班级或者某门课程的考试信息
	 * @param request
	 * @param response
	 */
	private void findGradesByCid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cid = request.getParameter("cid");
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		IAnsPaperBiz ansPaperBiz = new AnsPaperBizImpl();
		this.send(response, ansPaperBiz.findByCid(cid,pageNo,pageSize));
	}

	/**
	 * 查询某个班级所有学生考试信息
	 * @param request
	 * @param response
	 */
	private void findByCidAndEid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cid = request.getParameter("cid");
		String eid = request.getParameter("eid");
		IAnsPaperBiz ansPaperBiz = new AnsPaperBizImpl();
		this.send(response, ansPaperBiz.findByCidAndEid(cid,eid));
	}

	/**
	 * 查询某套试卷所有学生考试信息
	 * @param request
	 * @param response
	 */
	private void findByEid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String eid = request.getParameter("eid");
		IAnsPaperBiz ansPaperBiz = new AnsPaperBizImpl();
		this.send(response, ansPaperBiz.findByEid(eid));
	}

	/**
	 * 根据学号吗，试卷编号，查询答卷
	 * 
	 * @param request
	 * @param response
	 */
	private void findBySidAndEid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		HttpSession sesion = request.getSession();
		StuInfo stuInfo = (StuInfo) sesion.getAttribute(SessionAttributeKey.CURRENTLOGINUSER);
		String sid = stuInfo.getSid();
		String eid = request.getParameter("eid");
		IAnsPaperBiz ansPaperBiz = new AnsPaperBizImpl();
		this.send(response, ansPaperBiz.findBySidAndEid(sid, eid));
	}

	/**
	 * 所有试卷信息
	 * 
	 * @param request
	 * @param response
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IAnsPaperBiz ansPaperBiz = new AnsPaperBizImpl();
		this.send(response, ansPaperBiz.finds());
	}

	/**
	 * 判断该学生是否已经考过该套试卷了
	 * 
	 * @param request
	 * @param response
	 */
	private void checkExam(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		HttpSession sesion = request.getSession();
		StuInfo stuInfo = (StuInfo) sesion.getAttribute(SessionAttributeKey.CURRENTLOGINUSER);
		String sid = stuInfo.getSid();
		String eid = request.getParameter("eid");
		IAnsPaperBiz ansPaperBiz = new AnsPaperBizImpl();
		AnsPaper ansPaper = ansPaperBiz.findBySidAndEid(sid, eid);
		if (ansPaper == null) {
			this.send(response, 0);
		} else {
			this.send(response, 1);
		}

	}

	/**
	 * 所有考生考试信息
	 * 
	 * @param request
	 * @param response
	 */
	private void findGrades(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		IAnsPaperBiz ansPaperBiz = new AnsPaperBizImpl();
		Map<String, Object> map = ansPaperBiz.findGrades(pageNo, pageSize);
		this.send(response, map);
	}

	/**
	 * 删除答卷
	 * 
	 * @param request
	 * @param response
	 */
	private void deleteAnsPaper(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String aid = request.getParameter("aid");
		IAnsPaperBiz ansPaperBiz = new AnsPaperBizImpl();
		this.send(response, ansPaperBiz.deleteAnsPaper(aid));
	}

	/**
	 * 根据答卷编号查询
	 * 
	 * @param request
	 * @param response
	 */
	private void findByAid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String aid = request.getParameter("aid");
		IAnsPaperBiz ansPaperBiz = new AnsPaperBizImpl();
		this.send(response, ansPaperBiz.findByAid(aid));
	}

	/**
	 * 根据学生编号查询考试信息
	 * 
	 * @param request
	 * @param response
	 */
	private void findBySid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String sid = request.getParameter("sid");
		IAnsPaperBiz ansPaperBiz = new AnsPaperBizImpl();
		this.send(response, ansPaperBiz.findBySid(sid));
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
		IAnsPaperBiz ansPaperBiz = new AnsPaperBizImpl();
		Map<String, Object> map = ansPaperBiz.findByPage(pageNo, pageSize);
		this.send(response, map);
	}

	/**
	 * 条件分页查询
	 * 
	 * @param request
	 * @param response
	 */
	private void findByCondition(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String sid = request.getParameter("sid");
		String eid = request.getParameter("eid");

		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		IAnsPaperBiz ansPaperBiz = new AnsPaperBizImpl();
		Map<String, Object> map = ansPaperBiz.findByCondition(eid, sid, pageNo, pageSize);
		this.send(response, map);
	}

	/**
	 * 添加答卷
	 * 
	 * @param request
	 * @param response
	 */
	private void addAnsPaper(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int score = 0;
		Questions questions = null;
		AnsPaper ansPaper = new AnsPaper();
		String ans = request.getParameter("ans");
		String surplus = request.getParameter("surplus");
		surplus += " 分钟";
		HttpSession sesion = request.getSession();
		IQuestionsBiz questionsBiz = new QuestionsBizImpl();
		IAnsPaperBiz ansPaperBiz = new AnsPaperBizImpl();

		ExamPaper examPaper = (ExamPaper) sesion.getAttribute(SessionAttributeKey.EXAMPAPERINFO);
		StuInfo stuInfo = (StuInfo) sesion.getAttribute(SessionAttributeKey.CURRENTLOGINUSER);
		if (examPaper == null) {
			this.send(response, null);
		}
		if (stuInfo == null) {
			this.send(response, null);
		}
		// 包含题目类型编号和 分数
		Map<String, String> mscore = examPaper.getQuestionScore(); // 每种题型的分数

		// 每个数组包含题目 编号,类型，答案
		List<String[]> listans = ansPaper.getAnsExam(ans);

		/*
		 * for (int i = 0; i < listans.size(); i++) { String[] arrans =
		 * listans.get(i); //获取一道题的编号，类型，答案 questions =
		 * questionsBiz.findByQid(arrans[0]); //根据所给题目编号，获取这个题目对象 String trueAns
		 * = questions.getAns(); // 根据题目对象获取正确答案 if (trueAns.equals(arrans[2]))
		 * { // 如果答案正确
		 * 
		 * @SuppressWarnings("rawtypes") Iterator keys =
		 * mscore.keySet().iterator(); //童工迭代器来获取指定题目类型的分数 while
		 * (keys.hasNext()) { String key = (String) keys.next(); if
		 * (key.equals(arrans[1])) { //匹配题目类型 score +=
		 * Integer.parseInt(mscore.get(key)); } } } }
		 */
		for (int i = 0; i < listans.size(); i++) {
			String[] arrans = listans.get(i); // 获取一道题的编号，类型，答案
			questions = questionsBiz.findByQid(arrans[0]); // 根据所给题目编号，获取这个题目对象
			String trueAns = questions.getAns(); // 根据题目对象获取正确答案
			switch (Integer.parseInt(arrans[1])) {
			case 1: // 单选

				if (trueAns.equals(arrans[2])) { // 如果答案正确
					score += Integer.parseInt(mscore.get("1"));
					break;
				}

			case 2:// 多选
				if (trueAns.equals(arrans[2])) { // 如果答案正确
					score += Integer.parseInt(mscore.get("2"));
					break;
				} else if (trueAns.contains(arrans[2]) && trueAns.length() > arrans[2].length()) {
					score += Integer.parseInt(mscore.get("2")) / 2;
				} else {
					break;
				}

			case 3:// 判断

				if (trueAns.equals(arrans[2])) { // 如果答案正确
					score += Integer.parseInt(mscore.get("3"));
					break;
				}

			case 4:// 填空

				if (trueAns.equals(arrans[2])) { // 如果答案正确
					score += Integer.parseInt(mscore.get("4"));
					break;
				}
			default:// 填空
				break;
			}
		}
		int eid = examPaper.getEid();
		String sid = stuInfo.getSid();
		// 创建答卷对象
		AnsPaper aPaper = new AnsPaper();
		aPaper.setSid(sid);
		aPaper.setEid(eid);
		aPaper.setAns(ans);
		aPaper.setScore(score);
		aPaper.setSurplus(surplus);
/*		System.out.println(eid);
*/		int result = ansPaperBiz.addAnsPaper(aPaper);
		this.send(response, result);

	}
}
