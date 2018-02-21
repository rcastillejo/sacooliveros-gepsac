
--Deshabilitando los perfiles de acoso
Update tp_perfil set deshabilitado = true;


--Reglas
SELECT
	cod_perfil,
	nom_perfil,
	deshabilitado
FROM 
	tp_perfil a

--Habilitando las reglas
Update tp_perfil set deshabilitado = false;