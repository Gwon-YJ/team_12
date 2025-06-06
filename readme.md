## 게시글 API 설계

<details>
<summary>게시글 생성</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /posts<br>
HTTP METHOD : POST<br>
설명 : 게시글을 생성하는 API 입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## c.body

| 키        | 데이터타입  | 설명     |
|----------|--------|--------|
| title    | String | 게시글 제목 |
| content  | String | 게시글 내용 |


### 2. 요청예시
```json
{
   "title" : "오늘도 짜증난다.",
   "content" : "사랑합니다."
}
```

### 3. 응답(Response)
| 키             | 데이터타입  | 설명          |
|---------------|--------|-------------|
| postId        | long   | 생성된 게시글 식별자 |
| userName      | String | 사용자이름       |
| title         | String | 게시글 제목      |
| content       | String | 게시글 내용      |
| likesCount    | int    | 좋아요 수       |
| commentsCount | int    | 댓글 수        |
| createdAt     | LocalDateTime  | 생성날짜        |
| modifiedAt    | LocalDateTime  | 수정날짜  |

### 응답 예시
```json
{
     "postId ": 1,
     "userName" : "이형준",
     "title" : "오늘도 짜증난다.",
     "content" : "사랑합니다.",
     "likesCount" : 90,
     "commentsCount" : 20,
     "createdAt": "2025-05-28 11:06:54",
     "modifiedAt" : "2025-05-28 11:06:54"
}
```
### b. 생성 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
  //title 없을때
   "message" : "제목을 작성해주세요.",
  //content 없을때
  "message" : "작성글을 작성해주세요."
}
```
</details>
<details>
<summary>게시글 수정</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /posts/{postId}<br>
HTTP METHOD : PUT<br>
설명 : 게시글 정보를 수정하는 API입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URL(경로 변수)

### 1.설명
| 키      | 데이터타입  | 설명      |
|--------|--------|---------|
| PostId | long    | 게시글 식별자 |

```json
localhost:8080/posts/{postId}
```

## c.body

### 1.설명
| 키        | 데이터타입 | 설명     |
|----------|-------|--------|
| title    | String | 게시글 제목 |
| content  | String | 게시글 내용 | 

### 2. 요청예시
```json
{
   "title" : "오늘도 짜증난다2",
   "content" : "사랑합니다2"
}
```

### 3. 응답(Response)
| 키            | 데이터타입  | 설명    |
|--------------|--------|-------|
| postId     | long   | 생성된 게시글 식별자 |
| userName     | String | 사용자이름 |
| title        | String | 게시글 제목 |
| content      | String | 게시글 내용 |
| likesCount   | int    | 좋아요 수 |
| commentsCount    | int    | 댓글 수  |
| createdAt | LocalDateTime  | 생성날짜  |
| modifiedAt | LocalDateTime  | 수정날짜  |

### 응답 예시
```json
{
     "postId" : 1,
     "userName": "이형준",
     "title": "오늘도 짜증난다2",
     "content": "사랑합니다2",
     "likesCount" : 90,
     "commentsCount" : 20,
     "createdAt": "2025-05-28 11:06:54",
     "modifiedAt": "2025-05-28 11:08:54"
}
```
### b. 수정 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
  //게시글이 없을 시
   "status" : "404",
   "message" : "게시글을 찾을 수 없습니다.",
  //유저가 없을시
   "status" : "403",
   "message" : "작성자만 게시글을 수정할 수 있습니다."
}
```
</details>
<details>
<summary>특정 유저 게시글 조회</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /posts/{userId}<br>
HTTP METHOD : GET<br>
설명 : 특정 유저 게시글 정보를 조회하는 API입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URl(경로 변수)

### 1.설명
| 키      | 데이터타입  | 설명     |
|--------|--------|--------|
| userId | long    | 게시글 식별자 |

```json
localhost:8080/posts/{userId}
```

