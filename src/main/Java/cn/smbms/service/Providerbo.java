package cn.smbms.service;

import cn.smbms.dao.Providerdao;
import cn.smbms.pojo.Provider;
import cn.smbms.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Providerbo {


    @Autowired
    private Providerdao dao;

    private SqlSession sff;

    public Providerbo(){
    sff=SqlSessionUtil.getSqlSession();
    dao = sff.getMapper(dao.getClass());
    }
    //增加
    public String sava(Provider p){
        dao.save(p);
        return "加入成功";
    }
    //删除
    public String delect(int id){
      int a =  dao.finddelete(id);
        return "成功删除"+a+"条数据";
    }
    //修改
    public String sett(Provider p){
        int a = dao.findset(p);
        return "成功修改"+a+"条数据";
    }
    //查
    public String find(int id){
        dao.find(id);
        return "查找成功";
    }
    //查询所有
    public String finglist(Provider p){
        dao.findlist();
        return "查找成功";
    }
}
