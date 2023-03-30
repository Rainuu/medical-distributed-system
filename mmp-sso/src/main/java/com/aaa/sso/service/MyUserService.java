package com.aaa.sso.service;


import com.aaa.core.entity.Menu;
import com.aaa.core.entity.User;
import com.aaa.sso.aop.annotation.LoginLog;
import com.aaa.sso.feign.MenuFeign;
import com.aaa.sso.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class MyUserService implements UserDetailsService {
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private MenuFeign menuFeign;
    @Override
    @LoginLog
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userFeign.getByUsername(username);
        if (user==null){
            throw new RuntimeException("账号不纯在");
        }
        //查询用户权限
        List<Menu> menuList =menuFeign.findByUserid(user.getUserId());
        Collection<SimpleGrantedAuthority> authorities=menuList.stream().map(item->new SimpleGrantedAuthority(item.getPercode())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getPhone(),user.getPassword(),authorities);
    }
}
