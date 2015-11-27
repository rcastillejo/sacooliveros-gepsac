package com.sacooliveros.gepsac.dao.mybatis.mapper;

import com.sacooliveros.gepsac.model.experto.Alumno;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlumnoMapper {

    public List<Alumno> query();

    public Alumno get(@Param("codigo") String codigo);

    public int insertPostulante(Alumno model);  
    
    public int updatePostulante(Alumno model);  
    
    public Alumno getPostulante(@Param("codigo") String codigo);
}
