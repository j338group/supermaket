package com.linchen.bo;

import com.linchen.dto.Provider;

import java.util.List;

public interface ProviderBoI {
    List<Provider> queryProviderList(String queryCode,String queryName);

    List<Provider> queryProviderName();

    Integer addProvider(Provider provider);

    String deleteProvider(Long id);

    Provider queryProviderById(Long id);

    Integer updateProviderById(Provider provider);
}
