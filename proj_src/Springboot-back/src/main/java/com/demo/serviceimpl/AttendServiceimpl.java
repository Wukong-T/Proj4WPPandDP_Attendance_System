package com.demo.serviceimpl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Attend;
import com.demo.entity.Audit;
import com.demo.entity.Census;
import com.demo.entity.Data;
import com.demo.entity.Leave;
import com.demo.mapper.AttendMapper;
import com.demo.mapper.AuditMapper;
import com.demo.mapper.DataMapper;
import com.demo.mapper.LeaveMapper;
import com.demo.service.AttendService;
@Service
public class AttendServiceimpl implements AttendService {

	@Autowired
	private DataMapper dataMapper;
	
	@Autowired
	private AttendMapper attendMapper;
	
	@Autowired
	private LeaveMapper leaveMapper;
	
	@Autowired
	private AuditMapper auditMapper;
	
	@Override
	public String insertAttend(Attend attend) {
		// TODO Auto-generated method stub
		String username = attend.getUsername();
		Integer early = attend.getEarly();
		Integer late = attend.getLate();
		Integer truancy = attend.getTruancy();
		Data data = dataMapper.findAllByUsername(username);
		List<Leave> leave = leaveMapper.findAllByUsername(username);
		if(data == null) {
			return "empty";
		}else {
			String choice = data.getChoice();
			if(choice.equals("员工")) {
				String classes = data.getClasses();
				attend.setClasses(classes);
				String dept = data.getDept();
				attend.setDept(dept);
				 if(leave.isEmpty() == true){
					 attend.setVacation(0);
				}else {
				Integer vacation = leave.get(leave.size()-1).getFrequency();
				attend.setVacation(vacation);}
				Attend test = attendMapper.findAllByUsername(username);
				if(test == null) {
					Integer score = 100 - (early*2) - (late*3) - (truancy*5);
					if(score < 0) {score = 0;}
					attend.setScore(score);
					attendMapper.insert(attend);
					return "success";
				}else {
					Integer score = 100 - (early*2) - (late*3) - (truancy*5);
					if(score < 0) {score = 0;}
					attend.setScore(score);
					attendMapper.save(attend);
					return "repeat";
				}
			}else {
				return "empty";
			}

		}
	}

	@Override
	public Attend findAllByUsername(String username) {
		// TODO Auto-generated method stub
		return attendMapper.findAllByUsername(username);
	}

	@Override
	public  List<Attend> findAll(String realname) {
		// TODO Auto-generated method stub
		List<Audit> audit = auditMapper.FindClass(realname);
		List<Attend> attend = new ArrayList<Attend>();
		if(audit.isEmpty() == true) {
			return attend;
		}else {
			for(int i = 0 ; i < audit.size();i++) {
				String classes = audit.get(i).getClasses();
				List<Attend> list = attendMapper.findAllByClass(classes);
					if(list.isEmpty() == false) {
						attend.addAll(list);
					}
				}
			return attend;
		}

	}

	@Override
	public List<Attend> findByDept(String dept) {
		// TODO Auto-generated method stub
		return attendMapper.findAllByDept(dept);
	}

	@Override
	public List<Attend> findAll() {
		// TODO Auto-generated method stub
		return attendMapper.findAll();
	}

	@Override
	public List<Attend> findByClass(String classes) {
		// TODO Auto-generated method stub
		
		return attendMapper.findAllByClass(classes);
		
	}

	@Override
	public Integer[] showChartStudent(String username) {
		// TODO Auto-generated method stub
		Attend attend = attendMapper.findAllByUsername(username);
		Integer[] list = {0,0,0,0};
//		list[0] = 0;list[1] = 0;list[2] = 0;list[3] = 0;
		if(attend == null) {
			return list;
		}else {
			Integer early = attend.getEarly();
			Integer late = attend.getLate();
			Integer vacation = attend.getVacation();
			Integer truancy= attend.getTruancy();
			list[0] = early;
			list[1] = late;
			list[2] = vacation;
			list[3] = truancy;
			return list;
		}
	}

