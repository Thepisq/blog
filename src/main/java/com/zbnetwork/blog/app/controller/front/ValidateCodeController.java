package com.zbnetwork.blog.app.controller.front;

import com.zbnetwork.blog.app.utils.validatecode.ValidateCode;
import com.zbnetwork.blog.app.utils.validatecode.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zbnetwork.blog.app.utils.validatecode.ValidateCodeCsts.SESSION_KEY;

/**
 * @author 13496
 */
@RestController
public class ValidateCodeController {
    private final SessionStrategy sessionStrategy;

    @Autowired
    public ValidateCodeController(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    @GetMapping("/vCode/image")
    public void createCodeImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ValidateCodeGenerator codeGenerator = new ValidateCodeGenerator(new ServletWebRequest(request));
        ValidateCode validateCode = codeGenerator.generate();
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, validateCode);
        ImageIO.write(validateCode.getImage(), "JPG", response.getOutputStream());
    }
}
