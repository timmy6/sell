package com.qiming.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liuqiming
 * @date 2018/8/26
 * @email liuqiminggood@gmail.com
 * @Description
 */
@Data
@Component
@ConfigurationProperties(prefix = "classpath:application.yml")
public class WechatAccountConfig {
    private String mpAppId;
    private String mpAppSecret;
}