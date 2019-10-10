package cn.smbms.service;

import cn.smbms.dao.Roledao;
import cn.smbms.pojo.Role;
import cn.smbms.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Rolebo {
    @Autowired
    private Roledao dao;
    private SqlSession sff;

    public Rolebo(){
        sff= SqlSessionUtil.getSqlSession();
        dao = sff.getMapper(dao.getClass());
    }
    //增加
    public String save(Role r){
        dao.save(r);
        return "添加成功";
    }
    //删除
    public String delect(int id){
        dao.fiaddelete(id);
        return "删除成功";
    }
    //修改
    public String sett(Role r){
        dao.fiadset(r);
        return "修改成功";
    }
    //查
    public String fiad(int id){
        dao.fiad(id);
        return "查询成功";
    }
    //查所有
    public String fiadlist(){
        dao.fiadlist();
        return "查询成功";
    }
}
