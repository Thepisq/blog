package com.liushaonetwork.blog.app.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 13496
 */
public class ResultUtil implements Serializable {
    private static final long serialVersionUID = -5027048187176307508L;

    public static Map<String, Object> of(String status, Object msg) {
        HashMap<String, Object> result = new HashMap<>(8);
        result.put("status", status);
        result.put("msg", msg);
        return result;
    }

    public static Map<String, Object> of(String status) {
        return of(status, null);
    }

    public static Map<String, Object> success(Object msg) {
        HashMap<String, Object> result = new HashMap<>(8);
        result.put("status", "success");
        result.put("msg", msg);
        return result;
    }

    public static Map<String, Object> success() {
        return success(null);
    }

    public static Map<String, Object> fail(Object msg) {
        HashMap<String, Object> result = new HashMap<>(8);
        result.put("status", "fail");
        result.put("msg", msg);
        return result;
    }

    public static Map<String, Object> fail() {
        return fail(null);
    }
}
