/*
*    drop table & sequence
*/
drop table ordered_menu;
drop sequence ordered_menu_seq;
drop table customer;
drop sequence customer_seq;
drop table menu;
drop sequence menu_seq;
drop table store;
drop sequence store_seq;
drop table store_code;
drop sequence code_seq;
drop table area;
drop sequence area_seq;
drop table member;
drop sequence member_seq;
COMMIT;
/*
* create table
*/
create table MEMBER (   -- 회원 테이블
    member_num          number(38)     primary key      --회원 번호
    ,member_id          varchar2(20)   not null         -- 회원 id
    ,member_password    varchar2(20)   not null         -- 회원 비밀번호
    ,member_sort        number(1)                       -- 가입유형 분류 (0: 관리자, 1 : 매장회원, 2: 개인회원)
    ,member_signup      date           default sysdate  -- 회원가입일
    ,member_withdrawal  number(1)      default 0        -- 회원 상태 분류(0 : 활동 계정, 1: 탈퇴 계정, 2: 휴면 계정)
);

create SEQUENCE member_seq;

create table AREA ( -- 지역 테이블
    area_num    number(11)      primary key -- 지역 번호
    ,area_name  varchar2(60)    not null    -- 지역 이름
);

create sequence area_seq;

create table STORE_CODE (   -- 업종 구분 테이블
    code_num    number(11)      primary key -- 업종 번호
    ,code_name  varchar2(20)    not null    -- 업종 이름
);

create sequence code_seq;

create table STORE (    -- 매장 테이블
    store_num       number(38)      primary key                     -- 매장 번호
    ,member_num     number(38)      references member(member_num)   -- 회원 번호
    ,area_num       number(11)      references area(area_num)       -- 지역 번호
    ,code_num       number(11)      references store_code(code_num) -- 업종 번호
    ,store_name     varchar2(100)   not null                        -- 매장 이름
    ,store_phone    varchar2(15)    not null                        -- 매장 전화번호
    ,minOrderPrice  number(20)      default 0                       -- 최소주문 금액
    ,deliveryTime   number(20)      default 0                       -- 배달시간
    ,deliveryTip    number(20)      default 0                       -- 배달팁
);

create sequence store_seq;

create table MENU ( -- 메뉴 테이블
    menu_num        number(20)      primary key                 -- 메뉴 번호
    ,store_num      number(38)      references store(store_num) -- 매장 번호
    ,menu_name      varchar2(100)   not null                    -- 메뉴 이름
    ,menu_intro     varchar2(200)                               -- 메뉴 소개
    ,menu_price     number(20)      default 0                   -- 메뉴 가격
    ,menu_soldout   number(1)       default 0                   -- 메뉴 품질 여부(0: 판매중, 1: 매진)
);

create sequence menu_seq;

create table CUSTOMER ( -- 고객 테이블
    customer_num        number(38)      primary key                     -- 고객 번호
    ,member_num         number(38)      references member(member_num)   -- 회원 번호
    ,customer_name      varchar2(100)   not null                        -- 고객 이름
    ,customer_phone     varchar2(15)    not null                        -- 고객 전화번호
    ,area_num           number(11)      references area(area_num)       -- 지역 번호
    ,customer_address   varchar2(100)   not null                        -- 고객주소
);

create sequence customer_seq;

create table ORDERED_MENU ( -- 주문 테이블
    ordered_num     number(38)  primary key                         -- 주문 번호
    ,customer_num   number(38)  references customer(customer_num)   -- 고객 번호
    ,store_num      number(38)  references store(store_num)         -- 매장 번호
    ,menu_num       number(20)  references menu(menu_num)           -- 메뉴 번호
    ,menu_count     number(20)  default 1                           -- 메뉴 주문 개수
    ,ordered_time   date        default sysdate                     -- 주문날짜
    ,total_price    number(38)  default 0                           -- 총 주문 금액
);

create sequence ordered_menu_seq;

commit;
/*
* 지역 테이블(Area)
*/
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '서울특별시');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '경기도');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '인천광역시');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '강원도');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '충청남도');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '대전광역시');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '충청북도');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '세종시');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '부산광역시');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '울산광역시');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '대구광역시');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '경상북도');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '경상남도');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '전라남도');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '광주광역시');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '전라북도');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '제주도');

/*
* 업종 테이블 (store_code)
*/
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '1인분');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '한식');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '분식');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '카페,디저트');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '돈까스,회,일식');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '치킨');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '피자');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '아시안,양식');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '중국집');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '족발,보쌈');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '야식');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '찜,탕');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '도시락');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '패스트푸드');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '채식');

/*
*   회원 테이블(member)
*/

insert into 
    member(member_num, member_id, member_password, member_sort)
