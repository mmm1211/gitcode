package com.oes.bean;

import java.io.Serializable;

/**
 * 班级类
 * 
 * @author vean
 *
 */
public class ClassInfo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cid;// 班级编号
	private String cname;// 班级名称
	private int mid;// 所属专业编号
	private int grade;// 年级
	private int length;// 学制
	private int status;// 状态
	private String mname;// 所属专业名称

	public ClassInfo(int cid, String cname, int mid, int grade, int length, int status) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.mid = mid;
		this.grade = grade;
		this.length = length;
		this.status = status;
	}

	public ClassInfo() {
		// TODO Auto-generated constructor stub
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

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	@Override
	public String toString() {
		return "ClassInfo [cid=" + cid + ", cname=" + cname + ", mid=" + mid + ", grade=" + grade + ", length=" + length
				+ ", status=" + status + "]";
	}

}
