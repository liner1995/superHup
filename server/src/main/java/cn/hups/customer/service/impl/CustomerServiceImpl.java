package cn.hups.customer.service.impl;

import cn.hups.common.exception.GlobalException;
import cn.hups.common.po.Auth;
import cn.hups.common.po.ClientRefPo;
import cn.hups.common.utils.AjaxJson;
import cn.hups.common.utils.AuthUtil;
import cn.hups.common.utils.StringUtils;
import cn.hups.customer.dao.*;
import cn.hups.customer.entity.BdCustomdoc;
import cn.hups.customer.po.*;
import cn.hups.customer.service.ICustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * 客户信息服务实现类
 */
@Service("iCustomerService")
public class CustomerServiceImpl implements ICustomerService {

    @Resource
    private BdCustomerMapper bdCustomerMapper;

    @Resource
    private BdCustomerMapperExpand bdCustomerMapperExpand;

    @Resource
    private CustomInfoMapper customInfoMapper;

    @Resource
    private BdTakeoverMapper bdTakeoverMapper;

    @Resource
    private BdInvoiceMapper bdInvoiceMapper;

    @Resource
    private BdCustomdocMapper bdCustomdocMapper;

    /**
     * 分页查询所有客户数据
     * @param bdCustomerPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectAllCustomerAsPage(BdCustomerPo bdCustomerPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            Long countNum = bdCustomerMapperExpand.countData(bdCustomerPo);
            List<BdCustomerPo> resultList = bdCustomerMapperExpand.selectAllCustomerAsPage(bdCustomerPo);
            ajaxJson.setTotal(countNum);
            ajaxJson.setObj(resultList);
        }catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 查询所有客户作为参照
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectAllCustomerAsRef() throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            List<ClientRefPo> resultRefs = bdCustomerMapperExpand.selectAllCustomerAsRef();
            ajaxJson.setObj(resultRefs);
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 添加客户信息
     * @param bdCustomerPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson insertCustomer(BdCustomerPo bdCustomerPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdCustomerPo != null) {
                Auth auth = AuthUtil.getAuth();
                bdCustomerPo.setCreator(auth.getUserid());
                bdCustomerMapper.insertSelective(bdCustomerPo);
                ajaxJson.setPrimary(bdCustomerPo.getPkCustomer());
            }
        }catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 根据主键集合删除客户信息
     * @param bdCustomerPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson deleteCustomerByPks(BdCustomerPo bdCustomerPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdCustomerPo != null && StringUtils.isNotEmpty(bdCustomerPo.getPkArr())) {
                List<Integer> pkArr = StringUtils.idsParamToListInt(bdCustomerPo.getPkArr());
                bdCustomerMapperExpand.deleteCustomerByPks(pkArr);
            }
        }catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 更新客户信息
     * @param bdCustomerPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson updateCustomer(BdCustomerPo bdCustomerPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdCustomerPo != null && bdCustomerPo.getPkCustomer() != null) {
                bdCustomerMapper.updateByPrimaryKeySelective(bdCustomerPo);
            }
        }catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 查询客户基本信息集合
     * @param bdCustomerPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectCustomInfo(BdCustomerPo bdCustomerPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdCustomerPo != null && bdCustomerPo.getPkCustomer() != null) {
                Integer pkCustomer = bdCustomerPo.getPkCustomer();
                CustomerInfoPo customerInfoPo = customInfoMapper.selectPkCustomer(pkCustomer);
                customerInfoPo.setBdCustomerPo(customerInfoPo.getBdCustomerPo() == null ? new BdCustomerPo() : customerInfoPo.getBdCustomerPo());
                customerInfoPo.setBdInvoicePo(customerInfoPo.getBdInvoicePo() == null ? new BdInvoicePo() : customerInfoPo.getBdInvoicePo());
                ajaxJson.setObj(customerInfoPo);
            }
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 增加或修改收货信息
     * @param bdTakeoverPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson saveTakeOver(BdTakeoverPo bdTakeoverPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdTakeoverPo != null) {
                // 如果主键不为空执行更新
                if (bdTakeoverPo.getPkTakeover() != null) {
                    bdTakeoverMapper.updateByPrimaryKeySelective(bdTakeoverPo);
                } else { // 如果主键为空执行新增
                    bdTakeoverMapper.insertSelective(bdTakeoverPo);
                }

            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 保存或修改发票信息
     * @param bdInvoicePo
     * @return
     * @throws GlobalException
     */
    public AjaxJson saveInvoice(BdInvoicePo bdInvoicePo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdInvoicePo != null) {
                // 如果主键不为空执行更新
                if (bdInvoicePo.getPkInvoice() != null) {
                    bdInvoiceMapper.updateByPrimaryKeySelective(bdInvoicePo);
                } else {
                    bdInvoiceMapper.insertSelective(bdInvoicePo);
                }
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 保存或修改客户人员信息
     * @param bdCustomdocPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson saveCustomdoc(BdCustomdocPo bdCustomdocPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdCustomdocPo != null) {
                if ("是".equals(bdCustomdocPo.getSetdeflag())) {
                    Integer pkCustomdoc = bdCustomdocPo.getPkCustomdoc();
                    Integer pkCustomer = bdCustomdocPo.getPkCustomer();
                    String customtype = bdCustomdocPo.getCustomtype();
                    if (pkCustomdoc != null && pkCustomer != null && StringUtils.isNotEmpty(customtype)) {
                        customInfoMapper.updateAllCustomdocDefaultNo(pkCustomer, customtype);
                        // 更新默认联系人
                        BdCustomdocPo updateDefPo = new BdCustomdocPo();
                        updateDefPo.setPkCustomdoc(pkCustomdoc);
                        updateDefPo.setDefaultis("是");
                        bdCustomdocMapper.updateByPrimaryKeySelective(updateDefPo);
                    }
                } else {
                    // 如果主键不为空执行更新
                    if (bdCustomdocPo.getPkCustomdoc() != null) {
                        bdCustomdocMapper.updateByPrimaryKeySelective(bdCustomdocPo);
                    } else {
                        bdCustomdocMapper.insertSelective(bdCustomdocPo);
                    }
                }
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 根据客户查询客户设置的默认联系人
     * @param bdCustomdocPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectCustomdocDefaultPsn(BdCustomdocPo bdCustomdocPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdCustomdocPo != null && bdCustomdocPo.getPkCustomer() != null) {
                List<BdCustomdoc> resultList = customInfoMapper.selectCustomdocDefaultPsn(bdCustomdocPo.getPkCustomer());
                ajaxJson.setObj(resultList);
            }
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 查询所有客户人员档案信息作为参照
     * @param bdCustomdocPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectAllCustomdocAsRef(BdCustomdocPo bdCustomdocPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdCustomdocPo != null && bdCustomdocPo.getPkCustomer() != null) {
                List<ClientRefPo> resultRefs = customInfoMapper.selectAllCustomdocAsRef(bdCustomdocPo.getPkCustomer());
                ajaxJson.setObj(resultRefs);
            }
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }
}
