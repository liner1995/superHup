package cn.hups.flowproccess.po;

/**
 * 报价流程参数PO
 */
public class QuoteFlowParam extends FlowParamsPo {
    // 产品主键
    private Integer pkProduct;

    // 任务标题
    private String flowTitle;

    // 客户信息
    private Integer pkCustomer;

    // 人员信息
    private Integer pkPsndoc;

    public Integer getPkProduct() {
        return pkProduct;
    }

    public void setPkProduct(Integer pkProduct) {
        this.pkProduct = pkProduct;
    }

    public String getFlowTitle() {
        return flowTitle;
    }

    public void setFlowTitle(String flowTitle) {
        this.flowTitle = flowTitle;
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
}
