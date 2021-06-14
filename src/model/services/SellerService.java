package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class SellerService {
	private SellerDao dpDao = DaoFactory.createSellerDao();

	public List<Seller> findAll() {
		return dpDao.findAll();
	}

	public void saveOrUpdate(Seller obj) {
		if (obj.getId() == null) {
			dpDao.insert(obj);
		} else {
			dpDao.update(obj);
		}
	}

	public void remove(Seller obj) {
		dpDao.deleteById(obj.getId());
	}
}
