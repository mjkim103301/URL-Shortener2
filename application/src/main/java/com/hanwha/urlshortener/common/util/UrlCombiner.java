package com.hanwha.urlshortener.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlCombiner {

  @Value("localhost")
  private String host;
  @Value("9090")
  private String port;

  public String combinePathWithHost(String path) {
    return "http://" + host + ":" + port + "/" + path;
  }
}
