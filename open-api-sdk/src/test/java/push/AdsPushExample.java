package push;

import cn.ins.api.*;
import cn.ins.api.exception.InsApiException;
import cn.inspiry.api.service.push.domain.*;
import cn.inspiry.api.service.push.request.AdsPushRequest;
import cn.inspiry.api.service.push.response.AdsPushResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JQ.Feng
 * @title: AdsPushExample
 * @desc: TODO
 * @date 2024/8/22 18:17
 */

/*
{"dn":"4330220500100021","id":"100568933","method":"config_control",
"msg":"{\"metadata\":{\"desired\":{},\"reported\":{},\"property\":{}},\"property\":{},
\"state\":{\"desired\":{\"ADVERTCONFIG\":{\"customContent\":\"{\\\"plays\\\":1,\\\"qr_code_show_time\\\":0,
\\\"landing_page\\\":\\\"\\\",\\\"url\\\":\\\"http://http://inspiry-jp.oss-ap-northeast-1.aliyuncs.com/7700plusRes/dydo6-idle.tar.gz?type=video\\\"}\"}},
\"reported\":{}}}","ts":1663898961621}



"BIZDEFINE": {
    "file": "{\"resources\":{\"vedioURL\":\"http://payfun-jp.oss-ap-northeast-1.aliyuncs.com/ideas/pack/1698821941061150146.tar.bz2\",\"audioURL\":\"\",\"pictureURL\":\"\"}}"
 },
 */
public class AdsPushExample {
    private static final String ServerUrl = "https://api.inspay.jp";
    private static final String AppKey = "5DdSACvDJgB1sbVu";
    private static final String Secret = "8xBxtQCybkwI4flzT9errEx5RAzleWje";
    private InsConfig config;
    private InsClient insClient;
    @Before
    public void init(){
        config = new InsConfig(ServerUrl,AppKey,Secret);
        insClient = new DefaultInsClient(config);
    }

    //根据地址中的type来区分是视频、音频和图片
    //视频 type = video  音频 type = audio 图片 type = png
    private InsRequest<AdsPushResponse> createRequest(){
        AdsPushRequest adsPushRequest = new AdsPushRequest();

        CustomContent content = new CustomContent();
        content.setPlays(1);
        content.setLandingPage("");
        content.setQrCodeShowTime(0);
        //content.setUrl("http://inspiry-jp.oss-ap-northeast-1.aliyuncs.com/7700baseRes/tal-mp4.tar.gz?type=video");
        content.setUrl("http://inspiry-jp.oss-ap-northeast-1.aliyuncs.com/7700baseRes/tal-audio.tar.gz?type=audio");

        List<AdsModel> adsModelList = new ArrayList<>();
        AdsModel adsModel = new AdsModel();
        adsModel.setScene(SceneType.CUSTOME_CONTENT);
        adsModel.setUrl(content);
        adsModelList.add(adsModel);

        AdsPushModel adsPushModel = new AdsPushModel();
        adsPushModel.setType(AdsType.PAY_ADS);
        adsPushModel.setAction(AdsAction.PUSH);
        adsPushModel.setAds(adsModelList);

        List<String> serialNums = new ArrayList<>();
        //serialNums.add("4330220100100393");
        //serialNums.add("4330220900100063");
        serialNums.add("4330220900100049");
        adsPushModel.setSerialNums(serialNums);

        adsPushRequest.setBizModel(adsPushModel);

        return adsPushRequest;
    }

    @Test
    public void push()throws InsApiException {
        InsRequest<AdsPushResponse> request = createRequest();
        AdsPushResponse response = insClient.execute(request);
        System.out.println(response);
    }
}
