CREATE TABLE tp_estado
(
  cod_estado int NOT NULL,
  nom_estado character varying(100) NOT NULL,
  CONSTRAINT pk_tp_estado PRIMARY KEY (cod_estado)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_planestrategia
(
  anio int DEFAULT extract(year from now()),
  cod_plan character varying(11) NOT NULL,
  
  fec_registro timestamp without time zone, 
  fec_apertura timestamp without time zone, 
  fec_plan timestamp without time zone, 
  
  fec_inicio timestamp without time zone, 
  fec_fin timestamp without time zone, 
  
  cod_estado int, 
  
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_planestrategia PRIMARY KEY (cod_plan),
  CONSTRAINT un_tp_planestrategia UNIQUE (anio),
  CONSTRAINT fk_tp_planestrategia_tp_estado FOREIGN KEY (cod_estado)
      REFERENCES tp_estado (cod_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

