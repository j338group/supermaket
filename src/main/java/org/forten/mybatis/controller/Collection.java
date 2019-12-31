package org.forten.mybatis.controller;

import org.forten.mybatis.bo.Bo;
import org.forten.mybatis.dto.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.EmptyStackException;

@RestController
@RequestMapping("hello")
public class Collection {
    @Autowired
    Bo bo;

    @GetMapping("{i:\\d+}")
    public Games actionFindOne(@PathVariable int i){
        Games games = bo.doSelectOne(i);
        return games;
    }
}
