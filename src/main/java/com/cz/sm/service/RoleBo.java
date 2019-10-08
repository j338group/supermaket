package com.cz.sm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cz.sm.dao.RoleMapper;
import com.cz.sm.pojo.Role;

import java.util.List;

@Service
public class RoleBo {
    @Autowired
    private RoleMapper roleMapper;
    public List<Role> queryAllRole(){
        List<Role> roleList = roleMapper.findAll();
        return roleList;
    }
}
