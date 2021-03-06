
--Deshabilitando las reglas de acoso
Update tp_regla_acoso_escolar set deshabilitado = true;

--Eliminando la data de prueba
delete from tp_pregunta_evaluacion_regla where cod_evaluacion in ('EV20151205203901');
delete from tp_pregunta_evaluacion where cod_evaluacion in ('EV20151205203901');
delete from tp_evaluacion_acoso_escolar where cod_evaluacion in ('EV20151205203901');
delete from tp_alumno_evaluado where cod_alumno in ('A201500099');

--P0001 Agresor
insert into tp_alumno_evaluado (cod_alumno,nombres, apellido_pat, apellido_mat, sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,cod_perfil,cod_estado) values 
('A201500099', 'Ricardo', 'Castillejo', 'Luna', '1', '13', '2', '3', '3', '3', '3', '2', '4', '11', '2', '1', '1', '1', '1', '1', null,'ALU0001');

--A201500099 | P0001
insert into tp_evaluacion_acoso_escolar (cod_evaluacion, cod_plantilla, fec_resuelto,cod_alumno,cod_perfil,cod_estado) values ('EV20151205203901', 'PTEV0001', '30/03/2016', 'A201500099', null,'EVA0001');
	--Preguntas Respondidas Victima
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0001', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0002', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0003', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0004', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0005', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0001', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0002', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0003', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0004', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0005', 1, false);
	--Preguntas Respondidas Agresor
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0006', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0007', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0008', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0009', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0010', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0011', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0012', 1, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0006', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0007', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0008', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0009', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0010', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0011', 0, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0012', 0, false);
	--Preguntas Respondidas Testigo
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0013', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0014', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0015', 0, true);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0013', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0014', 1, false);
insert into tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia, seleccionado) values ('EV20151205203901', 'PTEV0001', 'PR0015', 1, false);


--Verificacion de resultados

--Estado de evaluacion
select cod_evaluacion as "Evaluacion", cod_solicitud as "Solicitud", a.cod_alumno as "Alumno", 
a.fec_resuelto as "Fecha Resuelto", a.fec_evaluacion as "Fecha Evaluacion",  b.nom_perfil as "Perfil", c.nom_estado as "Estado"
from tp_evaluacion_acoso_escolar a 
inner join tp_estado c on a.cod_estado = c.cod_estado
left join tp_perfil b on a.cod_perfil=b.cod_perfil
where a.cod_evaluacion in ('EV20151205203901');

--Estado del Detalle de evaluacion
select c.enunciado as "Pregunta", d.alternativa as "Respuesta", b.cod_regla as "Regla"
from tp_pregunta_evaluacion a 
inner join tp_pregunta c on a.cod_pregunta=c.cod_pregunta
inner join tp_alternativa d on a.secuencia = d.secuencia 
left join tp_pregunta_evaluacion_regla b on a.cod_evaluacion = b.cod_evaluacion and a.cod_plantilla=b.cod_plantilla and a.cod_pregunta=b.cod_pregunta and a.secuencia=b.secuencia
where a.cod_evaluacion in ('EV20151205203901') and seleccionado
order by a.cod_pregunta;

--Estado del alumno
select cod_alumno as "Alumno", nombres as "Nombres", concat(apellido_pat||' '||apellido_mat) as "Apellidos", b.nom_perfil as "Perfil", c.nom_estado as "Estado"
from tp_alumno_evaluado a 
inner join tp_estado c on a.cod_estado = c.cod_estado
left join tp_perfil b on a.cod_perfil=b.cod_perfil
where a.cod_alumno in ('A201500099');

--Reglas
SELECT
	a.cod_regla,
	a.deshabilitado,
	d.cod_perfil,
	d.nom_perfil
FROM 
	tp_regla_acoso_escolar a
INNER JOIN
	tp_perfil d ON a.cod_perfil = d.cod_perfil
Order by 
	a.cod_regla
--where a.cod_estado = 'EVA0001';

--Habilitando las reglas
Update tp_regla_acoso_escolar set deshabilitado = false;