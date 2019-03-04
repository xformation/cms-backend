--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1
-- Dumped by pg_dump version 11.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: academic_year; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.academic_year (
    id bigint NOT NULL,
    jhi_year character varying(255) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL
);


ALTER TABLE public.academic_year OWNER TO postgres;

--
-- Name: attendance_master; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attendance_master (
    id bigint NOT NULL,
    jhi_desc character varying(255),
    batch_id bigint,
    section_id bigint,
    teach_id bigint
);


ALTER TABLE public.attendance_master OWNER TO postgres;

--
-- Name: authorized_signatory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authorized_signatory (
    id bigint NOT NULL,
    signatory_name character varying(255) NOT NULL,
    signatory_father_name character varying(255) NOT NULL,
    signatory_designation character varying(255) NOT NULL,
    address_1 character varying(255) NOT NULL,
    address_2 character varying(255) NOT NULL,
    address_3 character varying(255) NOT NULL,
    address_4 character varying(255) NOT NULL,
    address_5 character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    pan_card_number character varying(255) NOT NULL,
    branch_id bigint,
    college_id bigint
);


ALTER TABLE public.authorized_signatory OWNER TO postgres;

--
-- Name: bank_accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bank_accounts (
    id bigint NOT NULL,
    name_of_bank character varying(255) NOT NULL,
    account_number bigint NOT NULL,
    type_of_account character varying(255) NOT NULL,
    ifsc_code character varying(255) NOT NULL,
    branch_address character varying(255) NOT NULL,
    corporate_id integer NOT NULL,
    branch_id bigint,
    college_id bigint
);


ALTER TABLE public.bank_accounts OWNER TO postgres;

--
-- Name: batch; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.batch (
    id bigint NOT NULL,
    batch character varying(255) NOT NULL,
    department_id bigint
);


ALTER TABLE public.batch OWNER TO postgres;

--
-- Name: branch; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.branch (
    id bigint NOT NULL,
    branch_name character varying(255) NOT NULL,
    address_1 character varying(255) NOT NULL,
    address_2 character varying(255) NOT NULL,
    branch_head character varying(255) NOT NULL,
    college_id bigint,
    city_id bigint,
    state_id bigint
);


ALTER TABLE public.branch OWNER TO postgres;

--
-- Name: city; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.city (
    id bigint NOT NULL,
    city_name character varying(255) NOT NULL,
    city_code character varying(255),
    std_code character varying(255),
    state_id bigint
);


ALTER TABLE public.city OWNER TO postgres;

--
-- Name: college; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.college (
    id bigint NOT NULL,
    short_name character varying(255) NOT NULL,
    logo bigint NOT NULL,
    background_image bigint NOT NULL,
    instruction_information character varying(255) NOT NULL
);


ALTER TABLE public.college OWNER TO postgres;

--
-- Name: country; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.country (
    id bigint NOT NULL,
    country_name character varying(255) NOT NULL,
    country_code character varying(255) NOT NULL,
    isd_code character varying(255)
);


ALTER TABLE public.country OWNER TO postgres;

--
-- Name: currency; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.currency (
    id bigint NOT NULL,
    currency_name character varying(255) NOT NULL,
    currency_code character varying(255),
    currency_symbol character varying(255),
    country_id bigint
);


ALTER TABLE public.currency OWNER TO postgres;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO postgres;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO postgres;

--
-- Name: department; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    dept_head character varying(255) NOT NULL,
    branch_id bigint,
    academicyear_id bigint
);


ALTER TABLE public.department OWNER TO postgres;

--
-- Name: due_date; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.due_date (
    id bigint NOT NULL,
    payment_method character varying(255) NOT NULL,
    installments integer NOT NULL,
    day_desc character varying(255) NOT NULL,
    frequency character varying(255) NOT NULL,
    college_id bigint,
    branch_id bigint
);


ALTER TABLE public.due_date OWNER TO postgres;

--
-- Name: facility; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.facility (
    id bigint NOT NULL,
    facility_name character varying(255) NOT NULL
);


ALTER TABLE public.facility OWNER TO postgres;

--
-- Name: fee_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fee_category (
    id bigint NOT NULL,
    category_name character varying(255) NOT NULL,
    description character varying(255) NOT NULL
);


ALTER TABLE public.fee_category OWNER TO postgres;

--
-- Name: fee_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fee_details (
    id bigint NOT NULL,
    fee_particulars_name character varying(255) NOT NULL,
    fee_particular_desc character varying(255) NOT NULL,
    student_type character varying(255) NOT NULL,
    gender character varying(255) NOT NULL,
    amount bigint NOT NULL,
    fee_category_id bigint,
    batch_id bigint,
    facility_id bigint,
    transport_route_id bigint,
    college_id bigint,
    department_id bigint,
    branch_id bigint,
    academic_year_id bigint
);


ALTER TABLE public.fee_details OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1000
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: holiday; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.holiday (
    id bigint NOT NULL,
    holiday_desc character varying(255) NOT NULL,
    holiday_date date NOT NULL,
    holiday_status character varying(255) NOT NULL,
    academicyear_id bigint
);


ALTER TABLE public.holiday OWNER TO postgres;

--
-- Name: invoice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invoice (
    id bigint NOT NULL,
    invoice_number character varying(255) NOT NULL,
    amount_paid bigint NOT NULL,
    payment_date date NOT NULL,
    next_payment_date date NOT NULL,
    out_standing_amount bigint NOT NULL,
    mode_of_payment character varying(255) NOT NULL,
    cheque_number bigint,
    demand_draft_number bigint,
    online_txn_ref_number character varying(255),
    payment_status character varying(255) NOT NULL,
    comments character varying(255) NOT NULL,
    updated_by character varying(255) NOT NULL,
    updated_on date NOT NULL,
    fee_category_id bigint,
    fee_details_id bigint,
    due_date_id bigint,
    payment_remainder_id bigint,
    college_id bigint,
    branch_id bigint,
    student_id bigint,
    academic_year_id bigint
);


ALTER TABLE public.invoice OWNER TO postgres;

--
-- Name: jhi_authority; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_authority (
    name character varying(50) NOT NULL
);


ALTER TABLE public.jhi_authority OWNER TO postgres;

--
-- Name: jhi_persistent_audit_event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_persistent_audit_event (
    event_id bigint NOT NULL,
    principal character varying(50) NOT NULL,
    event_date timestamp without time zone,
    event_type character varying(255)
);


ALTER TABLE public.jhi_persistent_audit_event OWNER TO postgres;

--
-- Name: jhi_persistent_audit_evt_data; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_persistent_audit_evt_data (
    event_id bigint NOT NULL,
    name character varying(150) NOT NULL,
    value character varying(255)
);


ALTER TABLE public.jhi_persistent_audit_evt_data OWNER TO postgres;

--
-- Name: jhi_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_user (
    id bigint NOT NULL,
    login character varying(50) NOT NULL,
    password_hash character varying(60) NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    email character varying(254),
    image_url character varying(256),
    activated boolean NOT NULL,
    lang_key character varying(6),
    activation_key character varying(20),
    reset_key character varying(20),
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    reset_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.jhi_user OWNER TO postgres;

--
-- Name: jhi_user_authority; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_user_authority (
    user_id bigint NOT NULL,
    authority_name character varying(50) NOT NULL
);


ALTER TABLE public.jhi_user_authority OWNER TO postgres;

--
-- Name: late_fee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.late_fee (
    id bigint NOT NULL,
    assign_late_fee character varying(255) NOT NULL,
    late_fee_days integer NOT NULL,
    fixed character varying(255),
    percentage character varying(255),
    fixed_charges bigint,
    percent_charges bigint,
    late_fee_assignment_frequency character varying(255) NOT NULL,
    college_id bigint,
    branch_id bigint
);


ALTER TABLE public.late_fee OWNER TO postgres;

--
-- Name: lecture; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lecture (
    id bigint NOT NULL,
    lec_date date NOT NULL,
    last_updated_on date NOT NULL,
    last_updated_by character varying(255) NOT NULL,
    start_time character varying(255) NOT NULL,
    end_time character varying(255) NOT NULL,
    attendancemaster_id bigint
);


ALTER TABLE public.lecture OWNER TO postgres;

--
-- Name: legal_entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.legal_entity (
    id bigint NOT NULL,
    logo bigint NOT NULL,
    legal_name_of_the_college character varying(255) NOT NULL,
    type_of_college character varying(255) NOT NULL,
    date_of_incorporation date NOT NULL,
    registered_office_address_1 character varying(255) NOT NULL,
    registered_office_address_2 character varying(255) NOT NULL,
    registered_office_address_3 character varying(255) NOT NULL,
    registered_office_address_4 character varying(255) NOT NULL,
    registered_office_address_5 character varying(255) NOT NULL,
    college_identification_number character varying(255) NOT NULL,
    pan character varying(255) NOT NULL,
    tan character varying(255) NOT NULL,
    tan_circle_number character varying(255) NOT NULL,
    cit_tds_location character varying(255) NOT NULL,
    form_signatory bigint NOT NULL,
    pf_number character varying(255) NOT NULL,
    pf_registration_date date NOT NULL,
    pf_signatory bigint NOT NULL,
    esi_number bigint NOT NULL,
    esi_registration_date date NOT NULL,
    esi_signatory bigint NOT NULL,
    pt_number bigint NOT NULL,
    pt_registration_date date NOT NULL,
    pt_signatory bigint NOT NULL,
    branch_id bigint,
    college_id bigint,
    state_id bigint,
    city_id bigint
);


ALTER TABLE public.legal_entity OWNER TO postgres;

--
-- Name: location; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.location (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    address character varying(255) NOT NULL,
    applies_to character varying(255) NOT NULL
);


ALTER TABLE public.location OWNER TO postgres;

--
-- Name: payment_remainder; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment_remainder (
    id bigint NOT NULL,
    fee_remainder character varying(255) NOT NULL,
    notice_day integer NOT NULL,
    over_due_remainder character varying(255) NOT NULL,
    remainder_recipients character varying(255) NOT NULL,
    due_date_id bigint,
    college_id bigint,
    branch_id bigint
);


ALTER TABLE public.payment_remainder OWNER TO postgres;

--
-- Name: section; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.section (
    id bigint NOT NULL,
    section character varying(255) NOT NULL,
    batch_id bigint
);


ALTER TABLE public.section OWNER TO postgres;

--
-- Name: state; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.state (
    id bigint NOT NULL,
    state_name character varying(255) NOT NULL,
    division_type character varying(255) NOT NULL,
    state_code character varying(255) NOT NULL,
    country_id bigint
);


ALTER TABLE public.state OWNER TO postgres;

--
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
    id bigint NOT NULL,
    student_name character varying(255) NOT NULL,
    student_middle_name character varying(255) NOT NULL,
    student_last_name character varying(255) NOT NULL,
    father_name character varying(255) NOT NULL,
    father_middle_name character varying(255) NOT NULL,
    father_last_name character varying(255) NOT NULL,
    mother_name character varying(255) NOT NULL,
    mother_middle_name character varying(255) NOT NULL,
    mother_last_name character varying(255) NOT NULL,
    aadhar_no bigint NOT NULL,
    date_of_birth date NOT NULL,
    place_of_birth character varying(255) NOT NULL,
    religion character varying(255) NOT NULL,
    caste character varying(255) NOT NULL,
    sub_caste character varying(255) NOT NULL,
    age integer NOT NULL,
    sex character varying(255) NOT NULL,
    blood_group character varying(255) NOT NULL,
    address_line_one character varying(255) NOT NULL,
    address_line_two character varying(255) NOT NULL,
    address_line_three character varying(255) NOT NULL,
    town character varying(255) NOT NULL,
    state character varying(255) NOT NULL,
    country character varying(255) NOT NULL,
    pincode bigint NOT NULL,
    student_contact_number bigint NOT NULL,
    alternate_contact_number bigint NOT NULL,
    student_email_address character varying(255) NOT NULL,
    alternate_email_address character varying(255) NOT NULL,
    relation_with_student character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    middle_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    contact_no bigint NOT NULL,
    email_address character varying(255) NOT NULL,
    transport character varying(255) NOT NULL,
    mess character varying(255) NOT NULL,
    gym character varying(255) NOT NULL,
    cultural_class character varying(255) NOT NULL,
    jhi_library character varying(255) NOT NULL,
    sports character varying(255) NOT NULL,
    swimming character varying(255) NOT NULL,
    extra_class character varying(255) NOT NULL,
    handicrafts character varying(255) NOT NULL,
    jhi_add character varying(255) NOT NULL,
    upload_photo bigint NOT NULL,
    admission_no bigint NOT NULL,
    roll_no character varying(255) NOT NULL,
    student_type character varying(255) NOT NULL,
    department_id bigint NOT NULL,
    batch_id bigint NOT NULL,
    section_id bigint NOT NULL,
    branch_id bigint NOT NULL
);


ALTER TABLE public.student OWNER TO postgres;

--
-- Name: student_attendance; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student_attendance (
    id bigint NOT NULL,
    attendance_status character varying(255) NOT NULL,
    comments character varying(255),
    student_id bigint,
    lecture_id bigint
);


ALTER TABLE public.student_attendance OWNER TO postgres;

--
-- Name: subject; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subject (
    id bigint NOT NULL,
    subject_code character varying(255) NOT NULL,
    subject_type character varying(255) NOT NULL,
    subject_desc character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    department_id bigint,
    batch_id bigint
);


ALTER TABLE public.subject OWNER TO postgres;

--
-- Name: teach; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.teach (
    id bigint NOT NULL,
    jhi_desc character varying(255),
    subject_id bigint,
    teacher_id bigint
);


ALTER TABLE public.teach OWNER TO postgres;

--
-- Name: teacher; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.teacher (
    id bigint NOT NULL,
    teacher_name character varying(255) NOT NULL,
    teacher_middle_name character varying(255) NOT NULL,
    teacher_last_name character varying(255) NOT NULL,
    father_name character varying(255) NOT NULL,
    father_middle_name character varying(255) NOT NULL,
    father_last_name character varying(255) NOT NULL,
    mother_name character varying(255) NOT NULL,
    mother_middle_name character varying(255) NOT NULL,
    mother_last_name character varying(255) NOT NULL,
    aadhar_no bigint NOT NULL,
    date_of_birth date NOT NULL,
    place_of_birth character varying(255) NOT NULL,
    religion character varying(255) NOT NULL,
    caste character varying(255) NOT NULL,
    sub_caste character varying(255) NOT NULL,
    age integer NOT NULL,
    sex character varying(255) NOT NULL,
    blood_group character varying(255) NOT NULL,
    address_line_one character varying(255) NOT NULL,
    address_line_two character varying(255) NOT NULL,
    address_line_three character varying(255) NOT NULL,
    town character varying(255) NOT NULL,
    state character varying(255) NOT NULL,
    country character varying(255) NOT NULL,
    pincode bigint NOT NULL,
    teacher_contact_number bigint NOT NULL,
    alternate_contact_number bigint NOT NULL,
    teacher_email_address character varying(255) NOT NULL,
    alternate_email_address character varying(255) NOT NULL,
    relation_with_staff character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    middle_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    contact_no bigint NOT NULL,
    email_address character varying(255) NOT NULL,
    upload_photo bigint NOT NULL,
    employee_id bigint NOT NULL,
    designation character varying(255) NOT NULL,
    staff_type character varying(255) NOT NULL,
    department_id bigint,
    branch_id bigint
);


ALTER TABLE public.teacher OWNER TO postgres;

--
-- Name: term; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.term (
    id bigint NOT NULL,
    terms_desc character varying(255) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    term_status character varying(255) NOT NULL,
    academicyear_id bigint
);


ALTER TABLE public.term OWNER TO postgres;

--
-- Name: transport_route; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transport_route (
    id bigint NOT NULL,
    route_name character varying(255) NOT NULL,
    route_details character varying(255) NOT NULL,
    route_map_url character varying(255) NOT NULL
);


ALTER TABLE public.transport_route OWNER TO postgres;

--
-- Data for Name: academic_year; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.academic_year (id, jhi_year, start_date, end_date) FROM stdin;
11100	2018	2018-01-01	2018-12-31
1051	2019	2019-01-01	2019-12-31
\.


--
-- Data for Name: attendance_master; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attendance_master (id, jhi_desc, batch_id, section_id, teach_id) FROM stdin;
1451	OS Attendance	1151	1201	1401
1452	SE Attendance	1151	1201	1402
1453	DB Attendance	1151	1201	1403
1454	UML Attendance	1151	1201	1404
1455	JAVA Attendance	1151	1201	1405
16350	OS Attendance	1151	12100	1401
16400	SE Attendance	1151	12100	1402
16450	DB Attendance	1151	12100	1403
16500	UML Attendance	1151	12100	1404
16550	JAVA Attendance	1151	12100	1405
16600	OS Attendance	1151	12150	1401
16650	SE Attendance	1151	12150	1402
16700	DB Attendance	1151	12150	1403
16750	UML Attendance	1151	12150	1404
16800	JAVA Attendance	1151	12150	1405
16850	OS Attendance	1151	12200	1401
16900	SE Attendance	1151	12200	1402
16950	DB Attendance	1151	12200	1403
17000	UML Attendance	1151	12200	1404
17050	JAVA Attendance	1151	12200	1405
17100	SAD Attendance	11350	12250	15800
17150	NET Attendance	11350	12250	15850
17200	OR Attendance	11350	12250	15900
17250	OOP Attendance	11350	12250	15950
17300	SAD Attendance	11350	12300	15800
17350	NET Attendance	11350	12300	15850
17400	OR Attendance	11350	12300	15900
17450	OOP Attendance	11350	12300	15950
17500	SAD Attendance	11350	12350	15800
17550	NET Attendance	11350	12350	15850
17600	OR Attendance	11350	12350	15900
17650	OOP Attendance	11350	12350	15950
17700	SAD Attendance	11350	12400	15800
17750	NET Attendance	11350	12400	15850
17800	OR Attendance	11350	12400	15900
17850	OOP Attendance	11350	12400	15950
17900	ELE Attendance	11400	12450	16000
17950	ASE Attendance	11400	12450	16050
18000	C Attendance	11400	12450	16100
18050	COBOL Attendance	11400	12450	16150
18100	ELE Attendance	11400	12500	16000
18150	ASE Attendance	11400	12500	16050
18200	C Attendance	11400	12500	16100
18250	COBOL Attendance	11400	12500	16150
18300	ELE Attendance	11400	12550	16000
18350	ASE Attendance	11400	12550	16050
18400	C Attendance	11400	12550	16100
18450	COBOL Attendance	11400	12550	16150
18500	ELE Attendance	11400	12600	16000
18550	ASE Attendance	11400	12600	16050
18600	C Attendance	11400	12600	16100
18650	COBOL Attendance	11400	12600	16150
18700	ME Attendance	11450	12650	16200
18750	SC Attendance	11450	12650	16250
18800	Internship Project	11450	12650	16300
18850	ME Attendance	11450	12700	16200
18900	SC Attendance	11450	12700	16250
18950	Internship Project	11450	12700	16300
19000	ME Attendance	11450	12750	16200
19050	SC Attendance	11450	12750	16250
19100	Internship Project	11450	12750	16300
19150	ME Attendance	11450	12800	16200
19200	SC Attendance	11450	12800	16250
19250	Internship Project	11450	12800	16300
\.


--
-- Data for Name: authorized_signatory; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.authorized_signatory (id, signatory_name, signatory_father_name, signatory_designation, address_1, address_2, address_3, address_4, address_5, email, pan_card_number, branch_id, college_id) FROM stdin;
1651	Jack	FJack	DIG	address_1	address_2	address_3	address_4	address_5	jack@gmail.com	AVGPT4072M	1001	951
\.


--
-- Data for Name: bank_accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bank_accounts (id, name_of_bank, account_number, type_of_account, ifsc_code, branch_address, corporate_id, branch_id, college_id) FROM stdin;
1751	HDFC	7338093889389	Savings	AD09832	madhapur	123	1001	951
\.


--
-- Data for Name: batch; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batch (id, batch, department_id) FROM stdin;
1151	FIRSTYEAR	1101
11350	SECONDYEAR	1101
11400	THIRDYEAR	1101
11450	FOURTHYEAR	1101
11500	FIRSTYEAR	11200
11550	SECONDYEAR	11200
11600	THIRDYEAR	11200
11650	FOURTHYEAR	11200
11700	FIRSTYEAR	11250
11750	SECONDYEAR	11250
11800	THIRDYEAR	11250
11850	FOURTHYEAR	11250
11900	FIRSTYEAR	11300
11950	SECONDYEAR	11300
12000	THIRDYEAR	11300
12050	FOURTHYEAR	11300
\.


--
-- Data for Name: branch; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.branch (id, branch_name, address_1, address_2, branch_head, college_id, city_id, state_id) FROM stdin;
1001	Hyd Engineering College	Hyd address 1	Hyd address 2	Mr. Venkat	951	\N	\N
11150	Grand Engineering College	Hyd address 1	Hyd address 2	Mr. Tez	951	\N	\N
\.


--
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.city (id, city_name, city_code, std_code, state_id) FROM stdin;
\.


--
-- Data for Name: college; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.college (id, short_name, logo, background_image, instruction_information) FROM stdin;
951	Hyd Technical University	123	456	Hyd Technical University
\.


--
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.country (id, country_name, country_code, isd_code) FROM stdin;
\.


--
-- Data for Name: currency; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.currency (id, currency_name, currency_code, currency_symbol, country_id) FROM stdin;
\.


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
00000000000000	jhipster	config/liquibase/changelog/00000000000000_initial_schema.xml	2019-03-04 17:20:34.821653	1	EXECUTED	7:a6235f40597a13436aa36c6d61db2269	createSequence sequenceName=hibernate_sequence		\N	3.5.4	\N	\N	1700234586
00000000000001	jhipster	config/liquibase/changelog/00000000000000_initial_schema.xml	2019-03-04 17:20:34.917659	2	EXECUTED	7:b5ee0758b78b82af99fff74284dca29f	createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...		\N	3.5.4	\N	\N	1700234586
20190118141512-1	jhipster	config/liquibase/changelog/20190118141512_added_entity_Department.xml	2019-03-04 17:20:34.93766	3	EXECUTED	7:609a44ee8a7f80b44e8c12e6b612d2a5	createTable tableName=department		\N	3.5.4	\N	\N	1700234586
20190118141513-1	jhipster	config/liquibase/changelog/20190118141513_added_entity_Batch.xml	2019-03-04 17:20:34.949661	4	EXECUTED	7:c680bb1d959026a27482af59024f3a3b	createTable tableName=batch		\N	3.5.4	\N	\N	1700234586
20190118141515-1	jhipster	config/liquibase/changelog/20190118141515_added_entity_Section.xml	2019-03-04 17:20:34.960661	5	EXECUTED	7:a3f5d37983acf555ef52d8af2f48d841	createTable tableName=section		\N	3.5.4	\N	\N	1700234586
20190118141516-1	jhipster	config/liquibase/changelog/20190118141516_added_entity_Term.xml	2019-03-04 17:20:34.975662	6	EXECUTED	7:b084e90a6ca30f84b23c3673dd304e26	createTable tableName=term		\N	3.5.4	\N	\N	1700234586
20190118141520-1	jhipster	config/liquibase/changelog/20190118141520_added_entity_Holiday.xml	2019-03-04 17:20:34.991663	7	EXECUTED	7:b2b3bbc0dc566733d1b6b0a4e8d99062	createTable tableName=holiday		\N	3.5.4	\N	\N	1700234586
20190118141527-1	jhipster	config/liquibase/changelog/20190118141527_added_entity_Location.xml	2019-03-04 17:20:35.006664	8	EXECUTED	7:dba1934903af0955a1cb5d1fabb8b478	createTable tableName=location		\N	3.5.4	\N	\N	1700234586
20190118141530-1	jhipster	config/liquibase/changelog/20190118141530_added_entity_BankAccounts.xml	2019-03-04 17:20:35.023665	9	EXECUTED	7:50863971152d24e79c9ff7471a638819	createTable tableName=bank_accounts		\N	3.5.4	\N	\N	1700234586
20190122100629-1	jhipster	config/liquibase/changelog/20190122100629_added_entity_Subject.xml	2019-03-04 17:20:35.036666	10	EXECUTED	7:07b062127f835f246117e5744086f71c	createTable tableName=subject		\N	3.5.4	\N	\N	1700234586
20190122101135-1	jhipster	config/liquibase/changelog/20190122101135_added_entity_StudentAttendance.xml	2019-03-04 17:20:35.051667	11	EXECUTED	7:d7c27b6e26d7f36e320efd20e1f2c722	createTable tableName=student_attendance		\N	3.5.4	\N	\N	1700234586
20190123115124-1	jhipster	config/liquibase/changelog/20190123115124_added_entity_Lecture.xml	2019-03-04 17:20:35.069668	12	EXECUTED	7:e38b83414dfba9e1908ad67932be1777	createTable tableName=lecture		\N	3.5.4	\N	\N	1700234586
20190124093523-1	jhipster	config/liquibase/changelog/20190124093523_added_entity_AttendanceMaster.xml	2019-03-04 17:20:35.079668	13	EXECUTED	7:6ea71444a4b583cabd293665e7555087	createTable tableName=attendance_master		\N	3.5.4	\N	\N	1700234586
20190124103741-1	jhipster	config/liquibase/changelog/20190124103741_added_entity_Teach.xml	2019-03-04 17:20:35.089669	14	EXECUTED	7:45316a12134793fe683a51cf8f8b464d	createTable tableName=teach		\N	3.5.4	\N	\N	1700234586
20190128125803-1	jhipster	config/liquibase/changelog/20190128125803_added_entity_Teacher.xml	2019-03-04 17:20:35.10867	15	EXECUTED	7:97055a0b9e5de2f79c78fd80cb6856bd	createTable tableName=teacher		\N	3.5.4	\N	\N	1700234586
20190205082417-1	jhipster	config/liquibase/changelog/20190205082417_added_entity_Student.xml	2019-03-04 17:20:35.129671	16	EXECUTED	7:9e5b3d75a939379a2ab6eeb5e10860ff	createTable tableName=student		\N	3.5.4	\N	\N	1700234586
20190208044628-1	jhipster	config/liquibase/changelog/20190208044628_added_entity_College.xml	2019-03-04 17:20:35.144672	17	EXECUTED	7:0dc18017970bbd3e1473ae868f39ad23	createTable tableName=college		\N	3.5.4	\N	\N	1700234586
20190214071443-1	jhipster	config/liquibase/changelog/20190214071443_added_entity_Branch.xml	2019-03-04 17:20:35.161673	18	EXECUTED	7:af29eac14e67e0b0c229fe29b5251579	createTable tableName=branch		\N	3.5.4	\N	\N	1700234586
20190208044647-1	jhipster	config/liquibase/changelog/20190208044647_added_entity_AuthorizedSignatory.xml	2019-03-04 17:20:35.177674	19	EXECUTED	7:66fb0119c78e505a472292485564d854	createTable tableName=authorized_signatory		\N	3.5.4	\N	\N	1700234586
20190222065258-1	jhipster	config/liquibase/changelog/20190222065258_added_entity_AcademicYear.xml	2019-03-04 17:20:35.186674	20	EXECUTED	7:b5eb835ba01801f4a4dc14838637539c	createTable tableName=academic_year		\N	3.5.4	\N	\N	1700234586
20190225061048-1	jhipster	config/liquibase/changelog/20190225061048_added_entity_LegalEntity.xml	2019-03-04 17:20:35.205675	21	EXECUTED	7:7eb3227785d9fc3b670edfa547a37975	createTable tableName=legal_entity		\N	3.5.4	\N	\N	1700234586
20190225061051-1	jhipster	config/liquibase/changelog/20190225061051_added_entity_Country.xml	2019-03-04 17:20:35.218676	22	EXECUTED	7:846368a1c0b6305f047f8d00432d0cd3	createTable tableName=country		\N	3.5.4	\N	\N	1700234586
20190225061052-1	jhipster	config/liquibase/changelog/20190225061052_added_entity_Currency.xml	2019-03-04 17:20:35.232677	23	EXECUTED	7:bbb83aa78cf4429204b02f441b95949b	createTable tableName=currency		\N	3.5.4	\N	\N	1700234586
20190225061053-1	jhipster	config/liquibase/changelog/20190225061053_added_entity_State.xml	2019-03-04 17:20:35.251678	24	EXECUTED	7:aa0fd71dc90b770a92d2abd164e0d21c	createTable tableName=state		\N	3.5.4	\N	\N	1700234586
20190225061054-1	jhipster	config/liquibase/changelog/20190225061054_added_entity_City.xml	2019-03-04 17:20:35.266679	25	EXECUTED	7:3b84f91c84c26111a6e93b77e8115598	createTable tableName=city		\N	3.5.4	\N	\N	1700234586
20190225061055-1	jhipster	config/liquibase/changelog/20190225061055_added_entity_FeeCategory.xml	2019-03-04 17:20:35.28168	26	EXECUTED	7:046743a42e33cf8951dafaee3988b72c	createTable tableName=fee_category		\N	3.5.4	\N	\N	1700234586
20190225061056-1	jhipster	config/liquibase/changelog/20190225061056_added_entity_Facility.xml	2019-03-04 17:20:35.29068	27	EXECUTED	7:0ed9dd81ee6a40d8ae4ad0bfb038a9c7	createTable tableName=facility		\N	3.5.4	\N	\N	1700234586
20190225061057-1	jhipster	config/liquibase/changelog/20190225061057_added_entity_TransportRoute.xml	2019-03-04 17:20:35.304681	28	EXECUTED	7:d920f9a258da59edef518300e856b1fb	createTable tableName=transport_route		\N	3.5.4	\N	\N	1700234586
20190225061058-1	jhipster	config/liquibase/changelog/20190225061058_added_entity_FeeDetails.xml	2019-03-04 17:20:35.327682	29	EXECUTED	7:90f170ee2389a4d65aed589490f01ada	createTable tableName=fee_details		\N	3.5.4	\N	\N	1700234586
20190225061059-1	jhipster	config/liquibase/changelog/20190225061059_added_entity_DueDate.xml	2019-03-04 17:20:35.341683	30	EXECUTED	7:418b001d767b314227719a5c059a9c60	createTable tableName=due_date		\N	3.5.4	\N	\N	1700234586
20190225061100-1	jhipster	config/liquibase/changelog/20190225061100_added_entity_PaymentRemainder.xml	2019-03-04 17:20:35.357684	31	EXECUTED	7:af129382cc9bce6770e340b5215464e6	createTable tableName=payment_remainder		\N	3.5.4	\N	\N	1700234586
20190225061101-1	jhipster	config/liquibase/changelog/20190225061101_added_entity_LateFee.xml	2019-03-04 17:20:35.376685	32	EXECUTED	7:29e456db68d4cf24b99b5c50e29605ec	createTable tableName=late_fee		\N	3.5.4	\N	\N	1700234586
20190225061102-1	jhipster	config/liquibase/changelog/20190225061102_added_entity_Invoice.xml	2019-03-04 17:20:35.398686	33	EXECUTED	7:11e67d67152215dadd28efe0a4c83e00	createTable tableName=invoice		\N	3.5.4	\N	\N	1700234586
20190118141512-2	jhipster	config/liquibase/changelog/20190118141512_added_entity_constraints_Department.xml	2019-03-04 17:20:35.409687	34	EXECUTED	7:53de67c219b94146c0759136fa060828	addForeignKeyConstraint baseTableName=department, constraintName=fk_department_branch_id, referencedTableName=branch; addForeignKeyConstraint baseTableName=department, constraintName=fk_department_academicyear_id, referencedTableName=academic_year		\N	3.5.4	\N	\N	1700234586
20190118141513-2	jhipster	config/liquibase/changelog/20190118141513_added_entity_constraints_Batch.xml	2019-03-04 17:20:35.417687	35	EXECUTED	7:87327eed6c22b67eefc24dc37471b7d5	addForeignKeyConstraint baseTableName=batch, constraintName=fk_batch_department_id, referencedTableName=department		\N	3.5.4	\N	\N	1700234586
20190118141515-2	jhipster	config/liquibase/changelog/20190118141515_added_entity_constraints_Section.xml	2019-03-04 17:20:35.424688	36	EXECUTED	7:78f71b5c751d567bb339085fb73b327c	addForeignKeyConstraint baseTableName=section, constraintName=fk_section_batch_id, referencedTableName=batch		\N	3.5.4	\N	\N	1700234586
20190118141516-2	jhipster	config/liquibase/changelog/20190118141516_added_entity_constraints_Term.xml	2019-03-04 17:20:35.431688	37	EXECUTED	7:f7ea236423beb89c3f961e64e21069d0	addForeignKeyConstraint baseTableName=term, constraintName=fk_term_academicyear_id, referencedTableName=academic_year		\N	3.5.4	\N	\N	1700234586
20190118141520-2	jhipster	config/liquibase/changelog/20190118141520_added_entity_constraints_Holiday.xml	2019-03-04 17:20:35.439689	38	EXECUTED	7:957f40e08ecd004219ce0c739d3c8bc7	addForeignKeyConstraint baseTableName=holiday, constraintName=fk_holiday_academicyear_id, referencedTableName=academic_year		\N	3.5.4	\N	\N	1700234586
20190122100629-2	jhipster	config/liquibase/changelog/20190122100629_added_entity_constraints_Subject.xml	2019-03-04 17:20:35.448689	39	EXECUTED	7:a31ba84a16ae94c8eb4637464a74ecde	addForeignKeyConstraint baseTableName=subject, constraintName=fk_subject_department_id, referencedTableName=department; addForeignKeyConstraint baseTableName=subject, constraintName=fk_subject_batch_id, referencedTableName=batch		\N	3.5.4	\N	\N	1700234586
20190122101135-2	jhipster	config/liquibase/changelog/20190122101135_added_entity_constraints_StudentAttendance.xml	2019-03-04 17:20:35.45669	40	EXECUTED	7:731eeee10c037f2f79a4867425484e1d	addForeignKeyConstraint baseTableName=student_attendance, constraintName=fk_student_attendance_student_id, referencedTableName=student; addForeignKeyConstraint baseTableName=student_attendance, constraintName=fk_student_attendance_lecture_id, refe...		\N	3.5.4	\N	\N	1700234586
20190123115124-2	jhipster	config/liquibase/changelog/20190123115124_added_entity_constraints_Lecture.xml	2019-03-04 17:20:35.46169	41	EXECUTED	7:30662fe2b472869dc680ca62ff65633a	addForeignKeyConstraint baseTableName=lecture, constraintName=fk_lecture_attendancemaster_id, referencedTableName=attendance_master		\N	3.5.4	\N	\N	1700234586
20190124093523-2	jhipster	config/liquibase/changelog/20190124093523_added_entity_constraints_AttendanceMaster.xml	2019-03-04 17:20:35.473691	42	EXECUTED	7:62edd2777d56b98505cd4d05adae81b3	addForeignKeyConstraint baseTableName=attendance_master, constraintName=fk_attendance_master_batch_id, referencedTableName=batch; addForeignKeyConstraint baseTableName=attendance_master, constraintName=fk_attendance_master_section_id, referencedTa...		\N	3.5.4	\N	\N	1700234586
20190124103741-2	jhipster	config/liquibase/changelog/20190124103741_added_entity_constraints_Teach.xml	2019-03-04 17:20:35.481691	43	EXECUTED	7:b5af7ed013cd7324419046e904e0fcd4	addForeignKeyConstraint baseTableName=teach, constraintName=fk_teach_subject_id, referencedTableName=subject; addForeignKeyConstraint baseTableName=teach, constraintName=fk_teach_teacher_id, referencedTableName=teacher		\N	3.5.4	\N	\N	1700234586
20190128125803-2	jhipster	config/liquibase/changelog/20190128125803_added_entity_constraints_Teacher.xml	2019-03-04 17:20:35.488692	44	EXECUTED	7:28caf2e2adc0f3abf2304b1253340332	addForeignKeyConstraint baseTableName=teacher, constraintName=fk_teacher_department_id, referencedTableName=department; addForeignKeyConstraint baseTableName=teacher, constraintName=fk_teacher_branch_id, referencedTableName=branch		\N	3.5.4	\N	\N	1700234586
20190205082417-2	jhipster	config/liquibase/changelog/20190205082417_added_entity_constraints_Student.xml	2019-03-04 17:20:35.502692	45	EXECUTED	7:2fb4f1941fa40fd126c322c418fc3db5	addForeignKeyConstraint baseTableName=student, constraintName=fk_student_department_id, referencedTableName=department; addForeignKeyConstraint baseTableName=student, constraintName=fk_student_batch_id, referencedTableName=batch; addForeignKeyCons...		\N	3.5.4	\N	\N	1700234586
20190118141530-2	jhipster	config/liquibase/changelog/20190118141530_added_entity_constraints_BankAccounts.xml	2019-03-04 17:20:35.511693	46	EXECUTED	7:1881abb31f495b8f1253e91020c4e6cb	addForeignKeyConstraint baseTableName=bank_accounts, constraintName=fk_bank_accounts_branch_id, referencedTableName=branch; addForeignKeyConstraint baseTableName=bank_accounts, constraintName=fk_bank_accounts_college_id, referencedTableName=college		\N	3.5.4	\N	\N	1700234586
20190214071443-2	jhipster	config/liquibase/changelog/20190214071443_added_entity_constraints_Branch.xml	2019-03-04 17:20:35.523694	47	EXECUTED	7:fa34714404d91862d8e3b274d3cc32b7	addForeignKeyConstraint baseTableName=branch, constraintName=fk_branch_college_id, referencedTableName=college; addForeignKeyConstraint baseTableName=branch, constraintName=fk_branch_city_id, referencedTableName=city; addForeignKeyConstraint baseT...		\N	3.5.4	\N	\N	1700234586
20190208044647-2	jhipster	config/liquibase/changelog/20190208044647_added_entity_constraints_AuthorizedSignatory.xml	2019-03-04 17:20:35.534694	48	EXECUTED	7:65df57c42fbe8cf6a5280da34d50c0a4	addForeignKeyConstraint baseTableName=authorized_signatory, constraintName=fk_authorized_signatory_branch_id, referencedTableName=branch; addForeignKeyConstraint baseTableName=authorized_signatory, constraintName=fk_authorized_signatory_college_id...		\N	3.5.4	\N	\N	1700234586
20190225061048-2	jhipster	config/liquibase/changelog/20190225061048_added_entity_constraints_LegalEntity.xml	2019-03-04 17:20:35.547695	49	EXECUTED	7:50085fd33cdebd071d034217c3fd1676	addForeignKeyConstraint baseTableName=legal_entity, constraintName=fk_legal_entity_branch_id, referencedTableName=branch; addForeignKeyConstraint baseTableName=legal_entity, constraintName=fk_legal_entity_college_id, referencedTableName=college; a...		\N	3.5.4	\N	\N	1700234586
20190225061052-2	jhipster	config/liquibase/changelog/20190225061052_added_entity_constraints_Currency.xml	2019-03-04 17:20:35.554695	50	EXECUTED	7:e618c43c2d7a98ec2a715c1668b31682	addForeignKeyConstraint baseTableName=currency, constraintName=fk_currency_country_id, referencedTableName=country		\N	3.5.4	\N	\N	1700234586
20190225061053-2	jhipster	config/liquibase/changelog/20190225061053_added_entity_constraints_State.xml	2019-03-04 17:20:35.560696	51	EXECUTED	7:763ec709ad29816303d0e2e5b78fb05c	addForeignKeyConstraint baseTableName=state, constraintName=fk_state_country_id, referencedTableName=country		\N	3.5.4	\N	\N	1700234586
20190225061054-2	jhipster	config/liquibase/changelog/20190225061054_added_entity_constraints_City.xml	2019-03-04 17:20:35.566696	52	EXECUTED	7:752c9d17d18524d96187ffb89af6e3f8	addForeignKeyConstraint baseTableName=city, constraintName=fk_city_state_id, referencedTableName=state		\N	3.5.4	\N	\N	1700234586
20190225061058-2	jhipster	config/liquibase/changelog/20190225061058_added_entity_constraints_FeeDetails.xml	2019-03-04 17:20:35.585697	53	EXECUTED	7:28b416a5736d4bf80117f5909d75f965	addForeignKeyConstraint baseTableName=fee_details, constraintName=fk_fee_details_fee_category_id, referencedTableName=fee_category; addForeignKeyConstraint baseTableName=fee_details, constraintName=fk_fee_details_batch_id, referencedTableName=batc...		\N	3.5.4	\N	\N	1700234586
20190225061059-2	jhipster	config/liquibase/changelog/20190225061059_added_entity_constraints_DueDate.xml	2019-03-04 17:20:35.594698	54	EXECUTED	7:b36e309ef45ff1f011995add4a442ac8	addForeignKeyConstraint baseTableName=due_date, constraintName=fk_due_date_college_id, referencedTableName=college; addForeignKeyConstraint baseTableName=due_date, constraintName=fk_due_date_branch_id, referencedTableName=branch		\N	3.5.4	\N	\N	1700234586
20190225061100-2	jhipster	config/liquibase/changelog/20190225061100_added_entity_constraints_PaymentRemainder.xml	2019-03-04 17:20:35.606698	55	EXECUTED	7:0bd78b59d59cf9075b0eb3b90e000ad0	addForeignKeyConstraint baseTableName=payment_remainder, constraintName=fk_payment_remainder_due_date_id, referencedTableName=due_date; addForeignKeyConstraint baseTableName=payment_remainder, constraintName=fk_payment_remainder_college_id, refere...		\N	3.5.4	\N	\N	1700234586
20190225061101-2	jhipster	config/liquibase/changelog/20190225061101_added_entity_constraints_LateFee.xml	2019-03-04 17:20:35.616699	56	EXECUTED	7:0a3af2e8749dbcef97c5de2adbfdf676	addForeignKeyConstraint baseTableName=late_fee, constraintName=fk_late_fee_college_id, referencedTableName=college; addForeignKeyConstraint baseTableName=late_fee, constraintName=fk_late_fee_branch_id, referencedTableName=branch		\N	3.5.4	\N	\N	1700234586
20190225061102-2	jhipster	config/liquibase/changelog/20190225061102_added_entity_constraints_Invoice.xml	2019-03-04 17:20:35.6367	57	EXECUTED	7:3de272ee91e1ac9d983f70874dede2f1	addForeignKeyConstraint baseTableName=invoice, constraintName=fk_invoice_fee_category_id, referencedTableName=fee_category; addForeignKeyConstraint baseTableName=invoice, constraintName=fk_invoice_fee_details_id, referencedTableName=fee_details; a...		\N	3.5.4	\N	\N	1700234586
local_test_data_load	jhipster	config/liquibase/cms_test_data/local_test_data_load.xml	2019-03-04 17:20:37.635814	58	EXECUTED	7:11dfd15e250e5d7ee22a5ab0ff730605	loadData tableName=college; loadData tableName=location; loadData tableName=academic_year; loadData tableName=term; loadData tableName=holiday; loadData tableName=branch; loadData tableName=legal_entity; loadData tableName=authorized_signatory; lo...		\N	3.5.4	\N	\N	1700234586
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.department (id, name, description, dept_head, branch_id, academicyear_id) FROM stdin;
1101	Department of Computer Science	Department of Computer Science	jack	1001	1051
11200	Department of Civil Engineering	Department of Civil Engineering	Thomas	1001	1051
11250	Department of Chemical Engineering	Department of Chemical Engineering	Ron	1001	1051
11300	Department of Robotics	Department of Robotics	Marry	1001	1051
\.


