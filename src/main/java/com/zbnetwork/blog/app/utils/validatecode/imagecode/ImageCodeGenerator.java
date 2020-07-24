package com.zbnetwork.blog.app.utils.validatecode.imagecode;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static com.zbnetwork.blog.app.utils.validatecode.imagecode.ImageCodeCsts.*;

/**
 * @author 13496
 */
public class ImageCodeGenerator {
    /**
     * 验证码中可以出现的字符,去除了部分易错的字符o0Oo0Ooo0o
     */
    private static final String CODE_WORDS = "abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLNMPQRSTUVWXYZ23456789";
    private int width;
    private int height;
    private int codeLength;

    public ImageCodeGenerator(ServletWebRequest request) {
        this.width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", WIDTH);
        this.height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", HEIGHT);
        this.codeLength = ServletRequestUtils.getIntParameter(request.getRequest(), "length", CODE_LENGTH);
    }

    public ImageCodeGenerator() {
        this.width = WIDTH;
        this.height = HEIGHT;
        this.codeLength = CODE_LENGTH;
    }

    public ImageCode generate() {
        //初始化图片
        BufferedImage codeImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = codeImg.getGraphics();
        g.setFont(new Font("Times New Roman", Font.ITALIC, width / height + width / 10 + height / 10 + 10));
        //画背景
        g.setColor(getRandColor(220, 250));
        g.fillRect(0, 0, width, height);
        //画线
        drawLines(g, 150);
        //画字
        String code = drawAndGenerateCodes(g, codeLength);
        //结束
        g.dispose();

        return new ImageCode(codeImg, code.toString(), EXPIRE_SECOND);
    }

    private String drawAndGenerateCodes(Graphics g, int codeLength) {
        Random random = new Random();
        StringBuilder codeStr = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            codeStr.append(RandomStringUtils.random(1, CODE_WORDS));
            g.setColor(new Color(20 + random.nextInt(100), 20 + random.nextInt(100), 20 + random.nextInt(100)));
            g.drawString(codeStr.substring(i, i + 1), width / 10 + i * width / codeLength, 2 + height / 10 + height / 2);
        }
        return codeStr.toString();
    }

    private void drawLines(Graphics g, int lineNum) {
        Random r = new Random();
        for (int i = 0; i < lineNum; i++) {
            g.setColor(getRandColor(150, 200));
            int x1 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int x2 = r.nextInt(height / 2 + width / 2);
            int y2 = r.nextInt(height / 2 + width / 2);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    //Positive Or Negative
    //返回1或-1,用来实现验证码的上下/左右浮动
    private static int NOP() {
        return ((Math.round(Math.random())) == 1 ? -1 : 1);
    }
}
