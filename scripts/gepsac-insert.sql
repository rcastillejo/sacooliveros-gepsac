﻿--
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

INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('IND0001', 'Activo'		, null);
INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('IND0002', 'Inactivo'	, null);
INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('ACT0001', 'Registrado'	, null);
INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('EST0001', 'Registrado'	, null);
INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('EST0002', 'Configurado'	, null);
INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('EAI0001', 'Activo'		, null);
INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('EAI0002', 'Inactivo'	, null);
INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('PLA0001', 'Registrado'	, null);
INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('PLA0002', 'Configurado'	, null);
INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('PLA0003', 'Programado'	, null);
INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('EVP0001', 'Registrado'	, 'Evaluacion de Postulante Registrado');
INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('EVA0001', 'Registrado'	, 'Evaluacion de Acoso Escolar Registrado');
INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('EVA0002', 'Evaluado'	, 'Evaluacion de Acoso Escolar Evaluado');
INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('ALU0001', 'Registrado'	, 'Alumno Registrado');
INSERT INTO tp_estado (cod_estado, nom_estado, des_estado) VALUES ('ALU0002', 'Evaluado'	, 'Alumno Evaluado');


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

INSERT INTO tp_config (parametro, valor, descripcion) VALUES ('service.sexo.Masculino', '1', NULL);
INSERT INTO tp_config (parametro, valor, descripcion) VALUES ('service.sexo.Femenino', '2', NULL);

INSERT INTO tp_config (parametro, valor, descripcion) VALUES ('service.nivelEscolar.Primaria', '1', NULL);
INSERT INTO tp_config (parametro, valor, descripcion) VALUES ('service.nivelEscolar.Secundaria', '2', NULL);


INSERT INTO tp_perfil (cod_perfil, nom_perfil, des_perfil, usu_crea, fec_crea, usu_modif, fec_modif) VALUES ('P0001', 'Agresor', 'Agresor', NULL, '2015-11-26 21:10:26.977', NULL, NULL);
INSERT INTO tp_perfil (cod_perfil, nom_perfil, des_perfil, usu_crea, fec_crea, usu_modif, fec_modif) VALUES ('P0002', 'Victima', 'Victima', NULL, '2015-11-26 21:10:46.65', NULL, NULL);
INSERT INTO tp_perfil (cod_perfil, nom_perfil, des_perfil, usu_crea, fec_crea, usu_modif, fec_modif) VALUES ('P0003', 'Testigo', 'Testigo', NULL, '2015-11-26 21:10:58.444', NULL, NULL);

INSERT INTO tp_contextura (cod_contextura, nom_contextura) VALUES (1, 'Pequeño');
INSERT INTO tp_contextura (cod_contextura, nom_contextura) VALUES (2, 'Grande');
INSERT INTO tp_contextura (cod_contextura, nom_contextura) VALUES (3, 'Mediano');

INSERT INTO tp_estatura (cod_estatura, nom_estatura) VALUES (1, 'Bajo');
INSERT INTO tp_estatura (cod_estatura, nom_estatura) VALUES (2, 'Medio');
INSERT INTO tp_estatura (cod_estatura, nom_estatura) VALUES (3, 'Alto');

INSERT INTO tp_tipo_familia (cod_familia, nom_familia) VALUES (1, 'Nuclear');
INSERT INTO tp_tipo_familia (cod_familia, nom_familia) VALUES (2, 'Monoparental');
INSERT INTO tp_tipo_familia (cod_familia, nom_familia) VALUES (3, 'Extensa');
INSERT INTO tp_tipo_familia (cod_familia, nom_familia) VALUES (4, 'Esamblada');


INSERT INTO tp_religion (cod_religion, nom_religion) VALUES (0, 'Ateo');
INSERT INTO tp_religion (cod_religion, nom_religion) VALUES (1, 'Católico');
INSERT INTO tp_religion (cod_religion, nom_religion) VALUES (2, 'Evangélico');
INSERT INTO tp_religion (cod_religion, nom_religion) VALUES (3, 'Mormón');

