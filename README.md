# URL Shortener 서비스 구현

### 개발 환경

* java 1.8.0_301
* Spring Boot, Spring MVC, Spring Data JPA
* gradle
* lombok
* swagger2
* h2 database

### 실행 방법
* db 확인: http://localhost:9090/h2
* swagger 주소에서 테스트: http://localhost:9090/swagger-ui/index.html

### 기능 설명

* Concurrent Hash Map에 <OriginalURL, EncodedID> 가 저장됩니다.
* 새로 저장되는 OriginalURL
  * DB의 ID를 Base62 로 변환해서 EncodedID로 만듭니다.
  * Concurrent Hash Map 에 저장됩니다.
  * Concurrent Hash Map 용량이 다 차면 오랫동안 사용하지 않은 값을 제거합니다.
* 기존에 저장됐던 OriginalURL
  * Concurrent Hash Map 에서 Value(EncodedID) 값을 바로 가져올 수 있어 DB를 거치지 않아도 됩니다.

### Controller

#### Url Controller

#### POST

/url/{urlReq}

* 줄이고 싶은 URL을 입력받아 짧게 만들어 반환합니다.
* INPUT

```
{
  "originalURL": "string"
}
```

* OUTPUT

 ```
{
  "originalURL": "string",
  "shortURL": "string"
}
 ```

#### GET

/url/{shortURL}

* 짧은 URL을 입력받아 원래 URL을 가져옵니다.
* INPUT

```
{
  "shortURL": "string"
}
```

* OUTPUT
  {
  "originalURL": "string"
  }
