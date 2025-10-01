기능

*회원가입/로그인
*남은 군 일수 표시 -전체/현재/남은 일수, 퍼센트, 입대일, 전역일
*휴가 계획 일정표 -외출/외박/휴가 CRUD -(포상,위로)추가 휴가 CRUD
*군 게시판 - 게시글 등록,수정,삭제,조회 -페이지네이션 -파일 업로드/다운로드 -댓글/대댓글 등록,수정,삭제
*프로필 -전체적인 데이터 조회 및 수정, 회원탈퇴
*식단 -오늘/내일 아침/점심/저녁 메뉴

-유저
CREATE TABLE military_user (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
army_number VARCHAR(20) NOT NULL,
password VARCHAR(255) NOT NULL,
email VARCHAR(100) NOT NULL,
name VARCHAR(50),
dept VARCHAR(100),
phone_number VARCHAR(50),
army_type VARCHAR(50),
role VARCHAR(10),
enlistment_date DATE,
discharge_date DATE,
UNIQUE(army_number)
);

-휴가 일수(포상, 위로휴가 추가)
CREATE TABLE military_leave (
leave_id BIGINT AUTO_INCREMENT PRIMARY KEY,
army_number VARCHAR(20) NOT NULL,
leave_type VARCHAR(20) NOT NULL,
leave_days int,
reason VARCHAR(255),
FOREIGN KEY (army_number) REFERENCES military_user(army_number) ON DELETE CASCADE
);

-휴가 사용
