-- 생성자 Oracle SQL Developer Data Modeler 21.2.0.183.1957
--   위치:        2023-02-27 17:54:33 KST
--   사이트:      Oracle Database 21c
--   유형:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE category (
                          ctid   VARCHAR2(30 BYTE) NOT NULL,
                          ctname VARCHAR2(30 BYTE) NOT NULL
);

ALTER TABLE category ADD CONSTRAINT category_pk PRIMARY KEY ( ctid );

CREATE TABLE competiton (
                            cid                 VARCHAR2(100 BYTE) NOT NULL,
                            cfunding_start_date DATE NOT NULL,
                            cfunding_end_date   DATE NOT NULL,
                            cstore_start_date   DATE NOT NULL,
                            cstore_end_date     DATE NOT NULL
);

ALTER TABLE competiton ADD CONSTRAINT competiton_pk PRIMARY KEY ( cid );

CREATE TABLE funding (
                         fid             VARCHAR2(100 BYTE) NOT NULL,
                         fstore_name     VARCHAR2(100 BYTE) NOT NULL,
                         ftitle          VARCHAR2(100 BYTE) NOT NULL,
                         fsummary        VARCHAR2(100 BYTE) NOT NULL,
                         fimg1           VARCHAR2(100 BYTE) NOT NULL,
                         fimg2           VARCHAR2(100 BYTE),
                         fimg3           VARCHAR2(100 BYTE),
                         fstory          CLOB NOT NULL,
                         fdate           DATE NOT NULL,
                         fstatus         VARCHAR2(20 BYTE) NOT NULL,
                         competiton_cid  VARCHAR2(100 BYTE) NOT NULL,
                         member_memail   VARCHAR2(60 BYTE) NOT NULL,
                         the_hyundai_tid VARCHAR2(100 BYTE) NOT NULL,
                         fstore_score    NUMBER,
                         ffunding_score  NUMBER,
                         category_ctid   VARCHAR2(30 BYTE) NOT NULL
);

COMMENT ON COLUMN funding.fstatus IS
    '초기는 PARTICIPATE
펀딩 시 FUNDING
스토어 STORE';

ALTER TABLE funding ADD CONSTRAINT funding_pk PRIMARY KEY ( fid );

CREATE TABLE funding_product (
                                 fpid           VARCHAR2(100 BYTE) NOT NULL,
                                 fpoption       VARCHAR2(100 BYTE) NOT NULL,
                                 funding_fid    VARCHAR2(100 BYTE) NOT NULL,
                                 fporigin_price NUMBER NOT NULL,
                                 fpprice        unknown
--  ERROR: Datatype UNKNOWN is not allowed
    ,
                                 fptitle        VARCHAR2(100 BYTE) NOT NULL,
                                 fpcontent      VARCHAR2(300 BYTE) NOT NULL
);

ALTER TABLE funding_product ADD CONSTRAINT funding_product_pk PRIMARY KEY ( fpid );

CREATE TABLE member (
                        memail     VARCHAR2(60 BYTE) NOT NULL,
                        mpassword  VARCHAR2(200 BYTE),
                        mname      VARCHAR2(80 BYTE) NOT NULL,
                        mphone     VARCHAR2(11 BYTE) ,
                        mlogintype VARCHAR2(30 BYTE),
                        menabled   NUMBER(1) NOT NULL,
                        mrole      VARCHAR2(100) NOT NULL,
                        mmileage   NUMBER
);

ALTER TABLE member ADD CONSTRAINT member_pk PRIMARY KEY ( memail );

CREATE TABLE notice (
                        nid       VARCHAR2(30 BYTE) NOT NULL,
                        ntitle    VARCHAR2(100 BYTE) NOT NULL,
                        nthum_img VARCHAR2(100 BYTE) NOT NULL,
                        nimg1     VARCHAR2(100 BYTE) NOT NULL,
                        nimg2     VARCHAR2(100 BYTE),
                        nimg3     VARCHAR2(100 BYTE),
                        ndate     DATE NOT NULL
);

ALTER TABLE notice ADD CONSTRAINT notice_pk PRIMARY KEY ( nid );

CREATE TABLE order_item (
                            funding_product_fpid VARCHAR2(100 BYTE) NOT NULL,
                            orders_oid           VARCHAR2(40 BYTE) NOT NULL,
                            oicount              NUMBER NOT NULL,
                            oitotalprice         NUMBER(8) NOT NULL
);

ALTER TABLE order_item ADD CONSTRAINT order_item_pk PRIMARY KEY ( funding_product_fpid,
                                                                  orders_oid );