--
-- Data for Name: due_date; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.due_date (id, payment_method, installments, day_desc, frequency, college_id, branch_id) FROM stdin;
\.


--
-- Data for Name: facility; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.facility (id, facility_name) FROM stdin;
\.


--
-- Data for Name: fee_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fee_category (id, category_name, description) FROM stdin;
\.


--
-- Data for Name: fee_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fee_details (id, fee_particulars_name, fee_particular_desc, student_type, gender, amount, fee_category_id, batch_id, facility_id, transport_route_id, college_id, department_id, branch_id, academic_year_id) FROM stdin;
\.


--
-- Data for Name: holiday; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.holiday (id, holiday_desc, holiday_date, holiday_status, academicyear_id) FROM stdin;
19300	New Year	2019-01-01	ACTIVE	1051
19350	Christmas Day	2019-12-25	ACTIVE	1051
19400	Guru Govind Singh Birthday	2019-01-13	ACTIVE	1051
19450	Makar Sankranti	2019-01-14	ACTIVE	1051
19500	Pongal	2019-01-15	ACTIVE	1051
19550	Republic Day	2019-01-26	ACTIVE	1051
19600	Basant Panchami	2019-02-10	ACTIVE	1051
19650	Maha Shivaratri/Shivaratri	2019-03-04	ACTIVE	1051
19700	Holi	2019-03-21	ACTIVE	1051
19750	Diwali	2019-10-27	ACTIVE	1051
\.


--
-- Data for Name: invoice; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.invoice (id, invoice_number, amount_paid, payment_date, next_payment_date, out_standing_amount, mode_of_payment, cheque_number, demand_draft_number, online_txn_ref_number, payment_status, comments, updated_by, updated_on, fee_category_id, fee_details_id, due_date_id, payment_remainder_id, college_id, branch_id, student_id, academic_year_id) FROM stdin;
\.


--
-- Data for Name: jhi_authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_authority (name) FROM stdin;
ROLE_ADMIN
ROLE_USER
\.


--
-- Data for Name: jhi_persistent_audit_event; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_persistent_audit_event (event_id, principal, event_date, event_type) FROM stdin;
\.


--
-- Data for Name: jhi_persistent_audit_evt_data; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_persistent_audit_evt_data (event_id, name, value) FROM stdin;
\.


--
-- Data for Name: jhi_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date) FROM stdin;
1	system	$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG	System	System	system@localhost		t	en	\N	\N	system	2019-03-04 17:20:34.829654	\N	system	\N
2	anonymoususer	$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO	Anonymous	User	anonymous@localhost		t	en	\N	\N	system	2019-03-04 17:20:34.829654	\N	system	\N
3	admin	$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC	Administrator	Administrator	admin@localhost		t	en	\N	\N	system	2019-03-04 17:20:34.829654	\N	system	\N
4	user	$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K	User	User	user@localhost		t	en	\N	\N	system	2019-03-04 17:20:34.829654	\N	system	\N
\.


--
-- Data for Name: jhi_user_authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_user_authority (user_id, authority_name) FROM stdin;
1	ROLE_ADMIN
1	ROLE_USER
3	ROLE_ADMIN
3	ROLE_USER
4	ROLE_USER
\.


--
-- Data for Name: late_fee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.late_fee (id, assign_late_fee, late_fee_days, fixed, percentage, fixed_charges, percent_charges, late_fee_assignment_frequency, college_id, branch_id) FROM stdin;
\.


