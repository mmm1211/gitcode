package com.oes.bean;

import java.io.Serializable;

/**
 * 课程类
 * @author vean
 *
 */
public class CourseInfo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cid;// 课程编号
	private String cname;// 课程名称
	private int semester;// 开设的学期
	private int status;// 状态

	public CourseInfo() {
		// TODO Auto-generated constructor stub
	}

	public CourseInfo(int cid, String cname, int semester, int status) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.semester = semester;
		this.status = status;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CourseInfo [cid=" + cid + ", cname=" + cname + ", semester=" + semester + ", status=" + status + "]";
	}

}