CREATE TABLE orders (
                        oid                   VARCHAR2(40 BYTE) NOT NULL,
                        oconsumer             VARCHAR2(10 BYTE) NOT NULL,
                        ophone                VARCHAR2(11 BYTE) NOT NULL,
                        omemo                 VARCHAR2(60 BYTE),
                        oused_mileage         NUMBER,
                        oorigin_price         NUMBER(8) NOT NULL,
                        oafter_price          NUMBER(8) NOT NULL,
                        ostatus               VARCHAR2(15 BYTE) NOT NULL,
                        odate                 DATE,
                        member_memail         VARCHAR2(60 BYTE) NOT NULL,
                        payment_method_pmcode VARCHAR2(30 BYTE) NOT NULL,
                        qrused_date           DATE
);

COMMENT ON COLUMN orders.ostatus IS
    'COMPLETE    결제 완료
WAITING               결제 대기
REFUND      환불';

ALTER TABLE orders ADD CONSTRAINT orders_pk PRIMARY KEY ( oid );

CREATE TABLE payment_method (
                                pmcode    VARCHAR2(30 BYTE) NOT NULL,
                                pmcompany VARCHAR2(30 BYTE),
                                pmmethod  NUMBER(1)
);

ALTER TABLE payment_method ADD CONSTRAINT payment_method_pk PRIMARY KEY ( pmcode );


--  ERROR: UK name length exceeds maximum allowed length(30)
ALTER TABLE payment_method ADD CONSTRAINT payment_method_pmmethod_pmcompany_un UNIQUE ( pmmethod,
                                                                                        pmcompany );

CREATE TABLE reply (
                       rscore        NUMBER NOT NULL,
                       rtype         VARCHAR2(20) NOT NULL,
                       rimg_url      VARCHAR2(100 BYTE),
                       member_memail VARCHAR2(60 BYTE) NOT NULL,
                       funding_fid   VARCHAR2(100 BYTE) NOT NULL,
                       rdate         DATE NOT NULL
);

COMMENT ON COLUMN reply.rtype IS
    'FUNDING
STORE';

ALTER TABLE reply
    ADD CONSTRAINT reply_pk PRIMARY KEY ( rtype,
                                          member_memail,
                                          funding_fid );

CREATE TABLE the_hyundai (
                             tid   VARCHAR2(100 BYTE) NOT NULL,
                             tname VARCHAR2(30) NOT NULL
);

ALTER TABLE the_hyundai ADD CONSTRAINT the_hyundai_pk PRIMARY KEY ( tid );

ALTER TABLE funding
    ADD CONSTRAINT funding_category_fk FOREIGN KEY ( category_ctid )
        REFERENCES category ( ctid );

ALTER TABLE funding
    ADD CONSTRAINT funding_competiton_fk FOREIGN KEY ( competiton_cid )
        REFERENCES competiton ( cid );

ALTER TABLE funding
    ADD CONSTRAINT funding_member_fk FOREIGN KEY ( member_memail )
        REFERENCES member ( memail );

ALTER TABLE funding_product
    ADD CONSTRAINT funding_product_funding_fk FOREIGN KEY ( funding_fid )
        REFERENCES funding ( fid );

ALTER TABLE funding
    ADD CONSTRAINT funding_the_hyundai_fk FOREIGN KEY ( the_hyundai_tid )
        REFERENCES the_hyundai ( tid );

ALTER TABLE order_item
    ADD CONSTRAINT order_item_funding_product_fk FOREIGN KEY ( funding_product_fpid )
        REFERENCES funding_product ( fpid );

ALTER TABLE order_item
    ADD CONSTRAINT order_item_orders_fk FOREIGN KEY ( orders_oid )
        REFERENCES orders ( oid );

ALTER TABLE orders
    ADD CONSTRAINT orders_member_fk FOREIGN KEY ( member_memail )
        REFERENCES member ( memail );

ALTER TABLE orders
    ADD CONSTRAINT orders_payment_method_fk FOREIGN KEY ( payment_method_pmcode )
        REFERENCES payment_method ( pmcode );

ALTER TABLE reply
    ADD CONSTRAINT reply_funding_fk FOREIGN KEY ( funding_fid )
        REFERENCES funding ( fid );

ALTER TABLE reply
    ADD CONSTRAINT reply_member_fk FOREIGN KEY ( member_memail )
        REFERENCES member ( memail );



-- Oracle SQL Developer Data Modeler 요약 보고서:
--
-- CREATE TABLE                            11
-- CREATE INDEX                             0
-- ALTER TABLE                             24
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
-- ERRORS                                   2
-- WARNINGS                                 0