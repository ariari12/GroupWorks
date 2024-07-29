create table workflow
(
    approval_count            int           not null,
    approver_count            int           not null,
    status                    int           not null,
    work_flow_type            int           not null,
    approval_date             datetime(6)   null,
    cost                      bigint        not null,
    department_id             bigint        not null,
    draft_date                datetime(6)   null,
    employee_id               bigint        not null,
    final_employee_id         bigint        not null,
    workflow_id               bigint auto_increment
        primary key,
    description               varchar(2000) null,
    code                      varchar(255)  null,
    department                varchar(255)  null,
    email                     varchar(255)  null,
    employee_name             varchar(255)  null,
    employee_rank             varchar(255)  null,
    final_approval_department varchar(255)  null,
    final_approval_name       varchar(255)  null,
    final_approval_rank       varchar(255)  null,
    phone                     varchar(255)  null,
    title                     varchar(255)  null
);

INSERT INTO ERP.workflow (approval_count, approver_count, status, work_flow_type, approval_date, cost, department_id, draft_date, employee_id, final_employee_id, workflow_id, description, code, department, email, employee_name, employee_rank, final_approval_department, final_approval_name, final_approval_rank, phone, title) VALUES (0, 3, 0, 1, null, 0, 2, '2024-07-25 11:57:00.000000', 1, 0, 1, 'test', '12 - 1 - 202407251157', '마케팅부서', 'employee1@example.com', '사원1', '직급2', '기술부서', '사원30', '직급1', '010-1111-1111', 'test');
INSERT INTO ERP.workflow (approval_count, approver_count, status, work_flow_type, approval_date, cost, department_id, draft_date, employee_id, final_employee_id, workflow_id, description, code, department, email, employee_name, employee_rank, final_approval_department, final_approval_name, final_approval_rank, phone, title) VALUES (0, 3, 0, 4, null, 0, 3, '2024-06-25 11:57:00.000000', 2, 0, 2, 'test', '23 - 4 - 202407251157', '영업부서', 'employee2@example.com', '사원2', '직급3', '기술부서', '사원30', '직급1', '010-1111-1112', 'test');
INSERT INTO ERP.workflow (approval_count, approver_count, status, work_flow_type, approval_date, cost, department_id, draft_date, employee_id, final_employee_id, workflow_id, description, code, department, email, employee_name, employee_rank, final_approval_department, final_approval_name, final_approval_rank, phone, title) VALUES (0, 3, 1, 2, null, 0, 3, '2024-07-25 11:58:00.000000', 2, 0, 3, 'test', '23 - 0 - 202407251158', '영업부서', 'employee2@example.com', '사원2', '직급3', '기술부서', '사원30', '직급1', '010-1111-1112', 'test01');
INSERT INTO ERP.workflow (approval_count, approver_count, status, work_flow_type, approval_date, cost, department_id, draft_date, employee_id, final_employee_id, workflow_id, description, code, department, email, employee_name, employee_rank, final_approval_department, final_approval_name, final_approval_rank, phone, title) VALUES (0, 3, 0, 5, null, 0, 3, '2024-02-25 11:58:00.000000', 2, 0, 4, 'test', '23 - 5 - 202407251158', '영업부서', 'employee2@example.com', '사원2', '직급3', '기술부서', '사원30', '직급1', '010-1111-1112', 'test03');
INSERT INTO ERP.workflow (approval_count, approver_count, status, work_flow_type, approval_date, cost, department_id, draft_date, employee_id, final_employee_id, workflow_id, description, code, department, email, employee_name, employee_rank, final_approval_department, final_approval_name, final_approval_rank, phone, title) VALUES (0, 3, 3, 5, null, 0, 3, '2024-05-25 11:58:00.000000', 2, 0, 5, 'test', '23 - 5 - 202407251158', '영업부서', 'employee2@example.com', '사원2', '직급3', '기술부서', '사원30', '직급1', '010-1111-1112', 'test04');
INSERT INTO ERP.workflow (approval_count, approver_count, status, work_flow_type, approval_date, cost, department_id, draft_date, employee_id, final_employee_id, workflow_id, description, code, department, email, employee_name, employee_rank, final_approval_department, final_approval_name, final_approval_rank, phone, title) VALUES (0, 3, 2, 3, null, 0, 4, '2024-07-25 11:59:00.000000', 3, 0, 6, 'test', '34 - 3 - 202407251159', '인사부서', 'employee3@example.com', '사원3', '직급4', '기술부서', '사원30', '직급1', '010-1111-1113', 'test');
INSERT INTO ERP.workflow (approval_count, approver_count, status, work_flow_type, approval_date, cost, department_id, draft_date, employee_id, final_employee_id, workflow_id, description, code, department, email, employee_name, employee_rank, final_approval_department, final_approval_name, final_approval_rank, phone, title) VALUES (0, 3, 2, 4, null, 0, 4, '2024-07-25 11:59:00.000000', 3, 0, 7, 'test', '34 - 4 - 202407251159', '인사부서', 'employee3@example.com', '사원3', '직급4', '기술부서', '사원30', '직급1', '010-1111-1113', 'test');
INSERT INTO ERP.workflow (approval_count, approver_count, status, work_flow_type, approval_date, cost, department_id, draft_date, employee_id, final_employee_id, workflow_id, description, code, department, email, employee_name, employee_rank, final_approval_department, final_approval_name, final_approval_rank, phone, title) VALUES (0, 3, 0, 5, null, 0, 4, '2024-07-25 12:00:00.000000', 3, 0, 8, 'test', '34 - 5 - 202407251200', '인사부서', 'employee3@example.com', '사원3', '직급4', '기술부서', '사원30', '직급1', '010-1111-1113', 'test04');
INSERT INTO ERP.workflow (approval_count, approver_count, status, work_flow_type, approval_date, cost, department_id, draft_date, employee_id, final_employee_id, workflow_id, description, code, department, email, employee_name, employee_rank, final_approval_department, final_approval_name, final_approval_rank, phone, title) VALUES (0, 3, 0, 1, null, 0, 2, '2024-07-25 14:59:00.000000', 1, 0, 9, 'test', '12 - 1 - 202407251459', '마케팅부서', 'employee1@example.com', '사원1', '직급2', '기술부서', '사원30', '직급1', '010-1111-1111', 'test');
