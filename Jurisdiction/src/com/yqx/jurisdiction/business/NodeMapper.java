package com.yqx.jurisdiction.business;
import java.util.List;
import java.util.Map;

import com.yqx.jurisdiction.entity.Node;


public interface NodeMapper {
	//根据节点状态获取所有节点
	public List<Node> getAllNodeByAdmin(int nodeStatus);
	
	//获取某一用户权限的节点
	public List<Node> getRoleNode(int roleId,int nodeStatus);
	
	//查询节点
	public List<Node> getNodeByRole(int roleId,int nodeStatus);
	
	
	/*  start  **/
	//获取所有模板信息
	public List<Node> getAllNode();
	
	//获取一级菜单节点
	public List<Node> getSysNodeTree();
	
	//增加模块
	public void addNode(Node node);
	
	//获取模块信息
	public Node getNodeById(int node_id);
	
	//修改模块信息
	public void updNode(Node node);
	
	//删除模块信息
	public String delNode(Map<String, Object> map);
	
	//修改节点状态
	public void updNodeStatus(int node_id,int status);
}
