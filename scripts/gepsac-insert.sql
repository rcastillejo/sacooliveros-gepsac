--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.9
-- Dumped by pg_dump version 9.3.9
-- Started on 2015-07-29 16:53:32

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 1992 (class 0 OID 16688)
-- Dependencies: 170
-- Data for Name: tp_estado; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tp_estado VALUES ('IND0001', 'Activo');
INSERT INTO tp_estado VALUES ('IND0002', 'Inactivo');
INSERT INTO tp_estado VALUES ('ACT0001', 'Registrado');
INSERT INTO tp_estado VALUES ('EST0001', 'Registrado');
INSERT INTO tp_estado VALUES ('EST0002', 'Configurado');
INSERT INTO tp_estado VALUES ('EAI0001', 'Activo');
INSERT INTO tp_estado VALUES ('EAI0002', 'Inactivo');
INSERT INTO tp_estado VALUES ('PLA0001', 'Registrado');
INSERT INTO tp_estado VALUES ('PLA0002', 'Configurado');
INSERT INTO tp_estado VALUES ('PLA0003', 'Programado');
INSERT INTO tp_estado VALUES ('EVP0001', 'Registrado');


--
-- TOC entry 1996 (class 0 OID 16730)
-- Dependencies: 174
-- Data for Name: tp_actividad; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tp_actividad VALUES ('ACT0001', 'Charla Prevención Bullying', 'Charla de prevención contra el bullying, conceptos.', NULL, 1, 1, 'S ', 1, 'ACT0001', NULL, '2015-07-29 15:35:27.888', NULL, NULL);
INSERT INTO tp_actividad VALUES ('ACT0002', 'Reconocimiento de  los roles del bullying', 'Permite reconocer el rol que participan los alumnos en el bullying', NULL, 1, 1, 'S ', 1, 'ACT0001', NULL, '2015-07-29 15:35:27.888', NULL, NULL);


--
-- TOC entry 1997 (class 0 OID 16744)
-- Dependencies: 175
-- Data for Name: tp_estrategia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tp_estrategia VALUES ('EST20150729001', 'Reducir los casos de acoso escolar', 'Reducir los casos de acoso escolar', NULL, 'EST0002');
INSERT INTO tp_estrategia VALUES ('EST20150729002', 'Incrementar la intervención de los padres', 'Incrementar la intervención de los padres', NULL, 'EST0001');


--
-- TOC entry 1995 (class 0 OID 16717)
-- Dependencies: 173
-- Data for Name: tp_indicador; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tp_indicador VALUES ('IND0001', 'Porcentaje Asistencia', NULL, 'count(if(asistencia))/count(*)', 'IND0001');


INSERT INTO tp_estrategia_actividad VALUES ('EST20150729001', 'ACT0001', 'EAI0001', NULL, '2015-07-29 16:22:23.112', NULL, NULL);
INSERT INTO tp_estrategia_actividad VALUES ('EST20150729001', 'ACT0002', 'EAI0001', NULL, '2015-07-29 16:22:31.914', NULL, NULL);

--
-- TOC entry 1998 (class 0 OID 16757)
-- Dependencies: 176
-- Data for Name: tp_estrategia_actividad_indicador; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tp_estrategia_indicador VALUES ('EST20150729001', 'ACT0001', 'IND0001', 'EAI0001', NULL, '2015-07-29 16:22:23.112', NULL, NULL);
INSERT INTO tp_estrategia_indicador VALUES ('EST20150729001', 'ACT0002', 'IND0001', 'EAI0001', NULL, '2015-07-29 16:22:31.914', NULL, NULL);


--
-- TOC entry 1993 (class 0 OID 16693)
-- Dependencies: 171
-- Data for Name: tp_plan; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tp_plan VALUES ('PLA20150729001', '2015', NULL, NULL, NULL, '2015-03-18 00:00:00', '2015-12-24 00:00:00', 'PLA0001', NULL, '2015-07-29 16:33:44.156', NULL, NULL);

INSERT INTO tp_plan_fecha_restriccion VALUES ('PLA20150729001', '2015-04-02', '2015-04-03', 'R', 'Semana Santa');
INSERT INTO tp_plan_fecha_restriccion VALUES ('PLA20150729001', '2015-05-01', '2015-05-01', 'U', 'Día del Trabajador');
INSERT INTO tp_plan_fecha_restriccion VALUES ('PLA20150729001', '2015-06-29', '2015-06-29', 'U', 'Día de San Pedro y San Pablo');
INSERT INTO tp_plan_fecha_restriccion VALUES ('PLA20150729001', '2015-07-28', '2015-07-29', 'R', 'Fiestas Patrias');
INSERT INTO tp_plan_fecha_restriccion VALUES ('PLA20150729001', '2015-10-08', '2015-10-08', 'U', 'Combate de Angamos');
INSERT INTO tp_plan_fecha_restriccion VALUES ('PLA20150729001', '2015-11-01', '2015-11-01', 'U', 'Día de los Santos');
INSERT INTO tp_plan_fecha_restriccion VALUES ('PLA20150729001', '2015-12-08', '2015-12-08', 'U', 'Día Festivo');


