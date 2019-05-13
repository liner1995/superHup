package cn.hups.common.po;

/**
 * 分页参数管理POJO
 */
public class PagePoJo {

    // 当前页码
    private Integer page;

    // 一页多少条数据
    private Integer limit;

    // 主键集合
    private String pkArr;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getPkArr() {
        return pkArr;
    }

    public void setPkArr(String pkArr) {
        this.pkArr = pkArr;
    }
}
