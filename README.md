# Army System

이 시스템은 군 복무 중인 사용자를 위한 관리 시스템입니다. 주요 기능은 회원가입/로그인, 군 복무 일정 추적, 휴가 관리, 군 게시판, 식단 정보 제공 등입니다.

## 기능

### 1. 회원가입 / 로그인
- 군번, 비밀번호, 이메일 등을 이용한 사용자 회원가입.
- 로그인 시 JWT(JSON Web Token) 기반의 인증 방식 적용.

### 2. 남은 군 일수 표시
- 군번을 기준으로 입대일과 전역일을 바탕으로 남은 군 복무 일수를 계산하여 표시.
- 전체 복무 일수, 현재 복무 일수, 남은 복무 일수, 복무 완료 비율(퍼센트) 제공.

### 3. 휴가 계획 일정표
- **외출, 외박, 휴가 CRUD**: 외출, 외박, 연가(일반 휴가) 등의 관리 기능 제공.
- **포상 및 위로휴가 CRUD**: 군복무 중 포상이나 위로휴가에 대한 CRUD 관리 기능.

### 4. 군 게시판
- 게시글 등록, 수정, 삭제, 조회 기능 제공.
- 페이지네이션을 통한 게시글 목록 관리.
- 파일 업로드 및 다운로드 지원.
- 댓글 및 대댓글 기능 제공 (등록, 수정, 삭제).

### 5. 프로필 관리
- 유저의 개인정보 및 군 복무 관련 정보를 조회 및 수정.
- 회원 탈퇴 기능 제공.

### 6. 식단 정보
- **오늘/내일 식단 조회**: 아침, 점심, 저녁 메뉴 제공.

---

## 데이터베이스 구조

### 1. 유저 테이블 (`military_user`)
군 복무 유저 정보를 저장하는 테이블입니다.

```sql
CREATE TABLE military_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    army_number VARCHAR(20) NOT NULL,   -- 군번
    password VARCHAR(255) NOT NULL,     -- 비밀번호 (암호화 저장)
    email VARCHAR(100) NOT NULL,        -- 이메일
    name VARCHAR(50),                   -- 이름
    dept VARCHAR(100),                  -- 부서
    phone_number VARCHAR(50),           -- 전화번호
    army_type VARCHAR(50),              -- 군 종류 (육군, 해군, 공군, 해병대)
    role VARCHAR(10),                   -- 역할 (병사, 간부 등)
    enlistment_date DATE,               -- 입대일
    discharge_date DATE,                -- 전역일
    UNIQUE(army_number)                 -- 군번은 유니크
);
```
### 2. 휴가 수 관리 테이블 (`military_leave`)
연가와 추가적으로 받은 휴가(포상, 위로)를 관리하는 테이블입니다.

```sql
CREATE TABLE military_leave (
    leave_id BIGINT AUTO_INCREMENT PRIMARY KEY,       -- 휴가 ID
    army_number VARCHAR(20) NOT NULL,                 -- 군번
    leave_type VARCHAR(20) NOT NULL,                  -- 휴가 종류 (포상, 위로, 연가, 징계)
    leave_days INT,                                   -- 휴가 일수
    reason VARCHAR(255),                              -- 휴가 사유
    reg_date_time DATETIME DEFAULT CURRENT_TIMESTAMP, -- 등록 일시 (현재 시간)
    FOREIGN KEY (army_number) REFERENCES military_user(army_number)ON DELETE CASCADE   -- 사용자 삭제 시 휴가도 함께 삭제
);

```

