package com.zbnetwork.blog.app.utils.validatecode.imagecode;

import com.zbnetwork.blog.app.utils.validatecode.ValidateCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;

/**
 * @author 13496
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
