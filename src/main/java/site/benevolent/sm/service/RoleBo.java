package site.benevolent.sm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.benevolent.sm.dao.RoleMapper;
import site.benevolent.sm.pojo.Role;

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
