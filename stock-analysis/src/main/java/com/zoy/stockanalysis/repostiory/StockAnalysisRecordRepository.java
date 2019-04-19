package com.zoy.stockanalysis.repostiory;

import com.zoy.stockanalysis.entity.StockAnalysisRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
public interface StockAnalysisRecordRepository extends JpaSpecificationExecutor<StockAnalysisRecord>,JpaRepository<StockAnalysisRecord,Long> {

    /**
     * 根据策略编号删除
     * @param stockAnalysisId
     * @return
     */
    @Modifying
    @Query("delete from StockAnalysisRecord dr where dr.stockAnalysisId=?1")
    void deleteAllByStockAnalysisId(Long stockAnalysisId);

}
