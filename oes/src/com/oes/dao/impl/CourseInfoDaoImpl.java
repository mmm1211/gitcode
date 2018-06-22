package com.oes.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oes.bean.CourseInfo;
import com.oes.dao.DBHelper;
import com.oes.dao.ICourseInfoDao;
import com.oes.util.StringUtil;

public class CourseInfoDaoImpl implements ICourseInfoDao {

	@Override
	public List<CourseInfo> finds() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from  CourseInfo";
		return db.finds(sql, CourseInfo.class);
	}

	@Override
	public List<CourseInfo> find(String cname, String semester, Integer status) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from  CourseInfo where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(cname)) {
			sql += " and cname like ?";
			params.add("%" + cname + "%");

		}
		if (!StringUtil.isNull(semester)) {
			sql += " and semester = ?";
			params.add(semester);

		}
		if (status != null) {
			sql += " and status = ?";
			params.add(status);

		}

		return db.finds(sql, CourseInfo.class, params);
	}

	@Override
	public int addCourseInfo(CourseInfo courseInfo) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "insert into CourseInfo values(seq_courseInfo_cid.nextval,?,?,1)";
		return db.update(sql, courseInfo.getCname(), courseInfo.getSemester());
	}

	@Override
	public int updateCourseInfo(CourseInfo courseInfo) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "update  CourseInfo set cname=?,semester=? where cid=?";
		return db.update(sql, courseInfo.getCname(), courseInfo.getSemester(), courseInfo.getCid());
	}

	@Override
	public int updateCourseInfo(String cids, Integer status) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		if (!StringUtil.isNull(cids) && !cids.contains(" or ")) {
			String sql = "update CourseInfo set status = ? where cid in(" + cids + ")";
			return db.update(sql, status);
		} else {
			String sql = "update CourseInfo set status = ? where cid = ?";
			return db.update(sql, status, cids);
		}
	}

	@Override
	public int getTotal(Map<String, String> map) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(cid) as total from CourseInfo where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (map != null) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				sql += " and " + key + " ?";
				params.add(map.get(key));
			}
		}
		return db.getTotal(sql, params);
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(cid) as total from CourseInfo ";
		return db.getTotal(sql);
	}

	@Override
	public int getTotal(String cname, int semester, Integer status) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(cid) from CourseInfo where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(cname)) {
			sql += " and cname like '%'||?||'%'";
			params.add(cname);

		}
		if (semester != -1) {
			sql += " and semester = ?";
			params.add(semester);

		}
		if (status != -1) {
			sql += " and status = ?";
			params.add(status);

		}
		return db.getTotal(sql, params);
	}

	@Override
	public List<CourseInfo> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select * from CourseInfo  "
				+ "order by cid) a where rownum<=?) where rn>?";
		return db.finds(sql, CourseInfo.class, pageNo * pageSize, (pageNo - 1) * pageSize);
	}

	@Override
	public List<CourseInfo> findByPage(int pageNo, int pageSize, String cname, int semester, Integer status) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from ( select a.*,rownum rn from ( select * from CourseInfo  where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(cname)) {
			sql += " and cname like '%'|| ? ||'%'";
			params.add(cname);
		}
		if (semester != -1) {
			sql += " and semester = ?";
			params.add(semester);
		}
		if (status != -1) {
			sql += " and status = ?";
			params.add(status);
		}
		params.add(pageNo * pageSize);
		params.add((pageNo - 1) * pageSize);
		sql += " order by  status desc) a   where rownum <=?) where rn>?";
		return db.finds(sql, CourseInfo.class, params);

	}

}
