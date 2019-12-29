package com.school.manager.service.impl;

import com.school.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author RoninLee
 * @description 登录
 */
@Component
public class LoginServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public synchronized UserDetails loadUserByUsername(String jobNumber) throws UsernameNotFoundException {
        return userService.info(jobNumber);
    }
}
