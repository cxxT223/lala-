package service;

import java.util.List;

import entity.Kehu;

public interface KehuService {
	/**
	 * ��ѯȫ����Ϣ
	 * @return
	 */
	public List<Kehu> getInfo(int page,int curr);
	
	/**
	 * ��ѯ�ܼ�¼��
	 */
    public int count();
    
    /**
     * ɾ��
     */
   public int Del(int id);
   
   /**
    * ����������ѯ
    * @param name
    * @return
    */
   public List<Kehu> getOneInfo(String name,int page,int curr);
   
   /**
	 * ������ѯ�ļ�¼��
	 */
	public int Onecount(String name);
}
