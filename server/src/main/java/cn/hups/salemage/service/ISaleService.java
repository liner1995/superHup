package cn.hups.salemage.service;

import cn.hups.common.exception.GlobalException;
import cn.hups.common.utils.AjaxJson;
import cn.hups.flowproccess.po.QuoteFlowParam;
import cn.hups.salemage.po.BdProductPo;
import cn.hups.salemage.po.BillSaleQuotePo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 销售管理服务类
 */
public interface ISaleService {

    /**
     * 分页查询所有产品信息
     * @param bdProductPo
     * @return
     * @throws GlobalException
     */
    AjaxJson selectAllProductAsPage(BdProductPo bdProductPo) throws GlobalException;

    /**
     * 新增产品信息
     * @param bdProductPo
     * @return
     * @throws GlobalException
     */
    AjaxJson insertProduct(BdProductPo bdProductPo) throws GlobalException;

    /**
     * 根据主键集合删除产品信息
     * @param bdProductPo
     * @return
     * @throws GlobalException
     */
    AjaxJson deleteProductByPks(BdProductPo bdProductPo) throws GlobalException;

    /**
     * 更新产品信息
     * @param bdProductPo
     * @return
     * @throws GlobalException
     */
    AjaxJson updateProduct(BdProductPo bdProductPo) throws GlobalException;

    /**
     * 更新销售报价任务主键
     * @param userid
     * @param pkProduct
     * @param taskId
     * @return
     * @throws GlobalException
     */
    AjaxJson claimSqleQuote(Integer userid, Integer pkProduct, String taskId) throws GlobalException;

    /**
     * 送审产品到工艺部
     * @param quoteFlowParam
     * @return
     * @throws GlobalException
     */
    AjaxJson commitProduct(QuoteFlowParam quoteFlowParam) throws GlobalException;

    /**
     * 保存产品中的3个文件到数据库
     * @param file1 BOM文件
     * @param file2 PCB文件
     * @param file3 工艺文件
     * @return
     * @throws GlobalException
     */
    AjaxJson saveProductFile(MultipartFile file1, MultipartFile file2, MultipartFile file3) throws GlobalException;

    /**
     * 分页查询所有销售报价
     * @param billSaleQuotePo
     * @return
     * @throws GlobalException
     */
    AjaxJson selectAllSaleQuoteAsPage(BillSaleQuotePo billSaleQuotePo) throws GlobalException;

    /**
     * 增加销售报价
     * @param billSaleQuotePo
     * @return
     * @throws GlobalException
     */
    AjaxJson insertSaleQuote(BillSaleQuotePo billSaleQuotePo) throws GlobalException;

    /**
     * 根据主键集合删除销售报价
     * @param billSaleQuotePo
     * @return
     * @throws GlobalException
     */
    AjaxJson deleteSaleQuoteByPks(BillSaleQuotePo billSaleQuotePo) throws GlobalException;

    /**
     * 更新销售报价
     * @param billSaleQuotePo
     * @return
     * @throws GlobalException
     */
    AjaxJson updateSaleQuote(BillSaleQuotePo billSaleQuotePo) throws GlobalException;
}
