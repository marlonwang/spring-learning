package net.logvv.swagger.model;

import java.util.Random;

public class User {
	private long id;
	private String name;
	private int age;
	
	public User(){
		Random random = new Random();
		this.id = random.nextLong();
		this.age = random.nextInt(30);
		this.name = String.valueOf("user_"+random.nextInt(100));
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
