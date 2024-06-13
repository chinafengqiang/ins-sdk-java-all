package cn.ins.api.exception;

/**
 * @author JQ.Feng
 * @title: InsApiException
 * @desc: api异常类
 * @date 2024/6/6 09:47
 */
public class InsApiException extends Exception{
    private static final long serialVersionUID = -238091758285157331L;

    private int code;
    private String msg;

    public InsApiException() {
        super();
    }

    public InsApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsApiException(InsApiErrorEnum messageEnum, Throwable cause) {
        super(messageEnum.getErrMsg(), cause);
    }

    public InsApiException(String message) {
        super(message);
    }

    public InsApiException(InsApiErrorEnum messageEnum) {
        super(messageEnum.getErrMsg());
    }

    public InsApiException(Throwable cause) {
        super(cause);
    }

    public InsApiException(int code, String msg) {
        super(code + ":" + msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
