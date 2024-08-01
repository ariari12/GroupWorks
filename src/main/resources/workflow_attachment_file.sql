create table attachment_file
(
    af_id       bigint auto_increment
        primary key,
    workflow_id bigint       null,
    file_name   varchar(255) null,
    save_path   varchar(255) null,
    constraint FKbk82ygihy21s5bj5iyv4k3r62
        foreign key (workflow_id) references workflow (workflow_id)
);

