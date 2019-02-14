package com.yqx.jurisdiction.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.yqx.jurisdiction.entity.Node;
import com.yqx.jurisdiction.entity.Role;


public interface RoleDao {
	
	//��ȡ���н�ɫ�����Ȩ��
	public List<Role> getRealNameRole();
	
	//��ȡ״̬Ϊ�����Ľ�ɫ
	public List<Role> getRealNameByStatus();
	
	//��ȡ��ɫ�� �ܷ�����Դ
	public List<Node> getNodeByRole(@Param("role")int role);
	
	//������ɫ
	public String addRole(Map<String, Object> map);
	
	//ɾ����ɫ
	public String delRole(Map<String, Object> map);
	
	//���Ľ�ɫ״̬
	public void updRole(@Param("status")int status,@Param("roleId")int roleId);
	
	//��ȡ��ɫ��Ϣ
	public Role getRoleById(@Param("roleId")int roleId);
	
	//�޸Ľ�ɫ��Ϣ
	public String updRoleById(Map<String, Object> map);
}
