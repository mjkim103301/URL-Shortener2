package com.hanwha.urlshortener.common.exception;


import com.hanwha.urlshortener.common.enums.ErrorType;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({CustomException.class})
  public void handleCustomException(HttpServletRequest request, HttpServletResponse response,
      CustomException ex) throws IOException {
    ErrorType errorType = ex.getErrorType();
    response.sendError(ex.getStatus().value(), errorType.getMessage());
    log.error("{} 시스템 오류 감지 : {}", errorType.getErrCode(), errorType.getMessage(), ex);
  }

  @ExceptionHandler({Exception.class})
  public void handleException(HttpServletRequest request, HttpServletResponse response,
      Exception ex) throws IOException {
    ex.printStackTrace();

    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");
    logger.error("알 수 없는 오류 감지.", ex);
  }
}
