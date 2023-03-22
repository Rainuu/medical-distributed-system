package com.aaa.system.controller.api;

import com.aaa.core.entity.Menu;
import com.aaa.core.vo.Result;
import com.aaa.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("system/api/menu")
public class ApiMenuController {
    @Autowired
    private MenuService Sercvice;
    @GetMapping("/leftMenu")
    public Result<List<Menu>> leftMenu(){

        return Sercvice.findLefMenu();
    }

}
