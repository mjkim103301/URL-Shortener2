package com.hanwha.urlshortener.api.controller;


import com.hanwha.urlshortener.api.request.UrlReq;
import com.hanwha.urlshortener.api.response.UrlRes;
import com.hanwha.urlshortener.api.service.UrlServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "URL API", tags = {"Url"})
@RequestMapping("/url")
@RestController
public class UrlController {

  private final UrlServiceImpl urlServiceImpl;

  @PostMapping("/")
  @ApiOperation(value = "Url Shortener", notes = "줄이고 싶은 URL을 입력받아 짧게 만들어 반환한다.")
  public ResponseEntity<?> UrlShortener(@RequestBody @Valid UrlReq urlReq) {
    UrlRes urlRes = null;
    try {
      urlRes = urlServiceImpl.shortenURL(urlReq.getOriginURL());
      return ResponseEntity.status(HttpStatus.OK).body(urlRes);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(urlRes);

  }

  @GetMapping("/{shortURL}")
  @ApiOperation(value = "Url Redirect", notes = "짧은 URL을 입력받아 원래 URL로 리다이렉트한다.")
  public ResponseEntity<?> UrlRedirect(@PathVariable(value = "shortURL") String shortURL,
      HttpServletResponse httpServletResponse) {
    String originURL = "";
    try {
      originURL = urlServiceImpl.restoreURL(shortURL);
      httpServletResponse.sendRedirect(originURL);
      return ResponseEntity.status(HttpStatus.OK).body(originURL);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(originURL);

  }
}
