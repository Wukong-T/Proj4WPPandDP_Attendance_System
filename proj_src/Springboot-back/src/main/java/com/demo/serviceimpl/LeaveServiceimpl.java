package com.demo.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Attend;
import com.demo.entity.Audit;
import com.demo.entity.Data;
import com.demo.entity.Leave;
import com.demo.mapper.AttendMapper;
import com.demo.mapper.AuditMapper;
import com.demo.mapper.DataMapper;
import com.demo.mapper.LeaveMapper;
import com.demo.service.LeaveService;

@Service
public class LeaveServiceimpl implements LeaveService {
	@Autowired
	private DataMapper dataMapper;
	@Autowired
	private LeaveMapper leaveMapper;
	@Autowired
	private AuditMapper auditMapper;
	
	@Autowired
	private AttendMapper attendMapper;
	
	@Override
	public String InsertLeave(Leave leave) {
		// TODO Auto-generated method stub
		String username = leave.getUsername();
		List<Leave> list = leaveMapper.findAllByUsername(username);
		Attend attend = attendMapper.findAllByUsername(username);
		if(attend == null) { return "error";}else {
		if(list.isEmpty()==true) {
			leave.setFrequency(1);
			attend.setVacation(1);
			attendMapper.save(attend);
			leaveMapper.insert(leave);
			return "success";
		}
		else {
			String checkState = list.get(list.size()-1).getState();
			Integer frequency = list.get(list.size()-1).getFrequency();
			
			if(checkState.equals("审核通过")||checkState.equals("审核未通过")) {
				leave.setFrequency(frequency+1);
				attend.setVacation(frequency+1);
				attendMapper.save(attend);
				leaveMapper.insert(leave);
				return "success";
			}else {
				return "repeat";
			}
		}}
	}
	@Override
	public Leave ShowOwnLeave(String username) {
		// TODO Auto-generated method stub
		Leave oldleave = new Leave();
		List<Leave> list = leaveMapper.findAllByUsername(username);
		
		if(list.isEmpty() == false) {
			Integer index = list.size()-1;
			oldleave = list.get(index);
			return oldleave;
		}else {
			
			return null;
		}
	}
	
	@Override
	public List<Leave> showLeave(String username,String state) {
		// TODO Auto-generated method stub
		Data data = dataMapper.findAllByUsername(username);
		String checkname = data.getRealname();
		String choice = data.getChoice();
		if(state.equals("全部")) {
			List<Leave> list1 = leaveMapper.findByCheckname(checkname, "审核通过");
			List<Leave> list2 = leaveMapper.findByCheckname(checkname, "审核未通过");
			
			list1.addAll(list2);
			List<Leave> pageList = list1;
			
			if(choice.equals("部门经理")) {
				List<Leave> list3 = leaveMapper.findByCheckname(checkname, "副经理待审");
				pageList.addAll(list3);
				return pageList;
			}else if(choice.equals("副经理")){
				
				List<Leave> list4 = leaveMapper.findByCheckname(checkname, "总经理待审");
				pageList.addAll(list4);
				return pageList;
			}else {
				return list1;
			}	
		}else {
			List<Leave> pageList = leaveMapper.findByCheckname(checkname, state);
			
			return pageList;
		}
		
	}
	
	
	@Override
	public List<Leave> showAOldLeave(String username,Integer total,Integer size) {
		// TODO Auto-generated method stub
		List<Leave> oldleave = new ArrayList<Leave>();
		List<Leave> list = leaveMapper.findAllByUsername(username);
		if(list.isEmpty() == false) {
			if(total >= size) {
				for(int i = 0;i<list.size()-1;i++) {
					oldleave.add(list.get(i));
				}
				return oldleave;
			}else {
				for(int i = 0;i<list.size();i++) {
					oldleave.add(list.get(i));
				}
				return oldleave;
			}
		}else {
			return oldleave;
			}
	}
	
	@Override
	public List<Leave> findAll(String username) {
		// TODO Auto-generated method stub
		return leaveMapper.findAllByUsername(username);
	}
	
