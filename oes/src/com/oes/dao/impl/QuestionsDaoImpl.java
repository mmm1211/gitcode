package com.oes.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.oes.bean.Questions;
import com.oes.dao.DBHelper;
import com.oes.dao.IQuestionsDao;
import com.oes.util.StringUtil;

public class QuestionsDaoImpl implements IQuestionsDao {

	@Override
	public int addQuestions(Questions questions) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "insert into Questions(qname,tid,cid,ans1,ans2,ans3,ans4,ans,status) values(?,?,?,?,?,?,?,?,1)";
		return db.update(sql, questions.getQname(), questions.getTid(), questions.getCid(), questions.getAns1(),
				questions.getAns2(), questions.getAns3(), questions.getAns4(), questions.getAns());
	}

	@Override
	public int addQuestions(List<List<String>> list) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();

		String sql = "insert into Questions values(seq_questions_qid.nextval,?,?,?,?,?,?,?,?,1)";
		return db.updates(sql, list);
	}

	@Override
	public List<Questions> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select * from Questions  "
				+ "order by qid) a where rownum<=?) where rn>?";
		return db.finds(sql, Questions.class, pageNo * pageSize, (pageNo - 1) * pageSize);
	}

	@Override
	public List<Questions> findByCondition(String tid, String cid, String qname, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select * from Questions where 1=1";
		List<Object> params = new ArrayList<Object>();

		if (!StringUtil.isNull(tid)) {
			sql += " and tid =?";
			params.add(tid);
		}
		if (!StringUtil.isNull(cid)) {
			sql += " and cid = ?";
			params.add(cid);
		}
		if (!StringUtil.isNull(qname)) {
			sql += " and qname like'%'||?||'%'";
			params.add(qname);
		}
		sql += "order by qid) a where rownum<=?) where rn>?";
		params.add(pageNo * pageSize);
		params.add((pageNo - 1) * pageSize);

		return db.gets(sql, Questions.class, params);
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(qid) from Questions ";
		return db.getTotal(sql);
	}

	@Override
	public int getTotal(String tid, String cid, String qname) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(qid) from Questions where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(tid)) {
			sql += " and tid =?";
			params.add(tid);
		}
		if (!StringUtil.isNull(cid)) {
			sql += " and cid = ?";
			params.add(cid);
		}
		if (!StringUtil.isNull(qname)) {
			sql += " and qname like'%'||?||'%'";
			params.add(qname);
		}
		return db.getTotal(sql, params);
	}

	@Override
	public int updateQuestions(Questions questions) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "update  Questions set qname=?,tid=?,cid=?,ans1=?,ans2=?,ans3=?,ans4=?,ans=? where qid=?";
		return db.update(sql, questions.getQname(), questions.getTid(), questions.getCid(), questions.getAns1(),
				questions.getAns2(), questions.getAns3(), questions.getAns4(), questions.getAns(), questions.getQid());
	}

	@Override
	public List<Map<String, String>> getTotals(String cid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select nvl(count(qid),0) total,tid from Questions where cid=? group by tid";
		return db.finds(sql, cid);
	}

	@Override
	public Questions findByQid(String qid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from Questions where qid=?";
		return db.find(sql, Questions.class, qid);
	}

	@Override
	public List<Questions> findByQids(String qids) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from Questions where qid in (" + qids + ") order by tid";
		return db.finds(sql, Questions.class);
	}

	@Override
	public int updateQuestion(String qids, String status) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		if (!StringUtil.isNull(qids) && !qids.contains(" or ")) {
			String sql = "update Questions set status = ? where qid in(" + qids + ")";
			return db.update(sql, status);
		} else {
			String sql = "update Questions set status = ? where qid like " + qids + "";
			return db.update(sql, status, qids);
		}
	}

}
