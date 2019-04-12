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