package com.sacooliveros.gepsac.dao.mybatis.mapper;

import com.sacooliveros.gepsac.model.experto.Alumno;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlumnoMapper {

    public List<Alumno> query();

    public Alumno get(@Param("codigo") String codigo);

    public int insertPostulante(Alumno model);  
    
    public int updatePostulante(Alumno model);  
    

    public int insertEvaluado(Alumno model);  
    
    public int updateEvaluado(Alumno model);  
    
    public Alumno getPostulante(@Param("codigo") String codigo);
    public Alumno getEvaluado(@Param("codigo") String codigo);
    
    public Alumno cargarCodificacionAlumno(Alumno model);
    
    public int updateEstadoEvaluado(Alumno model);  
}
