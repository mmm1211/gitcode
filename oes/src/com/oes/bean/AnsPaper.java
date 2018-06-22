package com.oes.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 答卷类
 * 
 * @author vean
 *
 */
public class AnsPaper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int aid;// 答卷编号
	private int eid;// 试卷编号
	private String sid;// 学生学号
	private String ans;// 答案
	private int score;// 得分
	private String surplus;// 剩余时长

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private int status;// 答卷状态

	private String sname;// 学生姓名
	private String ename;// 试卷名称
	private String cname;// 课程名称
	private Map<String, String> questionAndAns;// 存放每一道题的答案

	public AnsPaper() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取每一题的答案
	 */
	public String[] getSingleAns() {
		if (ans != null && !"".equals(ans)) {
			return ans.split(",");
		} else {
			return null;
		}
	}

	/**
	 * 获取这套答案的题目编号
	 * 
	 */
	public String getQuestionsId() {
		if (ans != null && !"".equals(ans)) { // Q_106-6-AD,Q_104-5-A,Q_108-7-正确,Q_110-8-去,
			String qids = "";
			String[] ansInfo = ans.split(",");
			String[] temp = null;
			questionAndAns = new HashMap<String, String>();// 准备存放每一道题的答案
			for (String str : ansInfo) {
				if (!"".equals(str)) {
					temp = str.split(",");
					qids = temp[0] + ",";
					questionAndAns.put(temp[0], temp[1]);
				}
			}
			qids = qids.substring(0, qids.lastIndexOf(","));
			return qids;
		} else {
			return "";
		}
	}

	/**
	 * 获取这套答案的题目编号,类型，答案
	 * 
	 * 
	 */

	public List<String[]> getAnsExam(String ans) {

		ans = ans.substring(0, ans.length() - 1);
		List<String[]> list = new ArrayList<String[]>();
		if (ans != null && !"".equals(ans)) { // Q_106-6-AD,Q_104-5-A,Q_108-7-正确,Q_110-8-去,
			String[] ansInfo = ans.split(",");// 每一道题的编号,类型，答案
			for (int i = 0; i < ansInfo.length; i++) {

				String[] arrans = new String[3];
				String[] temp = ansInfo[i].split("-");
				if (temp.length == 2) { // 判断是否作答本道题目 --没有作答
					continue;
				} else { // 判断是否作答本道题目 --作答
					arrans[0] = temp[0]; // 题目编号
					arrans[1] = temp[1];// 题目类型
					arrans[2] = temp[2];// 题目答案
					list.add(arrans);
				}
			}
		}

		return list;

	}

	/**
	 * 根据试题编号,获取学生答案
	 * 
	 */
	public String getAns(String qid) {
		if (questionAndAns != null && !questionAndAns.isEmpty()) {
			return questionAndAns.get(qid);
		} else {
			return "";
		}
	}

	public AnsPaper(int aid, int eid, String sid, String ans, int score, String surplus, int status) {
		super();
		this.aid = aid;
		this.eid = eid;
		this.sid = sid;
		this.ans = ans;
		this.score = score;
		this.surplus = surplus;
		this.status = status;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getSurplus() {
		return surplus;
	}

	public void setSurplus(String surplus) {
		this.surplus = surplus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Map<String, String> getQuestionAndAns() {
		return questionAndAns;
	}

	public void setQuestionAndAns(Map<String, String> questionAndAns) {
		this.questionAndAns = questionAndAns;
	}

	@Override
	public String toString() {
		return "AnsPaper [aid=" + aid + ", eid=" + eid + ", sid=" + sid + ", ans=" + ans + ", score=" + score
				+ ", surplus=" + surplus + ", status=" + status + "]";
	}

}
