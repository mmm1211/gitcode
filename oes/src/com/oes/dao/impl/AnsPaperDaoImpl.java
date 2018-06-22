package com.oes.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.oes.bean.AnsPaper;
import com.oes.bean.StuInfo;
import com.oes.dao.DBHelper;
import com.oes.dao.IAnsPaperDao;
import com.oes.util.StringUtil;

public class AnsPaperDaoImpl implements IAnsPaperDao {

	@Override
	public List<AnsPaper> finds() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from  AnsPaper order by aid ";
		return db.finds(sql, AnsPaper.class);
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(aid) as total from AnsPaper ";
		return db.getTotal(sql);
	}

	@Override
	public List<AnsPaper> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select * from AnsPaper  "
				+ "order by aid) a where rownum<=?) where rn>?";
		return db.finds(sql, AnsPaper.class, pageNo * pageSize, (pageNo - 1) * pageSize);
	}

	@Override
	public int addAnsPaper(AnsPaper ansPaper) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		System.out.println(ansPaper.getEid()+"\t"+ansPaper.getSid()+"\t"+ansPaper.getAns()+"\t"+ansPaper.getScore()+"\t"+ansPaper.getSurplus());
		String sql = "insert into AnsPaper values(seq_ansPaper_aid.nextval,?,?,?,?,?,1)";
		return db.update(sql, ansPaper.getEid(), ansPaper.getSid(), ansPaper.getAns(), ansPaper.getScore(),
				ansPaper.getSurplus());
	}

	@Override
	public int updateAnsPaper(AnsPaper ansPaper) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "update  AnsPaper set eid=?,sid=?,ans=?,score=?,surplus=? where aid=?";
		return db.update(sql, ansPaper.getEid(), ansPaper.getSid(), ansPaper.getAns(), ansPaper.getScore(),
				ansPaper.getSurplus(), ansPaper.getAid());
	}

	@Override
	public List<AnsPaper> findByCondition(String eid, String sid, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select * from AnsPaper where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(eid)) {
			sql += " and eid =?";
			params.add(eid);
		}
		if (!StringUtil.isNull(sid)) {
			sql += " and sid =?";
			params.add(sid);
		}
		sql += "order by aid) a where rownum<=?) where rn>?";
		params.add(pageNo * pageSize);
		params.add((pageNo - 1) * pageSize);
		return db.gets(sql, AnsPaper.class, params);
	}

	@Override
	public int getTotal(String eid, String sid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(aid) as total from AnsPaper where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(sid)) {
			sql += " and sid =?";
			params.add(sid);
		}
		return db.getTotal(sql, params);
	}

	@Override
	public AnsPaper findByAid(String aid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from AnsPaper where aid=? ";
		return db.find(sql, AnsPaper.class, aid);
	}

	@Override
	public AnsPaper findBySid(String sid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from AnsPaper where sid=? ";
		return db.find(sql, AnsPaper.class, sid);
	}

	@Override
	public int deleteAnsPaper(String aid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "delete from AnsPaper where aid=? ";
		return db.update(sql, aid);
	}

	@Override
	public List<StuInfo> findGrades(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select * from(select a.*,rownum rn from(select s.sid,e.eid,a.surplus,s.sname,c.cid,m.mname,c.grade,cs.cname,nvl(a.score,0)  score"
				+ " from StuInfo s,AnsPaper a,ExamPaper e,CourseInfo cs,ClassInfo c,MajorInfo m "
				+ " where s.sid =a.sid and a.eid = e.eid and e.cid = cs.cid and s.cid = c.cid and c.mid = m.mid ) a where rownum<=?) where rn>?";
		params.add(pageNo * pageSize);
		params.add((pageNo - 1) * pageSize);
		return db.finds(sql, StuInfo.class, params);
	}

	@Override
	public int getTotalGrade() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(a.aid) as total from StuInfo s,AnsPaper a,ExamPaper e,CourseInfo cs,ClassInfo c,MajorInfo m "
				+ " where s.sid =a.sid and a.eid = e.eid and e.cid = cs.cid and s.cid = c.cid and c.mid = m.mid ";
		return db.getTotal(sql);
	}

	@Override
	public AnsPaper findBySidAndEid(String sid, String eid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from AnsPaper where sid=? and eid=? ";
		return db.find(sql, AnsPaper.class, sid, eid);
	}

	@Override
	public List<AnsPaper> findByEid(String eid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select s.sid,s.sname,e.ename,c.cid,m.mname,c.grade,cs.cname,a.score from StuInfo s,"
				+ "AnsPaper a,ExamPaper e,CourseInfo cs,ClassInfo c,MajorInfo m "
				+" where s.sid =a.sid and a.eid = e.eid and e.cid = cs.cid and s.cid ="
				+ " c.cid and c.mid = m.mid and e.eid =? order by a.score desc";
		return db.finds(sql, AnsPaper.class, eid);

	}

	@Override
	public List<AnsPaper> findByCidAndEid(String cid,String eid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select s.sid,s.sname,e.ename,c.cid,c.cname,m.mname,c.grade,cs.cname,a.score from StuInfo s,"
				+ "AnsPaper a,ExamPaper e,CourseInfo cs,ClassInfo c,MajorInfo m "
				+" where s.sid =a.sid and a.eid = e.eid and e.cid = cs.cid and s.cid ="
				+ " c.cid and c.mid = m.mid and c.cid =? and a.eid =? order by a.score desc";
		return db.finds(sql, AnsPaper.class, cid,eid);
	}

	@Override
	public int getTotalCid(String cid ) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(s.sid) as total "
				+ " from StuInfo s,AnsPaper a,ExamPaper e,CourseInfo cs,ClassInfo c,MajorInfo m "
				+ " where s.sid =a.sid and a.eid = e.eid and e.cid = cs.cid and s.cid = c.cid and c.mid = m.mid ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(cid)) {
			sql += " and c.cid =?";
			params.add(cid);
		}
		
		return db.getTotal(sql, params);
	}
	@Override
	public List<AnsPaper> findByCid(String cid, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select s.sid,e.eid,a.surplus,s.sname,c.cid,m.mname,c.grade,cs.cname,nvl(a.score,0)  score"
				+ " from StuInfo s,AnsPaper a,ExamPaper e,CourseInfo cs,ClassInfo c,MajorInfo m "
				+ " where s.sid =a.sid and a.eid = e.eid and e.cid = cs.cid and s.cid = c.cid and c.mid = m.mid ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(cid)) {
			sql += " and c.cid =? ";
			params.add(cid);
		}
		
		sql += " ) a where rownum<=?) where rn>?";
		params.add(pageNo * pageSize);
		params.add((pageNo - 1) * pageSize);
		return db.gets(sql, AnsPaper.class, params);
	}

}
