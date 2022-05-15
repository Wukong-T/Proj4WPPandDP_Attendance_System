package com.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.entity.Attend;
import com.demo.entity.Census;
@Service
public interface AttendService {
	public String insertAttend(Attend attend);
	public Attend findAllByUsername(String username);
	
	public List<Attend> findAll(String realname);
	public List<Attend> findByDept(String dept);
	public List<Attend> findAll();
	public List<Attend> findByClass(String classes);
	
	public Integer[] showChartStudent(String username);
	
	public List<Census> showChatrAttend(String classes);
	public Integer[]   showChatrScore(String classes);
	
	public void saveAttend(Attend attend);
	
	public List<Attend>FuzzyQueryByRealname( String newrealname);
	public List<Attend> FuzzyQueryByChioce( String newrealname , String newclasses);
	
	public void delAttend(String username);
	public void delAttend();
	
	public List<Attend> startPage(List<Attend> list, Integer currentPage, Integer pageSize);
}
