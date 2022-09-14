package com.mantulife.common.utils.captcha;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;


/**
 * author W_wang
 * GatewayConfig 生成图形验证码工具类
 * email 1352255400@qq.com
 * date 2021-08-10 14:45:07
 */
@Slf4j
public class ImgCodeUtil {

    /**
     * 图片的宽度
     */
    private Integer width = 120;

    /**
     * 图片的高度
     */
    private Integer height = 40;

    /**
     * 验证码干扰线条数
     */
    private Integer lineCount = 8;

    /**
     * 验证码code
     */
    private String validateCode = null;

    /**
     * 验证码图片Buffer
     */
    private BufferedImage buffImg = null;

    private String[] fontNames = {"Georgia", "Verdana", "Arial", "Tahoma",
            "Time News Roman", "Courier New", "Arial Black", "Quantzite"};

    /**
     *  构造方法
     * @param validateCode validateCode
     */
    public ImgCodeUtil(String validateCode) {
        this.validateCode = validateCode;
        this.createCode();
    }

    /**
     *  构造方法
     * @param validateCode validateCode
     * @param width width
     * @param height height
     */
    public ImgCodeUtil(String validateCode, int width, int height) {
        this.width = width;
        this.height = height;
        this.validateCode = validateCode;
        this.createCode();
    }

    /**
     * 生成验证码图片
     */
    private void createCode() {
        int x = 0;
        int fontHeight;
        int codeY = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int codeCount = validateCode.length();
        //每个字符的宽度
        x = width / codeCount;
        //字体的高度
        fontHeight = height - 1;
        codeY = height - 4;

        // 图像buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        // 生成随机数
        Random random = new Random();
        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        //图片划线
        for (int i = 0; i < lineCount; i++) {
            int xs = random.nextInt(width / 2);
            int ys = random.nextInt(height);
            int xe = random.nextInt(width / 2) + width / 2;
            int ye = random.nextInt(height);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }

        // 初始化字体
        Font font = new Font(getFont(), Font.ITALIC, fontHeight);
        g.setFont(font);
        // 将验证码写入图片
        for (int i = 0; i < codeCount; i++) {
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(String.valueOf(validateCode.charAt(i)), i * x, codeY);
        }
    }

    //获取    一种  字体
    public String getFont() {
        Random rnd = new Random();
        int fontindex = rnd.nextInt(fontNames.length - 1);
        //获得该字体
        String fontName = fontNames[fontindex];
        return fontName;
    }

    /**
     *  输出图片到指定路径
     * @param path path
     * @throws IOException IOException
     */
    public void write(String path) throws IOException {
        OutputStream outputStream = new FileOutputStream(path);
        this.write(outputStream);
    }

    /**
     *  将图片输出到输出流中
     * @param  outputStream outputStream
     * @throws IOException IOException
     */
    public void write(OutputStream outputStream) throws IOException {
        ImageIO.write(buffImg, "png", outputStream);
        outputStream.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public static void main(String[] args) {
        CaptchaVO captchaVO = new CaptchaVO();
        // 图片验证码
        String code = RandomCodeUtil.genCode(6);
        captchaVO.setCode(code);
        captchaVO.setValue(code);

        // 图片验证码
        ImgCodeUtil imgCodeUtil = new ImgCodeUtil(code, 200, 60);
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
        log.info("<img src='data:image/gif;base64," + base64ImgCode + "' />");
    }


}
