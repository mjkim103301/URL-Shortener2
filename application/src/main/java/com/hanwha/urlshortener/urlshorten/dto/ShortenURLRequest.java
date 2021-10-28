package com.hanwha.urlshortener.urlshorten.dto;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@RequiredArgsConstructor @AllArgsConstructor
@Builder
@Setter @Getter
public class ShortenURLRequest {
    @URL(message = "not correct url format")
    @NotEmpty(message = "url empty error")
    private String originURL;
}
