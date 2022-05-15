package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.entity.User;

public interface UserMapper {
	
	@Options(useGeneratedKeys = true,keyProperty = "username")
	@Insert("insert into User(username,password,email,choice,verifyCode,realname) values(#{username},#{password},#{email},#{choice},#{verifyCode},#{realname})")
	public void insert(User user);
	
	@Select("Select * from User")
	public List<User> findAll();
	
	@Select("Select * from User where choice=#{newchoice}")
	public List<User> findAllByChoice(String newchoice);
	
	@Select("Select * FROM User where realname Like concat('%','${newrealname}','%')")
	public List<User> FuzzyQueryByRealname( String newrealnam);
	
	@Select("SELECT * FROM User where realname Like concat('%','${newrealname}','%') and choice=#{newchoice}")
	public List<User> FuzzyQueryByChioce( String newrealname , String newchoice);
	
	@Select("Select * from User where username=#{username}")
	public User findAllByUsername(String username);
	
	@Update("update User Set password=#{newencrypassword} where username=#{username}")
	public void updatePassword(String username, String newencrypassword);

	@Update("update User Set verifyCode = #{newCode} where username=#{username}")
	public void updateCode( String username,String newCode);
	
	@Update("update User Set email = #{newemail} where username=#{username}")
	public void updateEmail( String newemail,String username);
	
	@Update("update User Set choice = #{newchoice},realname = #{newrealname} where username=#{username}")
	public void updateChioceAndRealname( String newchoice,String username,String newrealname);
	
	@Delete("delete from User Where username = #{username}")
	public void delUser(String username);
	
	@Delete("delete from User Where choice = #{newchoice}")
	public void delUserByChoice(String newchoice);
	
	@Delete("delete from User")
	public void delAllUser();
	
}
