package com.liushaonetwork.blog.app.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 13496
 *
 *  status: 分为"success"和"fail"
 *  statusCode: HTTP状态码
 *  msg: 消息
 *  obj: 对象
 */
public class ResultUtil implements Serializable {
    private static final long serialVersionUID = -5027048187176307508L;

    public static Map<String, Object> of(String status, Object msg, int statusCode, Object obj) {
        HashMap<String, Object> result = new HashMap<>(4);
        result.put("status", status);
        result.put("statusCode", statusCode);
        result.put("msg", msg);
        result.put("obj", obj);
        return result;
    }

    public static Map<String, Object> of(boolean bool, Object msg, int statusCode, Object obj) {
        if (bool) {
            return success(msg, statusCode, obj);
        } else {
            return fail(msg, statusCode, obj);
        }
    }

    public static Map<String, Object> success(Object msg, int statusCode, Object obj) {
        return of("success", msg, statusCode, obj);
    }

    public static Map<String, Object> success() {
        return success(null, 0, null);
    }

    public static Map<String, Object> success(Object msg) {
        return success(msg, 0, null);
    }

    public static Map<String, Object> success(Object msg, int statusCode) {
        return of("success", msg, statusCode, null);
    }

    public static Map<String, Object> successWith(Object obj) {
        return success(null, 0, obj);
    }

    public static Map<String, Object> fail(Object msg, int statusCode, Object obj) {
        return of("fail", msg, statusCode, obj);
    }

    public static Map<String, Object> fail() {
        return fail(null, 0, null);
    }

    public static Map<String, Object> fail(Object msg) {
        return fail(msg, 0, null);
    }

    public static Map<String, Object> fail(Object msg, int statusCode) {
        return fail(msg, statusCode, null);
    }
}
