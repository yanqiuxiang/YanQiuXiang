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
import com.yqx.jurisdiction.business.RoleMapper;
import com.yqx.jurisdiction.entity.Node;
import com.yqx.jurisdiction.entity.Role;
import com.yqx.jurisdiction.entity.Users;
import com.yqx.jurisdiction.util.ResultToJackson;

/***
 * @author YQX
 * @content  后台角色权限管理
 * @time 2016年6月17日15:14:31
 * 
 * */
@Controller
@RequestMapping(value="/adminRole")
public class AdminRoleController extends CotrollerBase{
	
	//角色管理界面
	@RequestMapping(value="/roleView", method=RequestMethod.GET)
	public ModelAndView adminNodeView(HttpServletRequest request,HttpSession session){
		ModelAndView mav =null;
		try {
			Users users =  (Users) session.getAttribute("users");
			if(null==users){
				mav = new ModelAndView("/netError");
			}else{
				mav = new ModelAndView("/roleManage/roleManage");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("/login");
			mav.addObject("reminder", e.getMessage());
		}
		return mav;
	}
	
	//获取除系统管理员外的角色
	@RequestMapping(value="/getRoleInfo", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getRoleInfo(){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			RoleMapper realNameRoleMapper = (RoleMapper) this.factory.getBusinessProduct("realNameRole");
			
			List<Role> roleList = realNameRoleMapper.getRealNameRole();
			if(null!=roleList && roleList.size()>0){
				List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
				for(Role role : roleList){
					Map<String, Object> role_map = new HashMap<String, Object>();
					role_map.put("roleid", role.getROLE_ID());
					role_map.put("rolename", role.getROLE_NAME());
					role_map.put("roleaccess", role.getROLE_ACCESS());
					role_map.put("roleremark", role.getROLE_REMARK());
					role_map.put("rolestatus", role.getROLE_STATUS());
					maps.add(role_map);
				}
				map.put("total", roleList.size());
				map.put("rows", maps);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.getResultFromMap(map);
	}
	
	//获取角色用户所能访问的节点
	@RequestMapping(value="/getRoleTree", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getRoleTree(HttpServletRequest request){
		String treeData="";
		try {
			NodeMapper realNameNodeMapper =  (NodeMapper) this.factory.getBusinessProduct("realNameNode"); 
			List<Node> roleList = realNameNodeMapper.getAllNode();
			if(null!=roleList && roleList.size()>0){
				treeData = returnNodeTree(roleList);
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
						sys_map.put("text", node.getNODE_TITLE());//节点名称
						if(node.getR_STATUS()==1){
							sys_map.put("checked", true);
						}
					}
				 }
				for(Node node_child : paren_node){
				    List<Map<String, Object>> map4 = new ArrayList<Map<String,Object>>();//包裹每个一级菜单下的子菜单
				    Map<String, Object> map3 = new HashMap<String, Object>();//包裹每个一级菜单信息
				    map3.put("id", node_child.getNODE_ID());
				    map3.put("text", node_child.getNODE_TITLE());//节点名称
				    if(node_child.getR_STATUS()==1){
				    	map3.put("checked", true);
					}
					for(Node  node : nodeList){
						if(node.getNODE_PID() == node_child.getNODE_ID()){
							Map<String, Object> mapi = new HashMap<String, Object>();//包裹每个子菜单信息
							mapi.put("id", node.getNODE_ID());
							mapi.put("text", node.getNODE_TITLE());//节点名称
							if(node.getR_STATUS()==1){
								mapi.put("checked", true);
							}
					    	map4.add(mapi);
					    	map3.put("children", map4);
						}
					}
					maps.add(map3);
				}
				sys_map.put("children", maps);
				sys_maps.add(sys_map);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONArray.fromObject(sys_maps).toString();
	}
	
	//增加角色、并同时增加角色与模块相关联记录
	@RequestMapping(value="/addRole", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addRole(HttpServletRequest request){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String nodeIds = request.getParameter("nodeIds");//节点编号
			String role_name = request.getParameter("role_name");//角色名称
			String role_access = request.getParameter("role_access");//具体权限说明
			String role_remark = request.getParameter("role_remark");//角色说明
			String receive = request.getParameter("receive");
			
			
			
			Map<String, Object> mapCall = new HashMap<String, Object>();
			mapCall.put("nodeIds", nodeIds);
			mapCall.put("role_name", role_name);
			mapCall.put("role_access", role_access);
			mapCall.put("role_remark", role_remark);
			mapCall.put("role_receive", Long.parseLong(receive));
			
			mapCall.put("v_msg", "");
			RoleMapper realNameRoleMapper = (RoleMapper) this.factory.getBusinessProduct("realNameRole");
			
			realNameRoleMapper.addRole(mapCall);
			String v_msg=(String)mapCall.get("v_msg");
			map.put("status", "true");
			map.put("msg", v_msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("errorInfo", e.getMessage());
		}
		return result.getResultFromMap(map);
	}
	
	//删除角色、并同时删除角色与模块相关联记录
	@RequestMapping(value="/delRole", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delRole(HttpServletRequest request){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String roleId = request.getParameter("roleId");
			Map<String, Object> mapCall = new HashMap<String, Object>();
			mapCall.put("roleId", roleId);
			mapCall.put("v_msg", "");
			RoleMapper realNameRoleMapper = (RoleMapper) this.factory.getBusinessProduct("realNameRole");
			realNameRoleMapper.delRole(mapCall);
			String v_msg=(String)mapCall.get("v_msg");
			map.put("status", "true");
			map.put("msg", v_msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("errorInfo", e.getMessage());
		}
		return result.getResultFromMap(map);
	}
	
	//更改角色状态
	@RequestMapping(value="/updRoleStatus", method=RequestMethod.POST, produces = " text/html;charset=UTF-8")
	@ResponseBody
	public String updRoleStatus(HttpServletRequest request){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String roleId = request.getParameter("roleId");
			String status = request.getParameter("status");
			RoleMapper realNameRoleMapper = (RoleMapper) this.factory.getBusinessProduct("realNameRole");
			realNameRoleMapper.updRole(Integer.parseInt(status), Integer.parseInt(roleId));
			map.put("status", "true");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo", e.getMessage());
		}
		return result.getResultFromMap(map);
	}
	
	//角色编辑界面
	@RequestMapping(value="/editRoleView", method=RequestMethod.GET)
	public ModelAndView editRoleView(HttpServletRequest request,HttpSession session){
		ModelAndView mav =null;
		try {
			Users users =  (Users) session.getAttribute("users");
			if(null==users){
				mav = new ModelAndView("/netError");
			}else{
				String roleId = request.getParameter("roleId");
				if(null!=roleId){
					mav = new ModelAndView("/roleManage/editRole");
					RoleMapper realNameRoleMapper = (RoleMapper) this.factory.getBusinessProduct("realNameRole");
					Role role = realNameRoleMapper.getRoleById(Integer.parseInt(roleId));
					if(null!=role){
						mav.addObject("ROLE_ID",role.getROLE_ID());//角色编号
						mav.addObject("ROLE_NAME",role.getROLE_NAME());//角色名称
						mav.addObject("ROLE_ACCESS",role.getROLE_ACCESS());//角色可访问权限
						mav.addObject("ROLE_REMARK",role.getROLE_REMARK());//角色说明
						mav.addObject("role_receive",role.getPERMISSION_ONE());//角色说明
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav; 
	}
	
	//获取该角色下的节点权限
	@RequestMapping(value="/getNodeByRole", method=RequestMethod.POST, produces = " text/html;charset=UTF-8")
	@ResponseBody
	public String getNodeByRole(HttpServletRequest request){
		String returnTree = "";
		try {
			RoleMapper realNameRoleMapper = (RoleMapper) this.factory.getBusinessProduct("realNameRole");
			String ROLE_ID = request.getParameter("ROLE_ID");
			List<Node> nodeList = realNameRoleMapper.getNodeByRole(Integer.parseInt(ROLE_ID));
			returnTree = returnNodeTree(nodeList); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnTree;
	}
	
	//修改角色信息
	@RequestMapping(value="/updRoleInfo", method=RequestMethod.POST, produces = " text/html;charset=UTF-8")
	@ResponseBody
	public String updRoleInfo(HttpServletRequest request){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String nodeIds = request.getParameter("nodeIds");
			String roleId = request.getParameter("roleId");
			String role_name = request.getParameter("role_name");
			String role_access = request.getParameter("role_access");
			String role_remark = request.getParameter("role_remark");
			String receive = request.getParameter("receive");
			
			
			Map<String, Object> mapCall = new HashMap<String, Object>();
			mapCall.put("nodeIds", nodeIds);//节点编号
			mapCall.put("roleId", roleId);//角色编号
			mapCall.put("roleName", role_name);//角色名称
			mapCall.put("roleAccess", role_access);//具体权限说明
			mapCall.put("roleRemark", role_remark);//角色说明
			mapCall.put("roleReceive", Long.parseLong(receive));
			mapCall.put("v_msg", "");
			RoleMapper realNameRoleMapper = (RoleMapper) this.factory.getBusinessProduct("realNameRole");
			realNameRoleMapper.updRoleById(mapCall);
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
	
	//获取所有角色信息
	@RequestMapping(value="/getAllRole", method=RequestMethod.POST, produces ="text/html;charset=UTF-8")
	@ResponseBody
	public String getAllRole(){
		String roleTree = "";
		try {
			RoleMapper realNameRoleMapper = (RoleMapper) this.factory.getBusinessProduct("realNameRole");
			List<Role> roleList = realNameRoleMapper.getRealNameRole();
			if(null!=roleList && roleList.size()>0){
				roleTree = returnRoleTree(roleList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roleTree;
	}
	
	
	//获取状态为开启的角色信息
	@RequestMapping(value="/getRoleByStatus", method=RequestMethod.POST, produces ="text/html;charset=UTF-8")
	@ResponseBody
	public String getRoleByStatus(){
		
		String roleTree = "";
		try {
			RoleMapper realNameRoleMapper = (RoleMapper) this.factory.getBusinessProduct("realNameRole");
			List<Role> roleList = realNameRoleMapper.getRealNameByStatus();
			if(null!=roleList && roleList.size()>0){
				roleTree = returnRoleTree(roleList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roleTree;
	}
	
	//返回权限树
	public String returnRoleTree(List<Role> roleList){
		List<Map<String, Object>> sys_maps = new ArrayList<Map<String,Object>>();//最上级菜单
		try {
			if(roleList!=null && roleList.size()>0){
				for(Role role : roleList){
					Map<String, Object> sys_map = new HashMap<String, Object>();//存放二级菜单
					sys_map.put("id", role.getROLE_ID());
					sys_map.put("text", role.getROLE_NAME());
					sys_maps.add(sys_map);
				}
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONArray.fromObject(sys_maps).toString();
	}
}
