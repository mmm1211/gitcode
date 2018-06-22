package com.oes.dao.impl;

import java.util.List;

import com.oes.bean.MajorInfo;
import com.oes.dao.DBHelper;
import com.oes.dao.IMajorInfoDao;
import com.oes.util.StringUtil;

public class MajorInfoDaoImpl implements IMajorInfoDao {

	@Override
	public List<MajorInfo> finds() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from  MajorInfo order by mid";
		return db.finds(sql, MajorInfo.class);
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(mid) as total from MajorInfo ";
		return db.getTotal(sql);
	}

	@Override
	public List<MajorInfo> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select * from MajorInfo  "
				+ "order by mid) a where rownum<=?) where rn>?";
		return db.finds(sql, MajorInfo.class, pageNo * pageSize, (pageNo - 1) * pageSize);
	}

	@Override
	public int addMajorInfo(MajorInfo majorInfo) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "insert into MajorInfo values(seq_majorInfo_mid.nextval,?,1)";
		return db.update(sql, majorInfo.getMname());
	}

	@Override
	public int updateMajorInfo(MajorInfo majorInfo) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "update  MajorInfo set mname=? where mid=?";
		return db.update(sql, majorInfo.getMname(), majorInfo.getMid());
	}

	@Override
	public int updateMajorInfo(String mids, int status) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = null;
		if (!StringUtil.isNull(mids) && !mids.contains("or") && mids.contains(",")) { // 此处为了防止非法注入
			sql = "update  MajorInfo set status=? where mid in (" + mids + ") ";
		} else {
			sql = "update  MajorInfo set status=? where mid = ? ";
		}
		return db.update(sql, status, mids);
	}

}
