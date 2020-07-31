package com.zbnetwork.blog.app.utils.validatecode.imagecode;

import com.zbnetwork.blog.app.utils.validatecode.ValidateCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;

/**
 * @author 13496
 * ImageCode:
 *   图片验证码类
 *   继承了验证码类(ValidateCode)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

}
