drop table member cascade constraint purge;
drop table category cascade constraint purge;
drop table funding cascade constraint purge;
drop table funding_product cascade constraint purge;
drop table notice cascade constraint purge;
drop table order_item cascade constraint purge;
drop table orders cascade constraint purge;
drop table payment_method cascade constraint purge;
drop table review cascade constraint purge;
drop table the_hyundai cascade constraint purge;
drop table competition cascade constraint purge;
drop table product_option cascade constraint purge;
drop table event cascade constraint purge;
drop table coupon_detail cascade constraint purge;
drop table qr_code cascade constraint purge;
drop table funding_img cascade constraint purge;
drop table jwt_refresh cascade constraint purge;

-- 생성자 Oracle SQL Developer Data Modeler 21.2.0.183.1957
--   위치:        2023-03-26 22:03:02 KST
--   사이트:      Oracle Database 21c
--   유형:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE category (
                          ctid   VARCHAR2(100 BYTE) NOT NULL,
                          ctname VARCHAR2(30 BYTE) NOT NULL
);

ALTER TABLE category ADD CONSTRAINT category_pk PRIMARY KEY ( ctid );

CREATE TABLE competition (
                             cid                 VARCHAR2(100 BYTE) NOT NULL,
                             cfunding_start_date DATE NOT NULL,
                             cfunding_end_date   DATE NOT NULL,
                             cstore_start_date   DATE NOT NULL,
                             cstore_end_date     DATE NOT NULL,
                             tid                 VARCHAR2(100 BYTE) NOT NULL
);

ALTER TABLE competition ADD CONSTRAINT competition_pk PRIMARY KEY ( cid,
                                                                    tid );

CREATE TABLE coupon_detail (
                               cpid         VARCHAR2(100 BYTE) NOT NULL,
                               memail       VARCHAR2(60 BYTE) NOT NULL,
                               cpissuedate  TIMESTAMP NOT NULL,
                               cpexpiredate TIMESTAMP NOT NULL,
                               cpusedate    TIMESTAMP,
                               eid          VARCHAR2(100 BYTE) NOT NULL
);

ALTER TABLE coupon_detail ADD CONSTRAINT coupon_detail_pk PRIMARY KEY ( cpid );

ALTER TABLE coupon_detail ADD CONSTRAINT coupon_detail_memail_un UNIQUE ( memail,
                                                                          eid );

CREATE TABLE event (
                       eid          VARCHAR2(100 BYTE) NOT NULL,
                       etitle       VARCHAR2(100 BYTE) NOT NULL,
                       econtent     CLOB,
                       eissuedate   TIMESTAMP,
                       eexpiredate  TIMESTAMP,
                       ethumb       VARCHAR2(400 BYTE) NOT NULL,
                       ediscount    NUMBER NOT NULL,
                       ecoupontitle VARCHAR2(100) NOT NULL,
                       etype        VARCHAR2(100 BYTE) NOT NULL
);

ALTER TABLE event ADD CONSTRAINT event_pk PRIMARY KEY ( eid );

CREATE TABLE funding (
                         fid            VARCHAR2(100 BYTE) NOT NULL,
                         fstore_name    VARCHAR2(100 BYTE),
                         ftitle         VARCHAR2(100 BYTE),
                         fsummary       VARCHAR2(1000 BYTE),
                         fstory         CLOB,
                         fdate          DATE,
                         fstatus        VARCHAR2(20 BYTE),
                         memail         VARCHAR2(60 BYTE) NOT NULL,
                         fstore_score   NUMBER,
                         ffunding_score NUMBER,
                         ctid           VARCHAR2(100 BYTE),
                         fthumb         VARCHAR2(300 BYTE),
                         cid            VARCHAR2(100 BYTE),
                         tid            VARCHAR2(100 BYTE)
);

COMMENT ON COLUMN funding.fstatus IS
    '초기는 PARTICIPATE
펀딩 시 FUNDING
스토어 STORE';

ALTER TABLE funding ADD CONSTRAINT funding_pk PRIMARY KEY ( fid );

CREATE TABLE funding_img (
                             fiid  VARCHAR2(100 BYTE) NOT NULL,
                             fiurl VARCHAR2(300 BYTE) NOT NULL,
                             fid   VARCHAR2(100 BYTE) NOT NULL
);

ALTER TABLE funding_img ADD CONSTRAINT funding_img_pk PRIMARY KEY ( fiid,
                                                                    fid );

