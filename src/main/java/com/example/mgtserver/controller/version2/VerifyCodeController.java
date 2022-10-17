package com.example.mgtserver.controller.version2;

import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.utils.VerifyCodeUtil;
import com.example.mgtserver.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Base64;
import java.util.Base64.Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 15:19
 */
@Controller("verifyCodeController")
@RequestMapping("/api/v2/verify")
public class VerifyCodeController {
    @RequestMapping("/getCode")
    public void getVerifyCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            BufferedImage image = new BufferedImage(VerifyCodeUtil.DEFAULT_WIDTH, VerifyCodeUtil.DEFAULT_HEIGHT, BufferedImage.TYPE_INT_BGR);
            String code = VerifyCodeUtil.draw(image);
            // hint 这里添加在了session里面
            request.getSession().setAttribute("verifyCode", code);
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取base64的验证码
     * @param request
     * @return
     */
    @RequestMapping("/getCode/base64")
    @ResponseBody
    public ResultVO<?> getVerifyCodeBase64(HttpServletRequest request) {
        BufferedImage image = new BufferedImage(VerifyCodeUtil.DEFAULT_WIDTH, VerifyCodeUtil.DEFAULT_HEIGHT, BufferedImage.TYPE_INT_BGR);
        String code = VerifyCodeUtil.draw(image);
        // hint 这里添加在了session里面
        request.getSession().setAttribute("verifyCode", code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = outputStream.toByteArray();
        String pngBase64 = Base64.getEncoder().encodeToString(bytes);
        // 去掉所有的空白字符
        pngBase64 = "data:image/png;base64," + pngBase64.replaceAll("\\s", "");
        return new ResultVO<>(ResponseCode.auto(pngBase64), pngBase64);
    }
}
