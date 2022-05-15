package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.entity.Leave;

public interface LeaveMapper {
	@Options(useGeneratedKeys = true,keyProperty = "id")
	@Insert("insert into leaveForm(id,username,realname,classes,dept,duration,startTime,endTime,reason,checkname,state,frequency"
			+ ",auditTeacher,auditLeader,auditMaster)"
			+ " values(#{id},#{username},#{realname},#{classes},#{dept},#{duration},#{startTime},#{endTime},#{reason},#{checkname},#{state},#{frequency}"
			+ ",#{auditTeacher},#{auditLeader},#{auditMaster})")
	public void insert(Leave leave);
	
	@Select("Select * from leaveForm where username=#{username}")
	public List<Leave> findAllByUsername(String username);
	
	@Select("Select * from leaveForm where checkname=#{checkname}")
	public List<Leave> findAllByCheckname(String checkname);
	
	@Select("Select * from leaveForm where checkname=#{checkname} And state=#{state}")
	public List<Leave> findByCheckname(String checkname,String state);
	
	
	@Select("Select * from leaveForm where dept=#{dept}  And state=#{state}")
	public List<Leave>  findAllByDept(String dept,String state);
	
	@Select("Select * from leaveForm where state=#{state}")
	public List<Leave>  findAllByState(String state);
	
	@Select("Select * from leaveForm where id=#{id}")
	public Leave findAllById(Integer id);
	
			
	@Update("Update leaveForm Set username = #{username}, realname = #{realname}, classes = #{classes},"
			+ " dept = #{dept}, duration = #{duration}, startTime = #{startTime} , endTime = #{endTime} "
			+ ", reason = #{reason} , checkname = #{checkname} "
			+ ",auditTeacher = #{auditTeacher},auditLeader = #{auditLeader},auditMaster = #{auditMaster}"
			+ ", state = #{state}, frequency = #{frequency} where id=#{id}")
	public void saveLeave( Leave leave);
	
	@Delete("delete from leaveForm Where id = #{id}")
	public void delLeave(Integer id);
	
}
