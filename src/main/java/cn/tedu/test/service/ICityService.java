package cn.tedu.test.service;

import java.util.List;

import cn.tedu.test.entity.City;

/**
 * 业务层接口
 * @author Administrator
 *
 */
public interface ICityService {
	
	/**
	 * 通过id查找
	 * @param id
	 * @return
	 */
	City find(Integer id);
	
	/**
	 * 添加数据
	 * @param city
	 * @return
	 */
	Integer add(City city);
	
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	Integer delete(Integer id);
	
	/**
	 * 修改数据
	 * @param city
	 * @return
	 */
	Integer update(City city);
	
	/**
	 * 查找全部数据
	 * @return
	 */
	List<City> all();
}
