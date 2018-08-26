package com.qiming.sell.controller;

import com.qiming.sell.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuqiming
 * @date 2018/8/26
 * @email liuqiminggood@gmail.com
 * @Description
 */
@RestController("/weixin")
@Slf4j
public class WeixinController extends BaseController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("【进入auth】方法 code={}", code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class); //里面会有openid
    }
}