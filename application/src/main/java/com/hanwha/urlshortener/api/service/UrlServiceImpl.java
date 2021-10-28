package com.hanwha.urlshortener.api.service;


import com.hanwha.urlshortener.common.enums.ErrorType;
import com.hanwha.urlshortener.common.exception.CustomException;
import com.hanwha.urlshortener.common.util.Base62;
import com.hanwha.urlshortener.common.util.LRUCache;
import com.hanwha.urlshortener.common.util.UrlCombiner;
import com.hanwha.urlshortener.api.response.UrlRes;
import com.hanwha.urlshortener.db.entity.Url;
import com.hanwha.urlshortener.db.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UrlServiceImpl implements UrlService {

  private final UrlRepository urlRepository;
  private final Base62 base62;
  private final UrlCombiner urlCombiner;
  private final LRUCache<String, String> lruCache;

  @Override
  public UrlRes shortenURL(String originalURL) {
    String encodedId = "";
    if (lruCache.containsKey(originalURL)) {
      encodedId = lruCache.get(originalURL);
      String shortPath = urlCombiner
          .combinePathWithHost("url/" + encodedId);
      return new UrlRes(originalURL, shortPath);
    }

    Url savedURL = urlRepository
        .save(Url.builder()
            .originalURL(originalURL)
            .build());
    encodedId = base62.encode(savedURL.getId());
    String shortPath = urlCombiner.combinePathWithHost("url/" + encodedId);
    lruCache.put(originalURL, encodedId);

    return new UrlRes(originalURL, shortPath);
  }

  @Override
  public String getOriginalUrl(String shortURL) {
    Long id = base62.decode(shortURL);
    return urlRepository
        .findById(id)
        .orElseThrow(() -> new CustomException(ErrorType.URL_SHORTEN_NOT_FOUND))
        .getOriginalURL();
  }
}
