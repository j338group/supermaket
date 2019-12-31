package org.forten.mybatis.dao;

import org.forten.mybatis.dto.Games;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MybatisDao {
    Games selectOneGame(int gamesid);
}
