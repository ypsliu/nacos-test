package com.demo.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/12
 * Time: 15:58
 * Description: No Description
 */
@RestController
@RequestMapping("/user")
public class UserController {
    // 测试登录，浏览器访问： http://localhost:9001/authentication/user/doLogin?username=zhang&password=123456
    @GetMapping("doLogin")
    public SaResult  doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }

    // 查询登录状态，浏览器访问： http://localhost:9001/authentication/user/isLogin
    @GetMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("当前账号是否登录："+StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:9001/authentication/user/tokenInfo
    @GetMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 查询 Token 信息  ---- http://localhost:9001/authentication/user/safe
    @GetMapping("safe")
    public SaResult safe() {
        StpUtil.openSafe(200);
        return SaResult.ok("二级认证成功");
    }


    // 测试注销  ---- http://localhost:9001/authentication/user/logout
    @GetMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

}
