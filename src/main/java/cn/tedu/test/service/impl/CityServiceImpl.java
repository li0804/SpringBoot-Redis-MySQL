package cn.tedu.test.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import cn.tedu.test.entity.City;
import cn.tedu.test.mapper.CityMapper;
import cn.tedu.test.service.ICityService;

/**
 * 业务逻辑的实现类
 * @author Administrator
 *
 */
@Service
public class CityServiceImpl implements ICityService {
	
	//日志
	private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);
	
	@Autowired
	private CityMapper cityMapper;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public City find(Integer id) {
		
		long start = System.currentTimeMillis();
		
		//从redis缓存中获取信息
		String key = "city_"+id;
		ValueOperations<String, City> operations = redisTemplate.opsForValue();
		
		boolean haskey = redisTemplate.hasKey(key);		
		//如果有
		if(haskey){
			City city = operations.get(key);
			
			LOGGER.info(haskey+"----CityServiceImpl.findById:从缓存中获取数据-----"+city.toString());
			
			long end1 = System.currentTimeMillis();
			System.err.println("通过id查询耗时："+(end1-start));
			
			return city;
		}
		//如果没有
		City city = cityMapper.findById(id);
		//再插入redis缓存中方便下次使用
		operations.set(key,city,60,TimeUnit.SECONDS);
		
		LOGGER.info(haskey+"----CityServiceImpl.findById:将数据插入缓存中-----"+city.toString());
		
		long end2 = System.currentTimeMillis();
		System.err.println("通过id查询耗时："+(end2-start));
		
		return city;
	}

	@Override
	public Integer add(City city) {
		Integer rows = cityMapper.insert(city);
		return rows;
	}

	@Override
	public Integer delete(Integer id) {
		
		Integer rows = cityMapper.deleteById(id);
		
		String key = "city_"+id;
		
		boolean haskey = redisTemplate.hasKey(key);
		
		//如果有
		if(haskey){
			redisTemplate.delete(key);
			
			LOGGER.info(haskey+"---从缓存中删除数据---"+id);
		}
		return rows;
		
	}

	@Override
	public Integer update(City city) {
		Integer rows = cityMapper.updateById(city);
		
		Integer id = city.getId();
		
		String key = "city_"+id;
		
		boolean haskey = redisTemplate.hasKey(key);
		if(haskey){
			redisTemplate.delete(key);
			
			LOGGER.info(haskey+"---从缓存中删除数据---"+city.toString());
		}
		return rows;
	}

	@Override
	public List<City> all() {
		
		String key = "allCity";
		ValueOperations<String, List<City>> operations = redisTemplate.opsForValue();
		boolean haskey = redisTemplate.hasKey(key);
		
		long start = System.currentTimeMillis();
				
		//如果有
		if(haskey){
			List<City> list = operations.get(key);
			LOGGER.info(haskey+"---从缓存中读取全部数据---");
			
			long end1 = System.currentTimeMillis();
			System.err.println("findAll从Redis获取全部数据耗时："+(end1-start));
			
			return list;
		}
		//如果没有
		List<City> list = cityMapper.findAll();
		
		long end2 = System.currentTimeMillis();
		System.err.println("findAll从MySQL获取全部数据耗时："+(end2-start));
		
		LOGGER.info(haskey+"---将全部数据插入到缓存中---");
		operations.set(key, list,60,TimeUnit.SECONDS);	
		
		return list;
		
	}

}
