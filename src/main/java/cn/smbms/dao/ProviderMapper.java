package cn.smbms.dao;

import cn.smbms.pojo.Provider;
import cn.smbms.pojo.ProviderExample;
import org.apache.ibatis.annotations.Param;
import sun.security.jca.Providers;

import java.util.List;

public interface ProviderMapper {
    long countByExample(ProviderExample example);

    int deleteByExample(ProviderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Provider record);

    int insertSelective(Provider record);

    List<Provider> selectByExample(ProviderExample example);

    Provider selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Provider record, @Param("example") ProviderExample example);

    int updateByExample(@Param("record") Provider record, @Param("example") ProviderExample example);

    int updateByPrimaryKeySelective(Provider record);

    int updateByPrimaryKey(Provider record);

    List<Provider> findAllPro(String queryProCode, String queryProName);

    Providers findById(Long proId);

    int updateById(Providers provider);

    int saveByPro(Providers pro);

    Boolean delById(Long proId);
}