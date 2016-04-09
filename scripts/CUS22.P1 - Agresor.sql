

--Verificacion de resultados

--Estado de evaluacion
Select cod_evaluacion as "Evaluacion", cod_alumno as "Alumno", fec_evaluacion "Fecha Evaluacion"
from tp_evaluacion_postulante
where fec_evaluacion >= '2016-04-09';

Select nom_perfil as "Perfil", (probabilidad*100) as "Porcentaje", CASE WHEN seleccionado THEN 'TRUE' ELSE 'FALSE' END as "Selecionado"
from tp_perfil_evaluacion a inner join tp_perfil b on a.cod_perfil = b.cod_perfil
where cod_evaluacion = 'EV201604090000016958';

