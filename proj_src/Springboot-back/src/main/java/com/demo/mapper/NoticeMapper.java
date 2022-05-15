package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.entity.Notice;

public interface NoticeMapper {
	@Options(useGeneratedKeys = true,keyProperty = "id")
	@Insert("insert into Notice(realname,title,content,date) values(#{realname},#{title},#{content},#{date})")
	public void insert(Notice notice);
	
	@Update("update Notice Set realname = #{realname} , title = #{title}"
			+ " ,content = #{content} ,date = #{date} where id=#{id}")
	public void saveNotice(Notice notice);
	
	@Update("update Notice Set realname = #{newrealname} where realname=#{realname}")
	public void updateRealnameNotice(String newrealname,String realname);
	
	@Select("Select * from Notice where realname=#{newrealname}")
	public List<Notice> findAllByRealname(String newrealname);
	
	@Select("Select * from Notice ")
	public List<Notice> findAll();
	
	@Select("Select * from Notice where id= #{id}")
	public Notice findAllById(Integer id);
	
	@Delete("delete from Notice Where id = #{id}")
	public void deleteById(Integer id);
	
	@Delete("delete from Notice")
	public void deleteAll();
}
