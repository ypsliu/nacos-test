package com.demo.service.token;

import cn.dev33.satoken.strategy.SaStrategy;
import cn.dev33.satoken.util.SaFoxUtil;
import com.demo.common.IDUtils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/1/12
 * Time: 20:17
 * Created with IntelliJ IDEA
 * Description: 自定义生成token
 */
@Configuration
public class CreatToken {
    /**
     * 重写 Sa-Token 框架内部算法策略
     */
    @Autowired
    public void rewriteSaStrategy(){
        // 重写 Token 生成策略
        SaStrategy.me.createToken = (loginId, loginType) -> {
            // 随机60位长度字符串
//            String token = SaFoxUtil.getRandomString(60);
            String token = SnowflakeIdWorker.generateId().toString();
            return token;
        };
    }
}
