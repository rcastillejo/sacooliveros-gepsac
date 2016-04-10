
--Eliminando la data de prueba
/*delete from tp_pregunta_evaluacion_regla where cod_evaluacion in ('EV20151205203905','EV20151205203906');
delete from tp_pregunta_evaluacion where cod_evaluacion in ('EV20151205203905','EV20151205203906');
delete from tp_evaluacion_acoso_escolar where cod_evaluacion in ('EV20151205203905','EV20151205203906');
delete from tp_solicitud_alumno where cod_solicitud in ('SP20160330123');
delete from tp_solicitud_psicologica where cod_solicitud in ('SP20160330123');
delete from tp_alumno_evaluado where cod_alumno in ('A201500095','A201500094');*/



--Data inicial
select cod_evaluacion as "Evaluacion", cod_solicitud as "Solicitud", a.cod_alumno as "Alumno", 
a.fec_resuelto as "Fecha Resuelto", a.fec_evaluacion as "Fecha Evaluacion",  b.nom_perfil as "Perfil", c.nom_estado as "Estado"
from tp_evaluacion_acoso_escolar a 
inner join tp_estado c on a.cod_estado = c.cod_estado
left join tp_perfil b on a.cod_perfil=b.cod_perfil
--where a.cod_estado = 'EVA0001';

