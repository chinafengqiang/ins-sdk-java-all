package cn.inspiry.api.service.device.response;

import cn.ins.api.InsResponse;

/**
 * @author JQ.Feng
 * @title: DeviceOrderInfo
 * @desc: /open/device/orderInfo response
 * @date 2024/9/26 10:54
 */
public class DeviceOrderInfoResponse extends InsResponse {
    public DeviceOrderInfoResponse() {
    }

    public DeviceOrderInfoResponse(String requestId, int code, String msg) {
        super(requestId, code, msg);
    }

    private String ppTradeNo;

    private String totalFee;

    private String goodsDesc;

    public String getPpTradeNo() {
        return ppTradeNo;
    }

    public void setPpTradeNo(String ppTradeNo) {
        this.ppTradeNo = ppTradeNo;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }
}
