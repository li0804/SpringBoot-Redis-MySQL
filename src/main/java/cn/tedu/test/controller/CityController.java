package cn.tedu.test.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.test.entity.City;
import cn.tedu.test.service.ICityService;

/**
 * 数据的控制器层
 * @author Administrator
 *
 */
@RestController
@RequestMapping("city")
public class CityController {
	
	@Autowired
	private ICityService cityService;
	
	@RequestMapping("find/{id}")
	private City find(@PathVariable("id") Integer id){
		City city = cityService.find(id);
		return city;
	}
	
	@RequestMapping("add")
	public String add(City city){
		Integer rows = cityService.add(city);
		if(rows==1){
			return "添加数据成功";
		}else {
			return "添加数据失败";
		}
	}
	
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id){
		Integer rows = cityService.delete(id);
		if(rows==1){
			return "删除数据成功";
		}else {
			return "删除数据失败";
		}
	}
	
	@RequestMapping("update/{id}")
	public String update(@PathVariable("id") Integer id,@PathParam("province_id") Integer province_id,
														 @PathParam("city_name") String city_name,
														 @PathParam("description") String description){
		City city = new City();
		city.setId(id);
		city.setProvince_id(province_id);
		city.setCity_name(city_name);
		city.setDescription(description);
		Integer rows = cityService.update(city );
		
		if(rows==1){
			return "修改数据成功";
		}else {
			return "修改数据失败";
		}
		
	}
	
	@RequestMapping("all")
	public List<City> all(){
		List<City> list = cityService.all();
		return list;
	}
}
