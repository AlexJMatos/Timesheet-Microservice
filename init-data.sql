DO $$
DECLARE
	emp1 uuid := gen_random_uuid();
	emp2 uuid := gen_random_uuid();
	emp3 uuid := gen_random_uuid();
	emp4 uuid := gen_random_uuid();
	emp5 uuid := gen_random_uuid();
	emp6 uuid := gen_random_uuid();
	emp7 uuid := gen_random_uuid();
	prj1 uuid := gen_random_uuid();
	prj2 uuid := gen_random_uuid();
	prj3 uuid := gen_random_uuid();
	tms1 uuid := gen_random_uuid();
	tms2 uuid := gen_random_uuid();
	tms3 uuid := gen_random_uuid();
	tms4 uuid := gen_random_uuid();
	tms_prj1 uuid := gen_random_uuid();
	tms_prj2 uuid := gen_random_uuid();
	prj_hours1 int := (SELECT NEXTVAL('project_hours_sequence'));
	prj_hours2 int := (SELECT NEXTVAL('project_hours_sequence'));
BEGIN
    -- EMPLOYEES
    INSERT INTO employees(id, first_name, last_name, date_of_birth, job_email, phone, gender, hiring_date, employee_position, country, photo_url, active)
    VALUES (emp1, 'Alejandro', 'Matos', '2000-01-01', 'alejandro.matos@coolcompany.com', '514-415-1851', 'Male', '2022-03-16', 'Software Engineer I', 'Mexico', 'some_url', true);

    INSERT INTO employees(id, first_name, last_name, date_of_birth, job_email, phone, gender, hiring_date, employee_position, country, photo_url, active)
    VALUES (emp2, 'Giovanna', 'Borges', '2001-02-02', 'giovanna.borges@coolcompany.com', '998-887-9577', 'Female', '2022-03-16', 'Software Engineer I', 'Mexico', 'some_url', true);

    INSERT INTO employees(id, first_name, last_name, date_of_birth, job_email, phone, gender, hiring_date, employee_position, country, photo_url, active)
    VALUES (emp3, 'Axel', 'Cervera', '2002-03-03', 'axel.cervera@theksquaregroup.com', '998-887-9577', 'Male', '2021-03-16', 'Software Engineer I', 'Mexico', 'some_url', true);

    INSERT INTO employees(id, first_name, last_name, date_of_birth, job_email, phone, gender, hiring_date, employee_position, country, photo_url, active)
    VALUES (emp4, 'Shaid', 'Bojorquez', '2003-04-04', 'shaid.bojorquez@theksquaregroup.com', '212-122-1712', 'Male', '2022-03-16', 'Software Engineer I', 'Mexico', 'some_url', true);

    INSERT INTO employees(id, first_name, last_name, date_of_birth, job_email, phone, gender, hiring_date, employee_position, country, photo_url, active)
    VALUES (emp5, 'Emilio', 'Flores', '2004-05-05', 'emilio.flores@theksquaregroup.com', '722-212-0398', 'Male', '2021-01-01', 'Group Lead / Engineer V', 'Mexico', 'some_url', true);

    INSERT INTO employees(id, first_name, last_name, date_of_birth, job_email, phone, gender, hiring_date, employee_position, country, photo_url, active)
    VALUES (emp6, 'Jessica', 'Munoz', '2005-06-06', 'jessica.munoz@theksquaregroup.com', '222-248-7655', 'Female', '2018-02-02', 'PMO Group Manager', 'Mexico', 'some_url', true);

    INSERT INTO employees(id, first_name, last_name, date_of_birth, job_email, phone, gender, hiring_date, employee_position, country, photo_url, active)
    VALUES (emp7, 'Jose', 'Vecino', '2005-06-06', 'jose.vecino@theksquaregroup.com', '888-673-2311', 'Male', '2018-02-02', 'Department Head', 'Mexico', 'some_url', true);
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    -- ADDRESS
    INSERT INTO address(employee_id, line_1, line_2, city, state, zip_code, country)
    VALUES (emp1,'VIRREYES RESIDENCIAL', 'HAWAI NO. 418 S/N', 'Coahuila', 'Saltillo', '25230', 'Mexico');

    INSERT INTO address(employee_id, line_1, line_2, city, state, zip_code, country)
    VALUES (emp2,'AV. TULUM SUR MZA 4', 'NO. 260, SUPERMANZANA 7', 'Cancun', 'Quintana Roo', '77503', 'Mexico');

    INSERT INTO address(employee_id, line_1, line_2, city, state, zip_code, country)
    VALUES (emp3,'VICTORIA NO. 3332', 'VERACRUZ CENTRO', 'Veracruz', 'Veracruz', '91700', 'Mexico');

    INSERT INTO address(employee_id, line_1, line_2, city, state, zip_code, country)
    VALUES (emp4,'AV JUAREZ NO. 2312', '6TO PISO, LA PAZ', 'Puebla', 'Puebla', '72160', 'Mexico');

    INSERT INTO address(employee_id, line_1, line_2, city, state, zip_code, country)
    VALUES (emp5,'CALLE 28 SUR NO. 545', 'V CARRANZA', 'Venustiano Carranza', 'Puebla', '71780', 'Mexico');

    INSERT INTO address(employee_id, line_1, line_2, city, state, zip_code, country)
    VALUES (emp6,'BENITO JUAREZ NO. 30', 'SANTA ANA TALLPATITLAN', 'Toluca', 'Estado De Mexico', '50160', 'Mexico');

    INSERT INTO address(employee_id, line_1, line_2, city, state, zip_code, country)
    VALUES (emp7,'Puerta 207', 'Pasaje Eje 5 66', 'Monclova', 'Aguascalientes', '15486', 'Mexico');

    -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    -- PROJECTS
    INSERT INTO projects(id, name, start_date, end_date, active, project_owner, cost_usd, project_manager_id)
    VALUES (prj1, 'Krista Automation', '2022-05-01', '2022-12-31', false, 'Kris Kraus', 250000, emp6);

    INSERT INTO projects(id, name, start_date, end_date, active, project_owner, cost_usd, project_manager_id)
    VALUES (prj2, 'Boys Scouts of America', '2015-01-01', null, true, 'Random Owner', 500000, emp7);

    INSERT INTO projects(id, name, start_date, end_date, active, project_owner, cost_usd, project_manager_id)
    VALUES (prj3, 'Chubb Insurance', '2022-01-01', '2022-12-31', false, 'Random Owner', 100000, emp5);
    ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    -- TIMESHEET
    INSERT INTO timesheets(id, start_date, end_date, week_hours, requester_id)
    VALUES (tms1, '2023-03-20', '2023-03-26', 40, emp1);

    INSERT INTO timesheets(id, start_date, end_date, week_hours, requester_id)
    VALUES (tms2, '2023-03-27', '2023-04-02', 40, emp1);

    INSERT INTO timesheets(id, start_date, end_date, week_hours, requester_id)
    VALUES (tms3, '2023-03-27', '2023-04-02', 40, emp2);

    INSERT INTO timesheets(id, start_date, end_date, week_hours, requester_id)
    VALUES (tms4, '2023-03-27', '2023-04-02', 40, emp3);

    -- TIMESHEET PROJECTS
    INSERT INTO timesheet_projects(id, timesheet_id, project_id, approves_id, timesheet_project_status, project_hours, requester_comment)
    VALUES (tms_prj1, tms1, prj1, emp6, 0, 40, 'This week I worked on new krista extension outlook v2');

    INSERT INTO timesheet_projects(id, timesheet_id, project_id, approves_id, timesheet_project_status, project_hours, requester_comment)
    VALUES (tms_prj2, tms2, prj1, emp6, 3, 40, 'This week I worked on new krista extension blue prism');

    -- PROJECT HOURS
    INSERT INTO project_hours(id, monday, tuesday, wednesday, thursday, friday, saturday, sunday)
    VALUES (prj_hours1, 8, 8, 8, 8, 8, 0, 0);

    INSERT INTO project_hours(id, monday, tuesday, wednesday, thursday, friday, saturday, sunday)
    VALUES (prj_hours2, 8, 8, 8, 8, 8, 0, 0);

    INSERT INTO timesheet_project_hours(timesheet_project_id, project_hours_id) VALUES (tms_prj1, prj_hours1);
    INSERT INTO timesheet_project_hours(timesheet_project_id, project_hours_id) VALUES (tms_prj2, prj_hours2);
END $$;