### 3. 응답(Response)
| 키          | 데이터타입  | 설명          |
|------------|--------|-------------|
| postId     | long      | 생성한 게시글 식별자 |
| userName   | String | 사용자이름       |
| title      | String | 게시글 제목      |
| content    | String | 게시글 내용      |
| likesCount | int    | 좋아요 수       |
| commentsCount   | int    | 댓글 수        |
| createdAt  | LocalDateTime  | 생성날짜        |
| modifiedAt | LocalDateTime  | 수정날짜        |

### 응답 예시
```json
{
     "userId" : 1,
     "userName": "이형준",
     "title": "오늘도 짜증난다2",
     "content": "사랑합니다2",
     "likesCount" : 90,
     "commentsCount " : 20,
     "createdAt": "2025-05-28 11:06:54",
     "modifiedAt": "2025-05-28 11:08:54"
}
```
### b. 조회 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "해당 유저가 존재하지 않습니다."
}
```
</details>
<details>
<summary>게시글 조회</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /posts/page<br>
HTTP METHOD : GET<br>
설명 : 게시글 정보를 조회하는 API입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URl(경로 변수)

### 1.설명
| 키      | 데이터타입  | 설명      |
|--------|--------|---------|
| page | int    | 페이지 수   |
| size | int    | 페이지 사이즈 |

```json
localhost:8080/posts/page
```

### 3. 응답(Response)
| 키             | 데이터타입     | 설명           |
|---------------|-----------|--------------|
| postId        | long      | 생성된 게시글 식별자  |
| userName      | String    | 사용자이름        |
| title         | String    | 게시글 제목       |
| content       | String    | 게시글 내용       |
| likesCount    | int       | 좋아요 수        |
| commentsCount  | int       | 댓글 수         |
| createdAt     | LocalDateTime | 생성날짜         |
| modifiedAt    | LocalDateTime | 수정날짜         |
| page          | int       | 현재 페이지 수     |
| size          | int       | 페이지 사이즈      |
| totalElements | int       | 모든 페이지 게시글 수 |
| totalPages    | int       | 모든 페이지 수     |

### 응답 예시
```json
   " contents" : [ {
     "postId": 1,
     "userName": "이형준",
     "title": "오늘도 짜증난다",
     "content": "사랑합니다",
     "likesCount" : 90,
     "commentsCount" : 20,
     "createdAt": "2025-05-28 11:06:54",
     "modifiedAt": "2025-05-28 11:08:54"
   },
   {
     "postId": 2,
     "userName": "이형준2",
     "title": "오늘도 짜증난다2",
     "content": "사랑합니다2",
     "likesCount" : 70,
     "commentsCount" : 20,
     "createdAt": "2025-05-28 11:06:54",
     "modifiedAt": "2025-05-28 11:08:54",
   }
],
   "page" : 1,
   "size" : 10,
   "totalElements" : 2,
   "totalPages" : 1
   }
```
</details>
<details>
<summary>게시글 검색</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /posts?startDate=20250528&endDate=20250530 <br>
HTTP METHOD : GET<br>
설명 : 게시글 정보를 검색하는 API입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URl(경로 변수)

### 1.설명
| 키      | 데이터타입  | 설명          |
|--------|--------|-------------|
| startDate | int    | 검색 범위 시작날짜  |
| endDate | int    | 검색 범위 마지막날짜 |

```json
localhost:8080/posts?startDate=20250528&endDate=20250530
```

### 3. 응답(Response)
| 키          | 데이터타입     | 설명          |
|------------|-----------|-------------|
| postId     | long      | 생성된 게시글 식별자 |
| userName   | String    | 사용자이름       |
| title      | String    | 게시글 제목      |
| content    | String    | 게시글 내용      |
| likesCount | int       | 좋아요 수       |
| commentsCount  | int       | 댓글 수        |
| createdAt  | LocalDateTime  | 생성날짜        |
| modifiedAt | LocalDateTime  | 수정날짜        |

### 응답 예시
```json

   "status" : 200,
   {
     "postId": 1,
     "userName": "이형준",
     "title": "오늘도 짜증난다",
     "content": "사랑합니다",
     "likesCount" : 90,
     "commentsCount" : 20,
     "createdAt": "2025-05-28 11:06:54",
     "modifiedAt ": "2025-05-30 11:10:55"
   },
   {
     "postId": 2,
     "userName": "이형준",
     "title": "오늘도 짜증난다2",
     "content": "사랑합니다2",
     "likesCount" : 70,
     "commentsCount" : 20,
     "createdAt": "2025-05-30 11:06:54",
     "modifiedAt ": "2025-05-30 11:10:55"
   }
