package org.forten.mybatis.bo;


import org.forten.mybatis.dao.MybatisDao;
import org.forten.mybatis.dto.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Bo {
    @Autowired
    MybatisDao dao;

    public Games doSelectOne(int gamesid){
        Games games = dao.selectOneGame(gamesid);
        return games;
    }
}
