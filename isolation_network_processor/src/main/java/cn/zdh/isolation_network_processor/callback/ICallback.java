package cn.zdh.isolation_network_processor.callback;

/**
 * 顶层回调接口（扩展性 对应子类可以解析不同格式 比如Jason ，xml...）
 */
public interface ICallback {

    void onNetStart();

    void onNetEnd();

    void onNetSuccess(String result);

    void onNetFailure(String error);


}
