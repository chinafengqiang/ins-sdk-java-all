package cn.inspiry.api.service.pps6700.domain;

import cn.ins.api.InsObject;

/**
 * @author JQ.Feng
 * @title: DataResModel
 * @desc: TODO
 * @date 2024/6/11 09:34
 */
public class DataResModel extends InsObject {
    private String serialNumber;
    private String operatorCode;
    private String storeCode;
    private Integer dataType;
    private String occurredAt;
    private SalesModel sales;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(String occurredAt) {
        this.occurredAt = occurredAt;
    }

    public SalesModel getSales() {
        return sales;
    }

    public void setSales(SalesModel sales) {
        this.sales = sales;
    }
}
