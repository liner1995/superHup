package cn.hups.salemage.service.impl;

import cn.hups.common.exception.GlobalException;
import cn.hups.common.po.Auth;
import cn.hups.common.utils.*;
import cn.hups.flowproccess.po.QuoteFlowParam;
import cn.hups.purchase.dao.BillPurchaseQuoteMapper;
import cn.hups.purchase.dao.BillPurchaseQuoteMapperExpand;
import cn.hups.purchase.po.BillPurchaseQuotePo;
import cn.hups.salemage.dao.*;
import cn.hups.salemage.po.BdProductPo;
import cn.hups.salemage.po.BillSaleQuotePo;
import cn.hups.salemage.service.ISaleService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 销售管理服务实现类
 */
@Service("iSaleService")
public class SaleServiceImpl implements ISaleService {

    @Resource
    private BdProductMapper bdProductMapper;

    @Resource
    private BdProductMapperExpand bdProductMapperExpand;

    @Resource
    private BillSaleQuoteMapper billSaleQuoteMapper;

    @Resource
    private BillSaleQuoteMapperExpand billSaleQuoteMapperExpand;

    // 采购报价Mapper
    @Resource
    private BillPurchaseQuoteMapper billPurchaseQuoteMapper;

    @Resource
    private BillPurchaseQuoteMapperExpand billPurchaseQuoteMapperExpand;



    /**
     * 分页查询所有产品信息
     * @param bdProductPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectAllProductAsPage(BdProductPo bdProductPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            Long countNum = bdProductMapperExpand.countData(bdProductPo);
            List<BdProductPo> resultList = bdProductMapperExpand.selectAllProductAsPage(bdProductPo);
            ajaxJson.setTotal(countNum);
            ajaxJson.setObj(resultList);
        }catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 新增产品信息
     * @param bdProductPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson insertProduct(BdProductPo bdProductPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdProductPo != null) {
                Auth auth = AuthUtil.getAuth();
                bdProductPo.setCreator(auth.getUserid());
                bdProductMapper.insertSelective(bdProductPo);
                ajaxJson.setPrimary(bdProductPo.getPkProduct());
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 根据主键集合删除产品信息
     * @param bdProductPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson deleteProductByPks(BdProductPo bdProductPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdProductPo != null && StringUtils.isNotEmpty(bdProductPo.getPkArr())) {
                List<Integer> pkArr = StringUtils.idsParamToListInt(bdProductPo.getPkArr());
                bdProductMapperExpand.deleteProductByPs(pkArr);
            }
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 更新产品信息
     * @param bdProductPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson updateProduct(BdProductPo bdProductPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdProductPo != null && bdProductPo.getPkProduct() != null) {
                bdProductMapper.updateByPrimaryKeySelective(bdProductPo);
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 更新销售报价任务主键
     * @param userid
     * @param pkProduct
     * @param taskId
     * @return
     * @throws GlobalException
     */
    public AjaxJson claimSqleQuote(Integer userid, Integer pkProduct, String taskId) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (userid != null && pkProduct != null && StringUtils.isNotEmpty(taskId)) {
                billSaleQuoteMapperExpand.claimSqleQuote(userid, pkProduct, taskId);
            }
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 送审产品到工艺部
     * @param quoteFlowParam
     * @return
     * @throws GlobalException
     */
    public AjaxJson commitProduct(QuoteFlowParam quoteFlowParam) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (quoteFlowParam != null && quoteFlowParam.getPkProduct() != null) {
                // 修改单据状态
                BdProductPo commitPo = new BdProductPo();
                commitPo.setPkProduct(quoteFlowParam.getPkProduct());
                commitPo.setBillState(1);
                bdProductMapper.updateByPrimaryKeySelective(commitPo);

                // 自动插入数据到采购报价中进行
                BillPurchaseQuotePo billPurchaseQuotePo = new BillPurchaseQuotePo();
                billPurchaseQuotePo.setPkCustomer(quoteFlowParam.getPkCustomer()); // 客户信息
                billPurchaseQuotePo.setPkProduct(quoteFlowParam.getPkProduct()); // 产品信息
                billPurchaseQuotePo.setPkPsndoc(quoteFlowParam.getPkPsndoc()); // 业务员
                billPurchaseQuotePo.setBillState(0); // 单据状态
                billPurchaseQuotePo.setDr(0);
                billPurchaseQuotePo.setTs(DateUtils.getKnowTsStr());
                billPurchaseQuoteMapper.insertSelective(billPurchaseQuotePo);

                // 自动预置一张销售报价单据
                BillSaleQuotePo billSaleQuotePo = new BillSaleQuotePo();
                billSaleQuotePo.setPkCustomer(quoteFlowParam.getPkCustomer()); // 客户信息
                billSaleQuotePo.setPkProduct(quoteFlowParam.getPkProduct()); // 产品信息
                billSaleQuotePo.setCreator(null);
                billSaleQuotePo.setCreationtime(DateUtils.getKnowTsStr());
                billSaleQuotePo.setBillState(0); // 单据状态
                billSaleQuotePo.setDr(0);
                billSaleQuotePo.setTs(DateUtils.getKnowTsStr());
                billSaleQuoteMapper.insertSelective(billSaleQuotePo);
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 保存产品中的3个文件到数据库
     * @param file1 BOM文件
     * @param file2 PCB文件
     * @param file3 工艺文件
     * @return
     * @throws GlobalException
     */
    public AjaxJson saveProductFile(MultipartFile file1, MultipartFile file2, MultipartFile file3) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        FileUtils fileUtils = new FileUtils();
        Map<String, String> saveResultMap = fileUtils.saveProductFiles(file1, file2, file3);
        ajaxJson.setObj(saveResultMap);

        return ajaxJson;
    }

    /**
     * 分页查询所有销售报价
     * @param billSaleQuotePo
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectAllSaleQuoteAsPage(BillSaleQuotePo billSaleQuotePo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            Long countNum = billSaleQuoteMapperExpand.countData(billSaleQuotePo);
            List<BillSaleQuotePo> resultData = billSaleQuoteMapperExpand.selectAllSaleQuoteAsPage(billSaleQuotePo);
            ajaxJson.setTotal(countNum);
            ajaxJson.setObj(resultData);
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 增加销售报价
     * @param billSaleQuotePo
     * @return
     * @throws GlobalException
     */
    public AjaxJson insertSaleQuote(BillSaleQuotePo billSaleQuotePo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (billSaleQuotePo != null) {
                billSaleQuoteMapper.insertSelective(billSaleQuotePo);
                ajaxJson.setPrimary(billSaleQuotePo.getPkSaleQuote());
            }
        }catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 根据主键集合删除销售报价
     * @param billSaleQuotePo
     * @return
     * @throws GlobalException
     */
    public AjaxJson deleteSaleQuoteByPks(BillSaleQuotePo billSaleQuotePo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (billSaleQuotePo != null && StringUtils.isNotEmpty(billSaleQuotePo.getPkArr())) {
                List<Integer> pkArr = StringUtils.idsParamToListInt(billSaleQuotePo.getPkArr());
                billSaleQuoteMapperExpand.deleteSaleQuoteByPks(pkArr);
            }
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 更新销售报价
     * @param billSaleQuotePo
     * @return
     * @throws GlobalException
     */
    public AjaxJson updateSaleQuote(BillSaleQuotePo billSaleQuotePo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (billSaleQuotePo != null && billSaleQuotePo.getPkSaleQuote() != null) {
                billSaleQuoteMapper.updateByPrimaryKeySelective(billSaleQuotePo);
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }
}
