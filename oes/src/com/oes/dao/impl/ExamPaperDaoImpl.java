package com.oes.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.oes.bean.ExamPaper;
import com.oes.dao.DBHelper;
import com.oes.dao.IExamPaperDao;
import com.oes.util.StringUtil;

public class ExamPaperDaoImpl implements IExamPaperDao {

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(eid)   from ExamPaper ";
		return db.getTotal(sql);
	}

	@Override
	public List<ExamPaper> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select eid,ename,cid,to_char(examTime,'yyyy-mm-dd HH24:mi'),examTime,conTime,cids,subjects,score,status"
				+ " from ExamPaper  order by examTime desc,cid asc) a where rownum<=?) where rn>?";
		return db.finds(sql, ExamPaper.class, pageNo * pageSize, (pageNo - 1) * pageSize);
	}

	@Override
	public int addExamPaper(ExamPaper examPaper) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "insert into ExamPaper values(seq_examPaper_eid.nextval,?,?,?,to_date(?,'YYYY-mm-dd HH24:mi'),?,?,?,1)";
		return db.update(sql, examPaper.getEname(), examPaper.getCid(), examPaper.getConTime(), examPaper.getExamTime(),
				examPaper.getCids(), examPaper.getSubjects(), examPaper.getScore());
	}

	@Override
	public int updateExamPaper(ExamPaper examPaper) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "update  ExamPaper set ename=?,cid=?,conTime=?,examTime=to_date(?,'YYYY-mm-dd HH24:mi'),cids=?,subjects=?,score=? where eid=?";
		return db.update(sql, examPaper.getEname(), examPaper.getCid(), examPaper.getConTime(), examPaper.getExamTime(),
				examPaper.getCids(), examPaper.getSubjects(), examPaper.getScore(), examPaper.getEid());
	}

	@Override
	public List<Map<String, String>> findQuestionByCid(String cid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select qid||'-'||ans||'-'||tid qid,tid from questions where cid =?";
		return db.finds(sql, cid);
	}

	@Override
	public ExamPaper findExamPaperByEid(String eid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from ExamPaper  where eid =?";
		return db.find(sql, ExamPaper.class, eid);
	}

	@Override
	public List<ExamPaper> findByCid(String cid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from ExamPaper  where cid =?";
		return db.finds(sql, ExamPaper.class, cid);
	}

	public List<ExamPaper> findByCondition(String cid, String cids, String status, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select * from ExamPaper e where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(cid)) {
			sql += " and cid =?";
			params.add(cid);
		}
		if (!StringUtil.isNull(cids)) {
			cids += ",";
			sql += " and instr(cids,?)>0";
			params.add(cids);
		}
		if (!StringUtil.isNull(status)) {
			sql += " and status =?";
			params.add(status);
		}
		params.add(pageNo * pageSize);
		params.add((pageNo - 1) * pageSize);
		sql += " order by  eid ) a   where rownum <=?) where rn>?";
		return db.finds(sql, ExamPaper.class, params);
	}

	@Override
	public int getTotal(String cid, String cids, String status) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(eid) from ExamPaper where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(cid)) {
			sql += " and cid =?";
			params.add(cid);
		}
		if (!StringUtil.isNull(cids)) {
			cids += ",";
			sql += " and instr(cids,?)>0";
			params.add(cids);
		}
		if (!StringUtil.isNull(status)) {
			sql += " and status =?";
			params.add(status);
		}

		return db.getTotal(sql, params);
	}

	@Override
	public int updateExamPaper(String eid, String status) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "update ExamPaper  set status =? where eid =?";
		return db.update(sql, status, eid);
	}

	@Override
	public int deleteExamPaper(String eid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "delete from  ExamPaper   where eid =?";
		return db.update(sql, eid);
	}

	@Override
	public List<ExamPaper> finds() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select *  from ExamPaper ";
		return db.finds(sql, ExamPaper.class);
	}

}
