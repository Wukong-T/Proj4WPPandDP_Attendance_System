package com.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Audit;
import com.demo.entity.Leave;
import com.demo.service.LeaveService;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("/leave")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	
	/**
	 * 发送申请表
	 * @param leave
	 * @return
	 */
	@PostMapping("/sendLeave")
	public String sendLeave(@RequestBody Leave leave) {
		return leaveService.InsertLeave(leave);
		}
	
	/**
	 * 修改申请表
	 * @param leave
	 * @return
	 */
	@PostMapping("/updateLeave")
	public String updateLeave(@RequestBody Leave leave) {
		return leaveService.UpdateLeave(leave);
	}
	
	
	/**
	 *查找单个申请表 
	 * @param id
	 * @return
	 */
	@GetMapping("/findAllById/{id}")
	public Leave findAllByUsername(@PathVariable("id") Integer id) {
		return leaveService.findAllById(id);
	}

	
	/**
	 * 查询申请表当前状态
	 * @param username
	 * @return
	 */
	@GetMapping("/ShowOwnLeave/{username}")
	public Map<String, Object> ShowOwnLeave(@PathVariable("username") String username) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Leave> list = new ArrayList<Leave>();
		Leave ownleave =  leaveService.ShowOwnLeave(username);
		if(ownleave == null) {
			return returnMap;	
		}else {
			returnMap.put("content", list);
			list.add(ownleave);
			returnMap.put("content", list);
			return returnMap;	
		}
	}
	
	/**
	 * 查询以往申请记录
	 * @param currentPage
	 * @param pageSize
	 * @param username
	 * @return
	 */
	@GetMapping("/ShowOldLeave/{currentPage}/{pageSize}/{username}")
	public Map<String, Object> ShowOldLeave(@PathVariable("currentPage") Integer currentPage,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("username") String username) {
		List<Leave> test = leaveService.findAll(username);
		
		Integer size = test.size();//数据库中所有请假信息的总数
		
		PageHelper.startPage(currentPage, pageSize);
		Integer total = currentPage * pageSize; //分页可展示的总数
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Leave> list = null;
		returnMap.put("content", list);
		list = leaveService.showAOldLeave(username,total,size);
		returnMap.put("content", list);

		return returnMap;
		
		}
	
	/**
	 * 按审核状态查询以往审核记录
	 * @param currentPage
	 * @param pageSize
	 * @param username
	 * @return
	 */
	@GetMapping("/ShowLeave/{currentPage}/{pageSize}/{username}/{state}")
	public Map<String, Object> ShowLeave(@PathVariable("currentPage") Integer currentPage,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("username") String username,
			@PathVariable("state") String state){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Leave> list = null;
		List<Leave> pagelist = null;
			returnMap.put("content", list);
			list = leaveService.showLeave(username, state);
			
			pagelist = leaveService.startPage(list, currentPage, pageSize);
			
			returnMap.put("content", pagelist);
			return returnMap;
		
		}

	/**
	 * 删除申请表
	 * @param id
	 */
	@DeleteMapping("deleteLeave/{id}")
	public void deleteData(@PathVariable("id") Integer id) {
		leaveService.deleteLeave(id);
	}
	
	/**
	 *
	 * @param checkname
	 * @return
	 */
	@GetMapping("/findClass/{checkname}")
	public List<String>  findClass(@PathVariable("checkname") String checkname) {
		List<Audit> audit =  leaveService.FindClass(checkname);
		List<String> list = new ArrayList<String>();
		for(int i = 0 ; i < audit.size(); i++) {
			String classes  =  audit.get(i).getClasses();
			list.add(classes);
		}
		return list;
	}
	/**
	 *
	 * @param classes
	 */
	@DeleteMapping("delClass/{classes}")
	public void delClass(@PathVariable("classes") String  classes) {
		 leaveService.delClass(classes);
	}
	/**
	 *
	 * @param
	 * @param
	 * @return
	 */
	@PostMapping("/insertClass")
	public String insertClass(@RequestBody Audit aidit) {
			return leaveService.insertAudit(aidit);		

	}
	
	/**
	 *
	 * @param classes
	 * @return
	 */
	@GetMapping("/findCheckname/{classes}")
	public String findCheckname(@PathVariable("classes") String classes) {
		String checkname = leaveService.FindCheckname(classes);
		if(checkname == null) {
			return "error";
		} else {
			return checkname;
		}
	}
	
	/**
	 * 查询需要审核的请假表
	 * @param currentPage
	 * @param pageSize
	 * @param username
	 * @return
	 */
	@GetMapping("/showExamLeave/{currentPage}/{pageSize}/{username}")
	public Map<String, Object> showExamLeave(@PathVariable("currentPage") Integer currentPage,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("username") String username) {
		PageHelper.startPage(currentPage, pageSize);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Leave> list =  leaveService.showExamLeave(username);
		if(list == null) {
			return returnMap;	
		}else {
			returnMap.put("content", list);

			return returnMap;	
		}
	}
	
	/**
	 * 审核请假表
	 * @param username
	 * @param leave
	 * @return
	 */
	@PostMapping("/examLeave/{username}")
	public String examLeave(@PathVariable("username") String username,@RequestBody Leave leave) {
		String StuUsername = leave.getUsername();
		Leave ownleave =  leaveService.ShowOwnLeave(StuUsername);
		Integer oldId = ownleave.getId();
		Integer id = leave.getId();
		if(oldId == id) {
			return leaveService.examLeave(leave, username);
		}else {
			return "repeat";
			
		}	
	}

}
