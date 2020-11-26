package com.hpe.serviceImpl;

import java.util.List;

import com.hpe.dao.INoticeDao;
import com.hpe.daoImpl.NoticeDaoImpl;
import com.hpe.pojo.NoticePo;
import com.hpe.service.INoticeService;
import com.hpe.util.Page;
/**
 * 
* @Name: NoticeServiceImpl
* @Description: 公告服务层实体类
* @author yukinoo
* @date 2019年9月5日
*
 */
public class NoticeServiceImpl implements INoticeService {
	private INoticeDao nd = new NoticeDaoImpl();
	@Override
	public Page findNotice(Page page) {
		return nd.findNotice(page);
	}
	@Override
	public List findAllNotice() {
		/// 调用Dao层获取所有公告信息
		return nd.findAllNotice();
	}
	@Override
	public int addNotice(NoticePo notice) {
		NoticePo notice1 = nd.findByName(notice.getName());
		if(notice1!=null)return -1;
		return nd.addNotice(notice);
	}
	@Override
	public int deleteNotice(NoticePo notice) {
		// TODO Auto-generated method stub
		return nd.deleteNotice(notice);
	}

}
