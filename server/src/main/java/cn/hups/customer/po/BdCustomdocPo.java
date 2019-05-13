package cn.hups.customer.po;

import cn.hups.customer.entity.BdCustomdoc;

/**
 * 客户人员信息PO
 */
public class BdCustomdocPo extends BdCustomdoc {

    // 设置默认联系人flag
    private String setdeflag;

    public String getSetdeflag() {
        return setdeflag;
    }

    public void setSetdeflag(String setdeflag) {
        this.setdeflag = setdeflag;
    }

}
