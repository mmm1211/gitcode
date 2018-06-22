package com.oes.biz;

import java.util.List;
import java.util.Map;

import com.oes.bean.RoleInfo;

public interface IRoleInfoBiz {

	/**
	 * 获取所有用户信息
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
	 * 
	 */
	public Map<String, Object> findByPages(int pageNo, int pageSize);

	/**
	 * 添加
	 * 
	 * @param RoleInfo
	 * @return
	 */
	public int addRoleInfo(RoleInfo roleInfo);

	/**
	 * 修改
	 * 
	 * @param RoleInfo
	 * @return
	 */
	public int updateRoleInfo(RoleInfo roleInfo);

	/**
	 * 修改状态
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
