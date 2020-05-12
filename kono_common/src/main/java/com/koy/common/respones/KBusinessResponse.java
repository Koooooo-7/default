package com.koy.common.respones;

/**
 * @Description
 * @Auther Koy  https://github.com/Koooooo-7
 * @Date 2020/05/12
 */
public class KBusinessResponse<T> {

    private int code;
    private String msg;
    private T data;

    private KBusinessResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> KBusinessResponse<T> success(T data) {
        return new KBusinessResponse<T>(0, null, data);
    }

    public static <T> KBusinessResponse<T> failed(int code, String msg) {
        return new KBusinessResponse<T>(code, msg, null);
    }
}
