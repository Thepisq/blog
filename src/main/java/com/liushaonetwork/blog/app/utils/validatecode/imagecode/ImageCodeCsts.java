package com.liushaonetwork.blog.app.utils.validatecode.imagecode;

/**
 * @author 13496
 * ImageCodeCsts:
 *   图片验证码常量
 */
public class ImageCodeCsts {
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    /**
     * 图片宽度
     */
    public static final int WIDTH = 160;
    /**
     * 图片高度
     */
    public static final int HEIGHT = 40;
    /**
     * 验证码长度(位)
     */
    public static final int CODE_LENGTH = 4;
    /**
     * 验证码过期时间(秒)
     */
    public static final int EXPIRE_SECOND = 60;
}
