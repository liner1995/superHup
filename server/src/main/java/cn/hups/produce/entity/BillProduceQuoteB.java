package cn.hups.produce.entity;

import cn.hups.common.po.PagePoJo;

import java.io.Serializable;

public class BillProduceQuoteB extends PagePoJo implements Serializable {
    private Integer pkProduceQuoteB;

    private Integer pkProduceQuoteH;

    private String ismodel;

    private String type;

    private Integer param;

    private Integer unitPriceOut;

    private Integer unitTotalPriceOut;

    private Integer unitTotalOut;

    private Integer dr;

    private String ts;

    private Integer unitPriceSale;

    private Integer unitTotalPriceSale;

    private Integer unitTotalSale;

    private static final long serialVersionUID = 1L;

    public BillProduceQuoteB(Integer pkProduceQuoteB, Integer pkProduceQuoteH, String ismodel, String type, Integer param, Integer unitPriceOut, Integer unitTotalPriceOut, Integer unitTotalOut, Integer dr, String ts, Integer unitPriceSale, Integer unitTotalPriceSale, Integer unitTotalSale) {
        this.pkProduceQuoteB = pkProduceQuoteB;
        this.pkProduceQuoteH = pkProduceQuoteH;
        this.ismodel = ismodel;
        this.type = type;
        this.param = param;
        this.unitPriceOut = unitPriceOut;
        this.unitTotalPriceOut = unitTotalPriceOut;
        this.unitTotalOut = unitTotalOut;
        this.dr = dr;
        this.ts = ts;
        this.unitPriceSale = unitPriceSale;
        this.unitTotalPriceSale = unitTotalPriceSale;
        this.unitTotalSale = unitTotalSale;
    }

    public BillProduceQuoteB() {
        super();
    }

    public Integer getPkProduceQuoteB() {
        return pkProduceQuoteB;
    }

    public void setPkProduceQuoteB(Integer pkProduceQuoteB) {
        this.pkProduceQuoteB = pkProduceQuoteB;
    }

    public Integer getPkProduceQuoteH() {
        return pkProduceQuoteH;
    }

    public void setPkProduceQuoteH(Integer pkProduceQuoteH) {
        this.pkProduceQuoteH = pkProduceQuoteH;
    }

    public String getIsmodel() {
        return ismodel;
    }

    public void setIsmodel(String ismodel) {
        this.ismodel = ismodel == null ? null : ismodel.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getParam() {
        return param;
    }

    public void setParam(Integer param) {
        this.param = param;
    }

    public Integer getUnitPriceOut() {
        return unitPriceOut;
    }

    public void setUnitPriceOut(Integer unitPriceOut) {
        this.unitPriceOut = unitPriceOut;
    }

    public Integer getUnitTotalPriceOut() {
        return unitTotalPriceOut;
    }

    public void setUnitTotalPriceOut(Integer unitTotalPriceOut) {
        this.unitTotalPriceOut = unitTotalPriceOut;
    }

    public Integer getUnitTotalOut() {
        return unitTotalOut;
    }

    public void setUnitTotalOut(Integer unitTotalOut) {
        this.unitTotalOut = unitTotalOut;
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

    public Integer getUnitPriceSale() {
        return unitPriceSale;
    }

    public void setUnitPriceSale(Integer unitPriceSale) {
        this.unitPriceSale = unitPriceSale;
    }

    public Integer getUnitTotalPriceSale() {
        return unitTotalPriceSale;
    }

    public void setUnitTotalPriceSale(Integer unitTotalPriceSale) {
        this.unitTotalPriceSale = unitTotalPriceSale;
    }

    public Integer getUnitTotalSale() {
        return unitTotalSale;
    }

    public void setUnitTotalSale(Integer unitTotalSale) {
        this.unitTotalSale = unitTotalSale;
    }
}