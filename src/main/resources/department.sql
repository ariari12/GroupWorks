create table department
(
    department_id   bigint       not null
        primary key,
    contact_number  varchar(255) not null,
    department_name varchar(255) not null,
    location        varchar(255) not null
);

INSERT INTO ERP.department (department_id, contact_number, department_name, location) VALUES (1, '010-1234-5678', '기술부서', 'A동');
INSERT INTO ERP.department (department_id, contact_number, department_name, location) VALUES (2, '010-1234-5679', '마케팅부서', 'B동');
INSERT INTO ERP.department (department_id, contact_number, department_name, location) VALUES (3, '010-1234-5680', '영업부서', 'C동');
INSERT INTO ERP.department (department_id, contact_number, department_name, location) VALUES (4, '010-1234-5681', '인사부서', 'D동');
INSERT INTO ERP.department (department_id, contact_number, department_name, location) VALUES (5, '010-1234-5682', '재무부서', 'E동');
INSERT INTO ERP.department (department_id, contact_number, department_name, location) VALUES (6, '010-1234-5683', 'IT 지원부서', 'F동');
INSERT INTO ERP.department (department_id, contact_number, department_name, location) VALUES (7, '010-1234-5684', '제품 관리부서', 'G동');
INSERT INTO ERP.department (department_id, contact_number, department_name, location) VALUES (8, '010-1234-5685', '고객 서비스부서', 'H동');
INSERT INTO ERP.department (department_id, contact_number, department_name, location) VALUES (9, '010-1234-5686', '법무부서', 'I동');
INSERT INTO ERP.department (department_id, contact_number, department_name, location) VALUES (10, '010-1234-5687', '연구개발부서', 'J동');
