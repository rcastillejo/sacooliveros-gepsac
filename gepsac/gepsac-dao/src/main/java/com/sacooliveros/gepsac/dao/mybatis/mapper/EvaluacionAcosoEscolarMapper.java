package com.sacooliveros.gepsac.dao.mybatis.mapper;

import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacion;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EvaluacionAcosoEscolarMapper {

    public List<EvaluacionAcosoEscolar> query();

    public EvaluacionAcosoEscolar get(@Param("codigo") String codigo);

    public int insert(EvaluacionAcosoEscolar model);  
    
    public int insertPreguntas(PreguntaEvaluacion model);  
    
    public int update(EvaluacionAcosoEscolar model);  

    public int updatePreguntas(PreguntaEvaluacion model);      
    
    public List<EvaluacionAcosoEscolar> queryEstado(@Param("codigoEstado") String codigoEstado);
}
