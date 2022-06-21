package com.example.demo.controller.form;

import javax.validation.constraints.NotBlank;
public class LoginForm {

	@NotBlank(message="IDは必須です。  ")
	private String accountId;
	@NotBlank(message="パスワードは必須です。  ")
	private String password;


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


}