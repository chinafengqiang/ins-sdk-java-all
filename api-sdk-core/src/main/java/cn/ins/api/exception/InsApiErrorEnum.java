package cn.ins.api.exception;

/**
 * @author JQ.Feng
 * @title: InsApiErrorEnum
 * @desc: TODO
 * @date 2024/6/6 09:50
 */
public enum InsApiErrorEnum {
    SIGN_ERROR(1000,"sign error");


    private int errCode;
    private String errMsg;

    InsApiErrorEnum(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
