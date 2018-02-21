package com.sacooliveros.gepsac.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

public interface ConfigMapper {

    public String get(@Param("parametro") String parametro); 

}
