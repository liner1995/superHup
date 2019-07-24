package cn.hups.purchase.service.impl;

import cn.hups.common.exception.GlobalException;
import cn.hups.common.po.Auth;
import cn.hups.common.utils.*;
import cn.hups.purchase.dao.BillPurchaseQuoteMapper;
import cn.hups.purchase.dao.BillPurchaseQuoteMapperExpand;
import cn.hups.purchase.po.BillPurchaseQuotePo;
import cn.hups.purchase.service.IPurchaseService;
import cn.hups.salemage.dao.BillSaleQuoteMapper;
import cn.hups.salemage.dao.BillSaleQuoteMapperExpand;
import cn.hups.salemage.po.BillSaleQuotePo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 采购管理服务实现类
 */
@Service("iPurchaseService")
public class PurchaseServiceImpl implements IPurchaseService {

    @Resource
    private BillPurchaseQuoteMapper billPurchaseQuoteMapper;

    @Resource
    private BillPurchaseQuoteMapperExpand billPurchaseQuoteMapperExpand;

    @Resource
    private BillSaleQuoteMapper billSaleQuoteMapper;

    @Resource
    private BillSaleQuoteMapperExpand billSaleQuoteMapperExpand;

    /**
     * 分页查询所有采购报价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectPurchaseQuoteAsPage(BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            // 认领任务后才能查询到数据
            Auth auth = AuthUtil.getAuth();
            billPurchaseQuotePo.setApprover(auth.getUserid());

            Long countNum = billPurchaseQuoteMapperExpand.countData(billPurchaseQuotePo);
            List<BillPurchaseQuotePo> resultList = billPurchaseQuoteMapperExpand.selectAllPurchaseQuoteAsPage(billPurchaseQuotePo);
            ajaxJson.setTotal(countNum);
            ajaxJson.setObj(resultList);
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 新增采购保价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    public AjaxJson insertPurchaseQuote(BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (billPurchaseQuotePo != null) {
                billPurchaseQuoteMapper.insertSelective(billPurchaseQuotePo);
                ajaxJson.setPrimary(billPurchaseQuotePo.getPkPurchaseQuote());
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 根据主键集合删除采购保价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    public AjaxJson deletePurchaseQuoteByPks(BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (billPurchaseQuotePo != null && StringUtils.isNotEmpty(billPurchaseQuotePo.getPkArr())) {
                List<Integer> pkArr = StringUtils.idsParamToListInt(billPurchaseQuotePo.getPkArr());
                billPurchaseQuoteMapperExpand.deletePurchaseQuoteByPks(pkArr);
            }
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 更新采购报价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    public AjaxJson updatePurchaseQuote(BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (billPurchaseQuotePo != null && billPurchaseQuotePo.getPkPurchaseQuote() != null) {
                billPurchaseQuoteMapper.updateByPrimaryKeySelective(billPurchaseQuotePo);
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 保存采购报价文件
     * @param purfile
     * @return
     * @throws GlobalException
     */
    public AjaxJson savePurchaseFile(MultipartFile purfile) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        if (purfile != null) {
            FileUtils fileUtils = new FileUtils();
            Map<String, Object> resultMap = fileUtils.savePurchaseFile(purfile);
            ajaxJson.setObj(resultMap);
        }

        return ajaxJson;
    }

    /**
     * 认领采购报价任务
     * @param userid
     * @param pkProduct
     * @param taskId
     * @return
     * @throws GlobalException
     */
    public AjaxJson claimPurchaseQuote(Integer userid, Integer pkProduct, String taskId) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (userid != null && pkProduct != null && StringUtils.isNotEmpty(taskId)) {
                billPurchaseQuoteMapperExpand.claimPurchaseQuote(userid, pkProduct, taskId);
            }
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 完成采购报价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    public AjaxJson overPassPurchaseQuote(BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (billPurchaseQuotePo != null && billPurchaseQuotePo.getPkPurchaseQuote() != null) {
                billPurchaseQuoteMapper.updateByPrimaryKeySelective(billPurchaseQuotePo);

                // 回写销售报价 采购报价单据主键
                Integer pkProduct = billPurchaseQuotePo.getPkProduct();
                if (pkProduct != null) {
                    BillSaleQuotePo billSaleQuotePo = billSaleQuoteMapperExpand.selectAllSaleQuoteByPkProduct(pkProduct);

                    // 更新参数
                    BillSaleQuotePo updateParam = new BillSaleQuotePo();
                    updateParam.setPkSaleQuote(billSaleQuotePo.getPkSaleQuote());
                    updateParam.setPkPurchaseQuote(billPurchaseQuotePo.getPkPurchaseQuote());
                    updateParam.setCreationtime(DateUtils.getKnowTsStr());
                    updateParam.setCreator(AuthUtil.getAuth().getUserid());
                    if (billSaleQuotePo != null && billSaleQuotePo.getPkProduceQuoteH() != null) {
                        updateParam.setBillState(3); // 采购报价及生成报价完成 开始销售报价
                    } else {
                        updateParam.setBillState(1); // 采购报价完成
                    }
                    billSaleQuoteMapper.updateByPrimaryKeySelective(updateParam);
                }
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }
}
