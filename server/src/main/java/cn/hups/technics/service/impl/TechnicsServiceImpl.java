package cn.hups.technics.service.impl;

import cn.hups.common.constract.RefConstract;
import cn.hups.common.exception.GlobalException;
import cn.hups.common.po.Auth;
import cn.hups.common.utils.AjaxJson;
import cn.hups.common.utils.AuthUtil;
import cn.hups.common.utils.DateUtils;
import cn.hups.common.utils.StringUtils;
import cn.hups.produce.dao.BillProduceQuoteBMapper;
import cn.hups.produce.dao.BillProduceQuoteHMapper;
import cn.hups.produce.po.BillProduceQuoteBPo;
import cn.hups.produce.po.BillProduceQuoteHPo;
import cn.hups.salemage.dao.BdProductMapper;
import cn.hups.salemage.dao.BdProductMapperExpand;
import cn.hups.salemage.po.BdProductPo;
import cn.hups.technics.service.ITechnicsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * 工艺管理实现类
 */
@Service("iTechnicsService")
public class TechnicsServiceImpl implements ITechnicsService {

    @Resource
    private BdProductMapper bdProductMapper;

    @Resource
    private BdProductMapperExpand bdProductMapperExpand;

    @Resource
    private BillProduceQuoteHMapper billProduceQuoteHMapper;

    @Resource
    private BillProduceQuoteBMapper billProduceQuoteBMapper;

