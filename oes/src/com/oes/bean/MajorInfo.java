package com.oes.bean;

import java.io.Serializable;

/**
 * 专业类
 * @author vean
 *
 */
public class MajorInfo implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mid;// 专业编号
	private String mname;// 专业名称
	private int status;// 专业状态

	public MajorInfo() {
		// TODO Auto-generated constructor stub
	}

	public MajorInfo(int mid, String mname, int status) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.status = status;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MajorInfo [mid=" + mid + ", mname=" + mname + ", status=" + status + "]";
	}

}