CREATE TABLE funding_product (
                                 fpid           VARCHAR2(100 BYTE) NOT NULL,
                                 fid            VARCHAR2(100 BYTE) NOT NULL,
                                 fporigin_price NUMBER NOT NULL,
                                 fpprice        NUMBER NOT NULL,
                                 fptitle        VARCHAR2(100 BYTE) NOT NULL,
                                 fpcontent      VARCHAR2(300 BYTE) NOT NULL
);

ALTER TABLE funding_product ADD CONSTRAINT funding_product_pk PRIMARY KEY ( fpid );

CREATE TABLE jwt_refresh (
                             ref_token VARCHAR2(200 BYTE) NOT NULL,
                             memail    VARCHAR2(60 BYTE) NOT NULL
);

CREATE UNIQUE INDEX jwt_refresh__idx ON
    jwt_refresh (
                 memail
                 ASC );

CREATE TABLE member (
                        memail     VARCHAR2(60 BYTE) NOT NULL,
                        mpassword  VARCHAR2(200 BYTE),
                        mname      VARCHAR2(80 BYTE) NOT NULL,
                        mphone     VARCHAR2(30 BYTE) NOT NULL,
                        mlogintype VARCHAR2(30 BYTE),
                        menabled   NUMBER(1) NOT NULL,
                        mrole      VARCHAR2(100) NOT NULL,
                        mmileage   NUMBER
);

ALTER TABLE member ADD CONSTRAINT member_pk PRIMARY KEY ( memail );

CREATE TABLE notice (
                        nid      VARCHAR2(100 BYTE) NOT NULL,
                        ntitle   VARCHAR2(100 BYTE) NOT NULL,
                        ncontent CLOB NOT NULL,
                        ndate    DATE NOT NULL,
                        nthumb   VARCHAR2(300 BYTE) NOT NULL
);

ALTER TABLE notice ADD CONSTRAINT notice_pk PRIMARY KEY ( nid );

CREATE TABLE order_item (
                            oid          VARCHAR2(100 BYTE) NOT NULL,
                            poid         VARCHAR2(100 BYTE) NOT NULL,
                            oicount      NUMBER NOT NULL,
                            oitotalprice NUMBER NOT NULL
);

ALTER TABLE order_item ADD CONSTRAINT order_item_pk PRIMARY KEY ( oid,
                                                                  poid );

CREATE TABLE orders (
                        oid           VARCHAR2(100 BYTE) NOT NULL,
                        ophone        VARCHAR2(11 BYTE) NOT NULL,
                        omemo         VARCHAR2(300 BYTE),
                        oused_mileage NUMBER,
                        oorigin_price NUMBER NOT NULL,
                        oprice        NUMBER NOT NULL,
                        ostatus       VARCHAR2(15 BYTE) NOT NULL,
                        odate         DATE,
                        memail        VARCHAR2(60 BYTE) NOT NULL,
                        pmcode        VARCHAR2(100 BYTE) NOT NULL,
                        qrused_date   DATE,
                        cpid          VARCHAR2(100 BYTE)
);

COMMENT ON COLUMN orders.ostatus IS
    'COMPLETE	 결제 완료
WAITING            	결제 대기
REFUND		환불';

ALTER TABLE orders ADD CONSTRAINT orders_pk PRIMARY KEY ( oid );

CREATE TABLE payment_method (
                                pmcode    VARCHAR2(100 BYTE) NOT NULL,
                                pmcompany VARCHAR2(100 BYTE),
                                pmmethod  NUMBER(1)
);

ALTER TABLE payment_method ADD CONSTRAINT payment_method_pk PRIMARY KEY ( pmcode );

ALTER TABLE payment_method ADD CONSTRAINT payment_method_un UNIQUE ( pmmethod,
                                                                     pmcompany );

CREATE TABLE product_option (
                                poid     VARCHAR2(100 BYTE) NOT NULL,
                                pooption VARCHAR2(100) NOT NULL,
                                fpid     VARCHAR2(100 BYTE) NOT NULL
);

ALTER TABLE product_option ADD CONSTRAINT product_option_pk PRIMARY KEY ( poid );

CREATE TABLE qr_code (
                         qid     VARCHAR2(100 BYTE) NOT NULL,
                         link    VARCHAR2(300 BYTE) NOT NULL,
                         qr_code VARCHAR2(2000 BYTE) NOT NULL,
                         oid     VARCHAR2(100 BYTE) NOT NULL
);

CREATE UNIQUE INDEX qr_code__idx ON
    qr_code (
             oid
             ASC );

