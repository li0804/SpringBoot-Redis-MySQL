package cn.tedu.test.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 数据表：city 实体类
 * @author Administrator
 *
 */
@Data
public class City implements Serializable{

	private static final long serialVersionUID = -7547418699177451196L;
	//城市编号
	private Integer id;
	//省份编号
	private Integer province_id;
	//城市名字
	private String city_name;
	//描述
	private String description;
}
