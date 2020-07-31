package com.zbnetwork.blog.app.utils.validatecode;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 13496
 * ValidateCode:
 *   验证码的基本类
 *   目前继承的验证码类有: 图片验证码(ImageCode)
 */
@Data
public class ValidateCode {
    private String code;
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code.toLowerCase();
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code.toLowerCase();
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
