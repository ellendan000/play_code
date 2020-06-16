create table `environment_histories`(
    id varchar(80) primary key,
    customer_id bigint not null,
    project_id bigint not null,
    pipeline_history_id varchar(36) default null,
    env_name varchar(30) not null,
    sequence varchar(20) not null,
    status varchar(10),
    triggered_time datetime,
    end_time datetime,
    origin_pipeline_name varchar(50) not null,
    origin_pipeline_history_sequence bigint not null
)