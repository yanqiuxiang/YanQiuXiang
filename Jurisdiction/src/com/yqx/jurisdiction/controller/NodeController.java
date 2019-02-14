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
 * @content 后台节点管理
 * @author YQX
 * @time 2016年6月8日10:36:23
 * 
 * */
@Controller
@RequestMapping(value="/adminNode")
public class NodeController extends CotrollerBase{
	
	
	//后台节点界面
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
	
	//修改节点界面
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
				mav.addObject("title", node.getNODE_TITLE());//标题
				mav.addObject("url", node.getNODE_URL());//url地址
				mav.addObject("ico", node.getNODE_ICO());//图标
				mav.addObject("pid", node.getNODE_PID());//父ID
				mav.addObject("remark", node.getNODE_REMARK());//节点说明
				mav.addObject("sort",node.getNODE_SORT());//排序
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	//修改节点信息
	@RequestMapping(value="/updNode", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updNode(HttpServletRequest request){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			NodeMapper realNameNodeMapper = (NodeMapper) factory.getBusinessProduct("realNameNode");
			Node node = new Node();
			String nodeId = request.getParameter("nodeId");//节点编号
			String nodeTreeName = request.getParameter("nodeTreeName");//节点名称
			String sort = request.getParameter("sort");//排序
			String url = request.getParameter("url");//url
			String nodeTree = request.getParameter("nodeTree");//父节点ID
			String remark = request.getParameter("remark");//节点说明
			
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
	
	//删除节点
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
	
	//修改节点状态
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
	
	//循环获取所有节点信息
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
	
	//返回节点树
	public String returnNodeTree(List<Node> nodeList){
		List<Map<String, Object>> sys_maps = new ArrayList<Map<String,Object>>();//最上级菜单
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String, Object> sys_map = new HashMap<String, Object>();//存放二级菜单
		try {
			if(nodeList!=null && nodeList.size()>0){
				//存放一级菜单
				List<Node> paren_node = new ArrayList<Node>();
				for(Node  node : nodeList){
					
					if(node.getNODE_PID()==1){
						paren_node.add(node);
					}
					if(node.getNODE_PID()==0){
						sys_map.put("id", node.getNODE_ID());
						sys_map.put("name", node.getNODE_TITLE());//节点名称
						sys_map.put("icon", null==node.getNODE_ICO()?"":node.getNODE_ICO());//图标
						sys_map.put("url", null==node.getNODE_URL()?"":node.getNODE_URL());//节点路径
						sys_map.put("sort", node.getNODE_SORT());//排序
						sys_map.put("pName", null==node.getP_TITLE()?"":node.getP_TITLE());//上级节点
						sys_map.put("status", node.getNODE_STATUS());//状态
						sys_map.put("remark", node.getNODE_REMARK());//节点说明
					}
				 }

				for(Node node_child : paren_node){
				    List<Map<String, Object>> map4 = new ArrayList<Map<String,Object>>();//包裹每个一级菜单下的子菜单
				    Map<String, Object> map3 = new HashMap<String, Object>();//包裹每个一级菜单信息
				    map3.put("id", node_child.getNODE_ID());
				    map3.put("name", node_child.getNODE_TITLE());//节点名称
					map3.put("icon", null==node_child.getNODE_ICO()?"":node_child.getNODE_ICO());//图标
				    map3.put("url", null==node_child.getNODE_URL()?"":node_child.getNODE_URL());//节点路径
				    map3.put("sort", node_child.getNODE_SORT());//排序
				    map3.put("pName", null==node_child.getP_TITLE()?"":node_child.getP_TITLE());//上级节点
				    map3.put("status", node_child.getNODE_STATUS());//状态
				    map3.put("remark", node_child.getNODE_REMARK());//节点说明
				    
					for(Node  node : nodeList){
						if(node.getNODE_PID() == node_child.getNODE_ID()){
							Map<String, Object> mapi = new HashMap<String, Object>();//包裹每个子菜单信息
							mapi.put("id", node.getNODE_ID());
							mapi.put("name", node.getNODE_TITLE());//节点名称
							mapi.put("icon", null==node.getNODE_ICO()?"":node.getNODE_ICO());//图标
							mapi.put("url", null==node.getNODE_URL()?"":node.getNODE_URL());//节点路径
							mapi.put("sort", node.getNODE_SORT());//排序
							mapi.put("pName", null==node.getP_TITLE()?"":node.getP_TITLE());//上级节点
							mapi.put("status", node.getNODE_STATUS());//状态
							mapi.put("remark", node.getNODE_REMARK());//节点说明
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

	//获取一级菜单节点
	@RequestMapping(value="/getSysNodeTree", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getSysNodeTree(){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();//返回到前端
		try {
			NodeMapper realNameNodeMapper = (NodeMapper) factory.getBusinessProduct("realNameNode");

			List<Node> nodeTree = realNameNodeMapper.getSysNodeTree();
			if(null!=nodeTree && nodeTree.size()>0){
				List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
				for(Node node : nodeTree){
					Map<String, Object> mapi = new HashMap<String, Object>();//包裹每个子菜单信息
					mapi.put("nodeId", node.getNODE_ID());
					mapi.put("nodeTitle", node.getNODE_TITLE());
					mapList.add(mapi);
				}
				map.put("status", "true");
				map.put("list", mapList);
			}else{
				map.put("status", "false");
				map.put("errorInfo", "数据不存在");
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
			node.setNODE_ICO("");//图标
			node.setNODE_PID(Integer.parseInt(nodeTree));////父节点ID
			node.setNODE_TITLE(nodeTreeName);//节点标题
			node.setNODE_URL(url);//url
			node.setNODE_SORT(Integer.parseInt(sort));//节点排序
			node.setNODE_STATUS(0);//状态 
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
