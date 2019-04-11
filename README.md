# Java架构师进阶之路

## 业务系统

### 一 、人人股票策略分析系统（ stock-analysis ）

1 问题：如何寻找最佳的策略来投资股票？

2 解决方案：通过程序，同时跑多个策略，来决策使用盈利概率最高的策略。

技术选型：
    Jetty,
    Jpa,
    Spring-Task,
    OkHttpClient,
    MySql
    
#### 需求来源
买股票看别人的总是没有依据，而且行情总是会变动，专业书籍深涩难懂，而且不符合实际，根据别人的方法去操作，总是会亏钱。
如果有一套系统，可以同时跑n多个策略？然后再选出其中盈利概率最高的策略，进行实盘操作，这样肯定会大大提高成功的概率。
    
这套系统类似Mybatis半自动化，需要自己选股，然后设定时间自动卖出，统计出什么时间段买入，什么时间段卖出，以及什么选股策略盈利概率最高。
    
#### 功能
##### 买入股票

根据股票代码买入（已完成）

设定金额，自动计算买入股票数量（已完成）

##### 卖出股票

卖出全部股票（已完成）

自动卖出（待完成）

##### 策略排行榜

根据时间统计排行榜（待完成）

统计涨幅低于3%的盈利比率 （待完成）

统计涨幅高于5%的盈利比率 （待完成）

统计涨幅3%-5%的盈利比率 （待完成）

统计跌幅最大的股票里面的特性，规避风险（待完成）
        
#### 目录说明
    ow-architect
        case-jdbc 高并发案例
        common    通用模块
        doc       文档，sql
        stock-analysis 股票策略分析
        utils     工具类
        
#### 执行步骤：
    1 修改mysql连接
    2 通过jpa初始化表
        StockAnalysisServiceImplTest.findAll
    3 新增策略
        StockAnalysisServiceImplTest.addStockAnalysis
    4 买入股票   
        1 复制策略ID
        2 修改股票代码，策略编号，购买金额
          StockAnalysisServiceImplTest.buyStockPrice
    5 平仓
        StockAnalysisServiceImplTest.sellStockPrice
    6 查看盈亏
        select * from t_stock_analysis_record
        

#### 初级目标
       通过系统管理好自己的决策，通过数据来提升自我认知，选择出合适的适合自己的决策策略。（已初步实现）
#### 中级目标
       开源系统，让更多人可以在里面模拟炒股，然后选出最优选股策略 
#### 高级目标
       自动选股功能（构思中...欢迎交流）
#### 终极目标
       通过大数据+ai决策加权训练选股模型 
       接入第三方系统，实现自动化交易
       

## 技术系统（ 平时也在修炼功法，希望可以得到各位大神的Review ）     
 
### 一 、高并发下单例模式、枚举实现、JDBC工具类
jdbc
- doc
    - java-architect.sql  关联的sql
- com.zoy.jdbc.JdbcUtilsFactory.java

  


       