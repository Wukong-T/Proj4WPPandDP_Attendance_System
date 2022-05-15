package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.entity.Data;

public interface DataMapper {
	@Options(useGeneratedKeys = true,keyProperty = "username")
	@Insert("insert into Data(username,realname,sex,age,classes,dept,choice) values(#{username},#{realname},#{sex},#{age},#{classes},#{dept},#{choice})")
	public void insert(Data data);
	
	@Select("Select * from Data")
	public List<Data> findAll();
	
	@Select("Select * from Data where choice=#{newchoice}")
	public List<Data> findAllByChoice(String newchoice);
	
	@Select("Select * from Data where username=#{username}")
	public Data findAllByUsername(String username);
	
	@Select("Select * from Data where realname=#{realname}")
	public List<Data> findAllByRealname(String realname);
	
	
	@Select("SELECT * FROM Data where realname Like concat('%','${newrealname}','%')")
	public List<Data> FuzzyQueryByRealname( String newrealname);
	
	@Select("SELECT * FROM Data where realname Like concat('%','${newrealname}','%') and choice=#{newchoice}")
	public List<Data> FuzzyQueryByChioce( String newrealname , String newchoice);
	
	@Update("Update Data Set realname = #{realname }, sex = #{sex }, age = #{age },"
			+ " classes = #{classes }, dept = #{dept }, choice = #{choice } where username=#{username}")
	public void save( Data data);
	
	@Delete("delete from Data Where username = #{username}")
	public void delData(String username);

	@Delete("delete from Data ")
	public void delAllData();
	
	@Delete("delete from Data Where choice = #{newchoice}")
	public void delDataByChoice(String newchoice);
}