	@Override
	public void deleteLeave(Integer id) {
		// TODO Auto-generated method stub
			leaveMapper.delLeave(id);
	}
	@Override
	public Leave findAllById(Integer id) {
		// TODO Auto-generated method stub
		return leaveMapper.findAllById(id);
	}
	@Override
	public String UpdateLeave(Leave leave) {
		// TODO Auto-generated method stub
		String classes = leave.getClasses();
		String checkname = auditMapper.FindCheckname(classes);
		Integer id = leave.getId();
		Leave test = leaveMapper.findAllById(id);
		if(test != null) {
			String state = leave.getState();
			if(state.equals("审核通过")||state.equals("审核未通过")) {
				return "over";	
			}else {
				leave.setAuditLeader("");
				leave.setAuditTeacher("");
				leave.setAuditMaster("");
				
				leave.setState("审核中");
				leave.setCheckname(checkname);
				leaveMapper.saveLeave(leave);
				return "success";	
			}
		}else {
			return "empty";
		}
	}
	@Override
	public List<Audit> FindClass(String checkname) {
		// TODO Auto-generated method stub
		List<Audit> list = auditMapper.FindClass(checkname);
			
			return list;	
	}
	@Override
	public String FindCheckname(String classes) {
		// TODO Auto-generated method stub
		String checkname = auditMapper.FindCheckname(classes);
			return checkname;
	}
	@Override
	public List<Leave> showExamLeave(String username) {
		// TODO Auto-generated method stub
		Data data = dataMapper.findAllByUsername(username);
		String choice = data.getChoice();
		String checkname = data.getRealname();
		String dept = data.getDept();
		switch(choice) {
			case "部门经理":
				return leaveMapper.findByCheckname(checkname, "审核中");
			case "副经理":
				return leaveMapper.findAllByDept(dept, "副经理待审");
			case "总经理":
				return leaveMapper.findAllByState("总经理待审");
			default:
				return null;
		}
	}
	@Override
	public String examLeave(Leave leave,String username) {
		// TODO Auto-generated method stub
		Integer id = leave.getId();
		String state = leave.getState();
		Data data = dataMapper.findAllByUsername(username);
		String newcheckname = data.getRealname();
		String choice = data.getChoice();
		Leave test = leaveMapper.findAllById(id);
		if(test != null) {
			String duration = test.getDuration();
			if(state.equals("审核未通过")) {
				leave.setCheckname(newcheckname);
				leaveMapper.saveLeave(leave);
				return "error";
			}else if(state.equals("审核通过")){
				if(duration.equals("一天内")||(duration.equals("一天以上三天以内"))){
					leave.setCheckname(newcheckname);
					leaveMapper.saveLeave(leave);
					return "success";
				}else if(duration.equals("三天以上一周以内")){
					if(choice.equals("副经理")) {
						leave.setCheckname(newcheckname);
						leaveMapper.saveLeave(leave);
						return "success";
					}else if(choice.equals("部门经理")) {
						leave.setState("副经理待审");
						leave.setCheckname(newcheckname);
						leaveMapper.saveLeave(leave);
						return "sendDept";
					}else {
						return "false";
					}
				}else if(duration.equals("一周以上四周以内")){
						if(choice.equals("总经理")) {
							leave.setCheckname(newcheckname);
							leaveMapper.saveLeave(leave);
							return "success";
						}else if(choice.equals( "副经理")) {
							leave.setState("总经理待审");
							leave.setCheckname(newcheckname);
							leaveMapper.saveLeave(leave);
							return "sendoffice";
						}else if(choice.equals("部门经理")) {
							leave.setState("副经理待审");
							leave.setCheckname(newcheckname);
							leaveMapper.saveLeave(leave);
							return "sendDept";
						}else {
							return "false";
						}
					}else {
						return "false";
					}
				}else {
					return "noResponse";
				}
			}else {
				return "empty";
			}
		
		}
	@Override
	public void delClass(String classes) {
		// TODO Auto-generated method stub
		 auditMapper.delAudit(classes);
	}
	@Override
	public String insertAudit(Audit audit) {
		// TODO Auto-generated method stub
		String classes = audit.getClasses();
		Audit test = auditMapper.findAll(classes);
		if(test == null) {
			String checkname = audit.getCheckname();
			if(checkname == null) {
				return "empty";
			}else {
				auditMapper.insert(audit);
				return "success";
			}
		}else {
			return "repeat";
		}
		
	}
	@Override
	public void updateAuditCheckname(Data data) {
		// TODO Auto-generated method stub
		//修改请假表中的审批人
		String username = data.getUsername();
		String realname = data.getRealname();
		Data test = dataMapper.findAllByUsername(username);
		String checkname = test.getRealname();
		if(test != null) {
			List<Audit> audit = auditMapper.FindClass(checkname);
			
			for(int i = 0;i < audit.size();i++) {
				Audit result =audit.get(i);
				result.setCheckname(realname);
				auditMapper.saveAudit(result);
			}
		}

	}
	@Override
	public List<Leave> startPage(List<Leave> list, Integer currentPage, Integer pageSize) {
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
