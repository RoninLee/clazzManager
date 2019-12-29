package com.school.manager.controller;

import com.school.manager.common.PageResult;
import com.school.manager.common.Result;
import com.school.manager.dto.req.CommonSelOrDelReq;
import com.school.manager.dto.req.LoginUserReq;
import com.school.manager.dto.req.UserReq;
import com.school.manager.dto.resp.UserResp;
import com.school.manager.enums.StatusCode;
import com.school.manager.pojo.User;
import com.school.manager.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author RoninLee
 * @description 用户管理
 */
@RestController
@CrossOrigin
@Api(tags = {"用户管理模块"}, value = "用户管理")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<UserResp> login(@Valid @RequestBody LoginUserReq request) {
        UserResp userResp = userService.login(request);
        if (Objects.nonNull(userResp)) {
            return Result.success(userResp);
        }
        return Result.error(StatusCode.LOGIN_FAILURE.getDesc());
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    private Result<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return Result.success("已退出登录");
    }

    @ApiOperation("用户列表")
    @PostMapping("/list")
    public PageResult<List<User>> userList(@RequestBody UserReq request) {
        try {
            Page<User> userPage = userService.findValidList(request);
            return PageResult.success(userPage.getContent(), userPage.getTotalElements());
        } catch (Exception e) {
            return PageResult.error(e.getMessage());
        }
    }

    @ApiOperation("用户信息")
    @PostMapping("info")
    public Result<UserResp> userInfo(@RequestBody CommonSelOrDelReq<String> request) {
        try {
            return Result.success(userService.findById(request.getId()));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("新增人员")
    @PostMapping("/save")
    public Result<UserResp> save(@RequestBody @Valid UserReq request) {
        try {
            return Result.success(userService.findById(userService.save(request)));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("更新人员信息")
    @PostMapping("/update")
    public Result<UserResp> update(@RequestBody @Valid UserReq request) {
        try {
            return Result.success(userService.findById(userService.update(request)));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("删除人员信息")
    @PostMapping("/remove")
    public Result<Object> remove(@RequestBody CommonSelOrDelReq<String> request) {
        try {
            userService.removeById(request.getId());
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
