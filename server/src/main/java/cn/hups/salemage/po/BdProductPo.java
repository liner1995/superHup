package cn.hups.salemage.po;

import cn.hups.salemage.entity.BdProduct;

/**
 * 产品信息拓展实体PO
 */
public class BdProductPo extends BdProduct {

    // 客户名称
    private String customerName;

    // 审批人显示名称
    private String approverShow;

    // 创建人显示名称
    private String creatorShow;

    // 最后修改人显示名称
    private String modifierShow;

    // 查询模板条件-产品编号
    private String productCode;

    // 查询模板条件-客户简称
    private String customShortName;

    // 查询模板条件-创建日期范围
    private String creationdateBegin;
    private String creationdateEnd;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getApproverShow() {
        return approverShow;
    }

    public void setApproverShow(String approverShow) {
        this.approverShow = approverShow;
    }

    public String getCreatorShow() {
        return creatorShow;
    }

    public void setCreatorShow(String creatorShow) {
        this.creatorShow = creatorShow;
    }

    public String getModifierShow() {
        return modifierShow;
    }

    public void setModifierShow(String modifierShow) {
        this.modifierShow = modifierShow;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCustomShortName() {
        return customShortName;
    }

    public void setCustomShortName(String customShortName) {
        this.customShortName = customShortName;
    }

    public String getCreationdateBegin() {
        return creationdateBegin;
    }

    public void setCreationdateBegin(String creationdateBegin) {
        this.creationdateBegin = creationdateBegin;
    }

    public String getCreationdateEnd() {
        return creationdateEnd;
    }

    public void setCreationdateEnd(String creationdateEnd) {
        this.creationdateEnd = creationdateEnd;
    }
}
