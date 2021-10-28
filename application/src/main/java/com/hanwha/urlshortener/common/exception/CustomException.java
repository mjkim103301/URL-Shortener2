package com.hanwha.urlshortener.common.exception;


import com.hanwha.urlshortener.common.enums.ErrorType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

  protected HttpStatus status;
  protected ErrorType errorType;

  public CustomException(ErrorType errorType) {
    super(errorType.toString());
    this.status = errorType.getErrorResponse().getHttpStatus();
    this.errorType = errorType;
  }
}
