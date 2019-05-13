package cn.hups.produce.po;

import cn.hups.produce.entity.BillProduceQuoteH;

import java.util.List;

/**
 * 生产报价主表拓展实体PO
 */
public class BillProduceQuoteHPo extends BillProduceQuoteH {

    // 创建人显示名称
    private String creatorShow;

    // 审批人显示名称
    private String approverShow;

    // 客户编号
    private String customerCode;

    // 产品编号
    private String productCode;

    // 产品名称
    private String productName;

    // 产品型号
    private String productModel;

    // 订单数量
    private Integer productOrderNumber;

    // 工序报价
    private List<BillProduceQuoteBPo> billProduceQuoteBPoList;

    // 制具报价
    private List<BillProduceQuoteBPo> modelQuoteBPoList;

    // 查询模板条件-客户简称
    private String customShortName;

    // 查询模板条件-创建日期范围
    private String creationdateBegin;
    private String creationdateEnd;

    public String getCreatorShow() {
        return creatorShow;
    }

    public void setCreatorShow(String creatorShow) {
        this.creatorShow = creatorShow;
    }

    public String getApproverShow() {
        return approverShow;
    }

    public void setApproverShow(String approverShow) {
        this.approverShow = approverShow;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public Integer getProductOrderNumber() {
        return productOrderNumber;
    }

    public void setProductOrderNumber(Integer productOrderNumber) {
        this.productOrderNumber = productOrderNumber;
    }

    public List<BillProduceQuoteBPo> getBillProduceQuoteBPoList() {
        return billProduceQuoteBPoList;
    }

    public void setBillProduceQuoteBPoList(List<BillProduceQuoteBPo> billProduceQuoteBPoList) {
        this.billProduceQuoteBPoList = billProduceQuoteBPoList;
    }

    public List<BillProduceQuoteBPo> getModelQuoteBPoList() {
        return modelQuoteBPoList;
    }

    public void setModelQuoteBPoList(List<BillProduceQuoteBPo> modelQuoteBPoList) {
        this.modelQuoteBPoList = modelQuoteBPoList;
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
