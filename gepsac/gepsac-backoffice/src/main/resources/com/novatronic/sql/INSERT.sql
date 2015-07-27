----------------------------------------------------------------------------------------------------------------------------------------
--   DATA FOR TABLE TP_EMPRESA
----------------------------------------------------------------------------------------------------------------------------------------
Insert into TP_EMPRESA (ID_EMPRESA,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE) values (-1,to_timestamp('02/02/12 10:11:40,000000000 PM','DD/MM/RR HH12:MI:SS,FF AM'),null,'novatronic\system','novatronic\system',null,'HABILITADO','novatronic','Novatronic');
----------------------------------------------------------------------------------------------------------------------------------------
--   TABLE TP_APLICACION
----------------------------------------------------------------------------------------------------------------------------------------
Insert into TP_APLICACION (ID_APLICACION,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_EMPRESA, POLITICA_ACTIVA) values (1,null,null,null,null,null,'HABILITADO','seguridad','Sistema de Control de Accesos',1, 0);
----------------------------------------------------------------------------------------------------------------------------------------
--   DATA FOR TABLE TP_ROL
----------------------------------------------------------------------------------------------------------------------------------------
Insert into TP_ROL (ID_ROL,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION) values (1,to_timestamp('11/12/12 04:36:10,766000000 PM','DD/MM/RR HH12:MI:SS,FF AM'),to_timestamp('15/12/12 07:33:32,449000000 PM','DD/MM/RR HH12:MI:SS,FF AM'),'system/novatronic','system/novatronic',null,'HABILITADO','administrador','administrador',1);
----------------------------------------------------------------------------------------------------------------------------------------
--   DATA FOR TABLE TP_USUARIO
----------------------------------------------------------------------------------------------------------------------------------------
Insert into TP_USUARIO (ID_USUARIO,APELLIDOMATERNO,APELLIDOPATERNO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,CONTRASENA,CORREO, ESTADO,FECHA_CAMBIO_CLAVE,NOMBRE,NUMERODOCUMENTO,TELEFONO,TIPODOCUMENTO,USUARIO,ID_EMPRESA,FECHA_LOGIN,MAC) values (1,'Sistemas','Novatronic',to_timestamp('02/02/12 10:11:40,000000000 PM','DD/MM/RR HH12:MI:SS,FF AM'), to_timestamp('26/12/13 02:47:39,635000000 PM','DD/MM/RR HH12:MI:SS,FF AM'),'novatronic\system','system/novatronic', 'oDeai5G2/OH5rZS86MNAyGc8nIWwXOFiq73dbIAgg+4=', 'sistemas@novatronic.com,com','HABILITADO',to_timestamp('15/12/13 11:54:25,994000000 PM','DD/MM/RR HH12:MI:SS,FF AM'),'Super Administrador','123456789','4353453','DNI','admin',1,to_timestamp('26/12/12 02:47:39,619000000 PM','DD/MM/RR HH12:MI:SS,FF AM'),'1234');
----------------------------------------------------------------------------------------------------------------------------------------
--   DATA FOR TABLE TR_ROL_X_USUARIO
----------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO "SCAPCIDE"."TR_ROL_X_USUARIO" (ID_USUARIO, ID_ROL) VALUES ('1', '1')

