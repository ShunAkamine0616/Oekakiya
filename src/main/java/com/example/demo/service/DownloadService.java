package com.example.demo.service;


import java.util.List;

import com.example.demo.entity.Image;

public interface DownloadService {
	// ユーザー毎にダウンロードを管理
	public List<Image> findByUserId(Integer userId);

	// ダウンロード数を数える
	public int countDownload(Integer imageId);

	// ダウンロードされたときユーザーと画像をダウンロードしたことを処理
	public int insert(Integer userId, Integer imageId);
	
	//ユーザーテーブルと画像テーブルを結合して画像IDで指定
		public List<Image> usersJoinImages(Integer imageId);
}
