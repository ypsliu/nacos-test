package com.demo.controller;

import com.demo.pojo.User;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/17
 * Time: 16:44
 * Description: No Description
 */

/**
 * @Api：放在请求的类上，与 @Controller 并列，说明类的作用，如用户模块，订单类等。
 * tags="说明该类的作用"
 * value="该参数没什么意义，所以不需要配置"
 */
@Api(tags = "hello相关模块")
@RequestMapping("/api")
@RestController
public class SwaggerController {

    /**
     * @ApiOperation："用在请求的方法上，说明方法的作用" value="说明方法的作用" notes="方法的备注说明"
     *
     * @ApiImplicitParams：用在请求的方法上，包含一组参数说明
     *     @ApiImplicitParam：对单个参数的说明
     *         name：参数名
     *         value：参数的汉字说明、解释
     *         required：参数是否必须传
     *         paramType：参数放在哪个地方
     *             · header --> 请求参数的获取：@RequestHeader
     *             · query --> 请求参数的获取：@RequestParam
     *             · path（用于restful接口）--> 请求参数的获取：@PathVariable
     *             · body（请求体）-->  @RequestBody User user
     *             · form（普通表单提交）
     *         dataType：参数类型，默认String，其它值dataType="int"
     *         defaultValue：参数的默认值
     *
     * @ApiResponses：方法返回对象的说明
     *     @ApiResponse：每个参数的说明
     *         code：数字，例如400
     *         message：信息，例如"请求参数没填好"
     *         response：抛出异常的类
     *
     * @ApiModel：用于JavaBean的类上面，表示此 JavaBean 整体的信息
     *     （这种一般用在post创建的时候，使用 @RequestBody 这样的场景，请求参数无法使用 @ApiImplicitParam 注解进行描述的时候 ）
     *
     * @ApiModelProperty：用在JavaBean的属性上面，说明属性的含义
     *
     */
    @ApiOperation(value = "post I", notes = "测试post 接口一")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="密钥",required=true,paramType="header"),
            @ApiImplicitParam(name="name",value="姓名",required=true,paramType="query"),
    })
    @PostMapping("post1")
    public ResponseEntity post1(@RequestHeader String token,
                                @RequestParam String name){
        System.out.println("post1 token:"+token);
        System.out.println("post1 name:"+name);
        return  ResponseEntity.ok("hello world");
    }

    @ApiOperation(value = "post II", notes = "测试post 接口二")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="密钥",required=true,paramType="header"),
    })
    @PostMapping("post2")
    public ResponseEntity post2(@RequestHeader String token,
                                @RequestBody User user ){
        System.out.println("post2 token:"+token);
        System.out.println("post2 user:"+user);
        return  ResponseEntity.ok("hello world");
    }
}
