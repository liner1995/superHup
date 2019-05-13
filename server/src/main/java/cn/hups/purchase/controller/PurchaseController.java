package cn.hups.purchase.controller;

import cn.hups.common.exception.GlobalException;
import cn.hups.common.utils.AjaxJson;
import cn.hups.purchase.po.BillPurchaseQuotePo;
import cn.hups.purchase.service.IPurchaseService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 采购管理Controller
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Resource
    protected IPurchaseService iPurchaseService;

    /**
     * 分页查询所有采购报价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/selectPurchaseQuoteAsPage")
    public AjaxJson selectPurchaseQuoteAsPage(@RequestBody BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException {
        return iPurchaseService.selectPurchaseQuoteAsPage(billPurchaseQuotePo);
    }

    /**
     * 新增采购保价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/insertPurchaseQuote")
    public AjaxJson insertPurchaseQuote(@RequestBody BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException {
        return iPurchaseService.insertPurchaseQuote(billPurchaseQuotePo);
    }

    /**
     * 根据主键集合删除采购保价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/deletePurchaseQuoteByPks")
    public AjaxJson deletePurchaseQuoteByPks(@RequestBody BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException {
        return iPurchaseService.deletePurchaseQuoteByPks(billPurchaseQuotePo);
    }

    /**
     * 更新采购报价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/updatePurchaseQuote")
    public AjaxJson updatePurchaseQuote(@RequestBody BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException {
        return iPurchaseService.updatePurchaseQuote(billPurchaseQuotePo);
    }

    /**
     * 保存采购报价文件
     * @param purfile
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/savePurchaseFile")
    public AjaxJson savePurchaseFile(@RequestParam(required = false, value = "purfile") MultipartFile purfile) throws GlobalException {
        return iPurchaseService.savePurchaseFile(purfile);
    }

}
