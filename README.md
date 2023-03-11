# Wanted_a Server
| ip | url |
| :--: | :--:  |
|3.36.94.149 | <https://prod.wanted-a.online> |

[📜API 명세서](https://kwackr-my.sharepoint.com/:x:/g/personal/l990115l_kw_ac_kr/ETZyYKG_OrdEmI-8TL7TgpkBPpAcq5KnrnZVjMG8XcJIKA?e=vZXmj3)  
[📝ERD 설계](https://aquerytool.com/aquerymain/index/?rurl=d499a5af-40ba-4e71-941c-57a46a582aaf&) Password : k3b32q


<br>

# 🌄라이징 테스트
기간 : ***3/4(토) ~ 3/17(금)***
## 서버 일정
| 3/4(토) | 3/5(일) | 3/6(월) | 3/7(화) | 3/8(수) | 3/9(목) | 3/10(금) |
|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
| 라이징테스트 OT 및<br> 기획서 작성 | - | - | 위클리 스크럼 | 1차 피드백 | - | 라이징캠프<br> 2주차 시작 |

<br>

## 서버 일지

> 라이징 캠프 기간 발생한 오류 정리 : https://softsquared.notion.site/d158da0e4c2c4982bf80cb0f3d82a2ff?v=e099e01d7e1e4c13a52ff74596410955

<br>

### 3/4(토)
 - 깃허브 템플릿 업로드
 - 기획서 작성
 
### 3/5(일)
 - EC2 인스턴스 구축
 - 도메인 적용 (wanted-a.online)
 - dev/prod 서버 구축
 - ssl 적용
 - RDS 생성 및 연결
 - ERD 설계 시작 (30%)
 <details>
<summary> 🔎오류 해결</summary>
<div markdown="1">

> 1. DNS 네임 서버 적용 에러
>    - 홈페이지를 새로고침/ 다시 시작하여 알맞게 입력했는지 다시 한 번 확인하고 시간을 가지고 기다리자. 도메인이 적용되는데 최대 1시간은 필요할 수도 있음
> 2. EC2 ip로 외부 ip에서 요청시 실행 안 됨 
>    - EC2의 인바운드 규칙을 모든 ip로 수정
> 3. 서브 도메인 폴더 생성 권한 제한 : permission denied 
>    - mysql에 등록된 root 외에 다른 유저가 폴더에 접근할 수 있도록 권한을 부여해 주어야 한다.
> 4. 서브 도메인 : 연결이 비공개로 설정되어 있지 않습니다 
>    - nginx에 서버를 입력할 때 기존 server 안에 코드를 작성했다. 항상 각각의 서버는 서로를 포함하는 것이 아니라 구분하여 작성해야 함

</div>
</details>

<br>

### 3/6(월)
 - ERD 설계(50%)
 - 회원가입 API 명세서 작성
 - 회원가입 API 작성 및 서버 적용
 - 80번호 포트 진입시 자동 9000포트 사용 적용
 - 서버 백그라운드 실행 적용(nohup)
 
 <details>
<summary> 🔎오류 해결</summary>
<div markdown="1">

 > 1. DDL 적용할 때 No database selected 에러 
 >    - `use <database명>;` 실행 후 테이블 생성하기 --- https://dalpeng2.tistory.com/84 
 > 2. 비밀번호 정규 표현식 비정상작동 
 >    - match에 보내주는 문자열 변수를 잘 못 지정하고 있었다.
 > 3. 인스턴스 내 .jar 파일 실행시 mysql 연결 오류 
 >    - 인스턴스 인바운드 규칙에 mysql 추가 & RDS 연결 설정을 통해 해결하였다!

</div>
</details>

 
 <br>

### 3/7(화)
 - ERD 설계(80%)
 - 전체/ 특정 회원 조회 API 명세서 작성
 
 <br>

### 3/8(수)
 - ERD 설계 마무리 (수정 사항 발생시 반영 예정)
 - API 명세서 작성 (20%)
 - 전체 유저 조회 API 작성 및 서버 반영
 - 특정 유저 조회 API 작성 및 서버 반영
<details>
<summary> ✂1차 피드백</summary>
<div markdown="1">
<br>

**질문**
>  1. 서브 도메인을 분리하여 작성하였으나 기본 서버와 prod서버 동일하게 작동한다.  
  => nginx 서버 설정을 다시 한 번 살펴보도록 하자. 하지만 prod가 잘 돌아간다면 큰 문제 없다.
  
>  2. 각 서버 마다 RDS를 따로 두는 것인가?  
  => 그렇다. 작은 데이터를 다루게 될 해당 프로젝트에서는 스키마를 나눠서 따로 적용하면 된다.
  
>  3. 로그인시 이메일을 확인하고 있다면 비밀번호 요청, 없다면 회원가입으로 넘어간다. 이메일 확인 API를 따로 작성하는 것이 좋은가?  
  => 그렇다
  
>  4. 이미지를 포함하는 긴 글을 저장하는 것은 text 형식으로 그냥 저장하면 되나?  
  => 그렇다. 하지만 클라이언트에서 어떤 식으로 처리 할 것인지 상의하는 것이 좋다. 때에 따라서는 html을 저장하게 될 수도 있다. 'Summer note' 같은 웹 에디터를 사용해보라는 조언을 들었다.
  
**피드백**
>  - 코드 작성후 API 명세서를 작성하는 방식으로 진행했었다. 현재는 정확하지 않더라도 API 명세서를 먼저 작성해주어야 이를 기반으로 클라이언트가 작업하기 편할 것이라는 피드백을 받았다. 그래서 **코드 작성보다 API 명세서를 집중**하여 작성하고 있다
>  - 테이블 컬럼명은 파스칼 표현법 보다 **카멜 표기법**으로 변경해서 작성! 
>  - **표기법을 통일**하여 작성하기! (인수인계시 혼동을 줄 수 있음)
</div>
</details>

<details>
<summary> 🔎오류 해결</summary>
<div markdown="1">

> 1. 새롭게 작성한 코드를 서버에서 실행할 때 "Web server failed to start. Port 8080 was aleady in use"
>    - nohup으로 백그라운드 실행되고 있던 서버가 있어서 뜬 오류. --- https://zincod.tistory.com/16
>       1. `sudo lsof -i :<port 번호>` 명령으로 해당 포트에서 실행되는 프로세스 확인
>       2. `sudo kill -9 <pid>` 명령으로 해당하는 프로세스 종료
>       3. 새롭게 시작하려는 .jar 파일 실행 
</div>
</details>

 <br>

### 3/9(목)
 - ERD 직군(EMPLOYMENT) 테이블 추가
 - 이메일 확인 API 작성 및 서버 반영
 - 로그인 API 작성 및 서버 반영
 
<details>
<summary> 🔎오류 해결</summary>
<div markdown="1">

> 1. Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'wantedDB' defined in class path resource
>    - RDS연결이 막혀있거나 제대로 동작하지 못해서 발생하는 오류이다. 집 컴퓨터로 항상 진행하다가 카페에서 실행했더니 발생한 것으로, RDS의 인바운드 규칙을 확장 시킴으로 해결!
>    - 참고 : https://velog.io/@yhg3146/java-Spring-%EC%98%A4%EB%A5%98
> 2. Required String parameter 'email' is not present 오류
>    - email 확인 API 작성중 발생한 에러. pathvariable로 작성했는데 어노테이션을 @RequestParm 으로 해놓아서 생긴 문제였다. @PathVariable 로 변경하여서 해결
>    - 참고 : https://velog.io/@tkaqhcjstk/spring-Required-String-parameter-%EC%9D%B8%EC%9E%90-is-not-present-%EC%98%A4%EB%A5%98%ED%95%B4%EA%B2%B0

</div>
</details>

 <br>

### 3/10(금)
 - 비밀번호 변경 API 작성 및 서버 반영
 
<details>
<summary> 🔎오류 해결</summary>
<div markdown="1">

> 1. try-catch안에 if문을 이용해서 throw를 했더니 의도한 code가 아닌 가장 바깥쪽 throw가 실행 됨
>    - try-catch를 제대로 이해하지 못한 점에서 발생한 문제였다. try 안쪽이 아닌 바깥쪽에서 예외처리를 해주고 안이 아닌 밖에 try문을 다시 작성해야되는 구조였다.
>    - 참고 : https://sundrystore.tistory.com/14

</div>
</details>

 <br>

### 3/11(토)
 - 카카오 소셜 로그인 API 작성(90%) 및 서버 반영
 - 클라이언트(웹)에게 서버 진행 상황 공지
 
<details>
<summary> 🔎오류 해결</summary>
<div markdown="1">

> 1. 카카오 소셜 로그인 구현을 위해서 필요한 'org.google.gson.' 모듈 import 오류
>    - `build.gradle` 파일 dependencies에 'com.google.code.gson:gson:2.8.7' 넣어주어야 한다!
> 2. 리드미 파일 작성 전에 commit을 진행해버림 (혹은 깜빡 잊고 commit같이 하지 못 한 파일)
>    - `git add` 명령어로 해당 파일 스테이징
>    - `git commit --amend -m "<commit message>"` 를 통해 해당 파일을 포함하도록 마지막 커밋 수정!

</div>
</details>