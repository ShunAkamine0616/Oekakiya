package com.example.demo.dao;


import java.util.List;

import com.example.demo.entity.Image;

//dawnloadテーブル用DAO
public interface DownloadDao {

	// ユーザー毎にダウンロードを管理
	public List<Image> findByUserId(Integer userId);
	// ユーザー毎にダウンロードを管理
	public List<Image> findByUserIdList(Integer userId);
		
	// ダウンロード数を数える
	//	public int countDownload(Integer imageId);
	public Integer countDownload(Integer imageId);

	// ダウンロードされたときユーザーと画像をダウンロードしたことを処理
	public int insert(Integer userId, Integer imageId);

	//ユーザーテーブルと画像テーブルを結合して画像IDで指定
	public List<Image> usersJoinImages(Integer imageId);
}
