package com.hpe.daoImpl;

import java.util.List;
import com.hpe.util.*;
import com.hpe.pojo.*;
import com.hpe.dao.ITypesDao;
/**
 * 
* @Name: TypesDaoImpl
* @Description: Types持久层实现类
* @author yukinoo
* @date 2019年9月5日
*
 */
public class TypesDaoImpl implements ITypesDao {
	private DBUtil dbUtil = new DBUtil();
	@Override
	public List findAllTypes() {
		String sql = "SELECT * FROM apsfc.types";
		List list = null;
		try {
			list = dbUtil.getQueryList(TypePo.class, sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public Page findTypes(Page page) {
		String sql = "SELECT t.id,t.name from apsfc.types t";
		try {
			page = dbUtil.getQueryPage(TypePo.class, sql, null, page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	@Override
	public TypePo findByName(String name) {
		String sql = "SELECT * FROM types where name=?";
		Object[] objects = {name};
		TypePo type = new TypePo();
		try {
			type = (TypePo)dbUtil.getObject(TypePo.class, sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return type;
	}
	@Override
	public int addType(TypePo type) {
		int result = 0;
		String sql = "insert into types(name) values(?)";
		Object[] objects = {type.getName()};
		try {
			result = dbUtil.execute(sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int updateType(TypePo type) {
		int result = 0;
		String sql = "update types set name=? where id=?";
		Object[] objects = {type.getName(),type.getId()};
		try {
			result = dbUtil.execute(sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public TypePo findById(int id) {
		String sql = "SELECT * FROM types where id=?";
		Object[] objects = {id};
		TypePo type = new TypePo();
		try {
			type = (TypePo)dbUtil.getObject(TypePo.class, sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return type;
	}
	@Override
	public int deleteType(TypePo type) {
		int result = 0;
		String sql = "delete from types where id=?";
		Object[] objects = {type.getId()};
		try {
			result = dbUtil.execute(sql, objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
