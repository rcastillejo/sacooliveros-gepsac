
--Eliminando la tabla

drop table tp_evaluacion_postulante;

--Restableciendo la tabla

CREATE TABLE tp_evaluacion_postulante
(
  cod_evaluacion character varying(20) NOT NULL,
  fec_evaluacion timestamp without time zone NOT NULL,
  cod_alumno character varying(20) NOT NULL,
  
  cod_estado character varying(15) NOT NULL,   
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_evaluacion_postulante PRIMARY KEY (cod_evaluacion),
  CONSTRAINT fk_tp_evaluacion_postulante_tp_estado FOREIGN KEY (cod_estado)
      REFERENCES tp_estado (cod_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_evaluacion_postulante_tp_alumno_postulante FOREIGN KEY (cod_alumno)
      REFERENCES tp_alumno_postulante (cod_alumno) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
