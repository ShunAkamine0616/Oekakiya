package com.example.demo.service;

import java.awt.Image;
import java.util.List;

public interface DownloadService {
	// ユーザー毎にダウンロードを管理
		public List<Image> findByUserId(Integer userId);

		// ダウンロード数を数える
		public int countDownload(Integer imageId);

		// ダウンロードされたときユーザーと画像をダウンロードしたことを処理
		public int insert(Integer userId, Integer imageId);
}
