package cn.hups.salemage.entity;

import cn.hups.common.po.PagePoJo;

import java.io.Serializable;

public class BillSaleQuote extends PagePoJo implements Serializable {
    private Integer pkSaleQuote;

    private Integer pkCustomer;

    private Integer pkProduct;

    private Integer pkPurchaseQuote;

    private Integer pkProduceQuoteH;

    private Integer creator;

    private String creationtime;

    private String taskId;

    private Integer approver;

    private String approvetime;

    private Integer billState;

    private Integer dr;

    private String ts;

    private static final long serialVersionUID = 1L;

    public BillSaleQuote(Integer pkSaleQuote, Integer pkCustomer, Integer pkProduct, Integer pkPurchaseQuote, Integer pkProduceQuoteH, Integer creator, String creationtime, String taskId, Integer approver, String approvetime, Integer billState, Integer dr, String ts) {
        this.pkSaleQuote = pkSaleQuote;
        this.pkCustomer = pkCustomer;
        this.pkProduct = pkProduct;
        this.pkPurchaseQuote = pkPurchaseQuote;
        this.pkProduceQuoteH = pkProduceQuoteH;
        this.creator = creator;
        this.creationtime = creationtime;
        this.taskId = taskId;
        this.approver = approver;
        this.approvetime = approvetime;
        this.billState = billState;
        this.dr = dr;
        this.ts = ts;
    }

    public BillSaleQuote() {
        super();
    }

    public Integer getPkSaleQuote() {
        return pkSaleQuote;
    }

    public void setPkSaleQuote(Integer pkSaleQuote) {
        this.pkSaleQuote = pkSaleQuote;
    }

    public Integer getPkCustomer() {
        return pkCustomer;
    }

    public void setPkCustomer(Integer pkCustomer) {
        this.pkCustomer = pkCustomer;
    }

    public Integer getPkProduct() {
        return pkProduct;
    }

    public void setPkProduct(Integer pkProduct) {
        this.pkProduct = pkProduct;
    }

    public Integer getPkPurchaseQuote() {
        return pkPurchaseQuote;
    }

    public void setPkPurchaseQuote(Integer pkPurchaseQuote) {
        this.pkPurchaseQuote = pkPurchaseQuote;
    }

    public Integer getPkProduceQuoteH() {
        return pkProduceQuoteH;
    }

    public void setPkProduceQuoteH(Integer pkProduceQuoteH) {
        this.pkProduceQuoteH = pkProduceQuoteH;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime == null ? null : creationtime.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public Integer getApprover() {
        return approver;
    }

    public void setApprover(Integer approver) {
        this.approver = approver;
    }

    public String getApprovetime() {
        return approvetime;
    }

    public void setApprovetime(String approvetime) {
        this.approvetime = approvetime == null ? null : approvetime.trim();
    }

    public Integer getBillState() {
        return billState;
    }

    public void setBillState(Integer billState) {
        this.billState = billState;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts == null ? null : ts.trim();
    }
}