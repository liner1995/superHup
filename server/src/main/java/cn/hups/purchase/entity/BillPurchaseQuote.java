package cn.hups.purchase.entity;

import cn.hups.common.po.PagePoJo;

import java.io.Serializable;

public class BillPurchaseQuote extends PagePoJo implements Serializable {
    private Integer pkPurchaseQuote;

    private String contractno;

    private Integer pkCustomer;

    private Integer pkPsndoc;

    private Integer pkProduct;

    private Integer purchasePrice;

    private Integer purchaseTotal;

    private String quoteEndDate;

    private String fileName;

    private String fileFullname;

    private String taskId;

    private Integer billState;

    private Integer approver;

    private String approvetime;

    private Integer dr;

    private String ts;

    private Integer purchasePriceSale;

    private Integer purchaseTotalSale;

    private static final long serialVersionUID = 1L;

    public BillPurchaseQuote(Integer pkPurchaseQuote, String contractno, Integer pkCustomer, Integer pkPsndoc, Integer pkProduct, Integer purchasePrice, Integer purchaseTotal, String quoteEndDate, String fileName, String fileFullname, String taskId, Integer billState, Integer approver, String approvetime, Integer dr, String ts, Integer purchasePriceSale, Integer purchaseTotalSale) {
        this.pkPurchaseQuote = pkPurchaseQuote;
        this.contractno = contractno;
        this.pkCustomer = pkCustomer;
        this.pkPsndoc = pkPsndoc;
        this.pkProduct = pkProduct;
        this.purchasePrice = purchasePrice;
        this.purchaseTotal = purchaseTotal;
        this.quoteEndDate = quoteEndDate;
        this.fileName = fileName;
        this.fileFullname = fileFullname;
        this.taskId = taskId;
        this.billState = billState;
        this.approver = approver;
        this.approvetime = approvetime;
        this.dr = dr;
        this.ts = ts;
        this.purchasePriceSale = purchasePriceSale;
        this.purchaseTotalSale = purchaseTotalSale;
    }

    public BillPurchaseQuote() {
        super();
    }

    public Integer getPkPurchaseQuote() {
        return pkPurchaseQuote;
    }

    public void setPkPurchaseQuote(Integer pkPurchaseQuote) {
        this.pkPurchaseQuote = pkPurchaseQuote;
    }

    public String getContractno() {
        return contractno;
    }

    public void setContractno(String contractno) {
        this.contractno = contractno == null ? null : contractno.trim();
    }

    public Integer getPkCustomer() {
        return pkCustomer;
    }

    public void setPkCustomer(Integer pkCustomer) {
        this.pkCustomer = pkCustomer;
    }

    public Integer getPkPsndoc() {
        return pkPsndoc;
    }

    public void setPkPsndoc(Integer pkPsndoc) {
        this.pkPsndoc = pkPsndoc;
    }

    public Integer getPkProduct() {
        return pkProduct;
    }

    public void setPkProduct(Integer pkProduct) {
        this.pkProduct = pkProduct;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getPurchaseTotal() {
        return purchaseTotal;
    }

    public void setPurchaseTotal(Integer purchaseTotal) {
        this.purchaseTotal = purchaseTotal;
    }

    public String getQuoteEndDate() {
        return quoteEndDate;
    }

    public void setQuoteEndDate(String quoteEndDate) {
        this.quoteEndDate = quoteEndDate == null ? null : quoteEndDate.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileFullname() {
        return fileFullname;
    }

    public void setFileFullname(String fileFullname) {
        this.fileFullname = fileFullname == null ? null : fileFullname.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public Integer getBillState() {
        return billState;
    }

    public void setBillState(Integer billState) {
        this.billState = billState;
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

    public Integer getPurchasePriceSale() {
        return purchasePriceSale;
    }

    public void setPurchasePriceSale(Integer purchasePriceSale) {
        this.purchasePriceSale = purchasePriceSale;
    }

    public Integer getPurchaseTotalSale() {
        return purchaseTotalSale;
    }

    public void setPurchaseTotalSale(Integer purchaseTotalSale) {
        this.purchaseTotalSale = purchaseTotalSale;
    }
}