ALTER TABLE qr_code ADD CONSTRAINT qr_code_pk PRIMARY KEY ( qid );

CREATE TABLE review (
                        rscore    NUMBER NOT NULL,
                        rtype     VARCHAR2(20) NOT NULL,
                        rimg_url  VARCHAR2(300 BYTE),
                        memail    VARCHAR2(60 BYTE) NOT NULL,
                        fid       VARCHAR2(100 BYTE) NOT NULL,
                        rdate     DATE NOT NULL,
                        rcontent  VARCHAR2(400 BYTE) NOT NULL,
                        recontent VARCHAR2(400 BYTE),
                        redate    DATE
);

COMMENT ON COLUMN review.rtype IS
    'FUNDING
STORE';

ALTER TABLE review
    ADD CONSTRAINT review_pk PRIMARY KEY ( rtype,
                                           memail,
                                           fid );

CREATE TABLE the_hyundai (
                             tid   VARCHAR2(100 BYTE) NOT NULL,
                             tname VARCHAR2(30) NOT NULL
);

ALTER TABLE the_hyundai ADD CONSTRAINT the_hyundai_pk PRIMARY KEY ( tid );

ALTER TABLE competition
    ADD CONSTRAINT competition_the_hyundai_fk FOREIGN KEY ( tid )
        REFERENCES the_hyundai ( tid );

ALTER TABLE coupon_detail
    ADD CONSTRAINT coupon_detail_event_fk FOREIGN KEY ( eid )
        REFERENCES event ( eid );

ALTER TABLE coupon_detail
    ADD CONSTRAINT coupon_detail_member_fk FOREIGN KEY ( memail )
        REFERENCES member ( memail );

ALTER TABLE funding
    ADD CONSTRAINT funding_category_fk FOREIGN KEY ( ctid )
        REFERENCES category ( ctid );

ALTER TABLE funding
    ADD CONSTRAINT funding_competition_fk FOREIGN KEY ( cid,
                                                        tid )
        REFERENCES competition ( cid,
                                 tid );

ALTER TABLE funding_img
    ADD CONSTRAINT funding_img_funding_fk FOREIGN KEY ( fid )
        REFERENCES funding ( fid );

ALTER TABLE funding
    ADD CONSTRAINT funding_member_fk FOREIGN KEY ( memail )
        REFERENCES member ( memail );

ALTER TABLE funding_product
    ADD CONSTRAINT funding_product_funding_fk FOREIGN KEY ( fid )
        REFERENCES funding ( fid );

ALTER TABLE jwt_refresh
    ADD CONSTRAINT jwt_refresh_member_fk FOREIGN KEY ( memail )
        REFERENCES member ( memail );

ALTER TABLE order_item
    ADD CONSTRAINT order_item_orders_fk FOREIGN KEY ( oid )
        REFERENCES orders ( oid );

ALTER TABLE order_item
    ADD CONSTRAINT order_item_product_option_fk FOREIGN KEY ( poid )
        REFERENCES product_option ( poid );

ALTER TABLE orders
    ADD CONSTRAINT orders_coupon_detail_fk FOREIGN KEY ( cpid )
        REFERENCES coupon_detail ( cpid );

ALTER TABLE orders
    ADD CONSTRAINT orders_member_fk FOREIGN KEY ( memail )
        REFERENCES member ( memail );

ALTER TABLE orders
    ADD CONSTRAINT orders_payment_method_fk FOREIGN KEY ( pmcode )
        REFERENCES payment_method ( pmcode );

ALTER TABLE product_option
    ADD CONSTRAINT product_option_fk FOREIGN KEY ( fpid )
        REFERENCES funding_product ( fpid );

ALTER TABLE qr_code
    ADD CONSTRAINT qr_code_orders_fk FOREIGN KEY ( oid )
        REFERENCES orders ( oid );

ALTER TABLE review
    ADD CONSTRAINT review_funding_fk FOREIGN KEY ( fid )
        REFERENCES funding ( fid );

ALTER TABLE review
    ADD CONSTRAINT review_member_fk FOREIGN KEY ( memail )
        REFERENCES member ( memail );



-- Oracle SQL Developer Data Modeler 요약 보고서:
--
-- CREATE TABLE                            17
-- CREATE INDEX                             2
-- ALTER TABLE                             36
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
--
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
--
-- REDACTION POLICY                         0
--
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
--
-- ERRORS                                   0
-- WARNINGS                                 0
