package cn.smbms.service;

import cn.smbms.dao.RoleMapper;
import cn.smbms.pojo.Role;
import cn.smbms.pojo.RoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServicelmpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    //注解的意思是告诉你这个方法是从父类或者接口中继承过来的
    @Override
    public List<Role> queryRoleList(){
        RoleExample example =new RoleExample();
        return roleMapper.selectByExample(example);
    }
}