INSERT INTO tp_nacionalidad (cod_nacionalidad, nom_nacionalidad) VALUES (1, 'Peruano');
INSERT INTO tp_nacionalidad (cod_nacionalidad, nom_nacionalidad) VALUES (2, 'Chileno');
INSERT INTO tp_nacionalidad (cod_nacionalidad, nom_nacionalidad) VALUES (3, 'Colombiano');

INSERT INTO tp_distrito (cod_distrito, nom_distrito) VALUES (1, 'Lince');
INSERT INTO tp_distrito (cod_distrito, nom_distrito) VALUES (2, 'Jesus María');
INSERT INTO tp_distrito (cod_distrito, nom_distrito) VALUES (3, 'La Victoria');
INSERT INTO tp_distrito (cod_distrito, nom_distrito) VALUES (4, 'Breña');
INSERT INTO tp_distrito (cod_distrito, nom_distrito) VALUES (5, 'Pueblo Libre');

INSERT INTO tp_provincia (cod_provincia, nom_provincia) VALUES (1, 'Lima');

INSERT INTO tp_departamento (cod_departamento, nom_departamento) VALUES (1, 'Lima');


insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500001', '1', '15', '3', '3', '2', '1', '0', '2', '5', '10', '4', '1', '1', '3', '1', '1', 'P0001','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500002', '1', '14', '3', '2', '3', '2', '2', '2', '4', '10.5', '2', '0', '1', '2', '1', '1', 'P0001','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500003', '1', '13', '2', '3', '3', '3', '3', '2', '4', '11', '2', '1', '1', '1', '1', '1', 'P0001','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500004', '1', '10', '3', '3', '4', '2', '4', '1', '6', '12', '1', '0', '1', '4', '1', '1', 'P0001','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500005', '1', '12', '3', '3', '2', '1', '0', '2', '2', '13', '2', '1', '1', '5', '1', '1', 'P0001','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500006', '1', '15', '3', '3', '4', '1', '0', '2', '3', '11', '3', '0', '1', '1', '1', '1', 'P0001','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500007', '1', '16', '3', '3', '3', '2', '1', '2', '5', '13', '2', '1', '1', '1', '1', '1', 'P0001','ALU0002');

insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500008', '1', '14', '1', '2', '3', '3', '3', '2', '4', '12', '2', '1', '1', '1', '1', '1', 'P0002','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500009', '1', '13', '3', '1', '1', '1', '2', '2', '4', '13', '2', '1', '1', '3', '1', '1', 'P0002','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500010', '1', '13', '1', '1', '2', '1', '0', '2', '3', '12.5', '1', '1', '1', '2', '1', '1', 'P0002','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500011', '1', '12', '2', '2', '4', '2', '3', '2', '2', '13', '2', '1', '1', '4', '1', '1', 'P0002','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500012', '1', '15', '1', '3', '2', '2', '2', '2', '5', '11', '2', '1', '1', '5', '1', '1', 'P0002','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500013', '1', '14', '1', '2', '3', '3', '3', '2', '4', '12', '2', '2', '1', '1', '1', '1', 'P0002','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500014', '1', '13', '3', '2', '4', '1', '2', '2', '4', '13', '2', '3', '1', '3', '1', '1', 'P0002','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500015', '1', '13', '1', '1', '2', '1', '0', '2', '3', '12.5', '1', '2', '1', '2', '1', '1', 'P0002','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500016', '1', '12', '2', '1', '2', '2', '3', '2', '2', '13', '2', '0', '1', '4', '1', '1', 'P0002','ALU0002');
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500017', '1', '15', '1', '3', '2', '2', '2', '2', '5', '11', '2', '2', '1', '5', '1', '1', 'P0002','ALU0002');


--Preguntas
--Victima
insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0001', '', 'Excluyen','No me dejan participar, me excluyen');
insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0002', '', 'Rompen','Rompen mis cosas a propósito');
insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0003', '', 'Esconden','Me esconden las cosas');
insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0004', '', 'Insultan','Me insultan');
insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0005', '', 'No me hablen','Dicen a otros que no estén conmigo o que no me hablen');

--Agresor

insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0006', '', 'Excluyo','No dejo participar, excluyo');
insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0007', '', 'Rompo','Rompo sus cosas a propósito');
insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0008', '', 'Robo','Robo sus cosas');
insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0009', '', 'Insulto','Insulto a mis compañeros');
insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0010', '', 'Difamo','Digo chismes falsos sobre él o ella');
insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0011', '', 'Manipulo','Trato de hacer que otros les desprecien');
insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0012', '', 'Agredo Verbal','Digo nombres ofensivos, comentarios o gestos con contenido sexual');

--Testigo
insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0013', '', 'Colaboro Maltrato','Colaboras en una situación de maltrato');
insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0014', '', 'Participo Burla','Participas en las burlas');
insert into tp_pregunta (cod_pregunta,tipo,alias,enunciado) values ('PR0015', '', 'Grabo Agresión','Grabas o tomas fotos de las agresiones');


--P0001 Agresor
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500099', '1', '13', '2', '3', '3', '3', '3', '2', '4', '11', '2', '1', '1', '1', '1', '1', null,'ALU0001');

--A201500099 | P0001
insert into tp_evaluacion_acoso_escolar (cod_evaluacion,fec_evaluacion,cod_alumno,cod_perfil,cod_estado) values ('EV20151205203901', now(), 'A201500099', null,'EVA0001');
	--Preguntas Respondidas Victima
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0001', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0002', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0003', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0004', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0005', 'NO', 0);
	--Preguntas Respondidas Agresor
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0006', 'SI', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0007', 'SI', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0008', 'SI', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0009', 'SI', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0010', 'SI', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0011', 'SI', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0012', 'SI', 0);
	--Preguntas Respondidas Testigo
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0013', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0014', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203901', 'PR0015', 'NO', 0);


--P0002 Victima
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500098', '1', '15', '1', '3', '2', '2', '2', '2', '5', '11', '2', '2', '1', '5', '1', '1', null,'ALU0001');

--A201500098 | P0002
insert into tp_evaluacion_acoso_escolar (cod_evaluacion,fec_evaluacion,cod_alumno,cod_perfil,cod_estado) values ('EV20151205203902', now(), 'A201500098', null,'EVA0001');
	--Preguntas Respondidas Victima
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0001', 'SI', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0002', 'SI', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0003', 'SI', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0004', 'SI', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0005', 'SI', 0);
	--Preguntas Respondidas Agresor
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0006', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0007', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0008', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0009', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0010', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0011', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0012', 'NO', 0);
	--Preguntas Respondidas Testigo
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0013', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0014', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203902', 'PR0015', 'NO', 0);


--P0003 Testigo
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500097', '1', '15', '1', '3', '2', '2', '2', '2', '5', '11', '2', '2', '1', '5', '1', '1', null,'ALU0001');

--A201500097 | P0003
insert into tp_evaluacion_acoso_escolar (cod_evaluacion,fec_evaluacion,cod_alumno,cod_perfil,cod_estado) values ('EV20151205203903', now(), 'A201500097', null,'EVA0001');
	--Preguntas Respondidas Victima
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0001', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0002', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0003', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0004', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0005', 'NO', 0);
	--Preguntas Respondidas Agresor
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0006', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0007', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0008', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0009', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0010', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0011', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0012', 'NO', 0);
	--Preguntas Respondidas Testigo
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0013', 'SI', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0014', 'SI', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203903', 'PR0015', 'SI', 0);


-- Ninguno
insert into tp_alumno_evaluado (cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values ('A201500096', '1', '15', '1', '3', '2', '2', '2', '2', '5', '11', '2', '2', '1', '5', '1', '1', null,'ALU0001');

--A201500096 | 
insert into tp_evaluacion_acoso_escolar (cod_evaluacion,fec_evaluacion,cod_alumno,cod_perfil,cod_estado) values ('EV20151205203904', now(), 'A201500096', null,'EVA0001');
	--Preguntas Respondidas Victima
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0001', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0002', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0003', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0004', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0005', 'NO', 0);
	--Preguntas Respondidas Agresor
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0006', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0007', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0008', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0009', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0010', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0011', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0012', 'NO', 0);
	--Preguntas Respondidas Testigo
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0013', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0014', 'NO', 0);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_pregunta, respuesta, orden_evaluado) values ('EV20151205203904', 'PR0015', 'NO', 0);
