-- Generado por Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   en:        2023-02-14 08:42:41 COT
--   sitio:      Oracle Database 21c
--   tipo:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE CONDITIONS (
    idCondition   INTEGER NOT NULL,
    conditionDesc VARCHAR2(50) NOT NULL,
    createdAt     DATE
);

ALTER TABLE CONDITIONS ADD CONSTRAINT CONDITIONS_PK PRIMARY KEY ( idCondition );

CREATE TABLE DISTRICTS (
    idDistrict   INTEGER NOT NULL,
    districtDesc VARCHAR2(50) NOT NULL,
    createdAt    DATE,
    idProvince   INTEGER NOT NULL
);

ALTER TABLE DISTRICTS ADD CONSTRAINT DISTRICTS_PK PRIMARY KEY ( idDistrict );

CREATE TABLE HOSPITALS (
    idHospital   INTEGER NOT NULL,
    hospitalName VARCHAR2(50) NOT NULL,
    hospitalAge  INTEGER,
    hospitalArea NUMBER(5, 2),
    createdAt    DATE,
    idManager    INTEGER NOT NULL,
    idCondition  INTEGER NOT NULL,
    idDistrict   INTEGER NOT NULL,
    idLocation   INTEGER NOT NULL
);

CREATE UNIQUE INDEX HOSPITALS__IDX ON
    HOSPITALS (
        idManager
    ASC );

CREATE UNIQUE INDEX HOSPITALS__IDXv1 ON
    HOSPITALS (
        idLocation
    ASC );

ALTER TABLE HOSPITALS ADD CONSTRAINT HOSPITALS_PK PRIMARY KEY ( idHospital );

CREATE TABLE LOCATIONS (
    idLocation   INTEGER NOT NULL,
    locationDesc VARCHAR2(100) NOT NULL,
    createdAt    DATE
);

ALTER TABLE LOCATIONS ADD CONSTRAINT LOCATIONS_PK PRIMARY KEY ( idLocation );

CREATE TABLE MANAGERS (
    idManager   INTEGER NOT NULL,
    managerDesc VARCHAR2(50) NOT NULL,
    createdAt   DATE
);

ALTER TABLE MANAGERS ADD CONSTRAINT MANAGERS_PK PRIMARY KEY ( idManager );

CREATE TABLE PROVINCES (
    idProvince   INTEGER NOT NULL,
    provinceDesc VARCHAR2(50) NOT NULL,
    createdAt    DATE
);

ALTER TABLE PROVINCES ADD CONSTRAINT PROVINCES_PK PRIMARY KEY ( idProvince );

CREATE TABLE USERS (
    idUser    INTEGER NOT NULL,
    userName  VARCHAR2(50) NOT NULL,
    firstName VARCHAR2(25),
    lastName  VARCHAR2(25),
    email     VARCHAR2(50) NOT NULL,
    password  VARCHAR2(200) NOT NULL,
    createdAt DATE
);

ALTER TABLE USERS ADD CONSTRAINT USERS_PK PRIMARY KEY ( idUser );

ALTER TABLE DISTRICTS
    ADD CONSTRAINT DISTRICTS_PROVINCES_FK FOREIGN KEY ( idProvince )
        REFERENCES PROVINCES ( idProvince )
            ON DELETE CASCADE;

ALTER TABLE HOSPITALS
    ADD CONSTRAINT HOSPITALS_CONDITIONS_FK FOREIGN KEY ( idCondition )
        REFERENCES CONDITIONS ( idCondition )
            ON DELETE CASCADE;

ALTER TABLE HOSPITALS
    ADD CONSTRAINT HOSPITALS_DISTRICTS_FK FOREIGN KEY ( idDistrict )
        REFERENCES DISTRICTS ( idDistrict )
            ON DELETE CASCADE;

ALTER TABLE HOSPITALS
    ADD CONSTRAINT HOSPITALS_LOCATIONS_FK FOREIGN KEY ( idLocation )
        REFERENCES LOCATIONS ( idLocation )
            ON DELETE CASCADE;

ALTER TABLE HOSPITALS
    ADD CONSTRAINT HOSPITALS_MANAGERS_FK FOREIGN KEY ( idManager )
        REFERENCES MANAGERS ( idManager )
            ON DELETE CASCADE;



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             7
-- CREATE INDEX                             2
-- ALTER TABLE                             12
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
