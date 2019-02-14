package com.yqx.jurisdiction.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.yqx.jurisdiction.entity.Node;
/**
 * @author YQX
 * @content �ڵ����
 * @time 2016��12��1��19:32:03
 * 
 * */

public interface NodeDao {
	
	//���ݽڵ�״̬��ȡ���нڵ�
	public List<Node> getAllNodeByAdmin(@Param("nodeStatus")int nodeStatus);
	
	//��ȡĳһ�û�Ȩ�޵Ľڵ�
	public List<Node> getRoleNode(@Param("roleId")int roleId,@Param("nodeStatus")int nodeStatus);
	
	//��ѯ�ڵ�
	public List<Node> getNodeByRole(@Param("roleId")int roleId,@Param("nodeStatus")int nodeStatus);
	
	/*  start  **/
	//��ȡ����ģ����Ϣ
	public List<Node> getAllNode();
	
	//��ȡһ���˵��ڵ�
	public List<Node> getSysNodeTree();
	
	//����ģ��
	public void addNode(Node node);
	
	//��ȡģ����Ϣ
	public Node getNodeById(@Param("node_id")int node_id);
	
	//�޸�ģ����Ϣ
	public void updNode(Node node);
	
	//ɾ��ģ����Ϣ
	public String delNode(Map<String, Object> map);
	
	//�޸Ľڵ�״̬
	public void updNodeStatus(@Param("node_id")int node_id,@Param("status")int status);
}
