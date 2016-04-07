
--Eliminando la data de prueba
delete from tp_pregunta_evaluacion_regla where cod_evaluacion in ('EV20151205203903');
delete from tp_pregunta_evaluacion where cod_evaluacion in ('EV20151205203903');
delete from tp_evaluacion_acoso_escolar where cod_evaluacion in ('EV20151205203903');
delete from tp_alumno_evaluado where cod_alumno in ('A201500097');

--P0003 Testigo
insert into tp_alumno_evaluado (cod_alumno,nombres, apellido_pat, apellido_mat, sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values 
('A201500097', 'Jose', 'Carrillo', 'Luna', '1', '15', '1', '3', '2', '2', '2', '2', '5', '11', '2', '2', '1', '5', '1', '1', null,'ALU0001');

--A201500097 | P0003
insert into tp_evaluacion_acoso_escolar (cod_evaluacion,cod_plantilla, fec_resuelto,cod_alumno,cod_perfil,cod_estado) values ('EV20151205203903', 'PTEV0001', '30/03/2016', 'A201500097', null,'EVA0001');
	--Preguntas Respondidas Victima
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0001', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0002', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0003', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0004', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0005', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0001', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0002', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0003', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0004', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0005', 1, false);
	--Preguntas Respondidas Agresor                 
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0006', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0007', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0008', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0009', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0010', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0011', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0012', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0006', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0007', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0008', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0009', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0010', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0011', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0012', 1, false);
	--Preguntas Respondidas Testigo                 
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0013', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0014', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0015', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0013', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0014', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203903', 'PTEV0001', 'PR0015', 0, false);


--Data inicial
select cod_alumno as "Alumno", nombres as "Nombres", concat(apellido_pat||' '||apellido_mat) as "Apellidos", b.nom_perfil as "Perfil", c.nom_estado as "Estado"
from tp_alumno_evaluado a 
inner join tp_estado c on a.cod_estado = c.cod_estado
left join tp_perfil b on a.cod_perfil=b.cod_perfil
where a.cod_alumno in ('A201500097');

select cod_evaluacion as "Evaluacion", cod_solicitud as "Solicitud", a.cod_alumno as "Alumno", 
a.fec_resuelto as "Fecha Resuelto", a.fec_evaluacion as "Fecha Evaluacion",  b.nom_perfil as "Perfil", c.nom_estado as "Estado"
from tp_evaluacion_acoso_escolar a 
inner join tp_estado c on a.cod_estado = c.cod_estado
left join tp_perfil b on a.cod_perfil=b.cod_perfil
where a.cod_evaluacion in ('EV20151205203903');

--Condiciones de Entrada
select c.enunciado as "Pregunta", d.alternativa as "Respuesta", b.cod_regla as "Regla"
from tp_pregunta_evaluacion a 
inner join tp_pregunta c on a.cod_pregunta=c.cod_pregunta
inner join tp_alternativa d on a.secuencia = d.secuencia 
left join tp_pregunta_evaluacion_regla b on a.cod_evaluacion = b.cod_evaluacion and a.cod_plantilla=b.cod_plantilla and a.cod_pregunta=b.cod_pregunta and a.secuencia=b.secuencia
where a.cod_evaluacion in ('EV20151205203903') and seleccionado
order by a.cod_pregunta;



--Verificacion de resultados

--Estado de evaluacion
select cod_evaluacion as "Evaluacion", cod_solicitud as "Solicitud", a.cod_alumno as "Alumno", 
a.fec_resuelto as "Fecha Resuelto", a.fec_evaluacion as "Fecha Evaluacion",  b.nom_perfil as "Perfil", c.nom_estado as "Estado"
from tp_evaluacion_acoso_escolar a 
inner join tp_estado c on a.cod_estado = c.cod_estado
left join tp_perfil b on a.cod_perfil=b.cod_perfil
where a.cod_evaluacion in ('EV20151205203903');

--Estado del Detalle de evaluacion
select c.enunciado as "Pregunta", d.alternativa as "Respuesta", b.cod_regla as "Regla"
from tp_pregunta_evaluacion a 
inner join tp_pregunta c on a.cod_pregunta=c.cod_pregunta
inner join tp_alternativa d on a.secuencia = d.secuencia 
left join tp_pregunta_evaluacion_regla b on a.cod_evaluacion = b.cod_evaluacion and a.cod_plantilla=b.cod_plantilla and a.cod_pregunta=b.cod_pregunta and a.secuencia=b.secuencia
where a.cod_evaluacion in ('EV20151205203903') and seleccionado
order by a.cod_pregunta;

--Estado del alumno
select cod_alumno as "Alumno", nombres as "Nombres", concat(apellido_pat||' '||apellido_mat) as "Apellidos", b.nom_perfil as "Perfil", c.nom_estado as "Estado"
from tp_alumno_evaluado a 
inner join tp_estado c on a.cod_estado = c.cod_estado
left join tp_perfil b on a.cod_perfil=b.cod_perfil
where a.cod_alumno in ('A201500097');

