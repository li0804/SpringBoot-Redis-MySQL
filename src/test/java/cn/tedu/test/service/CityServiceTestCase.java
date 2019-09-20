package cn.tedu.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.test.entity.City;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceTestCase {
	
	@Autowired
	private ICityService cityService;
	
	@Test
	public void find(){
		Integer id = 1;
		City city = cityService.find(id );
		System.err.println(city);
	}
}
