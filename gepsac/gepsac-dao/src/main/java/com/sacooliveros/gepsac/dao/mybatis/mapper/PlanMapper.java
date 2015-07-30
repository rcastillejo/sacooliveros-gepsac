package com.sacooliveros.gepsac.dao.mybatis.mapper;

import com.sacooliveros.gepsac.model.Plan;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PlanMapper {

    public List<Plan> query();

    public Plan get(@Param("codigo") String codigo);

    public Plan obtenerVigente(@Param("anio") int anio);

    @Update("Update tp_plan set "
            + "fec_registro = #{fecRegistro}, fec_programacion = #{fecProgramacion} "
            + "fec_inicio = #{fecInicio}, fec_fin = #{fecFin}, cod_estado = #{estado} "
            + "tituo = #{titulo} where anio = #{anio} and cod_plan = #{codigo}")
    public int update(Plan model);

    @Update("Update tp_plan set "
            + "fec_registro = #{fecRegistro}, fec_programacion = #{fecProgramacion} "
            + "fec_inicio = #{fecInicio}, fec_fin = #{fecFin}, cod_estado = #{estado} "
            + "tituo = #{titulo} where anio = #{anio} and cod_plan = #{codigo}")
    public int insert(Plan model);

}
