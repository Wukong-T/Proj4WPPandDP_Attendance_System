package com.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Audit;
import com.demo.entity.Data;
import com.demo.entity.Leave;
import com.demo.mapper.AuditMapper;
import com.demo.mapper.DataMapper;
import com.demo.mapper.LeaveMapper;
import com.demo.mapper.NoticeMapper;
import com.demo.service.DataService;

@Service
public class DataServiceimpl implements DataService {
	@Autowired
	private DataMapper dataMapper;
	
	@Autowired
	private LeaveMapper leaveMapper;
	
	
	@Autowired
	private AuditMapper auditMapper;
	
	@Autowired
	private NoticeMapper noticeMapper;
	

	@Override
	public String getChoice(String username) {
		// TODO Auto-generated method stub
		Data test = dataMapper.findAllByUsername(username);
		if(test == null) {
			return "empty";
		}else {
			String choice = test.getChoice();
		
			return choice;
		}
	}
	
	
	@Override
	public List<Data> findAll() {
		// TODO Auto-generated method stub
		List<Data> list = null;
		list = dataMapper.findAll();
		return list;
	}


	@Override
	public Data findAllByUsername(String username) {
		// TODO Auto-generated method stub
		return dataMapper.findAllByUsername(username);
	}


	@Override
	public void deleteData(String username) {
		// TODO Auto-generated method stub
		Data data = dataMapper.findAllByUsername(username);
		String realname = data.getRealname();
		List<Audit> list = auditMapper.FindClass(realname);
		if(list.isEmpty() == false) {
			for(int i = 0;i < list.size(); i++) {
				String classes = list.get(i).getClasses();
				auditMapper.delAudit(classes);
			}
			dataMapper.delData(username);
		}
		dataMapper.delData(username);
		
	}


	@Override
	public void saveData(Data data) {
		// TODO Auto-generated method stub
		String username = data.getUsername();
		String realname = data.getRealname();
		String choice = data.getChoice();
		
		if(choice.equals("员工")) {
			List<Leave> leave = leaveMapper.findAllByUsername(username);
			if(leave != null) {
			for(int i = 0; i<leave.size();i++ ) {
				Leave result = leave.get(i);
				result.setRealname(realname);
				leaveMapper.saveLeave(result);
			}
			dataMapper.save(data);
		}
		}else {
			Data test = dataMapper.findAllByUsername(username);
			String checkname = test.getRealname();
			noticeMapper.updateRealnameNotice(realname, checkname);
			//修改请假表中的审批人;公告管理的发布人
			List<Leave> leave = leaveMapper.findAllByCheckname(checkname);
			if(leave != null) {
			for(int i = 0; i<leave.size();i++ ) {
				Leave result = leave.get(i);
				result.setCheckname(realname);
				leaveMapper.saveLeave(result);		
			}
			dataMapper.save(data);
			}
			
	    }

	}


	@Override
	public List<Data> findAllByChoice(String newchoice) {
		// TODO Auto-generated method stub
		List<Data> list= dataMapper.findAllByChoice(newchoice);
		return list;
	}

	@Override
	public String  InsertData(Data data) {
		// TODO Auto-generated method stub
		String username = data.getUsername();
		Data test = dataMapper.findAllByUsername(username);
		if(test == null){
			String choice = data.getChoice();
			String realname = data.getRealname();
			String classes = data.getClasses();
			String dept = data.getDept();
			List<Data> list = dataMapper.findAllByRealname(realname);
			for(int i = 0;i<list.size();i++) {
				String checkusername =list.get(i).getUsername();
				String checkrealname =  list.get(i).getRealname();
				String checkClasses = list.get(i).getClasses();
				String checkDept = list.get(i).getDept();
				String checkChice = list.get(i).getChoice();
				if(checkrealname.equals(realname)
						&&checkClasses.equals(classes)
						&&checkDept.equals(dept)
						&&checkChice.equals(choice)) {
					dataMapper.delData(checkusername);
					dataMapper.insert(data);
				}
			}
			dataMapper.insert(data);
			return "success";	
			
		}else {
			dataMapper.save(data);
//			System.out.println("repeat");
			return "repeat";
			
		}
		
	}


	@Override
	public List<Data> FuzzyQueryByRealname(String newrealname) {
		// TODO Auto-generated method stub
		return dataMapper.FuzzyQueryByRealname(newrealname);
	}


	@Override
	public List<Data> FuzzyQueryByChioce(String newrealname, String newchoice) {
		// TODO Auto-generated method stub
		return dataMapper.FuzzyQueryByChioce(newrealname, newchoice);
	}


	@Override
	public void deleteData() {
		// TODO Auto-generated method stub
		dataMapper.delAllData();
		auditMapper.delAllAudit();
	}


	@Override
	public void deleteDataByChoice(String choice) {
		// TODO Auto-generated method stub
		dataMapper.delDataByChoice(choice);
		if(choice.equals("部门经理")) {
			auditMapper.delAllAudit();
		}
	}







}