--
-- Data for Name: lecture; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lecture (id, lec_date, last_updated_on, last_updated_by, start_time, end_time, attendancemaster_id) FROM stdin;
19850	2019-01-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
19900	2019-01-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
19950	2019-01-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
20000	2019-01-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
20050	2019-01-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
20100	2019-01-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
20150	2019-01-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
20200	2019-01-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
20250	2019-01-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
20300	2019-01-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
20350	2019-01-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
20400	2019-01-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
20450	2019-01-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
20500	2019-01-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
20550	2019-01-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
20600	2019-02-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
20650	2019-02-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
20700	2019-02-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
20750	2019-02-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
20800	2019-02-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
20850	2019-02-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
20900	2019-02-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
20950	2019-02-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
21000	2019-02-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
21050	2019-02-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
21100	2019-02-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
21150	2019-02-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
21200	2019-02-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
21250	2019-02-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
21300	2019-02-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
21350	2019-02-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
21400	2019-02-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
21450	2019-02-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
21500	2019-02-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
21550	2019-02-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
21600	2019-03-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
21650	2019-03-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
21700	2019-03-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
21750	2019-03-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
21800	2019-03-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
21850	2019-03-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
21900	2019-03-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
21950	2019-03-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
22000	2019-03-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
22050	2019-03-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
22100	2019-03-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
22150	2019-03-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
22200	2019-03-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
22250	2019-03-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
22300	2019-03-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
22350	2019-04-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
22400	2019-04-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
22450	2019-04-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
22500	2019-04-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
22550	2019-04-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
22600	2019-04-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
22650	2019-04-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
22700	2019-04-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
22750	2019-04-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
22800	2019-04-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
22850	2019-04-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
22900	2019-04-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
22950	2019-04-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
23000	2019-04-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
23050	2019-04-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
23100	2019-04-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
23150	2019-04-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
23200	2019-04-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
23250	2019-04-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
23300	2019-04-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
23350	2019-04-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
23400	2019-04-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
23450	2019-04-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
23500	2019-04-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
23550	2019-04-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
23600	2019-05-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
23650	2019-05-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
23700	2019-05-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
23750	2019-05-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
23800	2019-05-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
23850	2019-05-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
23900	2019-05-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
23950	2019-05-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
24000	2019-05-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
24050	2019-05-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
24100	2019-05-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
24150	2019-05-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
24200	2019-05-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
24250	2019-05-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
24300	2019-05-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
24350	2019-05-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
24400	2019-05-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
24450	2019-05-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
24500	2019-05-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
24550	2019-05-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
24600	2019-06-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
24650	2019-06-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
24700	2019-06-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
24750	2019-06-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
24800	2019-06-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
24850	2019-06-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
24900	2019-06-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
24950	2019-06-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
25000	2019-06-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
25050	2019-06-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
25100	2019-06-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
25150	2019-06-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
25200	2019-06-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
25250	2019-06-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
25300	2019-06-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
25350	2019-06-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
25400	2019-06-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
25450	2019-06-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
25500	2019-06-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
25550	2019-06-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
25600	2019-01-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
25650	2019-01-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
25700	2019-01-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
25750	2019-01-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
25800	2019-01-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
25850	2019-01-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
25900	2019-01-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
25950	2019-01-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
26000	2019-01-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
26050	2019-01-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
26100	2019-01-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
26150	2019-01-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
26200	2019-01-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
26250	2019-01-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
26300	2019-01-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
26350	2019-02-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
26400	2019-02-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
26450	2019-02-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
26500	2019-02-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
26550	2019-02-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
26600	2019-02-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
26650	2019-02-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
26700	2019-02-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
26750	2019-02-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
26800	2019-02-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
26850	2019-02-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
26900	2019-02-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
26950	2019-02-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
27000	2019-02-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
27050	2019-02-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
27100	2019-02-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
27150	2019-02-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
27200	2019-02-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
27250	2019-02-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
27300	2019-02-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
27350	2019-03-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
27400	2019-03-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
27450	2019-03-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
27500	2019-03-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
27550	2019-03-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
27600	2019-03-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
27650	2019-03-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
27700	2019-03-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
27750	2019-03-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
27800	2019-03-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
27850	2019-03-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
27900	2019-03-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
27950	2019-03-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
28000	2019-03-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
28050	2019-03-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
28100	2019-03-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
28150	2019-03-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
28200	2019-03-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
28250	2019-03-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
28300	2019-03-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
28350	2019-04-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
28400	2019-04-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
28450	2019-04-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
28500	2019-04-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
28550	2019-04-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
28600	2019-04-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
28650	2019-04-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
28700	2019-04-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
28750	2019-04-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
28800	2019-04-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
28850	2019-04-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
28900	2019-04-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
28950	2019-04-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
29000	2019-04-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
29050	2019-04-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
29100	2019-04-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
29150	2019-04-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
29200	2019-04-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
29250	2019-04-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
29300	2019-04-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
29350	2019-04-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
29400	2019-04-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
29450	2019-04-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
29500	2019-04-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
29550	2019-04-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
29600	2019-05-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
29650	2019-05-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
29700	2019-05-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
29750	2019-05-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
29800	2019-05-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
29850	2019-05-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
29900	2019-05-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
29950	2019-05-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
30000	2019-05-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
30050	2019-05-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
30100	2019-05-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
30150	2019-05-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
30200	2019-05-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
30250	2019-05-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
30300	2019-05-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
30350	2019-05-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
30400	2019-05-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
30450	2019-05-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
30500	2019-05-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
30550	2019-05-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
30600	2019-06-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
30650	2019-06-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
30700	2019-06-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
30750	2019-06-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
30800	2019-06-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
30850	2019-06-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
30900	2019-06-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
30950	2019-06-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
31000	2019-06-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
31050	2019-06-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
31100	2019-06-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
31150	2019-06-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
31200	2019-06-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
31250	2019-06-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
31300	2019-06-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
31350	2019-06-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
31400	2019-06-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
31450	2019-06-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
31500	2019-06-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
31550	2019-06-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
31600	2019-01-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
31650	2019-01-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
31700	2019-01-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
31750	2019-01-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
31800	2019-01-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
31850	2019-01-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
31900	2019-01-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
31950	2019-01-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
32000	2019-01-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
32050	2019-01-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
32100	2019-01-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
32150	2019-01-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
32200	2019-01-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
32250	2019-01-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
32300	2019-01-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
32350	2019-01-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
32400	2019-01-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
32450	2019-01-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
32500	2019-01-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
32550	2019-01-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
32600	2019-01-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
32650	2019-01-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
32700	2019-01-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
32750	2019-01-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
32800	2019-01-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
32850	2019-02-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
32900	2019-02-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
32950	2019-02-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
33000	2019-02-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
33050	2019-02-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
33100	2019-02-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
33150	2019-02-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
33200	2019-02-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
33250	2019-02-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
33300	2019-02-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
33350	2019-02-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
33400	2019-02-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
33450	2019-02-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
33500	2019-02-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
33550	2019-02-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
33600	2019-02-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
33650	2019-02-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
33700	2019-02-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
33750	2019-02-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
33800	2019-02-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
33850	2019-03-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
33900	2019-03-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
33950	2019-03-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
34000	2019-03-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
34050	2019-03-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
34100	2019-03-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
34150	2019-03-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
34200	2019-03-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
34250	2019-03-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
34300	2019-03-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
34350	2019-03-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
34400	2019-03-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
34450	2019-03-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
34500	2019-03-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
34550	2019-03-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
34600	2019-03-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
34650	2019-03-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
34700	2019-03-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
34750	2019-03-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
34800	2019-03-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
34850	2019-04-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
34900	2019-04-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
34950	2019-04-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
35000	2019-04-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
35050	2019-04-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
35100	2019-04-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
35150	2019-04-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
35200	2019-04-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
35250	2019-04-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
35300	2019-04-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
35350	2019-04-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
35400	2019-04-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
35450	2019-04-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
35500	2019-04-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
35550	2019-04-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
35600	2019-04-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
35650	2019-04-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
35700	2019-04-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
35750	2019-04-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
35800	2019-04-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
35850	2019-05-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
35900	2019-05-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
35950	2019-05-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
36000	2019-05-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
36050	2019-05-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
36100	2019-05-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
36150	2019-05-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
36200	2019-05-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
36250	2019-05-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
36300	2019-05-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
36350	2019-05-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
36400	2019-05-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
36450	2019-05-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
36500	2019-05-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
36550	2019-05-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
36600	2019-05-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
36650	2019-05-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
36700	2019-05-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
36750	2019-05-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
36800	2019-05-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
36850	2019-05-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
36900	2019-05-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
36950	2019-05-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
37000	2019-05-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
37050	2019-05-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
37100	2019-06-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
37150	2019-06-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
37200	2019-06-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
37250	2019-06-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
37300	2019-06-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
37350	2019-06-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
37400	2019-06-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
37450	2019-06-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
37500	2019-06-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
37550	2019-06-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
37600	2019-06-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
37650	2019-06-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
37700	2019-06-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
37750	2019-06-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
37800	2019-06-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
37850	2019-06-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
37900	2019-06-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
37950	2019-06-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
38000	2019-06-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
38050	2019-06-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
38100	2019-01-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
38150	2019-01-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
38200	2019-01-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
38250	2019-01-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
38300	2019-01-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
38350	2019-01-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
38400	2019-01-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
38450	2019-01-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
38500	2019-01-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
38550	2019-01-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
38600	2019-01-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
38650	2019-01-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
38700	2019-01-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
38750	2019-01-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
38800	2019-01-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
38850	2019-01-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
38900	2019-01-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
38950	2019-01-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
39000	2019-01-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
39050	2019-01-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
39100	2019-01-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
39150	2019-01-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
39200	2019-01-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
39250	2019-01-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
39300	2019-01-31	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
39350	2019-02-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
39400	2019-02-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
39450	2019-02-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
39500	2019-02-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
39550	2019-02-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
39600	2019-02-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
39650	2019-02-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
39700	2019-02-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
39750	2019-02-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
39800	2019-02-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
39850	2019-02-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
39900	2019-02-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
39950	2019-02-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
40000	2019-02-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
40050	2019-02-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
40100	2019-02-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
40150	2019-02-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
40200	2019-02-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
40250	2019-02-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
40300	2019-02-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
40350	2019-03-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
40400	2019-03-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
40450	2019-03-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
40500	2019-03-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
40550	2019-03-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
40600	2019-03-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
40650	2019-03-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
40700	2019-03-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
40750	2019-03-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
40800	2019-03-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
40850	2019-03-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
40900	2019-03-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
40950	2019-03-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
41000	2019-03-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
41050	2019-03-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
41100	2019-04-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
41150	2019-04-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
41200	2019-04-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
41250	2019-04-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
41300	2019-04-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
41350	2019-04-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
41400	2019-04-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
41450	2019-04-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
41500	2019-04-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
41550	2019-04-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
41600	2019-04-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
41650	2019-04-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
41700	2019-04-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
41750	2019-04-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
41800	2019-04-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
41850	2019-04-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
41900	2019-04-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
41950	2019-04-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
42000	2019-04-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
42050	2019-04-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
42100	2019-05-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
42150	2019-05-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
42200	2019-05-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
42250	2019-05-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
42300	2019-05-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
42350	2019-05-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
42400	2019-05-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
42450	2019-05-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
42500	2019-05-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
42550	2019-05-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
42600	2019-05-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
42650	2019-05-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
42700	2019-05-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
42750	2019-05-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
42800	2019-05-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
42850	2019-05-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
42900	2019-05-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
42950	2019-05-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
43000	2019-05-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
43050	2019-05-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
43100	2019-05-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
43150	2019-05-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
43200	2019-05-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
43250	2019-05-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
43300	2019-05-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
43350	2019-06-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
43400	2019-06-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
43450	2019-06-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
43500	2019-06-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
43550	2019-06-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
43600	2019-06-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
43650	2019-06-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
43700	2019-06-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
43750	2019-06-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
43800	2019-06-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
43850	2019-06-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
43900	2019-06-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
43950	2019-06-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
44000	2019-06-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
44050	2019-06-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
44100	2019-06-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
44150	2019-06-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
44200	2019-06-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
44250	2019-06-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
44300	2019-06-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
44350	2019-01-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
44400	2019-01-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
44450	2019-01-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
44500	2019-01-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
44550	2019-01-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
44600	2019-01-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
44650	2019-01-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
44700	2019-01-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
44750	2019-01-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
44800	2019-01-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
44850	2019-01-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
44900	2019-01-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
44950	2019-01-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
45000	2019-01-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
45050	2019-01-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
45100	2019-01-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
45150	2019-01-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
45200	2019-01-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
45250	2019-01-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
45300	2019-01-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
45350	2019-02-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
45400	2019-02-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
45450	2019-02-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
45500	2019-02-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
45550	2019-02-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
45600	2019-02-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
45650	2019-02-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
45700	2019-02-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
45750	2019-02-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
45800	2019-02-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
45850	2019-02-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
45900	2019-02-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
45950	2019-02-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
46000	2019-02-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
46050	2019-02-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
46100	2019-02-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
46150	2019-02-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
46200	2019-02-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
46250	2019-02-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
46300	2019-02-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
46350	2019-03-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
46400	2019-03-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
46450	2019-03-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
46500	2019-03-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
46550	2019-03-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
46600	2019-03-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
46650	2019-03-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
46700	2019-03-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
46750	2019-03-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
46800	2019-03-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
46850	2019-03-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
46900	2019-03-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
46950	2019-03-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
47000	2019-03-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
47050	2019-03-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
47100	2019-03-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
47150	2019-03-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
47200	2019-03-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
47250	2019-03-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
47300	2019-03-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
47350	2019-03-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
47400	2019-03-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
47450	2019-03-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
47500	2019-03-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
47550	2019-03-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
47600	2019-04-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
47650	2019-04-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
47700	2019-04-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
47750	2019-04-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
47800	2019-04-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
47850	2019-04-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
47900	2019-04-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
47950	2019-04-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
48000	2019-04-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
48050	2019-04-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
48100	2019-04-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
48150	2019-04-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
48200	2019-04-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
48250	2019-04-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
48300	2019-04-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
48350	2019-04-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
48400	2019-04-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
48450	2019-04-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
48500	2019-04-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
48550	2019-04-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
48600	2019-05-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
48650	2019-05-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
48700	2019-05-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
48750	2019-05-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
48800	2019-05-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
48850	2019-05-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
48900	2019-05-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
48950	2019-05-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
49000	2019-05-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
49050	2019-05-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
49100	2019-05-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
49150	2019-05-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
49200	2019-05-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
49250	2019-05-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
49300	2019-05-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
49350	2019-05-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
49400	2019-05-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
49450	2019-05-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
49500	2019-05-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
49550	2019-05-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
49600	2019-05-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
49650	2019-05-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
49700	2019-05-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
49750	2019-05-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
49800	2019-05-31	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
49850	2019-06-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
49900	2019-06-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
49950	2019-06-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
50000	2019-06-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
50050	2019-06-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
50100	2019-06-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
50150	2019-06-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
50200	2019-06-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
50250	2019-06-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
50300	2019-06-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
50350	2019-06-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
50400	2019-06-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
50450	2019-06-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
50500	2019-06-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
50550	2019-06-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
50600	2019-06-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
50650	2019-06-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
50700	2019-06-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
50750	2019-06-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
50800	2019-06-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
50850	2019-01-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
50900	2019-01-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
50950	2019-01-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
51000	2019-01-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
51050	2019-01-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
51100	2019-01-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
51150	2019-01-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
51200	2019-01-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
51250	2019-01-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
51300	2019-01-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
51350	2019-01-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
51400	2019-01-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
51450	2019-01-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
51500	2019-01-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
51550	2019-01-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
51600	2019-02-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
51650	2019-02-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
51700	2019-02-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
51750	2019-02-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
51800	2019-02-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
51850	2019-02-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
51900	2019-02-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
51950	2019-02-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
52000	2019-02-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
52050	2019-02-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
52100	2019-02-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
52150	2019-02-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
52200	2019-02-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
52250	2019-02-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
52300	2019-02-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
52350	2019-02-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
52400	2019-02-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
52450	2019-02-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
52500	2019-02-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
52550	2019-02-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
52600	2019-03-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
52650	2019-03-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
52700	2019-03-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
52750	2019-03-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
52800	2019-03-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
52850	2019-03-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
52900	2019-03-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
52950	2019-03-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
53000	2019-03-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
53050	2019-03-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
53100	2019-03-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
53150	2019-03-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
53200	2019-03-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
53250	2019-03-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
53300	2019-03-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
53350	2019-04-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
53400	2019-04-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
53450	2019-04-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
53500	2019-04-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
53550	2019-04-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
53600	2019-04-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
53650	2019-04-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
53700	2019-04-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
53750	2019-04-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
53800	2019-04-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
53850	2019-04-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
53900	2019-04-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
53950	2019-04-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
54000	2019-04-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
54050	2019-04-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
54100	2019-04-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
54150	2019-04-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
54200	2019-04-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
54250	2019-04-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
54300	2019-04-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
54350	2019-04-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
54400	2019-04-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
54450	2019-04-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
54500	2019-04-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
54550	2019-04-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
54600	2019-05-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
54650	2019-05-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
54700	2019-05-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
54750	2019-05-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
54800	2019-05-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
54850	2019-05-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
54900	2019-05-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
54950	2019-05-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
55000	2019-05-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
55050	2019-05-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
55100	2019-05-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
55150	2019-05-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
55200	2019-05-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
55250	2019-05-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
55300	2019-05-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
55350	2019-05-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
55400	2019-05-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
55450	2019-05-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
55500	2019-05-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
55550	2019-05-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
55600	2019-06-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
55650	2019-06-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
55700	2019-06-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
55750	2019-06-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
55800	2019-06-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
55850	2019-06-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
55900	2019-06-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
55950	2019-06-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
56000	2019-06-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
56050	2019-06-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
56100	2019-06-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
56150	2019-06-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
56200	2019-06-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
56250	2019-06-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
56300	2019-06-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
56350	2019-06-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
56400	2019-06-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
56450	2019-06-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
56500	2019-06-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
56550	2019-06-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
56600	2019-01-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
56650	2019-01-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
56700	2019-01-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
56750	2019-01-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
56800	2019-01-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
56850	2019-01-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
56900	2019-01-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
56950	2019-01-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
57000	2019-01-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
57050	2019-01-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
57100	2019-01-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
57150	2019-01-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
57200	2019-01-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
57250	2019-01-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
57300	2019-01-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
57350	2019-02-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
57400	2019-02-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
57450	2019-02-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
57500	2019-02-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
57550	2019-02-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
57600	2019-02-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
57650	2019-02-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
57700	2019-02-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
57750	2019-02-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
57800	2019-02-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
57850	2019-02-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
57900	2019-02-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
57950	2019-02-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
58000	2019-02-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
58050	2019-02-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
58100	2019-02-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
58150	2019-02-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
58200	2019-02-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
58250	2019-02-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
58300	2019-02-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
58350	2019-03-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
58400	2019-03-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
58450	2019-03-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
58500	2019-03-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
58550	2019-03-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
58600	2019-03-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
58650	2019-03-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
58700	2019-03-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
58750	2019-03-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
58800	2019-03-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
58850	2019-03-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
58900	2019-03-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
58950	2019-03-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
59000	2019-03-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
59050	2019-03-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
59100	2019-03-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
59150	2019-03-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
59200	2019-03-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
59250	2019-03-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
59300	2019-03-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
59350	2019-04-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
59400	2019-04-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
59450	2019-04-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
59500	2019-04-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
59550	2019-04-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
59600	2019-04-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
59650	2019-04-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
59700	2019-04-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
59750	2019-04-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
59800	2019-04-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
59850	2019-04-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
59900	2019-04-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
59950	2019-04-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
60000	2019-04-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
60050	2019-04-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
60100	2019-04-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
60150	2019-04-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
60200	2019-04-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
60250	2019-04-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
60300	2019-04-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
60350	2019-04-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
60400	2019-04-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
60450	2019-04-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
60500	2019-04-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
60550	2019-04-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
60600	2019-05-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
60650	2019-05-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
60700	2019-05-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
60750	2019-05-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
60800	2019-05-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
60850	2019-05-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
60900	2019-05-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
60950	2019-05-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
61000	2019-05-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
61050	2019-05-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
61100	2019-05-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
61150	2019-05-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
61200	2019-05-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
61250	2019-05-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
61300	2019-05-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
61350	2019-05-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
61400	2019-05-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
61450	2019-05-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
61500	2019-05-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
61550	2019-05-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
61600	2019-06-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
61650	2019-06-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
61700	2019-06-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
61750	2019-06-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
61800	2019-06-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
61850	2019-06-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
61900	2019-06-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
61950	2019-06-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
62000	2019-06-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
62050	2019-06-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
62100	2019-06-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
62150	2019-06-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
62200	2019-06-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
62250	2019-06-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
62300	2019-06-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
62350	2019-06-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
62400	2019-06-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
62450	2019-06-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
62500	2019-06-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
62550	2019-06-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
62600	2019-01-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
62650	2019-01-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
62700	2019-01-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
62750	2019-01-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
62800	2019-01-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
62850	2019-01-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
62900	2019-01-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
62950	2019-01-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
63000	2019-01-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
63050	2019-01-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
63100	2019-01-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
63150	2019-01-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
63200	2019-01-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
63250	2019-01-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
63300	2019-01-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
63350	2019-01-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
63400	2019-01-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
63450	2019-01-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
63500	2019-01-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
63550	2019-01-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
63600	2019-01-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
63650	2019-01-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
63700	2019-01-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
63750	2019-01-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
63800	2019-01-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
63850	2019-02-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
63900	2019-02-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
63950	2019-02-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
64000	2019-02-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
64050	2019-02-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
64100	2019-02-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
64150	2019-02-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
64200	2019-02-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
64250	2019-02-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
64300	2019-02-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
64350	2019-02-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
64400	2019-02-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
64450	2019-02-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
64500	2019-02-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
64550	2019-02-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
64600	2019-02-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
64650	2019-02-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
64700	2019-02-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
64750	2019-02-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
64800	2019-02-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
64850	2019-03-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
64900	2019-03-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
64950	2019-03-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
65000	2019-03-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
65050	2019-03-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
65100	2019-03-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
65150	2019-03-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
65200	2019-03-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
65250	2019-03-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
65300	2019-03-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
65350	2019-03-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
65400	2019-03-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
65450	2019-03-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
65500	2019-03-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
65550	2019-03-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
65600	2019-03-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
65650	2019-03-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
65700	2019-03-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
65750	2019-03-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
65800	2019-03-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
65850	2019-04-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
65900	2019-04-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
65950	2019-04-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
66000	2019-04-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
66050	2019-04-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
66100	2019-04-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
66150	2019-04-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
66200	2019-04-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
66250	2019-04-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
66300	2019-04-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
66350	2019-04-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
66400	2019-04-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
66450	2019-04-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
66500	2019-04-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
66550	2019-04-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
66600	2019-04-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
66650	2019-04-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
66700	2019-04-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
66750	2019-04-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
66800	2019-04-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
66850	2019-05-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
66900	2019-05-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
66950	2019-05-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
67000	2019-05-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
67050	2019-05-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
67100	2019-05-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
67150	2019-05-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
67200	2019-05-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
67250	2019-05-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
67300	2019-05-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
67350	2019-05-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
67400	2019-05-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
67450	2019-05-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
67500	2019-05-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
67550	2019-05-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
67600	2019-05-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
67650	2019-05-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
67700	2019-05-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
67750	2019-05-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
67800	2019-05-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
67850	2019-05-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
67900	2019-05-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
67950	2019-05-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
68000	2019-05-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
68050	2019-05-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
68100	2019-06-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
68150	2019-06-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
68200	2019-06-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
68250	2019-06-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
68300	2019-06-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
68350	2019-06-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
68400	2019-06-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
68450	2019-06-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
68500	2019-06-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
68550	2019-06-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
68600	2019-06-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
68650	2019-06-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
68700	2019-06-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
68750	2019-06-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
68800	2019-06-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
68850	2019-06-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
68900	2019-06-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
68950	2019-06-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
69000	2019-06-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
69050	2019-06-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
69100	2019-01-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
69150	2019-01-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
69200	2019-01-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
69250	2019-01-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
69300	2019-01-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
69350	2019-01-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
69400	2019-01-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
69450	2019-01-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
69500	2019-01-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
69550	2019-01-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
69600	2019-01-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
69650	2019-01-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
69700	2019-01-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
69750	2019-01-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
69800	2019-01-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
69850	2019-01-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
69900	2019-01-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
69950	2019-01-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
70000	2019-01-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
70050	2019-01-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
70100	2019-01-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
70150	2019-01-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
70200	2019-01-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
70250	2019-01-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
70300	2019-01-31	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
70350	2019-02-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
70400	2019-02-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
70450	2019-02-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
70500	2019-02-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
70550	2019-02-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
70600	2019-02-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
70650	2019-02-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
70700	2019-02-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
70750	2019-02-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
70800	2019-02-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
70850	2019-02-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
70900	2019-02-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
70950	2019-02-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
71000	2019-02-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
71050	2019-02-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
71100	2019-02-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
71150	2019-02-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
71200	2019-02-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
71250	2019-02-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
71300	2019-02-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
71350	2019-03-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
71400	2019-03-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
71450	2019-03-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
71500	2019-03-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
71550	2019-03-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
71600	2019-03-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
71650	2019-03-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
71700	2019-03-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
71750	2019-03-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
71800	2019-03-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
71850	2019-03-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
71900	2019-03-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
71950	2019-03-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
72000	2019-03-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
72050	2019-03-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
72100	2019-04-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
72150	2019-04-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
72200	2019-04-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
72250	2019-04-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
72300	2019-04-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
72350	2019-04-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
72400	2019-04-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
72450	2019-04-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
72500	2019-04-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
72550	2019-04-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
72600	2019-04-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
72650	2019-04-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
72700	2019-04-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
72750	2019-04-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
72800	2019-04-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
72850	2019-04-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
72900	2019-04-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
72950	2019-04-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
73000	2019-04-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
73050	2019-04-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
73100	2019-05-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
73150	2019-05-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
73200	2019-05-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
73250	2019-05-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
73300	2019-05-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
73350	2019-05-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
73400	2019-05-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
73450	2019-05-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
73500	2019-05-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
73550	2019-05-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
73600	2019-05-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
73650	2019-05-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
73700	2019-05-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
73750	2019-05-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
73800	2019-05-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
73850	2019-05-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
73900	2019-05-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
73950	2019-05-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
74000	2019-05-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
74050	2019-05-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
74100	2019-05-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
74150	2019-05-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
74200	2019-05-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
74250	2019-05-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
74300	2019-05-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
74350	2019-06-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
74400	2019-06-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
74450	2019-06-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
74500	2019-06-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
74550	2019-06-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
74600	2019-06-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
74650	2019-06-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
74700	2019-06-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
74750	2019-06-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
74800	2019-06-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
74850	2019-06-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
74900	2019-06-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
74950	2019-06-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
75000	2019-06-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
75050	2019-06-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
75100	2019-06-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
75150	2019-06-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
75200	2019-06-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
75250	2019-06-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
75300	2019-06-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
75350	2019-01-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
75400	2019-01-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
75450	2019-01-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
75500	2019-01-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
75550	2019-01-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
75600	2019-01-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
75650	2019-01-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
75700	2019-01-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
75750	2019-01-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
75800	2019-01-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
75850	2019-01-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
75900	2019-01-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
75950	2019-01-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
76000	2019-01-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
76050	2019-01-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
76100	2019-01-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
76150	2019-01-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
76200	2019-01-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
76250	2019-01-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
76300	2019-01-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
76350	2019-02-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
76400	2019-02-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
76450	2019-02-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
76500	2019-02-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
76550	2019-02-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
76600	2019-02-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
76650	2019-02-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
76700	2019-02-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
76750	2019-02-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
76800	2019-02-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
76850	2019-02-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
76900	2019-02-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
76950	2019-02-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
77000	2019-02-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
77050	2019-02-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
77100	2019-02-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
77150	2019-02-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
77200	2019-02-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
77250	2019-02-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
77300	2019-02-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
77350	2019-03-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
77400	2019-03-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
77450	2019-03-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
77500	2019-03-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
77550	2019-03-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
77600	2019-03-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
77650	2019-03-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
77700	2019-03-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
77750	2019-03-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
77800	2019-03-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
77850	2019-03-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
77900	2019-03-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
77950	2019-03-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
78000	2019-03-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
78050	2019-03-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
78100	2019-03-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
78150	2019-03-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
78200	2019-03-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
78250	2019-03-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
78300	2019-03-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
78350	2019-03-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
78400	2019-03-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
78450	2019-03-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
78500	2019-03-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
78550	2019-03-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
78600	2019-04-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
78650	2019-04-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
78700	2019-04-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
78750	2019-04-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
78800	2019-04-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
78850	2019-04-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
78900	2019-04-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
78950	2019-04-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
79000	2019-04-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
79050	2019-04-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
79100	2019-04-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
79150	2019-04-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
79200	2019-04-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
79250	2019-04-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
79300	2019-04-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
79350	2019-04-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
79400	2019-04-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
79450	2019-04-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
79500	2019-04-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
79550	2019-04-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
79600	2019-05-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
79650	2019-05-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
79700	2019-05-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
79750	2019-05-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
79800	2019-05-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
79850	2019-05-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
79900	2019-05-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
79950	2019-05-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
80000	2019-05-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
80050	2019-05-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
80100	2019-05-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
80150	2019-05-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
80200	2019-05-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
80250	2019-05-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
80300	2019-05-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
80350	2019-05-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
80400	2019-05-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
80450	2019-05-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
80500	2019-05-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
80550	2019-05-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
80600	2019-05-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
80650	2019-05-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
80700	2019-05-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
80750	2019-05-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
80800	2019-05-31	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
80850	2019-06-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
80900	2019-06-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
80950	2019-06-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
81000	2019-06-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
81050	2019-06-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
81100	2019-06-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
81150	2019-06-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
81200	2019-06-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
81250	2019-06-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
81300	2019-06-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
81350	2019-06-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
81400	2019-06-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
81450	2019-06-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
81500	2019-06-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
81550	2019-06-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
81600	2019-06-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16350
81650	2019-06-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16400
81700	2019-06-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16450
81750	2019-06-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16500
81800	2019-06-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16550
81850	2019-01-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
81900	2019-01-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
81950	2019-01-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
82000	2019-01-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
82050	2019-01-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
82100	2019-01-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
82150	2019-01-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
82200	2019-01-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
82250	2019-01-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
82300	2019-01-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
82350	2019-01-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
82400	2019-01-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
82450	2019-01-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
82500	2019-01-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
82550	2019-01-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
82600	2019-02-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
82650	2019-02-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
82700	2019-02-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
82750	2019-02-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
82800	2019-02-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
82850	2019-02-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
82900	2019-02-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
82950	2019-02-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
83000	2019-02-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
83050	2019-02-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
83100	2019-02-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
83150	2019-02-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
83200	2019-02-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
83250	2019-02-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
83300	2019-02-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
83350	2019-02-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
83400	2019-02-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
83450	2019-02-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
83500	2019-02-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
83550	2019-02-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
83600	2019-03-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
83650	2019-03-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
83700	2019-03-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
83750	2019-03-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
83800	2019-03-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
83850	2019-03-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
83900	2019-03-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
83950	2019-03-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
84000	2019-03-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
84050	2019-03-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
84100	2019-03-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
84150	2019-03-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
84200	2019-03-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
84250	2019-03-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
84300	2019-03-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
84350	2019-04-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
84400	2019-04-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
84450	2019-04-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
84500	2019-04-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
84550	2019-04-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
84600	2019-04-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
84650	2019-04-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
84700	2019-04-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
84750	2019-04-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
84800	2019-04-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
84850	2019-04-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
84900	2019-04-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
84950	2019-04-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
85000	2019-04-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
85050	2019-04-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
85100	2019-04-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
85150	2019-04-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
85200	2019-04-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
85250	2019-04-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
85300	2019-04-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
85350	2019-04-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
85400	2019-04-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
85450	2019-04-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
85500	2019-04-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
85550	2019-04-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
85600	2019-05-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
85650	2019-05-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
85700	2019-05-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
85750	2019-05-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
85800	2019-05-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
85850	2019-05-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
85900	2019-05-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
85950	2019-05-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
86000	2019-05-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
86050	2019-05-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
86100	2019-05-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
86150	2019-05-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
86200	2019-05-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
86250	2019-05-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
86300	2019-05-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
86350	2019-05-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
86400	2019-05-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
86450	2019-05-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
86500	2019-05-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
86550	2019-05-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
86600	2019-06-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
86650	2019-06-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
86700	2019-06-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
86750	2019-06-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
86800	2019-06-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
86850	2019-06-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
86900	2019-06-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
86950	2019-06-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
87000	2019-06-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
87050	2019-06-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
87100	2019-06-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
87150	2019-06-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
87200	2019-06-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
87250	2019-06-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
87300	2019-06-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
87350	2019-06-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
87400	2019-06-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
87450	2019-06-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
87500	2019-06-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
87550	2019-06-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
87600	2019-01-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
87650	2019-01-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
87700	2019-01-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
87750	2019-01-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
87800	2019-01-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
87850	2019-01-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
87900	2019-01-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
87950	2019-01-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
88000	2019-01-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
88050	2019-01-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
88100	2019-01-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
88150	2019-01-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
88200	2019-01-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
88250	2019-01-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
88300	2019-01-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
88350	2019-02-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
88400	2019-02-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
88450	2019-02-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
88500	2019-02-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
88550	2019-02-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
88600	2019-02-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
88650	2019-02-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
88700	2019-02-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
88750	2019-02-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
88800	2019-02-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
88850	2019-02-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
88900	2019-02-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
88950	2019-02-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
89000	2019-02-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
89050	2019-02-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
89100	2019-02-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
89150	2019-02-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
89200	2019-02-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
89250	2019-02-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
89300	2019-02-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
89350	2019-03-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
89400	2019-03-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
89450	2019-03-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
89500	2019-03-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
89550	2019-03-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
89600	2019-03-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
89650	2019-03-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
89700	2019-03-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
89750	2019-03-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
89800	2019-03-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
89850	2019-03-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
89900	2019-03-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
89950	2019-03-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
90000	2019-03-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
90050	2019-03-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
90100	2019-03-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
90150	2019-03-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
90200	2019-03-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
90250	2019-03-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
90300	2019-03-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
90350	2019-04-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
90400	2019-04-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
90450	2019-04-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
90500	2019-04-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
90550	2019-04-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
90600	2019-04-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
90650	2019-04-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
90700	2019-04-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
90750	2019-04-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
90800	2019-04-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
90850	2019-04-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
90900	2019-04-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
90950	2019-04-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
91000	2019-04-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
91050	2019-04-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
91100	2019-04-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
91150	2019-04-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
91200	2019-04-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
91250	2019-04-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
91300	2019-04-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
91350	2019-04-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
91400	2019-04-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
91450	2019-04-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
91500	2019-04-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
91550	2019-04-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
91600	2019-05-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
91650	2019-05-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
91700	2019-05-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
91750	2019-05-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
91800	2019-05-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
91850	2019-05-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
91900	2019-05-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
91950	2019-05-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
92000	2019-05-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
92050	2019-05-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
92100	2019-05-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
92150	2019-05-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
92200	2019-05-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
92250	2019-05-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
92300	2019-05-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
92350	2019-05-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
92400	2019-05-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
92450	2019-05-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
92500	2019-05-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
92550	2019-05-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
92600	2019-06-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
92650	2019-06-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
92700	2019-06-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
92750	2019-06-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
92800	2019-06-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
92850	2019-06-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
92900	2019-06-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
92950	2019-06-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
93000	2019-06-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
93050	2019-06-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
93100	2019-06-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
93150	2019-06-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
93200	2019-06-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
93250	2019-06-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
93300	2019-06-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
93350	2019-06-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
93400	2019-06-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
93450	2019-06-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
93500	2019-06-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
93550	2019-06-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
93600	2019-01-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
93650	2019-01-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
93700	2019-01-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
93750	2019-01-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
93800	2019-01-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
93850	2019-01-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
93900	2019-01-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
93950	2019-01-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
94000	2019-01-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
94050	2019-01-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
94100	2019-01-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
94150	2019-01-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
94200	2019-01-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
94250	2019-01-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
94300	2019-01-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
94350	2019-01-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
94400	2019-01-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
94450	2019-01-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
94500	2019-01-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
94550	2019-01-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
94600	2019-01-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
94650	2019-01-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
94700	2019-01-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
94750	2019-01-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
94800	2019-01-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
94850	2019-02-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
94900	2019-02-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
94950	2019-02-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
95000	2019-02-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
95050	2019-02-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
95100	2019-02-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
95150	2019-02-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
95200	2019-02-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
95250	2019-02-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
95300	2019-02-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
95350	2019-02-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
95400	2019-02-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
95450	2019-02-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
95500	2019-02-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
95550	2019-02-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
95600	2019-02-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
95650	2019-02-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
95700	2019-02-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
95750	2019-02-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
95800	2019-02-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
95850	2019-03-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
95900	2019-03-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
95950	2019-03-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
96000	2019-03-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
96050	2019-03-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
96100	2019-03-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
96150	2019-03-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
96200	2019-03-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
96250	2019-03-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
96300	2019-03-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
96350	2019-03-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
96400	2019-03-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
96450	2019-03-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
96500	2019-03-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
96550	2019-03-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
96600	2019-03-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
96650	2019-03-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
96700	2019-03-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
96750	2019-03-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
96800	2019-03-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
96850	2019-04-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
96900	2019-04-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
96950	2019-04-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
97000	2019-04-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
97050	2019-04-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
97100	2019-04-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
97150	2019-04-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
97200	2019-04-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
97250	2019-04-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
97300	2019-04-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
97350	2019-04-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
97400	2019-04-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
97450	2019-04-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
97500	2019-04-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
97550	2019-04-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
97600	2019-04-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
97650	2019-04-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
97700	2019-04-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
97750	2019-04-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
97800	2019-04-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
97850	2019-05-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
97900	2019-05-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
97950	2019-05-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
98000	2019-05-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
98050	2019-05-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
98100	2019-05-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
98150	2019-05-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
98200	2019-05-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
98250	2019-05-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
98300	2019-05-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
98350	2019-05-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
98400	2019-05-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
98450	2019-05-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
98500	2019-05-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
98550	2019-05-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
98600	2019-05-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
98650	2019-05-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
98700	2019-05-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
98750	2019-05-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
98800	2019-05-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
98850	2019-05-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
98900	2019-05-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
98950	2019-05-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
99000	2019-05-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
99050	2019-05-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
99100	2019-06-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
99150	2019-06-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
99200	2019-06-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
99250	2019-06-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
99300	2019-06-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
99350	2019-06-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
99400	2019-06-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
99450	2019-06-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
99500	2019-06-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
99550	2019-06-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
99600	2019-06-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
99650	2019-06-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
99700	2019-06-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
99750	2019-06-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
99800	2019-06-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
99850	2019-06-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
99900	2019-06-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
99950	2019-06-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
100000	2019-06-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
100050	2019-06-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
100100	2019-01-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
100150	2019-01-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
100200	2019-01-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
100250	2019-01-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
100300	2019-01-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
100350	2019-01-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
100400	2019-01-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
100450	2019-01-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
100500	2019-01-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
100550	2019-01-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
100600	2019-01-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
100650	2019-01-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
100700	2019-01-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
100750	2019-01-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
100800	2019-01-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
100850	2019-01-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
100900	2019-01-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
100950	2019-01-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
101000	2019-01-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
101050	2019-01-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
101100	2019-01-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
101150	2019-01-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
101200	2019-01-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
101250	2019-01-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
101300	2019-01-31	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
101350	2019-02-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
101400	2019-02-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
101450	2019-02-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
101500	2019-02-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
101550	2019-02-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
101600	2019-02-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
101650	2019-02-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
101700	2019-02-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
101750	2019-02-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
101800	2019-02-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
101850	2019-02-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
101900	2019-02-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
101950	2019-02-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
102000	2019-02-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
102050	2019-02-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
102100	2019-02-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
102150	2019-02-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
102200	2019-02-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
102250	2019-02-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
102300	2019-02-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
102350	2019-03-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
102400	2019-03-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
102450	2019-03-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
102500	2019-03-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
102550	2019-03-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
102600	2019-03-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
102650	2019-03-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
102700	2019-03-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
102750	2019-03-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
102800	2019-03-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
102850	2019-03-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
102900	2019-03-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
102950	2019-03-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
103000	2019-03-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
103050	2019-03-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
103100	2019-04-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
103150	2019-04-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
103200	2019-04-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
103250	2019-04-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
103300	2019-04-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
103350	2019-04-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
103400	2019-04-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
103450	2019-04-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
103500	2019-04-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
103550	2019-04-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
103600	2019-04-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
103650	2019-04-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
103700	2019-04-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
103750	2019-04-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
103800	2019-04-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
103850	2019-04-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
103900	2019-04-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
103950	2019-04-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
104000	2019-04-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
104050	2019-04-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
104100	2019-05-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
104150	2019-05-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
104200	2019-05-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
104250	2019-05-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
104300	2019-05-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
104350	2019-05-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
104400	2019-05-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
104450	2019-05-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
104500	2019-05-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
104550	2019-05-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
104600	2019-05-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
104650	2019-05-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
104700	2019-05-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
104750	2019-05-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
104800	2019-05-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
104850	2019-05-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
104900	2019-05-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
104950	2019-05-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
105000	2019-05-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
105050	2019-05-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
105100	2019-05-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
105150	2019-05-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
105200	2019-05-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
105250	2019-05-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
105300	2019-05-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
105350	2019-06-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
105400	2019-06-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
105450	2019-06-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
105500	2019-06-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
105550	2019-06-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
105600	2019-06-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
105650	2019-06-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
105700	2019-06-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
105750	2019-06-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
105800	2019-06-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
105850	2019-06-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
105900	2019-06-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
105950	2019-06-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
106000	2019-06-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
106050	2019-06-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
106100	2019-06-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
106150	2019-06-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
106200	2019-06-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
106250	2019-06-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
106300	2019-06-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
106350	2019-01-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
106400	2019-01-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
106450	2019-01-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
106500	2019-01-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
106550	2019-01-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
106600	2019-01-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
106650	2019-01-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
106700	2019-01-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
106750	2019-01-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
106800	2019-01-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
106850	2019-01-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
106900	2019-01-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
106950	2019-01-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
107000	2019-01-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
107050	2019-01-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
107100	2019-01-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
107150	2019-01-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
107200	2019-01-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
107250	2019-01-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
107300	2019-01-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
107350	2019-02-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
107400	2019-02-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
107450	2019-02-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
107500	2019-02-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
107550	2019-02-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
107600	2019-02-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
107650	2019-02-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
107700	2019-02-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
107750	2019-02-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
107800	2019-02-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
107850	2019-02-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
107900	2019-02-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
107950	2019-02-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
108000	2019-02-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
108050	2019-02-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
108100	2019-02-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
108150	2019-02-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
108200	2019-02-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
108250	2019-02-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
108300	2019-02-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
108350	2019-03-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
108400	2019-03-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
108450	2019-03-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
108500	2019-03-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
108550	2019-03-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
108600	2019-03-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
108650	2019-03-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
108700	2019-03-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
108750	2019-03-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
108800	2019-03-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
108850	2019-03-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
108900	2019-03-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
108950	2019-03-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
109000	2019-03-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
109050	2019-03-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
109100	2019-03-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
109150	2019-03-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
109200	2019-03-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
109250	2019-03-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
109300	2019-03-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
109350	2019-03-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
109400	2019-03-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
109450	2019-03-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
109500	2019-03-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
109550	2019-03-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
109600	2019-04-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
109650	2019-04-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
109700	2019-04-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
109750	2019-04-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
109800	2019-04-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
109850	2019-04-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
109900	2019-04-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
109950	2019-04-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
110000	2019-04-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
110050	2019-04-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
110100	2019-04-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
110150	2019-04-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
110200	2019-04-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
110250	2019-04-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
110300	2019-04-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
110350	2019-04-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
110400	2019-04-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
110450	2019-04-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
110500	2019-04-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
110550	2019-04-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
110600	2019-05-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
110650	2019-05-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
110700	2019-05-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
110750	2019-05-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
110800	2019-05-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
110850	2019-05-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
110900	2019-05-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
110950	2019-05-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
111000	2019-05-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
111050	2019-05-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
111100	2019-05-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
111150	2019-05-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
111200	2019-05-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
111250	2019-05-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
111300	2019-05-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
111350	2019-05-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
111400	2019-05-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
111450	2019-05-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
111500	2019-05-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
111550	2019-05-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
111600	2019-05-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
111650	2019-05-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
111700	2019-05-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
111750	2019-05-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
111800	2019-05-31	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
111850	2019-06-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
111900	2019-06-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
111950	2019-06-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
112000	2019-06-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
112050	2019-06-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
112100	2019-06-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
112150	2019-06-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
112200	2019-06-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
112250	2019-06-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
112300	2019-06-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
112350	2019-06-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
112400	2019-06-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
112450	2019-06-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
112500	2019-06-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
112550	2019-06-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
112600	2019-06-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16600
112650	2019-06-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16650
112700	2019-06-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16700
112750	2019-06-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	16750
112800	2019-06-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	16800
112850	2019-01-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
112900	2019-01-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
112950	2019-01-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
113000	2019-01-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
113050	2019-01-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
113100	2019-01-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
113150	2019-01-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
113200	2019-01-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
113250	2019-01-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
113300	2019-01-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
113350	2019-01-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
113400	2019-01-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
113450	2019-01-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
113500	2019-01-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
113550	2019-01-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
113600	2019-02-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
113650	2019-02-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
113700	2019-02-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
113750	2019-02-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
113800	2019-02-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
113850	2019-02-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
113900	2019-02-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
113950	2019-02-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
114000	2019-02-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
114050	2019-02-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
114100	2019-02-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
114150	2019-02-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
114200	2019-02-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
114250	2019-02-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
114300	2019-02-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
114350	2019-02-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
114400	2019-02-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
114450	2019-02-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
114500	2019-02-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
114550	2019-02-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
114600	2019-03-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
114650	2019-03-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
114700	2019-03-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
114750	2019-03-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
114800	2019-03-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
114850	2019-03-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
114900	2019-03-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
114950	2019-03-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
115000	2019-03-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
115050	2019-03-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
115100	2019-03-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
115150	2019-03-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
115200	2019-03-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
115250	2019-03-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
115300	2019-03-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
115350	2019-04-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
115400	2019-04-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
115450	2019-04-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
115500	2019-04-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
115550	2019-04-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
115600	2019-04-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
115650	2019-04-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
115700	2019-04-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
115750	2019-04-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
115800	2019-04-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
115850	2019-04-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
115900	2019-04-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
115950	2019-04-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
116000	2019-04-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
116050	2019-04-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
116100	2019-04-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
116150	2019-04-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
116200	2019-04-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
116250	2019-04-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
116300	2019-04-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
116350	2019-04-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
116400	2019-04-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
116450	2019-04-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
116500	2019-04-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
116550	2019-04-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
116600	2019-05-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
116650	2019-05-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
116700	2019-05-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
116750	2019-05-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
116800	2019-05-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
116850	2019-05-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
116900	2019-05-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
116950	2019-05-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
117000	2019-05-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
117050	2019-05-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
117100	2019-05-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
117150	2019-05-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
117200	2019-05-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
117250	2019-05-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
117300	2019-05-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
117350	2019-05-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
117400	2019-05-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
117450	2019-05-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
117500	2019-05-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
117550	2019-05-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
117600	2019-06-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
117650	2019-06-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
117700	2019-06-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
117750	2019-06-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
117800	2019-06-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
117850	2019-06-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
117900	2019-06-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
117950	2019-06-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
118000	2019-06-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
118050	2019-06-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
118100	2019-06-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
118150	2019-06-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
118200	2019-06-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
118250	2019-06-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
118300	2019-06-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
118350	2019-06-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
118400	2019-06-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
118450	2019-06-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
118500	2019-06-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
118550	2019-06-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
118600	2019-01-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
118650	2019-01-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
118700	2019-01-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
118750	2019-01-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
118800	2019-01-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
118850	2019-01-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
118900	2019-01-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
118950	2019-01-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
119000	2019-01-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
119050	2019-01-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
119100	2019-01-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
119150	2019-01-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
119200	2019-01-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
119250	2019-01-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
119300	2019-01-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
119350	2019-02-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
119400	2019-02-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
119450	2019-02-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
119500	2019-02-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
119550	2019-02-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
119600	2019-02-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
119650	2019-02-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
119700	2019-02-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
119750	2019-02-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
119800	2019-02-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
119850	2019-02-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
119900	2019-02-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
119950	2019-02-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
120000	2019-02-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
120050	2019-02-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
120100	2019-02-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
120150	2019-02-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
120200	2019-02-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
120250	2019-02-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
120300	2019-02-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
120350	2019-03-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
120400	2019-03-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
120450	2019-03-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
120500	2019-03-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
120550	2019-03-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
120600	2019-03-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
120650	2019-03-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
120700	2019-03-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
120750	2019-03-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
120800	2019-03-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
120850	2019-03-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
120900	2019-03-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
120950	2019-03-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
121000	2019-03-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
121050	2019-03-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
121100	2019-03-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
121150	2019-03-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
121200	2019-03-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
121250	2019-03-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
121300	2019-03-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
121350	2019-04-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
121400	2019-04-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
121450	2019-04-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
121500	2019-04-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
121550	2019-04-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
121600	2019-04-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
121650	2019-04-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
121700	2019-04-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
121750	2019-04-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
121800	2019-04-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
121850	2019-04-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
121900	2019-04-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
121950	2019-04-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
122000	2019-04-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
122050	2019-04-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
122100	2019-04-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
122150	2019-04-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
122200	2019-04-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
122250	2019-04-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
122300	2019-04-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
122350	2019-04-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
122400	2019-04-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
122450	2019-04-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
122500	2019-04-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
122550	2019-04-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
122600	2019-05-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
122650	2019-05-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
122700	2019-05-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
122750	2019-05-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
122800	2019-05-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
122850	2019-05-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
122900	2019-05-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
122950	2019-05-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
123000	2019-05-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
123050	2019-05-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
123100	2019-05-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
123150	2019-05-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
123200	2019-05-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
123250	2019-05-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
123300	2019-05-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
123350	2019-05-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
123400	2019-05-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
123450	2019-05-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
123500	2019-05-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
123550	2019-05-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
123600	2019-06-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
123650	2019-06-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
123700	2019-06-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
123750	2019-06-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
123800	2019-06-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
123850	2019-06-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
123900	2019-06-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
123950	2019-06-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
124000	2019-06-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
124050	2019-06-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
124100	2019-06-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
124150	2019-06-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
124200	2019-06-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
124250	2019-06-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
124300	2019-06-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
124350	2019-06-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
124400	2019-06-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
124450	2019-06-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
124500	2019-06-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
124550	2019-06-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
124600	2019-01-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
124650	2019-01-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
124700	2019-01-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
124750	2019-01-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
124800	2019-01-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
124850	2019-01-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
124900	2019-01-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
124950	2019-01-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
125000	2019-01-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
125050	2019-01-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
125100	2019-01-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
125150	2019-01-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
125200	2019-01-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
125250	2019-01-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
125300	2019-01-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
125350	2019-01-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
125400	2019-01-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
125450	2019-01-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
125500	2019-01-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
125550	2019-01-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
125600	2019-01-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
125650	2019-01-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
125700	2019-01-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
125750	2019-01-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
125800	2019-01-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
125850	2019-02-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
125900	2019-02-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
125950	2019-02-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
126000	2019-02-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
126050	2019-02-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
126100	2019-02-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
126150	2019-02-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
126200	2019-02-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
126250	2019-02-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
126300	2019-02-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
126350	2019-02-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
126400	2019-02-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
126450	2019-02-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
126500	2019-02-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
126550	2019-02-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
126600	2019-02-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
126650	2019-02-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
126700	2019-02-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
126750	2019-02-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
126800	2019-02-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
126850	2019-03-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
126900	2019-03-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
126950	2019-03-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
127000	2019-03-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
127050	2019-03-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
127100	2019-03-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
127150	2019-03-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
127200	2019-03-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
127250	2019-03-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
127300	2019-03-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
127350	2019-03-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
127400	2019-03-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
127450	2019-03-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
127500	2019-03-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
127550	2019-03-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
127600	2019-03-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
127650	2019-03-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
127700	2019-03-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
127750	2019-03-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
127800	2019-03-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
127850	2019-04-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
127900	2019-04-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
127950	2019-04-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
128000	2019-04-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
128050	2019-04-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
128100	2019-04-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
128150	2019-04-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
128200	2019-04-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
128250	2019-04-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
128300	2019-04-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
128350	2019-04-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
128400	2019-04-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
128450	2019-04-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
128500	2019-04-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
128550	2019-04-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
128600	2019-04-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
128650	2019-04-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
128700	2019-04-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
128750	2019-04-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
128800	2019-04-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
128850	2019-05-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
128900	2019-05-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
128950	2019-05-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
129000	2019-05-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
129050	2019-05-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
129100	2019-05-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
129150	2019-05-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
129200	2019-05-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
129250	2019-05-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
129300	2019-05-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
129350	2019-05-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
129400	2019-05-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
129450	2019-05-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
129500	2019-05-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
129550	2019-05-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
129600	2019-05-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
129650	2019-05-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
129700	2019-05-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
129750	2019-05-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
129800	2019-05-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
129850	2019-05-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
129900	2019-05-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
129950	2019-05-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
130000	2019-05-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
130050	2019-05-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
130100	2019-06-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
130150	2019-06-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
130200	2019-06-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
130250	2019-06-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
130300	2019-06-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
130350	2019-06-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
130400	2019-06-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
130450	2019-06-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
130500	2019-06-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
130550	2019-06-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
130600	2019-06-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
130650	2019-06-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
130700	2019-06-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
130750	2019-06-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
130800	2019-06-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
130850	2019-06-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
130900	2019-06-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
130950	2019-06-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
131000	2019-06-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
131050	2019-06-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
131100	2019-01-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
131150	2019-01-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
131200	2019-01-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
131250	2019-01-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
131300	2019-01-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
131350	2019-01-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
131400	2019-01-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
131450	2019-01-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
131500	2019-01-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
131550	2019-01-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
131600	2019-01-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
131650	2019-01-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
131700	2019-01-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
131750	2019-01-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
131800	2019-01-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
131850	2019-01-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
131900	2019-01-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
131950	2019-01-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
132000	2019-01-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
132050	2019-01-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
132100	2019-01-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
132150	2019-01-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
132200	2019-01-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
132250	2019-01-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
132300	2019-01-31	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
132350	2019-02-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
132400	2019-02-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
132450	2019-02-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
132500	2019-02-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
132550	2019-02-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
132600	2019-02-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
132650	2019-02-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
132700	2019-02-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
132750	2019-02-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
132800	2019-02-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
132850	2019-02-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
132900	2019-02-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
132950	2019-02-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
133000	2019-02-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
133050	2019-02-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
133100	2019-02-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
133150	2019-02-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
133200	2019-02-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
133250	2019-02-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
133300	2019-02-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
133350	2019-03-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
133400	2019-03-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
133450	2019-03-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
133500	2019-03-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
133550	2019-03-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
133600	2019-03-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
133650	2019-03-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
133700	2019-03-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
133750	2019-03-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
133800	2019-03-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
133850	2019-03-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
133900	2019-03-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
133950	2019-03-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
134000	2019-03-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
134050	2019-03-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
134100	2019-04-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
134150	2019-04-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
134200	2019-04-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
134250	2019-04-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
134300	2019-04-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
134350	2019-04-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
134400	2019-04-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
134450	2019-04-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
134500	2019-04-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
134550	2019-04-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
134600	2019-04-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
134650	2019-04-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
134700	2019-04-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
134750	2019-04-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
134800	2019-04-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
134850	2019-04-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
134900	2019-04-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
134950	2019-04-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
135000	2019-04-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
135050	2019-04-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
135100	2019-05-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
135150	2019-05-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
135200	2019-05-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
135250	2019-05-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
135300	2019-05-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
135350	2019-05-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
135400	2019-05-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
135450	2019-05-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
135500	2019-05-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
135550	2019-05-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
135600	2019-05-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
135650	2019-05-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
135700	2019-05-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
135750	2019-05-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
135800	2019-05-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
135850	2019-05-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
135900	2019-05-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
135950	2019-05-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
136000	2019-05-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
136050	2019-05-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
136100	2019-05-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
136150	2019-05-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
136200	2019-05-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
136250	2019-05-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
136300	2019-05-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
136350	2019-06-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
136400	2019-06-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
136450	2019-06-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
136500	2019-06-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
136550	2019-06-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
136600	2019-06-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
136650	2019-06-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
136700	2019-06-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
136750	2019-06-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
136800	2019-06-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
136850	2019-06-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
136900	2019-06-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
136950	2019-06-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
137000	2019-06-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
137050	2019-06-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
137100	2019-06-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
137150	2019-06-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
137200	2019-06-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
137250	2019-06-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
137300	2019-06-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
137350	2019-01-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
137400	2019-01-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
137450	2019-01-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
137500	2019-01-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
137550	2019-01-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
137600	2019-01-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
137650	2019-01-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
137700	2019-01-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
137750	2019-01-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
137800	2019-01-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
137850	2019-01-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
137900	2019-01-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
137950	2019-01-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
138000	2019-01-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
138050	2019-01-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
138100	2019-01-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
138150	2019-01-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
138200	2019-01-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
138250	2019-01-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
138300	2019-01-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
138350	2019-02-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
138400	2019-02-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
138450	2019-02-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
138500	2019-02-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
138550	2019-02-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
138600	2019-02-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
138650	2019-02-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
138700	2019-02-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
138750	2019-02-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
138800	2019-02-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
138850	2019-02-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
138900	2019-02-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
138950	2019-02-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
139000	2019-02-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
139050	2019-02-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
139100	2019-02-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
139150	2019-02-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
139200	2019-02-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
139250	2019-02-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
139300	2019-02-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
139350	2019-03-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
139400	2019-03-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
139450	2019-03-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
139500	2019-03-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
139550	2019-03-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
139600	2019-03-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
139650	2019-03-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
139700	2019-03-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
139750	2019-03-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
139800	2019-03-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
139850	2019-03-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
139900	2019-03-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
139950	2019-03-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
140000	2019-03-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
140050	2019-03-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
140100	2019-03-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
140150	2019-03-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
140200	2019-03-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
140250	2019-03-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
140300	2019-03-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
140350	2019-03-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
140400	2019-03-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
140450	2019-03-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
140500	2019-03-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
140550	2019-03-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
140600	2019-04-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
140650	2019-04-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
140700	2019-04-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
140750	2019-04-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
140800	2019-04-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
140850	2019-04-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
140900	2019-04-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
140950	2019-04-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
141000	2019-04-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
141050	2019-04-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
141100	2019-04-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
141150	2019-04-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
141200	2019-04-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
141250	2019-04-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
141300	2019-04-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
141350	2019-04-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
141400	2019-04-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
141450	2019-04-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
141500	2019-04-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
141550	2019-04-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
141600	2019-05-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
141650	2019-05-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
141700	2019-05-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
141750	2019-05-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
141800	2019-05-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
141850	2019-05-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
141900	2019-05-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
141950	2019-05-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
142000	2019-05-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
142050	2019-05-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
142100	2019-05-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
142150	2019-05-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
142200	2019-05-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
142250	2019-05-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
142300	2019-05-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
142350	2019-05-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
142400	2019-05-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
142450	2019-05-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
142500	2019-05-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
142550	2019-05-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
142600	2019-05-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
142650	2019-05-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
142700	2019-05-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
142750	2019-05-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
142800	2019-05-31	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
142850	2019-06-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
142900	2019-06-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
142950	2019-06-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
143000	2019-06-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
143050	2019-06-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
143100	2019-06-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
143150	2019-06-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
143200	2019-06-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
143250	2019-06-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
143300	2019-06-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
143350	2019-06-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
143400	2019-06-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
143450	2019-06-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
143500	2019-06-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
143550	2019-06-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
143600	2019-06-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	16850
143650	2019-06-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	16900
143700	2019-06-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	16950
143750	2019-06-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17000
143800	2019-06-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	17050
143850	2019-01-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
143900	2019-01-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
143950	2019-01-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
144000	2019-01-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
144050	2019-01-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
144100	2019-01-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
144150	2019-01-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
144200	2019-01-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
144250	2019-01-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
144300	2019-01-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
144350	2019-01-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
144400	2019-01-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
144450	2019-02-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
144500	2019-02-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
144550	2019-02-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
144600	2019-02-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
144650	2019-02-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
144700	2019-02-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
144750	2019-02-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
144800	2019-02-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
144850	2019-02-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
144900	2019-02-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
144950	2019-02-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
145000	2019-02-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
145050	2019-02-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
145100	2019-02-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
145150	2019-02-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
145200	2019-02-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
145250	2019-03-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
145300	2019-03-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
145350	2019-03-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
145400	2019-03-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
145450	2019-03-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
145500	2019-03-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
145550	2019-03-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
145600	2019-03-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
145650	2019-03-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
145700	2019-03-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
145750	2019-03-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
145800	2019-03-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
145850	2019-04-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
145900	2019-04-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
145950	2019-04-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
146000	2019-04-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
146050	2019-04-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
146100	2019-04-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
146150	2019-04-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
146200	2019-04-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
146250	2019-04-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
146300	2019-04-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
146350	2019-04-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
146400	2019-04-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
146450	2019-04-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
146500	2019-04-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
146550	2019-04-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
146600	2019-04-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
146650	2019-04-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
146700	2019-04-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
146750	2019-04-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
146800	2019-04-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
146850	2019-05-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
146900	2019-05-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
146950	2019-05-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
147000	2019-05-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
147050	2019-05-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
147100	2019-05-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
147150	2019-05-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
147200	2019-05-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
147250	2019-05-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
147300	2019-05-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
147350	2019-05-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
147400	2019-05-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
147450	2019-05-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
147500	2019-05-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
147550	2019-05-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
147600	2019-05-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
147650	2019-06-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
147700	2019-06-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
147750	2019-06-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
147800	2019-06-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
147850	2019-06-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
147900	2019-06-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
147950	2019-06-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
148000	2019-06-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
148050	2019-06-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
148100	2019-06-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
148150	2019-06-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
148200	2019-06-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
148250	2019-06-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
148300	2019-06-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
148350	2019-06-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
148400	2019-06-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
148450	2019-01-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
148500	2019-01-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
148550	2019-01-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
148600	2019-01-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
148650	2019-01-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
148700	2019-01-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
148750	2019-01-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
148800	2019-01-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
148850	2019-01-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
148900	2019-01-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
148950	2019-01-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
149000	2019-01-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
149050	2019-02-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
149100	2019-02-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
149150	2019-02-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
149200	2019-02-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
149250	2019-02-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
149300	2019-02-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
149350	2019-02-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
149400	2019-02-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
149450	2019-02-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
149500	2019-02-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
149550	2019-02-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
149600	2019-02-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
149650	2019-02-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
149700	2019-02-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
149750	2019-02-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
149800	2019-02-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
149850	2019-03-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
149900	2019-03-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
149950	2019-03-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
150000	2019-03-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
150050	2019-03-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
150100	2019-03-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
150150	2019-03-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
150200	2019-03-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
150250	2019-03-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
150300	2019-03-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
150350	2019-03-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
150400	2019-03-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
150450	2019-03-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
150500	2019-03-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
150550	2019-03-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
150600	2019-03-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
150650	2019-04-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
150700	2019-04-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
150750	2019-04-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
150800	2019-04-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
150850	2019-04-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
150900	2019-04-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
150950	2019-04-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
151000	2019-04-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
151050	2019-04-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
151100	2019-04-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
151150	2019-04-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
151200	2019-04-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
151250	2019-04-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
151300	2019-04-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
151350	2019-04-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
151400	2019-04-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
151450	2019-04-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
151500	2019-04-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
151550	2019-04-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
151600	2019-04-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
151650	2019-05-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
151700	2019-05-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
151750	2019-05-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
151800	2019-05-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
151850	2019-05-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
151900	2019-05-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
151950	2019-05-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
152000	2019-05-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
152050	2019-05-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
152100	2019-05-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
152150	2019-05-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
152200	2019-05-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
152250	2019-05-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
152300	2019-05-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
152350	2019-05-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
152400	2019-05-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
152450	2019-06-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
152500	2019-06-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
152550	2019-06-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
152600	2019-06-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
152650	2019-06-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
152700	2019-06-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
152750	2019-06-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
152800	2019-06-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
152850	2019-06-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
152900	2019-06-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
152950	2019-06-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
153000	2019-06-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
153050	2019-06-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
153100	2019-06-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
153150	2019-06-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
153200	2019-06-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
153250	2019-01-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
153300	2019-01-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
153350	2019-01-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
153400	2019-01-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
153450	2019-01-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
153500	2019-01-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
153550	2019-01-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
153600	2019-01-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
153650	2019-01-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
153700	2019-01-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
153750	2019-01-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
153800	2019-01-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
153850	2019-01-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
153900	2019-01-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
153950	2019-01-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
154000	2019-01-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
154050	2019-01-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
154100	2019-01-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
154150	2019-01-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
154200	2019-01-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
154250	2019-02-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
154300	2019-02-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
154350	2019-02-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
154400	2019-02-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
154450	2019-02-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
154500	2019-02-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
154550	2019-02-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
154600	2019-02-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
154650	2019-02-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
154700	2019-02-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
154750	2019-02-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
154800	2019-02-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
154850	2019-02-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
154900	2019-02-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
154950	2019-02-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
155000	2019-02-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
155050	2019-03-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
155100	2019-03-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
155150	2019-03-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
155200	2019-03-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
155250	2019-03-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
155300	2019-03-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
155350	2019-03-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
155400	2019-03-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
155450	2019-03-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
155500	2019-03-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
155550	2019-03-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
155600	2019-03-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
155650	2019-03-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
155700	2019-03-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
155750	2019-03-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
155800	2019-03-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
155850	2019-04-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
155900	2019-04-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
155950	2019-04-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
156000	2019-04-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
156050	2019-04-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
156100	2019-04-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
156150	2019-04-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
156200	2019-04-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
156250	2019-04-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
156300	2019-04-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
156350	2019-04-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
156400	2019-04-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
156450	2019-04-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
156500	2019-04-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
156550	2019-04-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
156600	2019-04-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
156650	2019-05-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
156700	2019-05-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
156750	2019-05-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
156800	2019-05-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
156850	2019-05-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
156900	2019-05-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
156950	2019-05-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
157000	2019-05-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
157050	2019-05-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
157100	2019-05-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
157150	2019-05-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
157200	2019-05-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
157250	2019-05-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
157300	2019-05-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
157350	2019-05-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
157400	2019-05-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
157450	2019-05-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
157500	2019-05-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
157550	2019-05-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
157600	2019-05-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
157650	2019-06-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
157700	2019-06-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
157750	2019-06-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
157800	2019-06-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
157850	2019-06-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
157900	2019-06-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
157950	2019-06-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
158000	2019-06-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
158050	2019-06-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
158100	2019-06-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
158150	2019-06-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
158200	2019-06-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
158250	2019-06-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
158300	2019-06-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
158350	2019-06-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
158400	2019-06-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
158450	2019-01-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
158500	2019-01-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
158550	2019-01-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
158600	2019-01-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
158650	2019-01-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
158700	2019-01-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
158750	2019-01-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
158800	2019-01-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
158850	2019-01-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
158900	2019-01-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
158950	2019-01-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
159000	2019-01-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
159050	2019-01-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
159100	2019-01-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
159150	2019-01-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
159200	2019-01-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
159250	2019-01-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
159300	2019-01-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
159350	2019-01-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
159400	2019-01-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
159450	2019-02-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
159500	2019-02-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
159550	2019-02-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
159600	2019-02-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
159650	2019-02-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
159700	2019-02-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
159750	2019-02-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
159800	2019-02-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
159850	2019-02-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
159900	2019-02-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
159950	2019-02-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
160000	2019-02-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
160050	2019-02-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
160100	2019-02-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
160150	2019-02-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
160200	2019-02-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
160250	2019-03-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
160300	2019-03-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
160350	2019-03-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
160400	2019-03-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
160450	2019-03-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
160500	2019-03-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
160550	2019-03-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
160600	2019-03-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
160650	2019-03-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
160700	2019-03-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
160750	2019-03-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
160800	2019-03-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
160850	2019-04-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
160900	2019-04-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
160950	2019-04-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
161000	2019-04-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
161050	2019-04-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
161100	2019-04-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
161150	2019-04-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
161200	2019-04-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
161250	2019-04-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
161300	2019-04-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
161350	2019-04-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
161400	2019-04-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
161450	2019-04-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
161500	2019-04-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
161550	2019-04-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
161600	2019-04-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
161650	2019-05-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
161700	2019-05-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
161750	2019-05-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
161800	2019-05-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
161850	2019-05-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
161900	2019-05-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
161950	2019-05-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
162000	2019-05-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
162050	2019-05-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
162100	2019-05-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
162150	2019-05-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
162200	2019-05-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
162250	2019-05-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
162300	2019-05-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
162350	2019-05-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
162400	2019-05-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
162450	2019-05-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
162500	2019-05-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
162550	2019-05-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
162600	2019-05-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
162650	2019-06-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
162700	2019-06-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
162750	2019-06-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
162800	2019-06-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
162850	2019-06-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
162900	2019-06-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
162950	2019-06-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
163000	2019-06-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
163050	2019-06-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
163100	2019-06-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
163150	2019-06-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
163200	2019-06-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
163250	2019-06-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
163300	2019-06-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
163350	2019-06-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
163400	2019-06-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
163450	2019-01-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
163500	2019-01-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
163550	2019-01-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
163600	2019-01-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
163650	2019-01-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
163700	2019-01-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
163750	2019-01-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
163800	2019-01-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
163850	2019-01-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
163900	2019-01-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
163950	2019-01-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
164000	2019-01-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
164050	2019-01-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
164100	2019-01-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
164150	2019-01-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
164200	2019-01-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
164250	2019-02-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
164300	2019-02-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
164350	2019-02-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
164400	2019-02-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
164450	2019-02-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
164500	2019-02-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
164550	2019-02-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
164600	2019-02-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
164650	2019-02-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
164700	2019-02-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
164750	2019-02-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
164800	2019-02-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
164850	2019-02-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
164900	2019-02-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
164950	2019-02-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
165000	2019-02-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
165050	2019-03-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
165100	2019-03-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
165150	2019-03-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
165200	2019-03-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
165250	2019-03-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
165300	2019-03-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
165350	2019-03-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
165400	2019-03-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
165450	2019-03-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
165500	2019-03-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
165550	2019-03-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
165600	2019-03-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
165650	2019-03-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
165700	2019-03-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
165750	2019-03-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
165800	2019-03-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
165850	2019-03-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
165900	2019-03-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
165950	2019-03-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
166000	2019-03-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
166050	2019-04-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
166100	2019-04-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
166150	2019-04-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
166200	2019-04-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
166250	2019-04-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
166300	2019-04-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
166350	2019-04-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
166400	2019-04-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
166450	2019-04-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
166500	2019-04-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
166550	2019-04-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
166600	2019-04-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
166650	2019-04-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
166700	2019-04-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
166750	2019-04-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
166800	2019-04-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
166850	2019-05-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
166900	2019-05-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
166950	2019-05-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
167000	2019-05-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
167050	2019-05-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
167100	2019-05-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
167150	2019-05-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
167200	2019-05-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
167250	2019-05-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
167300	2019-05-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
167350	2019-05-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
167400	2019-05-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
167450	2019-05-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
167500	2019-05-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
167550	2019-05-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
167600	2019-05-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
167650	2019-05-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
167700	2019-05-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
167750	2019-05-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
167800	2019-05-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
167850	2019-06-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
167900	2019-06-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
167950	2019-06-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
168000	2019-06-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
168050	2019-06-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
168100	2019-06-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
168150	2019-06-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
168200	2019-06-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
168250	2019-06-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
168300	2019-06-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
168350	2019-06-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
168400	2019-06-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
168450	2019-06-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17100
168500	2019-06-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17150
168550	2019-06-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17200
168600	2019-06-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17250
168650	2019-01-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
168700	2019-01-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
168750	2019-01-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
168800	2019-01-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
168850	2019-01-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
168900	2019-01-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
168950	2019-01-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
169000	2019-01-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
169050	2019-01-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
169100	2019-01-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
169150	2019-01-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
169200	2019-01-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
169250	2019-02-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
169300	2019-02-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
169350	2019-02-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
169400	2019-02-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
169450	2019-02-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
169500	2019-02-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
169550	2019-02-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
169600	2019-02-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
169650	2019-02-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
169700	2019-02-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
169750	2019-02-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
169800	2019-02-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
169850	2019-02-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
169900	2019-02-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
169950	2019-02-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
170000	2019-02-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
170050	2019-03-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
170100	2019-03-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
170150	2019-03-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
170200	2019-03-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
170250	2019-03-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
170300	2019-03-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
170350	2019-03-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
170400	2019-03-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
170450	2019-03-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
170500	2019-03-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
170550	2019-03-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
170600	2019-03-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
170650	2019-04-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
170700	2019-04-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
170750	2019-04-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
170800	2019-04-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
170850	2019-04-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
170900	2019-04-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
170950	2019-04-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
171000	2019-04-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
171050	2019-04-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
171100	2019-04-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
171150	2019-04-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
171200	2019-04-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
171250	2019-04-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
171300	2019-04-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
171350	2019-04-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
171400	2019-04-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
171450	2019-04-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
171500	2019-04-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
171550	2019-04-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
171600	2019-04-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
171650	2019-05-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
171700	2019-05-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
171750	2019-05-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
171800	2019-05-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
171850	2019-05-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
171900	2019-05-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
171950	2019-05-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
172000	2019-05-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
172050	2019-05-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
172100	2019-05-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
172150	2019-05-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
172200	2019-05-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
172250	2019-05-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
172300	2019-05-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
172350	2019-05-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
172400	2019-05-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
172450	2019-06-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
172500	2019-06-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
172550	2019-06-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
172600	2019-06-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
172650	2019-06-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
172700	2019-06-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
172750	2019-06-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
172800	2019-06-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
172850	2019-06-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
172900	2019-06-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
172950	2019-06-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
173000	2019-06-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
173050	2019-06-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
173100	2019-06-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
173150	2019-06-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
173200	2019-06-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
173250	2019-01-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
173300	2019-01-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
173350	2019-01-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
173400	2019-01-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
173450	2019-01-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
173500	2019-01-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
173550	2019-01-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
173600	2019-01-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
173650	2019-01-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
173700	2019-01-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
173750	2019-01-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
173800	2019-01-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
173850	2019-02-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
173900	2019-02-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
173950	2019-02-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
174000	2019-02-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
174050	2019-02-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
174100	2019-02-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
174150	2019-02-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
174200	2019-02-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
174250	2019-02-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
174300	2019-02-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
174350	2019-02-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
174400	2019-02-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
174450	2019-02-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
174500	2019-02-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
174550	2019-02-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
174600	2019-02-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
174650	2019-03-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
174700	2019-03-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
174750	2019-03-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
174800	2019-03-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
174850	2019-03-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
174900	2019-03-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
174950	2019-03-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
175000	2019-03-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
175050	2019-03-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
175100	2019-03-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
175150	2019-03-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
175200	2019-03-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
175250	2019-03-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
175300	2019-03-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
175350	2019-03-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
175400	2019-03-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
175450	2019-04-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
175500	2019-04-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
175550	2019-04-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
175600	2019-04-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
175650	2019-04-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
175700	2019-04-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
175750	2019-04-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
175800	2019-04-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
175850	2019-04-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
175900	2019-04-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
175950	2019-04-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
176000	2019-04-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
176050	2019-04-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
176100	2019-04-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
176150	2019-04-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
176200	2019-04-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
176250	2019-04-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
176300	2019-04-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
176350	2019-04-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
176400	2019-04-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
176450	2019-05-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
176500	2019-05-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
176550	2019-05-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
176600	2019-05-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
176650	2019-05-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
176700	2019-05-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
176750	2019-05-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
176800	2019-05-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
176850	2019-05-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
176900	2019-05-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
176950	2019-05-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
177000	2019-05-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
177050	2019-05-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
177100	2019-05-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
177150	2019-05-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
177200	2019-05-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
177250	2019-06-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
177300	2019-06-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
177350	2019-06-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
177400	2019-06-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
177450	2019-06-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
177500	2019-06-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
177550	2019-06-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
177600	2019-06-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
177650	2019-06-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
177700	2019-06-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
177750	2019-06-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
177800	2019-06-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
177850	2019-06-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
177900	2019-06-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
177950	2019-06-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
178000	2019-06-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
178050	2019-01-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
178100	2019-01-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
178150	2019-01-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
178200	2019-01-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
178250	2019-01-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
178300	2019-01-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
178350	2019-01-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
178400	2019-01-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
178450	2019-01-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
178500	2019-01-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
178550	2019-01-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
178600	2019-01-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
178650	2019-01-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
178700	2019-01-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
178750	2019-01-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
178800	2019-01-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
178850	2019-01-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
178900	2019-01-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
178950	2019-01-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
179000	2019-01-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
179050	2019-02-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
179100	2019-02-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
179150	2019-02-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
179200	2019-02-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
179250	2019-02-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
179300	2019-02-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
179350	2019-02-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
179400	2019-02-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
179450	2019-02-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
179500	2019-02-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
179550	2019-02-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
179600	2019-02-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
179650	2019-02-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
179700	2019-02-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
179750	2019-02-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
179800	2019-02-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
179850	2019-03-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
179900	2019-03-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
179950	2019-03-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
180000	2019-03-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
180050	2019-03-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
180100	2019-03-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
180150	2019-03-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
180200	2019-03-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
180250	2019-03-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
180300	2019-03-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
180350	2019-03-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
180400	2019-03-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
180450	2019-03-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
180500	2019-03-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
180550	2019-03-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
180600	2019-03-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
180650	2019-04-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
180700	2019-04-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
180750	2019-04-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
180800	2019-04-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
180850	2019-04-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
180900	2019-04-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
180950	2019-04-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
181000	2019-04-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
181050	2019-04-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
181100	2019-04-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
181150	2019-04-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
181200	2019-04-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
181250	2019-04-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
181300	2019-04-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
181350	2019-04-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
181400	2019-04-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
181450	2019-05-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
181500	2019-05-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
181550	2019-05-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
181600	2019-05-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
181650	2019-05-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
181700	2019-05-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
181750	2019-05-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
181800	2019-05-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
181850	2019-05-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
181900	2019-05-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
181950	2019-05-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
182000	2019-05-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
182050	2019-05-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
182100	2019-05-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
182150	2019-05-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
182200	2019-05-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
182250	2019-05-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
182300	2019-05-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
182350	2019-05-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
182400	2019-05-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
182450	2019-06-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
182500	2019-06-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
182550	2019-06-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
182600	2019-06-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
182650	2019-06-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
182700	2019-06-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
182750	2019-06-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
182800	2019-06-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
182850	2019-06-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
182900	2019-06-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
182950	2019-06-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
183000	2019-06-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
183050	2019-06-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
183100	2019-06-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
183150	2019-06-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
183200	2019-06-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
183250	2019-01-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
183300	2019-01-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
183350	2019-01-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
183400	2019-01-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
183450	2019-01-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
183500	2019-01-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
183550	2019-01-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
183600	2019-01-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
183650	2019-01-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
183700	2019-01-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
183750	2019-01-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
183800	2019-01-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
183850	2019-01-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
183900	2019-01-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
183950	2019-01-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
184000	2019-01-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
184050	2019-01-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
184100	2019-01-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
184150	2019-01-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
184200	2019-01-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
184250	2019-02-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
184300	2019-02-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
184350	2019-02-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
184400	2019-02-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
184450	2019-02-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
184500	2019-02-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
184550	2019-02-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
184600	2019-02-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
184650	2019-02-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
184700	2019-02-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
184750	2019-02-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
184800	2019-02-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
184850	2019-02-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
184900	2019-02-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
184950	2019-02-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
185000	2019-02-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
185050	2019-03-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
185100	2019-03-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
185150	2019-03-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
185200	2019-03-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
185250	2019-03-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
185300	2019-03-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
185350	2019-03-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
185400	2019-03-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
185450	2019-03-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
185500	2019-03-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
185550	2019-03-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
185600	2019-03-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
185650	2019-04-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
185700	2019-04-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
185750	2019-04-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
185800	2019-04-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
185850	2019-04-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
185900	2019-04-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
185950	2019-04-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
186000	2019-04-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
186050	2019-04-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
186100	2019-04-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
186150	2019-04-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
186200	2019-04-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
186250	2019-04-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
186300	2019-04-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
186350	2019-04-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
186400	2019-04-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
186450	2019-05-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
186500	2019-05-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
186550	2019-05-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
186600	2019-05-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
186650	2019-05-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
186700	2019-05-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
186750	2019-05-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
186800	2019-05-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
186850	2019-05-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
186900	2019-05-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
186950	2019-05-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
187000	2019-05-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
187050	2019-05-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
187100	2019-05-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
187150	2019-05-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
187200	2019-05-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
187250	2019-05-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
187300	2019-05-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
187350	2019-05-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
187400	2019-05-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
187450	2019-06-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
187500	2019-06-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
187550	2019-06-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
187600	2019-06-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
187650	2019-06-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
187700	2019-06-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
187750	2019-06-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
187800	2019-06-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
187850	2019-06-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
187900	2019-06-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
187950	2019-06-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
188000	2019-06-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
188050	2019-06-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
188100	2019-06-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
188150	2019-06-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
188200	2019-06-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
188250	2019-01-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
188300	2019-01-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
188350	2019-01-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
188400	2019-01-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
188450	2019-01-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
188500	2019-01-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
188550	2019-01-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
188600	2019-01-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
188650	2019-01-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
188700	2019-01-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
188750	2019-01-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
188800	2019-01-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
188850	2019-01-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
188900	2019-01-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
188950	2019-01-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
189000	2019-01-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
189050	2019-02-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
189100	2019-02-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
189150	2019-02-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
189200	2019-02-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
189250	2019-02-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
189300	2019-02-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
189350	2019-02-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
189400	2019-02-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
189450	2019-02-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
189500	2019-02-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
189550	2019-02-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
189600	2019-02-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
189650	2019-02-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
189700	2019-02-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
189750	2019-02-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
189800	2019-02-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
189850	2019-03-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
189900	2019-03-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
189950	2019-03-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
190000	2019-03-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
190050	2019-03-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
190100	2019-03-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
190150	2019-03-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
190200	2019-03-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
190250	2019-03-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
190300	2019-03-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
190350	2019-03-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
190400	2019-03-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
190450	2019-03-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
190500	2019-03-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
190550	2019-03-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
190600	2019-03-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
190650	2019-03-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
190700	2019-03-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
190750	2019-03-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
190800	2019-03-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
190850	2019-04-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
190900	2019-04-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
190950	2019-04-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
191000	2019-04-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
191050	2019-04-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
191100	2019-04-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
191150	2019-04-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
191200	2019-04-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
191250	2019-04-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
191300	2019-04-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
191350	2019-04-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
191400	2019-04-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
191450	2019-04-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
191500	2019-04-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
191550	2019-04-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
191600	2019-04-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
191650	2019-05-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
191700	2019-05-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
191750	2019-05-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
191800	2019-05-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
191850	2019-05-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
191900	2019-05-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
191950	2019-05-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
192000	2019-05-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
192050	2019-05-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
192100	2019-05-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
192150	2019-05-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
192200	2019-05-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
192250	2019-05-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
192300	2019-05-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
192350	2019-05-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
192400	2019-05-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
192450	2019-05-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
192500	2019-05-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
192550	2019-05-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
192600	2019-05-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
192650	2019-06-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
192700	2019-06-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
192750	2019-06-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
192800	2019-06-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
192850	2019-06-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
192900	2019-06-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
192950	2019-06-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
193000	2019-06-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
193050	2019-06-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
193100	2019-06-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
193150	2019-06-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
193200	2019-06-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
193250	2019-06-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17300
193300	2019-06-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17350
193350	2019-06-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17400
193400	2019-06-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17450
193450	2019-01-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
193500	2019-01-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
193550	2019-01-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
193600	2019-01-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
193650	2019-01-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
193700	2019-01-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
193750	2019-01-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
193800	2019-01-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
193850	2019-01-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
193900	2019-01-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
193950	2019-01-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
194000	2019-01-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
194050	2019-02-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
194100	2019-02-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
194150	2019-02-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
194200	2019-02-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
194250	2019-02-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
194300	2019-02-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
194350	2019-02-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
194400	2019-02-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
194450	2019-02-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
194500	2019-02-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
194550	2019-02-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
194600	2019-02-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
194650	2019-02-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
194700	2019-02-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
194750	2019-02-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
194800	2019-02-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
194850	2019-03-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
194900	2019-03-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
194950	2019-03-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
195000	2019-03-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
195050	2019-03-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
195100	2019-03-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
195150	2019-03-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
195200	2019-03-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
195250	2019-03-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
195300	2019-03-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
195350	2019-03-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
195400	2019-03-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
195450	2019-04-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
195500	2019-04-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
195550	2019-04-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
195600	2019-04-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
195650	2019-04-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
195700	2019-04-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
195750	2019-04-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
195800	2019-04-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
195850	2019-04-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
195900	2019-04-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
195950	2019-04-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
196000	2019-04-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
196050	2019-04-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
196100	2019-04-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
196150	2019-04-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
196200	2019-04-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
196250	2019-04-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
196300	2019-04-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
196350	2019-04-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
196400	2019-04-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
196450	2019-05-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
196500	2019-05-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
196550	2019-05-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
196600	2019-05-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
196650	2019-05-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
196700	2019-05-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
196750	2019-05-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
196800	2019-05-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
196850	2019-05-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
196900	2019-05-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
196950	2019-05-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
197000	2019-05-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
197050	2019-05-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
197100	2019-05-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
197150	2019-05-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
197200	2019-05-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
197250	2019-06-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
197300	2019-06-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
197350	2019-06-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
197400	2019-06-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
197450	2019-06-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
197500	2019-06-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
197550	2019-06-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
197600	2019-06-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
197650	2019-06-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
197700	2019-06-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
197750	2019-06-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
197800	2019-06-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
197850	2019-06-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
197900	2019-06-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
197950	2019-06-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
198000	2019-06-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
198050	2019-01-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
198100	2019-01-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
198150	2019-01-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
198200	2019-01-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
198250	2019-01-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
198300	2019-01-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
198350	2019-01-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
198400	2019-01-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
198450	2019-01-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
198500	2019-01-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
198550	2019-01-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
198600	2019-01-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
198650	2019-02-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
198700	2019-02-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
198750	2019-02-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
198800	2019-02-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
198850	2019-02-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
198900	2019-02-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
198950	2019-02-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
199000	2019-02-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
199050	2019-02-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
199100	2019-02-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
199150	2019-02-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
199200	2019-02-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
199250	2019-02-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
199300	2019-02-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
199350	2019-02-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
199400	2019-02-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
199450	2019-03-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
199500	2019-03-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
199550	2019-03-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
199600	2019-03-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
199650	2019-03-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
199700	2019-03-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
199750	2019-03-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
199800	2019-03-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
199850	2019-03-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
199900	2019-03-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
199950	2019-03-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
200000	2019-03-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
200050	2019-03-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
200100	2019-03-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
200150	2019-03-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
200200	2019-03-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
200250	2019-04-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
200300	2019-04-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
200350	2019-04-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
200400	2019-04-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
200450	2019-04-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
200500	2019-04-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
200550	2019-04-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
200600	2019-04-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
200650	2019-04-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
200700	2019-04-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
200750	2019-04-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
200800	2019-04-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
200850	2019-04-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
200900	2019-04-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
200950	2019-04-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
201000	2019-04-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
201050	2019-04-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
201100	2019-04-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
201150	2019-04-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
201200	2019-04-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
201250	2019-05-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
201300	2019-05-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
201350	2019-05-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
201400	2019-05-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
201450	2019-05-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
201500	2019-05-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
201550	2019-05-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
201600	2019-05-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
201650	2019-05-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
201700	2019-05-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
201750	2019-05-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
201800	2019-05-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
201850	2019-05-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
201900	2019-05-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
201950	2019-05-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
202000	2019-05-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
202050	2019-06-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
202100	2019-06-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
202150	2019-06-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
202200	2019-06-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
202250	2019-06-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
202300	2019-06-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
202350	2019-06-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
202400	2019-06-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
202450	2019-06-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
202500	2019-06-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
202550	2019-06-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
202600	2019-06-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
202650	2019-06-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
202700	2019-06-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
202750	2019-06-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
202800	2019-06-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
202850	2019-01-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
202900	2019-01-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
202950	2019-01-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
203000	2019-01-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
203050	2019-01-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
203100	2019-01-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
203150	2019-01-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
203200	2019-01-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
203250	2019-01-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
203300	2019-01-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
203350	2019-01-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
203400	2019-01-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
203450	2019-01-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
203500	2019-01-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
203550	2019-01-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
203600	2019-01-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
203650	2019-01-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
203700	2019-01-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
203750	2019-01-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
203800	2019-01-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
203850	2019-02-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
203900	2019-02-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
203950	2019-02-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
204000	2019-02-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
204050	2019-02-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
204100	2019-02-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
204150	2019-02-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
204200	2019-02-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
204250	2019-02-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
204300	2019-02-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
204350	2019-02-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
204400	2019-02-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
204450	2019-02-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
204500	2019-02-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
204550	2019-02-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
204600	2019-02-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
204650	2019-03-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
204700	2019-03-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
204750	2019-03-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
204800	2019-03-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
204850	2019-03-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
204900	2019-03-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
204950	2019-03-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
205000	2019-03-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
205050	2019-03-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
205100	2019-03-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
205150	2019-03-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
205200	2019-03-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
205250	2019-03-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
205300	2019-03-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
205350	2019-03-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
205400	2019-03-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
205450	2019-04-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
205500	2019-04-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
205550	2019-04-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
205600	2019-04-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
205650	2019-04-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
205700	2019-04-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
205750	2019-04-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
205800	2019-04-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
205850	2019-04-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
205900	2019-04-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
205950	2019-04-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
206000	2019-04-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
206050	2019-04-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
206100	2019-04-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
206150	2019-04-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
206200	2019-04-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
206250	2019-05-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
206300	2019-05-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
206350	2019-05-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
206400	2019-05-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
206450	2019-05-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
206500	2019-05-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
206550	2019-05-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
206600	2019-05-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
206650	2019-05-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
206700	2019-05-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
206750	2019-05-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
206800	2019-05-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
206850	2019-05-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
206900	2019-05-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
206950	2019-05-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
207000	2019-05-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
207050	2019-05-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
207100	2019-05-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
207150	2019-05-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
207200	2019-05-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
207250	2019-06-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
207300	2019-06-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
207350	2019-06-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
207400	2019-06-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
207450	2019-06-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
207500	2019-06-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
207550	2019-06-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
207600	2019-06-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
207650	2019-06-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
207700	2019-06-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
207750	2019-06-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
207800	2019-06-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
207850	2019-06-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
207900	2019-06-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
207950	2019-06-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
208000	2019-06-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
208050	2019-01-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
208100	2019-01-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
208150	2019-01-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
208200	2019-01-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
208250	2019-01-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
208300	2019-01-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
208350	2019-01-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
208400	2019-01-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
208450	2019-01-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
208500	2019-01-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
208550	2019-01-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
208600	2019-01-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
208650	2019-01-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
208700	2019-01-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
208750	2019-01-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
208800	2019-01-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
208850	2019-01-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
208900	2019-01-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
208950	2019-01-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
209000	2019-01-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
209050	2019-02-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
209100	2019-02-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
209150	2019-02-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
209200	2019-02-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
209250	2019-02-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
209300	2019-02-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
209350	2019-02-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
209400	2019-02-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
209450	2019-02-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
209500	2019-02-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
209550	2019-02-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
209600	2019-02-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
209650	2019-02-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
209700	2019-02-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
209750	2019-02-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
209800	2019-02-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
209850	2019-03-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
209900	2019-03-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
209950	2019-03-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
210000	2019-03-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
210050	2019-03-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
210100	2019-03-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
210150	2019-03-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
210200	2019-03-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
210250	2019-03-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
210300	2019-03-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
210350	2019-03-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
210400	2019-03-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
210450	2019-04-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
210500	2019-04-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
210550	2019-04-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
210600	2019-04-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
210650	2019-04-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
210700	2019-04-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
210750	2019-04-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
210800	2019-04-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
210850	2019-04-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
210900	2019-04-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
210950	2019-04-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
211000	2019-04-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
211050	2019-04-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
211100	2019-04-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
211150	2019-04-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
211200	2019-04-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
211250	2019-05-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
211300	2019-05-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
211350	2019-05-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
211400	2019-05-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
211450	2019-05-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
211500	2019-05-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
211550	2019-05-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
211600	2019-05-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
211650	2019-05-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
211700	2019-05-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
211750	2019-05-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
211800	2019-05-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
211850	2019-05-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
211900	2019-05-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
211950	2019-05-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
212000	2019-05-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
212050	2019-05-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
212100	2019-05-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
212150	2019-05-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
212200	2019-05-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
212250	2019-06-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
212300	2019-06-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
212350	2019-06-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
212400	2019-06-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
212450	2019-06-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
212500	2019-06-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
212550	2019-06-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
212600	2019-06-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
212650	2019-06-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
212700	2019-06-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
212750	2019-06-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
212800	2019-06-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
212850	2019-06-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
212900	2019-06-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
212950	2019-06-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
213000	2019-06-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
213050	2019-01-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
213100	2019-01-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
213150	2019-01-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
213200	2019-01-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
213250	2019-01-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
213300	2019-01-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
213350	2019-01-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
213400	2019-01-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
213450	2019-01-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
213500	2019-01-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
213550	2019-01-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
213600	2019-01-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
213650	2019-01-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
213700	2019-01-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
213750	2019-01-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
213800	2019-01-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
213850	2019-02-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
213900	2019-02-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
213950	2019-02-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
214000	2019-02-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
214050	2019-02-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
214100	2019-02-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
214150	2019-02-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
214200	2019-02-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
214250	2019-02-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
214300	2019-02-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
214350	2019-02-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
214400	2019-02-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
214450	2019-02-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
214500	2019-02-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
214550	2019-02-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
214600	2019-02-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
214650	2019-03-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
214700	2019-03-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
214750	2019-03-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
214800	2019-03-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
214850	2019-03-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
214900	2019-03-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
214950	2019-03-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
215000	2019-03-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
215050	2019-03-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
215100	2019-03-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
215150	2019-03-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
215200	2019-03-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
215250	2019-03-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
215300	2019-03-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
215350	2019-03-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
215400	2019-03-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
215450	2019-03-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
215500	2019-03-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
215550	2019-03-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
215600	2019-03-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
215650	2019-04-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
215700	2019-04-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
215750	2019-04-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
215800	2019-04-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
215850	2019-04-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
215900	2019-04-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
215950	2019-04-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
216000	2019-04-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
216050	2019-04-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
216100	2019-04-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
216150	2019-04-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
216200	2019-04-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
216250	2019-04-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
216300	2019-04-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
216350	2019-04-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
216400	2019-04-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
216450	2019-05-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
216500	2019-05-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
216550	2019-05-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
216600	2019-05-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
216650	2019-05-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
216700	2019-05-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
216750	2019-05-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
216800	2019-05-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
216850	2019-05-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
216900	2019-05-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
216950	2019-05-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
217000	2019-05-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
217050	2019-05-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
217100	2019-05-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
217150	2019-05-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
217200	2019-05-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
217250	2019-05-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
217300	2019-05-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
217350	2019-05-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
217400	2019-05-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
217450	2019-06-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
217500	2019-06-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
217550	2019-06-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
217600	2019-06-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
217650	2019-06-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
217700	2019-06-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
217750	2019-06-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
217800	2019-06-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
217850	2019-06-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
217900	2019-06-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
217950	2019-06-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
218000	2019-06-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
218050	2019-06-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17500
218100	2019-06-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17550
218150	2019-06-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17600
218200	2019-06-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17650
218250	2019-01-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
218300	2019-01-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
218350	2019-01-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
218400	2019-01-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
218450	2019-01-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
218500	2019-01-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
218550	2019-01-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
218600	2019-01-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
218650	2019-01-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
218700	2019-01-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
218750	2019-01-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
218800	2019-01-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
218850	2019-02-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
218900	2019-02-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
218950	2019-02-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
219000	2019-02-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
219050	2019-02-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
219100	2019-02-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
219150	2019-02-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
219200	2019-02-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
219250	2019-02-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
219300	2019-02-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
219350	2019-02-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
219400	2019-02-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
219450	2019-02-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
219500	2019-02-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
219550	2019-02-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
219600	2019-02-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
219650	2019-03-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
219700	2019-03-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
219750	2019-03-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
219800	2019-03-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
219850	2019-03-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
219900	2019-03-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
219950	2019-03-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
220000	2019-03-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
220050	2019-03-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
220100	2019-03-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
220150	2019-03-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
220200	2019-03-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
220250	2019-04-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
220300	2019-04-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
220350	2019-04-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
220400	2019-04-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
220450	2019-04-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
220500	2019-04-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
220550	2019-04-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
220600	2019-04-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
220650	2019-04-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
220700	2019-04-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
220750	2019-04-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
220800	2019-04-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
220850	2019-04-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
220900	2019-04-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
220950	2019-04-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
221000	2019-04-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
221050	2019-04-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
221100	2019-04-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
221150	2019-04-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
221200	2019-04-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
221250	2019-05-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
221300	2019-05-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
221350	2019-05-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
221400	2019-05-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
221450	2019-05-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
221500	2019-05-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
221550	2019-05-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
221600	2019-05-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
221650	2019-05-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
221700	2019-05-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
221750	2019-05-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
221800	2019-05-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
221850	2019-05-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
221900	2019-05-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
221950	2019-05-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
222000	2019-05-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
222050	2019-06-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
222100	2019-06-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
222150	2019-06-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
222200	2019-06-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
222250	2019-06-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
222300	2019-06-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
222350	2019-06-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
222400	2019-06-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
222450	2019-06-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
222500	2019-06-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
222550	2019-06-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
222600	2019-06-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
222650	2019-06-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
222700	2019-06-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
222750	2019-06-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
222800	2019-06-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
222850	2019-01-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
222900	2019-01-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
222950	2019-01-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
223000	2019-01-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
223050	2019-01-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
223100	2019-01-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
223150	2019-01-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
223200	2019-01-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
223250	2019-01-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
223300	2019-01-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
223350	2019-01-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
223400	2019-01-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
223450	2019-02-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
223500	2019-02-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
223550	2019-02-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
223600	2019-02-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
223650	2019-02-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
223700	2019-02-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
223750	2019-02-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
223800	2019-02-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
223850	2019-02-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
223900	2019-02-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
223950	2019-02-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
224000	2019-02-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
224050	2019-02-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
224100	2019-02-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
224150	2019-02-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
224200	2019-02-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
224250	2019-03-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
224300	2019-03-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
224350	2019-03-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
224400	2019-03-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
224450	2019-03-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
224500	2019-03-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
224550	2019-03-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
224600	2019-03-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
224650	2019-03-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
224700	2019-03-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
224750	2019-03-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
224800	2019-03-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
224850	2019-03-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
224900	2019-03-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
224950	2019-03-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
225000	2019-03-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
225050	2019-04-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
225100	2019-04-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
225150	2019-04-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
225200	2019-04-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
225250	2019-04-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
225300	2019-04-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
225350	2019-04-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
225400	2019-04-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
225450	2019-04-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
225500	2019-04-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
225550	2019-04-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
225600	2019-04-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
225650	2019-04-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
225700	2019-04-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
225750	2019-04-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
225800	2019-04-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
225850	2019-04-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
225900	2019-04-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
225950	2019-04-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
226000	2019-04-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
226050	2019-05-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
226100	2019-05-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
226150	2019-05-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
226200	2019-05-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
226250	2019-05-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
226300	2019-05-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
226350	2019-05-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
226400	2019-05-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
226450	2019-05-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
226500	2019-05-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
226550	2019-05-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
226600	2019-05-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
226650	2019-05-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
226700	2019-05-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
226750	2019-05-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
226800	2019-05-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
226850	2019-06-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
226900	2019-06-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
226950	2019-06-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
227000	2019-06-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
227050	2019-06-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
227100	2019-06-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
227150	2019-06-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
227200	2019-06-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
227250	2019-06-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
227300	2019-06-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
227350	2019-06-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
227400	2019-06-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
227450	2019-06-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
227500	2019-06-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
227550	2019-06-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
227600	2019-06-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
227650	2019-01-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
227700	2019-01-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
227750	2019-01-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
227800	2019-01-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
227850	2019-01-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
227900	2019-01-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
227950	2019-01-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
228000	2019-01-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
228050	2019-01-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
228100	2019-01-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
228150	2019-01-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
228200	2019-01-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
228250	2019-01-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
228300	2019-01-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
228350	2019-01-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
228400	2019-01-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
228450	2019-01-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
228500	2019-01-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
228550	2019-01-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
228600	2019-01-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
228650	2019-02-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
228700	2019-02-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
228750	2019-02-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
228800	2019-02-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
228850	2019-02-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
228900	2019-02-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
228950	2019-02-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
229000	2019-02-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
229050	2019-02-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
229100	2019-02-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
229150	2019-02-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
229200	2019-02-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
229250	2019-02-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
229300	2019-02-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
229350	2019-02-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
229400	2019-02-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
229450	2019-03-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
229500	2019-03-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
229550	2019-03-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
229600	2019-03-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
229650	2019-03-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
229700	2019-03-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
229750	2019-03-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
229800	2019-03-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
229850	2019-03-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
229900	2019-03-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
229950	2019-03-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
230000	2019-03-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
230050	2019-03-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
230100	2019-03-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
230150	2019-03-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
230200	2019-03-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
230250	2019-04-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
230300	2019-04-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
230350	2019-04-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
230400	2019-04-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
230450	2019-04-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
230500	2019-04-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
230550	2019-04-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
230600	2019-04-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
230650	2019-04-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
230700	2019-04-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
230750	2019-04-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
230800	2019-04-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
230850	2019-04-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
230900	2019-04-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
230950	2019-04-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
231000	2019-04-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
231050	2019-05-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
231100	2019-05-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
231150	2019-05-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
231200	2019-05-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
231250	2019-05-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
231300	2019-05-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
231350	2019-05-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
231400	2019-05-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
231450	2019-05-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
231500	2019-05-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
231550	2019-05-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
231600	2019-05-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
231650	2019-05-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
231700	2019-05-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
231750	2019-05-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
231800	2019-05-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
231850	2019-05-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
231900	2019-05-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
231950	2019-05-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
232000	2019-05-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
232050	2019-06-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
232100	2019-06-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
232150	2019-06-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
232200	2019-06-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
232250	2019-06-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
232300	2019-06-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
232350	2019-06-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
232400	2019-06-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
232450	2019-06-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
232500	2019-06-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
232550	2019-06-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
232600	2019-06-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
232650	2019-06-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
232700	2019-06-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
232750	2019-06-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
232800	2019-06-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
232850	2019-01-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
232900	2019-01-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
232950	2019-01-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
233000	2019-01-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
233050	2019-01-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
233100	2019-01-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
233150	2019-01-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
233200	2019-01-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
233250	2019-01-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
233300	2019-01-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
233350	2019-01-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
233400	2019-01-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
233450	2019-01-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
233500	2019-01-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
233550	2019-01-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
233600	2019-01-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
233650	2019-01-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
233700	2019-01-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
233750	2019-01-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
233800	2019-01-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
233850	2019-02-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
233900	2019-02-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
233950	2019-02-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
234000	2019-02-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
234050	2019-02-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
234100	2019-02-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
234150	2019-02-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
234200	2019-02-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
234250	2019-02-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
234300	2019-02-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
234350	2019-02-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
234400	2019-02-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
234450	2019-02-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
234500	2019-02-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
234550	2019-02-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
234600	2019-02-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
234650	2019-03-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
234700	2019-03-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
234750	2019-03-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
234800	2019-03-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
234850	2019-03-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
234900	2019-03-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
234950	2019-03-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
235000	2019-03-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
235050	2019-03-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
235100	2019-03-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
235150	2019-03-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
235200	2019-03-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
235250	2019-04-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
235300	2019-04-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
235350	2019-04-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
235400	2019-04-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
235450	2019-04-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
235500	2019-04-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
235550	2019-04-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
235600	2019-04-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
235650	2019-04-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
235700	2019-04-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
235750	2019-04-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
235800	2019-04-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
235850	2019-04-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
235900	2019-04-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
235950	2019-04-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
236000	2019-04-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
236050	2019-05-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
236100	2019-05-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
236150	2019-05-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
236200	2019-05-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
236250	2019-05-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
236300	2019-05-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
236350	2019-05-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
236400	2019-05-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
236450	2019-05-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
236500	2019-05-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
236550	2019-05-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
236600	2019-05-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
236650	2019-05-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
236700	2019-05-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
236750	2019-05-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
236800	2019-05-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
236850	2019-05-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
236900	2019-05-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
236950	2019-05-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
237000	2019-05-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
237050	2019-06-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
237100	2019-06-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
237150	2019-06-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
237200	2019-06-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
237250	2019-06-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
237300	2019-06-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
237350	2019-06-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
237400	2019-06-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
237450	2019-06-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
237500	2019-06-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
237550	2019-06-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
237600	2019-06-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
237650	2019-06-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
237700	2019-06-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
237750	2019-06-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
237800	2019-06-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
237850	2019-01-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
237900	2019-01-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
237950	2019-01-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
238000	2019-01-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
238050	2019-01-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
238100	2019-01-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
238150	2019-01-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
238200	2019-01-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
238250	2019-01-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
238300	2019-01-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
238350	2019-01-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
238400	2019-01-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
238450	2019-01-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
238500	2019-01-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
238550	2019-01-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
238600	2019-01-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
238650	2019-02-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
238700	2019-02-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
238750	2019-02-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
238800	2019-02-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
238850	2019-02-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
238900	2019-02-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
238950	2019-02-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
239000	2019-02-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
239050	2019-02-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
239100	2019-02-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
239150	2019-02-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
239200	2019-02-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
239250	2019-02-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
239300	2019-02-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
239350	2019-02-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
239400	2019-02-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
239450	2019-03-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
239500	2019-03-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
239550	2019-03-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
239600	2019-03-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
239650	2019-03-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
239700	2019-03-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
239750	2019-03-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
239800	2019-03-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
239850	2019-03-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
239900	2019-03-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
239950	2019-03-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
240000	2019-03-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
240050	2019-03-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
240100	2019-03-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
240150	2019-03-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
240200	2019-03-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
240250	2019-03-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
240300	2019-03-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
240350	2019-03-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
240400	2019-03-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
240450	2019-04-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
240500	2019-04-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
240550	2019-04-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
240600	2019-04-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
240650	2019-04-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
240700	2019-04-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
240750	2019-04-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
240800	2019-04-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
240850	2019-04-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
240900	2019-04-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
240950	2019-04-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
241000	2019-04-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
241050	2019-04-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
241100	2019-04-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
241150	2019-04-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
241200	2019-04-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
241250	2019-05-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
241300	2019-05-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
241350	2019-05-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
241400	2019-05-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
241450	2019-05-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
241500	2019-05-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
241550	2019-05-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
241600	2019-05-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
241650	2019-05-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
241700	2019-05-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
241750	2019-05-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
241800	2019-05-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
241850	2019-05-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
241900	2019-05-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
241950	2019-05-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
242000	2019-05-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
242050	2019-05-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
242100	2019-05-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
242150	2019-05-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
242200	2019-05-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
242250	2019-06-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
242300	2019-06-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
242350	2019-06-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
242400	2019-06-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
242450	2019-06-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
242500	2019-06-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
242550	2019-06-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
242600	2019-06-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
242650	2019-06-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
242700	2019-06-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
242750	2019-06-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
242800	2019-06-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
242850	2019-06-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17700
242900	2019-06-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17750
242950	2019-06-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	17800
243000	2019-06-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	17850
243050	2019-01-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
243100	2019-01-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
243150	2019-01-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
243200	2019-01-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
243250	2019-01-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
243300	2019-01-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
243350	2019-01-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
243400	2019-01-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
243450	2019-01-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
243500	2019-01-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
243550	2019-01-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
243600	2019-01-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
243650	2019-01-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
243700	2019-01-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
243750	2019-01-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
243800	2019-01-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
243850	2019-01-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
243900	2019-01-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
243950	2019-01-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
244000	2019-01-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
244050	2019-01-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
244100	2019-01-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
244150	2019-01-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
244200	2019-01-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
244250	2019-01-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
244300	2019-01-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
244350	2019-01-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
244400	2019-01-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
244450	2019-01-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
244500	2019-01-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
244550	2019-01-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
244600	2019-01-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
244650	2019-01-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
244700	2019-01-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
244750	2019-01-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
244800	2019-01-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
244850	2019-01-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
244900	2019-01-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
244950	2019-01-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
245000	2019-01-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
245050	2019-01-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
245100	2019-01-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
245150	2019-01-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
245200	2019-01-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
245250	2019-01-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
245300	2019-01-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
245350	2019-01-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
245400	2019-01-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
245450	2019-02-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
245500	2019-02-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
245550	2019-02-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
245600	2019-02-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
245650	2019-02-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
245700	2019-02-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
245750	2019-02-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
245800	2019-02-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
245850	2019-02-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
245900	2019-02-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
245950	2019-02-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
246000	2019-02-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
246050	2019-02-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
246100	2019-02-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
246150	2019-02-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
246200	2019-02-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
246250	2019-02-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
246300	2019-02-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
246350	2019-02-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
246400	2019-02-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
246450	2019-02-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
246500	2019-02-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
246550	2019-02-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
246600	2019-02-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
246650	2019-02-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
246700	2019-02-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
246750	2019-02-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
246800	2019-02-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
246850	2019-02-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
246900	2019-02-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
246950	2019-02-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
247000	2019-02-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
247050	2019-02-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
247100	2019-02-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
247150	2019-02-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
247200	2019-02-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
247250	2019-02-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
247300	2019-02-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
247350	2019-02-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
247400	2019-02-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
247450	2019-02-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
247500	2019-02-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
247550	2019-02-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
247600	2019-02-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
247650	2019-02-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
247700	2019-02-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
247750	2019-02-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
247800	2019-02-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
247850	2019-02-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
247900	2019-02-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
247950	2019-02-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
248000	2019-02-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
248050	2019-02-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
248100	2019-02-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
248150	2019-02-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
248200	2019-02-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
248250	2019-02-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
248300	2019-02-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
248350	2019-02-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
248400	2019-02-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
248450	2019-02-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
248500	2019-02-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
248550	2019-02-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
248600	2019-02-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
248650	2019-03-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
248700	2019-03-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
248750	2019-03-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
248800	2019-03-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
248850	2019-03-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
248900	2019-03-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
248950	2019-03-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
249000	2019-03-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
249050	2019-03-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
249100	2019-03-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
249150	2019-03-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
249200	2019-03-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
249250	2019-03-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
249300	2019-03-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
249350	2019-03-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
249400	2019-03-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
249450	2019-03-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
249500	2019-03-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
249550	2019-03-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
249600	2019-03-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
249650	2019-03-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
249700	2019-03-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
249750	2019-03-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
249800	2019-03-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
249850	2019-03-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
249900	2019-03-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
249950	2019-03-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
250000	2019-03-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
250050	2019-03-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
250100	2019-03-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
250150	2019-03-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
250200	2019-03-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
250250	2019-03-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
250300	2019-03-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
250350	2019-03-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
250400	2019-03-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
250450	2019-03-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
250500	2019-03-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
250550	2019-03-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
250600	2019-03-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
250650	2019-03-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
250700	2019-03-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
250750	2019-03-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
250800	2019-03-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
250850	2019-03-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
250900	2019-03-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
250950	2019-03-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
251000	2019-03-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
251050	2019-04-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
251100	2019-04-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
251150	2019-04-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
251200	2019-04-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
251250	2019-04-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
251300	2019-04-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
251350	2019-04-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
251400	2019-04-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
251450	2019-04-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
251500	2019-04-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
251550	2019-04-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
251600	2019-04-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
251650	2019-04-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
251700	2019-04-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
251750	2019-04-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
251800	2019-04-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
251850	2019-04-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
251900	2019-04-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
251950	2019-04-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
252000	2019-04-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
252050	2019-04-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
252100	2019-04-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
252150	2019-04-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
252200	2019-04-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
252250	2019-04-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
252300	2019-04-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
252350	2019-04-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
252400	2019-04-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
252450	2019-04-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
252500	2019-04-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
252550	2019-04-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
252600	2019-04-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
252650	2019-04-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
252700	2019-04-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
252750	2019-04-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
252800	2019-04-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
252850	2019-04-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
252900	2019-04-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
252950	2019-04-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
253000	2019-04-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
253050	2019-04-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
253100	2019-04-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
253150	2019-04-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
253200	2019-04-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
253250	2019-04-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
253300	2019-04-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
253350	2019-04-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
253400	2019-04-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
253450	2019-04-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
253500	2019-04-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
253550	2019-04-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
253600	2019-04-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
253650	2019-04-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
253700	2019-04-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
253750	2019-04-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
253800	2019-04-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
253850	2019-04-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
253900	2019-04-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
253950	2019-04-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
254000	2019-04-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
254050	2019-04-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
254100	2019-04-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
254150	2019-04-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
254200	2019-04-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
254250	2019-04-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
254300	2019-04-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
254350	2019-04-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
254400	2019-04-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
254450	2019-04-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
254500	2019-04-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
254550	2019-04-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
254600	2019-04-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
254650	2019-04-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
254700	2019-04-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
254750	2019-04-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
254800	2019-04-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
254850	2019-04-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
254900	2019-04-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
254950	2019-04-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
255000	2019-04-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
255050	2019-05-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
255100	2019-05-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
255150	2019-05-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
255200	2019-05-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
255250	2019-05-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
255300	2019-05-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
255350	2019-05-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
255400	2019-05-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
255450	2019-05-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
255500	2019-05-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
255550	2019-05-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
255600	2019-05-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
255650	2019-05-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
255700	2019-05-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
255750	2019-05-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
255800	2019-05-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
255850	2019-05-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
255900	2019-05-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
255950	2019-05-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
256000	2019-05-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
256050	2019-05-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
256100	2019-05-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
256150	2019-05-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
256200	2019-05-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
256250	2019-05-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
256300	2019-05-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
256350	2019-05-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
256400	2019-05-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
256450	2019-05-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
256500	2019-05-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
256550	2019-05-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
256600	2019-05-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
256650	2019-05-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
256700	2019-05-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
256750	2019-05-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
256800	2019-05-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
256850	2019-05-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
256900	2019-05-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
256950	2019-05-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
257000	2019-05-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
257050	2019-05-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
257100	2019-05-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
257150	2019-05-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
257200	2019-05-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
257250	2019-05-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
257300	2019-05-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
257350	2019-05-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
257400	2019-05-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
257450	2019-05-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
257500	2019-05-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
257550	2019-05-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
257600	2019-05-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
257650	2019-05-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
257700	2019-05-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
257750	2019-05-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
257800	2019-05-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
257850	2019-05-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
257900	2019-05-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
257950	2019-05-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
258000	2019-05-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
258050	2019-05-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
258100	2019-05-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
258150	2019-05-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
258200	2019-05-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
258250	2019-06-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
258300	2019-06-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
258350	2019-06-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
258400	2019-06-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
258450	2019-06-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
258500	2019-06-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
258550	2019-06-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
258600	2019-06-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
258650	2019-06-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
258700	2019-06-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
258750	2019-06-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
258800	2019-06-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
258850	2019-06-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
258900	2019-06-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
258950	2019-06-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
259000	2019-06-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
259050	2019-06-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
259100	2019-06-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
259150	2019-06-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
259200	2019-06-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
259250	2019-06-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
259300	2019-06-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
259350	2019-06-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
259400	2019-06-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
259450	2019-06-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
259500	2019-06-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
259550	2019-06-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
259600	2019-06-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
259650	2019-06-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
259700	2019-06-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
259750	2019-06-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
259800	2019-06-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
259850	2019-06-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
259900	2019-06-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
259950	2019-06-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
260000	2019-06-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
260050	2019-06-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
260100	2019-06-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
260150	2019-06-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
260200	2019-06-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
260250	2019-06-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
260300	2019-06-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
260350	2019-06-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
260400	2019-06-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
260450	2019-06-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
260500	2019-06-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
260550	2019-06-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
260600	2019-06-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
260650	2019-06-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
260700	2019-06-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
260750	2019-06-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
260800	2019-06-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
260850	2019-06-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
260900	2019-06-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
260950	2019-06-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
261000	2019-06-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
261050	2019-06-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
261100	2019-06-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
261150	2019-06-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
261200	2019-06-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
261250	2019-06-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
261300	2019-06-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
261350	2019-06-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
261400	2019-06-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
261450	2019-01-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
261500	2019-01-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
261550	2019-01-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
261600	2019-01-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
261650	2019-01-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
261700	2019-01-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
261750	2019-01-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
261800	2019-01-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
261850	2019-01-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
261900	2019-01-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
261950	2019-01-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
262000	2019-01-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
262050	2019-01-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
262100	2019-01-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
262150	2019-01-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
262200	2019-01-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
262250	2019-01-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
262300	2019-01-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
262350	2019-01-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
262400	2019-01-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
262450	2019-01-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
262500	2019-01-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
262550	2019-01-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
262600	2019-01-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
262650	2019-01-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
262700	2019-01-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
262750	2019-01-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
262800	2019-01-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
262850	2019-01-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
262900	2019-01-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
262950	2019-01-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
263000	2019-01-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
263050	2019-01-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
263100	2019-01-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
263150	2019-01-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
263200	2019-01-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
263250	2019-01-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
263300	2019-01-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
263350	2019-01-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
263400	2019-01-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
263450	2019-01-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
263500	2019-01-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
263550	2019-01-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
263600	2019-01-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
263650	2019-01-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
263700	2019-01-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
263750	2019-01-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
263800	2019-01-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
263850	2019-02-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
263900	2019-02-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
263950	2019-02-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
264000	2019-02-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
264050	2019-02-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
264100	2019-02-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
264150	2019-02-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
264200	2019-02-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
264250	2019-02-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
264300	2019-02-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
264350	2019-02-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
264400	2019-02-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
264450	2019-02-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
264500	2019-02-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
264550	2019-02-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
264600	2019-02-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
264650	2019-02-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
264700	2019-02-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
264750	2019-02-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
264800	2019-02-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
264850	2019-02-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
264900	2019-02-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
264950	2019-02-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
265000	2019-02-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
265050	2019-02-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
265100	2019-02-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
265150	2019-02-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
265200	2019-02-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
265250	2019-02-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
265300	2019-02-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
265350	2019-02-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
265400	2019-02-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
265450	2019-02-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
265500	2019-02-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
265550	2019-02-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
265600	2019-02-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
265650	2019-02-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
265700	2019-02-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
265750	2019-02-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
265800	2019-02-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
265850	2019-02-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
265900	2019-02-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
265950	2019-02-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
266000	2019-02-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
266050	2019-02-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
266100	2019-02-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
266150	2019-02-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
266200	2019-02-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
266250	2019-02-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
266300	2019-02-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
266350	2019-02-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
266400	2019-02-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
266450	2019-02-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
266500	2019-02-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
266550	2019-02-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
266600	2019-02-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
266650	2019-02-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
266700	2019-02-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
266750	2019-02-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
266800	2019-02-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
266850	2019-02-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
266900	2019-02-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
266950	2019-02-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
267000	2019-02-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
267050	2019-03-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
267100	2019-03-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
267150	2019-03-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
267200	2019-03-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
267250	2019-03-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
267300	2019-03-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
267350	2019-03-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
267400	2019-03-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
267450	2019-03-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
267500	2019-03-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
267550	2019-03-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
267600	2019-03-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
267650	2019-03-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
267700	2019-03-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
267750	2019-03-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
267800	2019-03-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
267850	2019-03-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
267900	2019-03-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
267950	2019-03-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
268000	2019-03-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
268050	2019-03-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
268100	2019-03-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
268150	2019-03-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
268200	2019-03-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
268250	2019-03-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
268300	2019-03-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
268350	2019-03-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
268400	2019-03-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
268450	2019-03-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
268500	2019-03-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
268550	2019-03-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
268600	2019-03-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
268650	2019-03-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
268700	2019-03-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
268750	2019-03-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
268800	2019-03-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
268850	2019-03-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
268900	2019-03-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
268950	2019-03-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
269000	2019-03-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
269050	2019-03-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
269100	2019-03-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
269150	2019-03-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
269200	2019-03-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
269250	2019-03-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
269300	2019-03-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
269350	2019-03-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
269400	2019-03-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
269450	2019-03-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
269500	2019-03-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
269550	2019-03-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
269600	2019-03-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
269650	2019-03-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
269700	2019-03-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
269750	2019-03-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
269800	2019-03-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
269850	2019-03-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
269900	2019-03-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
269950	2019-03-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
270000	2019-03-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
270050	2019-03-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
270100	2019-03-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
270150	2019-03-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
270200	2019-03-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
270250	2019-04-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
270300	2019-04-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
270350	2019-04-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
270400	2019-04-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
270450	2019-04-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
270500	2019-04-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
270550	2019-04-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
270600	2019-04-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
270650	2019-04-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
270700	2019-04-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
270750	2019-04-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
270800	2019-04-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
270850	2019-04-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
270900	2019-04-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
270950	2019-04-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
271000	2019-04-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
271050	2019-04-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
271100	2019-04-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
271150	2019-04-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
271200	2019-04-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
271250	2019-04-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
271300	2019-04-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
271350	2019-04-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
271400	2019-04-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
271450	2019-04-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
271500	2019-04-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
271550	2019-04-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
271600	2019-04-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
271650	2019-04-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
271700	2019-04-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
271750	2019-04-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
271800	2019-04-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
271850	2019-04-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
271900	2019-04-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
271950	2019-04-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
272000	2019-04-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
272050	2019-04-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
272100	2019-04-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
272150	2019-04-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
272200	2019-04-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
272250	2019-04-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
272300	2019-04-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
272350	2019-04-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
272400	2019-04-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
272450	2019-04-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
272500	2019-04-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
272550	2019-04-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
272600	2019-04-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
272650	2019-04-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
272700	2019-04-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
272750	2019-04-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
272800	2019-04-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
272850	2019-04-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
272900	2019-04-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
272950	2019-04-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
273000	2019-04-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
273050	2019-04-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
273100	2019-04-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
273150	2019-04-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
273200	2019-04-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
273250	2019-04-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
273300	2019-04-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
273350	2019-04-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
273400	2019-04-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
273450	2019-04-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
273500	2019-04-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
273550	2019-04-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
273600	2019-04-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
273650	2019-04-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
273700	2019-04-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
273750	2019-04-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
273800	2019-04-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
273850	2019-04-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
273900	2019-04-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
273950	2019-04-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
274000	2019-04-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
274050	2019-04-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
274100	2019-04-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
274150	2019-04-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
274200	2019-04-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
274250	2019-05-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
274300	2019-05-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
274350	2019-05-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
274400	2019-05-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
274450	2019-05-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
274500	2019-05-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
274550	2019-05-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
274600	2019-05-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
274650	2019-05-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
274700	2019-05-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
274750	2019-05-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
274800	2019-05-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
274850	2019-05-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
274900	2019-05-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
274950	2019-05-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
275000	2019-05-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
275050	2019-05-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
275100	2019-05-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
275150	2019-05-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
275200	2019-05-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
275250	2019-05-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
275300	2019-05-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
275350	2019-05-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
275400	2019-05-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
275450	2019-05-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
275500	2019-05-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
275550	2019-05-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
275600	2019-05-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
275650	2019-05-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
275700	2019-05-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
275750	2019-05-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
275800	2019-05-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
275850	2019-05-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
275900	2019-05-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
275950	2019-05-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
276000	2019-05-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
276050	2019-05-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
276100	2019-05-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
276150	2019-05-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
276200	2019-05-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
276250	2019-05-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
276300	2019-05-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
276350	2019-05-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
276400	2019-05-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
276450	2019-05-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
276500	2019-05-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
276550	2019-05-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
276600	2019-05-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
276650	2019-05-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
276700	2019-05-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
276750	2019-05-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
276800	2019-05-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
276850	2019-05-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
276900	2019-05-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
276950	2019-05-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
277000	2019-05-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
277050	2019-05-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
277100	2019-05-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
277150	2019-05-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
277200	2019-05-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
277250	2019-05-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
277300	2019-05-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
277350	2019-05-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
277400	2019-05-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
277450	2019-06-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
277500	2019-06-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
277550	2019-06-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
277600	2019-06-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
277650	2019-06-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
277700	2019-06-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
277750	2019-06-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
277800	2019-06-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
277850	2019-06-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
277900	2019-06-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
277950	2019-06-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
278000	2019-06-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
278050	2019-06-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
278100	2019-06-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
278150	2019-06-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
278200	2019-06-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
278250	2019-06-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
278300	2019-06-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
278350	2019-06-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
278400	2019-06-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
278450	2019-06-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
278500	2019-06-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
278550	2019-06-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
278600	2019-06-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
278650	2019-06-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
278700	2019-06-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
278750	2019-06-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
278800	2019-06-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
278850	2019-06-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
278900	2019-06-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
278950	2019-06-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
279000	2019-06-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
279050	2019-06-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
279100	2019-06-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
279150	2019-06-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
279200	2019-06-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
279250	2019-06-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
279300	2019-06-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
279350	2019-06-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
279400	2019-06-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
279450	2019-06-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
279500	2019-06-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
279550	2019-06-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
279600	2019-06-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
279650	2019-06-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
279700	2019-06-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
279750	2019-06-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
279800	2019-06-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
279850	2019-06-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
279900	2019-06-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
279950	2019-06-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
280000	2019-06-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
280050	2019-06-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
280100	2019-06-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
280150	2019-06-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
280200	2019-06-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
280250	2019-06-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
280300	2019-06-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
280350	2019-06-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
280400	2019-06-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
280450	2019-06-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
280500	2019-06-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
280550	2019-06-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
280600	2019-06-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
280650	2019-01-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
280700	2019-01-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
280750	2019-01-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
280800	2019-01-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
280850	2019-01-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
280900	2019-01-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
280950	2019-01-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
281000	2019-01-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
281050	2019-01-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
281100	2019-01-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
281150	2019-01-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
281200	2019-01-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
281250	2019-01-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
281300	2019-01-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
281350	2019-01-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
281400	2019-01-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
281450	2019-01-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
281500	2019-01-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
281550	2019-01-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
281600	2019-01-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
281650	2019-01-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
281700	2019-01-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
281750	2019-01-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
281800	2019-01-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
281850	2019-01-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
281900	2019-01-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
281950	2019-01-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
282000	2019-01-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
282050	2019-01-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
282100	2019-01-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
282150	2019-01-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
282200	2019-01-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
282250	2019-01-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
282300	2019-01-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
282350	2019-01-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
282400	2019-01-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
282450	2019-01-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
282500	2019-01-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
282550	2019-01-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
282600	2019-01-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
282650	2019-01-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
282700	2019-01-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
282750	2019-01-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
282800	2019-01-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
282850	2019-01-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
282900	2019-01-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
282950	2019-01-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
283000	2019-01-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
283050	2019-01-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
283100	2019-01-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
283150	2019-01-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
283200	2019-01-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
283250	2019-01-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
283300	2019-01-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
283350	2019-01-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
283400	2019-01-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
283450	2019-01-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
283500	2019-01-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
283550	2019-01-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
283600	2019-01-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
283650	2019-01-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
283700	2019-01-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
283750	2019-01-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
283800	2019-01-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
283850	2019-01-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
283900	2019-01-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
283950	2019-01-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
284000	2019-01-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
284050	2019-01-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
284100	2019-01-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
284150	2019-01-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
284200	2019-01-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
284250	2019-01-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
284300	2019-01-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
284350	2019-01-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
284400	2019-01-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
284450	2019-01-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
284500	2019-01-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
284550	2019-01-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
284600	2019-01-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
284650	2019-02-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
284700	2019-02-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
284750	2019-02-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
284800	2019-02-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
284850	2019-02-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
284900	2019-02-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
284950	2019-02-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
285000	2019-02-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
285050	2019-02-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
285100	2019-02-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
285150	2019-02-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
285200	2019-02-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
285250	2019-02-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
285300	2019-02-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
285350	2019-02-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
285400	2019-02-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
285450	2019-02-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
285500	2019-02-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
285550	2019-02-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
285600	2019-02-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
285650	2019-02-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
285700	2019-02-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
285750	2019-02-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
285800	2019-02-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
285850	2019-02-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
285900	2019-02-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
285950	2019-02-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
286000	2019-02-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
286050	2019-02-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
286100	2019-02-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
286150	2019-02-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
286200	2019-02-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
286250	2019-02-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
286300	2019-02-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
286350	2019-02-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
286400	2019-02-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
286450	2019-02-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
286500	2019-02-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
286550	2019-02-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
286600	2019-02-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
286650	2019-02-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
286700	2019-02-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
286750	2019-02-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
286800	2019-02-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
286850	2019-02-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
286900	2019-02-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
286950	2019-02-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
287000	2019-02-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
287050	2019-02-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
287100	2019-02-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
287150	2019-02-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
287200	2019-02-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
287250	2019-02-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
287300	2019-02-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
287350	2019-02-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
287400	2019-02-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
287450	2019-02-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
287500	2019-02-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
287550	2019-02-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
287600	2019-02-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
287650	2019-02-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
287700	2019-02-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
287750	2019-02-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
287800	2019-02-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
287850	2019-03-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
287900	2019-03-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
287950	2019-03-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
288000	2019-03-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
288050	2019-03-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
288100	2019-03-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
288150	2019-03-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
288200	2019-03-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
288250	2019-03-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
288300	2019-03-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
288350	2019-03-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
288400	2019-03-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
288450	2019-03-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
288500	2019-03-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
288550	2019-03-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
288600	2019-03-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
288650	2019-03-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
288700	2019-03-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
288750	2019-03-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
288800	2019-03-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
288850	2019-03-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
288900	2019-03-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
288950	2019-03-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
289000	2019-03-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
289050	2019-03-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
289100	2019-03-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
289150	2019-03-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
289200	2019-03-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
289250	2019-03-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
289300	2019-03-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
289350	2019-03-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
289400	2019-03-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
289450	2019-03-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
289500	2019-03-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
289550	2019-03-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
289600	2019-03-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
289650	2019-03-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
289700	2019-03-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
289750	2019-03-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
289800	2019-03-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
289850	2019-03-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
289900	2019-03-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
289950	2019-03-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
290000	2019-03-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
290050	2019-03-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
290100	2019-03-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
290150	2019-03-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
290200	2019-03-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
290250	2019-03-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
290300	2019-03-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
290350	2019-03-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
290400	2019-03-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
290450	2019-03-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
290500	2019-03-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
290550	2019-03-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
290600	2019-03-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
290650	2019-03-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
290700	2019-03-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
290750	2019-03-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
290800	2019-03-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
290850	2019-03-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
290900	2019-03-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
290950	2019-03-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
291000	2019-03-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
291050	2019-04-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
291100	2019-04-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
291150	2019-04-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
291200	2019-04-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
291250	2019-04-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
291300	2019-04-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
291350	2019-04-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
291400	2019-04-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
291450	2019-04-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
291500	2019-04-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
291550	2019-04-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
291600	2019-04-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
291650	2019-04-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
291700	2019-04-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
291750	2019-04-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
291800	2019-04-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
291850	2019-04-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
291900	2019-04-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
291950	2019-04-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
292000	2019-04-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
292050	2019-04-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
292100	2019-04-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
292150	2019-04-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
292200	2019-04-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
292250	2019-04-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
292300	2019-04-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
292350	2019-04-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
292400	2019-04-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
292450	2019-04-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
292500	2019-04-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
292550	2019-04-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
292600	2019-04-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
292650	2019-04-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
292700	2019-04-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
292750	2019-04-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
292800	2019-04-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
292850	2019-04-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
292900	2019-04-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
292950	2019-04-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
293000	2019-04-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
293050	2019-04-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
293100	2019-04-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
293150	2019-04-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
293200	2019-04-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
293250	2019-04-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
293300	2019-04-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
293350	2019-04-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
293400	2019-04-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
293450	2019-04-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
293500	2019-04-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
293550	2019-04-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
293600	2019-04-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
293650	2019-04-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
293700	2019-04-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
293750	2019-04-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
293800	2019-04-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
293850	2019-04-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
293900	2019-04-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
293950	2019-04-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
294000	2019-04-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
294050	2019-04-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
294100	2019-04-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
294150	2019-04-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
294200	2019-04-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
294250	2019-05-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
294300	2019-05-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
294350	2019-05-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
294400	2019-05-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
294450	2019-05-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
294500	2019-05-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
294550	2019-05-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
294600	2019-05-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
294650	2019-05-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
294700	2019-05-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
294750	2019-05-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
294800	2019-05-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
294850	2019-05-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
294900	2019-05-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
294950	2019-05-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
295000	2019-05-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
295050	2019-05-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
295100	2019-05-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
295150	2019-05-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
295200	2019-05-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
295250	2019-05-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
295300	2019-05-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
295350	2019-05-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
295400	2019-05-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
295450	2019-05-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
295500	2019-05-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
295550	2019-05-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
295600	2019-05-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
295650	2019-05-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
295700	2019-05-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
295750	2019-05-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
295800	2019-05-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
295850	2019-05-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
295900	2019-05-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
295950	2019-05-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
296000	2019-05-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
296050	2019-05-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
296100	2019-05-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
296150	2019-05-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
296200	2019-05-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
296250	2019-05-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
296300	2019-05-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
296350	2019-05-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
296400	2019-05-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
296450	2019-05-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
296500	2019-05-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
296550	2019-05-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
296600	2019-05-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
296650	2019-05-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
296700	2019-05-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
296750	2019-05-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
296800	2019-05-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
296850	2019-05-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
296900	2019-05-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
296950	2019-05-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
297000	2019-05-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
297050	2019-05-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
297100	2019-05-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
297150	2019-05-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
297200	2019-05-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
297250	2019-05-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
297300	2019-05-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
297350	2019-05-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
297400	2019-05-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
297450	2019-05-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
297500	2019-05-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
297550	2019-05-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
297600	2019-05-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
297650	2019-05-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
297700	2019-05-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
297750	2019-05-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
297800	2019-05-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
297850	2019-05-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
297900	2019-05-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
297950	2019-05-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
298000	2019-05-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
298050	2019-05-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
298100	2019-05-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
298150	2019-05-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
298200	2019-05-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
298250	2019-06-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
298300	2019-06-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
298350	2019-06-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
298400	2019-06-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
298450	2019-06-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
298500	2019-06-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
298550	2019-06-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
298600	2019-06-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
298650	2019-06-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
298700	2019-06-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
298750	2019-06-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
298800	2019-06-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
298850	2019-06-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
298900	2019-06-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
298950	2019-06-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
299000	2019-06-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
299050	2019-06-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
299100	2019-06-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
299150	2019-06-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
299200	2019-06-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
299250	2019-06-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
299300	2019-06-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
299350	2019-06-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
299400	2019-06-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
299450	2019-06-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
299500	2019-06-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
299550	2019-06-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
299600	2019-06-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
299650	2019-06-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
299700	2019-06-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
299750	2019-06-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
299800	2019-06-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
299850	2019-06-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
299900	2019-06-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
299950	2019-06-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
300000	2019-06-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
300050	2019-06-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
300100	2019-06-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
300150	2019-06-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
300200	2019-06-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
300250	2019-06-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
300300	2019-06-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
300350	2019-06-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
300400	2019-06-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
300450	2019-06-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
300500	2019-06-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
300550	2019-06-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
300600	2019-06-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
300650	2019-06-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
300700	2019-06-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
300750	2019-06-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
300800	2019-06-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
300850	2019-06-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
300900	2019-06-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
300950	2019-06-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
301000	2019-06-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
301050	2019-06-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
301100	2019-06-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
301150	2019-06-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
301200	2019-06-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
301250	2019-06-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
301300	2019-06-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
301350	2019-06-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
301400	2019-06-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
301450	2019-01-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
301500	2019-01-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
301550	2019-01-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
301600	2019-01-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
301650	2019-01-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
301700	2019-01-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
301750	2019-01-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
301800	2019-01-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
301850	2019-01-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
301900	2019-01-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
301950	2019-01-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
302000	2019-01-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
302050	2019-01-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
302100	2019-01-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
302150	2019-01-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
302200	2019-01-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
302250	2019-01-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
302300	2019-01-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
302350	2019-01-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
302400	2019-01-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
302450	2019-01-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
302500	2019-01-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
302550	2019-01-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
302600	2019-01-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
302650	2019-01-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
302700	2019-01-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
302750	2019-01-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
302800	2019-01-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
302850	2019-01-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
302900	2019-01-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
302950	2019-01-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
303000	2019-01-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
303050	2019-01-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
303100	2019-01-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
303150	2019-01-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
303200	2019-01-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
303250	2019-01-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
303300	2019-01-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
303350	2019-01-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
303400	2019-01-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
303450	2019-01-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
303500	2019-01-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
303550	2019-01-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
303600	2019-01-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
303650	2019-01-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
303700	2019-01-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
303750	2019-01-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
303800	2019-01-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
303850	2019-01-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
303900	2019-01-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
303950	2019-01-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
304000	2019-01-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
304050	2019-01-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
304100	2019-01-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
304150	2019-01-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
304200	2019-01-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
304250	2019-01-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
304300	2019-01-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
304350	2019-01-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
304400	2019-01-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
304450	2019-01-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
304500	2019-01-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
304550	2019-01-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
304600	2019-01-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
304650	2019-01-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
304700	2019-01-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
304750	2019-01-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
304800	2019-01-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
304850	2019-01-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
304900	2019-01-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
304950	2019-01-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
305000	2019-01-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
305050	2019-01-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
305100	2019-01-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
305150	2019-01-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
305200	2019-01-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
305250	2019-01-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
305300	2019-01-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
305350	2019-01-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
305400	2019-01-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
305450	2019-02-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
305500	2019-02-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
305550	2019-02-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
305600	2019-02-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
305650	2019-02-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
305700	2019-02-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
305750	2019-02-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
305800	2019-02-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
305850	2019-02-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
305900	2019-02-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
305950	2019-02-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
306000	2019-02-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
306050	2019-02-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
306100	2019-02-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
306150	2019-02-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
306200	2019-02-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
306250	2019-02-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
306300	2019-02-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
306350	2019-02-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
306400	2019-02-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
306450	2019-02-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
306500	2019-02-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
306550	2019-02-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
306600	2019-02-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
306650	2019-02-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
306700	2019-02-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
306750	2019-02-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
306800	2019-02-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
306850	2019-02-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
306900	2019-02-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
306950	2019-02-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
307000	2019-02-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
307050	2019-02-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
307100	2019-02-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
307150	2019-02-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
307200	2019-02-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
307250	2019-02-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
307300	2019-02-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
307350	2019-02-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
307400	2019-02-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
307450	2019-02-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
307500	2019-02-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
307550	2019-02-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
307600	2019-02-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
307650	2019-02-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
307700	2019-02-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
307750	2019-02-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
307800	2019-02-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
307850	2019-02-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
307900	2019-02-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
307950	2019-02-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
308000	2019-02-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
308050	2019-02-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
308100	2019-02-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
308150	2019-02-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
308200	2019-02-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
308250	2019-02-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
308300	2019-02-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
308350	2019-02-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
308400	2019-02-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
308450	2019-02-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
308500	2019-02-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
308550	2019-02-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
308600	2019-02-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
308650	2019-03-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
308700	2019-03-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
308750	2019-03-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
308800	2019-03-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
308850	2019-03-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
308900	2019-03-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
308950	2019-03-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
309000	2019-03-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
309050	2019-03-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
309100	2019-03-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
309150	2019-03-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
309200	2019-03-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
309250	2019-03-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
309300	2019-03-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
309350	2019-03-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
309400	2019-03-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
309450	2019-03-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
309500	2019-03-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
309550	2019-03-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
309600	2019-03-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
309650	2019-03-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
309700	2019-03-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
309750	2019-03-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
309800	2019-03-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
309850	2019-03-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
309900	2019-03-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
309950	2019-03-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
310000	2019-03-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
310050	2019-03-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
310100	2019-03-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
310150	2019-03-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
310200	2019-03-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
310250	2019-03-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
310300	2019-03-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
310350	2019-03-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
310400	2019-03-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
310450	2019-03-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
310500	2019-03-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
310550	2019-03-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
310600	2019-03-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
310650	2019-03-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
310700	2019-03-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
310750	2019-03-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
310800	2019-03-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
310850	2019-03-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
310900	2019-03-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
310950	2019-03-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
311000	2019-03-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
311050	2019-04-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
311100	2019-04-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
311150	2019-04-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
311200	2019-04-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
311250	2019-04-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
311300	2019-04-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
311350	2019-04-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
311400	2019-04-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
311450	2019-04-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
311500	2019-04-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
311550	2019-04-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
311600	2019-04-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
311650	2019-04-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
311700	2019-04-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
311750	2019-04-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
311800	2019-04-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
311850	2019-04-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
311900	2019-04-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
311950	2019-04-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
312000	2019-04-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
312050	2019-04-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
312100	2019-04-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
312150	2019-04-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
312200	2019-04-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
312250	2019-04-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
312300	2019-04-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
312350	2019-04-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
312400	2019-04-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
312450	2019-04-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
312500	2019-04-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
312550	2019-04-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
312600	2019-04-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
312650	2019-04-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
312700	2019-04-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
312750	2019-04-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
312800	2019-04-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
312850	2019-04-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
312900	2019-04-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
312950	2019-04-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
313000	2019-04-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
313050	2019-04-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
313100	2019-04-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
313150	2019-04-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
313200	2019-04-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
313250	2019-04-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
313300	2019-04-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
313350	2019-04-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
313400	2019-04-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
313450	2019-04-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
313500	2019-04-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
313550	2019-04-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
313600	2019-04-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
313650	2019-04-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
313700	2019-04-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
313750	2019-04-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
313800	2019-04-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
313850	2019-04-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
313900	2019-04-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
313950	2019-04-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
314000	2019-04-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
314050	2019-04-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
314100	2019-04-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
314150	2019-04-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
314200	2019-04-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
314250	2019-05-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
314300	2019-05-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
314350	2019-05-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
314400	2019-05-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
314450	2019-05-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
314500	2019-05-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
314550	2019-05-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
314600	2019-05-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
314650	2019-05-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
314700	2019-05-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
314750	2019-05-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
314800	2019-05-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
314850	2019-05-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
314900	2019-05-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
314950	2019-05-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
315000	2019-05-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
315050	2019-05-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
315100	2019-05-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
315150	2019-05-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
315200	2019-05-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
315250	2019-05-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
315300	2019-05-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
315350	2019-05-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
315400	2019-05-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
315450	2019-05-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
315500	2019-05-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
315550	2019-05-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
315600	2019-05-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
315650	2019-05-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
315700	2019-05-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
315750	2019-05-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
315800	2019-05-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
315850	2019-05-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
315900	2019-05-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
315950	2019-05-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
316000	2019-05-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
316050	2019-05-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
316100	2019-05-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
316150	2019-05-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
316200	2019-05-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
316250	2019-05-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
316300	2019-05-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
316350	2019-05-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
316400	2019-05-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
316450	2019-05-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
316500	2019-05-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
316550	2019-05-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
316600	2019-05-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
316650	2019-05-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
316700	2019-05-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
316750	2019-05-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
316800	2019-05-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
316850	2019-05-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
316900	2019-05-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
316950	2019-05-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
317000	2019-05-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
317050	2019-05-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
317100	2019-05-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
317150	2019-05-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
317200	2019-05-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
317250	2019-05-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
317300	2019-05-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
317350	2019-05-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
317400	2019-05-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
317450	2019-05-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
317500	2019-05-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
317550	2019-05-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
317600	2019-05-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
317650	2019-05-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
317700	2019-05-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
317750	2019-05-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
317800	2019-05-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
317850	2019-05-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
317900	2019-05-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
317950	2019-05-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
318000	2019-05-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
318050	2019-05-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
318100	2019-05-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
318150	2019-05-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
318200	2019-05-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
318250	2019-06-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
318300	2019-06-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
318350	2019-06-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
318400	2019-06-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
318450	2019-06-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
318500	2019-06-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
318550	2019-06-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
318600	2019-06-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
318650	2019-06-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
318700	2019-06-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
318750	2019-06-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
318800	2019-06-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
318850	2019-06-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
318900	2019-06-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
318950	2019-06-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
319000	2019-06-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
319050	2019-06-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
319100	2019-06-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
319150	2019-06-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
319200	2019-06-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
319250	2019-06-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
319300	2019-06-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
319350	2019-06-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
319400	2019-06-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
319450	2019-06-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
319500	2019-06-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
319550	2019-06-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
319600	2019-06-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
319650	2019-06-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
319700	2019-06-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
319750	2019-06-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
319800	2019-06-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
319850	2019-06-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
319900	2019-06-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
319950	2019-06-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
320000	2019-06-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
320050	2019-06-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
320100	2019-06-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
320150	2019-06-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
320200	2019-06-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
320250	2019-06-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
320300	2019-06-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
320350	2019-06-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
320400	2019-06-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
320450	2019-06-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
320500	2019-06-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
320550	2019-06-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
320600	2019-06-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
320650	2019-06-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
320700	2019-06-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
320750	2019-06-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
320800	2019-06-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
320850	2019-06-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
320900	2019-06-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
320950	2019-06-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
321000	2019-06-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
321050	2019-06-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
321100	2019-06-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
321150	2019-06-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
321200	2019-06-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
321250	2019-06-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
321300	2019-06-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
321350	2019-06-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
321400	2019-06-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
321450	2019-01-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
321500	2019-01-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
321550	2019-01-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
321600	2019-01-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
321650	2019-01-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
321700	2019-01-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
321750	2019-01-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
321800	2019-01-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
321850	2019-01-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
321900	2019-01-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
321950	2019-01-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
322000	2019-01-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
322050	2019-01-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
322100	2019-01-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
322150	2019-01-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
322200	2019-01-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
322250	2019-01-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
322300	2019-01-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
322350	2019-01-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
322400	2019-01-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
322450	2019-01-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
322500	2019-01-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
322550	2019-01-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
322600	2019-01-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
322650	2019-01-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
322700	2019-01-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
322750	2019-01-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
322800	2019-01-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
322850	2019-01-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
322900	2019-01-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
322950	2019-01-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
323000	2019-01-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
323050	2019-01-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
323100	2019-01-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
323150	2019-01-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
323200	2019-01-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
323250	2019-01-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
323300	2019-01-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
323350	2019-01-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
323400	2019-01-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
323450	2019-01-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
323500	2019-01-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
323550	2019-01-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
323600	2019-01-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
323650	2019-01-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
323700	2019-01-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
323750	2019-01-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
323800	2019-01-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
323850	2019-01-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
323900	2019-01-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
323950	2019-01-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
324000	2019-01-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
324050	2019-01-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
324100	2019-01-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
324150	2019-01-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
324200	2019-01-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
324250	2019-01-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
324300	2019-01-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
324350	2019-01-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
324400	2019-01-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
324450	2019-01-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
324500	2019-01-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
324550	2019-01-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
324600	2019-01-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
324650	2019-02-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
324700	2019-02-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
324750	2019-02-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
324800	2019-02-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
324850	2019-02-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
324900	2019-02-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
324950	2019-02-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
325000	2019-02-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
325050	2019-02-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
325100	2019-02-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
325150	2019-02-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
325200	2019-02-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
325250	2019-02-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
325300	2019-02-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
325350	2019-02-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
325400	2019-02-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
325450	2019-02-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
325500	2019-02-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
325550	2019-02-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
325600	2019-02-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
325650	2019-02-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
325700	2019-02-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
325750	2019-02-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
325800	2019-02-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
325850	2019-02-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
325900	2019-02-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
325950	2019-02-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
326000	2019-02-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
326050	2019-02-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
326100	2019-02-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
326150	2019-02-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
326200	2019-02-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
326250	2019-02-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
326300	2019-02-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
326350	2019-02-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
326400	2019-02-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
326450	2019-02-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
326500	2019-02-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
326550	2019-02-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
326600	2019-02-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
326650	2019-02-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
326700	2019-02-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
326750	2019-02-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
326800	2019-02-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
326850	2019-02-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
326900	2019-02-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
326950	2019-02-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
327000	2019-02-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
327050	2019-02-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
327100	2019-02-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
327150	2019-02-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
327200	2019-02-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
327250	2019-02-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
327300	2019-02-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
327350	2019-02-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
327400	2019-02-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
327450	2019-02-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
327500	2019-02-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
327550	2019-02-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
327600	2019-02-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
327650	2019-02-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
327700	2019-02-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
327750	2019-02-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
327800	2019-02-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
327850	2019-03-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
327900	2019-03-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
327950	2019-03-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
328000	2019-03-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
328050	2019-03-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
328100	2019-03-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
328150	2019-03-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
328200	2019-03-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
328250	2019-03-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
328300	2019-03-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
328350	2019-03-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
328400	2019-03-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
328450	2019-03-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
328500	2019-03-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
328550	2019-03-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
328600	2019-03-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
328650	2019-03-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
328700	2019-03-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
328750	2019-03-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
328800	2019-03-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
328850	2019-03-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
328900	2019-03-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
328950	2019-03-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
329000	2019-03-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
329050	2019-03-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
329100	2019-03-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
329150	2019-03-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
329200	2019-03-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
329250	2019-03-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
329300	2019-03-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
329350	2019-03-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
329400	2019-03-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
329450	2019-03-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
329500	2019-03-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
329550	2019-03-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
329600	2019-03-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
329650	2019-03-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
329700	2019-03-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
329750	2019-03-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
329800	2019-03-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
329850	2019-03-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
329900	2019-03-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
329950	2019-03-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
330000	2019-03-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
330050	2019-03-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
330100	2019-03-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
330150	2019-03-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
330200	2019-03-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
330250	2019-03-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
330300	2019-03-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
330350	2019-03-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
330400	2019-03-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
330450	2019-03-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
330500	2019-03-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
330550	2019-03-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
330600	2019-03-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
330650	2019-03-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
330700	2019-03-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
330750	2019-03-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
330800	2019-03-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
330850	2019-03-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
330900	2019-03-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
330950	2019-03-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
331000	2019-03-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
331050	2019-03-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
331100	2019-03-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
331150	2019-03-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
331200	2019-03-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
331250	2019-03-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
331300	2019-03-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
331350	2019-03-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
331400	2019-03-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
331450	2019-03-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
331500	2019-03-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
331550	2019-03-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
331600	2019-03-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
331650	2019-03-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
331700	2019-03-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
331750	2019-03-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
331800	2019-03-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
331850	2019-04-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
331900	2019-04-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
331950	2019-04-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
332000	2019-04-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
332050	2019-04-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
332100	2019-04-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
332150	2019-04-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
332200	2019-04-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
332250	2019-04-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
332300	2019-04-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
332350	2019-04-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
332400	2019-04-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
332450	2019-04-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
332500	2019-04-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
332550	2019-04-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
332600	2019-04-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
332650	2019-04-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
332700	2019-04-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
332750	2019-04-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
332800	2019-04-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
332850	2019-04-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
332900	2019-04-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
332950	2019-04-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
333000	2019-04-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
333050	2019-04-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
333100	2019-04-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
333150	2019-04-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
333200	2019-04-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
333250	2019-04-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
333300	2019-04-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
333350	2019-04-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
333400	2019-04-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
333450	2019-04-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
333500	2019-04-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
333550	2019-04-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
333600	2019-04-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
333650	2019-04-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
333700	2019-04-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
333750	2019-04-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
333800	2019-04-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
333850	2019-04-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
333900	2019-04-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
333950	2019-04-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
334000	2019-04-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
334050	2019-04-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
334100	2019-04-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
334150	2019-04-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
334200	2019-04-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
334250	2019-04-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
334300	2019-04-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
334350	2019-04-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
334400	2019-04-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
334450	2019-04-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
334500	2019-04-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
334550	2019-04-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
334600	2019-04-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
334650	2019-04-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
334700	2019-04-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
334750	2019-04-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
334800	2019-04-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
334850	2019-04-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
334900	2019-04-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
334950	2019-04-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
335000	2019-04-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
335050	2019-05-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
335100	2019-05-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
335150	2019-05-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
335200	2019-05-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
335250	2019-05-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
335300	2019-05-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
335350	2019-05-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
335400	2019-05-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
335450	2019-05-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
335500	2019-05-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
335550	2019-05-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
335600	2019-05-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
335650	2019-05-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
335700	2019-05-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
335750	2019-05-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
335800	2019-05-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
335850	2019-05-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
335900	2019-05-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
335950	2019-05-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
336000	2019-05-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
336050	2019-05-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
336100	2019-05-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
336150	2019-05-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
336200	2019-05-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
336250	2019-05-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
336300	2019-05-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
336350	2019-05-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
336400	2019-05-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
336450	2019-05-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
336500	2019-05-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
336550	2019-05-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
336600	2019-05-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
336650	2019-05-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
336700	2019-05-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
336750	2019-05-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
336800	2019-05-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
336850	2019-05-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
336900	2019-05-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
336950	2019-05-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
337000	2019-05-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
337050	2019-05-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
337100	2019-05-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
337150	2019-05-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
337200	2019-05-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
337250	2019-05-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
337300	2019-05-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
337350	2019-05-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
337400	2019-05-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
337450	2019-05-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
337500	2019-05-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
337550	2019-05-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
337600	2019-05-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
337650	2019-05-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
337700	2019-05-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
337750	2019-05-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
337800	2019-05-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
337850	2019-05-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
337900	2019-05-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
337950	2019-05-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
338000	2019-05-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
338050	2019-05-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
338100	2019-05-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
338150	2019-05-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
338200	2019-05-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
338250	2019-05-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
338300	2019-05-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
338350	2019-05-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
338400	2019-05-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
338450	2019-05-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
338500	2019-05-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
338550	2019-05-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
338600	2019-05-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
338650	2019-05-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
338700	2019-05-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
338750	2019-05-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
338800	2019-05-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
338850	2019-05-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
338900	2019-05-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
338950	2019-05-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
339000	2019-05-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
339050	2019-06-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
339100	2019-06-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
339150	2019-06-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
339200	2019-06-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
339250	2019-06-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
339300	2019-06-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
339350	2019-06-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
339400	2019-06-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
339450	2019-06-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
339500	2019-06-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
339550	2019-06-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
339600	2019-06-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
339650	2019-06-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
339700	2019-06-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
339750	2019-06-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
339800	2019-06-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
339850	2019-06-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
339900	2019-06-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
339950	2019-06-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
340000	2019-06-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
340050	2019-06-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
340100	2019-06-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
340150	2019-06-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
340200	2019-06-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
340250	2019-06-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
340300	2019-06-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
340350	2019-06-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
340400	2019-06-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
340450	2019-06-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
340500	2019-06-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
340550	2019-06-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
340600	2019-06-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
340650	2019-06-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
340700	2019-06-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
340750	2019-06-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
340800	2019-06-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
340850	2019-06-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
340900	2019-06-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
340950	2019-06-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
341000	2019-06-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
341050	2019-06-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
341100	2019-06-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
341150	2019-06-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
341200	2019-06-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
341250	2019-06-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
341300	2019-06-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
341350	2019-06-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
341400	2019-06-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
341450	2019-06-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	17900
341500	2019-06-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	17950
341550	2019-06-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18000
341600	2019-06-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18050
341650	2019-06-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18100
341700	2019-06-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18150
341750	2019-06-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18200
341800	2019-06-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18250
341850	2019-06-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18300
341900	2019-06-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18350
341950	2019-06-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18400
342000	2019-06-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18450
342050	2019-06-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	18500
342100	2019-06-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	18550
342150	2019-06-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	18600
342200	2019-06-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	18650
443550	2019-07-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
443600	2019-07-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
443650	2019-07-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
443700	2019-07-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
443750	2019-07-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
443800	2019-07-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
443850	2019-07-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
443900	2019-07-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
443950	2019-07-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
444000	2019-07-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
444050	2019-07-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
444100	2019-07-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
444150	2019-07-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
444200	2019-07-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
444250	2019-07-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
444300	2019-07-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
444350	2019-07-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
444400	2019-07-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
444450	2019-07-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
444500	2019-07-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
444550	2019-07-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
444600	2019-07-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
444650	2019-07-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
444700	2019-07-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
444750	2019-07-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
444800	2019-08-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
444850	2019-08-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
444900	2019-08-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
444950	2019-08-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
445000	2019-08-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
445050	2019-08-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
445100	2019-08-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
445150	2019-08-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
445200	2019-08-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
445250	2019-08-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
445300	2019-08-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
445350	2019-08-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
445400	2019-08-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
445450	2019-08-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
445500	2019-08-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
445550	2019-08-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
445600	2019-08-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
445650	2019-08-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
445700	2019-08-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
445750	2019-08-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
445800	2019-09-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
445850	2019-09-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
445900	2019-09-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
445950	2019-09-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
446000	2019-09-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
446050	2019-09-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
446100	2019-09-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
446150	2019-09-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
446200	2019-09-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
446250	2019-09-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
446300	2019-09-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
446350	2019-09-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
446400	2019-09-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
446450	2019-09-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
446500	2019-09-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
446550	2019-09-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
446600	2019-09-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
446650	2019-09-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
446700	2019-09-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
446750	2019-09-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
446800	2019-09-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
446850	2019-09-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
446900	2019-09-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
446950	2019-09-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
447000	2019-09-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
447050	2019-10-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
447100	2019-10-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
447150	2019-10-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
447200	2019-10-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
447250	2019-10-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
447300	2019-10-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
447350	2019-10-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
447400	2019-10-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
447450	2019-10-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
447500	2019-10-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
447550	2019-10-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
447600	2019-10-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
447650	2019-10-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
447700	2019-10-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
447750	2019-10-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
447800	2019-10-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
447850	2019-10-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
447900	2019-10-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
447950	2019-10-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
448000	2019-10-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
448050	2019-11-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
448100	2019-11-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
448150	2019-11-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
448200	2019-11-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
448250	2019-11-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
448300	2019-11-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
448350	2019-11-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
448400	2019-11-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
448450	2019-11-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
448500	2019-11-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
448550	2019-11-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
448600	2019-11-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
448650	2019-11-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
448700	2019-11-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
448750	2019-11-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
448800	2019-11-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
448850	2019-11-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
448900	2019-11-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
448950	2019-11-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
449000	2019-11-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
449050	2019-12-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
449100	2019-12-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
449150	2019-12-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
449200	2019-12-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
449250	2019-12-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
449300	2019-12-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
449350	2019-12-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
449400	2019-12-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
449450	2019-12-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
449500	2019-12-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
449550	2019-12-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
449600	2019-12-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
449650	2019-12-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
449700	2019-12-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
449750	2019-12-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
449800	2019-12-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
449850	2019-12-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
449900	2019-12-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
449950	2019-12-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
450000	2019-12-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
450050	2019-12-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
450100	2019-12-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
450150	2019-12-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
450200	2019-12-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
450250	2019-12-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
450300	2019-07-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
450350	2019-07-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
450400	2019-07-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
450450	2019-07-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
450500	2019-07-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
450550	2019-07-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
450600	2019-07-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
450650	2019-07-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
450700	2019-07-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
450750	2019-07-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
450800	2019-07-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
450850	2019-07-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
450900	2019-07-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
450950	2019-07-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
451000	2019-07-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
451050	2019-07-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
451100	2019-07-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
451150	2019-07-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
451200	2019-07-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
451250	2019-07-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
451300	2019-07-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
451350	2019-07-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
451400	2019-07-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
451450	2019-07-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
451500	2019-07-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
451550	2019-08-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
451600	2019-08-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
451650	2019-08-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
451700	2019-08-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
451750	2019-08-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
451800	2019-08-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
451850	2019-08-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
451900	2019-08-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
451950	2019-08-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
452000	2019-08-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
452050	2019-08-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
452100	2019-08-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
452150	2019-08-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
452200	2019-08-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
452250	2019-08-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
452300	2019-08-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
452350	2019-08-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
452400	2019-08-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
452450	2019-08-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
452500	2019-08-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
452550	2019-09-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
452600	2019-09-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
452650	2019-09-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
452700	2019-09-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
452750	2019-09-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
452800	2019-09-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
452850	2019-09-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
452900	2019-09-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
452950	2019-09-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
453000	2019-09-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
453050	2019-09-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
453100	2019-09-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
453150	2019-09-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
453200	2019-09-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
453250	2019-09-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
453300	2019-09-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
453350	2019-09-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
453400	2019-09-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
453450	2019-09-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
453500	2019-09-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
453550	2019-10-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
453600	2019-10-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
453650	2019-10-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
453700	2019-10-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
453750	2019-10-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
453800	2019-10-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
453850	2019-10-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
453900	2019-10-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
453950	2019-10-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
454000	2019-10-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
454050	2019-10-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
454100	2019-10-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
454150	2019-10-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
454200	2019-10-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
454250	2019-10-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
454300	2019-10-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
454350	2019-10-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
454400	2019-10-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
454450	2019-10-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
454500	2019-10-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
454550	2019-10-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
454600	2019-10-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
454650	2019-10-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
454700	2019-10-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
454750	2019-10-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
454800	2019-11-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
454850	2019-11-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
454900	2019-11-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
454950	2019-11-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
455000	2019-11-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
455050	2019-11-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
455100	2019-11-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
455150	2019-11-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
455200	2019-11-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
455250	2019-11-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
455300	2019-11-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
455350	2019-11-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
455400	2019-11-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
455450	2019-11-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
455500	2019-11-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
455550	2019-11-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
455600	2019-11-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
455650	2019-11-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
455700	2019-11-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
455750	2019-11-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
455800	2019-12-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
455850	2019-12-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
455900	2019-12-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
455950	2019-12-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
456000	2019-12-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
456050	2019-12-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
456100	2019-12-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
456150	2019-12-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
456200	2019-12-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
456250	2019-12-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
456300	2019-12-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
456350	2019-12-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
456400	2019-12-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
456450	2019-12-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
456500	2019-12-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
456550	2019-12-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
456600	2019-12-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
456650	2019-12-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
456700	2019-12-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
456750	2019-12-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
456800	2019-12-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
456850	2019-12-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
456900	2019-12-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
456950	2019-12-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
457000	2019-12-31	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
457050	2019-07-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
457100	2019-07-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
457150	2019-07-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
457200	2019-07-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
457250	2019-07-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
457300	2019-07-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
457350	2019-07-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
457400	2019-07-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
457450	2019-07-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
457500	2019-07-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
457550	2019-07-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
457600	2019-07-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
457650	2019-07-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
457700	2019-07-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
457750	2019-07-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
457800	2019-07-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
457850	2019-07-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
457900	2019-07-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
457950	2019-07-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
458000	2019-07-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
458050	2019-07-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
458100	2019-07-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
458150	2019-07-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
458200	2019-07-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
458250	2019-07-31	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
458300	2019-08-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
458350	2019-08-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
458400	2019-08-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
458450	2019-08-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
458500	2019-08-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
458550	2019-08-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
458600	2019-08-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
458650	2019-08-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
458700	2019-08-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
458750	2019-08-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
458800	2019-08-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
458850	2019-08-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
458900	2019-08-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
458950	2019-08-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
459000	2019-08-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
459050	2019-08-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
459100	2019-08-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
459150	2019-08-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
459200	2019-08-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
459250	2019-08-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
459300	2019-09-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
459350	2019-09-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
459400	2019-09-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
459450	2019-09-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
459500	2019-09-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
459550	2019-09-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
459600	2019-09-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
459650	2019-09-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
459700	2019-09-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
459750	2019-09-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
459800	2019-09-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
459850	2019-09-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
459900	2019-09-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
459950	2019-09-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
460000	2019-09-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
460050	2019-09-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
460100	2019-09-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
460150	2019-09-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
460200	2019-09-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
460250	2019-09-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
460300	2019-10-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
460350	2019-10-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
460400	2019-10-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
460450	2019-10-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
460500	2019-10-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
460550	2019-10-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
460600	2019-10-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
460650	2019-10-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
460700	2019-10-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
460750	2019-10-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
460800	2019-10-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
460850	2019-10-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
460900	2019-10-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
460950	2019-10-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
461000	2019-10-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
461050	2019-10-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
461100	2019-10-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
461150	2019-10-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
461200	2019-10-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
461250	2019-10-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
461300	2019-10-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
461350	2019-10-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
461400	2019-10-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
461450	2019-10-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
461500	2019-10-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
461550	2019-11-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
461600	2019-11-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
461650	2019-11-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
461700	2019-11-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
461750	2019-11-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
461800	2019-11-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
461850	2019-11-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
461900	2019-11-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
461950	2019-11-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
462000	2019-11-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
462050	2019-11-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
462100	2019-11-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
462150	2019-11-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
462200	2019-11-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
462250	2019-11-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
462300	2019-11-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
462350	2019-11-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
462400	2019-11-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
462450	2019-11-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
462500	2019-11-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
462550	2019-12-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
462600	2019-12-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
462650	2019-12-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
462700	2019-12-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
462750	2019-12-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
462800	2019-12-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
462850	2019-12-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
462900	2019-12-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
462950	2019-12-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
463000	2019-12-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
463050	2019-12-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
463100	2019-12-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
463150	2019-12-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
463200	2019-12-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
463250	2019-12-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
463300	2019-07-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
463350	2019-07-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
463400	2019-07-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
463450	2019-07-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
463500	2019-07-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
463550	2019-07-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
463600	2019-07-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
463650	2019-07-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
463700	2019-07-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
463750	2019-07-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
463800	2019-07-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
463850	2019-07-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
463900	2019-07-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
463950	2019-07-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
464000	2019-07-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
464050	2019-07-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
464100	2019-07-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
464150	2019-07-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
464200	2019-07-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
464250	2019-07-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
464300	2019-08-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
464350	2019-08-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
464400	2019-08-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
464450	2019-08-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
464500	2019-08-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
464550	2019-08-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
464600	2019-08-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
464650	2019-08-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
464700	2019-08-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
464750	2019-08-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
464800	2019-08-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
464850	2019-08-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
464900	2019-08-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
464950	2019-08-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
465000	2019-08-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
465050	2019-08-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
465100	2019-08-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
465150	2019-08-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
465200	2019-08-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
465250	2019-08-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
465300	2019-08-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
465350	2019-08-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
465400	2019-08-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
465450	2019-08-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
465500	2019-08-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
465550	2019-09-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
465600	2019-09-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
465650	2019-09-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
465700	2019-09-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
465750	2019-09-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
465800	2019-09-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
465850	2019-09-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
465900	2019-09-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
465950	2019-09-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
466000	2019-09-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
466050	2019-09-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
466100	2019-09-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
466150	2019-09-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
466200	2019-09-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
466250	2019-09-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
466300	2019-09-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
466350	2019-09-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
466400	2019-09-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
466450	2019-09-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
466500	2019-09-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
466550	2019-10-03	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
466600	2019-10-03	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
466650	2019-10-03	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
466700	2019-10-03	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
466750	2019-10-03	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
466800	2019-10-10	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
466850	2019-10-10	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
466900	2019-10-10	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
466950	2019-10-10	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
467000	2019-10-10	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
467050	2019-10-17	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
467100	2019-10-17	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
467150	2019-10-17	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
467200	2019-10-17	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
467250	2019-10-17	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
467300	2019-10-24	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
467350	2019-10-24	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
467400	2019-10-24	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
467450	2019-10-24	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
467500	2019-10-24	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
467550	2019-10-31	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
467600	2019-10-31	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
467650	2019-10-31	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
467700	2019-10-31	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
467750	2019-10-31	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
467800	2019-11-07	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
467850	2019-11-07	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
467900	2019-11-07	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
467950	2019-11-07	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
468000	2019-11-07	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
468050	2019-11-14	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
468100	2019-11-14	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
468150	2019-11-14	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
468200	2019-11-14	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
468250	2019-11-14	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
468300	2019-11-21	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
468350	2019-11-21	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
468400	2019-11-21	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
468450	2019-11-21	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
468500	2019-11-21	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
468550	2019-11-28	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
468600	2019-11-28	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
468650	2019-11-28	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
468700	2019-11-28	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
468750	2019-11-28	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
468800	2019-12-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
468850	2019-12-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
468900	2019-12-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
468950	2019-12-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
469000	2019-12-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
469050	2019-12-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
469100	2019-12-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
469150	2019-12-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
469200	2019-12-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
469250	2019-12-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
469300	2019-12-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
469350	2019-12-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
469400	2019-12-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
469450	2019-12-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
469500	2019-12-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
469550	2019-12-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
469600	2019-12-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
469650	2019-12-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
469700	2019-12-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
469750	2019-12-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
469800	2019-07-05	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
469850	2019-07-05	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
469900	2019-07-05	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
469950	2019-07-05	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
470000	2019-07-05	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
470050	2019-07-12	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
470100	2019-07-12	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
470150	2019-07-12	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
470200	2019-07-12	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
470250	2019-07-12	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
470300	2019-07-19	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
470350	2019-07-19	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
470400	2019-07-19	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
470450	2019-07-19	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
470500	2019-07-19	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
470550	2019-07-26	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
470600	2019-07-26	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
470650	2019-07-26	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
470700	2019-07-26	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
470750	2019-07-26	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
470800	2019-08-02	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
470850	2019-08-02	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
470900	2019-08-02	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
470950	2019-08-02	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
471000	2019-08-02	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
471050	2019-08-09	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
471100	2019-08-09	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
471150	2019-08-09	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
471200	2019-08-09	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
471250	2019-08-09	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
471300	2019-08-16	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
471350	2019-08-16	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
471400	2019-08-16	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
471450	2019-08-16	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
471500	2019-08-16	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
471550	2019-08-23	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
471600	2019-08-23	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
471650	2019-08-23	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
471700	2019-08-23	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
471750	2019-08-23	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
471800	2019-08-30	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
471850	2019-08-30	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
471900	2019-08-30	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
471950	2019-08-30	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
472000	2019-08-30	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
472050	2019-09-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
472100	2019-09-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
472150	2019-09-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
472200	2019-09-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
472250	2019-09-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
472300	2019-09-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
472350	2019-09-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
472400	2019-09-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
472450	2019-09-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
472500	2019-09-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
472550	2019-09-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
472600	2019-09-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
472650	2019-09-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
472700	2019-09-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
472750	2019-09-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
472800	2019-09-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
472850	2019-09-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
472900	2019-09-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
472950	2019-09-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
473000	2019-09-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
473050	2019-10-04	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
473100	2019-10-04	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
473150	2019-10-04	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
473200	2019-10-04	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
473250	2019-10-04	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
473300	2019-10-11	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
473350	2019-10-11	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
473400	2019-10-11	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
473450	2019-10-11	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
473500	2019-10-11	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
473550	2019-10-18	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
473600	2019-10-18	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
473650	2019-10-18	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
473700	2019-10-18	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
473750	2019-10-18	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
473800	2019-10-25	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
473850	2019-10-25	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
473900	2019-10-25	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
473950	2019-10-25	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
474000	2019-10-25	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
474050	2019-11-01	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
474100	2019-11-01	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
474150	2019-11-01	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
474200	2019-11-01	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
474250	2019-11-01	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
474300	2019-11-08	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
474350	2019-11-08	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
474400	2019-11-08	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
474450	2019-11-08	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
474500	2019-11-08	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
474550	2019-11-15	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
474600	2019-11-15	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
474650	2019-11-15	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
474700	2019-11-15	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
474750	2019-11-15	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
474800	2019-11-22	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
474850	2019-11-22	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
474900	2019-11-22	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
474950	2019-11-22	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
475000	2019-11-22	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
475050	2019-11-29	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
475100	2019-11-29	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
475150	2019-11-29	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
475200	2019-11-29	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
475250	2019-11-29	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
475300	2019-12-06	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
475350	2019-12-06	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
475400	2019-12-06	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
475450	2019-12-06	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
475500	2019-12-06	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
475550	2019-12-13	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
475600	2019-12-13	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
475650	2019-12-13	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
475700	2019-12-13	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
475750	2019-12-13	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
475800	2019-12-20	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
475850	2019-12-20	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
475900	2019-12-20	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
475950	2019-12-20	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
476000	2019-12-20	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
476050	2019-12-27	2019-01-30	Application User	7:30:00Am	8:30:00Am	1451
476100	2019-12-27	2019-01-30	Application User	8:30:00Am	9:30:00Am	1452
476150	2019-12-27	2019-01-30	Application User	9:30:00Am	10:30:00Am	1453
476200	2019-12-27	2019-01-30	Application User	11:00:00Am	12:00:01Pm	1454
476250	2019-12-27	2019-01-30	Application User	02:00:00Pm	03:00:00Pm	1455
\.


