package com.yqx.jurisdiction.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yqx.jurisdiction.business.NodeMapper;
import com.yqx.jurisdiction.business.UserMapper;
import com.yqx.jurisdiction.entity.Node;
import com.yqx.jurisdiction.entity.Users;
import com.yqx.jurisdiction.util.Util;


@Controller
public class IndexController extends CotrollerBase{
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView index(HttpSession session){
		
		ModelAndView mav =null;
		try {
			Users users = (Users) session.getAttribute("users");
			if(null==users){
				mav = new ModelAndView("/netError");
			}else{
				mav = new ModelAndView("/frame/index");
				mav.addObject("receive", users.getPERMISSION_ONE()&1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("/login");
			mav.addObject("reminder", e.getMessage());
		}
		return mav;
	}
	//连接超时页面
	@RequestMapping(value="/accessRight", method=RequestMethod.GET)
	public ModelAndView accessRight(){
		return new ModelAndView("/accessRight");
	}
	
	//访问无权限
	@RequestMapping(value="/netError", method=RequestMethod.GET)
	public ModelAndView netError(){
		return new ModelAndView("/netError");
	}
		
		
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public ModelAndView main(HttpSession session,HttpServletRequest request){
		ModelAndView mav =null;
		Users users = (Users) session.getAttribute("users");
		if(null!=users){
			mav = new ModelAndView("/frame/main");
		}else{
			mav = new ModelAndView("/login");
		}
		return mav;
	}
	
	@RequestMapping(value="/left", method=RequestMethod.GET)
	public ModelAndView left(HttpSession session){
		ModelAndView mav = new ModelAndView("/frame/left");
		Users users = (Users) session.getAttribute("users");
		NodeMapper realNameNodeMapper = (NodeMapper) factory.getBusinessProduct("realNameNode");
		List<Node> nodes = null;
		nodes = realNameNodeMapper.getNodeByRole(users.getUSER_ID()==0?0:users.getROLE_ID(), 0);
		session.removeAttribute("str");
		
		List<String> str = new ArrayList<String>(); 
		if(null!=nodes && nodes.size()>0){
			
			for(Node r : nodes){
				if(null!=r.getNODE_URL() && !"".equals(r.getNODE_URL())){
					
					String firstUrl = r.getNODE_URL().substring(r.getNODE_URL().indexOf("/")+1, r.getNODE_URL().lastIndexOf("/"));
					
					str.add(firstUrl);
				}
			}
			session.setAttribute("str", str);
		}
		
		mav.addObject("rmCompetenceNodes", nodes); 
		return mav;
	}
	
	@RequestMapping(value="/right", method=RequestMethod.GET)
	public ModelAndView right(){
		ModelAndView mav = new ModelAndView("/frame/right");
		return mav ;
	}
	
	@RequestMapping(value="/top", method=RequestMethod.GET)
	public ModelAndView top(){
		ModelAndView mav  = new ModelAndView("/frame/top");
		return mav;
	}
	
	
	//登录
	@RequestMapping(value="/realNameLogin", method=RequestMethod.POST)
	public ModelAndView riskManageLogin(HttpServletRequest request,HttpSession session){
		ModelAndView mav =null;
		try {
			String userName =request.getParameter("userName");//用户名
			String passWord=Util.MD5(request.getParameter("passWord")).toString().toLowerCase();//密码加密处理
			String userCode=request.getParameter("userCode").toString().toLowerCase();//验证码
			String code = (String) session.getAttribute("code");
			if(null!=code && !"".equals(code)){
				if(null==userCode || !userCode.equals(code.toLowerCase())){
					mav = new ModelAndView("/login");
					mav.addObject("userName", userName);
					mav.addObject("passWord",passWord);
					mav.addObject("reminder", "验证码错误");
				}else{
					
					UserMapper realNameUserMapper = (UserMapper) factory.getBusinessProduct("realNameUser");
					Users users  = realNameUserMapper.login(userName, passWord); 
					/*
					users.setUSER_ACCOUNT("admin8");
					users.setUSER_ID(0);
					users.setROLE_ID(1);
					users.setUSER_NAME("developer@legentec.com");
					users.setUSER_STATUS(0);
					
					
					session.setAttribute("users", users);
					realNameUserMapper.updLoginTime(0);//users.getUSER_ID()
					mav =  new ModelAndView("redirect:/main");
					*/
					
					if(null==users){
						mav = new ModelAndView("/login");
						mav.addObject("userName", userName);
						mav.addObject("passWord",passWord);
						mav.addObject("reminder", "账号或密码错误");
					}else if(users.getUSER_STATUS()==1 ){
						mav = new ModelAndView("/login");
						mav.addObject("reminder", "该用户状态为禁用状态,请联系管理员");
					}else if(users.getROLE_STATUS()==1 && users.getUSER_ID()!=0){
						mav = new ModelAndView("/login");
						mav.addObject("reminder", "该用户角色权限状态为禁用状态,请联系管理员");
					}else{
						Thread.sleep(1000); 
						session.setAttribute("users", users);
						realNameUserMapper.updLoginTime(users.getUSER_ID());
						mav =  new ModelAndView("redirect:/main");
					}
				}
			}else{
				mav = new ModelAndView("/login");
				mav.addObject("reminder","连接超时,请重新登录");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("/login");
			mav.addObject("reminder", e.getMessage());
		}
		return mav;
	}

	//注销
	@RequestMapping(value="/loginOut", method=RequestMethod.GET)
	public ModelAndView loginOut(HttpSession session){
		session.removeAttribute("users");
		ModelAndView mav =null;
		mav = new ModelAndView("redirect:/main");
		return mav;
	}
	
	
	
	@RequestMapping(value="/remindWriteLogView", method=RequestMethod.GET)
	public ModelAndView remindWriteLogView(HttpSession session,HttpServletRequest request){
		ModelAndView mav =null;
		Users users = (Users) session.getAttribute("users");
		if(null!=users){
			mav = new ModelAndView("/frame/remindWriteLog");
		}else{
			mav = new ModelAndView("/login");
		}
		return mav;
	}
	
	
}
