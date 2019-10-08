package com.linchen.bo;

import com.linchen.dao.RoleMapper;
import com.linchen.dto.Role;
import com.linchen.dto.RoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleBo implements RoleBoI {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryRoleList() {
        return roleMapper.selectByExample(new RoleExample());
    }
}
