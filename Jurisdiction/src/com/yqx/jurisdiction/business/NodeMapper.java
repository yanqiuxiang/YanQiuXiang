package com.yqx.jurisdiction.business;
import java.util.List;
import java.util.Map;

import com.yqx.jurisdiction.entity.Node;


public interface NodeMapper {
	//���ݽڵ�״̬��ȡ���нڵ�
	public List<Node> getAllNodeByAdmin(int nodeStatus);
	
	//��ȡĳһ�û�Ȩ�޵Ľڵ�
	public List<Node> getRoleNode(int roleId,int nodeStatus);
	
	//��ѯ�ڵ�
	public List<Node> getNodeByRole(int roleId,int nodeStatus);
	
	
	/*  start  **/
	//��ȡ����ģ����Ϣ
	public List<Node> getAllNode();
	
	//��ȡһ���˵��ڵ�
	public List<Node> getSysNodeTree();
	
	//����ģ��
	public void addNode(Node node);
	
	//��ȡģ����Ϣ
	public Node getNodeById(int node_id);
	
	//�޸�ģ����Ϣ
	public void updNode(Node node);
	
	//ɾ��ģ����Ϣ
	public String delNode(Map<String, Object> map);
	
	//�޸Ľڵ�״̬
	public void updNodeStatus(int node_id,int status);
}
