package com.example.demo.controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class SignupForm {
	@Length(max=20, message="アカウントIDは20文字以内です。  ")
	@NotBlank(message="アカウントIDは必須です。  ")
	private String accountId;
	@Length(max=10, message="パスワードは10文字以内です。  ")
	@NotBlank(message="パスワードは必須です。  ")
	private String password;
	@NotBlank(message="パスワードの再入力は必須です。  ")
	private String repassword;
	@Length(max=20, message="名前は50文字以内です。  ")
	@NotBlank(message="名前は必須です。  ")
	private String name;
	
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
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
