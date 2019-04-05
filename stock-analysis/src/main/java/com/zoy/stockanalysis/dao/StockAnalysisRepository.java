package com.zoy.stockanalysis.dao;

import com.zoy.stockanalysis.entity.StockAnalysis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/5
 */
public interface StockAnalysisRepository extends CrudRepository<StockAnalysis,Long>{

    /**
     * 查询
     * @param id
     * @return
     */
    @Query("from StockAnalysis where id =:id ")
    StockAnalysis getStockAnalysis(@Param("id") Long id);

    /**
     * 新增
     * @param stockAnalysis
     * @return
     */
    @Override
    StockAnalysis save(StockAnalysis stockAnalysis);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<StockAnalysis> findAll(Pageable pageable);

    /**
     * 查询所有
     * @return
     */
    @Override
    List<StockAnalysis> findAll();

    /**
     * 修改
     * @param stockName
     * @param id
     * @return
     */
    @Modifying
    @Query("update StockAnalysis set stockName = :stockName where id=:id")
    int updateStockNameById(@Param("stockName")String stockName,@Param("id")Long id);

    @Modifying
    @Override
    void delete(StockAnalysis stockAnalysis);

}
