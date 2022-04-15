CREATE TABLE User
(
    id               BIGINT        NOT NULL AUTO_INCREMENT,
    name             VARCHAR(255)  NOT NULL,
    sex              CHAR(1)       NOT NULL,
    password         VARCHAR(1024) NOT NULL,
    money            INT           NOT NULL DEFAULT 0,
    register_date    DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP (6),
    last_modify_date DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP (6) ON UPDATE CURRENT_TIMESTAMP (6),
    CHECK (sex = 'M' OR sex = 'F'),
    PRIMARY KEY (id)
);

-- 日期类型通常就是使用 DATETIME 和 TIMESTAMP 两种类型，然而由于类型 TIMESTAMP 存在性能问题，建议你还是尽可能使用类型 DATETIME。我总结一下今天的重点内容：
--
-- MySQL 5.6 版本开始 DATETIME 和 TIMESTAMP 精度支持到毫秒；
-- DATETIME 占用 8 个字节，TIMESTAMP 占用 4 个字节，DATETIME(6) 依然占用 8 个字节，TIMESTAMP(6) 占用 7 个字节；
-- TIMESTAMP 日期存储的上限为 2038-01-19 03:14:07，业务用 TIMESTAMP 存在风险；
-- 使用 TIMESTAMP 必须显式地设置时区，不要使用默认系统时区，否则存在性能问题，推荐在配置文件中设置参数 time_zone = '+08:00'；
-- 推荐日期类型使用 DATETIME，而不是 TIMESTAMP 和 INT 类型；
-- 表结构设计时，每个核心业务表，推荐设计一个 last_modify_date 的字段，用以记录每条记录的最后修改时间。