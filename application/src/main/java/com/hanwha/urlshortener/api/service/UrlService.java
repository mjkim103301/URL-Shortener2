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
   * 짧은 URL로 원래 URL 반환
   *
   * @param shortURL
   * @return OriginURL
   */
  String getOriginalUrl(String shortURL);
}
