package com.demo.controller;

import cn.dev33.satoken.annotation.*;
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
@RequestMapping("/check")
public class CheckController {
    // 测试登录，浏览器访问： http://localhost:9001/authentication/check/info
    // 登录认证：只有登录之后才能进入该方法
    @SaCheckLogin
    @GetMapping("info")
    public SaResult info() {
        return SaResult.ok("查询用户信息");
    }

    // 测试登录，浏览器访问： http://localhost:9001/authentication/check/superAdmin
    // 角色认证：必须具有指定角色才能进入该方法
    @SaCheckRole("super-admin")
    @RequestMapping("superAdmin")
    public SaResult superAdmin() {
        return SaResult.ok("超级管理员角色");
    }

    // 测试登录，浏览器访问： http://localhost:9001/authentication/check/userAdd
    // 权限认证：必须具有指定权限才能进入该方法
    @SaCheckPermission("user-add")
    @RequestMapping("userAdd")
    public SaResult userAdd() {
        return SaResult.ok("用户增加");
    }

    // 测试登录，浏览器访问： http://localhost:9001/authentication/check/checkSafe
    // 二级认证：必须二级认证之后才能进入该方法
    @SaCheckSafe()
    @RequestMapping("checkSafe")
    public SaResult checkSafe() {
        return SaResult.ok("二级认证");
    }

    // 测试登录，浏览器访问： http://localhost:9001/authentication/check/checkBasic
    // Http Basic 认证：只有通过 Basic 认证后才能进入该方法
    @SaCheckBasic(account = "sa:123456")
    @RequestMapping("checkBasic")
    public SaResult checkBasic() {
        return SaResult.ok("basic 认证");
    }


    // 测试登录，浏览器访问： http://localhost:9001/authentication/check/atJurOr
    // 注解式鉴权：只要具有其中一个权限即可通过校验
    //mode有两种取值：
    //SaMode.AND, 标注一组权限，会话必须全部具有才可通过校验
    //SaMode.OR, 标注一组权限，会话只要具有其一即可通过校验
    @RequestMapping("atJurOr")
    @SaCheckPermission(value = {"user-add", "user-all", "user-delete"}, mode = SaMode.OR)
    public SaResult atJurOr() {
        return SaResult.data("用户信息");
    }


    // 测试登录，浏览器访问： http://localhost:9001/authentication/check/userAddOr
    // 注解式鉴权：只要具有其中一个权限即可通过校验
//    orRole 字段代表权限认证未通过时的次要选择，两者只要其一认证成功即可通过校验，其有三种写法：
//    写法一：orRole = "admin"，代表需要拥有角色 admin 。
//    写法二：orRole = {"admin", "manager", "staff"}，代表具有三个角色其一即可。
//    写法三：orRole = {"admin, manager, staff"}，代表必须同时具有三个角色。
    @RequestMapping("userAddOr")
    @SaCheckPermission(value = "user-add", orRole = "admin")
    public SaResult userAddOr() {
        return SaResult.data("用户信息");
    }
}
