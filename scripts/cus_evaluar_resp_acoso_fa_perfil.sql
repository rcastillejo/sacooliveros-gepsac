
--Eliminando la data de prueba
delete from tp_pregunta_evaluacion where cod_evaluacion in ('EV20151205203904');
delete from tp_evaluacion_acoso_escolar where cod_evaluacion in ('EV20151205203904');
delete from tp_alumno_evaluado where cod_alumno in ('A201500096');

--Flujo Basico

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


--Verificacion de resultados
--Estado del alumno
select cod_alumno as NumeroAlumno, nombres as nombres, apellido_pat as apellidoPaterno, apellido_mat as apellidoMaterno, b.nom_perfil as perfil, c.nom_estado as estado
from tp_alumno_evaluado a 
left join tp_perfil b on a.cod_perfil=b.cod_perfil
left join tp_estado c on a.cod_estado = c.cod_estado
where a.cod_alumno in ('A201500096');

--Estado de evaluacion
select cod_evaluacion as NumeroEvaluacion, b.nom_perfil as perfil, c.nom_estado as estado
from tp_evaluacion_acoso_escolar a 
left join tp_perfil b on a.cod_perfil=b.cod_perfil
left join tp_estado c on a.cod_estado = c.cod_estado
where a.cod_evaluacion in ('EV20151205203904');

--Estado de alumno y evaluacion
select cod_evaluacion as NumeroEvaluacion, d.cod_alumno as NumeroAlumno, d.nombres as nombres, d.apellido_pat as apellidoPaterno, d.apellido_mat as apellidoMaterno, b.nom_perfil as perfil, c.nom_estado as estado
from tp_evaluacion_acoso_escolar a 
inner join tp_alumno_evaluado d on a.cod_alumno = d.cod_alumno
left join tp_perfil b on a.cod_perfil=b.cod_perfil
left join tp_estado c on a.cod_estado = c.cod_estado
where a.cod_evaluacion in ('EV20151205203904');