--
-- Data for Name: legal_entity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.legal_entity (id, logo, legal_name_of_the_college, type_of_college, date_of_incorporation, registered_office_address_1, registered_office_address_2, registered_office_address_3, registered_office_address_4, registered_office_address_5, college_identification_number, pan, tan, tan_circle_number, cit_tds_location, form_signatory, pf_number, pf_registration_date, pf_signatory, esi_number, esi_registration_date, esi_signatory, pt_number, pt_registration_date, pt_signatory, branch_id, college_id, state_id, city_id) FROM stdin;
1601	123	legal document of college	PUBLIC	2019-01-03	address_1	address_2	address_3	address_4	address_5	1234	AAAAAAAAAA	BBBBBBBBBB	AAAA2222	Hyderabad	1050	CCCCCC0000	2019-01-03	1100	7890889	2019-01-03	1200	5555	2019-01-03	1250	1001	951	\N	\N
\.


--
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.location (id, name, address, applies_to) FROM stdin;
1701	madhapur	madhapur	20employees
\.


--
-- Data for Name: payment_remainder; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payment_remainder (id, fee_remainder, notice_day, over_due_remainder, remainder_recipients, due_date_id, college_id, branch_id) FROM stdin;
\.


--
-- Data for Name: section; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.section (id, section, batch_id) FROM stdin;
1201	A	1151
12100	B	1151
12150	C	1151
12200	D	1151
12250	A	11350
12300	B	11350
12350	C	11350
12400	D	11350
12450	A	11400
12500	B	11400
12550	C	11400
12600	D	11400
12650	A	11450
12700	B	11450
12750	C	11450
12800	D	11450
12850	A	11500
12900	B	11500
12950	C	11500
13000	D	11500
13050	A	11550
13100	B	11550
13150	C	11550
13200	D	11550
13250	A	11600
13300	B	11600
13350	C	11600
13400	D	11600
13450	A	11650
13500	B	11650
13550	C	11650
13600	D	11650
13650	A	11700
13700	B	11700
13750	C	11700
13800	D	11700
13850	A	11750
13900	B	11750
13950	C	11750
14000	D	11750
14050	A	11800
14100	B	11800
14150	C	11800
14200	D	11800
14250	A	11850
14300	B	11850
14350	C	11850
14400	D	11850
14450	A	11900
14500	B	11900
14550	C	11900
14600	D	11900
14650	A	11950
14700	B	11950
14750	C	11950
14800	D	11950
14850	A	12000
14900	B	12000
14950	C	12000
15000	D	12000
15050	A	12050
15100	B	12050
15150	C	12050
15200	D	12050
\.


