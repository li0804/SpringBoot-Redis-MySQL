package cn.tedu.test.mapper;

import java.util.List;

import cn.tedu.test.entity.City;

/**
 * 持久层接口
 * @author Administrator
 *
 */
public interface CityMapper {
	
	/**
	 * 通过id查找数据
	 * @param id
	 * @return
	 */
	City findById(Integer id);
	
	/**
	 * 添加数据
	 * @param city
	 * @return
	 */
	Integer insert(City city);
	
	/**
	 * 通过id删除数据
	 * @param id
	 * @return
	 */
	Integer deleteById(Integer id);
	
	/**
	 * 数据修改
	 * @param city
	 * @return
	 */
	Integer updateById(City city);
	
	/**
	 * 查找全部数据
	 * @return
	 */
	List<City> findAll();
}
