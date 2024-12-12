package cn.inspiry.api.service.speaker.domain;

import cn.ins.api.InsObject;
import cn.ins.api.internal.mapping.ApiField;

/**
 * @author JQ.Feng
 * @title: NotifyModel
 * @desc: NotifyModel
 * @date 2024/12/12 16:28
 */
public class NotifyModel extends InsObject {
    @ApiField("type")
    private Integer type;
    @ApiField("deviceName")
    private String deviceName;
    @ApiField("channel")
    private Integer channel;
    @ApiField("amount")
    private Long amount;
    @ApiField("transNo")
    private String transNo;
    @ApiField("orgKey")
    private String orgKey;
    @ApiField("merchantId")
    private String merchantId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getOrgKey() {
        return orgKey;
    }

    public void setOrgKey(String orgKey) {
        this.orgKey = orgKey;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
