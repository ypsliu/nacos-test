package com.demo.limit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/17
 * Time: 19:13
 * Description: No Description
 */
public class LimitManager {

    private LimitManager(){}

    public static class LazyLoader{
        private static final LimitManager INSTANCE = new LimitManager();
    }

    public static LimitManager getInstance(){
        return LimitManager.LazyLoader.INSTANCE;
    }

    /**
     * 限制规则时间总长度（秒）
     */
    private static final Long WINDOWS_TIME = 1L;

    /**
     * 时间格式
     */
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    /**
     * 时间块颗粒度（越多越精确，效率越低）
     */
    private static final int SLOT_NUMS = 1;

    /**
     * 限制规则总次数
     */
    private static final int LIMIT_NUMS = 1;


    private static final Map<String,SlidingTimeWindow> limit_map
            = new ConcurrentHashMap<>();

    /**
     * 判断当前urL是否触发限流
     * @param url
     * @return true通过 /false驳回
     */
    public boolean limiting(String url){
        synchronized (url.intern()){
            if(!limit_map.containsKey(url)){
                //初始化限流规则
                limit_map.put(url,
                        new SlidingTimeWindow(SLOT_NUMS,LIMIT_NUMS,WINDOWS_TIME,TIME_UNIT));
            }
            SlidingTimeWindow slidingTimeWindow = limit_map.get(url);
            boolean pass =  slidingTimeWindow.checkAndAdd();
            return pass;
        }
    }
}
