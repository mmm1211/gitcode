package com.oes.dao;

import java.util.List;

import com.oes.bean.AdminInfo;

public interface IAdminInfoDao {

	/**
	 * 获取所有管理员信息
	 * 
	 * @return
	 */
	public List<AdminInfo> finds();

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getTotal();

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<AdminInfo> findByPage(int pageNo, int pageSize);

	/**
	 * 添加
	 * 
	 * @param AdminInfo
	 * @return
	 */
	public int addAdminInfo(AdminInfo adminInfo);

	/**
	 * 修改
	 * 
	 * @param AdminInfo
	 * @return
	 */
	public int updateAdminInfo(AdminInfo adminInfo);

	/**
	 * 修改状态
	 * 
	 * @param AdminInfo
	 * @return
	 */
	public int updateAdminInfo(String aids, String status);

	/**
	 * 按指定条件分页查询
	 * 
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<AdminInfo> findByPage(int pageNo, int pageSize, String rid, String aname);

	/**
	 * 获取商品记录数
	 * 
	 * @param rid
	 * @param aname
	 * @return
	 */
	public int getTotal(String rid, String aname);

	/**
	 * 根据编号查询
	 * 
	 * @param aid
	 * @return
	 */
	public AdminInfo findByAid(String aid);

	/**
	 * 登录
	 * 
	 * @param account
	 * @param pwd
	 * @return
	 */
	public AdminInfo login(String account, String pwd);

}
