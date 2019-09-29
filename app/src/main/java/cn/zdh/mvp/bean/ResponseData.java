package cn.zdh.mvp.bean;

public class ResponseData {

    /**
     * result : null
     * reason : 当前可请求的次数不足
     * error_code : 10012
     * resultCode : 112
     */
    private String result;
    private String reason;
    private int error_code;
    private String resultCode;

    public void setResult(String result) {
        this.result = result;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }

    public int getError_code() {
        return error_code;
    }

    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "result='" + result + '\'' +
                ", reason='" + reason + '\'' +
                ", error_code=" + error_code +
                ", resultCode='" + resultCode + '\'' +
                '}';
    }
}
