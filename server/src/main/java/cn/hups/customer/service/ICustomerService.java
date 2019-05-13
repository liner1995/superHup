package cn.hups.customer.service;

import cn.hups.common.exception.GlobalException;
import cn.hups.common.utils.AjaxJson;
import cn.hups.customer.po.BdCustomdocPo;
import cn.hups.customer.po.BdCustomerPo;
import cn.hups.customer.po.BdInvoicePo;
import cn.hups.customer.po.BdTakeoverPo;

/**
 * 客户信息服务类
 */
public interface ICustomerService {

    /**
     * 分页查询所有客户数据
     * @param bdCustomerPo
     * @return
     * @throws GlobalException
     */
    AjaxJson selectAllCustomerAsPage(BdCustomerPo bdCustomerPo) throws GlobalException;

    /**
     * 查询所有客户作为参照
     * @return
     * @throws GlobalException
     */
    AjaxJson selectAllCustomerAsRef() throws GlobalException;

    /**
     * 添加客户信息
     * @param bdCustomerPo
     * @return
     * @throws GlobalException
     */
    AjaxJson insertCustomer(BdCustomerPo bdCustomerPo) throws GlobalException;

    /**
     * 根据主键集合删除客户信息
     * @param bdCustomerPo
     * @return
     * @throws GlobalException
     */
    AjaxJson deleteCustomerByPks(BdCustomerPo bdCustomerPo) throws GlobalException;

    /**
     * 更新客户信息
     * @param bdCustomerPo
     * @return
     * @throws GlobalException
     */
    AjaxJson updateCustomer(BdCustomerPo bdCustomerPo) throws GlobalException;

    /**
     * 查询客户基本信息集合
     * @param bdCustomerPo
     * @return
     * @throws GlobalException
     */
    AjaxJson selectCustomInfo(BdCustomerPo bdCustomerPo) throws GlobalException;

    /**
     * 增加或修改收货信息
     * @param bdTakeoverPo
     * @return
     * @throws GlobalException
     */
    AjaxJson saveTakeOver(BdTakeoverPo bdTakeoverPo) throws GlobalException;

    /**
     * 保存或修改发票信息
     * @param bdInvoicePo
     * @return
     * @throws GlobalException
     */
    AjaxJson saveInvoice(BdInvoicePo bdInvoicePo) throws GlobalException;

    /**
     * 保存或修改客户人员信息
     * @param bdCustomdocPo
     * @return
     * @throws GlobalException
     */
    AjaxJson saveCustomdoc(BdCustomdocPo bdCustomdocPo) throws GlobalException;

    /**
     * 根据客户查询客户设置的默认联系人
     * @param bdCustomdocPo
     * @return
     * @throws GlobalException
     */
    AjaxJson selectCustomdocDefaultPsn(BdCustomdocPo bdCustomdocPo) throws GlobalException;

    /**
     * 查询所有客户人员档案信息作为参照
     * @param bdCustomdocPo
     * @return
     * @throws GlobalException
     */
    AjaxJson selectAllCustomdocAsRef(BdCustomdocPo bdCustomdocPo) throws GlobalException;
}