----------------------------------------------------------------------------------------------------------------------------------------
--   TABLE TP_PERMISO
----------------------------------------------------------------------------------------------------------------------------------------
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (1,null,null,null,null,null,'HABILITADO','menu.aplicacion','Menu Aplicacion',1,'MENU');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (2,null,null,null,null,null,'HABILITADO','menu.usuario','Menu Usuario',1,'MENU');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (3,null,null,null,null,null,'HABILITADO','export.empresa','Export opcion empresa',1,'OPCION');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (4,null,null,null,null,null,'HABILITADO','boton.nuevaempresa','Boton nueva empresa',1,'BOTON');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (5,null,null,null,null,null,'HABILITADO','submenu.exportar','Submenu Exportar',1,'SUBMENU');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (6,null,null,null,null,null,'HABILITADO','menu.template','Menu Template',1,'MENU');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (7,null,null,null,null,null,'HABILITADO','import.empresa','Importar opcion empresa',1,'OPCION');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (8,null,null,null,null,null,'HABILITADO','boton.eliminarempresa','Boton Eliminar empresa',1,'BOTON');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (9,null,null,null,null,null,'HABILITADO','submenu.importar','Submenu Importar',1,'SUBMENU');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (10,null,null,null,null,null,'HABILITADO','import.aplicacion','Import opcion aplicacion',1,'OPCION');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (11,null,null,null,null,null,'HABILITADO','export.aplicacion','Exportar opcion aplicacion',1,'OPCION');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (12,null,null,null,null,null,'HABILITADO','menu.horario','Menu Horario',1,'MENU');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (13,null,null,null,null,null,'HABILITADO','menu.empresa','Menu Empresa',1,'MENU');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (14,null,null,null,null,null,'HABILITADO','menu.permiso','Menu Permiso',1,'MENU');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (15,null,null,null,null,null,'HABILITADO','menu.politica','Menu Politicas',1,'MENU');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (16,null,null,null,null,null,'HABILITADO','menu.reporte','Menu Reporte',1,'MENU');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (17,null,null,null,null,null,'HABILITADO','menu.rol','Menu Rol',1,'MENU');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (18,null,null,null,null,null,'HABILITADO','menu.bloqueo','Menu Bloqueo',1,'MENU');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (19,null,null,null,null,null,'HABILITADO','menu.desbloqueo','Menu Desbloqueo',1,'MENU');
Insert into TP_PERMISO (ID_PERMISO,AUD_FEC_CREAC,AUD_FEC_MODIF,AUD_USU_CREAC,AUD_USU_MODIF,DESCRIPCION,ESTADO,MNEMONICO,NOMBRE,ID_APLICACION,TIPO) values (20,null,null,null,null,null,'HABILITADO','submenu.gestionar','Submenu Gestionar Template',1,'SUBMENU');

----------------------------------------------------------------------------------------------------------------------------------------
--   TABLE TP_POLITICA
----------------------------------------------------------------------------------------------------------------------------------------
Insert into TP_POLITICA (ID_POLITICA,EVENTO,CODIGO) values ('unLockedAutomatic','login','1000');
Insert into TP_POLITICA (ID_POLITICA,EVENTO,CODIGO) values ('userLocked','login','1005');
Insert into TP_POLITICA (ID_POLITICA,EVENTO,CODIGO) values ('maxFailedLoginAttempts','login','1004');
Insert into TP_POLITICA (ID_POLITICA,EVENTO,CODIGO) values ('passwordExpired','login','1006');
Insert into TP_POLITICA (ID_POLITICA,EVENTO,CODIGO) values ('passwordChangeFirst','login','1001');
Insert into TP_POLITICA (ID_POLITICA,EVENTO,CODIGO) values ('passwordExpiredNotified','login','1002');
Insert into TP_POLITICA (ID_POLITICA,EVENTO,CODIGO) values ('passwordLength','create-change','1008');
Insert into TP_POLITICA (ID_POLITICA,EVENTO,CODIGO) values ('passwordStrength','create-change','1007');
Insert into TP_POLITICA (ID_POLITICA,EVENTO,CODIGO) values ('oldPassword','create-change','1009');
Insert into TP_POLITICA (ID_POLITICA,EVENTO,CODIGO) values ('usuarioLength','user-create-change','1010');
Insert into TP_POLITICA (ID_POLITICA,EVENTO,CODIGO) values ('maxTimeOut','read','1011');

----------------------------------------------------------------------------------------------------------------------------------------
--   TABLE TP_ATRIBUTO
----------------------------------------------------------------------------------------------------------------------------------------
Insert into TP_ATRIBUTO (ID_ATRIBUTO,ID_POLITICA) values ('HOUR','unLockedAutomatic');
Insert into TP_ATRIBUTO (ID_ATRIBUTO,ID_POLITICA) values ('MINUTE','unLockedAutomatic');
Insert into TP_ATRIBUTO (ID_ATRIBUTO,ID_POLITICA) values ('MAX_FAILED_ATTEMPTS','maxFailedLoginAttempts');
Insert into TP_ATRIBUTO (ID_ATRIBUTO,ID_POLITICA) values ('MIN_LENGTH','passwordLength');
Insert into TP_ATRIBUTO (ID_ATRIBUTO,ID_POLITICA) values ('MAX_LENGTH','passwordLength');
Insert into TP_ATRIBUTO (ID_ATRIBUTO,ID_POLITICA) values ('YEARS','passwordExpired');
Insert into TP_ATRIBUTO (ID_ATRIBUTO,ID_POLITICA) values ('MONTHS','passwordExpired');
Insert into TP_ATRIBUTO (ID_ATRIBUTO,ID_POLITICA) values ('DAYS','passwordExpired');
Insert into TP_ATRIBUTO (ID_ATRIBUTO,ID_POLITICA) values ('CRITERION','passwordStrength');
Insert into TP_ATRIBUTO (ID_ATRIBUTO,ID_POLITICA) values ('BACKTRACE','oldPassword');
Insert into TP_ATRIBUTO (ID_ATRIBUTO,ID_POLITICA) values ('DAY_NOTIFIED','passwordExpiredNotified');
Insert into TP_ATRIBUTO (ID_ATRIBUTO,ID_POLITICA) values ('MAX_MINUTE','maxTimeOut');
Insert into TP_ATRIBUTO (ID_ATRIBUTO,ID_POLITICA) values ('MAX_USER_LENGTH','usuarioLength');
Insert into TP_ATRIBUTO (ID_ATRIBUTO,ID_POLITICA) values ('MIN_USER_LENGTH','usuarioLength');

