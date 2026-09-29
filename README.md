# 일본어 학습 커뮤니티

JSP와 Servlet을 이용하여 개발한 일본어 학습 커뮤니티 개인 프로젝트입니다.

## 개발 기간

2026.07 ~ 2026.08

## 주요 기능

- 회원가입과 아이디 중복 검사
- 로그인과 로그아웃
- 회원정보 조회 및 수정
- 회원 탈퇴
- 공지사항 등록, 조회, 수정, 삭제
- 공지사항 검색과 페이지 이동
- 파일 첨부와 다운로드
- 이전 글과 다음 글 이동

## 사용 기술

### Frontend

- HTML
- CSS
- JavaScript
- JSP
- JSTL

### Backend

- Java 21
- Servlet
- JDBC

### Database

- Oracle Database 21c XE

### Environment

- Eclipse
- Apache Tomcat 9

## 실행 방법

1. Java 21과 Tomcat 9를 준비합니다.
2. Oracle DB에 필요한 테이블을 생성합니다.
3. Tomcat 실행 환경에 다음 환경변수를 등록합니다.
   - DB_URL
   - DB_USER
   - DB_PASSWORD
4. Eclipse에서 프로젝트를 Tomcat 서버에 추가합니다.
5. 서버를 실행합니다.

## 주의사항

DB 접속 정보와 실제 첨부파일은 저장소에 포함하지 않습니다.