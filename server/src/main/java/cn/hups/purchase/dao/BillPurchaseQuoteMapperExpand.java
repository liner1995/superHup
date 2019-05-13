package cn.hups.purchase.dao;

import cn.hups.purchase.po.BillPurchaseQuotePo;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * 采购报价拓展Mapper
 */
public interface BillPurchaseQuoteMapperExpand {

    /**
     * 查询表中数据数量
     * @param billPurchaseQuotePo
     * @return
     * @throws SQLException
     */
    Long countData(@Param("billPurchaseQuotePo") BillPurchaseQuotePo billPurchaseQuotePo) throws SQLException;

    /**
     * 分页查询所有销售报价
     * @param billPurchaseQuotePo
     * @return
     * @throws SQLException
     */
    List<BillPurchaseQuotePo> selectAllPurchaseQuoteAsPage(@Param("billPurchaseQuotePo") BillPurchaseQuotePo billPurchaseQuotePo) throws SQLException;

    /**
     * 根据主键集合删除采购报价
     * @param pkArr
     * @return
     * @throws SQLException
     */
    int deletePurchaseQuoteByPks(@Param("pkArr") List<Integer> pkArr) throws SQLException;

    /**
     * 认领采购报价任务
     * @param userid
     * @param pkProduct
     * @param taskId
     * @return
     * @throws SQLException
     */
    int claimPurchaseQuote(@Param("userid") Integer userid, @Param("pkProduct") Integer pkProduct, @Param("taskId") String taskId) throws SQLException;
}
