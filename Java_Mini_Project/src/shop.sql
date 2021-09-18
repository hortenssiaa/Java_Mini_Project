-- ȸ�����̺�
create table member_tb (
    member_id   varchar2(10) primary key -- ȸ��ID
    ,password   varchar2(10) not null -- ��й�ȣ
    ,name   varchar2(10)  not null  -- ȸ���̸�
    ,address   varchar2(200)  not null -- ȸ���ּ�
    ,indate    date     default sysdate -- ������
    ,gender   varchar2(1) constraint member_tb_gender_ck check(gender in('m', 'f')) -- ����
    ,age  number(6, -2) default 0 not null -- ����
);
-- ��ǰ���̺�
create table product_tb (
    p_id  number  primary key -- ��ǰID
    ,p_name varchar2(20) not null -- ��ǰ��
    ,cost  number   not null -- ����
    ,price number not null -- �Һ��ڰ���
    ,p_company  varchar2(20) not null  -- ������  
    ,p_detail   varchar2(200) not null -- ��ǰ����
);
drop sequence p_seq;
create sequence p_seq;

-- �ֹ����̺�
create table orders_tb (
    order_id   number   primary key -- �ֹ�ID
    ,member_id  varchar2(10)  constraint order1_fk references member_tb(member_id) on delete cascade -- ȸ��ID
    ,p_id   number    constraint order2_fk references product_tb(p_id) on delete cascade -- ��ǰID
    ,order_date date    default sysdate -- �ֹ���¥
    ,order_quan number  default 0 not null --�ֹ�����
);
drop sequence order_seq;
create sequence order_seq;

-- ���������̺�
create table admin_tb ( 
     admin_id  varchar2(10) primary key -- ���� ID
     ,admin_pwd number not null -- ��й�ȣ
);

-- ��ٱ������̺�
create table cart_tb (
     cart_id number primary key -- ��ٱ���ID
     ,member_id  varchar2(10) constraint cart1_fk references member_tb(member_id) on delete cascade --ȸ��ID
     ,p_id   number  constraint cart2_fk references product_tb(p_id) on delete cascade -- ��ǰID
     ,cart_quan number default 0 not null -- ����
);
create sequence cart_seq;

-- �Խ������̺�
create table board_tb (
    boardnum number primary key -- �۹�ȣ
    ,name  varchar2(10)  not null  -- ȸ���̸� 	
    ,title varchar2(100) not null	-- ����
    ,content varchar2(2000) not null -- ����
    ,hits number default 0 not null  -- ��ȸ��
    ,credate date default sysdate -- ������
);
create sequence board_seq;

-- �˻������̺�
create table keyword_tb (
    keyword_id number primary key -- �˻�ID
    ,keyword varchar2(20) not null -- �˻���
    ,member_id   varchar2(10) constraint keyword_fk references member_tb(member_id) on delete cascade -- ȸ��ID
    ,searchdate date default sysdate	--�˻���
);	
create sequence keyword_seq;
