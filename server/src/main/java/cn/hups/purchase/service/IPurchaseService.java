package cn.hups.purchase.service;

import cn.hups.common.exception.GlobalException;
import cn.hups.common.utils.AjaxJson;
import cn.hups.purchase.po.BillPurchaseQuotePo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 采购管理Service
 */
public interface IPurchaseService {

    /**
     * 分页查询所有采购报价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    AjaxJson selectPurchaseQuoteAsPage(BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException;

    /**
     * 新增采购保价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    AjaxJson insertPurchaseQuote(BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException;

    /**
     * 根据主键集合删除采购保价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    AjaxJson deletePurchaseQuoteByPks(BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException;

    /**
     * 更新采购报价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    AjaxJson updatePurchaseQuote(BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException;

    /**
     * 保存采购报价文件
     * @param purfile
     * @return
     * @throws GlobalException
     */
    AjaxJson savePurchaseFile(MultipartFile purfile) throws GlobalException;

    /**
     * 认领采购报价任务
     * @param userid
     * @param pkProduct
     * @param taskId
     * @return
     * @throws GlobalException
     */
    AjaxJson claimPurchaseQuote(Integer userid, Integer pkProduct, String taskId) throws GlobalException;

    /**
     * 完成采购报价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    AjaxJson overPassPurchaseQuote(BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException;
}