	@Override
	public void saveAttend(Attend attend) {
		// TODO Auto-generated method stub
		String username = attend.getUsername();
		Integer early = attend.getEarly();
		Integer late = attend.getLate();
		Integer truancy = attend.getTruancy();
		List<Leave> leave = leaveMapper.findAllByUsername(username);
		 if(leave.isEmpty() == true){
			 attend.setVacation(0);
		 }else {
			Integer vacation = leave.get(leave.size()-1).getFrequency();
			attend.setVacation(vacation);
		}
			Integer score = 100 - (early*2) - (late*3) - (truancy*5);
			if(score < 0) {score = 0;}
			attend.setScore(score);
			attendMapper.save(attend);
	}

	@Override
	public void delAttend(String username) {
		// TODO Auto-generated method stub
		attendMapper.delAttend(username);
	}

	@Override
	public List<Census> showChatrAttend(String classes) {
		// TODO Auto-generated method stub
		List<Attend> test = attendMapper.findAllByClass(classes);
		Integer[] list = {0,0,0,0};
		List<Census> census  =  new ArrayList<Census>();
		if(test.isEmpty() ==  false) {
			for(int i = 0 ;i <test.size();i++) {
				Attend attend = test.get(i);
				Integer early = attend.getEarly();
				Integer late = attend.getLate();
				Integer vacation = attend.getVacation();
				Integer truancy= attend.getTruancy();
				if(early != 0){list[0] ++;}
				if(late !=  0){list[1] ++;}
				if(vacation != 0) {list[2] ++;}
				if(truancy != 0) {list[3] ++;}					
			}
			Census census1 =   new Census();
			census1.setValue(list[0]);
			census1.setName("早退人数");
			census.add(census1);

			Census census2 =  new Census();
			census2.setValue(list[1]);
			census2.setName("迟到人数");
			census.add(census2);

			Census census3 =   new Census();
			census3.setValue(list[2]);
			census3.setName("请假人数");
			census.add(census3);
			
			Census census4 =  new Census();
			census4.setValue(list[3]);
			census4.setName("旷工人数");
			census.add(census4);
			return census;
		}else {
			return census;
		}

	}

	@Override
	public Integer[] showChatrScore(String classes) {
		// TODO Auto-generated method stub
		List<Attend> test = attendMapper.findAllByClass(classes);
		Integer[] list = {0,0,0,0};
		if(test.isEmpty() ==  false) {
			for(int i = 0 ;i <test.size();i++) {
				Attend attend = test.get(i);
				Integer score = attend.getScore();
				if(score < 60 &&score > 0){
					list[0] ++;
				}else if(score >= 60 && score < 75 ){
					list[1] ++;	
				}else if(score >= 75 && score < 90 ){
					list[2] ++;	
				}else {
					list[3] ++;	
				}

			}
			
			return list;
		}else {
			return list;
		}
	}

	@Override
	public List<Attend> FuzzyQueryByRealname(String newrealname) {
		// TODO Auto-generated method stub
		return attendMapper.FuzzyQueryByRealname(newrealname);
	}


	@Override
	public List<Attend> FuzzyQueryByChioce(String newrealname, String newclasses) {
		// TODO Auto-generated method stub
		return attendMapper.FuzzyQueryByClasses(newrealname, newclasses);
	}

	@Override
	public void delAttend() {
		// TODO Auto-generated method stub
		attendMapper.delAllAttend();
	}

	@Override
	public List<Attend> startPage(List<Attend> list, Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		if (list == null || list.isEmpty()) {
			 return Collections.emptyList();
       }

		Integer totalCount = list.size();
		Integer totalPage = (totalCount + pageSize - 1) / pageSize;//总页数
		if(currentPage > totalPage) {
			 return Collections.emptyList();
		}else {
			int fromIndex = (currentPage - 1) * pageSize;
	        if (fromIndex >= list.size()) {
	            return Collections.emptyList();//空数组
	        }
	        if (fromIndex < 0) {
	            return Collections.emptyList();//空数组
	        }
	        int toIndex = currentPage * pageSize;
	        if (toIndex >= list.size()) {
	            toIndex = list.size();
	        }
	        return list.subList(fromIndex, toIndex);
	        
		}
	}



}
