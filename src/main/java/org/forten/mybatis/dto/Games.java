package org.forten.mybatis.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Games {

    private int     gamesid;
    private String  gameName;
    private double  gameMoney;
    private double  gameDiscound;
    private int     gameRole;
    private Date    createTime;
    private String  gameMessage;
    private int     goodAppraise;
    private int     badAppraise;


}
