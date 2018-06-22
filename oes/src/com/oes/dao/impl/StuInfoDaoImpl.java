package com.oes.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.oes.bean.ClassInfo;
import com.oes.bean.StuInfo;
import com.oes.dao.DBHelper;
import com.oes.dao.IStuInfoDao;
import com.oes.util.StringUtil;

public class StuInfoDaoImpl implements IStuInfoDao {

	@Override
	public List<StuInfo> finds() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from  StuInfo ";
		return db.finds(sql, StuInfo.class);
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(sid) as total from StuInfo ";
		return db.getTotal(sql);
	}

	@Override
	public List<StuInfo> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select sid,sname,sex,cardID,tel,cname,grade,mname from "
				+ "StuInfo s,ClassInfo c,MajorInfo m where s.cid = c.cid and c.mid = m.mid order by sid) a where rownum<=?) where rn>?";
		return db.finds(sql, StuInfo.class, pageNo * pageSize, (pageNo - 1) * pageSize);
	}

	@Override
	public int addStuInfo(StuInfo stuInfo) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "insert into StuInfo values(?,?,?,?,?,?,?,?,1)";
		return db.update(sql, stuInfo.getSid(), stuInfo.getSname(), stuInfo.getPwd(), stuInfo.getCid(),
				stuInfo.getSex(), stuInfo.getPhoto(), stuInfo.getCardID(), stuInfo.getTel());
	}

	@Override
	public int updateStuInfo(StuInfo stuInfo) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "update  StuInfo set sname=?,pwd=?,cid=?,sex=?,photo=?,cardID=?,tel=? where sid=?";
		return db.update(sql, stuInfo.getSname(), stuInfo.getPwd(), stuInfo.getCid(), stuInfo.getSex(),
				stuInfo.getPhoto(), stuInfo.getCardID(), stuInfo.getTel(), stuInfo.getSid());
	}

	@Override
	public int updateStuInfo(String sids, int status) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = null;
		if (sids.contains(",") && !sids.contains("or")) {
			sql = "update  StuInfo set status=? where sid in (" + sids + ") ";
		} else {
			sql = "update  StuInfo set status=? where sid = ? and status=0";
		}
		return db.update(sql, status, sids);
	}

	@Override
	public List<StuInfo> find(String sid, String sname, String cid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from StuInfo  ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(sid)) {
			sql += " and sid =?";
			params.add(sid);
		}
		if (!StringUtil.isNull(sname)) {
			sql += " and sname = ?";
			params.add(sname);
		}
		if (!StringUtil.isNull(cid)) {
			sql += " and cid = ?";
			params.add(cid);
		}
		sql += " order by  sid ";
		return db.finds(sql, StuInfo.class, params);
	}

	@Override
	public List<StuInfo> findByPage(int pageNo, int pageSize, String sid, String sname, String cid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select * from StuInfo  " + "";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(sid)) {
			sql += " and sid =?";
			params.add(sid);
		}
		if (!StringUtil.isNull(sname)) {
			sql += " and sname = ?";
			params.add(sname);
		}
		if (!StringUtil.isNull(cid)) {
			sql += " and cid = ?";
			params.add(cid);
		}
		params.add(pageNo * pageSize);
		params.add((pageNo - 1) * pageSize);
		sql += " order by  sid ) a   where rownum <=?) where rn>?";
		return db.finds(sql, StuInfo.class, params);
	}

	@Override
	public int getTotal(String sid, String sname, String cid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select  count(sid) from StuInfo where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(sid)) {
			sql += " and sid =?";
			params.add(sid);
		}
		if (!StringUtil.isNull(sname)) {
			sql += " and sname = ?";
			params.add(sname);
		}
		if (!StringUtil.isNull(cid)) {
			sql += " and cid = ?";
			params.add(cid);
		}
		return db.getTotal(sql, params);
	}

	@Override
	public List<ClassInfo> getInfo() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		/**
		 * 此处将？？？？
		 */
		String sql = "select mid cid,mname cname,0 as mid, 0 as grade from majorInfo union select cid,cname,mid,grade from classInfo";
		return db.finds(sql, ClassInfo.class);
	}

	@Override
	public int addStuInfos(List<List<String>> list) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "insert into stuInfo values(?,?,?,?,?,?,?,?,1)";
		return db.updates(sql, list);
	}

	@Override
	public int addStuInfo(String sid, String sname, String pwd, String cid, String sex, String photo, String cardID,
			String tel) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "insert into stuInfo values(?,?,?,?,?,?,?,?,1)";
		return db.update(sql, sid, sname, pwd, cid, sex, photo, cardID, tel);
	}

	@Override
	public List<StuInfo> findByCondition(String sid, String sname, String mid, String cid, String grade, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select sid,s.cid,c.mid,sname,sex,cardID,tel,c.cname,c.grade,m.mname from "
				+ "StuInfo s,ClassInfo c,MajorInfo m where s.cid = c.cid and c.mid = m.mid";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(sid)) {
			sql += " and sid like '%'||?||'%'";
			params.add(sid);
		}
		if (!StringUtil.isNull(sname)) {
			sql += " and sname like '%'||?||'%'";
			params.add(sname);
		}
		if (!StringUtil.isNull(mid)) {
			sql += " and c.mid =?";
			params.add(mid);
		}
		if (!StringUtil.isNull(cid)) {
			sql += " and s.cid = ?";
			params.add(cid);
		}
		if (!StringUtil.isNull(grade)) {
			sql += " and c.grade = ?";
			params.add(grade);
		}
		/*
		 * if (!StringUtil.isNull(cid) && !StringUtil.isNull(mid)) { sql +=
		 * " and c.mid = ?"; params.add(mid); } if (!StringUtil.isNull(cid) &&
		 * !StringUtil.isNull(grade)) { sql += " and c.grade = ?";
		 * params.add(grade); }
		 */
		params.add(pageNo * pageSize);
		params.add((pageNo - 1) * pageSize);
		sql += " order by  sid ) a   where rownum <=?) where rn>?";
		return db.finds(sql, StuInfo.class, params);
	}

	@Override
	public int getTotal(String sid, String sname, String mid, String cid, String grade, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(sid) from StuInfo s,ClassInfo c,MajorInfo m where s.cid =c.cid and c.mid = m.mid";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(sid)) {
			sql += " and sid =?";
			params.add(sid);
		}
		if (!StringUtil.isNull(sname)) {
			sql += " and sname like '%'||?||'%'";
			params.add(sname);
		}
		if (!StringUtil.isNull(cid)) {
			sql += " and s.cid = ?";
			params.add(cid);
		}
		if (!StringUtil.isNull(cid) && !StringUtil.isNull(mid)) {
			sql += " and c.mid = ?";
			params.add(mid);
		}
		if (!StringUtil.isNull(cid) && !StringUtil.isNull(grade)) {
			sql += " and c.grade = ?";
			params.add(grade);
		}
		return db.getTotal(sql, params);
	}

	@Override
	public int resetPwd(String sid, String pwd) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "update StuInfo set pwd =? where sid = ?";
		return db.update(sql, pwd, sid);
	}

	@Override
	public StuInfo findBySid(String sid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select s.*,cname,mname,s.cid,c.mid from StuInfo s,ClassInfo c,MajorInfo m where sid=? ";
		return db.find(sql, StuInfo.class, sid);
	}

	@Override
	public int updatePhoto(String sid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "update SuInfo set photo =sid||'.jpg' where sid = ?";
		return db.update(sql, sid);
	}

	@Override
	public StuInfo login(String account, String pwd) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from StuInfo where  sid=? and pwd =? ";
		return db.find(sql, StuInfo.class, account, pwd);
	}

	@Override
	public List<StuInfo> findGadeBySid(int pageNo, int pageSize, String sid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select s.sid,e.eid,a.surplus,s.sname,c.cid,m.mname,c.grade,cs.cname,nvl(a.score,0) score"
				+ " from StuInfo s,AnsPaper a,ExamPaper e,CourseInfo cs,ClassInfo c,MajorInfo m "
				+ " where s.sid =a.sid and a.eid = e.eid and e.cid = cs.cid and s.cid = c.cid and c.mid = m.mid";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(sid)) {
			sql += " and s.sid like '%'||?||'%'";
			params.add(sid);
		}
		sql += "order by c.grade desc) a where rownum<=?) where rn>?";
		params.add(pageNo * pageSize);
		params.add((pageNo - 1) * pageSize);
		return db.finds(sql, StuInfo.class, params);
	}

	public int getTotal(String sid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(s.sid) from StuInfo s,AnsPaper a,ExamPaper e,CourseInfo cs,ClassInfo c,MajorInfo m "
				+ " where s.sid =a.sid and a.eid = e.eid and e.cid = cs.cid and s.cid = c.cid and c.mid = m.mid";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(sid)) {
			sql += " and s.sid like '%'||?||'%'";
			params.add(sid);
		}
		return db.getTotal(sql, params);
	}

}
