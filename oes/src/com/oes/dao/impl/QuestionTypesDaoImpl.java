package com.oes.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.oes.bean.QuestionTypes;
import com.oes.dao.DBHelper;
import com.oes.dao.IQuestionTypesDao;
import com.oes.util.StringUtil;

public class QuestionTypesDaoImpl implements IQuestionTypesDao {

	@Override
	public List<QuestionTypes> findAll() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from  QuestionTypes order by tid";
		return db.finds(sql, QuestionTypes.class);
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(tid) from QuestionTypes ";
		return db.getTotal(sql);
	}

	@Override
	public List<QuestionTypes> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select * from QuestionTypes  "
				+ "order by tid) a where rownum<=?) where rn>?";
		return db.finds(sql, QuestionTypes.class, pageNo * pageSize, (pageNo - 1) * pageSize);
	}

	@Override
	public int getTotal(String tid, String tname) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select  count(tid) from QuestionTypes where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(tid)) {
			sql += " and tid =?";
			params.add(tid);
		}
		if (!StringUtil.isNull(tname)) {
			sql += " and tname like '%'||?||'%'";
			params.add(tname);
		}
		return db.getTotal(sql, params);
	}

	@Override
	public List<QuestionTypes> findByPage(int pageNo, int pageSize, String tid, String tname) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select * from QuestionTypes  where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(tid)) {
			sql += " and tid =?";
			params.add(tid);
		}
		if (!StringUtil.isNull(tname)) {
			sql += " and tname like'%'||?||'%'";
			params.add(tname);
		}

		params.add(pageNo * pageSize);
		params.add((pageNo - 1) * pageSize);
		sql += " order by  tid ) a   where rownum <=?) where rn>?";
		return db.finds(sql, QuestionTypes.class, params);
	}

}
