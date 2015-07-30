package com.sacooliveros.gepsac.dao.mybatis.mapper;
 
import com.sacooliveros.gepsac.model.Estrategia;
import com.sacooliveros.gepsac.model.EstrategiaActividad;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EstrategiaMapper {

  
  public List<Estrategia> query();
  
  public List<EstrategiaActividad> queryActividad(@Param("codigoEstrategia") String codigoEstrategia);

  
  @Select("SELECT * FROM tp_estrategia where cod_estrategia = #{codigoEstrategia")
  public Estrategia get(@Param("codigoEstrategia") String codigoEstrategia);
   


}
