package com.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Notice;
import com.demo.mapper.NoticeMapper;
import com.demo.service.NoticeService;
@Service
public class NoticeServiceimpl implements NoticeService {
	@Autowired
	private NoticeMapper noticeMapper;
	
	
	@Override
	public String sendNotice(Notice notice) {
		// TODO Auto-generated method stub
		String realname = notice.getRealname();
		String content = notice.getContent();
		String title = notice.getTitle();
//		Date date = notice.getDate();
		List<Notice> list = noticeMapper.findAllByRealname(realname);
		if(list.isEmpty() == true) {
			noticeMapper.insert(notice);
			return "success";
			}
		else {
			for(int i = 0 ;i<list.size();i++) {
				String checkcontent = list.get(i).getContent();
				String checktitle = list.get(i).getTitle();
				if(checkcontent.equals(content)
						&&checktitle.equals(title)) {	
					return "repeat";
				}
			}
			noticeMapper.insert(notice);
			return "success";
		}	
	}

	@Override
	public Notice showNotice() {
		// TODO Auto-generated method stub
		List<Notice> list = noticeMapper.findAll();
		Notice notice = new Notice();
		if(list.isEmpty() == false) {
			 notice = list.get(list.size()-1);
			return notice;
		}
		return notice;
	}

	@Override
	public List<Notice>  showNoticebyRealname(String realname) {
		// TODO Auto-generated method stub
		List<Notice> list = noticeMapper.findAllByRealname(realname);
		return list;
	}

	@Override
	public String saveNotice(Notice notice) {
		// TODO Auto-generated method stub
		Integer id = notice.getId();
		Notice test = noticeMapper.findAllById(id);
		if(test == null) {
			return "empty";
		}else {
			noticeMapper.saveNotice(notice);
			return "success";
		}
		
	}

	@Override
	public Notice showById(Integer id) {
		// TODO Auto-generated method stub
		Notice notice = noticeMapper.findAllById(id);
		return notice;
	}

	@Override
	public void delNotice(Integer id) {
		// TODO Auto-generated method stub
		noticeMapper.deleteById(id);
	}

	@Override
	public List<Notice> showAll() {
		// TODO Auto-generated method stub
		List<Notice> list = noticeMapper.findAll();
		return list;
	}

	@Override
	public void delAllNotice() {
		// TODO Auto-generated method stub
		noticeMapper.deleteAll();
	}

}
