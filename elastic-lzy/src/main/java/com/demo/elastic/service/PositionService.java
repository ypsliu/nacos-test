package com.demo.elastic.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/9
 * Time: 16:26
 * Description: No Description
 */
public interface PositionService {
    /*** 分页查询
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     * */
    public List<Map<String, Object>> searchPos(String keyword, int pageNo, int pageSize) throws IOException;

    /*** 导入数据 */
    void importAll() throws IOException;
}
