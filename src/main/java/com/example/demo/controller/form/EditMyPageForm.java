package com.example.demo.controller.form;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class EditMyPageForm {
	@NotNull(message="名前は必須です。")
	private String name;
	@NotNull(message="アカウントIDは必須です。")
	private Integer accountId;
	@NotNull(message="パスワードは必須です。")
	private String password;
	@NotNull(message="パスワード確認用は必須です。")
	private String passConfirmation;
	private String mail;
	private String introduction;
	@NotNull(message="画像ファイルが選択されていません。  ")
	private MultipartFile file;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassConfirmation() {
		return passConfirmation;
	}
	public void setPassConfirmation(String passConfirmation) {
		this.passConfirmation = passConfirmation;
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	} 
	
	
}
