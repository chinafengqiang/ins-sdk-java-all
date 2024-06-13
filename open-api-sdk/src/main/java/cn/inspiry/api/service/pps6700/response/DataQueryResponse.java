package cn.inspiry.api.service.pps6700.response;

import cn.ins.api.InsResponse;
import cn.inspiry.api.service.pps6700.domain.DataResModel;

import java.util.List;

/**
 * @author JQ.Feng
 * @title: DataQueryResponse
 * @desc: TODO
 * @date 2024/6/11 09:24
 */
public class DataQueryResponse extends InsResponse {
    public DataQueryResponse(String requestId, int code, String msg) {
        super(requestId, code, msg);
    }

    private Integer total;
    private Integer pageIndex;
    private Integer pageSize;
    private List<DataResModel> list;

    public DataQueryResponse(){
        super();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<DataResModel> getList() {
        return list;
    }

    public void setList(List<DataResModel> list) {
        this.list = list;
    }
}
