package com.demo.controller;

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

import com.demo.entity.Notice;
import com.demo.service.NoticeService;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	/**
	 * 发送公告
	 * @param notice
	 * @return
	 */
	@PostMapping("/sendNotice")
	public String sendLeave(@RequestBody Notice notice) {	
		return noticeService.sendNotice(notice);
	}
	
	/**
	 * 显示最新公告
	 * @return
	 */
	@GetMapping("/showNotice")
	public Notice showNotice() {
		return noticeService.showNotice();
	}
	/**
	 * 按id显示公告
	 * @param id
	 * @return
	 */
	@GetMapping("/showById/{id}")
	public Notice showById( @PathVariable("id") Integer id) {
		return noticeService.showById(id);
	}
	
	/**
	 * 按发布人显示所有公告
	 * @param currentPage
	 * @param pageSize
	 * @param realname
	 * @return
	 */
	@GetMapping("showAllNotice/{currentPage}/{pageSize}/{realname}")
	public Map<String, Object> showAllNotice(@PathVariable("currentPage") Integer currentPage,
			@PathVariable("pageSize") Integer pageSize, @PathVariable("realname") String realname) {

		PageHelper.startPage(currentPage, pageSize);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Notice> list = null;
		returnMap.put("content", list);
		list = noticeService.showNoticebyRealname(realname);

		returnMap.put("content", list);
		return returnMap;
	}
	
	/**
	 * 显示所有公告
	 * @param currentPage
	 * @param pageSize
	 * @param realname
	 * @return
	 */
	@GetMapping("showAllNotice/{currentPage}/{pageSize}")
	public Map<String, Object> showAllNotice(@PathVariable("currentPage") Integer currentPage,
			@PathVariable("pageSize") Integer pageSize) {

		PageHelper.startPage(currentPage, pageSize);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Notice> list = null;
		returnMap.put("content", list);
		list = noticeService.showAll();

		returnMap.put("content", list);
		return returnMap;
	}
	
	/**
	 * 修改公告
	 * @param notice
	 * @return
	 */
	@PostMapping("/updateNotice")
	public String updateNotice(@RequestBody Notice notice) {	
		return noticeService.saveNotice(notice);
	}
	/**
	 * 删除公告
	 * @param id
	 */
	@DeleteMapping("delNotice/{id}")
	public void delNotice(@PathVariable("id") Integer id) {
		noticeService.delNotice(id);
	}
	
	/**
	 * 删除所有公告
	 * @param id
	 */
	@DeleteMapping("delNotice")
	public void delNotice() {
		noticeService.delAllNotice();
	}
}
