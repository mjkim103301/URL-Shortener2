package com.hanwha.urlshortener.common.util;

import org.springframework.stereotype.Service;

@Service
public class UrlCombiner {

  private final String HOST = "localhost";
  private final String PORT = "9090";

  public String combinePathWithHost(String path) {
    return "http://" + HOST + ":" + PORT + "/" + path;
  }
}
