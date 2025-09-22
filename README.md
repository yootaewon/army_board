기능
 - 회원가입/로그인
 - 남은 군 일수 표시
  - 남은 일수에 따라 문구(한참 남았어요 등등)
 - 휴가 계획 일정표
  - 외출/외박/휴가 등록,수정,삭제,조회
 - 군 게시판
  - 게시글 등록,수정,삭제,조회
  - 페이지네이션
  - 파일 업로드/다운로드
  - 댓글/대댓글 등록,수정,삭제

  이후에 리펙토링하면서 typescript 적용하면서 코드 개선

9/20일 목표
회원가입 로그인 로직 만들기(프론트는 나중에, 로직 이해하면서 주석 달기!!)

회원가입할때
군번 비밀번호 이름 입대일 군종류 이메일


군대 목표
 - 프로젝트 2개(개발하면서 공부)
 - 자격증 최소 1개
 - 리눅스 공부


 CREATE TABLE military_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    army_number VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    name VARCHAR(50),
    dept VARCHAR(100),
    phone_number VARCHAR(50),
    army_type VARCHAR(50),
    enlistment_date DATE,
    discharge_date DATE,
    UNIQUE(army_number)
);
