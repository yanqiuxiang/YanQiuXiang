package com.yqx.jurisdiction.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.yqx.jurisdiction.entity.Node;
/**
 * @author YQX
 * @content 节点管理
 * @time 2016年12月1日19:32:03
 * 
 * */

public interface NodeDao {
	
	//根据节点状态获取所有节点
	public List<Node> getAllNodeByAdmin(@Param("nodeStatus")int nodeStatus);
	
	//获取某一用户权限的节点
	public List<Node> getRoleNode(@Param("roleId")int roleId,@Param("nodeStatus")int nodeStatus);
	
	//查询节点
	public List<Node> getNodeByRole(@Param("roleId")int roleId,@Param("nodeStatus")int nodeStatus);
	
	/*  start  **/
	//获取所有模板信息
	public List<Node> getAllNode();
	
	//获取一级菜单节点
	public List<Node> getSysNodeTree();
	
	//增加模块
	public void addNode(Node node);
	
	//获取模块信息
	public Node getNodeById(@Param("node_id")int node_id);
	
	//修改模块信息
	public void updNode(Node node);
	
	//删除模块信息
	public String delNode(Map<String, Object> map);
	
	//修改节点状态
	public void updNodeStatus(@Param("node_id")int node_id,@Param("status")int status);
}
