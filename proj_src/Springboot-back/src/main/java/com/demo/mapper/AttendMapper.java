package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.entity.Attend;

public interface AttendMapper {
	
	@Options(useGeneratedKeys = true,keyProperty = "username")
	@Insert("insert into Attend(username,realname,classes,dept,early,late,vacation,truancy,score,propose) "
			+ "values(#{username},#{realname},#{classes},#{dept},#{early},#{late},#{vacation},#{truancy},#{score},#{propose})")
	public void insert(Attend attend );
	
	@Update("Update Attend Set realname = #{realname },classes = #{classes }, dept = #{dept }, "
			+ "  early = #{early }, late = #{late },vacation = #{vacation } ,truancy = #{truancy },"
			+ " score = #{score }, propose = #{propose } where username=#{username}")
	public void save( Attend attend );
	
	@Select("Select * from Attend where username=#{username}")
	public Attend findAllByUsername(String username);
	
	@Select("Select * from Attend where dept=#{dept}")
	public List<Attend> findAllByDept(String dept);
	
	@Select("Select * from Attend where classes=#{classes}")
	public List<Attend> findAllByClass(String classes);
	
	@Select("Select * from Attend")
	public List<Attend> findAll();
	
	@Select("SELECT * FROM Attend where realname Like concat('%','${newrealname}','%')")
	public List<Attend> FuzzyQueryByRealname( String newrealname);
	
	@Select("SELECT * FROM Attend where realname Like concat('%','${newrealname}','%') and classes=#{newclasses}")
	public List<Attend> FuzzyQueryByClasses( String newrealname , String newclasses);
	
	@Delete("delete from Attend Where username = #{username}")
	public void delAttend(String username);
	
	@Delete("delete from Attend ")
	public void delAllAttend();
}