--
-- Data for Name: state; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.state (id, state_name, division_type, state_code, country_id) FROM stdin;
\.


--
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student (id, student_name, student_middle_name, student_last_name, father_name, father_middle_name, father_last_name, mother_name, mother_middle_name, mother_last_name, aadhar_no, date_of_birth, place_of_birth, religion, caste, sub_caste, age, sex, blood_group, address_line_one, address_line_two, address_line_three, town, state, country, pincode, student_contact_number, alternate_contact_number, student_email_address, alternate_email_address, relation_with_student, name, middle_name, last_name, contact_no, email_address, transport, mess, gym, cultural_class, jhi_library, sports, swimming, extra_class, handicrafts, jhi_add, upload_photo, admission_no, roll_no, student_type, department_id, batch_id, section_id, branch_id) FROM stdin;
1251	Ron			Henry	abc	XYZ	Grey	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	1201	1001
1252	Kriss			Hamen	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	1201	1001
1253	Matt			Hamen	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	1201	1001
1254	Larry			Hamen	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	1201	1001
1255	Tim			Hamen	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	1201	1001
342300	Denny			FDenny	abc	XYZ	Grey	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12100	1001
342350	Rinki			FRinki	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12100	1001
342400	Brad			FBrad	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12100	1001
342450	Bush			FBush	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12100	1001
342500	Jerry			FJerry	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12100	1001
342550	Nick			FNick	abc	XYZ	Grey	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12150	1001
342600	Brook			FBrook	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12150	1001
342650	Katti			FKatti	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12150	1001
342700	Laim			FLaim	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12150	1001
342750	Mecky			FMecky	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12150	1001
342800	Nathan			FNathan	abc	XYZ	Grey	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12200	1001
342850	Tim			FTim	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12200	1001
342900	William			FWilliam	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12200	1001
342950	Martin			FMartin	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12200	1001
343000	Jackson			FJackson	abc	XYZ	Blue	xyz	abc	38019819883	1995-12-31	Delhi	CHRISTIAN	BC	BCB	19	FEMALE	OPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	HYD	TG	india	500081	91002617333	94749584985	ron@gmail.com	ronnew@gmail.com	FATHER	Tom	Dane	Paul	12346585	ron@gmail.com	YES	NO	NO	NO	NO	NO	YES	YES	YES	HYD	123	444334	13p31ao595	free	1101	1151	12200	1001
\.


