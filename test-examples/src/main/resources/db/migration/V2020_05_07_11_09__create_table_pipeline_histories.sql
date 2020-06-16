create table `pipeline_histories`(
    id varchar(36) primary key,
    pipeline_name varchar(50) not null,
    sequence bigint not null,
    customer_id bigint not null,
    project_id bigint not null
)