package com.zoy.stockanalysis.repostiory;

import com.zoy.stockanalysis.entity.StockPriceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
public interface StockPriceRecordRepository extends JpaSpecificationExecutor<StockPriceRecord>,JpaRepository<StockPriceRecord,Long> {

    /**
     * 根据策略编号删除
     * @param stockAnalysisId
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("delete from StockPriceRecord sr where sr.stockAnalysisId=?1")
    void deleteByStockAnalysisId(Long stockAnalysisId);

}
