package com.hpe.daoImpl;

import java.util.List;

import com.hpe.dao.INoticeDao;
import com.hpe.pojo.NoticePo;
import com.hpe.pojo.TypePo;
import com.hpe.util.DBUtil;
import com.hpe.util.Page;
/**
 * 
* @Name: NoticeDaoImpl
* @Description: 公告持久层实现类
* @author yukinoo
* @date 2019年9月5日
*
 */
public class NoticeDaoImpl implements INoticeDao {
	private DBUtil dbUtil = new DBUtil();
	@Override
	public Page findNotice(Page page) {
		String sql = "SELECT * FROM apsfc.notice";
		try {
			page = dbUtil.getQueryPage(NoticePo.class, sql, null, page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	@Override
	public List findAllNotice() {
		String sql = "SELECT * FROM apsfc.notice order by times desc";
		List list = null;
		try {
			list = dbUtil.getQueryList(NoticePo.class, sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int addNotice(NoticePo notice) {
		int result = 0;
		String sql = "insert into notice(name,content,times) values(?,?,?)";
		Object[] objects = {notice.getName(), notice.getContent(), notice.getTimes()};
		try {
			result = dbUtil.execute(sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public NoticePo findByName(String name) {
		String sql = "SELECT * FROM notice where name=?";
		Object[] objects = {name};
		NoticePo notice = new NoticePo();
		try {
			notice = (NoticePo)dbUtil.getObject(NoticePo.class, sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}
	@Override
	public int deleteNotice(NoticePo notice) {
		int result = 0;
		String sql = "delete from notice where id=?";
		Object[] objects = {notice.getId()};
		try {
			result = dbUtil.execute(sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
