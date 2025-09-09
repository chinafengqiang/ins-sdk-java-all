欢迎使用 SDK for Java 。

SDK for Java让您不用复杂编程即可访开放平台开放的各项能力，SDK可以自动帮您满足能力调用过程中所需的证书校验、加签、验签、发送HTTP请求等非功能性要求。

这里向您介绍如何获取 SDK for Java 并开始调用。
如果您在使用 SDK for Java 的过程中遇到任何问题，欢迎在当前 GitHub [提交 Issues](https://github.com/chinafengqiang/ins-sdk-java-all/issues/new)。

## 环境要求
1.  SDK for Java 需要配合`JKD 1.5`或其以上版本。

2. 使用 Inspiry SDK for Java 之前 ，您需要先获取AppKey、Secret等。

3. 准备工作完成后，注意保存AppKey、Secret等信息，后续将作为使用SDK的输入。


## 安装依赖
推荐通过Maven来管理项目依赖，您只需在项目的`pom.xml`文件中声明如下依赖
```xml
<dependency>
    <groupId>cn.inspiry.sdk</groupId>
    <artifactId>open-api-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 快速使用
以下这段代码示例向您展示了使用Inspiry SDK for Java调用一个API的4个主要步骤：
1. 设置InsConfig调用参数,包括ServerUrl、AppKey、Secret。
2. 创建DefaultInsClient实例并初始化。
3. 创建API请求对象并设置Model参数。
4. 发起请求并处理响应或异常。

```java

import cn.ins.api.DefaultInsClient;
import cn.ins.api.InsClient;
import cn.ins.api.InsConfig;
import cn.inspiry.api.service.pps6700.domain.DataQueryModel;
import cn.inspiry.api.service.pps6700.request.DataQueryRequest;
import cn.inspiry.api.service.pps6700.response.DataQueryResponse;

public class Main {
    private static final String ServerUrl = "https://xxx";
    private static final String AppKey = "xxx";
    private static final String Secret = "xxx";

    public static void main(String[] args) {
        try {
            // 1. 设置调用参数,实际调用时请替换为Inspiry提供的参数
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
```

## 快速排查
当response.getCode()返回非0值时，您可通过返回的msg信息定位异常原因，或把返回的requestId提供给Inspiry技术支持用于问题的快速排查

## 文档
[SDK文档首页](https://)


## 问题
如果您在使用 SDK for Java 的过程中遇到任何问题，欢迎在当前 GitHub [提交 Issues](https://github.com/chinafengqiang/ins-sdk-java-all/issues/new)。

## 变更日志
每个版本的详细更改记录在[变更日志](./CHANGELOG)中。

## 相关

* [开放平台文档中心](https://)


## 许可证
[Apache License ](http://www.apache.org/licenses/)


## 贡献
我们欢迎并感谢社区的贡献。
