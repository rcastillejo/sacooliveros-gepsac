create or replace
PROCEDURE INSERT_XML_EMP (
  IS_DESCRIPCION IN CLOB,
  IS_USUARIO     IN VARCHAR2,
  OS_COD_RETORNO  OUT VARCHAR2,
  OS_MEN_RETORNO  OUT VARCHAR2
) AS 
  XML XMLType;
  EMPRESA NUMBER;
  ID_USUARIO NUMBER;
  CONT       NUMBER;
  
BEGIN
    XML := XMLType(IS_DESCRIPCION);
    
    FOR R IN (SELECT ExtractValue(column_value, '/EMPRESA/@mnemonico') AS MNEMONICO, 
             ExtractValue(column_value, '/EMPRESA/@nombre') AS NOMBRE,
             Extract(column_value,'/EMPRESA/USUARIO') AS USUARIOS
             FROM TABLE(XMLSequence(XML)) p) LOOP
      IF (R.MNEMONICO IS NOT NULL) AND (R.NOMBRE IS NOT NULL) THEN  
        
        EMPRESA := SEQ_EMPRESA.NEXTVAL;
        
        SELECT COUNT(1) INTO CONT FROM TP_EMPRESA WHERE MNEMONICO = R.MNEMONICO;
        
        IF CONT = 0 THEN 
        
          INSERT INTO TP_EMPRESA VALUES (EMPRESA, R.MNEMONICO, R.NOMBRE, 'HABILITADO', '', SYSDATE, NULL, IS_USUARIO, NULL);
          --INGRESO DE POLITICAS
          INSERT INTO TS_ATRIBUTO_EMPRESA SELECT AE.ID_POLITICA,AE.ID_ATRIBUTO, R.MNEMONICO, AE.VALOR 
                                          FROM TS_ATRIBUTO_EMPRESA AE WHERE AE.EMPRESA = 'novatronic' ;
          
          INSERT_XML_APP(IS_DESCRIPCION, EMPRESA, IS_USUARIO, '/EMPRESA/APLICACION', OS_COD_RETORNO, OS_MEN_RETORNO);
          
          FOR U IN (SELECT ExtractValue(column_value, '/USUARIO/@usuario') AS USUARIO, 
                           ExtractValue(column_value, '/USUARIO/@nombre') AS NOMBRE,
                           Extract(column_value, '/USUARIO/USUARIOROL') AS USUARIOROLES
                    FROM TABLE(XMLSequence(R.USUARIOS)) p) LOOP
                    
            ID_USUARIO := SEQ_USUARIO.NEXTVAL;
            --INGRESO DE USUARIO
            INSERT INTO TP_USUARIO(ID_EMPRESA, ID_USUARIO, USUARIO,NOMBRE,  CONTRASENA,
                                   ESTADO, FECHA_CAMBIO_CLAVE, FECHA_LOGIN, MAC, AUD_FEC_CREAC, AUD_USU_CREAC ) 
            VALUES (EMPRESA, ID_USUARIO, U.USUARIO, U.NOMBRE, '12345','HABILITADO', SYSDATE, SYSDATE, '@@@@', SYSDATE, IS_USUARIO);
            
            FOR RP IN (SELECT ExtractValue(column_value, '/USUARIOROL/@mnemonico') AS MNEMONICO, 
                                ExtractValue(column_value, '/USUARIOROL/@nombre') AS NOMBRE
                       FROM TABLE(XMLSequence(U.USUARIOROLES)) p) LOOP
              --INGRESO DE ROL POR USUARIO
              INSERT INTO TR_ROL_X_USUARIO VALUES (ID_USUARIO, (SELECT ID_ROL FROM TP_ROL R 
                                                    INNER JOIN TP_APLICACION AP ON R.ID_APLICACION = AP.ID_APLICACION
                                                    INNER JOIN TP_EMPRESA EM ON AP.ID_EMPRESA = EM.ID_EMPRESA 
                                                    WHERE R.MNEMONICO = RP.MNEMONICO AND EM.ID_EMPRESA = EMPRESA));
            
            END LOOP;
            
          END LOOP;
        
        ELSE 
           OS_COD_RETORNO := '15';
           OS_MEN_RETORNO := 'La empresa ya existe';
        END IF;
      END IF;
    END LOOP;
    
END INSERT_XML_EMP;