package com.yqx.jurisdiction.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yqx.jurisdiction.business.UserMapper;
import com.yqx.jurisdiction.entity.Users;
import com.yqx.jurisdiction.util.EncryptUtil;
import com.yqx.jurisdiction.util.ResultToJackson;
import com.yqx.jurisdiction.util.Util;

/***
 * @author YQX
 * @content  后台用户管理
 * @time 2016年6月22日17:08:10
 * 
 * */
@Controller
@RequestMapping(value="/adminUser")
@SuppressWarnings("all")
public class AdminUserController extends CotrollerBase{
	
	//角色管理界面
	@RequestMapping(value="/userView", method=RequestMethod.GET)
	public ModelAndView adminUserView(HttpServletRequest request,HttpSession session){
		ModelAndView mav =null;
		try {
			Users users = (Users) session.getAttribute("users");
			if(null==users){
				mav = new ModelAndView("/netError");
			}else{
				mav = new ModelAndView("/userManage/userManage");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("/admin/index");
			mav.addObject("reminder", e.getMessage());
		}
		return mav;
	}
	
	//获取用户列表
	@RequestMapping(value="/getAllUser", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllUser(){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserMapper realNameUserMapper = (UserMapper) factory.getBusinessProduct("realNameUser");
			List<Users> userList = realNameUserMapper.getAllUser();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
			if(null!=userList && userList.size()>0){
				List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
				for(Users user : userList){
					Map<String, Object> user_map = new HashMap<String, Object>();
					user_map.put("USER_ID", user.getUSER_ID());//用户编号
					user_map.put("USER_ACCOUNT", user.getUSER_ACCOUNT());//账号
					user_map.put("USER_CREATDATE", null==user.getUSER_CREATDATE()?"": df.format(user.getUSER_CREATDATE()));//创建时间
					user_map.put("USER_LASTDATE", null==user.getUSER_LASTDATE()?"": df.format(user.getUSER_LASTDATE()));//最后登录时间
					user_map.put("USER_STATUS", user.getUSER_STATUS());//用户状态
					user_map.put("USER_ROLE", user.getUSER_ROLE());//角色权限
					user_map.put("USER_MAIL", user.getUSER_MAIL());//用户邮箱
					user_map.put("USER_NAME", user.getUSER_NAME());//用户邮箱
					user_map.put("USER_PHONE", user.getUSER_PHONE());//用户邮箱
					maps.add(user_map);
				}
				map.put("total", userList.size());
				map.put("rows", maps);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result.getResultFromMap(map);
		}
	
	//修改用户状态
	@RequestMapping(value="/updUserStatus", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserStatus(HttpServletRequest request){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String USER_ID = request.getParameter("USER_ID");
			String USER_STATUS = request.getParameter("USER_STATUS");
			UserMapper realNameUserMapper = (UserMapper) factory.getBusinessProduct("realNameUser");
			realNameUserMapper.updUserStatus(Integer.parseInt(USER_ID), Integer.parseInt(USER_STATUS));
			map.put("status", "true");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo", e.getMessage());
		}
		return result.getResultFromMap(map);
	}
	
	//新增用户信息
	@RequestMapping(value="/addUserInfo", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserInfo(HttpServletRequest request){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserMapper realNameUserMapper = (UserMapper) factory.getBusinessProduct("realNameUser");
			String USER_ACCOUNT = request.getParameter("USER_ACCOUNT");//帐号
			String USER_PASSWORD = request.getParameter("USER_PASSWORD");//密码
			String USER_NAME = request.getParameter("USER_NAME");//用户名
			String ROLE_ID = request.getParameter("ROLE_ID");//角色ID
			String userExplain = request.getParameter("userExplain");//角色说明
			String USER_MAIL = request.getParameter("USER_MAIL");//角色说明
			String USER_PHONE = request.getParameter("USER_PHONE");//手机号码
			String addRealName = request.getParameter("addRealName");
			realNameUserMapper.addUser(USER_ACCOUNT, Util.MD5(USER_PASSWORD), USER_NAME, Integer.parseInt(ROLE_ID), userExplain,USER_MAIL,"".equals(USER_PHONE)?0L:Long.parseLong(USER_PHONE),(null==addRealName || "".equals(addRealName)?0L:Long.parseLong(addRealName)));
			map.put("status", "true");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo",e.getMessage());
		}
		return result.getResultFromMap(map);
	}
	
	//用户编辑界面
	@RequestMapping(value="/editUserView", method=RequestMethod.GET)
	public ModelAndView editUserView(HttpServletRequest request,HttpSession session){
		ModelAndView mav =null;
		try {
			Users users = (Users) session.getAttribute("users");
			if(null==users){
				mav = new ModelAndView("/netError");
			}else{
				String userId = request.getParameter("userId");
				if(null!=userId){
					mav = new ModelAndView("/userManage/editUser");
					UserMapper realNameUserMapper = (UserMapper) factory.getBusinessProduct("realNameUser");
					Users user = realNameUserMapper.getUserById(Integer.parseInt(userId));
					if(null!=user){
						mav.addObject("USER_ID",user.getUSER_ID());//用户编号
						mav.addObject("ROLE_ID", user.getROLE_ID());//角色编号
						mav.addObject("USER_ACCOUNT", user.getUSER_ACCOUNT());//账号
						mav.addObject("USER_NAME", user.getUSER_NAME());//用户名
						mav.addObject("USER_ROLE", user.getUSER_ROLE());//角色说明
						mav.addObject("USER_MAIL", user.getUSER_MAIL());//邮箱
						mav.addObject("USER_PHONE", user.getUSER_PHONE());//邮箱
						mav.addObject("USER_CREATE_STATUS", user.getUSER_CREATE_STATUS());//邮箱
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav; 
	}
	
	//修改用户信息
	@RequestMapping(value="/updUser", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUser(HttpServletRequest request,HttpSession session){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String USER_ID = request.getParameter("USER_ID");
			String USER_NAME = request.getParameter("USER_NAME");
			String ROLE_ID = request.getParameter("ROLE_ID");
			String USER_ROLE = request.getParameter("USER_ROLE");
			String USER_MAIL = request.getParameter("USER_MAIL");
			String USER_PHONE = request.getParameter("USER_PHONE");//手机号码
			String addRealName = request.getParameter("addRealName");
			
			UserMapper realNameUserMapper = (UserMapper) factory.getBusinessProduct("realNameUser");

			realNameUserMapper.updUserByRole(Integer.parseInt(USER_ID),Integer.parseInt(ROLE_ID), USER_ROLE,USER_NAME,USER_MAIL,
					"".equals(USER_PHONE)?0L:Long.parseLong(USER_PHONE),(null==addRealName || "".equals(addRealName)?0L:Long.parseLong(addRealName)));
			map.put("status", "true");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo", e.getMessage());
		}
		return result.getResultFromMap(map);
	}
	
	
	//删除用户信息
	@RequestMapping(value="/delUser", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delUser(HttpServletRequest request){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String USER_ID = request.getParameter("USER_ID");
			UserMapper realNameUserMapper = (UserMapper) factory.getBusinessProduct("realNameUser");
			realNameUserMapper.delUser(Integer.parseInt(USER_ID));
			map.put("status", "true");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo", e.getMessage());
			}
			return result.getResultFromMap(map);
	}
	
	//修改密码
	@RequestMapping(value="/updPwd", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updPwd(HttpServletRequest request,HttpSession session){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String USER_ID = request.getParameter("USER_ID");
			String OLD_PASSWORD = request.getParameter("OLD_PASSWORD");
			String USER_PASSWORD = request.getParameter("USER_PASSWORD");
			
			Users users = (Users) session.getAttribute("users");
			if(null!=users){
				String oldPwd = Util.MD5(OLD_PASSWORD);
				if(users.getUSER_PASSWORD().equals(oldPwd)){
					UserMapper realNameUserMapper = (UserMapper) factory.getBusinessProduct("realNameUser");
					String newPwd = Util.MD5(USER_PASSWORD);
					realNameUserMapper.updPassword(Integer.parseInt(USER_ID), newPwd);
					map.put("status", "true");
				}else{
					map.put("status", "false");
					map.put("errorInfo", "原始密码错误");
				}
			}else{
				map.put("status", "false");
				map.put("errorInfo", "登录超时,请退出后重新登录");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo", e.getMessage());
			}
			return result.getResultFromMap(map);
	}

	//获取邮箱
	@RequestMapping(value="/getMailByUserName", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getMailByUserName(HttpServletRequest request,HttpSession session){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String findUserName = request.getParameter("findUserName");
			UserMapper realNameUserMapper = (UserMapper) factory.getBusinessProduct("realNameUser");
			String userMail = realNameUserMapper.getMailByUserName(findUserName);
			if(null!=userMail && !"".equals(userMail)){
				map.put("status", "true");
				map.put("msg", userMail.split(",")[0].toString());
				map.put("userId", userMail.split(",")[1].toString());
			}else{
				map.put("status", "false");
				map.put("errorInfo","用户信息不存在");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo", e.getMessage());
		}
			
		return result.getResultFromMap(map);
	}	
	
	//查询账号是否存在
	@RequestMapping(value="/getCountUserByName", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getCountUserByName(HttpServletRequest request){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String USER_ACCOUNT = request.getParameter("USER_ACCOUNT");
			UserMapper realNameUserMapper = (UserMapper) factory.getBusinessProduct("realNameUser");
			int flag = realNameUserMapper.getCountUserByName(USER_ACCOUNT);
			if(flag==0){
				map.put("status", "true");
			}else{
				map.put("status", "false");
				map.put("errorInfo", "用户账号已存在,请更换其他");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo", e.getMessage());
		}
			
		return result.getResultFromMap(map);
	}
	
	//发送邮件
	@RequestMapping(value="/sendEmail", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String sendEmail(HttpServletRequest request,HttpSession session){
		Map<String, Object> result_map = new HashMap<String, Object>();//返回给前台的数据
		ResultToJackson result = new ResultToJackson();
		try {
			String userId = request.getParameter("userId");
			String mail = request.getParameter("mail");
			
		//	String secretKey = Util.getRandomString(50) ;//UUID.randomUUID().toString(); // 密钥
			Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期
			long date = outDate.getTime() / 1000 * 1000;// 忽略毫秒数  mySql 取出时间是忽略毫秒数的

			String key =userId + "#" + date;// + "#"+secretKey;
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":"
					+ request.getServerPort()+"/riskmanage/adminUser/updPassword?r="+EncryptUtil.encode(key);
			
			
			String pathurl = request.getScheme() + "://"
					+ request.getServerName() + ":"
					+ request.getServerPort()+"/riskmanage/adminUser/updPassword?r="+Util.getRandomString(30);

			String emailContent = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href='"+basePath+"'>"+pathurl
					+ "</a><br/>本邮件超过30分钟链接将会失效";

			System.out.println(emailContent);
			//RmServer rmServer = RmServer.getInstance();
			boolean flag = true;//rmServer.mTcpBusTrans.sendMail(mail, "风控系统密码找回", emailContent);
			if(flag){
				result_map.put("status","true");
			} else{
				result_map.put("status","false");
				result_map.put("error","邮件发送错误,请联系系统管理员");
			}  
			
		} catch (Exception e) {
			e.printStackTrace();
			result_map.put("status","false");
			result_map.put("error","系统错误【"+e.getMessage()+"】"); 
		}
		return result.getResultFromMap(result_map);
	}
	
	//密码修改
	@RequestMapping(value="/updPassword", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ModelAndView updPassword(HttpSession session,HttpServletRequest request){
		ModelAndView mav = null;
		try {
			String r=request.getParameter("r");
			if(null==r){
				mav = new ModelAndView("/404");
				mav.addObject("error", "请求的页面已失效");
			}else{
				String[] urlParam = EncryptUtil.decode(r).split("#");
				Timestamp nowDate = new Timestamp(System.currentTimeMillis()+0 * 60 * 1000);// 30分钟后过期
				long date1 = nowDate.getTime() / 1000 * 1000;// 忽略毫秒数  mySql 取出时间是忽略毫秒数的
				if(date1>Long.parseLong(urlParam[1])){
					mav = new ModelAndView("/404");
					mav.addObject("error", "请求的页面已失效");//链接超时
				}else{
					if(null==urlParam[0] || null==urlParam[1]){
						mav = new ModelAndView("/404");
						mav.addObject("error", "请求的页面已失效");
					}else{
						mav = new ModelAndView("/updPwd");
						mav.addObject("userId", urlParam[0]);
					}
				}
			}
		}catch (Exception e) {
			mav = new ModelAndView("/404");
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/finadUpdPwd", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView riskManageLogin(HttpServletRequest request,HttpSession session){
		ModelAndView mav =null;
		try {
			String userCode=request.getParameter("userCode").toString().toLowerCase();//验证码
			String code = (String) session.getAttribute("code");
			if(null==userCode || !userCode.equals(code.toLowerCase())){
				mav = new ModelAndView("/updPwd");
				mav.addObject("reminder", "验证码错误");
			}else{
				String USER_PASSWORD=Util.MD5(request.getParameter("USER_PASSWORD")).toString().toLowerCase();//密码加密处理
				String userId=request.getParameter("userId");//验证码
				UserMapper realNameUserMapper = (UserMapper) factory.getBusinessProduct("realNameUser");
				realNameUserMapper.updPassword(Integer.parseInt(userId), USER_PASSWORD);
				mav = new ModelAndView("/login");
				mav.addObject("reminder", "密码修改成功,请使用新密码重新登录");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("/login");
			mav.addObject("reminder", e.getMessage());
		}
		return mav;
	}
	
	
}