--
-- TOC entry 1999 (class 0 OID 16783)
-- Dependencies: 177
-- Data for Name: tp_plan_estrategia; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1994 (class 0 OID 16707)
-- Dependencies: 172
-- Data for Name: tp_plan_hito; Type: TABLE DATA; Schema: public; Owner: postgres
--



-- Completed on 2015-07-29 16:53:33

--
-- PostgreSQL database dump complete
--

INSERT INTO tp_perfil (cod_perfil, nom_perfil, des_perfil, usu_crea, fec_crea, usu_modif, fec_modif) VALUES ('P0001', 'Agresor', 'Agresor', NULL, '2015-11-26 21:10:26.977', NULL, NULL);
INSERT INTO tp_perfil (cod_perfil, nom_perfil, des_perfil, usu_crea, fec_crea, usu_modif, fec_modif) VALUES ('P0002', 'Victima', 'Victima', NULL, '2015-11-26 21:10:46.65', NULL, NULL);
INSERT INTO tp_perfil (cod_perfil, nom_perfil, des_perfil, usu_crea, fec_crea, usu_modif, fec_modif) VALUES ('P0003', 'Testigo', 'Testigo', NULL, '2015-11-26 21:10:58.444', NULL, NULL);


insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500001', 'Masculino', '15', 'Grande', 'Alto', 'Monoparental', '1', '0', 'Secundaria', '5', '10', '4', 'Católico', 'Peruano', 'La Victoria', 'Lima', 'Lima', 'P0001');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500002', 'Masculino', '14', 'Grande', 'Medio', 'Extensa', '2', '2', 'Secundaria', '4', '10.5', '2', '', 'Peruano', 'Jesus María', 'Lima', 'Lima', 'P0001');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500003', 'Masculino', '13', 'Mediano', 'Alto', 'Extensa', '3', '3', 'Secundaria', '4', '11', '2', 'Católico', 'Peruano', 'Lince', 'Lima', 'Lima', 'P0001');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500004', 'Masculino', '10', 'Grande', 'Alto', 'Esamblada', '2', '4', 'Primaria', '6', '12', '1', '', 'Peruano', 'Breña', 'Lima', 'Lima', 'P0001');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500005', 'Masculino', '12', 'Grande', 'Alto', 'Monoparental', '1', '0', 'Secundaria', '2', '13', '2', 'Católico', 'Peruano', 'Pueblo Libre', 'Lima', 'Lima', 'P0001');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500006', 'Masculino', '15', 'Grande', 'Alto', 'Esamblada', '1', '0', 'Secundaria', '3', '11', '3', '', 'Peruano', 'Lince', 'Lima', 'Lima', 'P0001');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500007', 'Masculino', '16', 'Grande', 'Alto', 'Extensa', '2', '1', 'Secundaria', '5', '13', '2', 'Católico', 'Peruano', 'Lince', 'Lima', 'Lima', 'P0001');


insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500008', 'Masculino', '14', 'Pequeño', 'Medio', 'Extensa', '3', '3', 'Secundaria', '4', '12', '2', 'Católico', 'Peruana', 'Lince', 'Lima', 'Lima', 'P0002');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500009', 'Masculino', '13', 'Grande', 'Bajo', 'Nuclear', '1', '2', 'Secundaria', '4', '13', '2', 'Católico', 'Peruana', 'La Victoria', 'Lima', 'Lima', 'P0002');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500010', 'Masculino', '13', 'Pequeño', 'Bajo', 'Monoparental', '1', '0', 'Secundaria', '3', '12.5', '1', 'Católico', 'Peruana', 'Jesus María', 'Lima', 'Lima', 'P0002');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500011', 'Masculino', '12', 'Mediano', 'Medio', 'Esamblada', '2', '3', 'Secundaria', '2', '13', '2', 'Católico', 'Peruana', 'Breña', 'Lima', 'Lima', 'P0002');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500012', 'Masculino', '15', 'Pequeño', 'Alto', 'Monoparental', '2', '2', 'Secundaria', '5', '11', '2', 'Católico', 'Peruana', 'Pueblo Libre', 'Lima', 'Lima', 'P0002');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500013', 'Masculino', '14', 'Pequeño', 'Medio', 'Extensa', '3', '3', 'Secundaria', '4', '12', '2', 'Evangelico', 'Peruana', 'Lince', 'Lima', 'Lima', 'P0002');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500014', 'Masculino', '13', 'Grande', 'Medio', 'Esamblada', '1', '2', 'Secundaria', '4', '13', '2', 'Mormón', 'Peruana', 'La Victoria', 'Lima', 'Lima', 'P0002');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500015', 'Masculino', '13', 'Pequeño', 'Bajo', 'Monoparental', '1', '0', 'Secundaria', '3', '12.5', '1', 'Evangelico', 'Peruana', 'Jesus María', 'Lima', 'Lima', 'P0002');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500016', 'Masculino', '12', 'Mediano', 'Bajo', 'Monoparental', '2', '3', 'Secundaria', '2', '13', '2', '', 'Peruana', 'Breña', 'Lima', 'Lima', 'P0002');
insert into tp_alumno_evaluado (cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil) values ('A201500017', 'Masculino', '15', 'Pequeño', 'Alto', 'Monoparental', '2', '2', 'Secundaria', '5', '11', '2', 'Evangelico', 'Peruana', 'Pueblo Libre', 'Lima', 'Lima', 'P0002');
