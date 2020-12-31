package com.example.demo.wx.official;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.UtilsJson;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WxOfficialConfig {

    @Value("${wx_appid}")
    private static  String appId;
    @Value("${wx_appsecret}")
    private static String appSecret;

    @Resource
    private static RedisTemplate<String,String> redisTemplate;
    private static final Logger log = LoggerFactory.getLogger(WxOfficialConfig.class);


    @Bean
    public static WxMpService getWxMaService(){
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(appSecret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        return wxMpService;
    }

    /**
     * todo(获取token)
     * @return
     */
    public static String getTemplates(String accessToken,String templateId) {
        RestTemplate rt = new RestTemplate();
        Map<String, Object> map = null;

        String url = "https://api.weixin.qq.com/cgi-bin/template/get_all_pri break;vate_template?access_token="+accessToken;
        try {
            String s = rt.getForObject(url, String.class);
            map = UtilsJson.toMap(s);
            JSONArray jsonArray =
                    JSONArray.parseArray(map.get("template_list")+"");
            String content = "";
             for (int  i = 0 ; i < jsonArray.size() ; i++){
                String tempId = jsonArray.getJSONObject(i).get("template_id")+"";
                if(tempId.equals(templateId)){
                    content = jsonArray.getJSONObject(i).get("content")+"";
                    break;
                }
             }
           return content;
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取templates失败....");
        }
       return null;
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
                redisTemplate.opsForValue().set("token",map.get("access_token")+"",7200);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取token失败....");
        }

    }
}
