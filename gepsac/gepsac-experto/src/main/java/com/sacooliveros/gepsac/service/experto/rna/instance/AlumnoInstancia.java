/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.rna.instance;

import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.PerfilEvaluado;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.rna.clasificador.ClassifierType;
import com.sacooliveros.gepsac.service.experto.rna.clasificador.Classifiers;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

/**
 *
 * @author Ricardo
 */
public class AlumnoInstancia implements Instancia<Alumno, PerfilEvaluado> {

    private static final Logger log = LoggerFactory.getLogger(AlumnoInstancia.class);

    private static final String SQL_TRAIN = "Select cod_alumno,sexo,edad,cod_contextura,cod_estatura,cod_familia,orden_nacimiento,cant_hnos,nivel_escolar,grado_escolar,promedio_escolar,cant_cambio_colegio,cod_religion,cod_nacionalidad,cod_distrito,cod_provincia,cod_departamento,"
            + "CASE WHEN cod_perfil is null THEN '' ELSE cod_perfil END from tp_alumno_evaluado "
            + "where cod_estado='ALU0002' and cod_alumno between 'A201500101' and 'A201500137'";
    private static final String RELATION_NAME = "alumno2";
    private static final String URL_DATABASE = "jdbc:postgresql://localhost:5432/gepsac2";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private final InstanceQuery query;

    public AlumnoInstancia() throws Exception {
        query = new InstanceQuery();
        query.setDatabaseURL(URL_DATABASE);
        query.setUsername(USERNAME);
        query.setPassword(PASSWORD);
    }

    @Override
    public Instances getTrainInstances() {
        Instances instanciasEntrenadas = getInstances(SQL_TRAIN);
        int numClasses = instanciasEntrenadas.numClasses();
        for (int i = 0; i < numClasses; i++) {
            log.debug("Clasificacion obtenida [" + i + "={}]", instanciasEntrenadas.classAttribute().value(i));
        }
        return instanciasEntrenadas;
    }

    @Override
    public Instances getPredicInstances(Alumno alumno, List<Attribute> attrs, Attribute attrClass) {
        Instances datapredict;
        FastVector fvWekaAttributes;

        fvWekaAttributes = createAttributes(attrs, attrClass);

        datapredict = new Instances(RELATION_NAME, fvWekaAttributes, 0);
        Instance ins = new Instance(fvWekaAttributes.size());

        int idx = 0;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getSexo().getCodigo());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getEdad());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getContextura().getCodigo());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getEstatura().getCodigo());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getTipoFamilia().getCodigo());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getOrdenNacimiento());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getCantHnos());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getNivelEscolar().getCodigo());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getGradoEscolar());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getPromedioEscolar());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getCantCambioColegio());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getReligion().getCodigo());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getNacionalidad().getCodigo());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getDistrito().getCodigo());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getProvincia().getCodigo());
        idx++;
        ins.setValue((Attribute) fvWekaAttributes.elementAt(idx), alumno.getDepartamento().getCodigo());

        datapredict.add(ins);

        datapredict.setRelationName(RELATION_NAME);

        if (datapredict.classIndex() == -1) {
            datapredict.setClassIndex(datapredict.numAttributes() - 1);
        }
        log.debug("Instancia obtenida [{}]", datapredict);
        return datapredict;
    }

    private FastVector createAttributes(List<Attribute> attrs, Attribute attrClass) {
        FastVector fvWekaAttributes = new FastVector();
        for (Attribute attr : attrs) {
            log.debug("Atributo [{}]", attr);
            fvWekaAttributes.addElement(attr);
        }
        fvWekaAttributes.addElement(attrClass);
        return fvWekaAttributes;
    }

    private Instances getInstances(String sql) {
        try {
            log.debug("Obteniendo instancias sql[{}]", sql);
            query.setQuery(sql);
            Instances data = query.retrieveInstances();
            prepareInstances(data);
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void prepareInstances(Instances instances) {
        instances.setRelationName(RELATION_NAME);

        if (instances.classIndex() == -1) {
            instances.setClassIndex(instances.numAttributes() - 1);
        }
        instances.deleteAttributeAt(0);
    }

    @Override
    public List<PerfilEvaluado> predict(Classifier clasificador, Instances dataEntrenado, Instances dataEvaluar) {
        try {
            return obtenerPredicciones(clasificador, dataEntrenado, dataEvaluar);
        } catch (Exception e) {
            throw new ExpertoServiceException("Error al predecir alumno", e);
        }
    }

    private List<PerfilEvaluado> obtenerPredicciones(Classifier clasificador, Instances alumnosEvaluados, Instances alumnoEvaluar) throws Exception {
        List<PerfilEvaluado> predicciones;

        predicciones = new ArrayList<PerfilEvaluado>();

        //Obtener Prediccion
        for (int i = 0; i < alumnoEvaluar.numInstances(); i++) {
            Instance newInst = alumnoEvaluar.instance(i);
            double[] probabilidad = clasificador.distributionForInstance(newInst);
            double prediccion = clasificador.classifyInstance(newInst);
            int prediccionPerfil = (int) prediccion;

            log.trace("Prediccion [{}], probabilidad [{}]", new Object[]{prediccion, probabilidad});

            predicciones = obtenerProbabilidadPorPerfil(probabilidad, prediccionPerfil, alumnosEvaluados);

            break;
        }

        return predicciones;
    }

    private List<PerfilEvaluado> obtenerProbabilidadPorPerfil(double[] probabilidadPerfil, int predicionPerfil, Instances alumnosEvaluados) {
        List<PerfilEvaluado> predicciones;

        predicciones = new ArrayList<PerfilEvaluado>();

        for (int i = 0; i < probabilidadPerfil.length; i++) {
            PerfilEvaluado perfil = new PerfilEvaluado();
            double prediccion = probabilidadPerfil[i];
            String codigoPerfil = alumnosEvaluados.classAttribute().value(i);
            perfil.setIndice(i);
            perfil.setPerfil(codigoPerfil);
            perfil.setProbabilidad((Math.round(prediccion * 10000.0) / 10000.0));//Redondeado 2 decimales
            if (i == predicionPerfil) {
                perfil.seleccionar();
            }
            log.trace("Perfil evaluado [{}]", perfil);
            predicciones.add(perfil);
        }

        return predicciones;

    }

    @Override
    public Classifier train(Instances dataEntrenar) {
        try {
            Classifier clasificador = Classifiers.create(ClassifierType.MLP);
            clasificador.buildClassifier(dataEntrenar);
            return clasificador;
        } catch (Exception e) {
            throw new ExpertoServiceException("Error al predecir alumno", e);
        }
    }

    @Override
    public void destroy() {
        if (query != null) {
            try {
                log.debug("Revisando estado de instancia antes de cerrar [connected={}]", 
                        new Object[]{query.isConnected()});
                query.close();
                query.disconnectFromDatabase();
                log.debug("Revisando estado de instancia luego de cerrar [connected={}]", 
                        new Object[]{query.isConnected()});
            } catch (Exception e) {
                log.warn("No se pudo cerrar la instancia del query", e);
            }
        }
    }

}
