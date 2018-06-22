package com.oes.bean;

import java.io.Serializable;

/**
 * 学生类
 * 
 * @author vean
 *
 */
public class StuInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sid;// 学号
	private String sname;// 学生姓名
	private String pwd;// 学生密码
	private int cid;// 所在班级
	private String sex;// 性别
	private String photo;// 图像
	private String cardID;// 身份证号
	private String tel;// 联系方式
	private int status;// 学生状态
	private String cname;// 班级名称
	private String mname;// 专业属性
	private String grade;// 年级属性
	private int score;// 成绩
	private String eid; // 试卷编号
	private String surplus;

	public StuInfo() {
		// TODO Auto-generated constructor stub
	}

	public StuInfo(String sid, String sname, String pwd, int cid, String sex, String photo, String cardID, String tel,
			int status) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.pwd = pwd;
		this.cid = cid;
		this.sex = sex;
		this.photo = photo;
		this.cardID = cardID;
		this.tel = tel;
		this.status = status;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getSurplus() {
		return surplus;
	}

	public void setSurplus(String surplus) {
		this.surplus = surplus;
	}

	@Override
	public String toString() {
		return "StuInfo [sid=" + sid + ", sname=" + sname + ", pwd=" + pwd + ", cid=" + cid + ", sex=" + sex
				+ ", photo=" + photo + ", cardID=" + cardID + ", tel=" + tel + ", status=" + status + ", cname=" + cname
				+ ", mname=" + mname + ", grade=" + grade + ", score=" + score + ", eid=" + eid + ", surplus=" + surplus
				+ "]";
	}

}
