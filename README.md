
### Java架构师进阶之路

##### 一 、高并发下单例模式、枚举实现、JDBC工具类
jdbc
- doc
    -  java-architect.sql  关联的sql
- com.zoy.jdbc.JdbcUtilsFactory.java

##### 二 、股票分析程序 

1 问题：如何寻找最佳的策略来投资股票？

2 解决方案：通过程序，同时跑多个策略，来决策使用盈利概率最高的策略。

  技术方案：通过定时任务设定策略，记录股票价格，统计出每一种策略的盈亏，帮助投资者决策使用那种策略

技术选型：
    Jetty, 
    Jpa,
    Spring-Task,
    OkHttpClient,
    MySql
    
stock-analysis
- doc
    - stock-analysis.sql 关联sql

### 功能
    买入股票
        根据股票代码买入
    卖出股票
        卖出全部股票
    策略排行榜
        根据时间统计排行榜