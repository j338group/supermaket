package com.cz.sm.dao;

import com.cz.sm.pojo.Providers;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {
    List<Providers> findAllPro(@Param("queryProCode") String queryProCode, @Param("queryProName") String queryProName);
    Providers findById(Long proId);
    int updateById(Providers provider);
    Boolean delById(Long proId);
    int saveByPro(Providers pro);
}
