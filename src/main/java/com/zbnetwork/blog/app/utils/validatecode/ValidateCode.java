package com.zbnetwork.blog.app.utils.validatecode;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author 13496
 */
@Data
@AllArgsConstructor
public class ValidateCode {
    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime;

    public ValidateCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code.toLowerCase();
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
