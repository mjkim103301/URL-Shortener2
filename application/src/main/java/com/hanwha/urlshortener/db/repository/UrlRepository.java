package com.hanwha.urlshortener.db.repository;


import com.hanwha.urlshortener.db.entity.Url;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Integer> {

  public Optional<Url> findByOriginURL(String url);
}
