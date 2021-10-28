package com.hanwha.urlshortener.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class UrlRes {

  private String originalURL;

  private String shortURL;

}
