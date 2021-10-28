package com.hanwha.urlshortener.db.repository;


import com.hanwha.urlshortener.db.entity.Url;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {

}
