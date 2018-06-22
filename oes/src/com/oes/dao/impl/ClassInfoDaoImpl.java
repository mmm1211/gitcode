package com.oes.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.oes.bean.ClassInfo;
import com.oes.dao.DBHelper;
import com.oes.dao.IClassInfoDao;
import com.oes.util.StringUtil;

public class ClassInfoDaoImpl implements IClassInfoDao {

	@Override
	public List<ClassInfo> finds() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from  ClassInfo";
		return db.finds(sql, ClassInfo.class);
	}

	@Override
	public List<ClassInfo> find(String grade, String mid, String length) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from ClassInfo  c ,MajorInfo m where c.mid = m.mid";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(grade)) {
			sql += " and grade =?";
			params.add(grade);
		}
		if (!StringUtil.isNull(mid)) {
			sql += " and c.mid = ?";
			params.add(mid);
		}
		if (!StringUtil.isNull(length)) {
			sql += " and length = ?";
			params.add(length);
		}
		sql += " order by  cid ";
		return db.finds(sql, ClassInfo.class, params);
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(cid) as total from ClassInfo ";
		return db.getTotal(sql);
	}

	@Override
	public List<ClassInfo> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select * from ClassInfo  "
				+ "order by cid) a where rownum<=?) where rn>?";
		return db.finds(sql, ClassInfo.class, pageNo * pageSize, (pageNo - 1) * pageSize);
	}

	@Override
	public List<ClassInfo> findByPage(int pageNo, int pageSize, String grade, String mid, String length) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select * from ClassInfo   where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(grade)) {
			sql += " and grade =?";
			params.add(grade);
		}
		if (!StringUtil.isNull(mid)) {
			sql += " and mid = ?";
			params.add(mid);
		}
		if (!StringUtil.isNull(length)) {
			sql += " and length = ?";
			params.add(length);
		}
		params.add(pageNo * pageSize);
		params.add((pageNo - 1) * pageSize);
		sql += " order by  cid ) a   where rownum <=?) where rn>?";
		return db.finds(sql, ClassInfo.class, params);
	}

	@Override
	public int addClassInfo(ClassInfo classInfo) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "insert into ClassInfo values(seq_classInfo_cid.nextval,?,?,?,?,1)";
		return db.update(sql, classInfo.getCname(), classInfo.getMid(), classInfo.getGrade(), classInfo.getLength());
	}

	@Override
	public int updateClassInfo(ClassInfo classInfo) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "update  ClassInfo set cname=?,mid=?,grade=?,length=? where cid=?";
		return db.update(sql, classInfo.getCname(), classInfo.getMid(), classInfo.getGrade(), classInfo.getLength(),
				classInfo.getCid());
	}

	@Override
	public int updateClassInfo(String cids, int status) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = null;
		if (!StringUtil.isNull(cids) && cids.contains(",") && !cids.contains("or")) {
			sql = "update  ClassInfo set status=? where cid in (" + cids + ") ";
		} else {
			sql = "update  ClassInfo set status=? where cid = ? ";
		}
		return db.update(sql, status, cids);
	}

	@Override
	public int getTotal(String grade, String mid, String length) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select  count(cid) from ClassInfo where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(grade)) {
			sql += " and grade =?";
			params.add(grade);
		}
		if (!StringUtil.isNull(mid)) {
			sql += " and mid = ?";
			params.add(mid);
		}
		if (!StringUtil.isNull(length)) {
			sql += " and length = ?";
			params.add(length);
		}

		return db.getTotal(sql, params);
	}

	@Override
	public ClassInfo findByCid(String cid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from ClassInfo where cid=?";
		return db.find(sql, ClassInfo.class, cid);
	}

	@Override
	public List<ClassInfo> findByMid(String mid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from ClassInfo where mid=?";
		return db.finds(sql, ClassInfo.class, mid);
	}

}