```
</details>
<details>
<summary>게시글삭제</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /posts/{postId}<br>
HTTP METHOD : DELETE<br>
설명 : 단일 게시글 정보를 삭제하는 API 입니다. <br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URL(경로 변수)

### 1.설명
| 키      | 데이터타입  | 설명      |
|--------|--------|---------|
| postId | long    | 게시글 식별자 |

```json
localhost:8080/posts/{postId}
```
### b. 생성 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
  //게시글이 없을시
   "status" : "404",
   "message" : "게시글을 찾을 수 없습니다.",
  //유저가 없을시
   "status" : "403",
   "message" : "작성자만 게시글을 수정할 수 있습니다."
}
```
</details>

## 유저 API 설계
<details>
<summary>유저생성</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /users<br>
HTTP METHOD : POST<br>
설명 : 유저를 생성하는 API 입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## c.body

| 키        | 데이터타입 | 설명    |
|----------|-------|-------|
| userName | String | 사용자이름 |
| email    | String | 사용자 이메일 |
| password |String | 사용자 비밀번호 |

### 2. 요청예시
```json
{
   "userName" : "이형준",
   "email" : "xkrhd3@naver.com",
   "password" : 1234
}
```

### 3. 응답(Response)
| 키         | 데이터타입  | 설명         |
|-----------|--------|------------|
| id        | long   | 생성된 유저 식별자 |
| userName  | String | 사용자이름      |
| email     | String | 사용자 이메일   |
| createdAt  | Timestamp  | 생성날짜        |
| modifiedAt | Timestamp  | 수정날짜        |

### 응답 예시
```json
{
     "id": 1,
     "userName": "이형준",
     "email" : "xkrhd3@naver.com",
     "createdAt" : "2025-05-30 16:00:47",
     "modifiedAt" : "2025-05-30 16:00:47"
}
```
### b. 생성 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
  //유저이름&이메일&비밀번호 형식에 맞지 않을때
   "status" : "401",
   "message" : "형식을 준수해서 입력해야합니다.",
  //동일한 이메일 등록 시도 시
   "status" : "400",
   "message" : "이미 등록된 이메일입니다."
}
```
</details>
<details>
<summary>특정 유저 정보수정</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /users/{userId}<br>
HTTP METHOD : PATCH<br>
설명 : 특정 유저 정보를 수정하는 API입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URL(경로 변수)

### 1.설명
| 키       | 데이터타입  | 설명 |
|---------|--------|----|
| userId  | long    | 유저 식별자 |

```json
localhost:8080/users/{userId}
```

## c.body

### 1.설명
| 키        | 데이터타입 | 설명       |
|----------|-------|----------|
| userName| String | 사용자 이름   |
| email| String | 사용자 이메일  |
| Password| String | 사용자 비밀번호 |

### 2. 요청예시
```json
{
  "userName": "이형준",
  "email" : "xkrhd5@naver.com",
  "Password" : 12345
}
```

### 3. 응답(Response)
| 키           | 데이터타입  | 설명 |
|-------------|--------|----|
| id        | long   | 생성된 유저 식별자 |
| userName| String | 사용자 이름   |
| email| String | 사용자 이메일  |
| Password| String | 사용자 비밀번호 |

### 응답 예시
```json
{
    "id": 1,
    "userName": "이형준",
    "email" : "xkrhd5@naver.com"
}
```
### b. 수정 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "해당 유저를 찾을 수 없습니다.",
  //인가 실패
   "status" : "403",
   "message" : "접근 권한이 없습니다."
}
```
</details>
<details>
<summary>특정 유저 비밀번호수정</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /users/{userId}<br>
HTTP METHOD : PATCH<br>
설명 : 특정 유저 비밀번호를 수정하는 API입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URL(경로 변수)

