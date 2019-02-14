package com.yqx.jurisdiction.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.yqx.jurisdiction.business.NodeMapper;
import com.yqx.jurisdiction.entity.Node;
import com.yqx.jurisdiction.entity.Users;
import com.yqx.jurisdiction.util.ResultToJackson;

/***
 * @content ��̨�ڵ����
 * @author YQX
 * @time 2016��6��8��10:36:23
 * 
 * */
@Controller
@RequestMapping(value="/adminNode")
public class NodeController extends CotrollerBase{
	
	
	//��̨�ڵ����
	@RequestMapping(value="/nodeView", method=RequestMethod.GET)
	public ModelAndView adminNodeView(HttpServletRequest request,HttpSession session){
		ModelAndView mav =null;
		try {
			mav = new ModelAndView("/nodeManage/nodeManage");
			
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("/login");
			mav.addObject("reminder", e.getMessage());
		}
		return mav;
	}
	
	//�޸Ľڵ����
	@RequestMapping(value="/getEditNodeView", method=RequestMethod.GET)
	public ModelAndView getEditNodeView(HttpServletRequest request){
		ModelAndView mav =null;
		try {
			String nodeId = request.getParameter("nodeId");
			
			NodeMapper realNameNodeMapper = (NodeMapper) factory.getBusinessProduct("realNameNode");
			
			Node node = realNameNodeMapper.getNodeById(Integer.parseInt(nodeId));
			if(null!=node){
				mav = new ModelAndView("/nodeManage/editNode");
				mav.addObject("nodeId", node.getNODE_ID());//ID
				mav.addObject("title", node.getNODE_TITLE());//����
				mav.addObject("url", node.getNODE_URL());//url��ַ
				mav.addObject("ico", node.getNODE_ICO());//ͼ��
				mav.addObject("pid", node.getNODE_PID());//��ID
				mav.addObject("remark", node.getNODE_REMARK());//�ڵ�˵��
				mav.addObject("sort",node.getNODE_SORT());//����
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	//�޸Ľڵ���Ϣ
	@RequestMapping(value="/updNode", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updNode(HttpServletRequest request){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			NodeMapper realNameNodeMapper = (NodeMapper) factory.getBusinessProduct("realNameNode");
			Node node = new Node();
			String nodeId = request.getParameter("nodeId");//�ڵ���
			String nodeTreeName = request.getParameter("nodeTreeName");//�ڵ�����
			String sort = request.getParameter("sort");//����
			String url = request.getParameter("url");//url
			String nodeTree = request.getParameter("nodeTree");//���ڵ�ID
			String remark = request.getParameter("remark");//�ڵ�˵��
			
			node.setNODE_ID(Integer.parseInt(nodeId));
			node.setNODE_TITLE(nodeTreeName);
			node.setNODE_SORT(Integer.parseInt(sort));
			node.setNODE_URL(url);
			node.setNODE_PID(Integer.parseInt(nodeTree));
			node.setNODE_REMARK(remark);
			
			realNameNodeMapper.updNode(node);
			
			map.put("status", "true");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo", e.getMessage());
		}
		return result.getResultFromMap(map);
	}
	
	//ɾ���ڵ�
	@RequestMapping(value="/delNode", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delNode(HttpServletRequest request){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String nodeId = request.getParameter("nodeId");
			
			Map<String, Object> mapCall = new HashMap<String, Object>();
			mapCall.put("nodeId", nodeId);
			mapCall.put("v_msg", "");
			NodeMapper realNameNodeMapper = (NodeMapper) factory.getBusinessProduct("realNameNode");
			realNameNodeMapper.delNode(mapCall);
			String v_msg=(String)mapCall.get("v_msg");
			map.put("status", "true");
			map.put("msg", v_msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo", e.getMessage());
			
		}
		return result.getResultFromMap(map);
	}
	
	//�޸Ľڵ�״̬
	@RequestMapping(value="/updNodeStatus", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updNodeStatus(HttpServletRequest request){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String node_id = request.getParameter("node_id");
			String status = request.getParameter("status");
			NodeMapper realNameNodeMapper = (NodeMapper) factory.getBusinessProduct("realNameNode");
			realNameNodeMapper.updNodeStatus(Integer.parseInt(node_id), Integer.parseInt(status));
			map.put("status", "true");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo", e.getMessage());
		}
		
		return result.getResultFromMap(map);
	}
	
	//ѭ����ȡ���нڵ���Ϣ
	@RequestMapping(value="/nodeTreeJson", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String nodeTreeJson(HttpSession session){
		String treeData="";
		try {
			Users users =  (Users) session.getAttribute("users");
			if(null!=users){
				NodeMapper realNameNodeMapper = (NodeMapper) factory.getBusinessProduct("realNameNode");
				List<Node> nodeList = realNameNodeMapper.getAllNode();
				if(null!=nodeList  && nodeList.size()>0){
					treeData = returnNodeTree(nodeList);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return treeData;
	}
	
	//���ؽڵ���
	public String returnNodeTree(List<Node> nodeList){
		List<Map<String, Object>> sys_maps = new ArrayList<Map<String,Object>>();//���ϼ��˵�
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String, Object> sys_map = new HashMap<String, Object>();//��Ŷ����˵�
		try {
			if(nodeList!=null && nodeList.size()>0){
				//���һ���˵�
				List<Node> paren_node = new ArrayList<Node>();
				for(Node  node : nodeList){
					
					if(node.getNODE_PID()==1){
						paren_node.add(node);
					}
					if(node.getNODE_PID()==0){
						sys_map.put("id", node.getNODE_ID());
						sys_map.put("name", node.getNODE_TITLE());//�ڵ�����
						sys_map.put("icon", null==node.getNODE_ICO()?"":node.getNODE_ICO());//ͼ��
						sys_map.put("url", null==node.getNODE_URL()?"":node.getNODE_URL());//�ڵ�·��
						sys_map.put("sort", node.getNODE_SORT());//����
						sys_map.put("pName", null==node.getP_TITLE()?"":node.getP_TITLE());//�ϼ��ڵ�
						sys_map.put("status", node.getNODE_STATUS());//״̬
						sys_map.put("remark", node.getNODE_REMARK());//�ڵ�˵��
					}
				 }

				for(Node node_child : paren_node){
				    List<Map<String, Object>> map4 = new ArrayList<Map<String,Object>>();//����ÿ��һ���˵��µ��Ӳ˵�
				    Map<String, Object> map3 = new HashMap<String, Object>();//����ÿ��һ���˵���Ϣ
				    map3.put("id", node_child.getNODE_ID());
				    map3.put("name", node_child.getNODE_TITLE());//�ڵ�����
					map3.put("icon", null==node_child.getNODE_ICO()?"":node_child.getNODE_ICO());//ͼ��
				    map3.put("url", null==node_child.getNODE_URL()?"":node_child.getNODE_URL());//�ڵ�·��
				    map3.put("sort", node_child.getNODE_SORT());//����
				    map3.put("pName", null==node_child.getP_TITLE()?"":node_child.getP_TITLE());//�ϼ��ڵ�
				    map3.put("status", node_child.getNODE_STATUS());//״̬
				    map3.put("remark", node_child.getNODE_REMARK());//�ڵ�˵��
				    
					for(Node  node : nodeList){
						if(node.getNODE_PID() == node_child.getNODE_ID()){
							Map<String, Object> mapi = new HashMap<String, Object>();//����ÿ���Ӳ˵���Ϣ
							mapi.put("id", node.getNODE_ID());
							mapi.put("name", node.getNODE_TITLE());//�ڵ�����
							mapi.put("icon", null==node.getNODE_ICO()?"":node.getNODE_ICO());//ͼ��
							mapi.put("url", null==node.getNODE_URL()?"":node.getNODE_URL());//�ڵ�·��
							mapi.put("sort", node.getNODE_SORT());//����
							mapi.put("pName", null==node.getP_TITLE()?"":node.getP_TITLE());//�ϼ��ڵ�
							mapi.put("status", node.getNODE_STATUS());//״̬
							mapi.put("remark", node.getNODE_REMARK());//�ڵ�˵��
					    	map4.add(mapi);
					    	map3.put("children", map4);
						}
					}
					maps.add(map3);
				}
				sys_map.put("children", maps);
				sys_maps.add(sys_map);
			}else{
				
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JSONArray.fromObject(sys_maps).toString();
	}

	//��ȡһ���˵��ڵ�
	@RequestMapping(value="/getSysNodeTree", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getSysNodeTree(){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();//���ص�ǰ��
		try {
			NodeMapper realNameNodeMapper = (NodeMapper) factory.getBusinessProduct("realNameNode");

			List<Node> nodeTree = realNameNodeMapper.getSysNodeTree();
			if(null!=nodeTree && nodeTree.size()>0){
				List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
				for(Node node : nodeTree){
					Map<String, Object> mapi = new HashMap<String, Object>();//����ÿ���Ӳ˵���Ϣ
					mapi.put("nodeId", node.getNODE_ID());
					mapi.put("nodeTitle", node.getNODE_TITLE());
					mapList.add(mapi);
				}
				map.put("status", "true");
				map.put("list", mapList);
			}else{
				map.put("status", "false");
				map.put("errorInfo", "���ݲ�����");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status","false");
			map.put("errof_info",e.getMessage());
		}
		return result.getResultFromMap(map);
	}
	
	@RequestMapping(value="/addNode", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addNode(HttpServletRequest request){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String nodeTreeName = request.getParameter("nodeTreeName");
			String sort = request.getParameter("sort");
			String url = request.getParameter("url");
			String nodeTree = request.getParameter("nodeTree");
			String remark = request.getParameter("remark");
			NodeMapper realNameNodeMapper = (NodeMapper) factory.getBusinessProduct("realNameNode");
			Node node = new Node();
			node.setNODE_ICO("");//ͼ��
			node.setNODE_PID(Integer.parseInt(nodeTree));////���ڵ�ID
			node.setNODE_TITLE(nodeTreeName);//�ڵ����
			node.setNODE_URL(url);//url
			node.setNODE_SORT(Integer.parseInt(sort));//�ڵ�����
			node.setNODE_STATUS(0);//״̬ 
			node.setNODE_REMARK(null==remark?"":remark);
			realNameNodeMapper.addNode(node);
			map.put("status", "true");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo", e.getMessage());
		}
		return result.getResultFromMap(map);
	}
		
}
