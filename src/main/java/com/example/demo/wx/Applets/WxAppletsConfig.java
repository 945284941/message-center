package com.example.demo.wx.Applets;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import com.example.demo.util.UtilsJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Configuration
public class WxAppletsConfig {

    @Value("${wx_applets_appid}")
    private static  String appId;
    @Value("${wx_applets_appsecret}")
    private static String appSecret;


    private static final Logger log = LoggerFactory.getLogger(WxAppletsConfig.class);


    /**
     * todo(获取token)
     * @param =
     * @return
     */
    public static Map<String, Object> getMessageContent(String token , String templateId) {
        RestTemplate rt = new RestTemplate();
        Map<String, Object> map = null;
        map.put("id",templateId);
        String url = "https://api.weixin.qq.com/cgi-bin/wxopen/template/library/get?access_token="+token;
        try {
            String s = rt.postForObject(url,map,String.class);
            map = UtilsJson.toMap(s);


                return map;


        }catch (Exception e){
            e.printStackTrace();
            log.error("获取消息模板失败....");
        }
        return null;
    }
    /**
     * 初始配置
     * @return
     */
    @Bean
    public static  WxMaService getWxMaService(){
        WxMaInMemoryConfig wxConfig = new WxMaInMemoryConfig();
        wxConfig.setAppid(appId);//小程序appid
        wxConfig.setSecret(appSecret);//小程序AppSecret

        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(wxConfig);
        return wxMaService;
    }
    /**
     * todo(获取token)
     * @param =
     * @return
     */
    public static void getAccessToken() {
        RestTemplate rt = new RestTemplate();
        Map<String, Object> map = null;

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret;
        try {
            String s = rt.getForObject(url, String.class);
            map = UtilsJson.toMap(s);
            if (map.get("errcode").equals("0")) {
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取token失败....");
        }

    }

}
