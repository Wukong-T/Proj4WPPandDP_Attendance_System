package com.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.entity.Notice;

@Service
public interface NoticeService {
	public String sendNotice(Notice notice);
	public Notice showNotice() ;
	
	public String saveNotice(Notice notice);
	public List<Notice> showNoticebyRealname(String realname) ;
	public List<Notice> showAll() ;
	public Notice showById(Integer id);
	
	public void delNotice(Integer id);
	public void delAllNotice();
}
