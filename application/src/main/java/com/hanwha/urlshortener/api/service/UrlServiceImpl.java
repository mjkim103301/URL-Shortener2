package com.hanwha.urlshortener.api.service;


import com.hanwha.urlshortener.common.enums.ErrorType;
import com.hanwha.urlshortener.common.exception.CustomException;
import com.hanwha.urlshortener.common.util.Base62;
import com.hanwha.urlshortener.common.util.UrlCombiner;
import com.hanwha.urlshortener.api.response.UrlRes;
import com.hanwha.urlshortener.db.entity.Url;
import com.hanwha.urlshortener.db.repository.UrlRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UrlServiceImpl implements UrlService {

  private final UrlRepository urlShortenRepos;
  private final Base62 base62;
  private final UrlCombiner urlCombiner;

  @Override
  public UrlRes shortenURL(String originURL) {
    Optional<Url> urlShorten = urlShortenRepos.findByOriginURL(originURL);
    if (urlShorten.isPresent()) {
      urlShorten.get().increaseCount(1);
      urlShortenRepos.save(urlShorten.get());
      String shortPath = urlCombiner
          .combinePathWithHost("url/" + base62.encode(urlShorten.get().getId()));
      return new UrlRes(originURL, shortPath, urlShorten.get().getCount());
    }

    Url savedURL = urlShortenRepos
        .save(Url.builder()
            .originURL(originURL)
            .build());
    String shortPath = urlCombiner.combinePathWithHost("url/" + base62.encode(savedURL.getId()));
    return new UrlRes(originURL, shortPath, 0);
  }

  @Override
  public String restoreURL(String shortURL) {
    int id = base62.decode(shortURL);
    return urlShortenRepos
        .findById(id)
        .orElseThrow(() -> new CustomException(ErrorType.URL_SHORTEN_NOT_FOUND))
        .getOriginURL();
  }
}
