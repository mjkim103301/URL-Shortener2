package com.hanwha.urlshortener.urlshorten.controller;


import com.hanwha.urlshortener.urlshorten.dto.ShortenURLRequest;
import com.hanwha.urlshortener.urlshorten.dto.ShortenURLResponse;
import com.hanwha.urlshortener.urlshorten.service.URLShortenService;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/short")
@RestController
public class URLShortenController {
    private final URLShortenService urlShortenService;

    @PostMapping
    public ResponseEntity<?> shortenURL(@RequestBody @Valid ShortenURLRequest shortenURLRequest, Errors error){
        if(error.hasErrors()) {
            String errMsg = Objects.requireNonNull(error.getFieldError()).getDefaultMessage();
            log.info(errMsg);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errMsg);
        }

        ShortenURLResponse shortenURLResponse = urlShortenService.shortenURL(shortenURLRequest.getOriginURL());
        return ResponseEntity.ok(shortenURLResponse);
    }

    @GetMapping("/{shortURL}")
    public void redirectURL(@PathVariable(value = "shortURL") String shortURL, HttpServletResponse httpServletResponse) throws IOException {
        String originURL = urlShortenService.restoreURL(shortURL);
        httpServletResponse.sendRedirect(originURL);
    }
}
