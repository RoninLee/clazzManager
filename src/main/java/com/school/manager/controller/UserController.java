package com.school.manager.controller;

import com.school.manager.enums.StatusCode;
import com.school.manager.jwt.LoginUserInfo;
import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.common.FuzzyQueryReq;
import com.school.manager.pojo.dto.common.PageResult;
import com.school.manager.pojo.dto.common.Result;
import com.school.manager.pojo.dto.req.CommonSelOrDelReq;
import com.school.manager.pojo.dto.req.LoginReq;
import com.school.manager.pojo.dto.req.UserPasswordUpdateReq;
import com.school.manager.pojo.dto.req.UserSaveReq;
import com.school.manager.pojo.dto.req.UserUpdateReq;
import com.school.manager.pojo.dto.resp.LoginResp;
import com.school.manager.pojo.dto.resp.UserResp;
import com.school.manager.service.UserService;
import com.school.manager.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author RoninLee
 * @description 用户管理
 */
@Slf4j
@RestController
@CrossOrigin
@Api(tags = {"用户管理模块"}, value = "用户管理")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<LoginResp> login(@Valid @RequestBody LoginReq request) {
        LoginUserInfo loginUserInfo = userService.login(request);
        if (Objects.isNull(loginUserInfo)) {
            return Result.error(StatusCode.LOGIN_FAILURE.getDesc());
        }
        LoginResp loginResp = new LoginResp();
        String token = jwtUtil.createJwt(loginUserInfo.getId(), loginUserInfo.getName(), loginUserInfo);
        loginResp.setToken(token);
        loginResp.setLoginUserInfo(loginUserInfo);
        return Result.success(loginResp);
    }

    @ApiOperation("用户列表")
    @PostMapping("/list")
    public PageResult<List<BaseDTO<String>>> userList(@RequestBody @Valid FuzzyQueryReq request) {
        return userService.list(request);
    }

    @ApiOperation("用户信息")
    @PostMapping("info")
    public Result<UserResp> userInfo(@RequestBody @Valid CommonSelOrDelReq<String> request) {
        return Result.success(userService.info(request.getId()));
    }

    @ApiOperation("新增人员")
    @PostMapping("/save")
    public Result<UserResp> save(@RequestBody @Valid UserSaveReq request) {
        return Result.success(userService.info(userService.save(request)));
    }

    @ApiOperation("更新人员信息")
    @PostMapping("/update")
    public Result<UserResp> update(@RequestBody @Valid UserUpdateReq request) {
        try {
            return Result.success(userService.info(userService.update(request)));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("删除人员信息")
    @PostMapping("/remove")
    public Result<Object> remove(@RequestBody CommonSelOrDelReq<String> request) {
        try {
            userService.delete(request.getId());
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("更改密码")
    @PostMapping("/updatePassword")
    public Result<Object> updatePassword(@RequestBody @Valid UserPasswordUpdateReq request) {
        userService.updatePassword(request.getOldPassword(), request.getNewPassword());
        return Result.success();
    }
}
