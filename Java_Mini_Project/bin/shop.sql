-- 회원테이블
create table member_tb (
    member_id   varchar2(10) primary key -- 회원ID
    ,password   varchar2(10) not null -- 비밀번호
    ,name   varchar2(10)  not null  -- 회원이름
    ,address   varchar2(200)  not null -- 회원주소
    ,indate    date     default sysdate -- 가입일
    ,gender   varchar2(1) constraint member_tb_gender_ck check(gender in('m', 'f')) -- 성별
    ,age  number(6, -2) default 0 not null -- 나이
);
-- 상품테이블
create table product_tb (
    p_id  number  primary key -- 상품ID
    ,p_name varchar2(20) not null -- 상품명
    ,cost  number   not null -- 원가
    ,price number not null -- 소비자가격
    ,p_company  varchar2(20) not null  -- 제조사  
    ,p_detail   varchar2(200) not null -- 상품설명
);
drop sequence p_seq;
create sequence p_seq;

-- 주문테이블
create table orders_tb (
    order_id   number   primary key -- 주문ID
    ,member_id  varchar2(10)  constraint order1_fk references member_tb(member_id) on delete cascade -- 회원ID
    ,p_id   number    constraint order2_fk references product_tb(p_id) on delete cascade -- 상품ID
    ,order_date date    default sysdate -- 주문날짜
    ,order_quan number  default 0 not null --주문수량
);
drop sequence order_seq;
create sequence order_seq;

-- 관리자테이블
create table admin_tb ( 
     admin_id  varchar2(10) primary key -- 계정 ID
     ,admin_pwd number not null -- 비밀번호
);

-- 장바구니테이블
create table cart_tb (
     cart_id number primary key -- 장바구니ID
     ,member_id  varchar2(10) constraint cart1_fk references member_tb(member_id) on delete cascade --회원ID
     ,p_id   number  constraint cart2_fk references product_tb(p_id) on delete cascade -- 상품ID
     ,cart_quan number default 0 not null -- 수량
);
create sequence cart_seq;

-- 게시판테이블
create table board_tb (
    boardnum number primary key -- 글번호
    ,name  varchar2(10)  not null  -- 회원이름 	
    ,title varchar2(100) not null	-- 제목
    ,content varchar2(2000) not null -- 내용
    ,hits number default 0 not null  -- 조회수
    ,credate date default sysdate -- 생성일
);
create sequence board_seq;

-- 검색어테이블
create table keyword_tb (
    keyword_id number primary key -- 검색ID
    ,keyword varchar2(20) not null -- 검색어
    ,member_id   varchar2(10) constraint keyword_fk references member_tb(member_id) on delete cascade -- 회원ID
    ,searchdate date default sysdate	--검색일
);	
create sequence keyword_seq;
