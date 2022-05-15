package com.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.entity.Audit;
import com.demo.entity.Data;
import com.demo.entity.Leave;
@Service
public interface LeaveService {
	public String InsertLeave(Leave leave);
	public String UpdateLeave(Leave leave);
	
	public  Leave ShowOwnLeave(String username);
	public List<Leave> showAOldLeave(String username,Integer total,Integer size);
	public List<Leave>	findAll(String username);
	public Leave findAllById(Integer id);
	public List<Leave> showLeave(String username,String state) ;
	public void deleteLeave(Integer id);
	
	public  List<Leave> startPage(List<Leave> list, Integer currentPage, Integer pageSize);
	
	public List<Leave> showExamLeave(String username);
	public String examLeave(Leave leave,String username);
	
	public void updateAuditCheckname(Data data);
	
	public List<Audit> FindClass(String checkname);
	public String FindCheckname(String classes);
	public void delClass(String classes);
	public String insertAudit(Audit audit);
}
