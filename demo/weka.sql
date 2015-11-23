--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.9
-- Dumped by pg_dump version 9.3.9
-- Started on 2015-11-23 01:07:05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 32835)
-- Name: desertor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE desertor (
    pk_iddesertor integer,
    edad integer,
    genero character varying(2),
    educacion character varying(250),
    ingreso numeric,
    reclamo character varying(250),
    posesiontarjeta numeric,
    montoprestamo numeric,
    estadocuenta character varying(250),
    estadocredito character varying(250),
    notransacciones integer,
    montoconsumo numeric,
    cantidadtnx integer,
    desertor character varying(2)
);


ALTER TABLE public.desertor OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 32844)
-- Name: evaluado; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE evaluado (
    pk_iddesertor integer,
    edad integer,
    genero character varying(2),
    educacion character varying(250),
    ingreso numeric,
    reclamo character varying(250),
    posesiontarjeta numeric,
    montoprestamo numeric,
    estadocuenta character varying(250),
    estadocredito character varying(250),
    notransacciones integer,
    montoconsumo numeric,
    cantidadtnx integer,
    desertor character varying(2)
);


ALTER TABLE public.evaluado OWNER TO postgres;

--
-- TOC entry 1938 (class 0 OID 32835)
-- Dependencies: 172
-- Data for Name: desertor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (1, 54, 'M', 'UNIVERSITARIO', 1600, '1', 55, 8200, 'ABIERTO', 'ABIERTO', 0, 2250, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (2, 42, 'M', 'UNIVERSITARIO', 5800, '1', 33, 8200, 'ABIERTO', 'ABIERTO', 0, 3650, 7, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (3, 41, 'M', 'SECUNDARIA', 6900, '0', 28, 11500, 'ABIERTO', 'ABIERTO', 0, 600, 17, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (4, 35, 'M', 'TECNICO', 800, '3', 11, 2800, 'BLOQUEADO', 'BLOQUEADO', 0, 200, 6, 'si');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (5, 65, 'M', 'UNIVERSITARIO', 3800, '0', 18, 8400, 'ABIERTO', 'ABIERTO', 0, 4300, 8, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (6, 48, 'M', 'PRIMARIA', 5400, '1', 26, 12300, 'ABIERTO', 'ABIERTO', 0, 2600, 18, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (7, 57, 'F', 'UNIVERSITARIO', 5700, '0', 12, 13700, 'ABIERTO', 'ABIERTO', 0, 1650, 9, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (8, 58, 'F', 'PRIMARIA', 2000, '0', 22, 8300, 'ABIERTO', 'ABIERTO', 0, 1750, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (9, 57, 'M', 'TECNICO', 6100, '0', 46, 9700, 'ABIERTO', 'ABIERTO', 0, 4450, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (10, 44, 'M', 'PRIMARIA', 1600, '0', 24, 11900, 'ABIERTO', 'ABIERTO', 0, 2550, 13, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (11, 43, 'F', 'UNIVERSITARIO', 3400, '0', 44, 13500, 'ABIERTO', 'ABIERTO', 0, 4650, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (12, 46, 'F', 'UNIVERSITARIO', 3000, '1', 16, 7000, 'ABIERTO', 'ABIERTO', 0, 1250, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (13, 58, 'M', 'SECUNDARIA', 3500, '0', 15, 5900, 'ABIERTO', 'ABIERTO', 0, 3900, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (14, 55, 'F', 'SECUNDARIA', 5900, '1', 18, 11600, 'ABIERTO', 'ABIERTO', 0, 3600, 8, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (15, 37, 'F', 'UNIVERSITARIO', 5300, '0', 43, 11000, 'ABIERTO', 'ABIERTO', 0, 4500, 10, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (16, 60, 'M', 'PRIMARIA', 5600, '1', 27, 14100, 'ABIERTO', 'ABIERTO', 0, 1600, 11, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (17, 57, 'F', 'UNIVERSITARIO', 2200, '0', 40, 7400, 'ABIERTO', 'ABIERTO', 0, 3850, 8, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (18, 46, 'M', 'UNIVERSITARIO', 1300, '1', 17, 14800, 'ABIERTO', 'ABIERTO', 0, 1850, 13, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (19, 39, 'F', 'UNIVERSITARIO', 800, '3', 10, 3500, 'CERRADO', 'CERRADO', 2, 250, 4, 'si');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (20, 45, 'F', 'PRIMARIA', 6400, '0', 18, 9300, 'ABIERTO', 'ABIERTO', 0, 2650, 11, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (21, 41, 'M', 'UNIVERSITARIO', 3900, '1', 21, 5200, 'ABIERTO', 'ABIERTO', 0, 3650, 19, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (22, 38, 'M', 'PRIMARIA', 2200, '5', 9, 2500, 'BLOQUEADO', 'BLOQUEADO', 3, 250, 3, 'si');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (23, 65, 'F', 'PRIMARIA', 5700, '1', 54, 12200, 'ABIERTO', 'ABIERTO', 0, 3350, 16, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (24, 64, 'M', 'PRIMARIA', 4700, '0', 34, 5000, 'ABIERTO', 'ABIERTO', 0, 2600, 18, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (25, 47, 'M', 'PRIMARIA', 2200, '0', 46, 10000, 'ABIERTO', 'ABIERTO', 0, 1050, 17, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (26, 65, 'M', 'UNIVERSITARIO', 4200, '0', 31, 8600, 'ABIERTO', 'ABIERTO', 0, 3850, 20, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (27, 52, 'F', 'UNIVERSITARIO', 3000, '0', 30, 6500, 'ABIERTO', 'ABIERTO', 0, 1650, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (28, 65, 'M', 'PRIMARIA', 4000, '1', 21, 11100, 'ABIERTO', 'ABIERTO', 0, 3300, 14, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (29, 63, 'F', 'PRIMARIA', 3800, '0', 31, 9100, 'ABIERTO', 'ABIERTO', 0, 2400, 16, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (30, 36, 'M', 'UNIVERSITARIO', 4200, '1', 49, 5600, 'ABIERTO', 'ABIERTO', 0, 3100, 11, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (31, 47, 'M', 'TECNICO', 2800, '0', 23, 11100, 'ABIERTO', 'ABIERTO', 0, 3050, 20, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (32, 48, 'M', 'SECUNDARIA', 6300, '1', 32, 12900, 'ABIERTO', 'ABIERTO', 0, 4450, 11, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (33, 57, 'F', 'UNIVERSITARIO', 5000, '1', 52, 6300, 'ABIERTO', 'ABIERTO', 0, 700, 19, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (34, 44, 'F', 'SECUNDARIA', 2000, '0', 57, 5500, 'ABIERTO', 'ABIERTO', 0, 1350, 20, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (35, 41, 'M', 'SECUNDARIA', 2500, '0', 21, 10500, 'ABIERTO', 'ABIERTO', 0, 3600, 9, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (36, 18, 'F', 'PRIMARIA', 1300, '4', 12, 1800, 'BLOQUEADO', 'BLOQUEADO', 0, 350, 3, 'si');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (37, 61, 'M', 'TECNICO', 6300, '1', 26, 13400, 'ABIERTO', 'ABIERTO', 0, 4350, 8, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (38, 51, 'M', 'PRIMARIA', 4300, '0', 22, 12500, 'ABIERTO', 'ABIERTO', 0, 2500, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (39, 41, 'M', 'UNIVERSITARIO', 6100, '0', 50, 7900, 'ABIERTO', 'ABIERTO', 0, 2400, 8, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (40, 63, 'F', 'PRIMARIA', 2800, '1', 35, 9300, 'ABIERTO', 'ABIERTO', 0, 2650, 8, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (41, 59, 'F', 'UNIVERSITARIO', 1200, '1', 32, 10100, 'ABIERTO', 'ABIERTO', 0, 4450, 20, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (42, 57, 'M', 'UNIVERSITARIO', 1900, '1', 48, 12700, 'ABIERTO', 'ABIERTO', 0, 2550, 14, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (43, 18, 'F', 'UNIVERSITARIO', 1000, '4', 8, 4800, 'CERRADO', 'CERRADO', 3, 150, 3, 'si');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (44, 54, 'M', 'PRIMARIA', 3900, '0', 12, 11200, 'ABIERTO', 'ABIERTO', 0, 3150, 17, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (45, 58, 'M', 'UNIVERSITARIO', 4100, '0', 24, 11800, 'ABIERTO', 'ABIERTO', 0, 1600, 10, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (46, 57, 'M', 'UNIVERSITARIO', 1400, '0', 47, 9900, 'ABIERTO', 'ABIERTO', 0, 2550, 11, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (47, 36, 'F', 'SECUNDARIA', 4700, '1', 57, 11800, 'ABIERTO', 'ABIERTO', 0, 2600, 8, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (48, 41, 'F', 'UNIVERSITARIO', 1400, '0', 14, 8800, 'ABIERTO', 'ABIERTO', 0, 3600, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (49, 55, 'M', 'SECUNDARIA', 1400, '0', 35, 12100, 'ABIERTO', 'ABIERTO', 0, 2950, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (50, 46, 'M', 'UNIVERSITARIO', 2400, '0', 54, 8100, 'ABIERTO', 'ABIERTO', 0, 3800, 19, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (51, 45, 'M', 'PRIMARIA', 5200, '1', 55, 11200, 'ABIERTO', 'ABIERTO', 0, 1500, 7, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (52, 53, 'F', 'SECUNDARIA', 3000, '1', 35, 14700, 'ABIERTO', 'ABIERTO', 0, 500, 13, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (53, 39, 'F', 'PRIMARIA', 1200, '2', 15, 6400, 'CERRADO', 'CERRADO', 3, 350, 4, 'si');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (54, 38, 'M', 'TECNICO', 7000, '1', 23, 9100, 'ABIERTO', 'ABIERTO', 0, 1300, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (55, 63, 'F', 'UNIVERSITARIO', 6700, '1', 58, 11700, 'ABIERTO', 'ABIERTO', 0, 1150, 20, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (56, 63, 'M', 'SECUNDARIA', 3600, '0', 24, 12400, 'ABIERTO', 'ABIERTO', 0, 4150, 18, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (57, 61, 'F', 'PRIMARIA', 4200, '1', 48, 6200, 'ABIERTO', 'ABIERTO', 0, 3550, 17, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (58, 36, 'M', 'SECUNDARIA', 5700, '1', 37, 7700, 'ABIERTO', 'ABIERTO', 0, 1050, 19, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (59, 53, 'F', 'UNIVERSITARIO', 6100, '0', 44, 13000, 'ABIERTO', 'ABIERTO', 0, 3950, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (60, 48, 'M', 'UNIVERSITARIO', 2100, '1', 40, 9700, 'ABIERTO', 'ABIERTO', 0, 4350, 14, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (61, 42, 'M', 'UNIVERSITARIO', 2100, '4', 12, 2900, 'BLOQUEADO', 'BLOQUEADO', 2, 150, 6, 'si');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (62, 44, 'M', 'SECUNDARIA', 4600, '0', 57, 11200, 'ABIERTO', 'ABIERTO', 0, 4750, 9, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (63, 43, 'M', 'PRIMARIA', 5400, '0', 49, 14400, 'ABIERTO', 'ABIERTO', 0, 1900, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (64, 58, 'M', 'UNIVERSITARIO', 3300, '1', 47, 14700, 'ABIERTO', 'ABIERTO', 0, 1950, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (65, 54, 'M', 'PRIMARIA', 2900, '0', 20, 11300, 'ABIERTO', 'ABIERTO', 0, 4100, 19, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (66, 35, 'M', 'SECUNDARIA', 5300, '0', 37, 14300, 'ABIERTO', 'ABIERTO', 0, 3050, 14, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (67, 35, 'M', 'PRIMARIA', 6600, '1', 30, 14100, 'ABIERTO', 'ABIERTO', 0, 450, 13, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (68, 38, 'M', 'TECNICO', 3600, '1', 42, 9000, 'ABIERTO', 'ABIERTO', 0, 650, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (69, 37, 'M', 'TECNICO', 6400, '0', 23, 12000, 'ABIERTO', 'ABIERTO', 0, 3150, 20, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (70, 45, 'M', 'UNIVERSITARIO', 6300, '1', 57, 12400, 'ABIERTO', 'ABIERTO', 0, 500, 8, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (71, 56, 'F', 'UNIVERSITARIO', 2400, '0', 60, 6100, 'ABIERTO', 'ABIERTO', 0, 1100, 11, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (72, 65, 'M', 'UNIVERSITARIO', 5900, '0', 41, 6500, 'ABIERTO', 'ABIERTO', 0, 1850, 7, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (73, 37, 'M', 'UNIVERSITARIO', 3000, '1', 43, 7500, 'ABIERTO', 'ABIERTO', 0, 3500, 9, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (74, 62, 'M', 'UNIVERSITARIO', 5100, '1', 43, 9900, 'ABIERTO', 'ABIERTO', 0, 3200, 20, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (75, 53, 'F', 'UNIVERSITARIO', 4900, '1', 44, 7400, 'ABIERTO', 'ABIERTO', 0, 4400, 7, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (76, 27, 'F', 'SECUNDARIA', 2400, '4', 5, 6900, 'CERRADO', 'CERRADO', 1, 350, 6, 'si');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (77, 48, 'F', 'PRIMARIA', 6100, '0', 14, 7900, 'ABIERTO', 'ABIERTO', 0, 750, 11, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (78, 36, 'M', 'UNIVERSITARIO', 3500, '1', 56, 5300, 'ABIERTO', 'ABIERTO', 0, 4750, 8, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (79, 42, 'F', 'PRIMARIA', 5100, '1', 56, 12000, 'ABIERTO', 'ABIERTO', 0, 4750, 11, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (80, 41, 'F', 'TECNICO', 1800, '3', 8, 3300, 'BLOQUEADO', 'BLOQUEADO', 1, 250, 6, 'si');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (81, 45, 'M', 'TECNICO', 5400, '0', 56, 9300, 'ABIERTO', 'ABIERTO', 0, 2700, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (82, 48, 'F', 'UNIVERSITARIO', 3400, '1', 18, 14600, 'ABIERTO', 'ABIERTO', 0, 2550, 7, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (83, 64, 'M', 'PRIMARIA', 4900, '1', 33, 8100, 'ABIERTO', 'ABIERTO', 0, 2700, 18, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (84, 59, 'M', 'UNIVERSITARIO', 4100, '0', 32, 8000, 'ABIERTO', 'ABIERTO', 0, 950, 19, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (85, 51, 'F', 'PRIMARIA', 5800, '0', 48, 10300, 'ABIERTO', 'ABIERTO', 0, 3000, 20, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (86, 59, 'M', 'UNIVERSITARIO', 1200, '0', 14, 12900, 'ABIERTO', 'ABIERTO', 0, 2000, 20, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (87, 57, 'M', 'TECNICO', 4000, '0', 12, 12800, 'ABIERTO', 'ABIERTO', 0, 2550, 20, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (88, 56, 'F', 'UNIVERSITARIO', 5400, '1', 16, 10900, 'ABIERTO', 'ABIERTO', 0, 3300, 9, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (89, 50, 'M', 'SECUNDARIA', 5600, '0', 51, 9100, 'ABIERTO', 'ABIERTO', 0, 4450, 9, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (90, 53, 'F', 'UNIVERSITARIO', 4600, '1', 15, 9700, 'ABIERTO', 'ABIERTO', 0, 3550, 14, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (91, 50, 'F', 'UNIVERSITARIO', 1800, '0', 52, 9800, 'ABIERTO', 'ABIERTO', 0, 1550, 13, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (92, 37, 'M', 'PRIMARIA', 5600, '1', 59, 14300, 'ABIERTO', 'ABIERTO', 0, 3100, 16, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (93, 33, 'M', 'UNIVERSITARIO', 1100, '5', 6, 3100, 'CERRADO', 'CERRADO', 3, 150, 3, 'si');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (94, 54, 'M', 'SECUNDARIA', 1100, '0', 47, 13200, 'ABIERTO', 'ABIERTO', 0, 1850, 18, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (95, 37, 'M', 'UNIVERSITARIO', 4000, '1', 17, 12800, 'ABIERTO', 'ABIERTO', 0, 2200, 18, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (96, 47, 'M', 'UNIVERSITARIO', 2600, '1', 17, 12600, 'ABIERTO', 'ABIERTO', 0, 2400, 17, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (97, 59, 'F', 'TECNICO', 3200, '0', 53, 11600, 'ABIERTO', 'ABIERTO', 0, 4250, 14, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (98, 48, 'M', 'UNIVERSITARIO', 6700, '1', 37, 9800, 'ABIERTO', 'ABIERTO', 0, 1650, 7, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (99, 45, 'M', 'UNIVERSITARIO', 3800, '0', 40, 6900, 'ABIERTO', 'ABIERTO', 0, 3600, 17, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (100, 37, 'M', 'UNIVERSITARIO', 6200, '0', 45, 11500, 'ABIERTO', 'ABIERTO', 0, 2500, 19, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (101, 42, 'F', 'UNIVERSITARIO', 4800, '0', 23, 12600, 'ABIERTO', 'ABIERTO', 0, 3050, 9, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (102, 57, 'F', 'UNIVERSITARIO', 5300, '0', 60, 10100, 'ABIERTO', 'ABIERTO', 0, 1600, 9, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (103, 42, 'F', 'PRIMARIA', 4700, '1', 21, 9900, 'ABIERTO', 'ABIERTO', 0, 1500, 17, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (104, 44, 'F', 'UNIVERSITARIO', 3800, '1', 21, 5500, 'ABIERTO', 'ABIERTO', 0, 4300, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (105, 41, 'F', 'TECNICO', 4500, '1', 47, 11700, 'ABIERTO', 'ABIERTO', 0, 2800, 10, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (106, 65, 'F', 'UNIVERSITARIO', 1100, '0', 46, 9300, 'ABIERTO', 'ABIERTO', 0, 3100, 14, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (107, 47, 'F', 'UNIVERSITARIO', 6900, '1', 36, 5300, 'ABIERTO', 'ABIERTO', 0, 950, 16, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (108, 55, 'F', 'UNIVERSITARIO', 7000, '1', 43, 8500, 'ABIERTO', 'ABIERTO', 0, 2450, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (109, 38, 'F', 'SECUNDARIA', 2100, '0', 50, 7800, 'ABIERTO', 'ABIERTO', 0, 4700, 7, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (110, 64, 'F', 'UNIVERSITARIO', 6200, '0', 17, 10400, 'ABIERTO', 'ABIERTO', 0, 1050, 16, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (111, 35, 'F', 'UNIVERSITARIO', 3000, '1', 27, 8000, 'ABIERTO', 'ABIERTO', 0, 2850, 20, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (112, 37, 'F', 'SECUNDARIA', 2300, '0', 17, 12000, 'ABIERTO', 'ABIERTO', 0, 3500, 19, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (113, 48, 'F', 'UNIVERSITARIO', 3200, '0', 13, 12000, 'ABIERTO', 'ABIERTO', 0, 1900, 11, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (114, 38, 'F', 'SECUNDARIA', 6000, '0', 28, 8700, 'ABIERTO', 'ABIERTO', 0, 1100, 20, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (115, 55, 'F', 'TECNICO', 3800, '0', 56, 11000, 'ABIERTO', 'ABIERTO', 0, 1350, 18, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (116, 54, 'F', 'TECNICO', 2600, '0', 53, 8800, 'ABIERTO', 'ABIERTO', 0, 4350, 9, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (117, 38, 'F', 'SECUNDARIA', 2700, '0', 28, 6500, 'ABIERTO', 'ABIERTO', 0, 4800, 10, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (118, 37, 'F', 'PRIMARIA', 1700, '0', 36, 8300, 'ABIERTO', 'ABIERTO', 0, 3350, 14, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (119, 44, 'F', 'UNIVERSITARIO', 4200, '0', 45, 5100, 'ABIERTO', 'ABIERTO', 0, 2800, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (120, 42, 'F', 'UNIVERSITARIO', 1300, '1', 13, 5900, 'ABIERTO', 'ABIERTO', 0, 1150, 19, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (121, 40, 'F', 'UNIVERSITARIO', 3800, '1', 45, 14400, 'ABIERTO', 'ABIERTO', 0, 4400, 16, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (122, 63, 'F', 'TECNICO', 7000, '1', 16, 8100, 'ABIERTO', 'ABIERTO', 0, 500, 11, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (123, 46, 'F', 'TECNICO', 6700, '1', 55, 7800, 'ABIERTO', 'ABIERTO', 0, 2150, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (124, 61, 'F', 'UNIVERSITARIO', 2600, '1', 45, 8600, 'ABIERTO', 'ABIERTO', 0, 3250, 19, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (125, 61, 'F', 'UNIVERSITARIO', 1200, '1', 59, 14200, 'ABIERTO', 'ABIERTO', 0, 2600, 7, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (126, 36, 'F', 'UNIVERSITARIO', 2000, '0', 15, 13600, 'ABIERTO', 'ABIERTO', 0, 4000, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (127, 41, 'F', 'PRIMARIA', 7000, '1', 14, 12700, 'ABIERTO', 'ABIERTO', 0, 3600, 19, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (128, 63, 'F', 'TECNICO', 5700, '1', 60, 9400, 'ABIERTO', 'ABIERTO', 0, 4400, 11, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (129, 45, 'F', 'UNIVERSITARIO', 4400, '1', 58, 11200, 'ABIERTO', 'ABIERTO', 0, 450, 7, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (130, 60, 'F', 'TECNICO', 1800, '1', 51, 12300, 'ABIERTO', 'ABIERTO', 0, 2650, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (131, 39, 'F', 'SECUNDARIA', 6300, '0', 22, 13500, 'ABIERTO', 'ABIERTO', 0, 3550, 9, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (132, 62, 'F', 'PRIMARIA', 5300, '1', 41, 14800, 'ABIERTO', 'ABIERTO', 0, 1650, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (133, 60, 'F', 'PRIMARIA', 4600, '0', 22, 8800, 'ABIERTO', 'ABIERTO', 0, 2650, 17, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (134, 48, 'F', 'TECNICO', 5400, '1', 41, 10100, 'ABIERTO', 'ABIERTO', 0, 2000, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (135, 57, 'F', 'UNIVERSITARIO', 5600, '0', 36, 14100, 'ABIERTO', 'ABIERTO', 0, 1700, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (136, 51, 'F', 'PRIMARIA', 4000, '0', 28, 7800, 'ABIERTO', 'ABIERTO', 0, 3950, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (137, 51, 'F', 'TECNICO', 6600, '1', 57, 5200, 'ABIERTO', 'ABIERTO', 0, 2750, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (138, 41, 'F', 'SECUNDARIA', 2100, '1', 49, 8900, 'ABIERTO', 'ABIERTO', 0, 3150, 16, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (139, 59, 'F', 'PRIMARIA', 2500, '0', 57, 5600, 'ABIERTO', 'ABIERTO', 0, 2750, 7, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (140, 60, 'F', 'SECUNDARIA', 4600, '0', 35, 14100, 'ABIERTO', 'ABIERTO', 0, 900, 20, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (141, 36, 'F', 'UNIVERSITARIO', 3600, '0', 60, 7800, 'ABIERTO', 'ABIERTO', 0, 1650, 16, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (142, 37, 'F', 'UNIVERSITARIO', 3200, '1', 25, 11700, 'ABIERTO', 'ABIERTO', 0, 600, 19, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (143, 56, 'F', 'PRIMARIA', 5300, '1', 18, 13500, 'ABIERTO', 'ABIERTO', 0, 1550, 7, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (144, 40, 'F', 'UNIVERSITARIO', 1200, '0', 20, 9500, 'ABIERTO', 'ABIERTO', 0, 1250, 17, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (145, 42, 'F', 'UNIVERSITARIO', 2400, '1', 55, 10900, 'ABIERTO', 'ABIERTO', 0, 4800, 12, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (146, 48, 'F', 'PRIMARIA', 3100, '1', 27, 14100, 'ABIERTO', 'ABIERTO', 0, 3550, 14, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (147, 46, 'F', 'UNIVERSITARIO', 3800, '1', 18, 6500, 'ABIERTO', 'ABIERTO', 0, 1350, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (148, 65, 'F', 'PRIMARIA', 3300, '1', 33, 11000, 'ABIERTO', 'ABIERTO', 0, 2950, 18, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (149, 56, 'F', 'PRIMARIA', 6100, '0', 38, 7300, 'ABIERTO', 'ABIERTO', 0, 1300, 7, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (150, 52, 'F', 'TECNICO', 3400, '1', 35, 9000, 'ABIERTO', 'ABIERTO', 0, 2900, 10, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (151, 43, 'F', 'PRIMARIA', 5900, '0', 44, 7700, 'ABIERTO', 'ABIERTO', 0, 1100, 14, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (152, 45, 'F', 'UNIVERSITARIO', 6700, '0', 49, 7300, 'ABIERTO', 'ABIERTO', 0, 600, 11, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (153, 45, 'F', 'UNIVERSITARIO', 3000, '0', 27, 8900, 'ABIERTO', 'ABIERTO', 0, 4000, 9, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (154, 59, 'F', 'UNIVERSITARIO', 5200, '1', 33, 8000, 'ABIERTO', 'ABIERTO', 0, 2700, 20, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (155, 42, 'F', 'UNIVERSITARIO', 5200, '1', 29, 9900, 'ABIERTO', 'ABIERTO', 0, 1700, 16, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (156, 49, 'F', 'PRIMARIA', 2100, '0', 41, 11200, 'ABIERTO', 'ABIERTO', 0, 1800, 15, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (157, 45, 'F', 'TECNICO', 4100, '0', 17, 11300, 'ABIERTO', 'ABIERTO', 0, 2000, 19, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (158, 49, 'F', 'SECUNDARIA', 4600, '0', 16, 7900, 'ABIERTO', 'ABIERTO', 0, 2900, 18, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (159, 63, 'F', 'PRIMARIA', 3300, '1', 14, 14800, 'ABIERTO', 'ABIERTO', 0, 3750, 8, 'no');
INSERT INTO desertor (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (160, 53, 'F', 'PRIMARIA', 1100, '1', 17, 14100, 'ABIERTO', 'ABIERTO', 0, 2000, 9, 'no');


--
-- TOC entry 1939 (class 0 OID 32844)
-- Dependencies: 173
-- Data for Name: evaluado; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO evaluado (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (1, 41, 'M', 'SECUNDARIA', 6900, '0', 28, 11500, 'ABIERTO', 'ABIERTO', 0, 600, 17, '');
INSERT INTO evaluado (pk_iddesertor, edad, genero, educacion, ingreso, reclamo, posesiontarjeta, montoprestamo, estadocuenta, estadocredito, notransacciones, montoconsumo, cantidadtnx, desertor) VALUES (2, 35, 'M', 'TECNICO', 800, '3', 11, 2800, 'BLOQUEADO', 'BLOQUEADO', 0, 200, 6, NULL);


-- Completed on 2015-11-23 01:07:05

--
-- PostgreSQL database dump complete
--