### 1.설명
| 키       | 데이터타입  | 설명 |
|---------|--------|----|
| userId  | int    | 유저 식별자 |

```json
localhost:8080/users/{userId}
```

## c.body

### 1.설명
| 키        | 데이터타입 | 설명         |
|----------|-------|------------|
| savePassword| String | 사용자 기존비밀번호 |
| changePassword| String | 사용자 수정비밀번호 |

### 2. 요청예시
```json
{
  "savePassword": 1234,
  "changePassword" : 12345
}
```

### 3. 응답(Response)
| 키         | 데이터타입  | 설명          |
|-----------|--------|-------------|
| message  | String | 비밀번호 수정 메시지 |

### 응답 예시
```json
{
  "수정 성공"
}
```
### b. 수정 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "비밀번호가 일치하지 않습니다.",
  //인가 실패 
   "status" : "403",
   "message" : "접근 권한이 없습니다."
}
```
</details>
<details>
<summary>단일 유저조회</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /users/{userId}<br>
HTTP METHOD : GET<br>
설명 : 단일 유저 정보를 조회하는 API입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URL(경로 변수)

### 1.설명
| 키       | 데이터타입  | 설명 |
|---------|--------|----|
| userId  | long    | 유저 식별자 |

```json
localhost:8080/users/{userId}
```

### 3. 응답(Response)
| 키         | 데이터타입  | 설명         |
|-----------|--------|------------|
| id        | long   | 생성된 할일 식별자 |
| userName  | String | 사용자이름      |
| email     | String | 사용자 이메일    |

### 응답 예시
```json
{
     "id": 1,
     "userName" : "이형준",
     "email" : "xkrhd3@naver.com"
}
```
### b. 조회 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "해당 유저를 찾을 수 없습니다."
}
```
</details>
<details>
<summary>유저 전체조회</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /users<br>
HTTP METHOD : GET<br>
설명 : 유저 전체정보를 조회하는 API입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

### 3. 응답(Response)
| 키         | 데이터타입  | 설명 |
|-----------|--------|----|
| id        | long   | 생성된 할일 식별자 |
| userName  | String | 사용자이름 |
| email     | String | 사용자 이메일 |

### 응답 예시
```json

{
   "id": 1,
   "userName" : "이형준",
   "email" : "xkrhd3@naver.com"
},
{
   "id": 2,
   "userName" : "이형준2",
   "email" : "xkrhd4@naver.com"
}
```
### b. 조회 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "유저를 찾을 수 없습니다."
}
```
</details>
<details>
<summary>유저삭제</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /users/{userId}<br>
HTTP METHOD : DELETE<br>
설명 : 단일 유저 정보를 삭제하는 API 입니다. <br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URL(경로 변수)

### 1.설명
| 키      | 데이터타입  | 설명 |
|--------|--------|----|
| userId | bigint    | 유저 식별자 |

```json
localhost:8080/schedule/{userId}
```

### b. 삭제 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "유저를 찾을 수 없습니다."
}
```
</details>
<details>
<summary>로그인</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /users/login<br>
HTTP METHOD : GET<br>
설명 : 유저 정보로 로그인하는 API입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URL(경로 변수)

### 1.설명
| 키       | 데이터타입 | 설명  |
|---------|-----|-----|
| login  | String   | 로그인 |

```json
localhost:8080/users/login
```

## c.body

