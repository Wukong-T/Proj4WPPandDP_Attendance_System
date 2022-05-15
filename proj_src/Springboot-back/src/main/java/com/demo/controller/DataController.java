package com.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entity.Attend;
import com.demo.entity.Data;
import com.demo.service.AttendService;
import com.demo.service.DataService;
import com.demo.service.LeaveService;
import com.demo.service.UserService;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("/data")
public class DataController {

	@Autowired
	private DataService dataService;

	@Autowired
	private UserService userService;

	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private AttendService attendService;
	

	/**
	 * 录入用户信息
	 * 
	 * @param data
	 * @return
	 */
	@PostMapping("/insertData")
	public String insertData(@RequestBody Data data) {
		String username = data.getUsername();
		Data test = dataService.findAllByUsername(username);
		if (test == null) {
			return dataService.InsertData(data);
		} else {
			return "repeat";
		}

	}

	/**
	 * 分页查询所有用户信息
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@GetMapping("findAll/{currentPage}/{pageSize}")
	public Map<String, Object> findAll(@PathVariable("currentPage") Integer currentPage,
			@PathVariable("pageSize") Integer pageSize) {

		PageHelper.startPage(currentPage, pageSize);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Data> list = null;
		returnMap.put("content", list);
		list = dataService.findAll();

		returnMap.put("content", list);
		return returnMap;
	}

	/**
	 * 按姓名模糊查询某一种用户
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param realname
	 * @return
	 */
	@GetMapping("FuzzyQuery/{currentPage}/{pageSize}/{realname}")
	public Map<String, Object> FuzzyQuery(@PathVariable("currentPage") Integer currentPage,
			@PathVariable("pageSize") Integer pageSize, @PathVariable("realname") String realname) {

		PageHelper.startPage(currentPage, pageSize);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Data> list = null;
		returnMap.put("content", list);
		list = dataService.FuzzyQueryByRealname(realname);

		returnMap.put("content", list);
		return returnMap;
	}

	/**
	 * 
	 * 按用户类型通过姓名模糊查询
	 * @param currentPage
	 * @param pageSize
	 * @param realname
	 * @param choice
	 * @return
	 */
	@GetMapping("FuzzyQuery/{currentPage}/{pageSize}/{realname}/{choice}")
	public Map<String, Object> FuzzyQueryByChoice(@PathVariable("currentPage") Integer currentPage,
			@PathVariable("pageSize") Integer pageSize, @PathVariable("realname") String realname,
			@PathVariable("choice") String choice) {

		PageHelper.startPage(currentPage, pageSize);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Data> list = null;
		returnMap.put("content", list);
		list = dataService.FuzzyQueryByChioce(realname, choice);

		returnMap.put("content", list);
		return returnMap;
	}

