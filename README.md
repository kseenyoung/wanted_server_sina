# Wanted_a Server
| ip | url |
| :--: | :--:  |
|3.36.94.149 | <https://prod.wanted-a.online> |


<br>

# 🌄라이징 테스트
기간 : ***3/4(토) ~ 3/17(금)***
## 서버 일정
| 3/4(토) | 3/5(일) | 3/6(월) | 3/7(화) |
|:--:|:--:|:--:|:--:|
| 라이징테스트 OT 및 기획서 작성 | - | 위클리 스크럼 | 1차 피드백 |

<br>

## 서버 일지
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

> - DNS 네임 서버 적용 에러 : 홈페이지를 새로고침/ 다시 시작하여 알맞게 입력했는지 다시 한 번 확인하고 시간을 가지고 기다리자. 도메인이 적용되는데 최대 1시간은 필요할 수도 있음
> - EC2 ip로 외부 ip에서 요청시 실행 안 됨 : EC2의 인바운드 규칙을 모든 ip로 수정
> - 서브 도메인 폴더 생성 권한 제한 : permission denied : mysql에 등록된 root 외에 다른 유저가 폴더에 접근할 수 있도록 권한을 부여해 주어야 한다.
> - 서브 도메인 : 연결이 비공개로 설정되어 있지 않습니다 : nginx에 서버를 입력할 때 기존 server 안에 코드를 작성했다. 항상 각각의 서버는 서로를 포함하는 것이 아니라 구분하여 작성해야 함

</div>
</details>

<br>

### 3/6(월)
 - ERD 설계(50%)
 
|PW | URL|
|:--:|:--:|
| k3b32q | https://aquerytool.com/aquerymain/index/?rurl=d499a5af-40ba-4e71-941c-57a46a582aaf& |

 - 회원가입 API 명세서 작성
 - 회원가입 API 작성 및 서버 적용
 - 80번호 포트 진입시 자동 9000포트 사용 적용
 - 서버 백그라운드 실행 적용(nohup)
 
 <details>
<summary> 🔎오류 해결</summary>
<div markdown="1">

 > DDL 적용할 때 No database selected 에러 : `use <database명>;` 실행 후 테이블 생성하기 --- https://dalpeng2.tistory.com/84 
 > 비밀번호 정규 표현식 비정상작동 : match에 보내주는 문자열 변수를 잘 못 지정하고 있었다.
 > 인스턴스 내 .jar 파일 실행시 mysql 연결 오류 : 인스턴스 인바운드 규칙에 mysql 추가 & RDS 연결 설정을 통해 해결하였다!

</div>
</details>
