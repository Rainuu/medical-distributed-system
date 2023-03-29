package com.aaa.system.controller;

import com.aaa.core.entity.RegisteredItem;
import com.aaa.system.dao.RegisteredItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("system/registeredItem")
public class RegisteredItemController {
    @Autowired
    private RegisteredItemDao dao;
    @GetMapping("/getAll")
    public List<RegisteredItem> getAll(){
        return dao.selectList(null);
    }
}
