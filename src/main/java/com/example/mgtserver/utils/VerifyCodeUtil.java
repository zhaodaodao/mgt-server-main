package com.example.mgtserver.utils;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码生成模块
 * @author Hexrt
 */
public class VerifyCodeUtil {
    /**
     * 删除不易识别的字符
     */
    private static final String DEFAULT_CHARS = "2345678abcdefhjklmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();
    private static final Font DEFAULT_FONT = new Font("微软雅黑", Font.BOLD, 40);
    public static final int DEFAULT_WIDTH = 200;
    public static final int DEFAULT_HEIGHT = 60;
    public static final int DEFAULT_CHAR_LENGTH = 4;
    private static final int DEFAULT_LINE_NUM = 6;
    private static final int DEFAULT_DOT_NUM = 20;
    private static final int DEFAULT_DOT_RAD = 2;
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 100;
    private static final int MAX_CHAR_LENGTH = 10;
    private static final int MAX_LINE_NUM = 20;
    private static final int MAX_DOT_NUM = 50;
    private static final int MAX_DOT_RAD = 5;

    public static String draw(BufferedImage image) {
        String text = getRandomChar(DEFAULT_CHARS, DEFAULT_CHAR_LENGTH);
        return draw(DEFAULT_WIDTH, DEFAULT_HEIGHT, text, image);
    }

    /**
     * 根据传入参数画指定大小的验证码
     * @param width  长度
     * @param height 高度
     * @param text   验证码字符
     * @param image  画板
     * @return 验证码
     */
    public static String draw(int width, int height, String text, BufferedImage image) {
        if (width < DEFAULT_WIDTH || width > MAX_WIDTH) {
            width = DEFAULT_WIDTH;
        }
        if (height < DEFAULT_HEIGHT || height > MAX_HEIGHT) {
            height = DEFAULT_HEIGHT;
        }

        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, width, height);
        graphics2D.setFont(DEFAULT_FONT);
        int x = 5;
        int y = height / 2 + DEFAULT_FONT.getSize() / 2;
        int dx = width / text.length();
        // 写字
        for (int i = 0; i < text.length(); ++i) {
            graphics2D.setColor(getRandomColor());
            double degree = (RANDOM.nextInt() % 30) * Math.PI / 180d;
            graphics2D.rotate(degree, x, y);
            graphics2D.drawString(""+text.charAt(i), x, y);
            graphics2D.rotate(-degree, x, y);
            x += dx;
        }

        // 画线
        for (int i = 0; i < DEFAULT_LINE_NUM; ++i) {
            graphics2D.setColor(getRandomColor());
            graphics2D.drawLine(RANDOM.nextInt(width), RANDOM.nextInt(height),
                                RANDOM.nextInt(width), RANDOM.nextInt(height));
        }

        // 噪点
        for (int i = 0; i < DEFAULT_DOT_NUM; ++i) {
            graphics2D.setColor(getRandomColor());
            graphics2D.fillRect(RANDOM.nextInt(width), RANDOM.nextInt(height), DEFAULT_DOT_RAD, DEFAULT_DOT_RAD);
        }

        return text;
    }

    /**
     * 获取指定长度随机字符串
     * @param chars  字符集
     * @param length 长度
     * @return 随机字符串
     */
    private static String getRandomChar(String chars, int length) {
        if (length <= DEFAULT_CHAR_LENGTH || length > MAX_CHAR_LENGTH) {
            length = DEFAULT_CHAR_LENGTH;
        }
        if (null == chars || chars.trim().isEmpty()) {
            chars = DEFAULT_CHARS;
        }
        StringBuilder str = new StringBuilder();
        while (length-- > 0) {
            str.append(chars.charAt(RANDOM.nextInt(chars.length())));
        }
        return str.toString();
    }

    private static Color getRandomColor() {
        Color color = new Color(RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256));
        return color;
    }
}
