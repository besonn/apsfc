package com.hpe.service;

import java.util.List;

import com.hpe.pojo.NoticePo;
import com.hpe.util.Page;

/**
 * 
* @Name: INoticeServlet
* @Description: 公告服务层接口
* @author yukinoo
* @date 2019年9月5日
*
 */
public interface INoticeService {
	Page findNotice(Page page) ;
	
	/// 获取所有通告信息
	List findAllNotice();
	
	/// 添加公告
	int addNotice(NoticePo notice);
	
	int deleteNotice(NoticePo notice);
}
