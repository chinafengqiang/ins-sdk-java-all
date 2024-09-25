package device;

import cn.ins.api.*;
import cn.ins.api.exception.InsApiException;
import cn.inspiry.api.service.device.domain.DeviceStatusModel;
import cn.inspiry.api.service.device.request.DeviceStatusRequest;
import cn.inspiry.api.service.device.response.DeviceStatusResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * @author JQ.Feng
 * @title: DeviceStatus
 * @desc: 获取设备（Terminal）状态 对应API地址：/open/device/status
 * @date 2024/9/25 17:45
 */
public class DeviceStatusExample {
    private static final String ServerUrl = "https://api.inspay.jp";
    private static final String AppKey = "";
    private static final String Secret = "";
    private InsConfig config;
    private InsClient insClient;
    @Before
    public void init(){
        config = new InsConfig(ServerUrl,AppKey,Secret);
        insClient = new DefaultInsClient(config);
    }

    private InsRequest<DeviceStatusResponse> createRequest(){
        DeviceStatusRequest request = new DeviceStatusRequest();
        DeviceStatusModel model = new DeviceStatusModel();
        model.setSerialNum("");
        request.setBizModel(model);
        return request;
    }

    @Test
    public void deviceStatus()throws InsApiException {
        DeviceStatusResponse response = insClient.execute(createRequest());
        String status = response.getStatus();
        int code = response.getCode();
        System.out.println("code = "+code+" status = "+status);
    }
}
