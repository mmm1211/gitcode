package com.oes.bean;

import java.io.Serializable;

/**
 * 题目类
 * 
 * @author vean
 *
 */
public class Questions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String qid;// 题库编号
	private String qname;// 题目名称
	private int tid;// 题目类型
	private int cid;// 所属课程
	private String ans1;// 答案A
	private String ans2;// 答案B
	private String ans3;// 答案C
	private String ans4;// 答案D
	private String ans;// 正确答案
	private String sans;// 学生答案
	private int status;// 题目状态

	private String tname;// 题目类型名称
	private String cname;// 所属课程名称

	public Questions() {
		// TODO Auto-generated constructor stub
	}

	public Questions(String qid, String qname, int tid, int cid, String ans1, String ans2, String ans3, String ans4,
			String ans, int status, String tname, String cname) {
		super();
		this.qid = qid;
		this.qname = qname;
		this.tid = tid;
		this.cid = cid;
		this.ans1 = ans1;
		this.ans2 = ans2;
		this.ans3 = ans3;
		this.ans4 = ans4;
		this.ans = ans;
		this.status = status;
		this.tname = tname;
		this.cname = cname;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getAns1() {
		return ans1;
	}

	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}

	public String getAns2() {
		return ans2;
	}

	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}

	public String getAns3() {
		return ans3;
	}

	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}

	public String getAns4() {
		return ans4;
	}

	public void setAns4(String ans4) {
		this.ans4 = ans4;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getSans() {
		return sans;
	}

	public void setSans(String sans) {
		this.sans = sans;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Questions [qid=" + qid + ", qname=" + qname + ", tid=" + tid + ", cid=" + cid + ", ans1=" + ans1
				+ ", ans2=" + ans2 + ", ans3=" + ans3 + ", ans4=" + ans4 + ", ans=" + ans + ", sans=" + sans
				+ ", status=" + status + ", tname=" + tname + ", cname=" + cname + "]";
	}

}
