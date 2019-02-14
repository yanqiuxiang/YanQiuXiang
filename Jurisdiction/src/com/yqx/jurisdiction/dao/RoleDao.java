package com.yqx.jurisdiction.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.yqx.jurisdiction.entity.Node;
import com.yqx.jurisdiction.entity.Role;


public interface RoleDao {
	
	//获取所有角色及相关权限
	public List<Role> getRealNameRole();
	
	//获取状态为开启的角色
	public List<Role> getRealNameByStatus();
	
	//获取角色所 能访问资源
	public List<Node> getNodeByRole(@Param("role")int role);
	
	//新增角色
	public String addRole(Map<String, Object> map);
	
	//删除角色
	public String delRole(Map<String, Object> map);
	
	//更改角色状态
	public void updRole(@Param("status")int status,@Param("roleId")int roleId);
	
	//获取角色信息
	public Role getRoleById(@Param("roleId")int roleId);
	
	//修改角色信息
	public String updRoleById(Map<String, Object> map);
}
