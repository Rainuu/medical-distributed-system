package com.aaa.system.controller;


import com.aaa.core.entity.Menu;
import com.aaa.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("system/menu")
public class MenuController {
    @Autowired
    private MenuService service;
    @GetMapping("/findByUserid/{userId}")
    public List<Menu> findByUserid(@PathVariable Long userId){
        System.out.println("---------------"+userId);
        return service.findByUserid(userId);
    }
}
