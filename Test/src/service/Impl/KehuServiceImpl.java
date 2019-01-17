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
	 * ��ѯȫ����Ϣ
	 * @return
	 */
	public List<Kehu> getInfo(int page,int curr) {
		
	 return kehuDao.getInfo(page,curr);
	}

	
	/**
	 * ��ѯ�ܼ�¼��
	 * @return
	 */
	public int count() {
		return kehuDao.count();
		
	}


	@Override
	/*
	 * ɾ��(non-Javadoc)
	 * @see service.KehuService#Del(int)
	 */
	public int Del(int id) {
		return kehuDao.Del(id);
	}

     /**
      * ����������ѯ
      */
	@Override
	public List<Kehu> getOneInfo(String name,int page,int curr) {
		return kehuDao.getOneInfo(name, page, curr);
	}


	
	/**
	 * ������ѯ���ܼ�¼��
	 */
	@Override
	public int Onecount(String name) {
		return kehuDao.Onecount(name);
	}



	

}
