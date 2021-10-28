package com.hanwha.urlshortener.api.service;

import com.hanwha.urlshortener.api.response.UrlRes;

public interface UrlService {

  /**
   * 원래 URL로 짧은 URL을 만들어서 반환
   *
   * @param originalURL
   * @return 짧은 URL
   */
  UrlRes shortenURL(String originalURL);

  /**
   * 짧은 URL 재저장
   *
   * @param shortURL
   * @return
   */
  String restoreURL(String shortURL);
}
