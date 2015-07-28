SET search_path = bcamovil, public;
-- Table: tp_pinreset

-- DROP TABLE tp_pinreset;

CREATE TABLE tp_pinreset
(
  cod_proc character varying(11) NOT NULL,
  cod_adqui character varying(11) NOT NULL,
  cod_autoriza character varying(11) NOT NULL,
  num_celular character varying(11) NOT NULL, -- Numero de Celular
  fec_activacion timestamp without time zone, -- Fecha de Activacion
  ind_estado bigint DEFAULT 1, -- Indicador de estado 1 -> Activo 0 ->Inactivo
  usu_crea character varying(50) DEFAULT sys_context('USERENV'::character varying, 'TERMINAL'::character varying), -- Usuario de creacion
  fec_crea timestamp without time zone DEFAULT now(), -- Fecha de creacion
  usu_modif character varying(50), -- Usuario de Modificacion
  fec_modif timestamp without time zone, -- Fecha de modifcacion
  CONSTRAINT pk_tp_pinreset PRIMARY KEY (num_celular),
  CONSTRAINT fk_tp_pinreset_tp_proc_adq_aut FOREIGN KEY (cod_proc, cod_adqui, cod_autoriza)
      REFERENCES tp_proc_adq_autoriza (cod_proc, cod_adqui, cod_autoriza) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tp_pinreset
  OWNER TO bcamovil;
COMMENT ON COLUMN tp_pinreset.num_celular IS 'Numero de Celular';
COMMENT ON COLUMN tp_pinreset.fec_activacion IS 'Fecha de Activacion';
COMMENT ON COLUMN tp_pinreset.ind_estado IS 'Indicador de estado 1 -> Activo 0 ->Inactivo';
COMMENT ON COLUMN tp_pinreset.usu_crea IS 'Usuario de creacion';
COMMENT ON COLUMN tp_pinreset.fec_crea IS 'Fecha de creacion';
COMMENT ON COLUMN tp_pinreset.usu_modif IS 'Usuario de Modificacion';
COMMENT ON COLUMN tp_pinreset.fec_modif IS 'Fecha de modifcacion';


-- Oracle package 'PKG_PROCESO_PINRESET' declaration, please edit to match PostgreSQL syntax.
-- PostgreSQL does not recognize PACKAGES, using SCHEMA instead.
DROP SCHEMA IF EXISTS pkg_proceso_pinreset CASCADE;
CREATE SCHEMA pkg_proceso_pinreset;


CREATE OR REPLACE FUNCTION pkg_proceso_pinreset.spi_activar_pinreset(IN p_cod_proc text, IN p_cod_adqui text, IN p_cod_autoriza text, IN p_num_celular text, IN p_fec_activacion text, OUT p_result text, OUT p_mensaje text)
  RETURNS record AS
$BODY$
DECLARE

    
  v_fec_activacion   timestamp;
    
  
BEGIN
    p_result := '00';
    v_fec_activacion := TO_TIMESTAMP(p_fec_activacion,'MMDDYYYY HH24:MI:SS');
    UPDATE TP_PINRESET
    SET ind_estado = 1,
        fec_activacion =  v_fec_activacion,
        usu_modif = bcamovil.SYS_CONTEXT ('USERENV', 'TERMINAL'),
        fec_modif = now()
      WHERE
          COD_PROC     = p_COD_PROC     AND
          COD_ADQUI    = p_COD_ADQUI    AND
          COD_AUTORIZA = p_COD_AUTORIZA AND
          NUM_CELULAR  = p_NUM_CELULAR;

    IF NOT FOUND THEN
       INSERT INTO TP_PINRESET
         (
          COD_PROC,
          COD_ADQUI,
          COD_AUTORIZA,
          NUM_CELULAR
          ,FEC_ACTIVACION
         )
       VALUES
         (
          p_COD_PROC,
          p_COD_ADQUI,
          p_COD_AUTORIZA,
          p_NUM_CELULAR
          ,v_FEC_ACTIVACION
         );
       p_mensaje := 'Celular '|| p_num_celular || ' ACTIVADO';
    ELSE
       p_mensaje := 'El Celular '|| p_num_celular || ' Activado y Actualizado ';
--       COMMIT;
    END IF;
  EXCEPTION
  WHEN OTHERS THEN
     p_result := '01';
     p_mensaje := 'ERROR BD: '||substring(sqlerrm from 1 for 200);
--     ROLLBACK;
  END;

$body$
LANGUAGE PLPGSQL;




CREATE OR REPLACE FUNCTION pkg_proceso_pinreset.spu_validar_pinreset(IN p_cod_proc text, IN p_cod_adqui text, IN p_cod_autoriza text, IN p_num_celular text, IN p_fec_consulta text, IN p_num_min bigint, OUT p_result text, OUT p_mensaje text)
  RETURNS record AS
$BODY$
DECLARE

   
  v_sw              integer:=0;
  v_ind_estado      integer := 0;
  v_no_existe_cel   integer := 1;
  v_num_celular     varchar(11);
  v_fec_activacion  timestamp;
  v_fec_consulta    timestamp without time zone;
  v_fec_consultav   varchar(20);
  v_result          integer;
  
  
BEGIN
    p_result := '00';
    -- Verfica datos celular ---
--    RAISE NOTICE 'paso 1';
    BEGIN
    
      SELECT NUM_CELULAR, FEC_ACTIVACION, IND_ESTADO
      INTO STRICT   v_num_celular, v_fec_activacion, v_ind_estado
      FROM TP_PINRESET
      WHERE
          COD_PROC     = p_COD_PROC     AND
          COD_ADQUI    = p_COD_ADQUI    AND
          COD_AUTORIZA = p_COD_AUTORIZA AND
          NUM_CELULAR  = p_NUM_CELULAR;
       EXCEPTION 
      WHEN NO_DATA_FOUND THEN
        v_no_existe_cel := 0;
    END;
    v_fec_consultav := p_fec_consulta;
--    RAISE NOTICE 'paso 2 %',p_fec_consulta;
--    RAISE NOTICE 'paso 2a %',v_fec_consultav;
    v_fec_consulta := TO_TIMESTAMP(v_fec_consultav,'MMDDYYYY HH24MISS');

--    RAISE NOTICE 'paso 3 %',v_fec_consulta;
    v_fec_consultav := TO_CHAR(v_fec_consulta,'MMDDYYYY HH24MISS'); 
--    RAISE NOTICE 'paso 4';

    v_result := extract('epoch' from age(v_fec_consulta, v_fec_activacion))::integer/60;
    RAISE NOTICE 'paso Result %', v_result ;


    CASE 
      WHEN v_no_existe_cel = 0 THEN
           BEGIN
             p_result := '01';
             p_mensaje := 'Celular '|| p_num_celular || ' No Existe';
           END;
      WHEN v_ind_estado = 0 THEN
           BEGIN
             p_result := '01';
             p_mensaje := 'El Celular '|| p_num_celular || ' con estado Inactivo ';
           END;
           
      WHEN v_fec_consulta < v_fec_activacion or v_result < 0 THEN
           BEGIN
             p_result := '01';
             p_mensaje := 'El Celular '|| p_num_celular || ' Tiene F.Consulta ' || v_fec_consultav ||' menor que F.Activacion '|| TO_CHAR(v_fec_activacion,'MMDDYYYY HH24:MI:SS');
           END;
      
--      WHEN ROUND((v_fec_consulta - v_fec_activacion) * 24 *60,0)  > p_num_min THEN
      WHEN v_result > p_num_min THEN
           BEGIN
  --          RAISE NOTICE 'paso 6';

             p_result := '01';
             p_mensaje := 'El Celular '|| p_num_celular || ' supera la cantidad de minutos ' || CAST(p_num_min as varchar) || ' F.Cons: '|| TO_CHAR(v_fec_consulta,'MMDDYYYY HH24:MI:SS')||' F.Acti: '|| TO_CHAR(v_fec_activacion,'MMDDYYYY HH24:MI:SS');
           END;
      ELSE 
           BEGIN
             p_result := '00';
             p_mensaje := 'Celular '|| p_num_celular || ' Verificacion OK, MIN :' ||CAST(v_result as varchar) || ' F.Cons: '|| TO_CHAR(v_fec_consulta,'MMDDYYYY HH24:MI:SS')||' F.Acti: '|| TO_CHAR(v_fec_activacion,'MMDDYYYY HH24:MI:SS');           END;
    END CASE;

  EXCEPTION
  WHEN OTHERS THEN
     p_result := '99';
     p_mensaje := 'ERROR BD: '||substring(sqlerrm from 1 for 200);
  END;

$body$
LANGUAGE PLPGSQL;




CREATE OR REPLACE FUNCTION pkg_proceso_pinreset.spu_desactivar_pinreset(IN p_cod_proc text, IN p_cod_adqui text, IN p_cod_autoriza text, IN p_num_celular text, OUT p_result text, OUT p_mensaje text)
  RETURNS record AS
$BODY$
BEGIN
    p_result := '00';
    UPDATE TP_PINRESET
    SET ind_estado = 0,
         usu_modif = bcamovil.SYS_CONTEXT ('USERENV', 'TERMINAL'),
         fec_modif = now()
    WHERE
          COD_PROC     = p_COD_PROC     AND
          COD_ADQUI    = p_COD_ADQUI    AND
          COD_AUTORIZA = p_COD_AUTORIZA AND
          NUM_CELULAR  = p_NUM_CELULAR;

    IF NOT FOUND THEN
       p_result := '01';
       p_mensaje := 'El Celular '|| p_num_celular || ' no existe ';
    ELSE
       p_mensaje := 'El Celular '|| p_num_celular || ' DESACTIVADO ';
--       COMMIT;
    END IF;
  EXCEPTION
  WHEN OTHERS THEN
     p_result := '99';
     p_mensaje := 'ERROR BD: '||substring(sqlerrm from 1 for 200);
--     ROLLBACK;
  END;

$body$
LANGUAGE PLPGSQL;
-- End of Oracle package 'PKG_PROCESO_PINRESET' declaration