	/**
	 * 按用户类型查询用户信息
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param choice
	 * @return
	 */
	@GetMapping("findAllByChoice/{currentPage}/{pageSize}/{choice}")
	public Map<String, Object> findAllByChoice(@PathVariable("currentPage") Integer currentPage,
			@PathVariable("pageSize") Integer pageSize, @PathVariable("choice") String choice) {
		PageHelper.startPage(currentPage, pageSize);
		Map<String, Object> returnMap = new HashMap<String, Object>();

		List<Data> list = null;
		returnMap.put("content", list);

		list = dataService.findAllByChoice(choice);
		returnMap.put("content", list);
		return returnMap;

	}
	
	
	/**
	 * 按用户名查找单个用户信息
	 * 
	 * @param username
	 * @return
	 */
	@GetMapping("/findAllByUsername/{username}")
	public Data findAllByUsername(@PathVariable("username") String username) {
		return dataService.findAllByUsername(username);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param data
	 * @return
	 */
	@PostMapping("/updateData")
	public String updateData(@RequestBody Data data) {
		String username = data.getUsername();
		String chioce = data.getChoice();
		String classes = data.getClasses();
		String dept = data.getDept();
		String realname = data.getRealname();
		Data test = dataService.findAllByUsername(username);
		if (test != null) {
			leaveService.updateAuditCheckname(data);
			userService.updateChioceAndRealname(username, chioce,realname);
			Attend attend = attendService.findAllByUsername(username);
			
			if(attend != null) {
				attend.setClasses(classes);
				attend.setDept(dept);
				attend.setRealname(realname);
				attendService.saveAttend(attend);
			}
			dataService.saveData(data);
			return "success";
		} else {
			return "empty";
		}

	}

	/**
	 * 删除用户信息
	 * 
	 * @param username
	 */
	@DeleteMapping("deleteData/{username}")
	public void deleteData(@PathVariable("username") String username) {
		dataService.deleteData(username);
	}
	/**
	 * 删除所有用户信息
	 * 
	 * @param username
	 */
	@DeleteMapping("deleteData")
	public void deleteData() {
		dataService.deleteData();
	}
	/**
	 * 删除用户信息
	 * 
	 * @param username
	 */
	@DeleteMapping("deleteDataByChoice/{choice}")
	public void deleteDataByChoice(@PathVariable("choice") String choice) {
		dataService.deleteDataByChoice(choice);
		
	}

	/**
	 * 批量上传excel文件导入用户信息
	 * @param multipartFile
	 * @param request
	 * @return
	 * @throws IOException
	 */
	
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/fileUpload")
	public String handlerForUpload(@RequestParam("fileList") MultipartFile multipartFile, HttpServletRequest request)
			throws IOException {
		// 判断所上传文件是否存在
		List<String> test = new ArrayList<String>();
		if (multipartFile.isEmpty()) {
			return "error";
		} else {
			InputStream in = null;
			try {
				// 将file转InputStream
				in = multipartFile.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 将InputStream转XLSX对象（即表格对象）

			@SuppressWarnings("resource")
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
			// 获取表格的第一页
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
			// 获取该页有多少数据
			int rowNum = xssfSheet.getLastRowNum();
			for (int i = 1; i <= rowNum; i++) {
				// 获取当前行
				XSSFRow xssfRow = xssfSheet.getRow(i);
				if(xssfRow == null) {
					return "error";
				}
				// 由于年龄是纯数字，默认纯数字是以double传的，写进据库会变成科学计数法格式的，需要改为以string传进来
				xssfRow.getCell(2).setCellType(CellType.STRING);
				xssfRow.getCell(3).setCellType(CellType.STRING);
				xssfRow.getCell(4).setCellType(CellType.STRING);

				// 以此获得该行的所有单元格
				String realname = xssfRow.getCell(0).getStringCellValue();
				String sex = xssfRow.getCell(1).getStringCellValue();
				String ageString = xssfRow.getCell(2).getStringCellValue();
				String username = xssfRow.getCell(3).getStringCellValue();
				String classes = xssfRow.getCell(4).getStringCellValue();
				String dept = xssfRow.getCell(5).getStringCellValue();
				String choice = xssfRow.getCell(6).getStringCellValue();
				Integer age = Integer.valueOf(ageString);
				// 将数据写入实体类
				Data data = new Data();
				data.setRealname(realname);
				data.setSex(sex);
				data.setAge(age);
				data.setUsername(username);
				data.setClasses(classes);
				data.setDept(dept);
				data.setChoice(choice);

				// 将实体类插入数据库
//				dataService.InsertData(data);
				 String isRepeat = dataService.InsertData(data);
				 test.add(isRepeat);
//				 System.out.println("循环监视");
			}
			
			for(int j = 0;j <test.size();j++ ) {
				if(test.get(j).equals("success")) {
//					System.out.println(test.get(j));
					return "success";
				}else {
//					System.out.println(test.get(j));
					return "repeat";
				}
			}
			return "repeat";
		}
	}


}
