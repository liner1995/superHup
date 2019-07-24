package cn.hups.produce.service.impl;

import cn.hups.common.exception.GlobalException;
import cn.hups.common.po.Auth;
import cn.hups.common.utils.AjaxJson;
import cn.hups.common.utils.AuthUtil;
import cn.hups.common.utils.DateUtils;
import cn.hups.common.utils.StringUtils;
import cn.hups.produce.dao.BillProduceQuoteBMapper;
import cn.hups.produce.dao.BillProduceQuoteHMapper;
import cn.hups.produce.dao.BillProduceQuoteHMapperExpand;
import cn.hups.produce.po.BillProduceQuoteBPo;
import cn.hups.produce.po.BillProduceQuoteHPo;
import cn.hups.produce.service.IProduceService;
import cn.hups.salemage.dao.BillSaleQuoteMapper;
import cn.hups.salemage.dao.BillSaleQuoteMapperExpand;
import cn.hups.salemage.po.BillSaleQuotePo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * 生产管理Service实现类
 */
@Service("iProduceService")
public class ProduceServiceImpl implements IProduceService {

    @Resource
    private BillProduceQuoteHMapper billProduceQuoteHMapper;

    @Resource
    private BillProduceQuoteHMapperExpand billProduceQuoteHMapperExpand;

    @Resource
    private BillProduceQuoteBMapper billProduceQuoteBMapper;

    @Resource
    private BillSaleQuoteMapper billSaleQuoteMapper;

    @Resource
    private BillSaleQuoteMapperExpand billSaleQuoteMapperExpand;

    /**
     * 分页查询所有生产报价
     * @param billProduceQuoteHPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectAllProduceQuoteAsPage(BillProduceQuoteHPo billProduceQuoteHPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            // 任务认领后才能查询到单据
            Auth auth = AuthUtil.getAuth();
            billProduceQuoteHPo.setApprover(auth.getUserid());

            Long countNum = billProduceQuoteHMapperExpand.countData(billProduceQuoteHPo);
            List<BillProduceQuoteHPo> resultList = billProduceQuoteHMapperExpand.selectAllProduceQuoteAsPage(billProduceQuoteHPo);
            ajaxJson.setTotal(countNum);
            ajaxJson.setObj(resultList);
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 增加生产报价
     * @param billProduceQuoteHPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson insertProduceQuote(BillProduceQuoteHPo billProduceQuoteHPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (billProduceQuoteHPo != null) {
                billProduceQuoteHMapper.insertSelective(billProduceQuoteHPo);
                ajaxJson.setPrimary(billProduceQuoteHPo.getPkProduceQuoteH());
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 根据主键集合删除生产报价
     * @param billProduceQuoteHPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson deleteProduceQuoteByPks(BillProduceQuoteHPo billProduceQuoteHPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (billProduceQuoteHPo != null && StringUtils.isNotEmpty(billProduceQuoteHPo.getPkArr())) {
                List<Integer> pkArr = StringUtils.idsParamToListInt(billProduceQuoteHPo.getPkArr());
                billProduceQuoteHMapperExpand.deleteProduceQuoteByPks(pkArr);
            }
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 更新生产报价
     * @param billProduceQuoteHPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson updateProduceQuote(BillProduceQuoteHPo billProduceQuoteHPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (billProduceQuoteHPo != null && billProduceQuoteHPo.getPkProduceQuoteH() != null) {
                billProduceQuoteHMapper.updateByPrimaryKeySelective(billProduceQuoteHPo);
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 批量更新报价子表信息
     * @param billProduceQuoteBPoList
     * @return
     * @throws GlobalException
     */
    public AjaxJson updateBatchProduceQuoteB(List<BillProduceQuoteBPo> billProduceQuoteBPoList) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (billProduceQuoteBPoList != null && billProduceQuoteBPoList.size() > 0) {
                for (BillProduceQuoteBPo billProduceQuoteBPo : billProduceQuoteBPoList) {
                    if (billProduceQuoteBPo != null && billProduceQuoteBPo.getPkProduceQuoteB() != null) {
                        billProduceQuoteBMapper.updateByPrimaryKeySelective(billProduceQuoteBPo);
                    }
                }
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 认领生产报价任务
     * @param userid
     * @param pkProduct
     * @param taskId
     * @return
     * @throws GlobalException
     */
    public AjaxJson claimProduceQuote(Integer userid, Integer pkProduct, String taskId) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (userid != null && pkProduct != null && StringUtils.isNotEmpty(taskId)) {
                billProduceQuoteHMapperExpand.claimProduceQuote(userid, pkProduct, taskId);
            }
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 完成生成报价
     * @param billProduceQuoteHPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson overPassProduceQuote(BillProduceQuoteHPo billProduceQuoteHPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (billProduceQuoteHPo != null && billProduceQuoteHPo.getPkProduceQuoteH() != null) {
                billProduceQuoteHMapper.updateByPrimaryKeySelective(billProduceQuoteHPo);

                // 回写销售报价 采购报价单据主键
                Integer pkProduct = billProduceQuoteHPo.getPkProduct();
                if (pkProduct != null) {
                    BillSaleQuotePo billSaleQuotePo = billSaleQuoteMapperExpand.selectAllSaleQuoteByPkProduct(pkProduct);

                    // 更新参数
                    BillSaleQuotePo updateParam = new BillSaleQuotePo();
                    updateParam.setPkSaleQuote(billSaleQuotePo.getPkSaleQuote());
                    updateParam.setPkProduceQuoteH(billProduceQuoteHPo.getPkProduceQuoteH());
                    updateParam.setCreationtime(DateUtils.getKnowTsStr());
                    updateParam.setCreator(AuthUtil.getAuth().getUserid());
                    if (billSaleQuotePo != null && billSaleQuotePo.getPkPurchaseQuote() != null) {
                        updateParam.setBillState(3); // 采购报价及生成报价完成 开始销售报价
                    } else {
                        updateParam.setBillState(2); // 采购报价完成
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