values
    (member_seq.nextval, 'scit0', 'scit0', 0);

insert into 
    member(member_num, member_id, member_password, member_sort)
values
    (member_seq.nextval, 'scit1', 'scit1', 1);

insert into 
    member(member_num, member_id, member_password, member_sort)
values
    (member_seq.nextval, 'scit2', 'scit2', 2);

insert into 
    member(member_num, member_id, member_password, member_sort)
values
    (member_seq.nextval, 'scit3', 'scit3', 1);

insert into 
    member(member_num, member_id, member_password, member_sort)
values
    (member_seq.nextval, 'scit4', 'scit4', 2);

insert into 
    member(member_num, member_id, member_password, member_sort)
values
    (member_seq.nextval, 'scit5', 'scit5', 1);
    
insert into 
    member(member_num, member_id, member_password, member_sort, member_withdrawal)
values
    (member_seq.nextval, 'scit6', 'scit6', 1, 1);


insert into 
    member(member_num, member_id, member_password, member_sort, member_withdrawal)
values
    (member_seq.nextval, 'scit7', 'scit7', 2, 1);


insert into 
    member(member_num, member_id, member_password, member_sort, member_withdrawal)
values
    (member_seq.nextval, 'scit8', 'scit8', 2, 2);


insert into 
    member(member_num, member_id, member_password, member_sort, member_withdrawal)
values
    (member_seq.nextval, 'scit9', 'scit9', 2, 1);
    
insert into 
    member(member_num, member_id, member_password, member_sort)
values
    (member_seq.nextval, 'scit9', 'scit9', 1);

/*
* 매장 테이블 (store)
*/
insert into 
    store(store_num
         ,member_num
         ,area_num
         ,code_num
         ,store_name
         ,store_phone
         ,minorderprice
         ,deliverytime
         ,deliverytip
         )
values
    (store_seq.nextval, 7, 1, 2, 'IT한식', '02-1111-2222', 1000, 30, 1000);
    
insert into 
    store(store_num
         ,member_num
         ,area_num
         ,code_num
         ,store_name
         ,store_phone
         ,minorderprice
         ,deliverytime
         ,deliverytip
         )
values
    (store_seq.nextval, 8, 2, 1, '일본어돈까스', '031-1231-2782', 6000, 40, 3000);

insert into 
    store(store_num
         ,member_num
         ,area_num
         ,code_num
         ,store_name
         ,store_phone
         ,minorderprice
         ,deliverytime
         ,deliverytip
         )
values
    (store_seq.nextval, 10, 1, 8, '자바파스타', '02-5555-2323', 3000, 35, 2000);

/*
* 메뉴 테이블 (menu)
*/
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 1, '김치찌개', '돼지고기가 들어간 김치찌개', 6500);
    
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 1, '순두부찌개', '', 6000);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 1, '부대찌개', '', 7000);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 1, '된장찌개', '', 6500);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 1, '제육볶음', '', 5500);
    
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 2, '일반돈까스', '', 6500);
    
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 2, '치즈돈까스', '', 7000);
    
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 2, '안심돈까스', '', 7500);
    
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 2, '우동', '', 4500);
    
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 2, '메밀소바', '', 6000);
    
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 3, '알리오올리오', '', 5000);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 3, '크림파스타', '', 6000);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 3, '건마늘 크림치즈 피자', '', 14900);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 3, '미트파스타', '', 8500);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 3, '오일파스타', '', 7500);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 3, '페퍼로니피자', '', 14900);

/*
* 고객 테이블 (customer)
*/
insert into 
    customer
values
    (CUSTOMER_SEQ.nextval, 3, '마리오', '010-1212-4343', 1, '서울특별시 강남구');
    
insert into 
    customer
values
    (CUSTOMER_SEQ.nextval, 5, '쿠파', '010-7654-8888', 1, '서울특별시 동작구');
    
insert into 
    customer
values
    (CUSTOMER_SEQ.nextval, 8, '루이지', '010-2113-9908', 2, '경기도 파주시');

insert into 
    customer
values
    (CUSTOMER_SEQ.nextval, 9, '요시', '010-3433-9658', 2, '경기도 안산시');

insert into 
    customer
values
    (CUSTOMER_SEQ.nextval, 10, '크리보', '010-8745-0012', 2, '경기도 수원시');

/*
* 주문 테이블(ordered_menu)
*/

insert into
    ordered_menu(ordered_num, customer_num, store_num, menu_num)
values
    (ORDERED_MENU_SEQ.nextval, 1, 1, 1);

insert into
    ordered_menu(ordered_num, customer_num, store_num, menu_num)
values
    (ORDERED_MENU_SEQ.nextval, 2, 2, 16);

commit;