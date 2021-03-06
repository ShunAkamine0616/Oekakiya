package com.example.demo.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
public class UploadForm {
	@NotBlank(message="画像のタイトルは必須です。  ")
	@Length(max=100, message="100文字以内で入力してください。")
	private String imageTitle;
	private Integer categoryId;
	@Length(max=2000, message="2000文字以内で入力してください。")
	private String comment;
	@NotNull(message="画像ファイルが選択されていません。  ")
	private MultipartFile file;
	
	public String getImageTitle() {
		return imageTitle;
	}

	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}