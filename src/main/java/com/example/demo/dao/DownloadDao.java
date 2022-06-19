package com.example.demo.dao;

import java.awt.Image;
import java.util.List;

//dawnloadテーブル用DAO
public interface DownloadDao {

	// ユーザー毎にダウンロードを管理
	public List<Image> findByUserId(Integer userId);

	// ダウンロード数を数える
	public int countDownload(Integer imageId);

	// ダウンロードされたときユーザーと画像をダウンロードしたことを処理
	public int insert(Integer userId, Integer imageId);

}
