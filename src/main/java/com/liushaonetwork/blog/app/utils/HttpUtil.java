package com.liushaonetwork.blog.app.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @author 13496
 */
public class HttpUtil {

    public static String getIPAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        String result = "1: " + ip;

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        result += ", 2: " + ip;

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        result += ", 3: " + ip;

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            System.out.println("ip addr: " + ip);
            if ("127.0.0.1".equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        result += ", 4: " + ip;

        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        result += ", 5: " + ip;

        return ip + " \n " + result;
    }
}
