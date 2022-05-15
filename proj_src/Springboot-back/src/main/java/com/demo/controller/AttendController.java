
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
import com.demo.entity.Census;
import com.demo.entity.Data;
import com.demo.service.AttendService;
import com.demo.service.DataService;
import com.github.pagehelper.PageHelper;



@RestController
@RequestMapping("/attend")
public class AttendController {
	@Autowired
	private AttendService attendService;
	@Autowired
	private DataService dataService;
	
	/**
	 * 添加学生考勤信息
	 * @param attend
	 * @return
	 */
	@PostMapping("/insertAttend")
	public String insertAttend(@RequestBody Attend attend) {
		String username = attend.getUsername();
		Attend test = attendService.findAllByUsername(username);
		if (test == null) {
			return attendService.insertAttend(attend);
		} else {
			return "repeat";
		}
	}
	
	/**
	 * 按用户类型显示学生考勤信息
	 * @param currentPage
	 * @param pageSize
	 * @param username
	 * @return
	 */
	@GetMapping("findAll/{currentPage}/{pageSize}/{username}")
	public Map<String,Object>  findAll(@PathVariable("currentPage") Integer currentPage,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("username") String username){
		Map<String,Object> returnMap=new HashMap<String,Object>();
		Data data =  dataService.findAllByUsername(username);
		if(data == null) {
			return returnMap;
		}else {
			
			String choice = data.getChoice();
			if(choice.equals("部门经理")) {
				String realname = data.getRealname();
				List<Attend> list=null;
				returnMap.put("content", list);
				
				list = attendService.findAll(realname);
				List<Attend> pageList = attendService.startPage(list, currentPage, pageSize);
				returnMap.put("content", pageList);
				return returnMap;
			}else if(choice.equals("副经理")) {
				String dept = data.getDept();
				List<Attend> list=null;
				returnMap.put("content", list);
				
				list = attendService.findByDept(dept);
				
				List<Attend> pageList = attendService.startPage(list, currentPage, pageSize);
				returnMap.put("content", pageList);
				return returnMap;
			}else {
				List<Attend> list=null;
				returnMap.put("content", list);
				
				list = attendService.findAll();
				
				List<Attend> pageList = attendService.startPage(list, currentPage, pageSize);
				returnMap.put("content", pageList);
				return returnMap;
			}		
		}
	}
	
	/**
	 * 按班级查询
	 * @param currentPage
	 * @param pageSize
	 * @param classes
	 * @return
	 */
	@GetMapping("findByClass/{currentPage}/{pageSize}/{classes}/{username}")
	public Map<String,Object>  findByClass(@PathVariable("currentPage") Integer currentPage,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("classes") String classes,
			@PathVariable("username") String username
			){
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		Data data = dataService.findAllByUsername(username);
		List<Attend> attend = attendService.findAll();
		int[] arr = {0};
		if(data !=null || attend.isEmpty() == false) {
			String choice =data.getChoice();
			String dept = data.getDept();
			if(choice.equals("副经理")) {
				for(int i = 0; i<attend.size();i++) {
					String checkdept = attend.get(i).getDept();
					String checkclasses = attend.get(i).getClasses();
					if(checkdept.equals(dept)&&checkclasses.equals(classes)) {
						arr[0] = 1;
					}
				}
				if(arr[0] == 1) {
					PageHelper.startPage(currentPage, pageSize);
					List<Attend> list=null;
					returnMap.put("content", list);
					list = attendService.findByClass(classes);
					returnMap.put("content", list);
					return returnMap;
				}else {
					return returnMap;
				}
			}
			PageHelper.startPage(currentPage, pageSize);
			List<Attend> list=null;
			returnMap.put("content", list);
			list = attendService.findByClass(classes);
			returnMap.put("content", list);
			return returnMap;
		}
		return returnMap;
	}
	
	/**
	 * 将考勤信息单独传给后端做个人考勤信息柱状图
	 * @param username
	 * @return
	 */
	@GetMapping("showChatrStudent/{username}")
	public Integer[]  showChatrStudent(@PathVariable("username") String username){
		return attendService.showChartStudent(username);
	}
	/**
	 * 将考勤信息单独传给后端做考勤信息饼图图
	 * @param classes
	 * @return
	 */
	@GetMapping("showChatrAttend/{classes}")
	public List<Census>  showChatrAttend(@PathVariable("classes") String classes){
		return attendService.showChatrAttend(classes);
	}
	
	@GetMapping("showChatrScore/{classes}")
	public Integer[]   showChatrScore(@PathVariable("classes") String classes){
		return attendService.showChatrScore(classes);
	}
	
