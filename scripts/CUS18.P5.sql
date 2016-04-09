
--Eliminando la data de prueba
delete from tp_pregunta_evaluacion_regla where cod_evaluacion in ('EV20151205203905','EV20151205203906');
delete from tp_pregunta_evaluacion where cod_evaluacion in ('EV20151205203905','EV20151205203906');
delete from tp_evaluacion_acoso_escolar where cod_evaluacion in ('EV20151205203905','EV20151205203906');
delete from tp_solicitud_alumno where cod_solicitud in ('SP20160330123');
delete from tp_solicitud_psicologica where cod_solicitud in ('SP20160330123');
delete from tp_alumno_evaluado where cod_alumno in ('A201500095','A201500094');

--P0001 Agresor
insert into tp_alumno_evaluado (cod_alumno,nombres, apellido_pat, apellido_mat, sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values 
('A201500095', 'Francisco', 'Robles', 'Peñaherrera', '1', '13', '2', '3', '3', '3', '3', '2', '4', '11', '2', '1', '1', '1', '1', '1', null,'ALU0001');

--P0002 Victima
insert into tp_alumno_evaluado (cod_alumno,nombres, apellido_pat, apellido_mat, sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values 
('A201500094', 'Rodrigo', 'Silva', 'Prieto', '1', '15', '1', '3', '2', '2', '2', '2', '5', '11', '2', '2', '1', '5', '1', '1', null,'ALU0001');

--Solicitud
INSERT INTO tp_solicitud_psicologica(cod_solicitud, fec_solicitud, solicitante,descripcion,motivo, cod_estado)
    VALUES ('SP20160330123', '30/03/2016', 'gepsac','prueba',1, 'SPS0002');

INSERT INTO tp_solicitud_alumno(
            cod_solicitud, cod_alumno, dirigido)
    VALUES ('SP20160330123', 'A201500095', true);

INSERT INTO tp_solicitud_alumno(
            cod_solicitud, cod_alumno, dirigido)
    VALUES ('SP20160330123', 'A201500094', false);


--A201500095 | P0001
insert into tp_evaluacion_acoso_escolar (cod_evaluacion, cod_solicitud, cod_plantilla, fec_resuelto,cod_alumno,cod_perfil,cod_estado) values ('EV20151205203905', 'SP20160330123', 'PTEV0001', '30/03/2016', 'A201500095', null,'EVA0001');
	--Preguntas Respondidas Victima
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0001', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0002', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0003', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0004', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0005', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0001', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0002', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0003', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0004', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0005', 1, false);
	--Preguntas Respondidas Agresor
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0006', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0007', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0008', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0009', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0010', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0011', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0012', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0006', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0007', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0008', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0009', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0010', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0011', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0012', 0, false);
	--Preguntas Respondidas Testigo
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0013', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0014', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0015', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0013', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0014', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203905', 'PTEV0001', 'PR0015', 1, false);

--A201500094 | P0002
insert into tp_evaluacion_acoso_escolar (cod_evaluacion, cod_solicitud, cod_plantilla, fec_resuelto,cod_alumno,cod_perfil,cod_estado) values ('EV20151205203906', 'SP20160330123', 'PTEV0001', '30/03/2016', 'A201500094', null,'EVA0001');
	--Preguntas Respondidas Victima
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0001', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0002', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0003', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0004', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0005', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0001', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0002', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0003', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0004', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0005', 0, false);
	--Preguntas Respondidas Agresor                 
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0006', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0007', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0008', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0009', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0010', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0011', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0012', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0006', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0007', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0008', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0009', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0010', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0011', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0012', 1, false);
	--Preguntas Respondidas Testigo                 
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0013', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0014', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0015', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0013', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0014', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203906', 'PTEV0001', 'PR0015', 1, false);



--Data inicial
select cod_alumno as "Alumno", nombres as "Nombres", concat(apellido_pat||' '||apellido_mat) as "Apellidos", b.nom_perfil as "Perfil", c.nom_estado as "Estado"
from tp_alumno_evaluado a 
inner join tp_estado c on a.cod_estado = c.cod_estado
left join tp_perfil b on a.cod_perfil=b.cod_perfil
where a.cod_alumno in ('A201500095','A201500094');


select cod_solicitud as "Solicitud", a.nom_estado as "Estado"
from tp_solicitud_psicologica a 
inner join tp_estado c on a.cod_estado = c.cod_estado
where a.cod_solicitud in ('SP20160330123');

select cod_evaluacion as "Evaluacion", cod_solicitud as "Solicitud", a.cod_alumno as "Alumno", 
a.fec_resuelto as "Fecha Resuelto", a.fec_evaluacion as "Fecha Evaluacion",  b.nom_perfil as "Perfil", c.nom_estado as "Estado"
from tp_evaluacion_acoso_escolar a 
inner join tp_estado c on a.cod_estado = c.cod_estado
left join tp_perfil b on a.cod_perfil=b.cod_perfil
where a.cod_evaluacion in ('EV20151205203906','EV20151205203905');

