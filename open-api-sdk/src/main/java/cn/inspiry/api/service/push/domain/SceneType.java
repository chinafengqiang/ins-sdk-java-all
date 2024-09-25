package cn.inspiry.api.service.push.domain;

/**
 * @author JQ.Feng
 * @title: SceneType
 * @desc: TODO
 * @date 2024/8/22 20:22
 */
public interface SceneType {
    //支付前
    String BEFORE_PAYMENT = "beforePayment";

    //支付中
    String IN_PAYMENT = "inPayment";

    //支付后头部
    String AFTER_PAYMENT_HEADER = "afterPaymentHeader";

    //支付后底部
    String AFTER_PAYMENT_BOTTOM = "afterPaymentBottom";

    //系统广告
    String SYSTEM_ADVERT = "systemAdvert";

    //主题
    String THEME = "theme";

    //语音包
    String VOICE = "voice";

    //自定义推送内容
    String CUSTOME_CONTENT = "customContent";

}
