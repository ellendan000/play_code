create table `projects`(
    id bigint primary key auto_increment,
    name varchar(50) not null,
    customer_id bigint
)