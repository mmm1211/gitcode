package com.oes.bean;

import java.io.Serializable;

/**
 * 题型类
 * @author vean
 *
 */
public class QuestionTypes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tid;// 题型编号
	private String tname;// 题型名称
	private int status;// 题型状态

	public QuestionTypes() {
		// TODO Auto-generated constructor stub
	}

	public QuestionTypes(int tid, String tname, int status) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.status = status;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "QuestionTypes [tid=" + tid + ", tname=" + tname + ", status=" + status + "]";
	}

}