### 1.설명
| 키   | 데이터타입 | 설명      |
|-----|-----|---------|
| email |  String   | 유저 이메일  |
| Password    | String    | 유저 비밀번호 | 

### 2. 요청예시
```json
{
  "username" : "이형준",
  "Password" : 1234
}
```

### 3. 응답(Response)
| 키         | 데이터타입  | 설명         |
|-----------|--------|------------|
| message  | String | 로그인 메시지    |

### 응답 예시
```json
{
     "로그인에 성공했습니다."
}
```
### b. 로그인 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "400",
  //비밀번호&이메일 불일치
   "message" : "비밀번호나 이메일이 일치하지 않습니다."
}
```
</details>
<details>
<summary>로그아웃</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /users/logout/{userId}<br>
HTTP METHOD : GET<br>
설명 : 유저 정보로 로그아웃하는 API입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b.  URL(경로 변수)

### 1.설명
| 키       | 데이터타입  | 설명 |
|---------|--------|----|
| userId  | long    | 유저 식별자 |

```json
localhost:8080/users/logout/{userId}
```

### 3. 응답(Response)
| 키         | 데이터타입  | 설명       |
|-----------|--------|----------|
| message  | String | 로그아웃 메시지 |

### 응답 예시
```json
{
  "로그아웃에 성공했습니다."
}
```
### b. 로그아웃 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "유저를 찾을 수 없습니다."
}
```
</details>

## 댓글 API 설계

<details>
<summary>댓글생성</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /comments<br>
HTTP METHOD : POST<br>
설명 : 댓글를 생성하는 API 입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## c.body

| 키               | 데이터타입 | 설명 |
|-----------------|-------|----|
| commentContents | String | 댓글 |

### 2. 요청예시
```json
{
   "commentContents" : "스프링 어렵다...인생"
}
```

### 3. 응답(Response)
| 키      | 데이터타입  | 설명        |
|--------|--------|-----------|
| id     | long | 댓글 고유 식별자 |
| Comment |String| 생성 댓글     |

### 응답 예시
```json
{
     "id" : 1,
     "Comment" : "스프링 어렵다...인생"
}
```
### b. 생성 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "댓글을 찾을 수 없습니다."
}
```
</details>
<details>
<summary>댓글삭제</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /comments/{commentid}<br>
HTTP METHOD : DELETE<br>
설명 : 단일 댓글 정보를 삭제하는 API 입니다. <br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URL(경로 변수)

### 1.설명
| 키       | 데이터타입  | 설명 |
|---------|--------|----|
| commentid  | long    | 유저 식별자 |

```json
localhost:8080/comments/{commentid}
```

### b. 삭제 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "댓글을 찾을 수 없습니다."
}
```
</details>

## 좋아요 API 설계

<details>
<summary>좋아요 생성</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /likes<br>
HTTP METHOD : POST<br>
설명 : 좋아요를 생성하는 API 입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## c.body

| 키               | 데이터타입 | 설명  |
|-----------------|-------|-----|
| likeCount | Long   | 좋아요 |

### 2. 요청예시
```json
{
   "likeCount" : 999999
}
```

### 3. 응답(Response)
| 키       | 데이터타입  | 설명         |
|---------|--------|------------|
| id           | Long | 좋아요 고유 식별자 | 
| likeCount   | Long   | 좋아요        |


### 응답 예시
```json
{
     "id" : 1,
     "likeCount" : 999999
}
```
### b. 생성 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "좋아요를 찾을 수 없습니다."
}
```
</details>
<details>
<summary>좋아요 삭제</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /likes/{likeId}<br>
HTTP METHOD : DELETE<br>
설명 : 좋아요를 삭제하는 API 입니다. <br>


## 01.요청(Request)

### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URL(경로 변수)

### 1.설명
| 키       | 데이터타입  | 설명 |
|---------|--------|----|
| likeId  | Long    | 유저 식별자 |

### 2. 요청예시
```json
localhost:8080/likes/{likeId}
```
### b. 삭제 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "좋아요를 찾을 수 없습니다."
}
```
</details>

