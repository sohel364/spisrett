package com.spis.rett.model;

public class User {
	private String deviceId;
	private String userName;
	private String password;
	private String email;
	private int userType;
	
	
	
	
	public User(String userName, String password, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public User(String deviceId, String userName, String password,
			String email, int userType) {
		super();
		this.deviceId = deviceId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.userType = userType;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	
}
