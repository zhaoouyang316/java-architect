-- 根据策略编号查询买入价格
select
spot_price 买入价格,
stock_name 策略名称,
stock_code 股票代码,
position_number 持仓数量
from t_stock_price_record where stock_analysis_id='169039296274104320'


-- 根据策略编号查询平仓信息
select
total_settlement 总结算,
stock_name 策略名称,
settlement_price 结算价,
volatility_percentage 波动百分比,
broader_market_status  大盘涨跌
from t_stock_analysis_record where  stock_analysis_id='168674780231237632'


-- 查询逆向投资股票

select * from t_stock_analysis where stock_name like '%逆向投资%'


-- 查询逆向投资股票记录

select
tspr.spot_price 买入价格,
tspr.stock_name 策略名称,
tspr.stock_code 股票代码,
tspr.position_number 持仓数量,
tspr.yesterday_settlement 上期盈亏,
tspr.volatility_percentage 波动幅度
 from t_stock_price_record tspr
 where stock_analysis_id='170889798196658176'

 -- 查看逆向投资的盈亏

 select * from t_stock_analysis_record where stock_analysis_id='170539967515197440'
