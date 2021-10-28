package com.hanwha.urlshortener.urlshorten.repos;


import com.hanwha.urlshortener.urlshorten.model.URLShorten;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URLShortenRepository  extends JpaRepository<URLShorten, Integer> {
    public Optional<URLShorten> findByOriginURL(String url);
}
