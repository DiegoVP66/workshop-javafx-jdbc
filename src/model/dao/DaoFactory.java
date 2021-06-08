package model.dao;

import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	public SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
	
	public DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC();
	}
}
