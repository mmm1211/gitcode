package com.oes.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.oes.bean.AdminInfo;
import com.oes.dao.DBHelper;
import com.oes.dao.IAdminInfoDao;
import com.oes.util.MD5Encryption;
import com.oes.util.StringUtil;

public class AdminInfoDaoImpl implements IAdminInfoDao {

	@Override
	public List<AdminInfo> finds() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from  AdminInfo order by status desc,aid asc";
		return db.finds(sql, AdminInfo.class);
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(aid)  from AdminInfo ";
		return db.getTotal(sql);
	}

	@Override
	public List<AdminInfo> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*,rownum rn from(select * from AdminInfo  "
				+ "order by aid) a where rownum<=?) where rn>?";
		return db.finds(sql, AdminInfo.class, pageNo * pageSize, (pageNo - 1) * pageSize);
	}

	@Override
	public int addAdminInfo(AdminInfo adminInfo) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "insert into AdminInfo values(seq_adminInfo_aid.nextval,?,?,?,?,?,1)";
		return db.update(sql, adminInfo.getRid(), adminInfo.getAname(), adminInfo.getPwd(), adminInfo.getEmail(),
				adminInfo.getPhoto());
	}

	@Override
	public int updateAdminInfo(AdminInfo adminInfo) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "update  AdminInfo set rid=?,aname=?,pwd=?,email=?,photo=? where aid=?";
		return db.update(sql, adminInfo.getRid(), adminInfo.getAname(), adminInfo.getPwd(), adminInfo.getEmail(),
				adminInfo.getPhoto(), adminInfo.getAid());
	}

	@Override
	public int updateAdminInfo(String aids, String status) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = null;
		if (aids.contains(",") && !aids.contains("or")) {
			sql = "update  AdminInfo set status=? where aid in (" + aids + ") ";
		} else {
			sql = "update  AdminInfo set status=? where aid = ?";
		}

		return db.update(sql, status, aids);
	}

	@Override
	public List<AdminInfo> findByPage(int pageNo, int pageSize, String rid, String aname) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from ( select a.*,rownum rn from ( select * from AdminInfo  where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(rid)) {
			sql += " and rid = ?";
			params.add(rid);
		}
		if (!StringUtil.isNull(aname)) {
			sql += " and aname like '%'|| ? ||'%'";
			params.add(aname);
		}
		params.add(pageNo * pageSize);
		params.add((pageNo - 1) * pageSize);
		sql += " order by  status desc) a   where rownum <=?) where rn>?";
		return db.finds(sql, AdminInfo.class, params);
	}

	@Override
	public int getTotal(String rid, String aname) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select count(aid) from AdminInfo where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(rid)) {
			sql += " and rid = ?";
			params.add(rid);
		}
		if (!StringUtil.isNull(aname)) {
			sql += " and aname like '%'|| ? ||'%'";
			params.add(aname);
		}
		return db.getTotal(sql, params);
	}

	@Override
	public AdminInfo findByAid(String aid) {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper();
		String sql = "select * from AdminInfo where aid=?";
		return db.find(sql, AdminInfo.class, aid);
	}

	@Override
	public AdminInfo login(String account, String pwd) {
		// TODO Auto-generated method stub
		pwd = MD5Encryption.createPassword(pwd);
		DBHelper db = new DBHelper();
		String sql = "select * from AdminInfo where  aid=? and pwd =? ";
		return db.find(sql, AdminInfo.class, account, pwd);
	}

}
