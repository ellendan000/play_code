insert into pipeline_histories(id, pipeline_name, sequence, customer_id, project_id)
VALUES ('c26dfd63-4a4e-4d82-bf6c-855aa2ac99f3', 'account-management', 211, 1, 1),
       ('bb69a826-0395-4712-a05f-7dc6abfb1141', 'lead-management', 368, 1, 1),
       ('0b67135a-af7d-45a6-82aa-8c5a43f49270', 'lead-management', 369, 1, 2),
       ('32e24ad8-b5ed-42df-8b2f-4262d9900ff0', 'lead-management', 370, 2, 1),
       ('a4f5d1fc-7dfe-419e-95e5-7d4358363a4e', 'lead-management', 371, 2, 2);

INSERT INTO environment_histories (id, customer_id, project_id, pipeline_history_id, env_name, sequence, status,
                                   triggered_time, end_time, origin_pipeline_name, origin_pipeline_history_sequence)
VALUES ('1-1-account-management-plus-master-211-ci', 1, 1, 'c26dfd63-4a4e-4d82-bf6c-855aa2ac99f3', 'ci', '1-1-1',
        'PASSED', '2019-09-20 02:59:47', '2019-09-20 03:16:57', 'account-management-plus-master', 211),
       ('1-1-lead-management-plus-master-368-ci', 1, 1, 'bb69a826-0395-4712-a05f-7dc6abfb1141', 'ci', '1-1-1', 'PASSED',
        '2019-09-29 02:59:47', '2019-09-29 03:16:57', 'lead-management-plus-master', 368),
       ('1-1-lead-management-plus-master-369-ci', 1, 2, '0b67135a-af7d-45a6-82aa-8c5a43f49270', 'ci', '1-1-1', 'PASSED',
        '2019-09-27 08:38:44', '2019-09-27 08:57:31', 'lead-management-plus-master', 369),
       ('1-1-lead-management-plus-master-370-ci', 2, 1, '32e24ad8-b5ed-42df-8b2f-4262d9900ff0', 'ci', '1-1-1', 'PASSED',
        '2019-09-29 08:53:45', '2019-09-29 09:14:54', 'lead-management-plus-master', 370),
       ('1-1-lead-management-plus-master-371-ci', 2, 2, 'a4f5d1fc-7dfe-419e-95e5-7d4358363a4e', 'ci', '2-1-1', 'PASSED',
        '2019-09-30 02:09:59', '2019-09-30 02:25:11', 'lead-management-plus-master', 371);




