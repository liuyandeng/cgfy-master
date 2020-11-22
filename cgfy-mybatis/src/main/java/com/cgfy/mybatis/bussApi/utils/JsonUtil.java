
package com.cgfy.mybatis.bussApi.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * @作者   廉福业 (William)
 * @日期 2017年1月1日
 * @描述 JSON工具类
 * Spring Boot Web 组件默认使用的是  jackson,好像没有显式的pom引入也可以
 */
public final class JsonUtil{

	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * bean 转 JSON 字符串
	 * @param <T>
	 *        泛型声明
	 * @param bean
	 *        类的实例
	 * @return JSON字符串
	 */
	public static <T> String toJson(T bean){
		StringBuffer json = new StringBuffer();
		if(null == bean){
			json.append("{}");
			return json.toString();
		}
		try{
			json.append(mapper.writeValueAsString(bean));
		}catch(JsonProcessingException e1){
			e1.printStackTrace();
		}
		return json.toString();
	}

	/**
	 * JSON 转指定的bean对象
	 * @param <T>
	 *        泛型声明
	 * @param json
	 *        JSON字符串
	 * @param c
	 *        要转换对象的类型
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> c){
		T t = null;
		try{
			t = mapper.readValue(json, c);
		}catch(JsonParseException e){
			e.printStackTrace();
		}catch(JsonMappingException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * JSON 转 Map
	 * @param json
	 *        JSON字符串,保持key是加了双引号的
	 * @return Map对象,默认为HashMap
	 */
	public static Map<?, ?> fromJson(String json){
		Map<?, ?> map = null;
		try{
			map = mapper.readValue(json, HashMap.class);
		}catch(JsonParseException e){
			e.printStackTrace();
		}catch(JsonMappingException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * JSON 转指定的bean对象
	 * @param <T>
	 *        泛型声明
	 * @param file
	 *        JSON文件
	 * @param c
	 *        要转换对象的类型
	 * @return
	 */
	public static <T> T fromFile(File file, Class<T> c){
		T t = null;
		try{
			t = mapper.readValue(file, c);
		}catch(JsonParseException e){
			e.printStackTrace();
		}catch(JsonMappingException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * JSON 转 Map
	 * @param file
	 *        JSON,请保持key是加了双引号的
	 * @return Map对象,默认为HashMap
	 */
	public static Map<?, ?> fromFile(File file){
		Map<?, ?> map = null;
		try{
			map = mapper.readValue(file, HashMap.class);
		}catch(JsonParseException e){
			e.printStackTrace();
		}catch(JsonMappingException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * json字符串转list
	 * @param jsonFile
	 * @return
	 * @throws Exception
	 * @author fanchenxi
	 */
	public static List<Map<String,Object>> GetJsonListByString(String jsonFile){
		JSONArray arry = JSONArray.fromObject(jsonFile);
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < arry.size(); i++)
		{
			JSONObject jsonObject = arry.getJSONObject(i);
			Map<String, Object> map = new HashMap<String, Object>();
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				Object value = jsonObject.get(key);
				map.put(key, value);
			}
			rsList.add(map);
		}
		return rsList;
	}


	public static void main(String[] args){
		//
		String json = "{\"success\":false,\"message\":\"密码错误!\",\"jsonData\":\"{}\"}";
		Map map = JsonUtil.fromJson(json);
		System.out.print(map);
		System.out.print("---------------------------");
		List<Map<String, Object>> books = new ArrayList<Map<String, Object>>();
		Map<String, Object> b = new HashMap<String, Object>();
		b.put("id", "阿杰卡");
		b.put("name", "jkakj");
		b.put("bushi", 12);
		b.put("wkw", "kaoo.22.2");
		books.add(b);
		//
		b = new HashMap<String, Object>();
		b.put("obj_aa", "jkakj");
		books.add(b);
		//
		b = new HashMap<String, Object>();
		b.put("ceshi", "阿哭了起来 ");
		b.put("haiha", 0.33);
		b.put("cc3", 1234567);
		b.put("vvvv", "aja2");
		books.add(b);
		json = JsonUtil.toJson(books);
		System.out.println(json);

		List<Map<String, Object>> x = new ArrayList<Map<String, Object>>();


	}
}
