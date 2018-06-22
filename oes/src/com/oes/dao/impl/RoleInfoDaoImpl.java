package com.oes.dao.impl;

import java.util.List;

import com.oes.bean.RoleInfo;
import com.oes.dao.DBHelper;
import com.oes.dao.IRoleInfoDao;

public class RoleInfoDaoImpl implements IRoleInfoDao {

	@Override
	public List<RoleInfo> finds() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from  RoleInfo";
		return db.finds(sql, RoleInfo.class);
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(rid) as total from RoleInfo ";
		return db.getTotal(sql);
	}

	@Override
	public List<RoleInfo> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select * from RoleInfo  "
				+ "order by rid) a where rownum<=?) where rn>?";
		return db.finds(sql, RoleInfo.class, pageNo * pageSize, (pageNo - 1) * pageSize);
	}

	@Override
	public int addRoleInfo(RoleInfo roleInfo) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "insert into RoleInfo values(seq_roleInfo_rid.nextval,?,1)";
		return db.update(sql, roleInfo.getRname());
	}

	@Override
	public int updateRoleInfo(RoleInfo roleInfo) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "update  AdminInfo set rname=? where rid=?";
		return db.update(sql, roleInfo.getRname(), roleInfo.getRid());
	}

	@Override
	public int updateRoleInfo(String rids, String status) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = null;
		if (rids.contains(",") && !rids.contains("or")) {
			sql = "update  RoleInfo set status=? where rid in (" + rids + ") ";
		} else {
			sql = "update  RoleInfo set status=? where rid = ? ";
		}
		return db.update(sql, rids);
	}

	@Override
	public RoleInfo findByRid(String rid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from RoleInfo where rid=?";
		return db.find(sql, RoleInfo.class, rid);
	}

}
