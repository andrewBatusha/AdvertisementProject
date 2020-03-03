--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2020-03-03 17:23:06

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 205 (class 1259 OID 24693)
-- Name: advertisement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.advertisement (
    id integer NOT NULL,
    headline character varying(30),
    description character varying(500),
    theme character varying(30),
    phonenumber character varying(30),
    status character varying(30),
    date_published timestamp without time zone,
    id_user integer
);


ALTER TABLE public.advertisement OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 24691)
-- Name: advertisement_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.advertisement_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.advertisement_id_seq OWNER TO postgres;

--
-- TOC entry 2836 (class 0 OID 0)
-- Dependencies: 204
-- Name: advertisement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.advertisement_id_seq OWNED BY public.advertisement.id;


--
-- TOC entry 203 (class 1259 OID 24685)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    firstname character varying(30),
    lastname character varying(30),
    email character varying(40),
    password character varying(30),
    role character varying(30),
    banstatus integer,
    activated integer,
    token character varying(40)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 24683)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 2837 (class 0 OID 0)
-- Dependencies: 202
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 2695 (class 2604 OID 24696)
-- Name: advertisement id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advertisement ALTER COLUMN id SET DEFAULT nextval('public.advertisement_id_seq'::regclass);


--
-- TOC entry 2694 (class 2604 OID 24688)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 2830 (class 0 OID 24693)
-- Dependencies: 205
-- Data for Name: advertisement; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.advertisement (id, headline, description, theme, phonenumber, status, date_published, id_user) VALUES (1, 'Велопробіжка', 'Збираємося всі на пробіжку 09.03.2020 у 10:00 біля головного входу у парк Шевченка', 'SPORT', '+380993396684', 'APPROVED', '2020-03-03 17:21:37.975', 1);
INSERT INTO public.advertisement (id, headline, description, theme, phonenumber, status, date_published, id_user) VALUES (2, 'Вечір читання віршів Шевченка', 'Захід відбудеться 10.03.2020 по адресі Ольги Кобилянської 53-А о 18:00', 'ART', '+380993396684', 'APPROVED', '2020-03-03 17:21:38.001', 1);
INSERT INTO public.advertisement (id, headline, description, theme, phonenumber, status, date_published, id_user) VALUES (3, 'Виставка рукотворних робіт', 'Завітайте на виставку рукотворних робіт та майте змогу обмінятись досвідом з іншими майстрами цієї справи', 'HOBBY', '+380993396684', 'APPROVED', '2020-03-03 17:21:38.124', 1);
INSERT INTO public.advertisement (id, headline, description, theme, phonenumber, status, date_published, id_user) VALUES (4, 'Шукаємо продавця на калинку', 'Ви вмієте продавати і це у вас добре виходить, тоді вам до нас! Ми даруємо людям свято та посмішку, натомість отримуємо винагороду, котрою ми готові поділитись із вами.Якщо зацікавлені, то приходьте на Калинівський ринок 3 сектор 4 ряд біля чебуречної за деталями', 'WORK', '+380993396684', 'APPROVED', '2020-03-03 17:21:38.151', 1);
INSERT INTO public.advertisement (id, headline, description, theme, phonenumber, status, date_published, id_user) VALUES (5, 'Пиріжки', 'Продаю пиріжки з маком, картоплею та капустою.', 'ART', '+380993396684', 'DENIED', '2020-03-03 17:21:38.175', 1);
INSERT INTO public.advertisement (id, headline, description, theme, phonenumber, status, date_published, id_user) VALUES (6, 'Турнір з шахів', 'Шахматний клуб ''Біла Тура'' запрошує всіх бажаючих випробувати свої сили на міському чемпіонаті з шахів, що відбудеться 30.03.2020 о 13:00Плата за участь: 40 грн', 'SPORT', '+380993396684', 'WAITING', '2020-03-03 17:21:38.239', 1);
INSERT INTO public.advertisement (id, headline, description, theme, phonenumber, status, date_published, id_user) VALUES (7, 'Марафон по Калічанці', '20.03.2020 о 11:00 відбудеться легендарний забіг по Калічанці для всіх бажаючих за головний приз - мікрохвильова піч.', 'SPORT', '+380993396684', 'WAITING', '2020-03-03 17:21:38.267', 1);


--
-- TOC entry 2828 (class 0 OID 24685)
-- Dependencies: 203
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, firstname, lastname, email, password, role, banstatus, activated, token) VALUES (1, 'userName', 'userSurname', 'user@gmail.com', 'dXNlcg==', 'USER', 0, 1, 'b49844b1d5754ced8a034cc6af11a462');
INSERT INTO public.users (id, firstname, lastname, email, password, role, banstatus, activated, token) VALUES (2, 'managerName', 'managerSurname', 'manager@gmail.com', 'bWFuYWdlcg==', 'MANAGER', 0, 1, '4fd264b4709d4cbea58fa389aba3fdb8');
INSERT INTO public.users (id, firstname, lastname, email, password, role, banstatus, activated, token) VALUES (3, 'adminName', 'adminSurname', 'admin@gmail.com', 'YWRtaW4=', 'ADMIN', 0, 1, '8a3719a144a944dd80af9118f2cff28c');


--
-- TOC entry 2838 (class 0 OID 0)
-- Dependencies: 204
-- Name: advertisement_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.advertisement_id_seq', 7, true);


--
-- TOC entry 2839 (class 0 OID 0)
-- Dependencies: 202
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 3, true);


--
-- TOC entry 2699 (class 2606 OID 24701)
-- Name: advertisement advertisement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advertisement
    ADD CONSTRAINT advertisement_pkey PRIMARY KEY (id);


--
-- TOC entry 2697 (class 2606 OID 24690)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2700 (class 2606 OID 24702)
-- Name: advertisement advertisement_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advertisement
    ADD CONSTRAINT advertisement_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id);


-- Completed on 2020-03-03 17:23:06

--
-- PostgreSQL database dump complete
--