--Condiciones de Entrada
select c.enunciado as "Pregunta", d.alternativa as "Respuesta", b.cod_regla as "Regla"
from tp_pregunta_evaluacion a 
inner join tp_pregunta c on a.cod_pregunta=c.cod_pregunta
inner join tp_alternativa d on a.secuencia = d.secuencia 
left join tp_pregunta_evaluacion_regla b on a.cod_evaluacion = b.cod_evaluacion and a.cod_plantilla=b.cod_plantilla and a.cod_pregunta=b.cod_pregunta and a.secuencia=b.secuencia
where a.cod_evaluacion in ('EV20151205203905') and seleccionado
order by a.cod_pregunta;
select c.enunciado as "Pregunta", d.alternativa as "Respuesta", b.cod_regla as "Regla"
from tp_pregunta_evaluacion a 
inner join tp_pregunta c on a.cod_pregunta=c.cod_pregunta
inner join tp_alternativa d on a.secuencia = d.secuencia 
left join tp_pregunta_evaluacion_regla b on a.cod_evaluacion = b.cod_evaluacion and a.cod_plantilla=b.cod_plantilla and a.cod_pregunta=b.cod_pregunta and a.secuencia=b.secuencia
where a.cod_evaluacion in ('EV20151205203906') and seleccionado
order by a.cod_pregunta;



--Verificacion de resultados

--Estado de Psicologia
select cod_solicitud as "Solicitud", c.nom_estado as "Estado"
from tp_solicitud_psicologica a 
inner join tp_estado c on a.cod_estado = c.cod_estado
where a.cod_solicitud in ('SP20160330123');

select cod_solicitud as "Solicitud", cod_alumno as "Alumno"
from tp_solicitud_alumno a 
where a.cod_solicitud in ('SP20160330123');

--Estado de evaluacion
select cod_evaluacion as "Evaluacion", cod_solicitud as "Solicitud", a.cod_alumno as "Alumno", 
a.fec_resuelto as "Fecha Resuelto", a.fec_evaluacion as "Fecha Evaluacion",  b.nom_perfil as "Perfil", c.nom_estado as "Estado"
from tp_evaluacion_acoso_escolar a 
inner join tp_estado c on a.cod_estado = c.cod_estado
left join tp_perfil b on a.cod_perfil=b.cod_perfil
where a.cod_evaluacion in ('EV20151205203906');

--Estado del Detalle de evaluacion
select c.enunciado as "Pregunta", d.alternativa as "Respuesta", b.cod_regla as "Regla"
from tp_pregunta_evaluacion a 
inner join tp_pregunta c on a.cod_pregunta=c.cod_pregunta
inner join tp_alternativa d on a.secuencia = d.secuencia 
left join tp_pregunta_evaluacion_regla b on a.cod_evaluacion = b.cod_evaluacion and a.cod_plantilla=b.cod_plantilla and a.cod_pregunta=b.cod_pregunta and a.secuencia=b.secuencia
where a.cod_evaluacion in ('EV20151205203906') and seleccionado
order by a.cod_pregunta;


--Estado de evaluacion
select cod_evaluacion as "Evaluacion", cod_solicitud as "Solicitud", a.cod_alumno as "Alumno", 
a.fec_resuelto as "Fecha Resuelto", a.fec_evaluacion as "Fecha Evaluacion",  b.nom_perfil as "Perfil", c.nom_estado as "Estado"
from tp_evaluacion_acoso_escolar a 
inner join tp_estado c on a.cod_estado = c.cod_estado
left join tp_perfil b on a.cod_perfil=b.cod_perfil
where a.cod_evaluacion in ('EV20151205203905');

select c.enunciado as "Pregunta", d.alternativa as "Respuesta", b.cod_regla as "Regla"
from tp_pregunta_evaluacion a 
inner join tp_pregunta c on a.cod_pregunta=c.cod_pregunta
inner join tp_alternativa d on a.secuencia = d.secuencia 
left join tp_pregunta_evaluacion_regla b on a.cod_evaluacion = b.cod_evaluacion and a.cod_plantilla=b.cod_plantilla and a.cod_pregunta=b.cod_pregunta and a.secuencia=b.secuencia
where a.cod_evaluacion in ('EV20151205203905') and seleccionado
order by a.cod_pregunta;

--Estado del alumno
select cod_alumno as "Alumno", nombres as "Nombres", concat(apellido_pat||' '||apellido_mat) as "Apellidos", b.nom_perfil as "Perfil", c.nom_estado as "Estado"
from tp_alumno_evaluado a 
inner join tp_estado c on a.cod_estado = c.cod_estado
left join tp_perfil b on a.cod_perfil=b.cod_perfil
where a.cod_alumno in ('A201500095','A201500094');

