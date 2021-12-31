package com.demo.consumer.seata;

import io.seata.spring.annotation.GlobalTransactional;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/20
 * Time: 16:02
 * Description: 全局事务
 */
@GlobalTransactional//全局事务注解，seata提供
public class GlobalService {
    /*seata客户端的版本需要和服务端保持一致
      每个服务的数据库都要创建一个undo_log回滚日志表
      客户端指定的事务分组名称要和Nacos相同，比如
        service.vgroupMapping.seata-account-tx-group=default
        前缀：service.vgroupMapping.
        后缀：{自定义}*/


//    CREATE TABLE `undo_log`
//    ( `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id'
//    ,`xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'global transaction id'
//    ,`context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'undo_log context,such as serialization'
//    , `rollback_info` longblob NOT NULL COMMENT 'rollback info'
//    , `log_status` int(11) NOT NULL COMMENT '0:normal status,1:defense status'
//    , `log_created` datetime(6) NOT NULL COMMENT 'create datetime'
//    , `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime'
//    , UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE )
//    ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci
//     COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Compact;
}
