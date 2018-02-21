package com.sacooliveros.gepsac.dao.mybatis.mapper;

import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.model.experto.PerfilEvaluado;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EvaluacionPostulanteMapper {

    public String getCodigo();
    
    public List<EvaluacionPostulante> query();

    public EvaluacionPostulante get(@Param("codigo") String codigo);

    public int insert(EvaluacionPostulante model);  
    
    public int insertPerfiles(PerfilEvaluado model);  
    
    public int update(EvaluacionPostulante model);  

    public int updatePerfiles(PerfilEvaluado model);      
    
    //public EvaluacionPostulante getPostulante(@Param("codigo") String codigo);
}
