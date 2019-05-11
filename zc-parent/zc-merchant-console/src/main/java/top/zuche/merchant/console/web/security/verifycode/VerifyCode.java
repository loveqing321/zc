package top.zuche.merchant.console.web.security.verifycode;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author lzx
 * @date 2019/5/9 下午2:44
 */
@Setter
@Getter
public class VerifyCode implements Serializable {

    private BufferedImage image;

    private String random;

    private LocalDateTime expireTime;

    public VerifyCode(BufferedImage image, String random, int expire) {
        this.image = image;
        this.random = random;
        LocalDateTime now = LocalDateTime.now();
        this.expireTime = now.plus(expire, ChronoUnit.SECONDS);
    }

    /**
     * 判断验证码是否超时
     *
     * @param that
     * @return
     */
    public boolean isExpire(LocalDateTime that) {
        return that.isBefore(this.expireTime);
    }

    /**
     * 擦出图片部分
     */
    public void eraseImage() {
        this.image = null;
    }

}