## 팔로우 API 설계

<details>
<summary>팔로우 생성</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /follows/{targetUserId}<br>
HTTP METHOD : POST<br>
설명 : 팔로우를 생성하는 API 입니다.<br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URL(경로 변수)

### 1.설명
| 키            | 데이터 타입 | 설명          |
|---------------|--------------|-------------|
| targetUserId  | Long         | 팔로우할 유저의 Id |

### 2. 요청예시
```json
localhost:8080/follows/{targetUserId}
```

### 3. 응답(Response)
| 키      | 데이터타입  | 설명         |
|--------|--------|------------|
| follow | String   | 팔로우 상태 메시지 |


### 응답 예시
```json
{
     "follow" : "팔로우 성공하였습니다."
}
```
### b. 생성 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "팔로우를 할 수 없는 유저입니다."
}
```
</details>
<details>
<summary>유저가 팔로우한 사람들 조회</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /follows/following<br>
HTTP METHOD : GET<br>
설명 : 유저가 팔로우한 사람들을 조회하는 API 입니다. <br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URL (경로 변수)

### 1.설명
| 키       | 데이터타입  | 설명      |
|---------|--------|---------|
| following  | String | 팔로우한 사람 |

```json
localhost:8080/follow/following
```

### 3. 응답(Response)
| 키 | 데이터타입 | 설명         |
|--|------|------------|
| username1 | String     | 팔로우한 유저    |
| username2 | String     | 팔로우한 유저    |
| username3 | String     | 팔로우한 유저    |

### 응답 예시
```json
{
    "username1" : "홍길동",
    "username2" : "이형준",
    "username3" : "아기공룡둘리"
}
```
### b. 조회 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "팔로우한 사람이 없습니다."
}
```
</details>
<details>
<summary>유저를 팔로우한 사람들 조회</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /follows/followers<br>
HTTP METHOD : GET<br>
설명 : 유저를 팔로우한 사람들을 조회하는 API 입니다. <br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. URL(경로 변수)

### 1.설명
| 키       | 데이터타입  | 설명      |
|---------|--------|---------|
| followers  | String | 팔로우한 유저 |

```json
localhost:8080/follows/followers
```

### 3. 응답(Response)
| 키 | 데이터타입 | 설명         |
|--|------|------------|
| username1 | String     | 팔로우한 유저    |
| username2 | String     | 팔로우한 유저    |
| username3 | String     | 팔로우한 유저    |


### 응답 예시
```json
{
    "username1" : "길동",
    "username2" : "형준",
    "username3" : "아기공룡희동이"
  }
}
```
### b. 조회 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "팔로우한 사람이 없습니다."
}
```
</details>
<details>
<summary>팔로우 취소</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 00.개요<br>
URL : /follows/{followId}<br>
HTTP METHOD : DELETE<br>
설명 : 단일 댓글 정보를 삭제하는 API 입니다. <br>


## 01.요청(Request)
### 1.설명

|key|value|
|---|-----|
|Content-type|application/json

### 2. 예시
Content-type : application/json

## b. Param(파라미터 값이 필요한 경우)

### 1.설명
| 키       | 데이터타입  | 설명      |
|---------|--------|---------|
| followId  | Long | 팔로우 식별자 |

```json
localhost:8080/follows/{followId}
```

### 3. 응답(Response)
| 키 | 데이터타입 | 설명         |
|--|------|------------|
| follow | String     | 팔로우 상태 메시지 |

### 응답 예시
```json
{
    "follow" : "팔로우 취소하였습니다."
}
```
### b. 생성 실패 응답

| 키       | 데이터타입  | 설명  |
|---------|--------|-----|
| status  | int    | 상태코드 |
| message | String | 에러관련 메시지 |

### 실패 응답 예시
```json
{
   "status" : "404",
   "message" : "팔로우한 사람이 없습니다."
}
```
</details>