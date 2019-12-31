package org.forten.mybatis.vo;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.forten.mybatis.dto.Games;

import java.util.List;

public interface fenye {
    List<Games> selectfen(Pagination page,Integer state);
}
