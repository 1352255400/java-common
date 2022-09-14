package com.mantulife.common.utils.captcha;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * author W_wang
 * GatewayConfig 生成验证码
 * email 1352255400@qq.com
 * date 2021-08-10 14:45:07
 */
@Slf4j
public class CaptchaUtil {

    private CaptchaUtil() {
    }

    /**
     * 图片的宽度
     */
    private static Integer width = 180;

    /**
     * 图片的高度
     */
    private static Integer height = 60;

    /**
     * 运算类型
     */
    private static final String OPERATORS = "+-*";


    /**
     * author W_wang
     * GatewayConfig 获取验证码（图片和运算随机）
     * email 1352255400@qq.com
     * date 2021-08-10 14:45:07
     * @return  return return
     */
    public static CaptchaVO getCaptcha() {
        CaptchaVO captchaVO = new CaptchaVO();
        // 图片验证码
        String code = RandomCodeUtil.genCode(6);
        captchaVO.setCode(code);
        captchaVO.setValue(code);
        // 运算验证码
        if (RandomUtils.nextInt() % 2 == 1) {
            captchaVO = getOperationCaptcha();
            code = captchaVO.getCode();
        }
        // 图片验证码
        ImgCodeUtil imgCodeUtil = new ImgCodeUtil(code, width, height);
        BufferedImage buffImg = imgCodeUtil.getBuffImg();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        String base64ImgCode = null;
        try {
            ImageIO.write(buffImg, "png", byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            base64ImgCode = Base64Utils.encodeToString(bytes);
        } catch (IOException var10) {
            log.error("生成随机字符串图形验证码失败");
        }
        captchaVO.setCode(base64ImgCode);
        return captchaVO;
    }

    // 运算验证码
    public static CaptchaVO getOperationCaptcha() {
        Integer firstNum = RandomUtils.nextInt() % 10 + 1;
        Integer secondNum = RandomUtils.nextInt() % 10 + 1;
        Integer validateCode = 0;
        char operation = RandomUtil.randomChar(OPERATORS);
        switch (operation) {
            case '*':
                validateCode = firstNum * secondNum;
                break;
            case '+':
                validateCode = firstNum + secondNum;
                break;
            case '-':
                if (firstNum < secondNum) {
                    Integer tmp = firstNum;
                    firstNum = secondNum;
                    secondNum = tmp;
                }
                validateCode = firstNum - secondNum;
                break;
            default:
                validateCode = 0;
                break;
        }
        String code = firstNum + "" + operation + secondNum + "" + "= ?";
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCode(code);
        captchaVO.setValue(validateCode.toString());
        return captchaVO;
    }

}
