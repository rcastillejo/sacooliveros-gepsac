create or replace
PROCEDURE INSERT_XML_APP (
  IS_DESCRIPCION IN CLOB,
  IS_ID_EMPRESA IN NUMBER,
  IS_USUARIO      IN VARCHAR2,
  IS_PATH         IN VARCHAR2,
  OS_COD_RETORNO  OUT VARCHAR2,
  OS_MEN_RETORNO  OUT VARCHAR2
) AS 
  XML           XMLType;
  CONT          NUMBER;
  ID_APP        NUMBER;
  ID_PERMISO    NUMBER;
  ID_ROL        NUMBER;
BEGIN
    XML := XMLType(IS_DESCRIPCION);
    
    FOR AP IN (SELECT ExtractValue(column_value, '/APLICACION/@mnemonico') AS MNEMONICO, 
                     ExtractValue(column_value,'/APLICACION/@nombre') AS NOMBRE,
                     Extract(column_value,'/APLICACION/PERMISO') AS PERMISOS,
                     Extract(column_value,'/APLICACION/ROL') AS ROLS                                          
              FROM TABLE(XMLSequence(Extract(XML, IS_PATH))) p) LOOP
      
      IF (AP.MNEMONICO IS NOT NULL) AND (AP.NOMBRE IS NOT NULL) THEN  
      
        SELECT COUNT(1) INTO CONT FROM TP_APLICACION WHERE MNEMONICO = AP.MNEMONICO AND ID_EMPRESA = IS_ID_EMPRESA;
        
        IF CONT = 0 THEN 
        
          ID_APP := SEQ_APLICACION.NEXTVAL;        
          --INGRESO DE APLICACION
          INSERT INTO TP_APLICACION VALUES (IS_ID_EMPRESA, ID_APP, AP.MNEMONICO, AP.NOMBRE, 'HABILITADO', '', '0', SYSDATE, NULL, IS_USUARIO, NULL);
          --INGRESO DE POLITICAS
          INSERT INTO TS_ATRIBUTO_APLICACION SELECT AE.ID_POLITICA,AE.ID_ATRIBUTO, AP.MNEMONICO, AE.VALOR, IS_ID_EMPRESA  FROM TS_ATRIBUTO_EMPRESA AE 
                                             WHERE AE.EMPRESA = (SELECT MNEMONICO FROM TP_EMPRESA EMP WHERE EMP.ID_EMPRESA = IS_ID_EMPRESA);
         
                                              
          FOR P IN (SELECT ExtractValue(column_value, '/PERMISO/@mnemonico') AS MNEMONICO, 
                           ExtractValue(column_value, '/PERMISO/@nombre') AS NOMBRE
                    FROM TABLE(XMLSequence(AP.PERMISOS)) p) LOOP
            ID_PERMISO := SEQ_PERMISO.NEXTVAL;
            --INGRESO DE PERMISO
            INSERT INTO TP_PERMISO VALUES (ID_APP, ID_PERMISO, P.MNEMONICO, P.NOMBRE, 'HABILITADO', 'OPCION', '', SYSDATE, NULL, IS_USUARIO, NULL);
          END LOOP;
          
          FOR R IN (SELECT ExtractValue(column_value,'/ROL/@mnemonico') AS MNEMONICO, 
                           ExtractValue(column_value, '/ROL/@nombre') AS NOMBRE,
                           Extract(column_value, '/ROL/ROLPERMISO') AS ROLPERMISOS
                    FROM TABLE(XMLSequence(AP.ROLS)) p) LOOP
            ID_ROL := SEQ_ROL.NEXTVAL;
            --INGRESO DE ROLES
            INSERT INTO TP_ROL VALUES (ID_APP, ID_ROL, R.MNEMONICO, R.NOMBRE, 'HABILITADO', '', SYSDATE, NULL, IS_USUARIO, NULL);
            
            FOR RP IN (SELECT ExtractValue(column_value, '/ROLPERMISO/@mnemonico') AS MNEMONICO, 
                              ExtractValue(column_value, '/ROLPERMISO/@nombre') AS NOMBRE
                       FROM TABLE(XMLSequence(R.ROLPERMISOS)) p) LOOP
              --INGRESO DE PERMISO POR ROLES
              INSERT INTO TR_PERMISO_X_ROL VALUES (ID_ROL, (SELECT ID_PERMISO FROM TP_PERMISO PE WHERE PE.MNEMONICO = RP.MNEMONICO AND PE.ID_APLICACION = ID_APP));
            END LOOP;
            
          END LOOP;
         ELSE 
           OS_COD_RETORNO := '25';
           OS_MEN_RETORNO := 'La aplicación ya existe';
        END IF;
      END IF;
    END LOOP;
      
END INSERT_XML_APP;