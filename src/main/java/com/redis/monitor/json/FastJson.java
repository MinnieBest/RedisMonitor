package com.redis.monitor.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;


/**
 * @JSONField(serialize=false) 
 * 在属性get方法上加serialize=false可设置属性不输出 
 * name="" 可设置序列化后的属性名称   
 * 试用场景  实体对象往协议层数据转换
 * @author Fe
 *
 */
public class FastJson {
	
	public static final Logger logger = LoggerFactory.getLogger(FastJson.class);
	
	public static SerializeConfig mapping = new SerializeConfig();
	
	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	static {
		mapping.put(Date.class, new SimpleDateFormatSerializer(DEFAULT_DATE_FORMAT));
	}
	
	public static <T> String toJson(T t) {
		return JSON.toJSONString(t, mapping);
	}
	
	public static <T> T fromJson(String json,Class<T> t) {
		return (T) JSON.parseObject(json, t);
	}
	
	public static JSONObject fromJson(String json) {
		return JSON.parseObject(json);
	}
	
	
	public static <T> List<T> jsonToList(String json,Class<T> t) {
		return (List<T>) JSON.parseArray(json,t);
	}

	public static JSONArray jsonToList(String json) {
		return JSON.parseArray(json);
	}
	
	
	public static void main(String args[]) {
		User user = new User();
		user.setId(1);
		user.setName("fe");
		user.setCreateTime(new Date());
		
		Hobby hobby = new Hobby();
		hobby.setHobbyId(1);
		hobby.setHobbyName("as");
		List<Hobby> hobbyList = new ArrayList<Hobby>();
		hobbyList.add(hobby);
		
		user.setHobbyList(hobbyList);
		
		String jsonRes = toJson(user);
		
		System.out.println("jsonRes :" + jsonRes);
		
		User u1 = new User();
		u1.setId(2);
		u1.setName("n1");
		
		User u2 = new User();
		u2.setId(3);
		u2.setName("n3");
		
		List<Object> l = new ArrayList<Object>();
		
		l.add(u1);
		l.add(u2);
		
		List<User> list = jsonToList(toJson(l), User.class);
		System.out.println(list.size());
		
		User u3 = new User();
		u2.setId(3);
		System.out.println(toJson(u3));
		
		/*User u1 = fromJson(jsonRes,User.class);
		
		System.out.println(u1);
		
		List<String> strList = new ArrayList<String>();
		strList.add("a");
		strList.add("b");
		
		String strRes = toJson(strList);
		
		System.out.println(strRes);
		
		List<String> sList = jsonToList(strRes, String.class);
		System.out.println(sList.size());*/
		
	}
}

class User {
	private int id;
	private String name;
	private Long status;
	private Date createTime;
	private List<Hobby> hobbyList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@JSONField(serialize=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JSONField(name="st")
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<Hobby> getHobbyList() {
		return hobbyList;
	}
	public void setHobbyList(List<Hobby> hobbyList) {
		this.hobbyList = hobbyList;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", status=" + status
				+ ", createTime=" + createTime + ", hobbyList=" + hobbyList
				+ "]";
	}
}

class Hobby {
	private int hobbyId;
	private String hobbyName;
	
	public Hobby() {}

	public int getHobbyId() {
		return hobbyId;
	}

	public void setHobbyId(int hobbyId) {
		this.hobbyId = hobbyId;
	}

	public String getHobbyName() {
		return hobbyName;
	}

	public void setHobbyName(String hobbyName) {
		this.hobbyName = hobbyName;
	}
	
	
}
