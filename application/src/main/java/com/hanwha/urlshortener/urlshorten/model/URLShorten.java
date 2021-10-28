package com.hanwha.urlshortener.urlshorten.model;


import com.hanwha.urlshortener.common.model.TimeBaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor @RequiredArgsConstructor
@Getter @Builder
@Entity
public class URLShorten extends TimeBaseEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Lob
    @Column(nullable = false)
    private String originURL;

    @Column(nullable = false)
    private int count=0;

    public void increaseCount(int num){
        count+=num;
    }
}
