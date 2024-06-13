package pps6700;

import cn.ins.api.*;
import cn.ins.api.exception.InsApiException;
import cn.inspiry.api.service.pps6700.domain.DataQueryModel;
import cn.inspiry.api.service.pps6700.request.DataQueryRequest;
import cn.inspiry.api.service.pps6700.response.DataQueryResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * @author JQ.Feng
 * @title: DataQueryExample
 * @desc: TODO
 * @date 2024/6/11 10:00
 */
public class DataQueryExample {
    private static final String ServerUrl = "https://xxx.xxx.xx";
    private static final String AppKey = "xxx";
    private static final String Secret = "xxx";
    private InsConfig config;
    private InsClient insClient;
    @Before
    public void init(){
        config = new InsConfig(ServerUrl,AppKey,Secret);
        insClient = new DefaultInsClient(config);
    }

    private InsRequest<DataQueryResponse> createRequest() {
        DataQueryRequest request = new DataQueryRequest();
        DataQueryModel model = new DataQueryModel();
        model.setStartTime("2024-06-05 00:00:00");
        model.setEndTime("2024-06-05 23:57:00");
        model.setPageIndex(1);
        model.setPageSize(10);
        request.setBizModel(model);
        return request;
    }

    @Test
    public void query()throws InsApiException {
        InsRequest<DataQueryResponse> request = createRequest();
        DataQueryResponse response = insClient.execute(request);
        System.out.println(response.getTotal());
    }
}
