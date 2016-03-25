package com.sacooliveros.gepsac.dao.mybatis.mapper;

import com.sacooliveros.gepsac.model.comun.Estado;
import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacion;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacionAlternativa;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EvaluacionAcosoEscolarMapper {

    public List<EvaluacionAcosoEscolar> query();

    public EvaluacionAcosoEscolar get(@Param("codigo") String codigo);
    
    public EvaluacionAcosoEscolar getEvaluacionAcosoEscolar(Estado estado);
    
    public EvaluacionAcosoEscolar getRespuestaEvaluacion(@Param("codigo") String codigo);

    public int insert(EvaluacionAcosoEscolar model);  
    
    public int insertPreguntas(PreguntaEvaluacion model);  
    
    public int update(EvaluacionAcosoEscolar model);  
    
    public int updateRespEvalAcosoEscolar(EvaluacionAcosoEscolar model);  

    public int updatePreguntas(PreguntaEvaluacion model);      
    
    /**
     * Resolver Evaluacion Acoso Escolar
     */
    public int updateResueltoEvalAcosoEscolar(EvaluacionAcosoEscolar model);

    /**
     * Resolver Evaluacion Acoso Escolar
     */
    public int updateRespuestaPreguntas(PreguntaEvaluacionAlternativa model);
    
    public List<EvaluacionAcosoEscolar> queryEstado(@Param("codigoEstado") String codigoEstado);
    
    public List<EvaluacionAcosoEscolar> queryEvaluadoResuelto();
    
    public List<PreguntaEvaluacion> queryPregunta(@Param("codigoEvaluacion") String codigoEvaluacion);
    
    public List<PreguntaEvaluacion> queryPreguntaAlternativa(@Param("codigoEvaluacion") String codigoEvaluacion,
            @Param("codigoPregunta") String codigoPregunta);
    
    public List<PreguntaEvaluacion> queryPreguntaAfirmativa(@Param("codigoEvaluacion") String codigoEvaluacion);
}
