package cn.tedu.test.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.test.entity.City;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityMpperTestCase {
	
	@Autowired
	private CityMapper cityMapper;
	
	@Test
	public void findById(){
		Integer id = 1;
		City result = cityMapper.findById(id );
		System.err.println(result);
	}
	@Test
	public void insert(){
		City city = new City();
		city.setProvince_id(2);
		city.setCity_name("福州");
		city.setDescription("上学在福州");
		Integer rows = cityMapper.insert(city );
		System.err.println(rows);
		
	}
	@Test
	public void deleteById(){
		Integer id = 3;
		Integer rows = cityMapper.deleteById(id );
		System.err.println(rows);
	}
	@Test
	public void updateById(){
		City city = new City();
		city.setId(4);
		city.setProvince_id(4);
		city.setCity_name("龙岩市");
		city.setDescription("住在龙岩市");
		Integer rows = cityMapper.updateById(city);
		System.err.println(rows);
		
	}
	@Test
	public void findAll(){
		List<City> list = cityMapper.findAll();
		System.err.println(list);
	}
}
