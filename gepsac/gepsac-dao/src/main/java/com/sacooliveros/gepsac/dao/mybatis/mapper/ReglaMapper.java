package com.sacooliveros.gepsac.dao.mybatis.mapper;

import com.sacooliveros.gepsac.model.comun.Perfil;
import com.sacooliveros.gepsac.model.evaluacion.Pregunta;
import com.sacooliveros.gepsac.model.experto.PreguntaRegla;
import com.sacooliveros.gepsac.model.experto.Regla;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReglaMapper {

    public List<Regla> query();

    public Regla get(@Param("codigo") String codigo);

    public List<PreguntaRegla> getPreguntas(@Param("codigo") String codigo);

    public int insert(Regla model);

    public int insertPregunta(PreguntaRegla model);

    public int update(Regla model);

    //public int updatePregunta(PreguntaRegla model);

    public int delete(Regla model);

    public int deletePregunta(PreguntaRegla model);
    
    public List<Pregunta> queryPregunta();
    
    public List<Perfil> queryPerfil();

}
