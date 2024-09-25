package cn.inspiry.api.service.push.domain;

import cn.ins.api.internal.json.JSONWriter;
import cn.ins.api.internal.mapping.ApiField;

/**
 * @author JQ.Feng
 * @title: AdsModel
 * @desc: TODO
 * @date 2024/8/22 17:57
 */
public class AdsModel {

    @ApiField("scene")
    private String scene;
    @ApiField("url")
    private String url;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrl(CustomContent customContent){
        JSONWriter jsonWriter = new JSONWriter();
        String content = jsonWriter.write(customContent, true);
        this.url = content;
    }
}
