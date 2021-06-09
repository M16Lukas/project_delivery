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
create table MEMBER (   -- ȸ�� ���̺�
    member_num          number(38)     primary key      --ȸ�� ��ȣ
    ,member_id          varchar2(20)   not null         -- ȸ�� id
    ,member_password    varchar2(20)   not null         -- ȸ�� ��й�ȣ
    ,member_sort        number(1)                       -- �������� �з� (0: ������, 1 : ����ȸ��, 2: ����ȸ��)
    ,member_signup      date           default sysdate  -- ȸ��������
    ,member_withdrawal  number(1)      default 0        -- ȸ�� ���� �з�(0 : Ȱ�� ����, 1: Ż�� ����, 2: �޸� ����)
);

create SEQUENCE member_seq;

create table AREA ( -- ���� ���̺�
    area_num    number(11)      primary key -- ���� ��ȣ
    ,area_name  varchar2(60)    not null    -- ���� �̸�
);

create sequence area_seq;

create table STORE_CODE (   -- ���� ���� ���̺�
    code_num    number(11)      primary key -- ���� ��ȣ
    ,code_name  varchar2(20)    not null    -- ���� �̸�
);

create sequence code_seq;

create table STORE (    -- ���� ���̺�
    store_num       number(38)      primary key                     -- ���� ��ȣ
    ,member_num     number(38)      references member(member_num)   -- ȸ�� ��ȣ
    ,area_num       number(11)      references area(area_num)       -- ���� ��ȣ
    ,code_num       number(11)      references store_code(code_num) -- ���� ��ȣ
    ,store_name     varchar2(100)   not null                        -- ���� �̸�
    ,store_phone    varchar2(15)    not null                        -- ���� ��ȭ��ȣ
    ,minOrderPrice  number(20)      default 0                       -- �ּ��ֹ� �ݾ�
    ,deliveryTime   number(20)      default 0                       -- ��޽ð�
    ,deliveryTip    number(20)      default 0                       -- �����
);

create sequence store_seq;

create table MENU ( -- �޴� ���̺�
    menu_num        number(20)      primary key                 -- �޴� ��ȣ
    ,store_num      number(38)      references store(store_num) -- ���� ��ȣ
    ,menu_name      varchar2(100)   not null                    -- �޴� �̸�
    ,menu_intro     varchar2(200)                               -- �޴� �Ұ�
    ,menu_price     number(20)      default 0                   -- �޴� ����
    ,menu_soldout   number(1)       default 0                   -- �޴� ǰ�� ����(0: �Ǹ���, 1: ����)
);

create sequence menu_seq;

create table CUSTOMER ( -- �� ���̺�
    customer_num        number(38)      primary key                     -- �� ��ȣ
    ,member_num         number(38)      references member(member_num)   -- ȸ�� ��ȣ
    ,customer_name      varchar2(100)   not null                        -- �� �̸�
    ,customer_phone     varchar2(15)    not null                        -- �� ��ȭ��ȣ
    ,area_num           number(11)      references area(area_num)       -- ���� ��ȣ
    ,customer_address   varchar2(100)   not null                        -- ���ּ�
);

create sequence customer_seq;

create table ORDERED_MENU ( -- �ֹ� ���̺�
    ordered_num     number(38)  primary key                         -- �ֹ� ��ȣ
    ,customer_num   number(38)  references customer(customer_num)   -- �� ��ȣ
    ,store_num      number(38)  references store(store_num)         -- ���� ��ȣ
    ,menu_num       number(20)  references menu(menu_num)           -- �޴� ��ȣ
    ,menu_count     number(20)  default 1                           -- �޴� �ֹ� ����
    ,ordered_time   date        default sysdate                     -- �ֹ���¥
    ,total_price    number(38)  default 0                           -- �� �ֹ� �ݾ�
);

create sequence ordered_menu_seq;

commit;
/*
* ���� ���̺�(Area)
*/
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '����Ư����');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '��⵵');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '��õ������');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '������');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '��û����');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '����������');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '��û�ϵ�');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '������');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '�λ걤����');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '��걤����');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '�뱸������');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '���ϵ�');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '��󳲵�');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '���󳲵�');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '���ֱ�����');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '����ϵ�');
insert into area(area_num, area_name) values (AREA_SEQ.nextval, '���ֵ�');

/*
* ���� ���̺� (store_code)
*/
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '1�κ�');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '�ѽ�');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '�н�');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, 'ī��,����Ʈ');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '���,ȸ,�Ͻ�');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, 'ġŲ');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '����');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '�ƽþ�,���');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '�߱���');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '����,����');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '�߽�');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '��,��');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '���ö�');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, '�н�ƮǪ��');
insert into store_code(code_num, code_name) values (CODE_SEQ.nextval, 'ä��');

/*
*   ȸ�� ���̺�(member)
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
* ���� ���̺� (store)
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
    (store_seq.nextval, 7, 1, 2, 'IT�ѽ�', '02-1111-2222', 1000, 30, 1000);
    
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
    (store_seq.nextval, 8, 2, 1, '�Ϻ���', '031-1231-2782', 6000, 40, 3000);

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
    (store_seq.nextval, 10, 1, 8, '�ڹ��Ľ�Ÿ', '02-5555-2323', 3000, 35, 2000);

/*
* �޴� ���̺� (menu)
*/
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 1, '��ġ�', '������Ⱑ �� ��ġ�', 6500);
    
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 1, '���κ��', '', 6000);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 1, '�δ��', '', 7000);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 1, '�����', '', 6500);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 1, '��������', '', 5500);
    
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 2, '�Ϲݵ��', '', 6500);
    
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 2, 'ġ��', '', 7000);
    
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 2, '�Ƚɵ��', '', 7500);
    
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 2, '�쵿', '', 4500);
    
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 2, '�޹мҹ�', '', 6000);
    
insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 3, '�˸����ø���', '', 5000);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 3, 'ũ���Ľ�Ÿ', '', 6000);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 3, '�Ǹ��� ũ��ġ�� ����', '', 14900);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 3, '��Ʈ�Ľ�Ÿ', '', 8500);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 3, '�����Ľ�Ÿ', '', 7500);

insert into
    menu(menu_num, store_num, menu_name, menu_intro, menu_price)
values
    (MENU_SEQ.nextval, 3, '���۷δ�����', '', 14900);

/*
* �� ���̺� (customer)
*/
insert into 
    customer
values
    (CUSTOMER_SEQ.nextval, 3, '������', '010-1212-4343', 1, '����Ư���� ������');
    
insert into 
    customer
values
    (CUSTOMER_SEQ.nextval, 5, '����', '010-7654-8888', 1, '����Ư���� ���۱�');
    
insert into 
    customer
values
    (CUSTOMER_SEQ.nextval, 8, '������', '010-2113-9908', 2, '��⵵ ���ֽ�');

insert into 
    customer
values
    (CUSTOMER_SEQ.nextval, 9, '���', '010-3433-9658', 2, '��⵵ �Ȼ��');

insert into 
    customer
values
    (CUSTOMER_SEQ.nextval, 10, 'ũ����', '010-8745-0012', 2, '��⵵ ������');

/*
* �ֹ� ���̺�(ordered_menu)
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