package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.entity.Audit;

public interface AuditMapper {
	@Options(useGeneratedKeys = true,keyProperty = "classes")
	@Insert("insert into Audit(classes,checkname) values (#{classes},#{checkname})")
	public void insert(Audit audit);
	
	@Select("Select * from Audit where checkname=#{checkname}")
	public List<Audit> FindClass(String checkname);
	
	@Select("Select checkname from Audit where classes=#{classes}")
	public String FindCheckname(String classes);
	
	@Select("Select * from Audit where classes=#{classes}")
	public Audit findAll(String classes);
	
	@Delete("delete from Audit Where classes  = #{classes}")
	public void delAudit(String classes);
	
	@Update("update Audit Set checkname = #{checkname} where classes=#{classes}")
	public void saveAudit( Audit audit);
	
	@Delete("delete from Audit ")
	public void delAllAudit();
}
