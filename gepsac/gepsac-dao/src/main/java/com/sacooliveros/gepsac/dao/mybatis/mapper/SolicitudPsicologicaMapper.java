package com.sacooliveros.gepsac.dao.mybatis.mapper;

import com.sacooliveros.gepsac.model.evaluacion.SolicitudAlumno;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudPsicologica;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SolicitudPsicologicaMapper {

    public List<SolicitudPsicologica> query();

    public List<SolicitudPsicologica> queryEstado(@Param("codigoEstado") String codigoEstado);
    
    public List<SolicitudPsicologica> queryPendiente(@Param("maxMinutosPendiente") int maxMinutosPendiente);

    public SolicitudPsicologica get(@Param("codigo") String codigo);

    public int insert(SolicitudPsicologica model);

    public int insertAlumnos(SolicitudAlumno model);

    public int update(SolicitudPsicologica model);

    public int updateEstado(SolicitudPsicologica model);

    public int delete(SolicitudPsicologica model);
}
