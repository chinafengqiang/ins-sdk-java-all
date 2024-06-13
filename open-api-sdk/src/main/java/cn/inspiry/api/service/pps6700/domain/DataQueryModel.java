package cn.inspiry.api.service.pps6700.domain;

import cn.ins.api.InsObject;
import cn.ins.api.internal.mapping.ApiField;

/**
 * @author JQ.Feng
 * @title: DataQueryModal
 * @desc: 获取pps6700支付的信息操作
 * @date 2024/6/11 09:07
 */
public class DataQueryModel extends InsObject {
    @ApiField("startTime")
    private String startTime;
    @ApiField("endTime")
    private String endTime;
    @ApiField("dataType")
    private Integer dataType;
    @ApiField("serialNumber")
    private String serialNumber;
    @ApiField("pageIndex")
    private Integer pageIndex;
    @ApiField("pageSize")
    private Integer pageSize;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
