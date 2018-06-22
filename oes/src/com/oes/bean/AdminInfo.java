package com.oes.bean;

import java.io.Serializable;

/**
 * 管理员类
 * 
 * @author vean
 *
 */
public class AdminInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int aid;// 管理员编号
	private int rid;// 所属角色编号
	private String aname;// 管理员名称
	private String pwd;// 管理员密码
	private String email;// 管理员邮箱
	private String photo;// 管理员图像
	private int status;// 管理员状态

	public AdminInfo() {
		// TODO Auto-generated constructor stub
	}

	public AdminInfo(int aid, int rid, String aname, String pwd, String email, String photo, int status) {
		super();
		this.aid = aid;
		this.rid = rid;
		this.aname = aname;
		this.pwd = pwd;
		this.email = email;
		this.photo = photo;
		this.status = status;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AdminInfo [aid=" + aid + ", rid=" + rid + ", aname=" + aname + ", pwd=" + pwd + ", email=" + email
				+ ", photo=" + photo + ", status=" + status + "]";
	}

}
