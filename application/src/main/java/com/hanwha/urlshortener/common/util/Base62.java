package com.hanwha.urlshortener.common.util;

import org.springframework.stereotype.Service;

@Service
public class Base62 {

  private static final char[] BASE62 = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ"
      .toCharArray();
  private static final Long BASE62_SIZE =62L;

  public String encode(Long id) {
    StringBuilder shortURL = new StringBuilder();
    while (id > 0) {
      shortURL.append(BASE62[(int)(id % BASE62_SIZE)]);
      id /= BASE62_SIZE;
    }
    return shortURL.reverse().toString();
  }

  public Long decode(String str) {
    Long id = 0L;

    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if ('a' <= ch && ch <= 'z') {
        id = id * 62 + ch - 'a';
      } else if ('A' <= ch && ch <= 'Z') {
        id = id * 62 + ch - 'A' + 36;
      } else if ('0' <= ch && ch <= '9') {
        id = id * 62 + ch - '0' + 26;
      }
    }
    return id;
  }
}
