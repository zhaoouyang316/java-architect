-- 股票策略表
CREATE TABLE `java-architect`.`stock_analysis` (
  `id` BIGINT(20) NOT NULL,
  `stock_name` VARCHAR(200) NULL COMMENT '策略名称',
  `buy_time` TIMESTAMP NULL COMMENT '买入时间',
  `sell_time` TIMESTAMP NULL COMMENT '卖出时间',
  `create_time` TIMESTAMP NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

-- 股票策略记录表
CREATE TABLE `java-architect`.`stock_analysis_record` (
  `id` BIGINT(20) NOT NULL,
  `stock_name` VARCHAR(200) NULL COMMENT '策略名称',
  `stock_analysis_id` BIGINT(20) NULL COMMENT '策略编号',
  `settlement_price` DECIMAL(12,4) NULL COMMENT '结算价',
  `broader_market_status` INT NULL COMMENT '状态 0 大盘跌 1 大盘涨',
  `create_time` TIMESTAMP NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

-- 股票价格记录表
CREATE TABLE `java-architect`.`stock_record` (
  `id` BIGINT(20) NOT NULL,
  `stock_name` VARCHAR(200) NULL COMMENT '股票名字',
  `stock_analysis_id` BIGINT(20) NULL COMMENT '策略编号',
  `today_price` DECIMAL(12,4) NULL COMMENT '今日开盘价',
  `yesterday_price` DECIMAL(12,4) NULL COMMENT '昨日收盘价',
  `spot_price` DECIMAL(12,4) NULL COMMENT '当前价格',
  `high_price` DECIMAL(12,4) NULL COMMENT '今日最高价',
  `lower_price` DECIMAL(12,4) NULL COMMENT '今日最低价',
  `buy_one_price_first` DECIMAL(12,4) NULL COMMENT '买一价',
  `sell_one_price_first` DECIMAL(12,4) NULL COMMENT '卖一价',
  `volume` BIGINT(20) NULL COMMENT '成交量',
  `turnover` DECIMAL(12,4) NULL COMMENT '成交额',
  `buy_one_number` BIGINT(20) NULL COMMENT '买一量',
  `buy_one_price` DECIMAL(12,4) NULL COMMENT '买一价',
  `buy_two_number` BIGINT(20) NULL COMMENT '买二量',
  `buy_two_price` DECIMAL(12,4) NULL COMMENT '买二价',
  `buy_three_number` BIGINT(20) NULL COMMENT '买三量',
  `buy_three_price` DECIMAL(12,4) NULL COMMENT '买三价',
  `buy_four_number` BIGINT(20) NULL COMMENT '买四量',
  `buy_four_price` DECIMAL(12,4) NULL COMMENT '买四价',
  `buy_five_number` BIGINT(20) NULL COMMENT '买五量',
  `buy_five_price` DECIMAL(12,4) NULL COMMENT '买五价',
  `sell_one_number` BIGINT(20) NULL COMMENT '卖一量',
  `sell_one_price` DECIMAL(12,4) NULL COMMENT '卖一价',
  `sell_two_number` BIGINT(20) NULL COMMENT '卖二量',
  `sell_two_price` DECIMAL(12,4) NULL COMMENT '卖二价',
  `sell_three_number` BIGINT(20) NULL COMMENT '卖三量',
  `sell_three_price` DECIMAL(12,4) NULL COMMENT '卖三价',
  `sell_four_number` BIGINT(20) NULL COMMENT '卖四量',
  `sell_four_price` DECIMAL(12,4) NULL COMMENT '卖四价',
  `sell_five_number` BIGINT(20) NULL COMMENT '卖五量',
  `sell_five_price` DECIMAL(12,4) NULL COMMENT '卖五价',
  `time` VARCHAR(200) NULL COMMENT '日期',
  `status` INT NULL COMMENT '状态 0 卖出 1 持仓',
  `broader_market_status` INT NULL COMMENT '状态 0 大盘跌 1 大盘涨',
  `create_time` TIMESTAMP NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));