--
-- Data for Name: student_attendance; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student_attendance (id, attendance_status, comments, student_id, lecture_id) FROM stdin;
383300	PRESENT		1251	38100
383350	PRESENT		1252	38100
383400	PRESENT		1253	38100
383450	PRESENT		1254	38100
383500	PRESENT		1255	38100
383550	PRESENT		1251	38150
383600	PRESENT		1252	38150
383650	PRESENT		1253	38150
383700	PRESENT		1254	38150
383750	PRESENT		1255	38150
383800	PRESENT		1251	38200
383850	PRESENT		1252	38200
383900	PRESENT		1253	38200
383950	PRESENT		1254	38200
384000	PRESENT		1255	38200
384050	PRESENT		1251	38250
384100	PRESENT		1252	38250
384150	PRESENT		1253	38250
384200	PRESENT		1254	38250
384250	PRESENT		1255	38250
384300	PRESENT		1251	38300
384350	PRESENT		1252	38300
384400	PRESENT		1253	38300
384450	PRESENT		1254	38300
384500	PRESENT		1255	38300
384550	PRESENT		342300	69100
384600	PRESENT		342350	69100
384650	PRESENT		342400	69100
384700	PRESENT		342450	69100
384750	PRESENT		342500	69100
384800	PRESENT		342300	69150
384850	PRESENT		342350	69150
384900	PRESENT		342400	69150
384950	PRESENT		342450	69150
385000	PRESENT		342500	69150
385050	PRESENT		342300	69200
385100	PRESENT		342350	69200
385150	PRESENT		342400	69200
385200	PRESENT		342450	69200
385250	PRESENT		342500	69200
385300	PRESENT		342300	69250
385350	PRESENT		342350	69250
385400	PRESENT		342400	69250
385450	PRESENT		342450	69250
385500	PRESENT		342500	69250
385550	PRESENT		342300	69300
385600	PRESENT		342350	69300
385650	PRESENT		342400	69300
385700	PRESENT		342450	69300
385750	PRESENT		342500	69300
385800	PRESENT		342550	100100
385850	PRESENT		342600	100100
385900	PRESENT		342650	100100
385950	PRESENT		342700	100100
386000	PRESENT		342750	100100
386050	PRESENT		342550	100150
386100	PRESENT		342600	100150
386150	PRESENT		342650	100150
386200	PRESENT		342700	100150
386250	PRESENT		342750	100150
386300	PRESENT		342550	100200
386350	PRESENT		342600	100200
386400	PRESENT		342650	100200
386450	PRESENT		342700	100200
386500	PRESENT		342750	100200
386550	PRESENT		342550	100250
386600	PRESENT		342600	100250
386650	PRESENT		342650	100250
386700	PRESENT		342700	100250
386750	PRESENT		342750	100250
386800	PRESENT		342550	100300
386850	PRESENT		342600	100300
386900	PRESENT		342650	100300
386950	PRESENT		342700	100300
387000	PRESENT		342750	100300
387050	PRESENT		342800	131100
387100	PRESENT		342850	131100
387150	PRESENT		342900	131100
387200	PRESENT		342950	131100
387250	PRESENT		343000	131100
387300	PRESENT		342800	131150
387350	PRESENT		342850	131150
387400	PRESENT		342900	131150
387450	PRESENT		342950	131150
387500	PRESENT		343000	131150
387550	PRESENT		342800	131200
387600	PRESENT		342850	131200
387650	PRESENT		342900	131200
387700	PRESENT		342950	131200
387750	PRESENT		343000	131200
387800	PRESENT		342800	131250
387850	PRESENT		342850	131250
387900	PRESENT		342900	131250
387950	PRESENT		342950	131250
388000	PRESENT		343000	131250
388050	PRESENT		342800	131300
388100	PRESENT		342850	131300
388150	PRESENT		342900	131300
388200	PRESENT		342950	131300
388250	PRESENT		343000	131300
388300	PRESENT		1251	44350
388350	PRESENT		1252	44350
388400	PRESENT		1253	44350
388450	PRESENT		1254	44350
388500	PRESENT		1255	44350
388550	PRESENT		1251	44400
388600	PRESENT		1252	44400
388650	PRESENT		1253	44400
388700	PRESENT		1254	44400
388750	PRESENT		1255	44400
388800	PRESENT		1251	44450
388850	PRESENT		1252	44450
388900	PRESENT		1253	44450
388950	PRESENT		1254	44450
389000	PRESENT		1255	44450
389050	PRESENT		1251	44500
389100	PRESENT		1252	44500
389150	PRESENT		1253	44500
389200	PRESENT		1254	44500
389250	PRESENT		1255	44500
389300	PRESENT		1251	44550
389350	PRESENT		1252	44550
389400	PRESENT		1253	44550
389450	PRESENT		1254	44550
389500	PRESENT		1255	44550
389550	PRESENT		342300	75350
389600	PRESENT		342350	75350
389650	PRESENT		342400	75350
389700	PRESENT		342450	75350
389750	PRESENT		342500	75350
389800	PRESENT		342300	75400
389850	PRESENT		342350	75400
389900	PRESENT		342400	75400
389950	PRESENT		342450	75400
390000	PRESENT		342500	75400
390050	PRESENT		342300	75450
390100	PRESENT		342350	75450
390150	PRESENT		342400	75450
390200	PRESENT		342450	75450
390250	PRESENT		342500	75450
390300	PRESENT		342300	75500
390350	PRESENT		342350	75500
390400	PRESENT		342400	75500
390450	PRESENT		342450	75500
390500	PRESENT		342500	75500
390550	PRESENT		342300	75550
390600	PRESENT		342350	75550
390650	PRESENT		342400	75550
390700	PRESENT		342450	75550
390750	PRESENT		342500	75550
390800	PRESENT		342550	106350
390850	PRESENT		342600	106350
390900	PRESENT		342650	106350
390950	PRESENT		342700	106350
391000	PRESENT		342750	106350
391050	PRESENT		342550	106400
391100	PRESENT		342600	106400
391150	PRESENT		342650	106400
391200	PRESENT		342700	106400
391250	PRESENT		342750	106400
391300	PRESENT		342550	106450
391350	PRESENT		342600	106450
391400	PRESENT		342650	106450
391450	PRESENT		342700	106450
391500	PRESENT		342750	106450
391550	PRESENT		342550	106500
391600	PRESENT		342600	106500
391650	PRESENT		342650	106500
391700	PRESENT		342700	106500
391750	PRESENT		342750	106500
391800	PRESENT		342550	106550
391850	PRESENT		342600	106550
391900	PRESENT		342650	106550
391950	PRESENT		342700	106550
392000	PRESENT		342750	106550
392050	PRESENT		342800	137350
392100	PRESENT		342850	137350
392150	PRESENT		342900	137350
392200	PRESENT		342950	137350
392250	PRESENT		343000	137350
392300	PRESENT		342800	137400
392350	PRESENT		342850	137400
392400	PRESENT		342900	137400
392450	PRESENT		342950	137400
392500	PRESENT		343000	137400
392550	PRESENT		342800	137450
392600	PRESENT		342850	137450
392650	PRESENT		342900	137450
392700	PRESENT		342950	137450
392750	PRESENT		343000	137450
392800	PRESENT		342800	137500
392850	PRESENT		342850	137500
392900	PRESENT		342900	137500
392950	PRESENT		342950	137500
393000	PRESENT		343000	137500
393050	PRESENT		342800	137550
393100	PRESENT		342850	137550
393150	PRESENT		342900	137550
393200	PRESENT		342950	137550
393250	PRESENT		343000	137550
414300	PRESENT		1251	44800
414350	PRESENT		1252	44800
414400	PRESENT		1253	44800
414450	PRESENT		1254	44800
414500	PRESENT		1255	44800
414550	PRESENT		342300	75600
414600	PRESENT		342350	75600
414650	PRESENT		342400	75600
414700	PRESENT		342450	75600
414750	PRESENT		342500	75600
414800	PRESENT		342300	75650
414850	PRESENT		342350	75650
414900	PRESENT		342400	75650
414950	PRESENT		342450	75650
415000	PRESENT		342500	75650
415050	PRESENT		342300	75700
415100	PRESENT		342350	75700
415150	PRESENT		342400	75700
415200	PRESENT		342450	75700
415250	PRESENT		342500	75700
415300	PRESENT		342300	75750
415350	PRESENT		342350	75750
415400	PRESENT		342400	75750
415450	PRESENT		342450	75750
415500	PRESENT		342500	75750
415550	PRESENT		342300	75800
415600	PRESENT		342350	75800
415650	PRESENT		342400	75800
415700	PRESENT		342450	75800
415750	PRESENT		342500	75800
415800	PRESENT		342550	106600
415850	PRESENT		342600	106600
415900	PRESENT		342650	106600
415950	PRESENT		342700	106600
416000	PRESENT		342750	106600
416050	PRESENT		342550	106650
416100	PRESENT		342600	106650
416150	PRESENT		342650	106650
416200	PRESENT		342700	106650
416250	PRESENT		342750	106650
393300	PRESENT		1251	19850
393350	PRESENT		1252	19850
393400	PRESENT		1253	19850
393450	PRESENT		1254	19850
393500	PRESENT		1255	19850
393550	PRESENT		1251	19900
393600	PRESENT		1252	19900
393650	PRESENT		1253	19900
393700	PRESENT		1254	19900
393750	PRESENT		1255	19900
393800	PRESENT		1251	19950
393850	PRESENT		1252	19950
393900	PRESENT		1253	19950
393950	PRESENT		1254	19950
394000	PRESENT		1255	19950
394050	PRESENT		1251	20000
394100	PRESENT		1252	20000
394150	PRESENT		1253	20000
394200	PRESENT		1254	20000
394250	PRESENT		1255	20000
394300	PRESENT		1251	20050
394350	PRESENT		1252	20050
394400	PRESENT		1253	20050
394450	PRESENT		1254	20050
394500	PRESENT		1255	20050
394550	PRESENT		342300	50850
394600	PRESENT		342350	50850
394650	PRESENT		342400	50850
394700	PRESENT		342450	50850
394750	PRESENT		342500	50850
394800	PRESENT		342300	50900
394850	PRESENT		342350	50900
394900	PRESENT		342400	50900
394950	PRESENT		342450	50900
395000	PRESENT		342500	50900
395050	PRESENT		342300	50950
395100	PRESENT		342350	50950
395150	PRESENT		342400	50950
395200	PRESENT		342450	50950
395250	PRESENT		342500	50950
395300	PRESENT		342300	51000
395350	PRESENT		342350	51000
395400	PRESENT		342400	51000
395450	PRESENT		342450	51000
395500	PRESENT		342500	51000
395550	PRESENT		342300	51050
395600	PRESENT		342350	51050
395650	PRESENT		342400	51050
395700	PRESENT		342450	51050
395750	PRESENT		342500	51050
395800	PRESENT		342550	81850
395850	PRESENT		342600	81850
395900	PRESENT		342650	81850
395950	PRESENT		342700	81850
396000	PRESENT		342750	81850
396050	PRESENT		342550	81900
396100	PRESENT		342600	81900
396150	PRESENT		342650	81900
396200	PRESENT		342700	81900
396250	PRESENT		342750	81900
396300	PRESENT		342550	81950
396350	PRESENT		342600	81950
396400	PRESENT		342650	81950
396450	PRESENT		342700	81950
396500	PRESENT		342750	81950
396550	PRESENT		342550	82000
396600	PRESENT		342600	82000
396650	PRESENT		342650	82000
396700	PRESENT		342700	82000
396750	PRESENT		342750	82000
396800	PRESENT		342550	82050
396850	PRESENT		342600	82050
396900	PRESENT		342650	82050
396950	PRESENT		342700	82050
397000	PRESENT		342750	82050
397050	PRESENT		342800	112850
397100	PRESENT		342850	112850
397150	PRESENT		342900	112850
397200	PRESENT		342950	112850
397250	PRESENT		343000	112850
397300	PRESENT		342800	112900
397350	PRESENT		342850	112900
397400	PRESENT		342900	112900
397450	PRESENT		342950	112900
397500	PRESENT		343000	112900
397550	PRESENT		342800	112950
397600	PRESENT		342850	112950
397650	PRESENT		342900	112950
397700	PRESENT		342950	112950
397750	PRESENT		343000	112950
397800	PRESENT		342800	113000
397850	PRESENT		342850	113000
397900	PRESENT		342900	113000
397950	PRESENT		342950	113000
398000	PRESENT		343000	113000
398050	PRESENT		342800	113050
398100	PRESENT		342850	113050
398150	PRESENT		342900	113050
398200	PRESENT		342950	113050
398250	PRESENT		343000	113050
398300	PRESENT		1251	25600
398350	PRESENT		1252	25600
398400	PRESENT		1253	25600
398450	PRESENT		1254	25600
398500	PRESENT		1255	25600
398550	PRESENT		1251	25650
398600	PRESENT		1252	25650
398650	PRESENT		1253	25650
398700	PRESENT		1254	25650
398750	PRESENT		1255	25650
398800	PRESENT		1251	25700
398850	PRESENT		1252	25700
398900	PRESENT		1253	25700
398950	PRESENT		1254	25700
399000	PRESENT		1255	25700
399050	PRESENT		1251	25750
399100	PRESENT		1252	25750
399150	PRESENT		1253	25750
399200	PRESENT		1254	25750
399250	PRESENT		1255	25750
399300	PRESENT		1251	25800
399350	PRESENT		1252	25800
399400	PRESENT		1253	25800
399450	PRESENT		1254	25800
399500	PRESENT		1255	25800
399550	PRESENT		342300	56600
399600	PRESENT		342350	56600
399650	PRESENT		342400	56600
399700	PRESENT		342450	56600
399750	PRESENT		342500	56600
399800	PRESENT		342300	56650
399850	PRESENT		342350	56650
399900	PRESENT		342400	56650
399950	PRESENT		342450	56650
400000	PRESENT		342500	56650
400050	PRESENT		342300	56700
400100	PRESENT		342350	56700
400150	PRESENT		342400	56700
400200	PRESENT		342450	56700
400250	PRESENT		342500	56700
400300	PRESENT		342300	56750
400350	PRESENT		342350	56750
400400	PRESENT		342400	56750
400450	PRESENT		342450	56750
400500	PRESENT		342500	56750
400550	PRESENT		342300	56800
400600	PRESENT		342350	56800
400650	PRESENT		342400	56800
400700	PRESENT		342450	56800
400750	PRESENT		342500	56800
400800	PRESENT		342550	87600
400850	PRESENT		342600	87600
400900	PRESENT		342650	87600
400950	PRESENT		342700	87600
401000	PRESENT		342750	87600
401050	PRESENT		342550	87650
401100	PRESENT		342600	87650
401150	PRESENT		342650	87650
401200	PRESENT		342700	87650
401250	PRESENT		342750	87650
401300	PRESENT		342550	87700
401350	PRESENT		342600	87700
401400	PRESENT		342650	87700
401450	PRESENT		342700	87700
401500	PRESENT		342750	87700
401550	PRESENT		342550	87750
401600	PRESENT		342600	87750
401650	PRESENT		342650	87750
401700	PRESENT		342700	87750
401750	PRESENT		342750	87750
401800	PRESENT		342550	87800
401850	PRESENT		342600	87800
401900	PRESENT		342650	87800
401950	PRESENT		342700	87800
402000	PRESENT		342750	87800
402050	PRESENT		342800	118600
402100	PRESENT		342850	118600
402150	PRESENT		342900	118600
402200	PRESENT		342950	118600
402250	PRESENT		343000	118600
402300	PRESENT		342800	118650
402350	PRESENT		342850	118650
402400	PRESENT		342900	118650
402450	PRESENT		342950	118650
402500	PRESENT		343000	118650
402550	PRESENT		342800	118700
402600	PRESENT		342850	118700
402650	PRESENT		342900	118700
402700	PRESENT		342950	118700
402750	PRESENT		343000	118700
402800	PRESENT		342800	118750
402850	PRESENT		342850	118750
402900	PRESENT		342900	118750
402950	PRESENT		342950	118750
403000	PRESENT		343000	118750
403050	PRESENT		342800	118800
403100	PRESENT		342850	118800
403150	PRESENT		342900	118800
403200	PRESENT		342950	118800
403250	PRESENT		343000	118800
416300	PRESENT		342550	106700
416350	PRESENT		342600	106700
416400	PRESENT		342650	106700
416450	PRESENT		342700	106700
416500	PRESENT		342750	106700
416550	PRESENT		342550	106750
416600	PRESENT		342600	106750
416650	PRESENT		342650	106750
416700	PRESENT		342700	106750
416750	PRESENT		342750	106750
416800	PRESENT		342550	106800
416850	PRESENT		342600	106800
416900	PRESENT		342650	106800
416950	PRESENT		342700	106800
417000	PRESENT		342750	106800
417050	PRESENT		342800	137600
417100	PRESENT		342850	137600
417150	PRESENT		342900	137600
417200	PRESENT		342950	137600
417250	PRESENT		343000	137600
417300	PRESENT		342800	137650
417350	PRESENT		342850	137650
417400	PRESENT		342900	137650
417450	PRESENT		342950	137650
417500	PRESENT		343000	137650
417550	PRESENT		342800	137700
417600	PRESENT		342850	137700
417650	PRESENT		342900	137700
417700	PRESENT		342950	137700
417750	PRESENT		343000	137700
417800	PRESENT		342800	137750
417850	PRESENT		342850	137750
417900	PRESENT		342900	137750
417950	PRESENT		342950	137750
418000	PRESENT		343000	137750
418050	PRESENT		342800	137800
418100	PRESENT		342850	137800
418150	PRESENT		342900	137800
418200	PRESENT		342950	137800
418250	PRESENT		343000	137800
403300	PRESENT		1251	31850
403350	PRESENT		1252	31850
403400	PRESENT		1253	31850
403450	PRESENT		1254	31850
403500	PRESENT		1255	31850
403550	PRESENT		1251	31900
403600	PRESENT		1252	31900
403650	PRESENT		1253	31900
403700	PRESENT		1254	31900
403750	PRESENT		1255	31900
403800	PRESENT		1251	31950
403850	PRESENT		1252	31950
403900	PRESENT		1253	31950
403950	PRESENT		1254	31950
404000	PRESENT		1255	31950
404050	PRESENT		1251	32000
404100	PRESENT		1252	32000
404150	PRESENT		1253	32000
404200	PRESENT		1254	32000
404250	PRESENT		1255	32000
404300	PRESENT		1251	32050
404350	PRESENT		1252	32050
404400	PRESENT		1253	32050
404450	PRESENT		1254	32050
404500	PRESENT		1255	32050
404550	PRESENT		342300	62850
404600	PRESENT		342350	62850
404650	PRESENT		342400	62850
404700	PRESENT		342450	62850
404750	PRESENT		342500	62850
404800	PRESENT		342300	62900
404850	PRESENT		342350	62900
404900	PRESENT		342400	62900
404950	PRESENT		342450	62900
405000	PRESENT		342500	62900
405050	PRESENT		342300	62950
405100	PRESENT		342350	62950
405150	PRESENT		342400	62950
405200	PRESENT		342450	62950
405250	PRESENT		342500	62950
405300	PRESENT		342300	63000
405350	PRESENT		342350	63000
405400	PRESENT		342400	63000
405450	PRESENT		342450	63000
405500	PRESENT		342500	63000
405550	PRESENT		342300	63050
405600	PRESENT		342350	63050
405650	PRESENT		342400	63050
405700	PRESENT		342450	63050
405750	PRESENT		342500	63050
405800	PRESENT		342550	93850
405850	PRESENT		342600	93850
405900	PRESENT		342650	93850
405950	PRESENT		342700	93850
406000	PRESENT		342750	93850
406050	PRESENT		342550	93900
406100	PRESENT		342600	93900
406150	PRESENT		342650	93900
406200	PRESENT		342700	93900
406250	PRESENT		342750	93900
406300	PRESENT		342550	93950
406350	PRESENT		342600	93950
406400	PRESENT		342650	93950
406450	PRESENT		342700	93950
406500	PRESENT		342750	93950
406550	PRESENT		342550	94000
406600	PRESENT		342600	94000
406650	PRESENT		342650	94000
406700	PRESENT		342700	94000
406750	PRESENT		342750	94000
406800	PRESENT		342550	94050
406850	PRESENT		342600	94050
406900	PRESENT		342650	94050
406950	PRESENT		342700	94050
407000	PRESENT		342750	94050
407050	PRESENT		342800	124850
407100	PRESENT		342850	124850
407150	PRESENT		342900	124850
407200	PRESENT		342950	124850
407250	PRESENT		343000	124850
407300	PRESENT		342800	124900
407350	PRESENT		342850	124900
407400	PRESENT		342900	124900
407450	PRESENT		342950	124900
407500	PRESENT		343000	124900
407550	PRESENT		342800	124950
407600	PRESENT		342850	124950
407650	PRESENT		342900	124950
407700	PRESENT		342950	124950
407750	PRESENT		343000	124950
407800	PRESENT		342800	125000
407850	PRESENT		342850	125000
407900	PRESENT		342900	125000
407950	PRESENT		342950	125000
408000	PRESENT		343000	125000
408050	PRESENT		342800	125050
408100	PRESENT		342850	125050
408150	PRESENT		342900	125050
408200	PRESENT		342950	125050
408250	PRESENT		343000	125050
408300	PRESENT		1251	38350
408350	PRESENT		1252	38350
408400	PRESENT		1253	38350
408450	PRESENT		1254	38350
408500	PRESENT		1255	38350
408550	PRESENT		1251	38400
408600	PRESENT		1252	38400
408650	PRESENT		1253	38400
408700	PRESENT		1254	38400
408750	PRESENT		1255	38400
408800	PRESENT		1251	38450
408850	PRESENT		1252	38450
408900	PRESENT		1253	38450
408950	PRESENT		1254	38450
409000	PRESENT		1255	38450
409050	PRESENT		1251	38500
409100	PRESENT		1252	38500
409150	PRESENT		1253	38500
409200	PRESENT		1254	38500
409250	PRESENT		1255	38500
409300	PRESENT		1251	38550
409350	PRESENT		1252	38550
409400	PRESENT		1253	38550
409450	PRESENT		1254	38550
409500	PRESENT		1255	38550
409550	PRESENT		342300	69350
409600	PRESENT		342350	69350
409650	PRESENT		342400	69350
409700	PRESENT		342450	69350
409750	PRESENT		342500	69350
409800	PRESENT		342300	69400
409850	PRESENT		342350	69400
409900	PRESENT		342400	69400
409950	PRESENT		342450	69400
410000	PRESENT		342500	69400
410050	PRESENT		342300	69450
410100	PRESENT		342350	69450
410150	PRESENT		342400	69450
410200	PRESENT		342450	69450
410250	PRESENT		342500	69450
410300	PRESENT		342300	69500
410350	PRESENT		342350	69500
410400	PRESENT		342400	69500
410450	PRESENT		342450	69500
410500	PRESENT		342500	69500
410550	PRESENT		342300	69550
410600	PRESENT		342350	69550
410650	PRESENT		342400	69550
410700	PRESENT		342450	69550
410750	PRESENT		342500	69550
410800	PRESENT		342550	100350
410850	PRESENT		342600	100350
410900	PRESENT		342650	100350
410950	PRESENT		342700	100350
411000	PRESENT		342750	100350
411050	PRESENT		342550	100400
411100	PRESENT		342600	100400
411150	PRESENT		342650	100400
411200	PRESENT		342700	100400
411250	PRESENT		342750	100400
411300	PRESENT		342550	100450
411350	PRESENT		342600	100450
411400	PRESENT		342650	100450
411450	PRESENT		342700	100450
411500	PRESENT		342750	100450
411550	PRESENT		342550	100500
411600	PRESENT		342600	100500
411650	PRESENT		342650	100500
411700	PRESENT		342700	100500
411750	PRESENT		342750	100500
411800	PRESENT		342550	100550
411850	PRESENT		342600	100550
411900	PRESENT		342650	100550
411950	PRESENT		342700	100550
412000	PRESENT		342750	100550
412050	PRESENT		342800	131350
412100	PRESENT		342850	131350
412150	PRESENT		342900	131350
412200	PRESENT		342950	131350
412250	PRESENT		343000	131350
412300	PRESENT		342800	131400
412350	PRESENT		342850	131400
412400	PRESENT		342900	131400
412450	PRESENT		342950	131400
412500	PRESENT		343000	131400
412550	PRESENT		342800	131450
412600	PRESENT		342850	131450
412650	PRESENT		342900	131450
412700	PRESENT		342950	131450
412750	PRESENT		343000	131450
412800	PRESENT		342800	131500
412850	PRESENT		342850	131500
412900	PRESENT		342900	131500
412950	PRESENT		342950	131500
413000	PRESENT		343000	131500
413050	PRESENT		342800	131550
413100	PRESENT		342850	131550
413150	PRESENT		342900	131550
413200	PRESENT		342950	131550
413250	PRESENT		343000	131550
418300	PRESENT		1251	32100
418350	PRESENT		1252	32100
418400	PRESENT		1253	32100
418450	PRESENT		1254	32100
418500	PRESENT		1255	32100
418550	PRESENT		1251	32150
418600	PRESENT		1252	32150
418650	PRESENT		1253	32150
418700	PRESENT		1254	32150
418750	PRESENT		1255	32150
418800	PRESENT		1251	32200
418850	PRESENT		1252	32200
418900	PRESENT		1253	32200
418950	PRESENT		1254	32200
419000	PRESENT		1255	32200
419050	PRESENT		1251	32250
419100	PRESENT		1252	32250
419150	PRESENT		1253	32250
419200	PRESENT		1254	32250
419250	PRESENT		1255	32250
419300	PRESENT		1251	32300
419350	PRESENT		1252	32300
419400	PRESENT		1253	32300
419450	PRESENT		1254	32300
419500	PRESENT		1255	32300
419550	PRESENT		342300	63100
419600	PRESENT		342350	63100
419650	PRESENT		342400	63100
419700	PRESENT		342450	63100
419750	PRESENT		342500	63100
419800	PRESENT		342300	63150
419850	PRESENT		342350	63150
419900	PRESENT		342400	63150
419950	PRESENT		342450	63150
420000	PRESENT		342500	63150
420050	PRESENT		342300	63200
420100	PRESENT		342350	63200
420150	PRESENT		342400	63200
420200	PRESENT		342450	63200
420250	PRESENT		342500	63200
413300	PRESENT		1251	44600
413350	PRESENT		1252	44600
413400	PRESENT		1253	44600
413450	PRESENT		1254	44600
413500	PRESENT		1255	44600
378300	PRESENT		1251	31600
378350	PRESENT		1252	31600
378400	PRESENT		1253	31600
378450	PRESENT		1254	31600
378500	PRESENT		1255	31600
378550	PRESENT		1251	31650
378600	PRESENT		1252	31650
378650	PRESENT		1253	31650
378700	PRESENT		1254	31650
378750	PRESENT		1255	31650
378800	PRESENT		1251	31700
378850	PRESENT		1252	31700
378900	PRESENT		1253	31700
378950	PRESENT		1254	31700
379000	PRESENT		1255	31700
379050	PRESENT		1251	31750
379100	PRESENT		1252	31750
379150	PRESENT		1253	31750
379200	PRESENT		1254	31750
379250	PRESENT		1255	31750
379300	PRESENT		1251	31800
379350	PRESENT		1252	31800
379400	PRESENT		1253	31800
379450	PRESENT		1254	31800
379500	PRESENT		1255	31800
379550	PRESENT		342300	62600
379600	PRESENT		342350	62600
379650	PRESENT		342400	62600
379700	PRESENT		342450	62600
379750	PRESENT		342500	62600
379800	PRESENT		342300	62650
379850	PRESENT		342350	62650
379900	PRESENT		342400	62650
379950	PRESENT		342450	62650
380000	PRESENT		342500	62650
380050	PRESENT		342300	62700
380100	PRESENT		342350	62700
380150	PRESENT		342400	62700
380200	PRESENT		342450	62700
380250	PRESENT		342500	62700
380300	PRESENT		342300	62750
380350	PRESENT		342350	62750
380400	PRESENT		342400	62750
380450	PRESENT		342450	62750
380500	PRESENT		342500	62750
380550	PRESENT		342300	62800
380600	PRESENT		342350	62800
380650	PRESENT		342400	62800
380700	PRESENT		342450	62800
380750	PRESENT		342500	62800
380800	PRESENT		342550	93600
380850	PRESENT		342600	93600
380900	PRESENT		342650	93600
380950	PRESENT		342700	93600
381000	PRESENT		342750	93600
381050	PRESENT		342550	93650
381100	PRESENT		342600	93650
381150	PRESENT		342650	93650
381200	PRESENT		342700	93650
381250	PRESENT		342750	93650
381300	PRESENT		342550	93700
381350	PRESENT		342600	93700
381400	PRESENT		342650	93700
381450	PRESENT		342700	93700
381500	PRESENT		342750	93700
381550	PRESENT		342550	93750
381600	PRESENT		342600	93750
381650	PRESENT		342650	93750
381700	PRESENT		342700	93750
381750	PRESENT		342750	93750
381800	PRESENT		342550	93800
381850	PRESENT		342600	93800
381900	PRESENT		342650	93800
381950	PRESENT		342700	93800
382000	PRESENT		342750	93800
382050	PRESENT		342800	124600
382100	PRESENT		342850	124600
382150	PRESENT		342900	124600
382200	PRESENT		342950	124600
382250	PRESENT		343000	124600
382300	PRESENT		342800	124650
382350	PRESENT		342850	124650
382400	PRESENT		342900	124650
382450	PRESENT		342950	124650
382500	PRESENT		343000	124650
382550	PRESENT		342800	124700
382600	PRESENT		342850	124700
382650	PRESENT		342900	124700
382700	PRESENT		342950	124700
382750	PRESENT		343000	124700
382800	PRESENT		342800	124750
382850	PRESENT		342850	124750
382900	PRESENT		342900	124750
382950	PRESENT		342950	124750
383000	PRESENT		343000	124750
383050	PRESENT		342800	124800
383100	PRESENT		342850	124800
383150	PRESENT		342900	124800
383200	PRESENT		342950	124800
383250	PRESENT		343000	124800
413550	PRESENT		1251	44650
413600	PRESENT		1252	44650
413650	PRESENT		1253	44650
413700	PRESENT		1254	44650
413750	PRESENT		1255	44650
413800	PRESENT		1251	44700
413850	PRESENT		1252	44700
413900	PRESENT		1253	44700
413950	PRESENT		1254	44700
414000	PRESENT		1255	44700
414100	PRESENT		1252	44750
414150	PRESENT		1253	44750
414200	PRESENT		1254	44750
420300	PRESENT		342300	63250
420350	PRESENT		342350	63250
420400	PRESENT		342400	63250
420450	PRESENT		342450	63250
420500	PRESENT		342500	63250
420550	PRESENT		342300	63300
420600	PRESENT		342350	63300
420650	PRESENT		342400	63300
420700	PRESENT		342450	63300
420750	PRESENT		342500	63300
420800	PRESENT		342550	94100
420850	PRESENT		342600	94100
420900	PRESENT		342650	94100
420950	PRESENT		342700	94100
421000	PRESENT		342750	94100
421050	PRESENT		342550	94150
421100	PRESENT		342600	94150
421150	PRESENT		342650	94150
421200	PRESENT		342700	94150
421250	PRESENT		342750	94150
421300	PRESENT		342550	94200
421350	PRESENT		342600	94200
421400	PRESENT		342650	94200
421450	PRESENT		342700	94200
421500	PRESENT		342750	94200
421550	PRESENT		342550	94250
421600	PRESENT		342600	94250
421650	PRESENT		342650	94250
421700	PRESENT		342700	94250
421750	PRESENT		342750	94250
421800	PRESENT		342550	94300
421850	PRESENT		342600	94300
421900	PRESENT		342650	94300
421950	PRESENT		342700	94300
422000	PRESENT		342750	94300
422050	PRESENT		342800	125100
422100	PRESENT		342850	125100
422150	PRESENT		342900	125100
422200	PRESENT		342950	125100
422250	PRESENT		343000	125100
422300	PRESENT		342800	125150
422350	PRESENT		342850	125150
422400	PRESENT		342900	125150
422450	PRESENT		342950	125150
422500	PRESENT		343000	125150
422550	PRESENT		342800	125200
422600	PRESENT		342850	125200
422650	PRESENT		342900	125200
422700	PRESENT		342950	125200
422750	PRESENT		343000	125200
422800	PRESENT		342800	125250
422850	PRESENT		342850	125250
422900	PRESENT		342900	125250
422950	PRESENT		342950	125250
423000	PRESENT		343000	125250
423050	PRESENT		342800	125300
423100	PRESENT		342850	125300
423150	PRESENT		342900	125300
423200	PRESENT		342950	125300
423250	PRESENT		343000	125300
423300	PRESENT		1251	38600
423350	PRESENT		1252	38600
423400	PRESENT		1253	38600
423450	PRESENT		1254	38600
423500	PRESENT		1255	38600
423550	PRESENT		1251	38650
423600	PRESENT		1252	38650
423650	PRESENT		1253	38650
423700	PRESENT		1254	38650
423750	PRESENT		1255	38650
423800	PRESENT		1251	38700
423850	PRESENT		1252	38700
423900	PRESENT		1253	38700
423950	PRESENT		1254	38700
424000	PRESENT		1255	38700
424050	PRESENT		1251	38750
424100	PRESENT		1252	38750
424150	PRESENT		1253	38750
424200	PRESENT		1254	38750
424250	PRESENT		1255	38750
424300	PRESENT		1251	38800
424350	PRESENT		1252	38800
424400	PRESENT		1253	38800
424450	PRESENT		1254	38800
424500	PRESENT		1255	38800
424550	PRESENT		342300	69600
424600	PRESENT		342350	69600
424650	PRESENT		342400	69600
424700	PRESENT		342450	69600
424750	PRESENT		342500	69600
424800	PRESENT		342300	69650
424850	PRESENT		342350	69650
424900	PRESENT		342400	69650
424950	PRESENT		342450	69650
425000	PRESENT		342500	69650
425050	PRESENT		342300	69700
425100	PRESENT		342350	69700
425150	PRESENT		342400	69700
425200	PRESENT		342450	69700
425250	PRESENT		342500	69700
425300	PRESENT		342300	69750
425350	PRESENT		342350	69750
425400	PRESENT		342400	69750
425450	PRESENT		342450	69750
425500	PRESENT		342500	69750
425550	PRESENT		342300	69800
425600	PRESENT		342350	69800
425650	PRESENT		342400	69800
425700	PRESENT		342450	69800
425750	PRESENT		342500	69800
425800	PRESENT		342550	100600
425850	PRESENT		342600	100600
425900	PRESENT		342650	100600
425950	PRESENT		342700	100600
426000	PRESENT		342750	100600
426050	PRESENT		342550	100650
426100	PRESENT		342600	100650
426150	PRESENT		342650	100650
426200	PRESENT		342700	100650
426250	PRESENT		342750	100650
426300	PRESENT		342550	100700
426350	PRESENT		342600	100700
426400	PRESENT		342650	100700
426450	PRESENT		342700	100700
426500	PRESENT		342750	100700
426550	PRESENT		342550	100750
426600	PRESENT		342600	100750
426650	PRESENT		342650	100750
426700	PRESENT		342700	100750
426750	PRESENT		342750	100750
426800	PRESENT		342550	100800
426850	PRESENT		342600	100800
426900	PRESENT		342650	100800
426950	PRESENT		342700	100800
427000	PRESENT		342750	100800
427050	PRESENT		342800	131600
427100	PRESENT		342850	131600
427150	PRESENT		342900	131600
427200	PRESENT		342950	131600
427250	PRESENT		343000	131600
427300	PRESENT		342800	131650
427350	PRESENT		342850	131650
427400	PRESENT		342900	131650
427450	PRESENT		342950	131650
427500	PRESENT		343000	131650
427550	PRESENT		342800	131700
427600	PRESENT		342850	131700
427650	PRESENT		342900	131700
427700	PRESENT		342950	131700
427750	PRESENT		343000	131700
427800	PRESENT		342800	131750
427850	PRESENT		342850	131750
427900	PRESENT		342900	131750
427950	PRESENT		342950	131750
428000	PRESENT		343000	131750
428050	PRESENT		342800	131800
428100	PRESENT		342850	131800
428150	PRESENT		342900	131800
428200	PRESENT		342950	131800
428250	PRESENT		343000	131800
428300	PRESENT		1251	44850
428350	PRESENT		1252	44850
428400	PRESENT		1253	44850
428450	PRESENT		1254	44850
428500	PRESENT		1255	44850
428550	PRESENT		1251	44900
428600	PRESENT		1252	44900
428650	PRESENT		1253	44900
428700	PRESENT		1254	44900
428750	PRESENT		1255	44900
428800	PRESENT		1251	44950
428850	PRESENT		1252	44950
428900	PRESENT		1253	44950
428950	PRESENT		1254	44950
429000	PRESENT		1255	44950
429050	PRESENT		1251	45000
429100	PRESENT		1252	45000
429150	PRESENT		1253	45000
429200	PRESENT		1254	45000
429250	PRESENT		1255	45000
429300	PRESENT		1251	45050
429350	PRESENT		1252	45050
429400	PRESENT		1253	45050
429450	PRESENT		1254	45050
429500	PRESENT		1255	45050
429550	PRESENT		342300	75850
429600	PRESENT		342350	75850
429650	PRESENT		342400	75850
429700	PRESENT		342450	75850
429750	PRESENT		342500	75850
429800	PRESENT		342300	75900
429850	PRESENT		342350	75900
429900	PRESENT		342400	75900
429950	PRESENT		342450	75900
430000	PRESENT		342500	75900
430050	PRESENT		342300	75950
430100	PRESENT		342350	75950
430150	PRESENT		342400	75950
430200	PRESENT		342450	75950
430250	PRESENT		342500	75950
430300	PRESENT		342300	76000
430350	PRESENT		342350	76000
430400	PRESENT		342400	76000
430450	PRESENT		342450	76000
430500	PRESENT		342500	76000
430550	PRESENT		342300	76050
430600	PRESENT		342350	76050
430650	PRESENT		342400	76050
430700	PRESENT		342450	76050
430750	PRESENT		342500	76050
430800	PRESENT		342550	106850
430850	PRESENT		342600	106850
430900	PRESENT		342650	106850
430950	PRESENT		342700	106850
431000	PRESENT		342750	106850
431050	PRESENT		342550	106900
431100	PRESENT		342600	106900
431150	PRESENT		342650	106900
431200	PRESENT		342700	106900
431250	PRESENT		342750	106900
431300	PRESENT		342550	106950
431350	PRESENT		342600	106950
431400	PRESENT		342650	106950
431450	PRESENT		342700	106950
431500	PRESENT		342750	106950
431550	PRESENT		342550	107000
431600	PRESENT		342600	107000
431650	PRESENT		342650	107000
431700	PRESENT		342700	107000
431750	PRESENT		342750	107000
431800	PRESENT		342550	107050
431850	PRESENT		342600	107050
431900	PRESENT		342650	107050
431950	PRESENT		342700	107050
432000	PRESENT		342750	107050
432050	PRESENT		342800	137850
432100	PRESENT		342850	137850
432150	PRESENT		342900	137850
432200	PRESENT		342950	137850
432250	PRESENT		343000	137850
432300	PRESENT		342800	137900
432350	PRESENT		342850	137900
432400	PRESENT		342900	137900
432450	PRESENT		342950	137900
432500	PRESENT		343000	137900
432550	PRESENT		342800	137950
432600	PRESENT		342850	137950
432650	PRESENT		342900	137950
432700	PRESENT		342950	137950
432750	PRESENT		343000	137950
432800	PRESENT		342800	138000
432850	PRESENT		342850	138000
432900	PRESENT		342900	138000
432950	PRESENT		342950	138000
433000	PRESENT		343000	138000
433050	PRESENT		342800	138050
433100	PRESENT		342850	138050
433150	PRESENT		342900	138050
433200	PRESENT		342950	138050
433250	PRESENT		343000	138050
433300	PRESENT		1251	20100
433350	PRESENT		1252	20100
433400	PRESENT		1253	20100
433450	PRESENT		1254	20100
433500	PRESENT		1255	20100
433550	PRESENT		1251	20150
433600	PRESENT		1252	20150
433650	PRESENT		1253	20150
433700	PRESENT		1254	20150
433750	PRESENT		1255	20150
433800	PRESENT		1251	20200
433850	PRESENT		1252	20200
433900	PRESENT		1253	20200
433950	PRESENT		1254	20200
434000	PRESENT		1255	20200
434050	PRESENT		1251	20250
434100	PRESENT		1252	20250
434150	PRESENT		1253	20250
434200	PRESENT		1254	20250
434250	PRESENT		1255	20250
434300	PRESENT		1251	20300
434350	PRESENT		1252	20300
434400	PRESENT		1253	20300
434450	PRESENT		1254	20300
434500	PRESENT		1255	20300
434550	PRESENT		342300	51100
434600	PRESENT		342350	51100
434650	PRESENT		342400	51100
434700	PRESENT		342450	51100
434750	PRESENT		342500	51100
434800	PRESENT		342300	51150
434850	PRESENT		342350	51150
434900	PRESENT		342400	51150
434950	PRESENT		342450	51150
435000	PRESENT		342500	51150
435050	PRESENT		342300	51200
435100	PRESENT		342350	51200
435150	PRESENT		342400	51200
435200	PRESENT		342450	51200
435250	PRESENT		342500	51200
435300	PRESENT		342300	51250
435350	PRESENT		342350	51250
435400	PRESENT		342400	51250
435450	PRESENT		342450	51250
435500	PRESENT		342500	51250
435550	PRESENT		342300	51300
435600	PRESENT		342350	51300
435650	PRESENT		342400	51300
435700	PRESENT		342450	51300
435750	PRESENT		342500	51300
435800	PRESENT		342550	82100
435850	PRESENT		342600	82100
435900	PRESENT		342650	82100
435950	PRESENT		342700	82100
436000	PRESENT		342750	82100
436050	PRESENT		342550	82150
436100	PRESENT		342600	82150
436150	PRESENT		342650	82150
436200	PRESENT		342700	82150
436250	PRESENT		342750	82150
436300	PRESENT		342550	82200
436350	PRESENT		342600	82200
436400	PRESENT		342650	82200
436450	PRESENT		342700	82200
436500	PRESENT		342750	82200
436550	PRESENT		342550	82250
436600	PRESENT		342600	82250
436650	PRESENT		342650	82250
436700	PRESENT		342700	82250
436750	PRESENT		342750	82250
436800	PRESENT		342550	82300
436850	PRESENT		342600	82300
436900	PRESENT		342650	82300
436950	PRESENT		342700	82300
437000	PRESENT		342750	82300
437050	PRESENT		342800	113100
437100	PRESENT		342850	113100
437150	PRESENT		342900	113100
437200	PRESENT		342950	113100
437250	PRESENT		343000	113100
437300	PRESENT		342800	113150
437350	PRESENT		342850	113150
437400	PRESENT		342900	113150
437450	PRESENT		342950	113150
437500	PRESENT		343000	113150
437550	PRESENT		342800	113200
437600	PRESENT		342850	113200
437650	PRESENT		342900	113200
437700	PRESENT		342950	113200
437750	PRESENT		343000	113200
437800	PRESENT		342800	113250
437850	PRESENT		342850	113250
437900	PRESENT		342900	113250
437950	PRESENT		342950	113250
438000	PRESENT		343000	113250
438050	PRESENT		342800	113300
438100	PRESENT		342850	113300
438150	PRESENT		342900	113300
438200	PRESENT		342950	113300
438250	PRESENT		343000	113300
438300	PRESENT		1251	25850
438350	PRESENT		1252	25850
438400	PRESENT		1253	25850
438450	PRESENT		1254	25850
438500	PRESENT		1255	25850
438550	PRESENT		1251	25900
438600	PRESENT		1252	25900
438650	PRESENT		1253	25900
438700	PRESENT		1254	25900
438750	PRESENT		1255	25900
438800	PRESENT		1251	25950
438850	PRESENT		1252	25950
438900	PRESENT		1253	25950
438950	PRESENT		1254	25950
439000	PRESENT		1255	25950
439050	PRESENT		1251	26000
439100	PRESENT		1252	26000
439150	PRESENT		1253	26000
439200	PRESENT		1254	26000
439250	PRESENT		1255	26000
439300	PRESENT		1251	26050
439350	PRESENT		1252	26050
439400	PRESENT		1253	26050
439450	PRESENT		1254	26050
439500	PRESENT		1255	26050
439550	PRESENT		342300	56850
439600	PRESENT		342350	56850
439650	PRESENT		342400	56850
439700	PRESENT		342450	56850
439750	PRESENT		342500	56850
439800	PRESENT		342300	56900
439850	PRESENT		342350	56900
439900	PRESENT		342400	56900
439950	PRESENT		342450	56900
440000	PRESENT		342500	56900
440050	PRESENT		342300	56950
440100	PRESENT		342350	56950
440150	PRESENT		342400	56950
440200	PRESENT		342450	56950
440250	PRESENT		342500	56950
440300	PRESENT		342300	57000
440350	PRESENT		342350	57000
440400	PRESENT		342400	57000
440450	PRESENT		342450	57000
440500	PRESENT		342500	57000
440550	PRESENT		342300	57050
440600	PRESENT		342350	57050
440650	PRESENT		342400	57050
440700	PRESENT		342450	57050
440750	PRESENT		342500	57050
440800	PRESENT		342550	87850
440850	PRESENT		342600	87850
440900	PRESENT		342650	87850
440950	PRESENT		342700	87850
441000	PRESENT		342750	87850
441050	PRESENT		342550	87900
441100	PRESENT		342600	87900
441150	PRESENT		342650	87900
441200	PRESENT		342700	87900
441250	PRESENT		342750	87900
441300	PRESENT		342550	87950
441350	PRESENT		342600	87950
441400	PRESENT		342650	87950
441450	PRESENT		342700	87950
441500	PRESENT		342750	87950
441550	PRESENT		342550	88000
441600	PRESENT		342600	88000
441650	PRESENT		342650	88000
441700	PRESENT		342700	88000
441750	PRESENT		342750	88000
441800	PRESENT		342550	88050
441850	PRESENT		342600	88050
441900	PRESENT		342650	88050
441950	PRESENT		342700	88050
442000	PRESENT		342750	88050
442050	PRESENT		342800	118850
442100	PRESENT		342850	118850
442150	PRESENT		342900	118850
442200	PRESENT		342950	118850
442250	PRESENT		343000	118850
442300	PRESENT		342800	118900
442350	PRESENT		342850	118900
442400	PRESENT		342900	118900
442450	PRESENT		342950	118900
442500	PRESENT		343000	118900
442550	PRESENT		342800	118950
442600	PRESENT		342850	118950
442650	PRESENT		342900	118950
442700	PRESENT		342950	118950
442750	PRESENT		343000	118950
442800	PRESENT		342800	119000
442850	PRESENT		342850	119000
442900	PRESENT		342900	119000
442950	PRESENT		342950	119000
443000	PRESENT		343000	119000
443050	PRESENT		342800	119050
443100	PRESENT		342850	119050
443150	PRESENT		342900	119050
443200	PRESENT		342950	119050
443250	PRESENT		343000	119050
414050	ABSENT		1251	44750
414250	ABSENT		1255	44750
\.


