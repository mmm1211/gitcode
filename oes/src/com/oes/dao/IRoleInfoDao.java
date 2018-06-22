package com.oes.dao;

import java.util.List;

import com.oes.bean.RoleInfo;

public interface IRoleInfoDao {

	/**
	 * 获取所有角色信息
	 * 
	 * @return
	 */
	public List<RoleInfo> finds();

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
	public List<RoleInfo> findByPage(int pageNo, int pageSize);

	/**
	 * 添加管理员信息
	 * 
	 * @param RoleInfo
	 * @return
	 */
	public int addRoleInfo(RoleInfo roleInfo);

	/**
	 * 修改管理员信息
	 * 
	 * @param RoleInfo
	 * @return
	 */
	public int updateRoleInfo(RoleInfo roleInfo);

	/**
	 * 修改管理员状态
	 * 
	 * @param RoleInfo
	 * @return
	 */
	public int updateRoleInfo(String rids, String status);

	/**
	 * 根据编号查询
	 * 
	 * @param aid
	 * @return
	 */
	public RoleInfo findByRid(String rid);

}
