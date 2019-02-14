package com.yqx.jurisdiction.business.impl;

import java.util.List;
import java.util.Map;
import com.yqx.jurisdiction.business.NodeMapper;
import com.yqx.jurisdiction.dao.NodeDao;
import com.yqx.jurisdiction.entity.Node;

public class NodeMapperImpl implements NodeMapper{
	
	
	NodeDao realNameNodeDao;
	
	public NodeMapperImpl(NodeDao realNameNodeDao){
		
		this.realNameNodeDao = realNameNodeDao;
	}
	
	public List<Node> getAllNodeByAdmin(int nodeStatus) {
		
		return realNameNodeDao.getAllNodeByAdmin(nodeStatus);
	}

	public List<Node> getRoleNode(int roleId, int nodeStatus) {
		
		return realNameNodeDao.getRoleNode(roleId, nodeStatus);
	}

	public List<Node> getNodeByRole(int roleId, int nodeStatus) {
		
		List<Node> nodes = null;
		if(roleId==0){
			nodes = getAllNodeByAdmin(nodeStatus);
		}else{
			nodes = getRoleNode(roleId, nodeStatus);
		}
		return nodes;
	}

	public List<Node> getAllNode() {
		
		return realNameNodeDao.getAllNode();
	}

	public List<Node> getSysNodeTree() {
		
		return realNameNodeDao.getSysNodeTree();
	}

	public void addNode(Node node) {
		
		realNameNodeDao.addNode(node);
	}

	public Node getNodeById(int node_id) {
		
		return realNameNodeDao.getNodeById(node_id);
	}

	public void updNode(Node node) {
		
		realNameNodeDao.updNode(node);
	}

	public String delNode(Map<String, Object> map) {
		
		return realNameNodeDao.delNode(map);
	}

	public void updNodeStatus(int node_id, int status) {
		
		realNameNodeDao.updNodeStatus(node_id, status);
	}
	
	
}