----------------------------------------------------------------------------------------------------------------------------------------
--   TABLE TS_ATRIBUTO_EMPRESA
----------------------------------------------------------------------------------------------------------------------------------------
Insert into TS_ATRIBUTO_EMPRESA (VALOR,ID_POLITICA,MNEMONICO_EMPRESA,ID_ATRIBUTO) values ('0','unLockedAutomatic','novatronic','HOUR');
Insert into TS_ATRIBUTO_EMPRESA (VALOR,ID_POLITICA,MNEMONICO_EMPRESA,ID_ATRIBUTO) values ('2','unLockedAutomatic','novatronic','MINUTE');
Insert into TS_ATRIBUTO_EMPRESA (VALOR,ID_POLITICA,MNEMONICO_EMPRESA,ID_ATRIBUTO) values ('3','passwordLength','novatronic','MIN_LENGTH');
Insert into TS_ATRIBUTO_EMPRESA (VALOR,ID_POLITICA,MNEMONICO_EMPRESA,ID_ATRIBUTO) values ('12','passwordLength','novatronic','MAX_LENGTH');
Insert into TS_ATRIBUTO_EMPRESA (VALOR,ID_POLITICA,MNEMONICO_EMPRESA,ID_ATRIBUTO) values ('3','maxFailedLoginAttempts','novatronic','MAX_FAILED_ATTEMPTS');
Insert into TS_ATRIBUTO_EMPRESA (VALOR,ID_POLITICA,MNEMONICO_EMPRESA,ID_ATRIBUTO) values ('3','passwordExpired','novatronic','YEARS');
Insert into TS_ATRIBUTO_EMPRESA (VALOR,ID_POLITICA,MNEMONICO_EMPRESA,ID_ATRIBUTO) values ('10','passwordExpired','novatronic','MONTHS');
Insert into TS_ATRIBUTO_EMPRESA (VALOR,ID_POLITICA,MNEMONICO_EMPRESA,ID_ATRIBUTO) values ('0','passwordExpired','novatronic','DAYS');
Insert into TS_ATRIBUTO_EMPRESA (VALOR,ID_POLITICA,MNEMONICO_EMPRESA,ID_ATRIBUTO) values ('123','passwordStrength','novatronic','CRITERION');
Insert into TS_ATRIBUTO_EMPRESA (VALOR,ID_POLITICA,MNEMONICO_EMPRESA,ID_ATRIBUTO) values ('3','oldPassword','novatronic','BACKTRACE');
Insert into TS_ATRIBUTO_EMPRESA (VALOR,ID_POLITICA,MNEMONICO_EMPRESA,ID_ATRIBUTO) values ('10','passwordExpiredNotified','novatronic','DAY_NOTIFIED');
Insert into TS_ATRIBUTO_EMPRESA (VALOR,ID_POLITICA,MNEMONICO_EMPRESA,ID_ATRIBUTO) values ('10','timeOut','novatronic','MAX_MINUTE');
Insert into TS_ATRIBUTO_EMPRESA (VALOR,ID_POLITICA,MNEMONICO_EMPRESA,ID_ATRIBUTO) values ('12','usuarioLength','novatronic','MAX_USER_LENGTH');
Insert into TS_ATRIBUTO_EMPRESA (VALOR,ID_POLITICA,MNEMONICO_EMPRESA,ID_ATRIBUTO) values ('6','usuarioLength','novatronic','MIN_USER_LENGTH');

----------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------
--   TABLE TR_PERMISO_X_ROL
----------------------------------------------------------------------------------------------------------------------------------------
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,1);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,2);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,3);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,4);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,5);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,6);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,7);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,8);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,9);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,10);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,11);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,12);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,13);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,14);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,15);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,16);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,17);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,18);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,19);
Insert into TR_PERMISO_X_ROL (ID_ROL,ID_PERMISO) values (1,20);
