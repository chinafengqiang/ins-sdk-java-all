package cn.inspiry.api.service.push.domain;

import cn.ins.api.internal.mapping.ApiField;

/**
 * @author JQ.Feng
 * @title: CustomContent
 * @desc: 自定义广告对象
 * @date 2024/8/22 18:21
 */
public class CustomContent {

    @ApiField("plays")
    private Integer plays;

    @ApiField("qr_code_show_time")
    private Integer qrCodeShowTime;

    @ApiField("landing_page")
    private String landingPage;

    //根据地址中的type来区分是视频、音频和图片
    //视频 type = video  音频 type = audio 图片 type = png
    @ApiField("url")
    private String url;

    public Integer getPlays() {
        return plays;
    }

    public void setPlays(Integer plays) {
        this.plays = plays;
    }

    public Integer getQrCodeShowTime() {
        return qrCodeShowTime;
    }

    public void setQrCodeShowTime(Integer qrCodeShowTime) {
        this.qrCodeShowTime = qrCodeShowTime;
    }

    public String getLandingPage() {
        return landingPage;
    }

    public void setLandingPage(String landingPage) {
        this.landingPage = landingPage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
