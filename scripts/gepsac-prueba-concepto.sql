
--Eliminando la data de prueba
delete from tp_evaluacion_acoso_escolar where cod_evaluacion in ('EV20151205203901', 'EV20151205203902', 'EV20151205203903', 'EV20151205203904');
delete from tp_alumno_evaluado where cod_alumno in ('A201500099', 'A201500098', 'A201500097', 'A201500096');

--Registrando data de prueba