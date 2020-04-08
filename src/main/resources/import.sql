insert into helloworldmessage (message_text) values ('Hello World!');
insert into helloworldmessage (message_text) values ('Nice to Meet You...');
insert into helloworldmessage (message_text) values ('Welcome to WFDSS NextGen!');
--employees
insert into employee(employee_id, employee_name, employee_age, employee_salary, profile_image) values (1, 'Alice Baxter', 46, 97000, 'path/to/image' );
insert into employee(employee_id, employee_name, employee_age, employee_salary, profile_image) values (2, 'Bob Donahue', 46, 98000, 'path/to/image' );
insert into employee(employee_id, employee_name, employee_age, employee_salary, profile_image) values (3, 'Carson Fox', 46, 99000, 'path/to/image' );
insert into employee(employee_id, employee_name, employee_age, employee_salary, profile_image) values (4, 'David Castro', 46, 96000, 'path/to/image' );
insert into employee(employee_id, employee_name, employee_age, employee_salary, profile_image) values (5, 'Erick Steiner', 46, 95000, 'path/to/image' );
insert into employee(employee_id, employee_name, employee_age, employee_salary, profile_image) values (6, 'Fred Quick', 46, 94000, 'path/to/image' );
insert into employee(employee_id, employee_name, employee_age, employee_salary, profile_image) values (7, 'Greg Hanan', 46, 93000, 'path/to/image' );
insert into employee(employee_id, employee_name, employee_age, employee_salary, profile_image) values (8, 'Henry Gupta', 46, 92000, 'path/to/image' );
insert into employee(employee_id, employee_name, employee_age, employee_salary, profile_image) values (9, 'Ignacio Greer', 46, 91000, 'path/to/image' );
insert into employee(employee_id, employee_name, employee_age, employee_salary, profile_image) values (10, 'James Graves', 46, 90000, 'path/to/image' );
--projects
insert into project(project_id, project_name, project_manager_employee_id) values (1, 'WFDSS', 1);
insert into project(project_id, project_name, project_manager_employee_id) values (2, 'IRWIN', 2);
--project_team_members
insert into project_team_members(project_project_id, team_members_employee_id) values (1, 3);
insert into project_team_members(project_project_id, team_members_employee_id) values (1, 4);
insert into project_team_members(project_project_id, team_members_employee_id) values (1, 5);
insert into project_team_members(project_project_id, team_members_employee_id) values (1, 6);
insert into project_team_members(project_project_id, team_members_employee_id) values (2, 7);
insert into project_team_members(project_project_id, team_members_employee_id) values (2, 8);
insert into project_team_members(project_project_id, team_members_employee_id) values (2, 9);
insert into project_team_members(project_project_id, team_members_employee_id) values (2, 10);
--timelines
insert into timeline(start_date, mvp1, end_date, parent_project_project_id) values (TIMESTAMP '2020-03-10 15:24:41.506', TIMESTAMP '2021-03-10 15:24:41.506', TIMESTAMP '2022-03-10 15:24:41.506', 1);
insert into timeline(start_date, mvp1, end_date, parent_project_project_id) values (TIMESTAMP '2016-03-10 15:24:41.506', TIMESTAMP '2017-03-10 15:24:41.506', TIMESTAMP '2025-03-10 15:24:41.506', 2);
--stories
insert into story(story_id, story_name, project_id) values (1, 'Build Widget A', 1);
insert into story(story_id, story_name, project_id) values (2, 'Build Widget B', 1);
insert into story(story_id, story_name, project_id) values (3, 'Build UI Component', 1);
insert into story(story_id, story_name, project_id) values (4, 'Build Gadget A', 2);
insert into story(story_id, story_name, project_id) values (5, 'Build Gadget B', 2);
insert into story(story_id, story_name, project_id) values (6, 'Do Market Research', 2);