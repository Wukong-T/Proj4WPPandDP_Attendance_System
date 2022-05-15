package com.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.User;
import com.demo.service.DataService;
import com.demo.service.UserService;
import com.demo.util.RandomUtil;
import com.demo.util.SendMailUtil;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("/user")
public class AccountController {
	@Autowired
	private UserService userService;
	@Autowired
	private DataService dataService;

	/**
	 * 注册
	 * @param user
	 * @return
	 */
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		String username = user.getUsername();
		String choice = user.getChoice();
		String checkChoice = dataService.getChoice(username);
		if(checkChoice.equals("empty")) {
//			员工信息不存在，请核对信息或者先进行录入
			return "empty";
		}else {
			if(choice.equals(checkChoice)) {
//				员工姓名和工号与真实对应，可进行注册
				return userService.insertUser(user);
			}else {
//				选择用户错误
				return "error";
			}
		}
	}
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		String check = user.getChoice();
		User test =  userService.findAllByUsername(username);
		if(test != null){
			String choice = test.getChoice();
			String encrypassword = test.getPassword();
			
				if (check.equals(choice)) {
						String matches = userService.matchesEncoder(password, encrypassword);
						return matches;			
				}else {
					return "check";
				}		
		}else {
			if(username.equals("admin")||username.equals("Admin")){
					return userService.loginAdmin(user);
			} else {
				return "empty";
				}
		}
	}
	
	
	/**
	 * 通过旧密码或者验证码修改密码
	 * @param user
	 * @return
	 */
	@PostMapping("/updatePassword")
	public String updatePassword(@RequestBody User user) {
		String username = user.getUsername();
		
		String check = user.getChoice();//前端输入的密码或验证码
		String newpassword = user.getPassword();
		User test =  userService.findAllByUsername(username);
		if(test != null){
			String oldpassword = test.getPassword();
			String oldverifyCode = test.getVerifyCode();
			String passwordMatches =userService.matchesEncoder(check, oldpassword);
			String verifyCodeMatches =userService.matchesEncoder(check, oldverifyCode);
			if(passwordMatches.equals("success")||verifyCodeMatches.equals("success")) {
				userService.updatePassword(username,newpassword);
				return "success";
			}
			else {
				return "error";
			}
			
		
		}else {
			return "empty";
		}
				
	}
	
	/**
	 * 通过验证码修改邮箱
	 * @param user
	 * @return
	 */
	@PostMapping("/changeEmial")
	public String changeEmial(@RequestBody User user){
		String username = user.getUsername();
		String email = user.getEmail();
		String verifyCode = user.getVerifyCode();
		User test =  userService.findAllByUsername(username);
		if(test != null){
		
			String oldverifyCode = test.getVerifyCode();
			
			String matches =userService.matchesEncoder(verifyCode, oldverifyCode);
			if(matches.equals("success")) {
				userService.updateEmail(username, email);
				return "success";
			}else {
				return "error";
			}
		}else {
			return "empty";
		}
		
	}
	
	/**
	 * 发送邮箱并获取验证码
	 * @param user
	 * @return
	 */
	@PostMapping("/getCheckemail")
	public String sendCodeToMail(@RequestBody User user){
		String username = user.getUsername();
		String email = user.getEmail();
		String choice = user.getChoice();
		User test = userService.findAllByUsername(username);
		if(test != null){		//判断用户是否存在
			String mailAddress = test.getEmail();
			String checkchoice = test.getChoice();
			String checkusername = test.getUsername();
			if (checkusername.equals(username)&&choice.equals(checkchoice)) {
				//根据username获取保存的邮箱
				if(email.equals(mailAddress)){		//用户输入邮箱与绑定邮箱一致→发送验证码
			        try{
			            //生成验证码
			            String verifyCode = RandomUtil.getCode();
			            //邮件主题
			            String emailTitle = "邮箱验证";
			            //邮件内容
			            String emailContent = "您正在进行邮箱验证，您的验证码为：" + verifyCode 
			            		+"\n\n 验证码将于5分钟后失效！";
			            //发送邮件
			            SendMailUtil.sendEmail(mailAddress, emailTitle, emailContent);
			            
			            /* 将验证码注入数据库 */
			            userService.updateCode(username, verifyCode);
			            
					/* 验证码在指定时间内失效 */
		            Timer timer = new Timer();
		            timer.schedule(new TimerTask() {
						public void run() {
							String RandomCode = RandomUtil.getCode();
							userService.updateCode(username, RandomCode);
						}
		            },300000);//300000ms(300s)即5分钟
			            
			    	    return "success";
			        }catch(Exception e){
			        	return "error";
			        }
				}else{
					//邮箱地址错误
					return "addressError";
				}
			}else {
				//用户或选择用户错误
				return "userError";
			}
		}else{
			//用户不存在
			return "empty";
		}
	}

	/**
	 * 按用户名查找单个账号信息
	 * @param username
	 * @return
	 */
	@GetMapping("/findAllByUsername/{username}")
	public User findAllByUsername(@PathVariable("username") String username) {
		return userService.findAllByUsername(username);
	}
	
	/**
	 * 分页查询所有账号信息
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@GetMapping("findAll/{currentPage}/{pageSize}")
	public Map<String,Object>  findAll(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize){
		PageHelper.startPage(currentPage, pageSize);
		Map<String,Object> returnMap=new HashMap<String,Object>();

		List<User> list=null;
		returnMap.put("content", list);
		
		list= userService.findAll();
		returnMap.put("content", list);
		return returnMap;

	}
	
	/**
	 * 按用户类型查询账号所有信息
	 * @param currentPage
	 * @param pageSize
	 * @param choice
	 * @return
	 */
	@GetMapping("findAllByChoice/{currentPage}/{pageSize}/{choice}")
	public Map<String,Object>  findAllByChoice(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize
			, @PathVariable("choice") String choice){
		PageHelper.startPage(currentPage, pageSize);
		Map<String,Object> returnMap=new HashMap<String,Object>();

		List<User> list=null;
		returnMap.put("content", list);
		
		list= userService.findAllByChoice(choice);
		returnMap.put("content", list);
		return returnMap;

	}
	
	/**
	 * 删除用户信息
	 * @param username
	 */
	@DeleteMapping("deleteByUsername/{username}")
	public void deleteByUsername(@PathVariable("username") String username) {
		 userService.deleteByUsername(username);
	}
	
	/**
	 * 删除用户信息
	 * @param username
	 */
	@DeleteMapping("deleteAll")
	public void deleteByUsername() {
		 userService.deleteAll();
	}
	
	/**
	 * 通过验证码注销账号
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unused")
	@PostMapping("/delUser")
	public String delUserByCode(@RequestBody User user){
		String username = user.getUsername();
		String email = user.getEmail();
		String verifyCode = user.getVerifyCode();
		User test =  userService.findAllByUsername(username);
		if(test != null){
		
			String oldverifyCode = test.getVerifyCode();
			
			String matches =userService.matchesEncoder(verifyCode, oldverifyCode);
			if(matches.equals("success")) {
				userService.deleteByUsername(username);
				return "success";
			}else {
				return "error";
			}
		}else {
			return "empty";
		}
	}
	
	/**
	 * 按姓名模糊查询全部
	 * @param currentPage
	 * @param pageSize
	 * @param realname
	 * @return
	 */
	@GetMapping("FuzzyQuery/{currentPage}/{pageSize}/{realname}")
	public Map<String, Object> FuzzyQuery(@PathVariable("currentPage") Integer currentPage,
			@PathVariable("pageSize") Integer pageSize, @PathVariable("realname") String realname) {

		PageHelper.startPage(currentPage, pageSize);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<User> list = null;
		returnMap.put("content", list);
		list = userService.FuzzyQueryByRealname(realname);

		returnMap.put("content", list);
		return returnMap;
	}
	
	/**
	 * 
	 * 按用户类型通过姓名模糊查询
	 * @param currentPage
	 * @param pageSize
	 * @param realname
	 * @param choice
	 * @return
	 */
	@GetMapping("FuzzyQuery/{currentPage}/{pageSize}/{realname}/{choice}")
	public Map<String, Object> FuzzyQueryByChoice(@PathVariable("currentPage") Integer currentPage,
			@PathVariable("pageSize") Integer pageSize, @PathVariable("realname") String realname,
			@PathVariable("choice") String choice) {

		PageHelper.startPage(currentPage, pageSize);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<User> list = null;
		returnMap.put("content", list);
		list = userService.FuzzyQueryByChioce(realname, choice);

		returnMap.put("content", list);
		return returnMap;
	}
}

