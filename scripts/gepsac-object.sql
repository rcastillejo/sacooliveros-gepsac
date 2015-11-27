CREATE TABLE tp_estado
(
  cod_estado character varying(15) NOT NULL NOT NULL,
  nom_estado character varying(100) NOT NULL,
  CONSTRAINT pk_tp_estado PRIMARY KEY (cod_estado)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_plan
(
  cod_plan character varying(15) NOT NULL,
  anio int NOT NULL,
  
  fec_registro timestamp without time zone, 
  fec_configuracion timestamp without time zone, 
  fec_programacion timestamp without time zone,
  
  fec_inicio timestamp without time zone, 
  fec_fin timestamp without time zone, 
  
  cod_estado character varying(15) NOT NULL, 
  
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_plan PRIMARY KEY (cod_plan),
  CONSTRAINT fk_tp_plan_tp_estado FOREIGN KEY (cod_estado)
      REFERENCES tp_estado (cod_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tp_plan_hito
(
  cod_plan character varying(15) NOT NULL,  
  fec_hito date, 

  cod_estado character varying(15) NOT NULL, 
  
  CONSTRAINT pk_tp_plan_hito PRIMARY KEY (cod_plan,fec_hito),
  CONSTRAINT fk_tp_plan_hito_tp_plan FOREIGN KEY (cod_plan)
      REFERENCES tp_plan (cod_plan) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tp_plan_fecha_restriccion
(
  cod_plan character varying(15) NOT NULL,  
  fec_ini_rest date NOT NULL,  -- Se utiliza para una fecha o rango
  fec_fin_rest date NOT NULL, -- Se utiliza para el rango
  tip_rest character varying(2) NOT NULL,  -- U: Una Fecha, R: Rango de fechas
  des_rest character varying(150) NOT NULL,  
  
  CONSTRAINT pk_tp_plan_fecha_restriccion PRIMARY KEY (cod_plan, fec_ini_rest, fec_fin_rest),
  CONSTRAINT fk_tp_plan_fecha_restriccion_1 FOREIGN KEY (cod_plan)
      REFERENCES tp_plan (cod_plan) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_indicador
(
  cod_indicador character varying(15) NOT NULL,  
  nom_indicador character varying(250) NOT NULL,
  fec_registro timestamp without time zone, 
  formula character varying(250) NOT NULL,
  
  cod_estado character varying(15) NOT NULL, 
  
  CONSTRAINT pk_tp_indicador PRIMARY KEY (cod_indicador),
  CONSTRAINT fk_tp_indicador_tp_estado FOREIGN KEY (cod_estado)
      REFERENCES tp_estado (cod_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_actividad
(
  cod_actividad character varying(15) NOT NULL,
  nom_actividad character varying(250) NOT NULL,
  des_actividad character varying(250) NOT NULL,
  fec_registro timestamp without time zone, 
  duracion int,
  sesiones int,
  tipo_frecuencia character(2) NOT NULL,
  frecuencia int,
  
  cod_estado character varying(15) NOT NULL, 
  
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_actividad PRIMARY KEY (cod_actividad),
  CONSTRAINT fk_tp_actividad_tp_estado FOREIGN KEY (cod_estado)
      REFERENCES tp_estado (cod_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_estrategia
(
  cod_estrategia character varying(15) NOT NULL,  
  titulo_estrategia character varying(250) NOT NULL,
  des_estrategia character varying(250) NOT NULL,
  fec_registro timestamp without time zone, 

  cod_estado character varying(15) NOT NULL, 
  
  CONSTRAINT pk_tp_estrategia PRIMARY KEY (cod_estrategia),
  CONSTRAINT fk_tp_actividad_tp_estado FOREIGN KEY (cod_estado)
      REFERENCES tp_estado (cod_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tp_estrategia_actividad
(
  cod_estrategia character varying(15) NOT NULL,
  cod_actividad character varying(15) NOT NULL,
  
  cod_estado character varying(15) NOT NULL, 
  
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_est_act PRIMARY KEY (cod_estrategia, cod_actividad),
  CONSTRAINT fk_tp_est_act_tp_est FOREIGN KEY (cod_estrategia)
      REFERENCES tp_estrategia (cod_estrategia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_est_act_tp_act FOREIGN KEY (cod_actividad)
      REFERENCES tp_actividad (cod_actividad) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_est_act_tp_estado FOREIGN KEY (cod_estado)
      REFERENCES tp_estado (cod_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tp_estrategia_indicador
(
  cod_estrategia character varying(15) NOT NULL,
  cod_actividad character varying(15) NOT NULL,
  cod_indicador character varying(15) NOT NULL,
  
  cod_estado character varying(15) NOT NULL, 
  
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_est_act_ind PRIMARY KEY (cod_estrategia, cod_actividad, cod_indicador),
  CONSTRAINT fk_tp_est_act_ind_tp_est_act FOREIGN KEY (cod_estrategia, cod_actividad)
      REFERENCES tp_estrategia_actividad (cod_estrategia, cod_actividad) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_est_act_ind_tp_estado FOREIGN KEY (cod_estado)
      REFERENCES tp_estado (cod_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tp_plan_estrategia
(
  cod_plan character varying(15) NOT NULL,
  cod_estrategia character varying(15) NOT NULL,
  
  CONSTRAINT pk_tp_plan_estrategia PRIMARY KEY (cod_plan, cod_estrategia),
  CONSTRAINT fk_tp_plan_estrategia_tp_plan FOREIGN KEY (cod_plan)
      REFERENCES tp_plan (cod_plan) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_plan_estrategia_tp_estrategia FOREIGN KEY (cod_estrategia)
      REFERENCES tp_estrategia (cod_estrategia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_plan_actividad
(
  cod_plan character varying(15) NOT NULL,
  cod_estrategia character varying(15) NOT NULL,
  cod_actividad character varying(15) NOT NULL,
  
  fec_programacion timestamp without time zone, 
  fec_ejecucion timestamp without time zone,
  
  cod_estado character varying(15) NOT NULL, 
  
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_plan_actividad PRIMARY KEY (cod_plan, cod_estrategia, cod_actividad),
  CONSTRAINT fk_tp_plan_actividad_1 FOREIGN KEY (cod_plan, cod_estrategia)
      REFERENCES tp_plan_estrategia (cod_plan, cod_estrategia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_plan_actividad_2 FOREIGN KEY (cod_estrategia, cod_actividad)
      REFERENCES tp_estrategia_actividad (cod_estrategia, cod_actividad) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tp_plan_indicador
(
  cod_plan character varying(15) NOT NULL,
  cod_estrategia character varying(15) NOT NULL,
  cod_actividad character varying(15) NOT NULL,
  cod_indicador character varying(15) NOT NULL,
  
  meta numeric(10,2),
  
  cod_estado character varying(15) NOT NULL, 
  
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_plan_indicador PRIMARY KEY (cod_plan, cod_estrategia, cod_actividad, cod_indicador),
  CONSTRAINT fk_tp_plan_indicador_1 FOREIGN KEY (cod_plan, cod_estrategia, cod_actividad)
      REFERENCES tp_plan_actividad (cod_plan, cod_estrategia, cod_actividad) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_plan_indicador_2 FOREIGN KEY (cod_estrategia, cod_actividad, cod_indicador)
      REFERENCES tp_estrategia_indicador (cod_estrategia, cod_actividad, cod_indicador) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


--Experto

CREATE TABLE tp_perfil
(
  cod_perfil character varying(15) NOT NULL,
  nom_perfil character varying(25) NOT NULL,
  des_perfil character varying(250) NOT NULL,
  
  --cod_estado character varying(15) NOT NULL, 
  
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_perfil PRIMARY KEY (cod_perfil)/*,
  CONSTRAINT fk_tp_perfil_tp_estado FOREIGN KEY (cod_estado)
      REFERENCES tp_estado (cod_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION*/
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tp_alumno_evaluado
(
  cod_alumno character varying(15) NOT NULL,
  nommbres character varying(150) NULL,
  apellido_pat character varying(250) NULL,
  apellido_mat character varying(250) NULL,
  
  genero character varying(15) NOT NULL, -- Masculino, Femenino,
  edad int NOT NULL,
  contextura character varying(10) NULL, -- PEQUEÑO, MEDIANO, GRANDE
  altura character varying(10) NOT NULL, --Alto, Medio, Bajo
  tipo_familia character varying(50) NOT NULL, --Nuclear, Extensa, Monopariental, Esamblada, Homoparental
  
  orden_nacimiento int NOT NULL DEFAULT 1, -- 1=Primero, 2=segundo,etc.
  num_hnos int NOT NULL DEFAULT 0, --Numero de hermanos, 0 para hijo Unico
  
  nivel_escolar character varying(15) NOT NULL, --Primaria, Secundaria
  grado_escolar int NOT NULL,
  promedio_escolar numeric(4,2) NOT NULL, --Promedio escolar del momento de la evaluacion al alumno
  
  nro_cambio_colegio int NOT NULL DEFAULT 0, --nro. Cambios Colegios
  religion character varying(50) NOT NULL, --Cristiano, Catolico, Mormon, Evangelico, Judio, etc.
  nacionalidad character varying(50) NOT NULL, --Peruano, Chileno, Venezolano, etc. 
  distrito character varying(50) NOT NULL, --Nombre del distrito del alumno
  provincia character varying(50) NOT NULL, --Nombre del provincia del alumno
  departamento character varying(50) NOT NULL, --Nombre del departamento del alumno
    
  cod_perfil character varying(15) NULL, 
  --cod_estado character varying(15) NOT NULL,   
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  /*CONSTRAINT fk_tp_alumno_tp_estado FOREIGN KEY (cod_estado)
      REFERENCES tp_estado (cod_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,*/
  CONSTRAINT pk_tp_alumno_evaluado PRIMARY KEY (cod_alumno),
  CONSTRAINT fk_tp_alumno_evaluado_tp_perfil FOREIGN KEY (cod_perfil)
      REFERENCES tp_perfil (cod_perfil) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tp_alumno_postulante
(
  cod_alumno character varying(15) NOT NULL,
  nommbres character varying(150) NULL,
  apellido_pat character varying(250) NULL,
  apellido_mat character varying(250) NULL,
  
  genero character varying(15) NOT NULL, -- Masculino, Femenino,
  edad int NOT NULL,
  contextura character varying(10) NULL, -- PEQUEÑO, MEDIANO, GRANDE
  altura character varying(10) NOT NULL, --Alto, Medio, Bajo
  tipo_familia character varying(50) NOT NULL, --Nuclear, Extensa, Monopariental, Esamblada, Homoparental
  
  orden_nacimiento int NOT NULL DEFAULT 1, -- 1=Primero, 2=segundo,etc.
  num_hnos int NOT NULL DEFAULT 0, --Numero de hermanos, 0 para hijo Unico
  
  nivel_escolar character varying(15) NOT NULL, --Primaria, Secundaria
  grado_escolar int NOT NULL,
  promedio_escolar numeric(4,2) NOT NULL, --Promedio escolar del momento de la evaluacion al alumno
  
  nro_cambio_colegio int NOT NULL DEFAULT 0, --nro. Cambios Colegios
  religion character varying(50) NOT NULL, --Cristiano, Catolico, Mormon, Evangelico, Judio, etc.
  nacionalidad character varying(50) NOT NULL, --Peruano, Chileno, Venezolano, etc. 
  distrito character varying(50) NOT NULL, --Nombre del distrito del alumno
  provincia character varying(50) NOT NULL, --Nombre del provincia del alumno
  departamento character varying(50) NOT NULL, --Nombre del departamento del alumno
    
  cod_perfil character varying(15) NULL, 
  --cod_estado character varying(15) NOT NULL,   
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_alumno_postulante_evaluado PRIMARY KEY (cod_alumno),
  /*CONSTRAINT fk_tp_alumno_tp_estado FOREIGN KEY (cod_estado)
      REFERENCES tp_estado (cod_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,*/
  CONSTRAINT fk_tp_alumno_postulante_tp_perfil FOREIGN KEY (cod_perfil)
      REFERENCES tp_perfil (cod_perfil) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
