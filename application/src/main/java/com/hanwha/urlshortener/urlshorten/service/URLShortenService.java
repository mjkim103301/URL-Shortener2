package com.hanwha.urlshortener.urlshorten.service;


import com.hanwha.urlshortener.common.constants.ErrorEnum;
import com.hanwha.urlshortener.common.exception.CustomException;
import com.hanwha.urlshortener.common.util.Base62;
import com.hanwha.urlshortener.common.util.URLCombiner;
import com.hanwha.urlshortener.urlshorten.dto.ShortenURLResponse;
import com.hanwha.urlshortener.urlshorten.model.URLShorten;
import com.hanwha.urlshortener.urlshorten.repos.URLShortenRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class URLShortenService {
    private final URLShortenRepository urlShortenRepos;
    private final Base62 base62;
    private final URLCombiner urlCombiner;

    public ShortenURLResponse shortenURL(String originURL) {
        Optional<URLShorten> urlShorten = urlShortenRepos.findByOriginURL(originURL);
        if(urlShorten.isPresent()){
            urlShorten.get().increaseCount(1);
            urlShortenRepos.save(urlShorten.get());
            String shortPath = urlCombiner.combinePathWithHost("short/"+base62.encode(urlShorten.get().getId()));
            return new ShortenURLResponse(originURL, shortPath, urlShorten.get().getCount());
        }

        URLShorten savedURL = urlShortenRepos
                .save(URLShorten.builder()
                        .originURL(originURL)
                        .build());
        String shortPath = urlCombiner.combinePathWithHost("short/"+base62.encode(savedURL.getId()));
        return new ShortenURLResponse(originURL, shortPath, 0);
    }

    public String restoreURL(String shortURL) {
        int id = base62.decode(shortURL);
        return urlShortenRepos
                .findById(id)
                .orElseThrow(() -> new CustomException(ErrorEnum.URL_SHORTEN_NOT_FOUND))
                .getOriginURL();
    }
}
