package com.hpe.dao;

import java.util.List;

import com.hpe.pojo.NoticePo;
import com.hpe.util.Page;

/**
 * 
* @Name: INoticeDao
* @Description: 公告持久层接口
* @author yukinoo
* @date 2019年9月5日
*
 */
public interface INoticeDao {
	Page findNotice(Page page);
	
	/// 获取所有公告信息
	List findAllNotice();
	
	/// 添加公告
	int addNotice(NoticePo notice);
	
	/// 通过名字查找
	NoticePo findByName(String name);
	
	int deleteNotice(NoticePo notice);
}
