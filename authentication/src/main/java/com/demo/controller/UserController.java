package com.demo.controller;

import cn.dev33.satoken.basic.SaBasicUtil;
import cn.dev33.satoken.stp.SaLoginModel;
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
//            StpUtil.login(10001);
            // 设置登录账号id为10001，第二个参数指定是否为[记住我]，当此值为false后，关闭浏览器后再次打开需要重新登录
//            StpUtil.login(10001, false);

            // 指定`账号id`和`设备标识`进行登录 单点登录
//            StpUtil.login(10001, "PC");

            // `SaLoginModel`为登录参数Model，其有诸多参数决定登录时的各种逻辑，例如：
            StpUtil.login(10001, new SaLoginModel()
                    .setDevice("PC")                // 此次登录的客户端设备标识, 用于[同端互斥登录]时指定此次登录的设备名称
                    .setIsLastingCookie(true)        // 是否为持久Cookie（临时Cookie在浏览器关闭时会自动删除，持久Cookie在重新打开后依然存在）
                    .setTimeout(60 * 60 * 24 * 7)    // 指定此次登录token的有效期, 单位:秒 （如未指定，自动取全局配置的timeout值）
            );
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

    // http://localhost:9001/authentication/user/basic
    @GetMapping("basic")
    public SaResult basic() {
        SaBasicUtil.check("sa:123456");
        return SaResult.ok("basic 认证成功");
    }

    // 测试注销  ---- http://localhost:9001/authentication/user/logout
    @GetMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }


}