    /**
     * 分页查询工艺审核
     * @param bdProductPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectAllTechniceAsPage(BdProductPo bdProductPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            // 认领任务之后才能查询数据
            Auth auth = AuthUtil.getAuth();
            bdProductPo.setApprover(auth.getUserid());

            Long countNum = bdProductMapperExpand.countData(bdProductPo);
            List<BdProductPo> resultList = bdProductMapperExpand.selectAllProductAsPage(bdProductPo);
            ajaxJson.setObj(resultList);
            ajaxJson.setTotal(countNum);
        }catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 工艺审核通过
     * @param bdProductPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson auditPassTecaudit(BdProductPo bdProductPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdProductPo != null && bdProductPo.getPkProduct() != null) {
                // 修改单据状态 2:工艺审核通过
                BdProductPo commitPo = new BdProductPo();
                commitPo.setPkProduct(bdProductPo.getPkProduct());
                commitPo.setBillState(2);
                bdProductMapper.updateByPrimaryKeySelective(commitPo);

                // 生成生产报价数据
                BillProduceQuoteHPo billProduceQuoteHPo = new BillProduceQuoteHPo();
                billProduceQuoteHPo.setPkCustomer(bdProductPo.getPkCustomer()); // 客户主键
                billProduceQuoteHPo.setPkProduct(bdProductPo.getPkProduct()); // 产品主键
                billProduceQuoteHPo.setPkPsndoc(bdProductPo.getPkPsndoc()); // 业务员主键
                billProduceQuoteHPo.setBillState(0);
                billProduceQuoteHPo.setDr(0);
                billProduceQuoteHPo.setTs(DateUtils.getKnowTsStr());
                billProduceQuoteHMapper.insertSelective(billProduceQuoteHPo);

                // 根据产品信息生成生成报价子表数据
                // SMT 报价
                initSavePurchaseQuoteBVO(billProduceQuoteHPo.getPkProduceQuoteH(), bdProductPo.getSmt(), "否", "SMT");

                // DIP 报价
                initSavePurchaseQuoteBVO(billProduceQuoteHPo.getPkProduceQuoteH(), bdProductPo.getDip(), "否", "DIP");

                // 根据产品判断是否有三防报价
                if (RefConstract.YESORNO_YES.equals(bdProductPo.getDefense())) {
                    initSavePurchaseQuoteBVO(billProduceQuoteHPo.getPkProduceQuoteH(), bdProductPo.getDefenseArea().intValue(), "否", "三防");
                }

                // 根据产品判断是否需要组装报价
                if (RefConstract.YESORNO_YES.equals(bdProductPo.getIsinstal()) ||
                        RefConstract.YESORNO_YES.equals(bdProductPo.getIstest()) ||
                        RefConstract.YESORNO_YES.equals(bdProductPo.getIsaging())) {
                    Integer instalMin = bdProductPo.getInstalMin() == null ? 0 : bdProductPo.getInstalMin();
                    Integer testMin = bdProductPo.getTestMin() == null ? 0 : bdProductPo.getTestMin();
                    Integer agingMin = bdProductPo.getAgingMin() == null ? 0 : bdProductPo.getAgingMin();
                    Integer instalTestAging = instalMin + testMin + agingMin; // 组装测试老化时间累积
                    initSavePurchaseQuoteBVO(billProduceQuoteHPo.getPkProduceQuoteH(), instalTestAging, "否", "组装测试老化");
                }

                // 首单制具 报价
                if (RefConstract.YESORNO_YES_CHAR.equals(bdProductPo.getIsmakeTool())) {
                    // 钢网制具
                    if (RefConstract.STEEL_MESH_SOURCE_PARTY_B.equals(bdProductPo.getMakeSteelMesh())) {
                        initSavePurchaseQuoteBVO(billProduceQuoteHPo.getPkProduceQuoteH(), null, "是", "钢网制具");
                    }
                    // DIP制具
                    if (RefConstract.STEEL_MESH_SOURCE_PARTY_B.equals(bdProductPo.getMakeDip())) {
                        initSavePurchaseQuoteBVO(billProduceQuoteHPo.getPkProduceQuoteH(), null, "是", "DIP制具");
                    }
                    // 测试制具
                    if (RefConstract.STEEL_MESH_SOURCE_PARTY_B.equals(bdProductPo.getMakeTest())) {
                        initSavePurchaseQuoteBVO(billProduceQuoteHPo.getPkProduceQuoteH(), null, "是", "测试制具");
                    }
                    // 三防制具
                    if (RefConstract.STEEL_MESH_SOURCE_PARTY_B.equals(bdProductPo.getMakeDefense())) {
                        initSavePurchaseQuoteBVO(billProduceQuoteHPo.getPkProduceQuoteH(), null, "是", "三防制具");
                    }
                    // 老化制具
                    if (RefConstract.STEEL_MESH_SOURCE_PARTY_B.equals(bdProductPo.getMakeAging())) {
                        initSavePurchaseQuoteBVO(billProduceQuoteHPo.getPkProduceQuoteH(), null, "是", "老化制具");
                    }
                }
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 初始化生产报价子表数据并保存
     * @param masterId
     * @param param
     * @param isModel
     * @param type
     * @throws GlobalException
     */
    private void initSavePurchaseQuoteBVO(Integer masterId, Integer param, String isModel, String type) throws GlobalException {
        if (masterId != null && StringUtils.isNotEmpty(isModel) && StringUtils.isNotEmpty(type)) {
            try {
                BillProduceQuoteBPo quoteBPo = new BillProduceQuoteBPo();
                quoteBPo.setPkProduceQuoteH(masterId);
                quoteBPo.setIsmodel(isModel);
                quoteBPo.setParam(param);
                quoteBPo.setType(type);
                quoteBPo.setDr(0);
                quoteBPo.setTs(DateUtils.getKnowTsStr());
                billProduceQuoteBMapper.insertSelective(quoteBPo);
            } catch (Exception e) {
                throw new GlobalException(e.getMessage());
            }
        }
    }

    /**
     * 认领工艺审核任务
     * @param userid
     * @param pkProduct
     * @param taskId
     * @return
     * @throws GlobalException
     */
    public AjaxJson claimTecaudit(Integer userid, Integer pkProduct, String taskId) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (userid != null && pkProduct != null && StringUtils.isNotEmpty(taskId)) {
                bdProductMapperExpand.claimTecaudit(userid, pkProduct, taskId);
            }
        }catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }
}
