package dao;

import java.util.List;

import entity.Kehu;

public interface KehuDao {
		
	
	/**
	 * 查询全部信息
	 * @return
	 */
	public List<Kehu> getInfo(int page,int curr);
	
	/**
	 * 条件查询的记录数
	 */
	public int Onecount(String name);
	
	
	/**
	 * 查询总记录数
	 */
    public int count();
    
    /**
     * 删除
     */
    public int Del(int id);
    
    /**
     * 根据条件查询
     * @param name
     * @return
     */
    public List<Kehu> getOneInfo(String name,int page,int curr);
}
