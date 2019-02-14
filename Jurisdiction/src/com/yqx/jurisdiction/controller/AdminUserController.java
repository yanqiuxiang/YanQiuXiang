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
 * @content  ��̨�û�����
 * @time 2016��6��22��17:08:10
 * 
 * */
@Controller
@RequestMapping(value="/adminUser")
@SuppressWarnings("all")
public class AdminUserController extends CotrollerBase{
	
	//��ɫ�������
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
	
	//��ȡ�û��б�
	@RequestMapping(value="/getAllUser", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllUser(){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserMapper realNameUserMapper = (UserMapper) factory.getBusinessProduct("realNameUser");
			List<Users> userList = realNameUserMapper.getAllUser();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//��ʽ��ʱ��
			if(null!=userList && userList.size()>0){
				List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
				for(Users user : userList){
					Map<String, Object> user_map = new HashMap<String, Object>();
					user_map.put("USER_ID", user.getUSER_ID());//�û����
					user_map.put("USER_ACCOUNT", user.getUSER_ACCOUNT());//�˺�
					user_map.put("USER_CREATDATE", null==user.getUSER_CREATDATE()?"": df.format(user.getUSER_CREATDATE()));//����ʱ��
					user_map.put("USER_LASTDATE", null==user.getUSER_LASTDATE()?"": df.format(user.getUSER_LASTDATE()));//����¼ʱ��
					user_map.put("USER_STATUS", user.getUSER_STATUS());//�û�״̬
					user_map.put("USER_ROLE", user.getUSER_ROLE());//��ɫȨ��
					user_map.put("USER_MAIL", user.getUSER_MAIL());//�û�����
					user_map.put("USER_NAME", user.getUSER_NAME());//�û�����
					user_map.put("USER_PHONE", user.getUSER_PHONE());//�û�����
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
	
	//�޸��û�״̬
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
	
	//�����û���Ϣ
	@RequestMapping(value="/addUserInfo", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserInfo(HttpServletRequest request){
		ResultToJackson result = new ResultToJackson();	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserMapper realNameUserMapper = (UserMapper) factory.getBusinessProduct("realNameUser");
			String USER_ACCOUNT = request.getParameter("USER_ACCOUNT");//�ʺ�
			String USER_PASSWORD = request.getParameter("USER_PASSWORD");//����
			String USER_NAME = request.getParameter("USER_NAME");//�û���
			String ROLE_ID = request.getParameter("ROLE_ID");//��ɫID
			String userExplain = request.getParameter("userExplain");//��ɫ˵��
			String USER_MAIL = request.getParameter("USER_MAIL");//��ɫ˵��
			String USER_PHONE = request.getParameter("USER_PHONE");//�ֻ�����
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
	
	//�û��༭����
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
						mav.addObject("USER_ID",user.getUSER_ID());//�û����
						mav.addObject("ROLE_ID", user.getROLE_ID());//��ɫ���
						mav.addObject("USER_ACCOUNT", user.getUSER_ACCOUNT());//�˺�
						mav.addObject("USER_NAME", user.getUSER_NAME());//�û���
						mav.addObject("USER_ROLE", user.getUSER_ROLE());//��ɫ˵��
						mav.addObject("USER_MAIL", user.getUSER_MAIL());//����
						mav.addObject("USER_PHONE", user.getUSER_PHONE());//����
						mav.addObject("USER_CREATE_STATUS", user.getUSER_CREATE_STATUS());//����
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav; 
	}
	
	//�޸��û���Ϣ
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
			String USER_PHONE = request.getParameter("USER_PHONE");//�ֻ�����
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
	
	
	//ɾ���û���Ϣ
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
	
	//�޸�����
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
					map.put("errorInfo", "ԭʼ�������");
				}
			}else{
				map.put("status", "false");
				map.put("errorInfo", "��¼��ʱ,���˳������µ�¼");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo", e.getMessage());
			}
			return result.getResultFromMap(map);
	}

	//��ȡ����
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
				map.put("errorInfo","�û���Ϣ������");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo", e.getMessage());
		}
			
		return result.getResultFromMap(map);
	}	
	
