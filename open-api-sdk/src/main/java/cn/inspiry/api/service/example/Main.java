package cn.inspiry.api.service.example;

import cn.ins.api.DefaultInsClient;
import cn.ins.api.InsClient;
import cn.ins.api.InsConfig;
import cn.inspiry.api.service.pps6700.domain.DataQueryModel;
import cn.inspiry.api.service.pps6700.request.DataQueryRequest;
import cn.inspiry.api.service.pps6700.response.DataQueryResponse;

/**
 * @author JQ.Feng
 * @title: Main
 * @desc: 示例
 * @date 2024/6/13 19:59
 */
public class Main {
    private static final String ServerUrl = "https://xxx.xxx.xx";
    private static final String AppKey = "xxx";
    private static final String Secret = "xxx";

    public static void main(String[] args) {
        try {
            // 1. 设置调用参数
            InsConfig config = new InsConfig(ServerUrl,AppKey,Secret);
            // 2. 创建AlipayClient实例
            InsClient insClient = new DefaultInsClient(config);
            // 3. 创建使用的Open API对应的Request请求对象
            DataQueryRequest request = getRequest();
            // 4. 发起请求并处理响应
            DataQueryResponse response = insClient.execute(request);
            if (response.getCode() == 0){
                System.out.println("调用成功。");
            }else {
                System.out.println("调用失败，原因：" + response.getMsg());
            }
        }catch (Exception e){
            System.out.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static DataQueryRequest getRequest(){
        // 初始化Request，并填充Model属性。实际调用时请替换为您想要使用的API对应的Request对象。
        DataQueryRequest request = new DataQueryRequest();
        DataQueryModel model = new DataQueryModel();
        model.setStartTime("2024-06-05 00:00:00");
        model.setEndTime("2024-06-05 23:57:00");
        model.setPageIndex(1);
        model.setPageSize(10);
        request.setBizModel(model);
        return request;
    }
}
