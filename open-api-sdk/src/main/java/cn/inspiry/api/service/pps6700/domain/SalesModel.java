package cn.inspiry.api.service.pps6700.domain;

/**
 * @author JQ.Feng
 * @title: SalesModel
 * @desc: TODO
 * @date 2024/6/11 09:36
 */
public class SalesModel {
    private Integer paymentMethod;
    private Integer paymentResult;
    private Integer amount;
    private String dealNumber;

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getPaymentResult() {
        return paymentResult;
    }

    public void setPaymentResult(Integer paymentResult) {
        this.paymentResult = paymentResult;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDealNumber() {
        return dealNumber;
    }

    public void setDealNumber(String dealNumber) {
        this.dealNumber = dealNumber;
    }
}
