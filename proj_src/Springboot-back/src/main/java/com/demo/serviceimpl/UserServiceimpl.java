package com.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.entity.Data;
import com.demo.entity.User;
import com.demo.mapper.DataMapper;
import com.demo.mapper.UserMapper;
import com.demo.service.UserService;
import com.demo.util.RandomUtil;

@Service
public class UserServiceimpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private DataMapper dataMapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@Override
	public User findAllByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.findAllByUsername(username);
	}
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		List<User> list= userMapper.findAll();
		return list;
	}
	@Override
	public void deleteByUsername(String username) {
		// TODO Auto-generated method stub
		userMapper.delUser(username);
	}
	@Override
	public String insertUser(User user) {
		// TODO Auto-generated method stub
		String username  = user.getUsername();
		String password  = user.getPassword();
		String random = RandomUtil.getCode();
		User test = userMapper.findAllByUsername(username);		
		if(test == null) {
			Data data = dataMapper.findAllByUsername(username);
			if(data!=null) {
				String realname = data.getRealname();
				String verifyCode = passwordEncoder.encode(random);
				String encodepassword = passwordEncoder.encode(password);
				user.setPassword(encodepassword);
				user.setVerifyCode(verifyCode);
				user.setRealname(realname);
				userMapper.insert(user);
				return "success";
			}else {
				return "empty";
			}
			
		}else {
			return "repeat";
		}
	}
	@Override
	public String matchesEncoder(String encoder, String encryCoder) {
		// TODO Auto-generated method stub
		boolean matches = passwordEncoder.matches(encoder, encryCoder);
		if (matches == true) {	
				return "success";
		} else {
			return "error";	
		}
	}
	@Override
	public String loginAdmin(User user) {
		// TODO Auto-generated method stub
//		String username = user.getUsername();
		String password = user.getPassword();
		String check = user.getChoice();
		
		String choice = "管理员";
		String encrypassword = "123456";
//		String encrypassword = passwordEncoder.encode("123456");
//		boolean matches = passwordEncoder.matches(password, encrypassword);
//		if (matches == true) {
		if (encrypassword.equals(password)) {
			if (check.equals(choice)) {
//				user.setUsername("admin");
//				user.setChoice(choice);
//				user.setEmail("xytjade@163.com");
//				user.setPassword(encrypassword);
//				userMapper.insert(user);
				return "success";
			}else {
				return "check";
			}	
	}else {
		return "error";
		}
	}
	@Override
	public void updatePassword(String username, String newpassword) {
		// TODO Auto-generated method stub
		String newencrypassword = passwordEncoder.encode(newpassword);
		userMapper.updatePassword(username,newencrypassword);
	}
	@Override
	public void updateEmail(String username, String email) {
		// TODO Auto-generated method stub
		userMapper.updateEmail(email, username);
	}

	@Override
	public void updateCode(String username,String verifyCode) {
		// TODO Auto-generated method stub
		String encryverifyCode = passwordEncoder.encode(verifyCode);
        userMapper.updateCode(username,encryverifyCode);
	}
	@Override
	public void updateChioceAndRealname(String username ,String chioce,String realname) {
		// TODO Auto-generated method stub
		userMapper.updateChioceAndRealname(chioce, username, realname);
	}
	@Override
	public List<User> findAllByChoice(String newchoice) {
		// TODO Auto-generated method stub
		List<User> list= userMapper.findAllByChoice(newchoice);
		return list;
	}


	@Override
	public List<User> FuzzyQueryByRealname(String newrealname) {
		// TODO Auto-generated method stub
		return userMapper.FuzzyQueryByRealname(newrealname);
	}


	@Override
	public List<User> FuzzyQueryByChioce(String newrealname, String newchoice) {
		// TODO Auto-generated method stub
		return userMapper.FuzzyQueryByChioce(newrealname, newchoice);
	}
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		userMapper.delAllUser();
	}




	



	
	

}
