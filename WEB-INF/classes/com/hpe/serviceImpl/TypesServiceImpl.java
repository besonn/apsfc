package com.hpe.serviceImpl;

import java.util.List;

import com.hpe.dao.ITypesDao;
import com.hpe.daoImpl.TypesDaoImpl;
import com.hpe.pojo.TypePo;
import com.hpe.service.ITypesService;
import com.hpe.util.DBUtil;
import com.hpe.util.Page;

public class TypesServiceImpl implements ITypesService {
	private ITypesDao td = new TypesDaoImpl();
	@Override
	public List findAllTypes() {
		List types = null;
		types = td.findAllTypes();
		return types;
	}
	@Override
	public Page findTypes(Page page) {
		
		return td.findTypes(page);
	}
	@Override
	public int addType(TypePo type) {
		TypePo type1 = td.findByName(type.getName());
		if(type1!=null) {
			return -1;
		}
		return td.addType(type);
	}
	@Override
	public int updateType(TypePo type) {
		TypePo type1 = td.findByName(type.getName());
		if(type1!=null && type1.getId()!=type.getId()) {
			return -1;
		}
		return td.updateType(type);
	}
	@Override
	public TypePo findById(int id) {
		// TODO Auto-generated method stub
		return td.findById(id);
	}
	@Override
	public int deleteType(TypePo type) {
		return td.deleteType(type);
	}
}
