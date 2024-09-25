package cn.inspiry.api.service.push.domain;

import cn.ins.api.InsObject;
import cn.ins.api.internal.json.JSONWriter;
import cn.ins.api.internal.mapping.ApiField;

import java.util.List;

/**
 * @author JQ.Feng
 * @title: AdsPushModel
 * @desc: 对应接口open/isp/ads/push
 * @date 2024/8/22 17:46
 */
public class AdsPushModel extends InsObject {
    @ApiField("serialNums")
    private String serialNums;

    @ApiField("ads")
    private String ads;

    @ApiField("type")
    private Integer type;

    @ApiField("action")
    private Integer action;

    public String getSerialNums() {
        return serialNums;
    }

    public void setSerialNums(String serialNums) {
        this.serialNums = serialNums;
    }

    public String getAds() {
        return ads;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public void setAds(List<AdsModel> adsList) {
        JSONWriter writer = new JSONWriter();
        String adsArr = writer.write(adsList,true);
        this.ads = adsArr;
    }

    public void setSerialNums(List<String> serialList) {
        JSONWriter writer = new JSONWriter();
        String serials = writer.write(serialList,false);
        this.serialNums = serials;
    }
}
