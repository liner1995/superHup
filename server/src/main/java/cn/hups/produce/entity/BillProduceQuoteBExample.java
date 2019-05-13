package cn.hups.produce.entity;

import java.util.ArrayList;
import java.util.List;

public class BillProduceQuoteBExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BillProduceQuoteBExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPkProduceQuoteBIsNull() {
            addCriterion("pk_produce_quote_b is null");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteBIsNotNull() {
            addCriterion("pk_produce_quote_b is not null");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteBEqualTo(Integer value) {
            addCriterion("pk_produce_quote_b =", value, "pkProduceQuoteB");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteBNotEqualTo(Integer value) {
            addCriterion("pk_produce_quote_b <>", value, "pkProduceQuoteB");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteBGreaterThan(Integer value) {
            addCriterion("pk_produce_quote_b >", value, "pkProduceQuoteB");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteBGreaterThanOrEqualTo(Integer value) {
            addCriterion("pk_produce_quote_b >=", value, "pkProduceQuoteB");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteBLessThan(Integer value) {
            addCriterion("pk_produce_quote_b <", value, "pkProduceQuoteB");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteBLessThanOrEqualTo(Integer value) {
            addCriterion("pk_produce_quote_b <=", value, "pkProduceQuoteB");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteBIn(List<Integer> values) {
            addCriterion("pk_produce_quote_b in", values, "pkProduceQuoteB");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteBNotIn(List<Integer> values) {
            addCriterion("pk_produce_quote_b not in", values, "pkProduceQuoteB");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteBBetween(Integer value1, Integer value2) {
            addCriterion("pk_produce_quote_b between", value1, value2, "pkProduceQuoteB");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteBNotBetween(Integer value1, Integer value2) {
            addCriterion("pk_produce_quote_b not between", value1, value2, "pkProduceQuoteB");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteHIsNull() {
            addCriterion("pk_produce_quote_h is null");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteHIsNotNull() {
            addCriterion("pk_produce_quote_h is not null");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteHEqualTo(Integer value) {
            addCriterion("pk_produce_quote_h =", value, "pkProduceQuoteH");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteHNotEqualTo(Integer value) {
            addCriterion("pk_produce_quote_h <>", value, "pkProduceQuoteH");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteHGreaterThan(Integer value) {
            addCriterion("pk_produce_quote_h >", value, "pkProduceQuoteH");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteHGreaterThanOrEqualTo(Integer value) {
            addCriterion("pk_produce_quote_h >=", value, "pkProduceQuoteH");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteHLessThan(Integer value) {
            addCriterion("pk_produce_quote_h <", value, "pkProduceQuoteH");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteHLessThanOrEqualTo(Integer value) {
            addCriterion("pk_produce_quote_h <=", value, "pkProduceQuoteH");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteHIn(List<Integer> values) {
            addCriterion("pk_produce_quote_h in", values, "pkProduceQuoteH");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteHNotIn(List<Integer> values) {
            addCriterion("pk_produce_quote_h not in", values, "pkProduceQuoteH");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteHBetween(Integer value1, Integer value2) {
            addCriterion("pk_produce_quote_h between", value1, value2, "pkProduceQuoteH");
            return (Criteria) this;
        }

        public Criteria andPkProduceQuoteHNotBetween(Integer value1, Integer value2) {
            addCriterion("pk_produce_quote_h not between", value1, value2, "pkProduceQuoteH");
            return (Criteria) this;
        }

        public Criteria andIsmodelIsNull() {
            addCriterion("ismodel is null");
            return (Criteria) this;
        }

        public Criteria andIsmodelIsNotNull() {
            addCriterion("ismodel is not null");
            return (Criteria) this;
        }

        public Criteria andIsmodelEqualTo(String value) {
            addCriterion("ismodel =", value, "ismodel");
            return (Criteria) this;
        }

        public Criteria andIsmodelNotEqualTo(String value) {
            addCriterion("ismodel <>", value, "ismodel");
            return (Criteria) this;
        }

        public Criteria andIsmodelGreaterThan(String value) {
            addCriterion("ismodel >", value, "ismodel");
            return (Criteria) this;
        }

        public Criteria andIsmodelGreaterThanOrEqualTo(String value) {
            addCriterion("ismodel >=", value, "ismodel");
            return (Criteria) this;
        }

        public Criteria andIsmodelLessThan(String value) {
            addCriterion("ismodel <", value, "ismodel");
            return (Criteria) this;
        }

        public Criteria andIsmodelLessThanOrEqualTo(String value) {
            addCriterion("ismodel <=", value, "ismodel");
            return (Criteria) this;
        }

        public Criteria andIsmodelLike(String value) {
            addCriterion("ismodel like", value, "ismodel");
            return (Criteria) this;
        }

        public Criteria andIsmodelNotLike(String value) {
            addCriterion("ismodel not like", value, "ismodel");
            return (Criteria) this;
        }

        public Criteria andIsmodelIn(List<String> values) {
            addCriterion("ismodel in", values, "ismodel");
            return (Criteria) this;
        }

        public Criteria andIsmodelNotIn(List<String> values) {
            addCriterion("ismodel not in", values, "ismodel");
            return (Criteria) this;
        }

        public Criteria andIsmodelBetween(String value1, String value2) {
            addCriterion("ismodel between", value1, value2, "ismodel");
            return (Criteria) this;
        }

        public Criteria andIsmodelNotBetween(String value1, String value2) {
            addCriterion("ismodel not between", value1, value2, "ismodel");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andParamIsNull() {
            addCriterion("param is null");
            return (Criteria) this;
        }

        public Criteria andParamIsNotNull() {
            addCriterion("param is not null");
            return (Criteria) this;
        }

        public Criteria andParamEqualTo(Integer value) {
            addCriterion("param =", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotEqualTo(Integer value) {
            addCriterion("param <>", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamGreaterThan(Integer value) {
            addCriterion("param >", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamGreaterThanOrEqualTo(Integer value) {
            addCriterion("param >=", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamLessThan(Integer value) {
            addCriterion("param <", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamLessThanOrEqualTo(Integer value) {
            addCriterion("param <=", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamIn(List<Integer> values) {
            addCriterion("param in", values, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotIn(List<Integer> values) {
            addCriterion("param not in", values, "param");
            return (Criteria) this;
        }

        public Criteria andParamBetween(Integer value1, Integer value2) {
            addCriterion("param between", value1, value2, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotBetween(Integer value1, Integer value2) {
            addCriterion("param not between", value1, value2, "param");
            return (Criteria) this;
        }

        public Criteria andUnitPriceOutIsNull() {
            addCriterion("unit_price_out is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceOutIsNotNull() {
            addCriterion("unit_price_out is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceOutEqualTo(Integer value) {
            addCriterion("unit_price_out =", value, "unitPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitPriceOutNotEqualTo(Integer value) {
            addCriterion("unit_price_out <>", value, "unitPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitPriceOutGreaterThan(Integer value) {
            addCriterion("unit_price_out >", value, "unitPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitPriceOutGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_price_out >=", value, "unitPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitPriceOutLessThan(Integer value) {
            addCriterion("unit_price_out <", value, "unitPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitPriceOutLessThanOrEqualTo(Integer value) {
            addCriterion("unit_price_out <=", value, "unitPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitPriceOutIn(List<Integer> values) {
            addCriterion("unit_price_out in", values, "unitPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitPriceOutNotIn(List<Integer> values) {
            addCriterion("unit_price_out not in", values, "unitPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitPriceOutBetween(Integer value1, Integer value2) {
            addCriterion("unit_price_out between", value1, value2, "unitPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitPriceOutNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_price_out not between", value1, value2, "unitPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceOutIsNull() {
            addCriterion("unit_total_price_out is null");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceOutIsNotNull() {
            addCriterion("unit_total_price_out is not null");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceOutEqualTo(Integer value) {
            addCriterion("unit_total_price_out =", value, "unitTotalPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceOutNotEqualTo(Integer value) {
            addCriterion("unit_total_price_out <>", value, "unitTotalPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceOutGreaterThan(Integer value) {
            addCriterion("unit_total_price_out >", value, "unitTotalPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceOutGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_total_price_out >=", value, "unitTotalPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceOutLessThan(Integer value) {
            addCriterion("unit_total_price_out <", value, "unitTotalPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceOutLessThanOrEqualTo(Integer value) {
            addCriterion("unit_total_price_out <=", value, "unitTotalPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceOutIn(List<Integer> values) {
            addCriterion("unit_total_price_out in", values, "unitTotalPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceOutNotIn(List<Integer> values) {
            addCriterion("unit_total_price_out not in", values, "unitTotalPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceOutBetween(Integer value1, Integer value2) {
            addCriterion("unit_total_price_out between", value1, value2, "unitTotalPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceOutNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_total_price_out not between", value1, value2, "unitTotalPriceOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalOutIsNull() {
            addCriterion("unit_total_out is null");
            return (Criteria) this;
        }

        public Criteria andUnitTotalOutIsNotNull() {
            addCriterion("unit_total_out is not null");
            return (Criteria) this;
        }

        public Criteria andUnitTotalOutEqualTo(Integer value) {
            addCriterion("unit_total_out =", value, "unitTotalOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalOutNotEqualTo(Integer value) {
            addCriterion("unit_total_out <>", value, "unitTotalOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalOutGreaterThan(Integer value) {
            addCriterion("unit_total_out >", value, "unitTotalOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalOutGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_total_out >=", value, "unitTotalOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalOutLessThan(Integer value) {
            addCriterion("unit_total_out <", value, "unitTotalOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalOutLessThanOrEqualTo(Integer value) {
            addCriterion("unit_total_out <=", value, "unitTotalOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalOutIn(List<Integer> values) {
            addCriterion("unit_total_out in", values, "unitTotalOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalOutNotIn(List<Integer> values) {
            addCriterion("unit_total_out not in", values, "unitTotalOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalOutBetween(Integer value1, Integer value2) {
            addCriterion("unit_total_out between", value1, value2, "unitTotalOut");
            return (Criteria) this;
        }

        public Criteria andUnitTotalOutNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_total_out not between", value1, value2, "unitTotalOut");
            return (Criteria) this;
        }

        public Criteria andDrIsNull() {
            addCriterion("dr is null");
            return (Criteria) this;
        }

        public Criteria andDrIsNotNull() {
            addCriterion("dr is not null");
            return (Criteria) this;
        }

        public Criteria andDrEqualTo(Integer value) {
            addCriterion("dr =", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotEqualTo(Integer value) {
            addCriterion("dr <>", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrGreaterThan(Integer value) {
            addCriterion("dr >", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrGreaterThanOrEqualTo(Integer value) {
            addCriterion("dr >=", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrLessThan(Integer value) {
            addCriterion("dr <", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrLessThanOrEqualTo(Integer value) {
            addCriterion("dr <=", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrIn(List<Integer> values) {
            addCriterion("dr in", values, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotIn(List<Integer> values) {
            addCriterion("dr not in", values, "dr");
            return (Criteria) this;
        }

        public Criteria andDrBetween(Integer value1, Integer value2) {
            addCriterion("dr between", value1, value2, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotBetween(Integer value1, Integer value2) {
            addCriterion("dr not between", value1, value2, "dr");
            return (Criteria) this;
        }

        public Criteria andTsIsNull() {
            addCriterion("ts is null");
            return (Criteria) this;
        }

        public Criteria andTsIsNotNull() {
            addCriterion("ts is not null");
            return (Criteria) this;
        }

        public Criteria andTsEqualTo(String value) {
            addCriterion("ts =", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotEqualTo(String value) {
            addCriterion("ts <>", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThan(String value) {
            addCriterion("ts >", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThanOrEqualTo(String value) {
            addCriterion("ts >=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThan(String value) {
            addCriterion("ts <", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThanOrEqualTo(String value) {
            addCriterion("ts <=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLike(String value) {
            addCriterion("ts like", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotLike(String value) {
            addCriterion("ts not like", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsIn(List<String> values) {
            addCriterion("ts in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotIn(List<String> values) {
            addCriterion("ts not in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsBetween(String value1, String value2) {
            addCriterion("ts between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotBetween(String value1, String value2) {
            addCriterion("ts not between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andUnitPriceSaleIsNull() {
            addCriterion("unit_price_sale is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceSaleIsNotNull() {
            addCriterion("unit_price_sale is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceSaleEqualTo(Integer value) {
            addCriterion("unit_price_sale =", value, "unitPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitPriceSaleNotEqualTo(Integer value) {
            addCriterion("unit_price_sale <>", value, "unitPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitPriceSaleGreaterThan(Integer value) {
            addCriterion("unit_price_sale >", value, "unitPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitPriceSaleGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_price_sale >=", value, "unitPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitPriceSaleLessThan(Integer value) {
            addCriterion("unit_price_sale <", value, "unitPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitPriceSaleLessThanOrEqualTo(Integer value) {
            addCriterion("unit_price_sale <=", value, "unitPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitPriceSaleIn(List<Integer> values) {
            addCriterion("unit_price_sale in", values, "unitPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitPriceSaleNotIn(List<Integer> values) {
            addCriterion("unit_price_sale not in", values, "unitPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitPriceSaleBetween(Integer value1, Integer value2) {
            addCriterion("unit_price_sale between", value1, value2, "unitPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitPriceSaleNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_price_sale not between", value1, value2, "unitPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceSaleIsNull() {
            addCriterion("unit_total_price_sale is null");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceSaleIsNotNull() {
            addCriterion("unit_total_price_sale is not null");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceSaleEqualTo(Integer value) {
            addCriterion("unit_total_price_sale =", value, "unitTotalPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceSaleNotEqualTo(Integer value) {
            addCriterion("unit_total_price_sale <>", value, "unitTotalPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceSaleGreaterThan(Integer value) {
            addCriterion("unit_total_price_sale >", value, "unitTotalPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceSaleGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_total_price_sale >=", value, "unitTotalPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceSaleLessThan(Integer value) {
            addCriterion("unit_total_price_sale <", value, "unitTotalPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceSaleLessThanOrEqualTo(Integer value) {
            addCriterion("unit_total_price_sale <=", value, "unitTotalPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceSaleIn(List<Integer> values) {
            addCriterion("unit_total_price_sale in", values, "unitTotalPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceSaleNotIn(List<Integer> values) {
            addCriterion("unit_total_price_sale not in", values, "unitTotalPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceSaleBetween(Integer value1, Integer value2) {
            addCriterion("unit_total_price_sale between", value1, value2, "unitTotalPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalPriceSaleNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_total_price_sale not between", value1, value2, "unitTotalPriceSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalSaleIsNull() {
            addCriterion("unit_total_sale is null");
            return (Criteria) this;
        }

        public Criteria andUnitTotalSaleIsNotNull() {
            addCriterion("unit_total_sale is not null");
            return (Criteria) this;
        }

        public Criteria andUnitTotalSaleEqualTo(Integer value) {
            addCriterion("unit_total_sale =", value, "unitTotalSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalSaleNotEqualTo(Integer value) {
            addCriterion("unit_total_sale <>", value, "unitTotalSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalSaleGreaterThan(Integer value) {
            addCriterion("unit_total_sale >", value, "unitTotalSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalSaleGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_total_sale >=", value, "unitTotalSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalSaleLessThan(Integer value) {
            addCriterion("unit_total_sale <", value, "unitTotalSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalSaleLessThanOrEqualTo(Integer value) {
            addCriterion("unit_total_sale <=", value, "unitTotalSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalSaleIn(List<Integer> values) {
            addCriterion("unit_total_sale in", values, "unitTotalSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalSaleNotIn(List<Integer> values) {
            addCriterion("unit_total_sale not in", values, "unitTotalSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalSaleBetween(Integer value1, Integer value2) {
            addCriterion("unit_total_sale between", value1, value2, "unitTotalSale");
            return (Criteria) this;
        }

        public Criteria andUnitTotalSaleNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_total_sale not between", value1, value2, "unitTotalSale");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}