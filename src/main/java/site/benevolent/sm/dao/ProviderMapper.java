package site.benevolent.sm.dao;

import org.apache.ibatis.annotations.Param;
import site.benevolent.sm.pojo.Providers;

import java.util.List;

public interface ProviderMapper {
    List<Providers> findAllPro(@Param("queryProCode") String queryProCode,@Param("queryProName") String queryProName);
    Providers findById(Long proId);
    int updateById(Providers provider);
    Boolean delById(Long proId);
    int saveByPro(Providers pro);
}
