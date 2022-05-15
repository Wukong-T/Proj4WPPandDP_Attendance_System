package com.demo.service;

import java.util.List;

import com.demo.entity.User;

public interface UserService {
	
	public User findAllByUsername(String username);
	public String insertUser(User user);
	public String matchesEncoder(String encoder,String encryCoder);
	public String loginAdmin(User user);
	public List<User> findAll();
	public List<User> findAllByChoice(String newchoice);
	public void deleteByUsername(String username);
	public void deleteAll();
	public void updateCode(String username,String verifyCode);
	public void updatePassword( String username,String newpassword);
	public void updateEmail(String username,String email);
	public void updateChioceAndRealname(String username ,String chioce,String realname) ;
	
	public List<User> FuzzyQueryByRealname( String newrealname);
	public List<User> FuzzyQueryByChioce( String newrealname , String newchoice);

}
