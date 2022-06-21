package com.example.demo.entity;

public class User {
	private int id;
	private String accountId;
	private String password;
	private String name;
	private int role;
	private String iconPath;
	private String mail;
	private String introduction;
	
	public User() {
		
	}
	
	public User(String accountId, String password, String name, String iconPath, String mail, String introduction){
		this.accountId = accountId;
		this.password = password;
		this.name = name;
		this.iconPath = iconPath;
		this.mail = mail;
		this.introduction = introduction;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
}
