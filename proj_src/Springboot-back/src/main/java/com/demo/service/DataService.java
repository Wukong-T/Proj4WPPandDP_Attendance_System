package com.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.entity.Data;

@Service
public interface DataService {
	
	public String InsertData(Data data);
	public String getChoice(String username);
	public Data findAllByUsername(String username);
	public List<Data> findAllByChoice(String newchoice);
	public List<Data> FuzzyQueryByRealname( String newrealname);
	public List<Data> FuzzyQueryByChioce( String newrealname , String newchoice);
	public List<Data>  findAll();
	public void deleteData(String username);
	public void deleteData();
	public void deleteDataByChoice(String choice);
	public void saveData(Data data);

}
