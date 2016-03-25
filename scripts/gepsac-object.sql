CREATE TABLE tp_estado
(
  cod_estado character varying(15) NOT NULL NOT NULL,
  nom_estado character varying(100) NOT NULL,
  des_estado character varying(250) NULL,
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

CREATE TABLE tp_config
(
  parametro character varying(50) NOT NULL,  
  valor character varying(50) NOT NULL,  
  descripcion character varying(150),
  CONSTRAINT pk_tp_config PRIMARY KEY (parametro)
)
WITH (
  OIDS=FALSE
);

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

CREATE TABLE tp_contextura
(
  cod_contextura int,
  nom_contextura character varying(50) NOT NULL,
  
  CONSTRAINT pk_tp_contextura PRIMARY KEY (cod_contextura)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_estatura
(
  cod_estatura int,
  nom_estatura character varying(50) NOT NULL,
  
  CONSTRAINT pk_tp_estatura PRIMARY KEY (cod_estatura)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_tipo_familia
(
  cod_familia int,
  nom_familia character varying(50) NOT NULL,
  des_familia character varying(150) NULL,
  
  CONSTRAINT pk_tipo_familia PRIMARY KEY (cod_familia)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_religion
(
  cod_religion int,
  nom_religion character varying(50) NOT NULL,
  
  CONSTRAINT pk_tp_religion PRIMARY KEY (cod_religion)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_nacionalidad
(
  cod_nacionalidad int,
  nom_nacionalidad character varying(50) NOT NULL,
  
  CONSTRAINT pk_tp_nacionalidad PRIMARY KEY (cod_nacionalidad)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_distrito
(
  cod_distrito int,
  nom_distrito character varying(50) NOT NULL,
  
  CONSTRAINT pk_tp_distrito PRIMARY KEY (cod_distrito)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_provincia
(
  cod_provincia int,
  nom_provincia character varying(50) NOT NULL,
  
  CONSTRAINT pk_tp_provincia PRIMARY KEY (cod_provincia)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_departamento
(
  cod_departamento int,
  nom_departamento character varying(50) NOT NULL,
  
  CONSTRAINT pk_tp_departamento PRIMARY KEY (cod_departamento)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_alumno_evaluado
(
  cod_alumno character varying(20) NOT NULL,
  nombres character varying(150) NULL,
  apellido_pat character varying(250) NULL,
  apellido_mat character varying(250) NULL,
  
  sexo int NOT NULL, -- Masculino, Femenino,
  edad int NOT NULL,
  cod_contextura int NULL, -- PEQUEÑO, MEDIANO, GRANDE
  cod_estatura int NOT NULL, --Alto, Medio, Bajo
  cod_familia int NOT NULL, --Nuclear, Extensa, Monopariental, Esamblada, Homoparental
  
  orden_nacimiento int NOT NULL DEFAULT 1, -- 1=Primero, 2=segundo,etc.
  cant_hnos int NOT NULL DEFAULT 0, --Cantidad de hermanos, 0 para hijo Unico
  
  nivel_escolar int NOT NULL, --1=Primaria, 2=Secundaria
  grado_escolar int NOT NULL,
  promedio_escolar numeric(4,2) NOT NULL, --Promedio escolar del momento de la evaluacion al alumno
  
  cant_cambio_colegio int NOT NULL DEFAULT 0, --cantidad de Cambios Colegios
  cod_religion int NOT NULL, --Cristiano, Catolico, Mormon, Evangelico, Judio, etc.
  cod_nacionalidad int NOT NULL, --Peruano, Chileno, Venezolano, etc. 
  cod_distrito int NOT NULL, --Nombre del distrito del alumno
  cod_provincia int NOT NULL, --Nombre del provincia del alumno
  cod_departamento int NOT NULL, --Nombre del departamento del alumno
    
  cod_perfil character varying(15) NULL, 
  cod_estado character varying(15) NOT NULL,   
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT fk_tp_alumno_tp_estado FOREIGN KEY (cod_estado)
      REFERENCES tp_estado (cod_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pk_tp_alumno_evaluado PRIMARY KEY (cod_alumno),
  CONSTRAINT fk_tp_alumno_evaluado_tp_perfil FOREIGN KEY (cod_perfil)
      REFERENCES tp_perfil (cod_perfil) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
	  
  CONSTRAINT fk_tp_alumno_evaluado_contextura FOREIGN KEY (cod_contextura)
      REFERENCES tp_contextura (cod_contextura) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_alumno_evaluado_estatura FOREIGN KEY (cod_estatura)
      REFERENCES tp_estatura (cod_estatura) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_alumno_evaluado_tipo_familia FOREIGN KEY (cod_familia)
      REFERENCES tp_tipo_familia (cod_familia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_alumno_evaluado_religion FOREIGN KEY (cod_religion)
      REFERENCES tp_religion (cod_religion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_alumno_evaluado_nacionalidad FOREIGN KEY (cod_nacionalidad)
      REFERENCES tp_nacionalidad (cod_nacionalidad) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_alumno_evaluado_distrito FOREIGN KEY (cod_distrito)
      REFERENCES tp_distrito (cod_distrito) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_alumno_evaluado_provincia FOREIGN KEY (cod_provincia)
      REFERENCES tp_provincia (cod_provincia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_alumno_evaluado_departamento FOREIGN KEY (cod_departamento)
      REFERENCES tp_departamento (cod_departamento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tp_alumno_postulante
(
  cod_alumno character varying(20) NOT NULL,
  nombres character varying(150) NULL,
  apellido_pat character varying(250) NULL,
  apellido_mat character varying(250) NULL,
  
  sexo int NOT NULL, -- Masculino, Femenino,
  edad int NOT NULL,
  cod_contextura int NULL, -- PEQUEÑO, MEDIANO, GRANDE
  cod_estatura int NOT NULL, --Alto, Medio, Bajo
  cod_familia int NOT NULL, --Nuclear, Extensa, Monopariental, Esamblada, Homoparental
  
  orden_nacimiento int NOT NULL DEFAULT 1, -- 1=Primero, 2=segundo,etc.
  cant_hnos int NOT NULL DEFAULT 0, --Cantidad de hermanos, 0 para hijo Unico
  
  nivel_escolar int NOT NULL, --1=Primaria, 2=Secundaria
  grado_escolar int NOT NULL,
  promedio_escolar numeric(4,2) NOT NULL, --Promedio escolar del momento de la evaluacion al alumno
  
  cant_cambio_colegio int NOT NULL DEFAULT 0, --cantidad de Cambios Colegios
  cod_religion int NOT NULL, --Cristiano, Catolico, Mormon, Evangelico, Judio, etc.
  cod_nacionalidad int NOT NULL, --Peruano, Chileno, Venezolano, etc. 
  cod_distrito int NOT NULL, --Nombre del distrito del alumno
  cod_provincia int NOT NULL, --Nombre del provincia del alumno
  cod_departamento int NOT NULL, --Nombre del departamento del alumno
    
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
      ON UPDATE NO ACTION ON DELETE NO ACTION,
	  
  CONSTRAINT fk_tp_alumno_evaluado_contextura FOREIGN KEY (cod_contextura)
      REFERENCES tp_contextura (cod_contextura) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_alumno_evaluado_estatura FOREIGN KEY (cod_estatura)
      REFERENCES tp_estatura (cod_estatura) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_alumno_evaluado_tipo_familia FOREIGN KEY (cod_familia)
      REFERENCES tp_tipo_familia (cod_familia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_alumno_evaluado_religion FOREIGN KEY (cod_religion)
      REFERENCES tp_religion (cod_religion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_alumno_evaluado_nacionalidad FOREIGN KEY (cod_nacionalidad)
      REFERENCES tp_nacionalidad (cod_nacionalidad) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_alumno_evaluado_distrito FOREIGN KEY (cod_distrito)
      REFERENCES tp_distrito (cod_distrito) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_alumno_evaluado_provincia FOREIGN KEY (cod_provincia)
      REFERENCES tp_provincia (cod_provincia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_alumno_evaluado_departamento FOREIGN KEY (cod_departamento)
      REFERENCES tp_departamento (cod_departamento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


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


CREATE TABLE tp_perfil_evaluacion
(
  cod_evaluacion character varying(20) NOT NULL,
  indice int not null,
  cod_perfil character varying(15) NULL,
  
  probabilidad numeric(10,2) NOT NULL,
  seleccionado boolean NOT NULL,
  
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_perfil_evaluacion PRIMARY KEY (cod_evaluacion, indice),
  CONSTRAINT fk_tp_perfil_evaluacion_tp_perfil FOREIGN KEY (cod_perfil)
      REFERENCES tp_perfil (cod_perfil) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pk_tp_perfil_evaluacion_postulante FOREIGN KEY (cod_evaluacion)
      REFERENCES tp_evaluacion_postulante (cod_evaluacion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_pregunta
(
  cod_pregunta character varying(15) NOT NULL,
  --tipo character varying(25) NOT NULL, 
  --alias character varying(25) NOT NULL,
  enunciado character varying(250) NOT NULL,
  
  --cod_estado character varying(15) NOT NULL, 
  
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_pregunta PRIMARY KEY (cod_pregunta)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_alternativa
(
  secuencia int not null,
  alternativa character varying(50) NOT NULL,
  
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_alternativa PRIMARY KEY (secuencia)
)
WITH (
  OIDS=FALSE
);



CREATE TABLE tp_plantilla_evaluacion_acoso_escolar
(
  cod_plantilla character varying(20) NOT NULL,
  fec_registro timestamp without time zone NULL,
  vigente  boolean NOT NULL,
  
  cod_estado character varying(15) NOT NULL,   
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_plantilla_evaluacion_acoso_escolar PRIMARY KEY (cod_plantilla)
)
WITH (
  OIDS=FALSE
);



CREATE TABLE tp_pregunta_plantilla
(
  cod_plantilla character varying(20) NOT NULL,
  cod_pregunta character varying(15) NOT NULL,
  secuencia int not null,
  
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_pregunta_plantilla PRIMARY KEY (cod_plantilla,cod_pregunta,secuencia),
  CONSTRAINT fk_tp_pregunta_plantilla_tp_plantilla FOREIGN KEY (cod_plantilla)
      REFERENCES tp_plantilla_evaluacion_acoso_escolar (cod_plantilla) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_pregunta_plantilla_tp_pregunta FOREIGN KEY (cod_pregunta)
      REFERENCES tp_pregunta (cod_pregunta) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_pregunta_plantilla_tp_alternativa FOREIGN KEY (secuencia)
      REFERENCES tp_alternativa (secuencia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_usuario
(
  cod_usuario character varying(20) NOT NULL,
  nombres character varying(50) NOT NULL,
  apellidos character varying(150) NOT NULL,
  CONSTRAINT pk_tp_usuario PRIMARY KEY (cod_usuario)
)
WITH (
  OIDS=FALSE
);


-- DROP TABLE tp_solicitud_psicologica;

CREATE TABLE tp_solicitud_psicologica
(
  cod_solicitud character varying(20) NOT NULL,
  fec_solicitud timestamp without time zone NOT NULL,
  fec_atencion timestamp without time zone,
  solicitante character varying(100) NOT NULL,
  descripcion character varying(200) NOT NULL,
  motivo integer NOT NULL,
  lugar character varying(200) NULL,
  cod_estado character varying(15) NOT NULL,
  usu_crea character varying(50),
  fec_crea timestamp without time zone DEFAULT now(),
  usu_modif character varying(50),
  fec_modif timestamp without time zone,
  CONSTRAINT pk_tp_solicitud_psicologica PRIMARY KEY (cod_solicitud),
  CONSTRAINT fk_tp_solicitud_psicologica_tp_usuario FOREIGN KEY (solicitante)
      REFERENCES tp_usuario (cod_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_ev_acoso_escolar_tp_estado FOREIGN KEY (cod_estado)
      REFERENCES tp_estado (cod_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
	  
)
WITH (
  OIDS=FALSE
);

CREATE TABLE tp_solicitud_alumno
(
  cod_solicitud character varying(20) NOT NULL,
  cod_alumno character varying(20) NOT NULL,
  dirigido boolean NOT NULL,
  
  CONSTRAINT pk_tp_solicitud_alumno PRIMARY KEY (cod_solicitud, cod_alumno),
  CONSTRAINT fk_tp_solicitud_alumno_tp_solicitud FOREIGN KEY (cod_solicitud)
      REFERENCES tp_solicitud_psicologica (cod_solicitud) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_solicitud_alumno_tp_alumno FOREIGN KEY (cod_alumno)
      REFERENCES tp_alumno_evaluado (cod_alumno) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tp_evaluacion_acoso_escolar
(
  cod_evaluacion character varying(20) NOT NULL,
  cod_plantilla character varying(20) NOT NULL,
  cod_solicitud character varying(20) NULL,
  fec_resuelto timestamp without time zone NULL,
  fec_evaluacion timestamp without time zone NULL,
  cod_alumno character varying(20) NOT NULL,
  cod_perfil character varying(15) NULL,
  
  cod_estado character varying(15) NOT NULL,   
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_ev_acoso_escolar PRIMARY KEY (cod_evaluacion),
  CONSTRAINT fk_tp_ev_acoso_escolar_tp_plantilla FOREIGN KEY (cod_plantilla)
      REFERENCES tp_plantilla_evaluacion_acoso_escolar (cod_plantilla) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_ev_acoso_escolar_tp_estado FOREIGN KEY (cod_estado)
      REFERENCES tp_estado (cod_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_ev_acoso_escolar_tp_alumno_evaluado FOREIGN KEY (cod_alumno)
      REFERENCES tp_alumno_evaluado (cod_alumno) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_ev_acoso_escolar_tp_perfil FOREIGN KEY (cod_perfil)
      REFERENCES tp_perfil (cod_perfil) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_ev_acoso_escolar_tp_solicitud FOREIGN KEY (cod_solicitud)
      REFERENCES tp_solicitud_psicologica (cod_solicitud) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tp_pregunta_evaluacion
(	
  cod_evaluacion character varying(20) NOT NULL,

  cod_plantilla character varying(20) NOT NULL,
  cod_pregunta character varying(15) NULL,
  secuencia int not null,
 
  seleccionado boolean NOT NULL,
  
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_pregunta_evaluacion PRIMARY KEY (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia),
  CONSTRAINT fk_tp_pregunta_evaluacion_tp_eva_acoso FOREIGN KEY (cod_evaluacion)
      REFERENCES tp_evaluacion_acoso_escolar (cod_evaluacion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_pregunta_evaluacion_tp_pregunta FOREIGN KEY (cod_plantilla, cod_pregunta, secuencia)
      REFERENCES tp_pregunta_plantilla (cod_plantilla, cod_pregunta, secuencia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tp_regla_acoso_escolar
(
  cod_regla character varying(15) NULL,
  
  --tipo character varying(2) NULL, -- Decision (D), Respuesta (R)
  
  cod_perfil character varying(15) NULL, -- Codigo Perfil Acoso Escolar
  deshabilitado boolean not null, 
  
  usu_crea character varying(50), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_regla PRIMARY KEY (cod_regla),
  CONSTRAINT fk_tp_regla_tp_perfil FOREIGN KEY (cod_perfil)
      REFERENCES tp_perfil (cod_perfil) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tp_pregunta_regla
(
  cod_regla character varying(15) NOT NULL,
  cod_pregunta character varying(15) NOT NULL,
  
  CONSTRAINT pk_tp_regla_pregunta PRIMARY KEY (cod_regla, cod_pregunta),
  CONSTRAINT fk_tp_regla_pregunta_tp_pregunta FOREIGN KEY (cod_pregunta)
      REFERENCES tp_pregunta (cod_pregunta) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_regla_pregunta_tp_regla FOREIGN KEY (cod_regla)
      REFERENCES tp_regla_acoso_escolar (cod_regla) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE TABLE tp_pregunta_evaluacion_regla
(
  cod_regla character varying(15) NOT NULL,
  
  cod_evaluacion character varying(20) NOT NULL,
  cod_plantilla character varying(20) NOT NULL,
  cod_pregunta character varying(15) NOT NULL,
  secuencia int not null,
  
  CONSTRAINT pk_tp_regla_pregunta_evaluacion PRIMARY KEY (cod_regla, cod_pregunta, cod_plantilla, secuencia),
  CONSTRAINT fk_tp_regla_pregunta_tp_evaluacion FOREIGN KEY (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia)
      REFERENCES tp_pregunta_evaluacion (cod_evaluacion, cod_plantilla, cod_pregunta, secuencia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tp_regla_pregunta_tp_regla FOREIGN KEY (cod_regla)
      REFERENCES tp_regla_acoso_escolar (cod_regla) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE SEQUENCE SEQ_REGLA START 1;