	/**
	 * 查找单个考勤信息
	 * @param username
	 * @return
	 */
	@GetMapping("findAllByUsername/{username}")
	public Attend  findAllByUsername(@PathVariable("username") String username){
		return attendService.findAllByUsername(username);
	}
	
	/**
	 * 查找单个考勤信息（表格）
	 * @param username
	 * @return
	 */
	@GetMapping("findAllOwn/{username}")
	public Map<String,Object>   findAllOwn(@PathVariable("username") String username){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		Attend attend =  attendService.findAllByUsername(username);
		List<Attend> list = new ArrayList<Attend>();
		returnMap.put("content", list);
		if(attend == null) {
			return returnMap;
		}else {
			list.add(attend);
			returnMap.put("content", list);
			return returnMap;
		}
	}
	
	/**
	 * 修改考勤信息
	 * @param attend
	 * @return
	 */
	@PostMapping("/updateAttend")
	public String updateAttend(@RequestBody Attend attend) {
		String username = attend.getUsername();
		Attend test = attendService.findAllByUsername(username);
		if (test != null) {
			 attendService.saveAttend(attend);
			 return "success";
		} else {
			return "empty";
		}
	}
	/**
	 * 删除考勤信息
	 * @param username
	 */
	@DeleteMapping("deleteAttend/{username}")
	public void deleteData(@PathVariable("username") String username) {
		attendService.delAttend(username);
		}
	/**
	 * 删除全部的考勤信息
	 */
	@DeleteMapping("deleteAttend")
	public void deleteData() {
		attendService.delAttend();
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
		List<Attend> list = null;
		returnMap.put("content", list);
		list = attendService.FuzzyQueryByRealname(realname);

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
	@GetMapping("FuzzyQuery/{currentPage}/{pageSize}/{realname}/{classes}")
	public Map<String, Object> FuzzyQueryByChoice(@PathVariable("currentPage") Integer currentPage,
			@PathVariable("pageSize") Integer pageSize, @PathVariable("realname") String realname,
			@PathVariable("classes") String classes) {

		PageHelper.startPage(currentPage, pageSize);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Attend> list = null;
		returnMap.put("content", list);
		list = attendService.FuzzyQueryByChioce(realname, classes);

		returnMap.put("content", list);
		return returnMap;
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
				// 默认纯数字是以double传的，写进据库会变成科学计数法格式的，需要改为以string传进来
				xssfRow.getCell(1).setCellType(CellType.STRING);
				xssfRow.getCell(2).setCellType(CellType.STRING);
				
				xssfRow.getCell(4).setCellType(CellType.STRING);
				xssfRow.getCell(5).setCellType(CellType.STRING);
				xssfRow.getCell(6).setCellType(CellType.STRING);
				xssfRow.getCell(7).setCellType(CellType.STRING);
//				xssfRow.getCell(8).setCellType(CellType.STRING);

				// 以此获得该行的所有单元格
//				String realname = xssfRow.getCell(0).getStringCellValue();
//				String username = xssfRow.getCell(1).getStringCellValue();
//				String classes = xssfRow.getCell(2).getStringCellValue();
//				String dept = xssfRow.getCell(3).getStringCellValue();
//				
//				
//				String earlyString = xssfRow.getCell(4).getStringCellValue();
//				String lateString = xssfRow.getCell(5).getStringCellValue();
//				String vacationString = xssfRow.getCell(6).getStringCellValue();
//				String truancyString = xssfRow.getCell(7).getStringCellValue();
				
				String realname = xssfRow.getCell(0).toString();
				String username = xssfRow.getCell(1).toString();
				String classes = xssfRow.getCell(2).toString();
				String dept = xssfRow.getCell(3).toString();
				
				String earlyString = xssfRow.getCell(4).toString();
				String lateString = xssfRow.getCell(5).toString();
				String vacationString = xssfRow.getCell(6).toString();
				String truancyString = xssfRow.getCell(7).toString();
				

				
//				String scoreString = xssfRow.getCell(8).toString();
				
				Integer early = Integer.valueOf(earlyString);
				Integer late = Integer.valueOf(lateString);
				Integer vacation = Integer.valueOf(vacationString);
				Integer truancy = Integer.valueOf(truancyString);
				
//				Integer score = Integer.valueOf(scoreString);
				
				// 将数据写入实体类
//				 data = new Data();
				Attend attend = new Attend();
				attend.setRealname(realname);
				attend.setUsername(username);
				attend.setClasses(classes);
				attend.setDept(dept);
				
				attend.setEarly(early);
				attend.setLate(late);
				attend.setVacation(vacation);
				attend.setTruancy(truancy);
				
//				attend.setScore(score);
			

				// 将实体类插入数据库
//				dataService.InsertData(data);
				 String isRepeat = attendService.insertAttend(attend);
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
