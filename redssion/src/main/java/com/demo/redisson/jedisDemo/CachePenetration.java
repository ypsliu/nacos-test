package com.demo.redisson.jedisDemo;

import com.alibaba.fastjson2.JSON;
import org.springframework.util.StringUtils;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/5/12
 * Time: 23:08
 * Created with IntelliJ IDEA
 * Description: 缓存穿透策略 存储过期null值
 */
public class CachePenetration {
    //缓存空对象
    public static final String EMPTY_PRODUCT_CACHE = "{}";

    public Product getProductInfo(Long productId){
        Product product = null;
        //创建缓存key
        String productCacheKey = "PRODUCT_INFO_"+productId;
        //查询缓存
        String productCacheStr = ""/* redis.get(productCacheKey) */;
        //判断缓存是否存在
        if(!StringUtils.isEmpty(productCacheStr)){
            //判断是否为空对象
            if(EMPTY_PRODUCT_CACHE.equals(productCacheStr)){
                //继续续命
                /*redis.expire(productCacheKey,空对象有限期,时间单位)*/;
                return null;
            }
            product = JSON.parseObject(productCacheKey, Product.class);
            //继续续命
            /*redis.expire(productCacheKey,产品有限期,时间单位)*/;
            return product;
        }

        /* 从数据库中查询得到 实体*/
        /* product = productDao.getProductInfoById(productId) */;

        if(product != null){
            //放入缓存
/*
             redis.set(productCacheKey,JSON.toJSONGString(product),产品有限期,时间单位);
*/
        }else{
            //缓存空对象
            /*redis.set(productCacheKey,EMPTY_PRODUCT_CACHE,空对象有限期,时间单位);*/
        }

        return product;
    }
}
