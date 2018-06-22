package com.oes.bean;

import java.io.Serializable;

/**
 * 角色类
 * @author vean
 *
 */
public class RoleInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rid; // 角色编号
	private String rname;// 角色名称
	private int status;// 角色状态

	public RoleInfo() {
		// TODO Auto-generated constructor stub
	}

	public RoleInfo(int rid, String rname, int status) {
		super();
		this.rid = rid;
		this.rname = rname;
		this.status = status;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RoleInfo [rid=" + rid + ", rname=" + rname + ", status=" + status + "]";
	}

}
