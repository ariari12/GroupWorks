create table workflow_approver
(
    approval        int          not null,
    approval_method int          not null,
    approver_type   int          not null,
    sequence_num    int          not null,
    approval_date   datetime(6)  null,
    approver_id     bigint auto_increment
        primary key,
    employee_id     bigint       not null,
    workflow_id     bigint       null,
    approver_email  varchar(255) null,
    approver_name   varchar(255) null,
    approver_phone  varchar(255) null,
    approver_rank   varchar(255) null,
    comment         varchar(255) null,
    department      varchar(255) null,
    constraint FKiggqtpcao6lb9qjnnhhu3eop9
        foreign key (workflow_id) references workflow (workflow_id)
);

INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 1, null, 1, 10, 1, 'employee10@example.com', '사원10', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 2, null, 2, 20, 1, 'employee20@example.com', '사원20', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 3, null, 3, 30, 1, 'employee30@example.com', '사원30', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 1, null, 4, 10, 2, 'employee10@example.com', '사원10', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 2, null, 5, 20, 2, 'employee20@example.com', '사원20', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 3, null, 6, 30, 2, 'employee30@example.com', '사원30', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 1, null, 7, 10, 3, 'employee10@example.com', '사원10', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 2, null, 8, 20, 3, 'employee20@example.com', '사원20', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 3, null, 9, 30, 3, 'employee30@example.com', '사원30', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 1, null, 10, 10, 4, 'employee10@example.com', '사원10', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 2, null, 11, 20, 4, 'employee20@example.com', '사원20', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 3, null, 12, 30, 4, 'employee30@example.com', '사원30', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 1, null, 13, 10, 5, 'employee10@example.com', '사원10', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 2, null, 14, 20, 5, 'employee20@example.com', '사원20', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 3, null, 15, 30, 5, 'employee30@example.com', '사원30', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 1, null, 16, 10, 6, 'employee10@example.com', '사원10', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 2, null, 17, 20, 6, 'employee20@example.com', '사원20', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 3, null, 18, 30, 6, 'employee30@example.com', '사원30', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 1, null, 19, 10, 7, 'employee10@example.com', '사원10', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 2, null, 20, 20, 7, 'employee20@example.com', '사원20', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 3, null, 21, 30, 7, 'employee30@example.com', '사원30', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 2, 1, null, 22, 11, 7, 'employee11@example.com', '사원11', '010-1111-1111', '직급2', null, '마케팅부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 2, 2, null, 23, 21, 7, 'employee21@example.com', '사원21', '010-1111-1111', '직급2', null, '마케팅부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 3, 1, null, 24, 31, 7, 'employee31@example.com', '사원31', '010-1111-1111', '직급2', null, '마케팅부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 1, null, 25, 10, 8, 'employee10@example.com', '사원10', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 2, null, 26, 20, 8, 'employee20@example.com', '사원20', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 3, null, 27, 30, 8, 'employee30@example.com', '사원30', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 2, 1, null, 28, 11, 8, 'employee11@example.com', '사원11', '010-1111-1111', '직급2', null, '마케팅부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 2, 2, null, 29, 12, 8, 'employee12@example.com', '사원12', '010-1111-1112', '직급3', null, '영업부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 3, 1, null, 30, 13, 8, 'employee13@example.com', '사원13', '010-1111-1113', '직급4', null, '인사부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 3, 2, null, 31, 14, 8, 'employee14@example.com', '사원14', '010-1111-1114', '직급5', null, '재무부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 1, null, 32, 10, 9, 'employee10@example.com', '사원10', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 2, null, 33, 20, 9, 'employee20@example.com', '사원20', '010-1111-1110', '직급1', null, '기술부서');
INSERT INTO ERP.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department) VALUES (0, 0, 1, 3, null, 34, 30, 9, 'employee30@example.com', '사원30', '010-1111-1110', '직급1', null, '기술부서');
