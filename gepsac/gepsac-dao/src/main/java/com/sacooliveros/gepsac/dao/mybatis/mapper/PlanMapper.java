package com.sacooliveros.gepsac.dao.mybatis.mapper;

import com.sacooliveros.gepsac.model.Plan;
import com.sacooliveros.gepsac.model.PlanActividad;
import com.sacooliveros.gepsac.model.PlanEstrategia;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlanMapper {

    public List<Plan> query();

    public Plan get(@Param("codigo") String codigo);

    public Plan obtenerVigente(@Param("anio") int anio);

    public int update(Plan model);
    
    public int insertEstrategia(PlanEstrategia model);
    
    public int insertActividad(PlanActividad model);
    
    public int deleteEstrategia(String codigoPlan);
    
    public int deleteActividad(String codigoPlan);

    //public int insert(Plan model);

}