--
-- Data for Name: subject; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subject (id, subject_code, subject_type, subject_desc, status, department_id, batch_id) FROM stdin;
1351	OS	COMMON	Operating System	ACTIVE	1101	1151
1352	SE	COMMON	Software Engineering	ACTIVE	1101	1151
1353	DB	COMMON	DataBase	ACTIVE	1101	1151
1354	UML	COMMON	uml	ACTIVE	1101	1151
1355	JAVA	COMMON	Java	ACTIVE	1101	1151
15250	SAD	COMMON	System Analysis and Design	ACTIVE	1101	11350
15300	NET	COMMON	Networking	ACTIVE	1101	11350
15400	OOP	COMMON	Object Oriented Programming	ACTIVE	1101	11350
15450	ELE	COMMON	Electronics	ACTIVE	1101	11400
15500	ASE	COMMON	Advanced System Engineering	ACTIVE	1101	11400
15550	C	COMMON	C Programming	ACTIVE	1101	11400
15600	Cobol	COMMON	Cobol Programming	ACTIVE	1101	11400
15650	ME	COMMON	Mathmatical Analysis	ACTIVE	1101	11450
15700	SC	COMMON	Scientific Computing	ACTIVE	1101	11450
15750	Project	COMMON	Internship Project	ACTIVE	1101	11450
15350	OR	COMMON	Operations Research	ACTIVE	1101	11350
\.


