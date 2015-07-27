ALTER TABLE TP_AUDITORIA DROP COLUMN VALORES_ANTIGUOS;
ALTER TABLE TP_AUDITORIA DROP COLUMN VALORES_NUEVOS;

ALTER TABLE TP_AUDITORIA ADD VALORES_ANTIGUOS VARCHAR2(4000);
ALTER TABLE TP_AUDITORIA ADD VALORES_NUEVOS VARCHAR2(4000);

ALTER TABLE TS_ATRIBUTO_APLICACION ADD ID_EMPRESA NUMBER(19);

ALTER TABLE TS_ATRIBUTO_APLICACION ADD CONSTRAINT TS_EMPRESA_PK
FOREIGN KEY (ID_EMPRESA) REFERENCES TP_EMPRESA (ID_EMPRESA));

ALTER TABLE TP_TEMPLATE MODIFY ( DESCRIPCION varchar2(4000) );

ALTER TABLE TP_PERMISO drop CONSTRAINT TP_PERMISO_MNEMONICO;
ALTER TABLE TP_PERMISO add CONSTRAINT TP_PERMISO_MNEMONICO UNIQUE ("MNEMONICO", "ID_APLICACION");

ALTER TABLE TP_ROL drop CONSTRAINT TP_ROL_MNEMONICO;
ALTER TABLE TP_ROL add CONSTRAINT TP_ROL_MNEMONICO UNIQUE ("MNEMONICO", "ID_APLICACION");

ALTER TABLE TP_APLICACION drop CONSTRAINT TP_APLICACION_MNEMONICO;
ALTER TABLE TP_APLICACION add CONSTRAINT TP_APLICACION_MNEMONICO UNIQUE ("MNEMONICO", "ID_APLICACION");

UPDATE TP_APLICACION SET ESTADO='HABILITADO';
UPDATE TP_EMPRESA SET ESTADO='HABILITADO';

UPDATE TP_USUARIO SET CONTRASENA = '123456';

ALTER TABLE TP_TEMPLATE ADD ID_EMPRESA NUMBER(19);

ALTER TABLE TP_TEMPLATE drop CONSTRAINT TP_TEMPLATE_MNEMONICO;
ALTER TABLE TP_TEMPLATE add CONSTRAINT TP_TEMPLATE_MNEMONICO UNIQUE ("MNEMONICO", "ID_EMPRESA");
