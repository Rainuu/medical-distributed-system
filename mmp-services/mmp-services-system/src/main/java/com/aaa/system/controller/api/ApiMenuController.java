package com.aaa.system.controller.api;

import com.aaa.core.entity.Menu;
import com.aaa.core.vo.Result;
import com.aaa.system.service.MenuService;
import com.aaa.system.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/list")
    public Result getList(MenuVo menuVo){
        String menuName = menuVo.getMenuName();
        Long status = menuVo.getStatus();
        return Sercvice.getAll(menuName,status);
    }
    @GetMapping("{id}")
    public Result findByRoleId(@PathVariable Long id){
        return Sercvice.findByRoleId(id);
    }
    @PostMapping("confirmMenu")
    public Result<String> confirmMenu(Long roleId,Long[] menuIds){
        return Sercvice.confirmMenu(roleId,menuIds);
    }
    @PostMapping("getMenu")
    public Result getMenu(){

        return new Result(200,null,null);
    }

    @PostMapping("add")
    public Result add(Menu menu){
        int i = Sercvice.addMenu(menu);
        if (i>0){
            return new Result(200,"添加成功",true);
        }
        return new Result(200,"添加失败",false);
    }

    @DeleteMapping("{id}")
    public Result delMenu(@PathVariable Long id){
        int i = Sercvice.delMenu(id);
        if (i>0){
            return new Result(200,"成功",true);
        }
        return new Result(200,"失败",false);
    }
}
