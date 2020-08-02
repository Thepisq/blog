package com.liushaonetwork.blog.app.controller.front;

import com.liushaonetwork.blog.app.utils.validatecode.imagecode.ImageCode;
import com.liushaonetwork.blog.app.utils.validatecode.imagecode.ImageCodeGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.liushaonetwork.blog.app.utils.validatecode.imagecode.ImageCodeCsts.SESSION_KEY;

/**
 * @author 13496
 */
@RestController
public class ValidateCodeController {

    @GetMapping("/vCode/image")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator(new ServletWebRequest(request));
        ImageCode ImageCode = codeGenerator.generate();
        request.getSession().setAttribute(SESSION_KEY, ImageCode);
        ImageIO.write(ImageCode.getImage(), "JPG", response.getOutputStream());
    }

    @GetMapping("/vCode/get")
    public ResponseEntity<?> getCurrentCode(HttpServletRequest request) {
        return ResponseEntity.ok("Code: " + ((ImageCode) request.getSession().getAttribute(SESSION_KEY)).getCode());
    }
}
