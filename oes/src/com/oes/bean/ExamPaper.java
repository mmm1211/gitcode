package com.oes.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 试卷类
 * 
 * @author vean
 *
 */
public class ExamPaper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int eid;// 试卷编号
	private String ename;// 试卷名称
	private int cid;// 课程编号
	private String examTime;// 开考时间
	private String conTime;// 考试时长
	private String cids;// 考试班级
	private String subjects;// 题目信息
	private String score;// 每种题型的分数
	private int status;// 试卷状态
	private String cname;// 课程名称

	public ExamPaper() {
		// TODO Auto-generated constructor stub
	}

	public ExamPaper(int eid, String ename, int cid, String examTime, String conTime, String cids, String subjects,
			String score, int status) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.cid = cid;
		this.examTime = examTime;
		this.conTime = conTime;
		this.cids = cids;
		this.subjects = subjects;
		this.score = score;
		this.status = status;
	}

	/**
	 * 获取这套试卷的试题编号
	 */
	public String getQuestionsId() {
		if (subjects != null && !"".equals(subjects)) {
			String qids = "";
			String[] ansInfo = subjects.split(",");

			for (String str : ansInfo) {
				if (!"".equals(str)) {
					qids += str.substring(0, str.lastIndexOf("-")) + ",";
				}
			}
			qids = qids.substring(0, qids.lastIndexOf(","));
			return qids;
		} else {
			return "";
		}
	}

	/**
	 * 根据题型获取分数
	 */
	public int[] getSingleScore() {
		int[] scores = new int[5];
		if (score != null && !"".equals(scores)) {
			String[] strs = score.split(";");
			String[] temp = null;
			for (String str : strs) {
				temp = str.split("-");
				scores[Integer.parseInt(temp[0])] = Integer.parseInt(temp[1]);
			}
		}
		return scores;
	}

	/**
	 * 根据题型获取分数
	 */
	public Map<String, String> getQuestionScore() {
		Map<String, String> map = new HashMap<String, String>();
		if (score != null) {
			String[] strs = score.split(";"); // 1-2；2-4；3-1；4-2
			for (int i = 0; i < strs.length; i++) {
				String[] temp = strs[i].split("-"); // 1-2
				map.put(temp[0], temp[1]);
			}

		}
		return map;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getConTime() {
		return conTime;
	}

	public void setConTime(String conTime) {
		this.conTime = conTime;
	}

	public String getCids() {
		return cids;
	}

	public void setCids(String cids) {
		this.cids = cids;
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "ExamPaper [eid=" + eid + ", ename=" + ename + ", cid=" + cid + ", examTime=" + examTime + ", conTime="
				+ conTime + ", cids=" + cids + ", subjects=" + subjects + ", score=" + score + ", status=" + status
				+ ", cname=" + cname + "]";
	}

}