--
-- Data for Name: teach; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.teach (id, jhi_desc, subject_id, teacher_id) FROM stdin;
1401	OS Teaching	1351	1301
1402	SE Teaching	1352	1302
1403	DB Teaching	1353	1303
1404	UML Teaching	1354	1304
1405	JAVA Teaching	1355	1305
16200	ME Teaching	1351	1304
16250	SC Teaching	1352	1305
16300	Internship Project	1353	1306
15800	SAD Teaching	15250	1306
15850	NET Teaching	15300	1307
15900	OR Teaching	15350	1308
15950	OOP Teaching	15400	1309
16000	ELE Teaching	15450	1310
16050	ASE Teaching	15500	1301
16100	C Teaching	15550	1302
16150	COBOL Teaching	15600	1303
\.


--
-- Data for Name: teacher; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.teacher (id, teacher_name, teacher_middle_name, teacher_last_name, father_name, father_middle_name, father_last_name, mother_name, mother_middle_name, mother_last_name, aadhar_no, date_of_birth, place_of_birth, religion, caste, sub_caste, age, sex, blood_group, address_line_one, address_line_two, address_line_three, town, state, country, pincode, teacher_contact_number, alternate_contact_number, teacher_email_address, alternate_email_address, relation_with_staff, name, middle_name, last_name, contact_no, email_address, upload_photo, employee_id, designation, staff_type, department_id, branch_id) FROM stdin;
1302	Prof. Tonny Kris	K	Kris	xyz	xyz	xyz	xyz	xyz	xyz	389598984845	1992-07-06	Delhi	HINDU	BC	BCB	19	MALE	ABPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	Hyd	TG	india	533101	91002617333	94749584985	brown@gmail.com	profbrown@gmail.com	FATHER	Nitim			9491637830	nitim@gmail.com	123	596	vp	TEACHING	1101	1001
1303	Prof. Kim Theressa	T	Theressa	xyz	xyz	xyz	xyz	xyz	xyz	389598984845	1992-07-06	Delhi	HINDU	BC	BCB	19	MALE	ABPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	Hyd	TG	india	533101	91002617333	94749584985	brown@gmail.com	profbrown@gmail.com	FATHER	Nitim			9491637830	nitim@gmail.com	123	596	vp	TEACHING	1101	1001
1304	Prof. Tim Victor	V	Victor	xyz	xyz	xyz	xyz	xyz	xyz	389598984845	1992-07-06	Delhi	HINDU	BC	BCB	19	MALE	ABPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	Hyd	TG	india	533101	91002617333	94749584985	brown@gmail.com	profbrown@gmail.com	FATHER	Nitim			9491637830	nitim@gmail.com	123	596	vp	TEACHING	1101	1001
1305	Prof. Jenny Thomas	J	Thomas	xyz	xyz	xyz	xyz	xyz	xyz	389598984845	1992-07-06	Delhi	HINDU	BC	BCB	19	MALE	ABPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	Hyd	TG	india	533101	91002617333	94749584985	brown@gmail.com	profbrown@gmail.com	FATHER	Nitim			9491637830	nitim@gmail.com	123	596	vp	TEACHING	1101	1001
1306	Prof. Ken Babel	K	Babel	xyz	xyz	xyz	xyz	xyz	xyz	389598984845	1992-07-06	Delhi	HINDU	BC	BCB	19	MALE	ABPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	Hyd	TG	india	533101	91002617333	94749584985	brown@gmail.com	profbrown@gmail.com	FATHER	Nitim			9491637830	nitim@gmail.com	123	596	vp	TEACHING	1101	1001
1307	Prof. Jenny Hon	J	Hon	xyz	xyz	xyz	xyz	xyz	xyz	389598984845	1992-07-06	Delhi	HINDU	BC	BCB	19	MALE	ABPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	Hyd	TG	india	533101	91002617333	94749584985	brown@gmail.com	profbrown@gmail.com	FATHER	Nitim			9491637830	nitim@gmail.com	123	596	vp	TEACHING	1101	1001
1308	Prof. Rick Qunicy	M	Qunicy	xyz	xyz	xyz	xyz	xyz	xyz	389598984845	1992-07-06	Delhi	HINDU	BC	BCB	19	MALE	ABPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	Hyd	TG	india	533101	91002617333	94749584985	brown@gmail.com	profbrown@gmail.com	FATHER	Nitim			9491637830	nitim@gmail.com	123	596	vp	TEACHING	1101	1001
1309	Prof. Wayn Alda	K	Alda	xyz	xyz	xyz	xyz	xyz	xyz	389598984845	1992-07-06	Delhi	HINDU	BC	BCB	19	MALE	ABPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	Hyd	TG	india	533101	91002617333	94749584985	brown@gmail.com	profbrown@gmail.com	FATHER	Nitim			9491637830	nitim@gmail.com	123	596	vp	TEACHING	1101	1001
1310	Prof. Karen Nicy	S	Nicy	xyz	xyz	xyz	xyz	xyz	xyz	389598984845	1992-07-06	Delhi	HINDU	BC	BCB	19	MALE	ABPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	Hyd	TG	india	533101	91002617333	94749584985	brown@gmail.com	profbrown@gmail.com	FATHER	Nitim			9491637830	nitim@gmail.com	123	596	vp	TEACHING	1101	1001
1301	Prof. Jhon Brown	W	Brown	xyz	xyz	xyz	xyz	xyz	xyz	389598984845	1992-07-06	Delhi	HINDU	BC	BCB	19	MALE	ABPOSITIVE	DoorNo:42-3-2	kothapeta	Hyd	Hyd	TG	india	533101	91002617333	94749584985	brown@gmail.com	profbrown@gmail.com	FATHER	Nitim			9491637830	nitim@gmail.com	123	595	vp	TEACHING	1101	1001
\.


--
-- Data for Name: term; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.term (id, terms_desc, start_date, end_date, term_status, academicyear_id) FROM stdin;
1501	1st Semester	2019-01-01	2019-06-30	ACTIVE	1051
19800	2nd Semester	2019-07-01	2019-12-31	ACTIVE	1051
\.


--
-- Data for Name: transport_route; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transport_route (id, route_name, route_details, route_map_url) FROM stdin;
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1000, false);


--
-- Name: jhi_persistent_audit_evt_data jhi_persistent_audit_evt_data_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT jhi_persistent_audit_evt_data_pkey PRIMARY KEY (event_id, name);


--
-- Name: jhi_user_authority jhi_user_authority_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT jhi_user_authority_pkey PRIMARY KEY (user_id, authority_name);


--
-- Name: academic_year pk_academic_year; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.academic_year
    ADD CONSTRAINT pk_academic_year PRIMARY KEY (id);


--
-- Name: attendance_master pk_attendance_master; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attendance_master
    ADD CONSTRAINT pk_attendance_master PRIMARY KEY (id);


--
-- Name: authorized_signatory pk_authorized_signatory; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorized_signatory
    ADD CONSTRAINT pk_authorized_signatory PRIMARY KEY (id);


--
-- Name: bank_accounts pk_bank_accounts; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bank_accounts
    ADD CONSTRAINT pk_bank_accounts PRIMARY KEY (id);


--
-- Name: batch pk_batch; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch
    ADD CONSTRAINT pk_batch PRIMARY KEY (id);


--
-- Name: branch pk_branch; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.branch
    ADD CONSTRAINT pk_branch PRIMARY KEY (id);


--
-- Name: city pk_city; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT pk_city PRIMARY KEY (id);


--
-- Name: college pk_college; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.college
    ADD CONSTRAINT pk_college PRIMARY KEY (id);


--
-- Name: country pk_country; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT pk_country PRIMARY KEY (id);


--
-- Name: currency pk_currency; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.currency
    ADD CONSTRAINT pk_currency PRIMARY KEY (id);


--
-- Name: databasechangeloglock pk_databasechangeloglock; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT pk_databasechangeloglock PRIMARY KEY (id);


--
-- Name: department pk_department; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT pk_department PRIMARY KEY (id);


--
-- Name: due_date pk_due_date; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.due_date
    ADD CONSTRAINT pk_due_date PRIMARY KEY (id);


--
-- Name: facility pk_facility; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.facility
    ADD CONSTRAINT pk_facility PRIMARY KEY (id);


--
-- Name: fee_category pk_fee_category; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fee_category
    ADD CONSTRAINT pk_fee_category PRIMARY KEY (id);


--
-- Name: fee_details pk_fee_details; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fee_details
    ADD CONSTRAINT pk_fee_details PRIMARY KEY (id);


--
-- Name: holiday pk_holiday; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.holiday
    ADD CONSTRAINT pk_holiday PRIMARY KEY (id);


--
-- Name: invoice pk_invoice; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT pk_invoice PRIMARY KEY (id);


--
-- Name: jhi_authority pk_jhi_authority; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_authority
    ADD CONSTRAINT pk_jhi_authority PRIMARY KEY (name);


--
-- Name: jhi_persistent_audit_event pk_jhi_persistent_audit_event; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_persistent_audit_event
    ADD CONSTRAINT pk_jhi_persistent_audit_event PRIMARY KEY (event_id);


--
-- Name: jhi_user pk_jhi_user; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT pk_jhi_user PRIMARY KEY (id);


--
-- Name: late_fee pk_late_fee; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.late_fee
    ADD CONSTRAINT pk_late_fee PRIMARY KEY (id);


--
-- Name: lecture pk_lecture; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lecture
    ADD CONSTRAINT pk_lecture PRIMARY KEY (id);


--
-- Name: legal_entity pk_legal_entity; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.legal_entity
    ADD CONSTRAINT pk_legal_entity PRIMARY KEY (id);


--
-- Name: location pk_location; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.location
    ADD CONSTRAINT pk_location PRIMARY KEY (id);


--
-- Name: payment_remainder pk_payment_remainder; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_remainder
    ADD CONSTRAINT pk_payment_remainder PRIMARY KEY (id);


--
-- Name: section pk_section; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.section
    ADD CONSTRAINT pk_section PRIMARY KEY (id);


--
-- Name: state pk_state; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.state
    ADD CONSTRAINT pk_state PRIMARY KEY (id);


--
-- Name: student pk_student; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT pk_student PRIMARY KEY (id);


--
-- Name: student_attendance pk_student_attendance; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_attendance
    ADD CONSTRAINT pk_student_attendance PRIMARY KEY (id);


--
-- Name: subject pk_subject; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subject
    ADD CONSTRAINT pk_subject PRIMARY KEY (id);


--
-- Name: teach pk_teach; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teach
    ADD CONSTRAINT pk_teach PRIMARY KEY (id);


--
-- Name: teacher pk_teacher; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teacher
    ADD CONSTRAINT pk_teacher PRIMARY KEY (id);


--
-- Name: term pk_term; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.term
    ADD CONSTRAINT pk_term PRIMARY KEY (id);


--
-- Name: transport_route pk_transport_route; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transport_route
    ADD CONSTRAINT pk_transport_route PRIMARY KEY (id);


--
-- Name: jhi_user ux_user_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_email UNIQUE (email);


--
-- Name: jhi_user ux_user_login; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_login UNIQUE (login);


--
-- Name: idx_persistent_audit_event; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_persistent_audit_event ON public.jhi_persistent_audit_event USING btree (principal, event_date);


--
-- Name: idx_persistent_audit_evt_data; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_persistent_audit_evt_data ON public.jhi_persistent_audit_evt_data USING btree (event_id);


--
-- Name: attendance_master fk_attendance_master_batch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attendance_master
    ADD CONSTRAINT fk_attendance_master_batch_id FOREIGN KEY (batch_id) REFERENCES public.batch(id);


--
-- Name: attendance_master fk_attendance_master_section_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attendance_master
    ADD CONSTRAINT fk_attendance_master_section_id FOREIGN KEY (section_id) REFERENCES public.section(id);


--
-- Name: attendance_master fk_attendance_master_teach_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attendance_master
    ADD CONSTRAINT fk_attendance_master_teach_id FOREIGN KEY (teach_id) REFERENCES public.teach(id);


--
-- Name: jhi_user_authority fk_authority_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES public.jhi_authority(name);


--
-- Name: authorized_signatory fk_authorized_signatory_branch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorized_signatory
    ADD CONSTRAINT fk_authorized_signatory_branch_id FOREIGN KEY (branch_id) REFERENCES public.branch(id);


--
-- Name: authorized_signatory fk_authorized_signatory_college_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorized_signatory
    ADD CONSTRAINT fk_authorized_signatory_college_id FOREIGN KEY (college_id) REFERENCES public.college(id);


--
-- Name: bank_accounts fk_bank_accounts_branch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bank_accounts
    ADD CONSTRAINT fk_bank_accounts_branch_id FOREIGN KEY (branch_id) REFERENCES public.branch(id);


--
-- Name: bank_accounts fk_bank_accounts_college_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bank_accounts
    ADD CONSTRAINT fk_bank_accounts_college_id FOREIGN KEY (college_id) REFERENCES public.college(id);


--
-- Name: batch fk_batch_department_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batch
    ADD CONSTRAINT fk_batch_department_id FOREIGN KEY (department_id) REFERENCES public.department(id);


--
-- Name: branch fk_branch_city_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.branch
    ADD CONSTRAINT fk_branch_city_id FOREIGN KEY (city_id) REFERENCES public.city(id);


--
-- Name: branch fk_branch_college_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.branch
    ADD CONSTRAINT fk_branch_college_id FOREIGN KEY (college_id) REFERENCES public.college(id);


--
-- Name: branch fk_branch_state_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.branch
    ADD CONSTRAINT fk_branch_state_id FOREIGN KEY (state_id) REFERENCES public.state(id);


--
-- Name: city fk_city_state_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT fk_city_state_id FOREIGN KEY (state_id) REFERENCES public.state(id);


--
-- Name: currency fk_currency_country_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.currency
    ADD CONSTRAINT fk_currency_country_id FOREIGN KEY (country_id) REFERENCES public.country(id);


--
-- Name: department fk_department_academicyear_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT fk_department_academicyear_id FOREIGN KEY (academicyear_id) REFERENCES public.academic_year(id);


--
-- Name: department fk_department_branch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT fk_department_branch_id FOREIGN KEY (branch_id) REFERENCES public.branch(id);


--
-- Name: due_date fk_due_date_branch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.due_date
    ADD CONSTRAINT fk_due_date_branch_id FOREIGN KEY (branch_id) REFERENCES public.branch(id);


--
-- Name: due_date fk_due_date_college_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.due_date
    ADD CONSTRAINT fk_due_date_college_id FOREIGN KEY (college_id) REFERENCES public.college(id);


--
-- Name: jhi_persistent_audit_evt_data fk_evt_pers_audit_evt_data; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT fk_evt_pers_audit_evt_data FOREIGN KEY (event_id) REFERENCES public.jhi_persistent_audit_event(event_id);


--
-- Name: fee_details fk_fee_details_academic_year_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fee_details
    ADD CONSTRAINT fk_fee_details_academic_year_id FOREIGN KEY (academic_year_id) REFERENCES public.academic_year(id);


--
-- Name: fee_details fk_fee_details_batch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fee_details
    ADD CONSTRAINT fk_fee_details_batch_id FOREIGN KEY (batch_id) REFERENCES public.batch(id);


--
-- Name: fee_details fk_fee_details_branch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fee_details
    ADD CONSTRAINT fk_fee_details_branch_id FOREIGN KEY (branch_id) REFERENCES public.branch(id);


--
-- Name: fee_details fk_fee_details_college_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fee_details
    ADD CONSTRAINT fk_fee_details_college_id FOREIGN KEY (college_id) REFERENCES public.college(id);


--
-- Name: fee_details fk_fee_details_department_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fee_details
    ADD CONSTRAINT fk_fee_details_department_id FOREIGN KEY (department_id) REFERENCES public.department(id);


--
-- Name: fee_details fk_fee_details_facility_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fee_details
    ADD CONSTRAINT fk_fee_details_facility_id FOREIGN KEY (facility_id) REFERENCES public.facility(id);


--
-- Name: fee_details fk_fee_details_fee_category_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fee_details
    ADD CONSTRAINT fk_fee_details_fee_category_id FOREIGN KEY (fee_category_id) REFERENCES public.fee_category(id);


--
-- Name: fee_details fk_fee_details_transport_route_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fee_details
    ADD CONSTRAINT fk_fee_details_transport_route_id FOREIGN KEY (transport_route_id) REFERENCES public.transport_route(id);


--
-- Name: holiday fk_holiday_academicyear_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.holiday
    ADD CONSTRAINT fk_holiday_academicyear_id FOREIGN KEY (academicyear_id) REFERENCES public.academic_year(id);


--
-- Name: invoice fk_invoice_academic_year_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fk_invoice_academic_year_id FOREIGN KEY (academic_year_id) REFERENCES public.academic_year(id);


--
-- Name: invoice fk_invoice_branch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fk_invoice_branch_id FOREIGN KEY (branch_id) REFERENCES public.branch(id);


--
-- Name: invoice fk_invoice_college_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fk_invoice_college_id FOREIGN KEY (college_id) REFERENCES public.college(id);


--
-- Name: invoice fk_invoice_due_date_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fk_invoice_due_date_id FOREIGN KEY (due_date_id) REFERENCES public.due_date(id);


--
-- Name: invoice fk_invoice_fee_category_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fk_invoice_fee_category_id FOREIGN KEY (fee_category_id) REFERENCES public.fee_category(id);


--
-- Name: invoice fk_invoice_fee_details_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fk_invoice_fee_details_id FOREIGN KEY (fee_details_id) REFERENCES public.fee_details(id);


--
-- Name: invoice fk_invoice_payment_remainder_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fk_invoice_payment_remainder_id FOREIGN KEY (payment_remainder_id) REFERENCES public.payment_remainder(id);


--
-- Name: invoice fk_invoice_student_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT fk_invoice_student_id FOREIGN KEY (student_id) REFERENCES public.student(id);


--
-- Name: late_fee fk_late_fee_branch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.late_fee
    ADD CONSTRAINT fk_late_fee_branch_id FOREIGN KEY (branch_id) REFERENCES public.branch(id);


--
-- Name: late_fee fk_late_fee_college_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.late_fee
    ADD CONSTRAINT fk_late_fee_college_id FOREIGN KEY (college_id) REFERENCES public.college(id);


--
-- Name: lecture fk_lecture_attendancemaster_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lecture
    ADD CONSTRAINT fk_lecture_attendancemaster_id FOREIGN KEY (attendancemaster_id) REFERENCES public.attendance_master(id);


--
-- Name: legal_entity fk_legal_entity_branch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.legal_entity
    ADD CONSTRAINT fk_legal_entity_branch_id FOREIGN KEY (branch_id) REFERENCES public.branch(id);


--
-- Name: legal_entity fk_legal_entity_city_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.legal_entity
    ADD CONSTRAINT fk_legal_entity_city_id FOREIGN KEY (city_id) REFERENCES public.city(id);


--
-- Name: legal_entity fk_legal_entity_college_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.legal_entity
    ADD CONSTRAINT fk_legal_entity_college_id FOREIGN KEY (college_id) REFERENCES public.college(id);


--
-- Name: legal_entity fk_legal_entity_state_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.legal_entity
    ADD CONSTRAINT fk_legal_entity_state_id FOREIGN KEY (state_id) REFERENCES public.state(id);


--
-- Name: payment_remainder fk_payment_remainder_branch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_remainder
    ADD CONSTRAINT fk_payment_remainder_branch_id FOREIGN KEY (branch_id) REFERENCES public.branch(id);


--
-- Name: payment_remainder fk_payment_remainder_college_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_remainder
    ADD CONSTRAINT fk_payment_remainder_college_id FOREIGN KEY (college_id) REFERENCES public.college(id);


--
-- Name: payment_remainder fk_payment_remainder_due_date_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_remainder
    ADD CONSTRAINT fk_payment_remainder_due_date_id FOREIGN KEY (due_date_id) REFERENCES public.due_date(id);


--
-- Name: section fk_section_batch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.section
    ADD CONSTRAINT fk_section_batch_id FOREIGN KEY (batch_id) REFERENCES public.batch(id);


--
-- Name: state fk_state_country_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.state
    ADD CONSTRAINT fk_state_country_id FOREIGN KEY (country_id) REFERENCES public.country(id);


--
-- Name: student_attendance fk_student_attendance_lecture_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_attendance
    ADD CONSTRAINT fk_student_attendance_lecture_id FOREIGN KEY (lecture_id) REFERENCES public.lecture(id);


--
-- Name: student_attendance fk_student_attendance_student_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_attendance
    ADD CONSTRAINT fk_student_attendance_student_id FOREIGN KEY (student_id) REFERENCES public.student(id);


--
-- Name: student fk_student_batch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fk_student_batch_id FOREIGN KEY (batch_id) REFERENCES public.batch(id);


--
-- Name: student fk_student_branch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fk_student_branch_id FOREIGN KEY (branch_id) REFERENCES public.branch(id);


--
-- Name: student fk_student_department_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fk_student_department_id FOREIGN KEY (department_id) REFERENCES public.department(id);


--
-- Name: student fk_student_section_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fk_student_section_id FOREIGN KEY (section_id) REFERENCES public.section(id);


--
-- Name: subject fk_subject_batch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subject
    ADD CONSTRAINT fk_subject_batch_id FOREIGN KEY (batch_id) REFERENCES public.batch(id);


--
-- Name: subject fk_subject_department_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subject
    ADD CONSTRAINT fk_subject_department_id FOREIGN KEY (department_id) REFERENCES public.department(id);


--
-- Name: teach fk_teach_subject_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teach
    ADD CONSTRAINT fk_teach_subject_id FOREIGN KEY (subject_id) REFERENCES public.subject(id);


--
-- Name: teach fk_teach_teacher_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teach
    ADD CONSTRAINT fk_teach_teacher_id FOREIGN KEY (teacher_id) REFERENCES public.teacher(id);


--
-- Name: teacher fk_teacher_branch_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teacher
    ADD CONSTRAINT fk_teacher_branch_id FOREIGN KEY (branch_id) REFERENCES public.branch(id);


--
-- Name: teacher fk_teacher_department_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teacher
    ADD CONSTRAINT fk_teacher_department_id FOREIGN KEY (department_id) REFERENCES public.department(id);


--
-- Name: term fk_term_academicyear_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.term
    ADD CONSTRAINT fk_term_academicyear_id FOREIGN KEY (academicyear_id) REFERENCES public.academic_year(id);


--
-- Name: jhi_user_authority fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.jhi_user(id);


--
-- PostgreSQL database dump complete
--

