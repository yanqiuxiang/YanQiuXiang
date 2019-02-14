package com.yqx.jurisdiction.business.impl;

import java.util.List;
import java.util.Map;

import com.yqx.jurisdiction.business.RoleMapper;
import com.yqx.jurisdiction.dao.RoleDao;
import com.yqx.jurisdiction.entity.Node;
import com.yqx.jurisdiction.entity.Role;

public class RoleMapperImpl implements RoleMapper{
	
	RoleDao realNameRoleDao;
	
	public RoleMapperImpl(RoleDao realNameRoleDao){
		
		this.realNameRoleDao = realNameRoleDao;
	}
	

	public List<Role> getRealNameByStatus() {
		
		return realNameRoleDao.getRealNameByStatus();
	}

	public List<Node> getNodeByRole(int role) {
		
		return realNameRoleDao.getNodeByRole(role);
	}

	public String addRole(Map<String, Object> map) {
		
		return realNameRoleDao.addRole(map);
	}

	public String delRole(Map<String, Object> map) {
		
		return realNameRoleDao.delRole(map);
	}

	public void updRole(int status, int roleId) {
		
		realNameRoleDao.updRole(status, roleId);
	}

	public Role getRoleById(int roleId) {
		
		return realNameRoleDao.getRoleById(roleId);
	}

	public String updRoleById(Map<String, Object> map) {
		
		return realNameRoleDao.updRoleById(map);
	}


	public List<Role> getRealNameRole() {
	
		return realNameRoleDao.getRealNameRole();
	}

	
	
}