	//��ѯ�˺��Ƿ����
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
				map.put("errorInfo", "�û��˺��Ѵ���,���������");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "false");
			map.put("errorInfo", e.getMessage());
		}
			
		return result.getResultFromMap(map);
	}
	
	//�����ʼ�
	@RequestMapping(value="/sendEmail", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String sendEmail(HttpServletRequest request,HttpSession session){
		Map<String, Object> result_map = new HashMap<String, Object>();//���ظ�ǰ̨������
		ResultToJackson result = new ResultToJackson();
		try {
			String userId = request.getParameter("userId");
			String mail = request.getParameter("mail");
			
		//	String secretKey = Util.getRandomString(50) ;//UUID.randomUUID().toString(); // ��Կ
			Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);// 30���Ӻ����
			long date = outDate.getTime() / 1000 * 1000;// ���Ժ�����  mySql ȡ��ʱ���Ǻ��Ժ�������

			String key =userId + "#" + date;// + "#"+secretKey;
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":"
					+ request.getServerPort()+"/riskmanage/adminUser/updPassword?r="+EncryptUtil.encode(key);
			
			
			String pathurl = request.getScheme() + "://"
					+ request.getServerName() + ":"
					+ request.getServerPort()+"/riskmanage/adminUser/updPassword?r="+Util.getRandomString(30);

			String emailContent = "����ظ����ʼ�.������������,��������<br/><a href='"+basePath+"'>"+pathurl
					+ "</a><br/>���ʼ�����30�������ӽ���ʧЧ";

			System.out.println(emailContent);
			//RmServer rmServer = RmServer.getInstance();
			boolean flag = true;//rmServer.mTcpBusTrans.sendMail(mail, "���ϵͳ�����һ�", emailContent);
			if(flag){
				result_map.put("status","true");
			} else{
				result_map.put("status","false");
				result_map.put("error","�ʼ����ʹ���,����ϵϵͳ����Ա");
			}  
			
		} catch (Exception e) {
			e.printStackTrace();
			result_map.put("status","false");
			result_map.put("error","ϵͳ����"+e.getMessage()+"��"); 
		}
		return result.getResultFromMap(result_map);
	}
	
	//�����޸�
	@RequestMapping(value="/updPassword", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ModelAndView updPassword(HttpSession session,HttpServletRequest request){
		ModelAndView mav = null;
		try {
			String r=request.getParameter("r");
			if(null==r){
				mav = new ModelAndView("/404");
				mav.addObject("error", "�����ҳ����ʧЧ");
			}else{
				String[] urlParam = EncryptUtil.decode(r).split("#");
				Timestamp nowDate = new Timestamp(System.currentTimeMillis()+0 * 60 * 1000);// 30���Ӻ����
				long date1 = nowDate.getTime() / 1000 * 1000;// ���Ժ�����  mySql ȡ��ʱ���Ǻ��Ժ�������
				if(date1>Long.parseLong(urlParam[1])){
					mav = new ModelAndView("/404");
					mav.addObject("error", "�����ҳ����ʧЧ");//���ӳ�ʱ
				}else{
					if(null==urlParam[0] || null==urlParam[1]){
						mav = new ModelAndView("/404");
						mav.addObject("error", "�����ҳ����ʧЧ");
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
			String userCode=request.getParameter("userCode").toString().toLowerCase();//��֤��
			String code = (String) session.getAttribute("code");
			if(null==userCode || !userCode.equals(code.toLowerCase())){
				mav = new ModelAndView("/updPwd");
				mav.addObject("reminder", "��֤�����");
			}else{
				String USER_PASSWORD=Util.MD5(request.getParameter("USER_PASSWORD")).toString().toLowerCase();//������ܴ���
				String userId=request.getParameter("userId");//��֤��
				UserMapper realNameUserMapper = (UserMapper) factory.getBusinessProduct("realNameUser");
				realNameUserMapper.updPassword(Integer.parseInt(userId), USER_PASSWORD);
				mav = new ModelAndView("/login");
				mav.addObject("reminder", "�����޸ĳɹ�,��ʹ�����������µ�¼");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("/login");
			mav.addObject("reminder", e.getMessage());
		}
		return mav;
	}
	
	
}
