package cn.hups.customer.po;

import cn.hups.customer.entity.BdCustomer;

/**
 * 客户信息拓展PO实体
 */
public class BdCustomerPo extends BdCustomer {

    // 创建人显示名称
    private String creatorShow;

    public String getCreatorShow() {
        return creatorShow;
    }

    public void setCreatorShow(String creatorShow) {
        this.creatorShow = creatorShow;
    }
}
