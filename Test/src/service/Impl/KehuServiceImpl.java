package service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.KehuDao;
import entity.Kehu;
import service.KehuService;


@Service("kehuservice")
public class KehuServiceImpl implements KehuService{
		
	@Autowired
	private KehuDao kehuDao;
	
	/**
	 * 查询全部信息
	 * @return
	 */
	public List<Kehu> getInfo(int page,int curr) {
		
	 return kehuDao.getInfo(page,curr);
	}

	
	/**
	 * 查询总记录数
	 * @return
	 */
	public int count() {
		return kehuDao.count();
		
	}


	@Override
	/*
	 * 删除(non-Javadoc)
	 * @see service.KehuService#Del(int)
	 */
	public int Del(int id) {
		return kehuDao.Del(id);
	}

     /**
      * 根据条件查询
      */
	@Override
	public List<Kehu> getOneInfo(String name,int page,int curr) {
		return kehuDao.getOneInfo(name, page, curr);
	}


	
	/**
	 * 条件查询的总记录数
	 */
	@Override
	public int Onecount(String name) {
		return kehuDao.Onecount(name);
	}



	

}
