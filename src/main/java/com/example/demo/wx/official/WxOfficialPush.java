package com.example.demo.wx.official;


import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;


import java.util.Map;

public class WxOfficialPush {

    private static final Logger log = LoggerFactory.getLogger(WxOfficialPush.class);



    /**
     *
     *
     * @param openId todo(公众号)

     * @param templetId
     * @param url
     * @return
     */
    public static String SendWxMsg(String openId, String templetId, String url,
                             Map<String,String> map){
        // 发送模板消息接口
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                // 接收者openid
                .toUser(openId)
                // 模板id
                .templateId(templetId)
                // 模板跳转链接
                .url(url)
                .build();
        // 添加模板数据
        int num = 1;
        for(Map.Entry<String, String> entry : map.entrySet()){
            templateMessage.addData(new WxMpTemplateData(entry.getKey(), entry.getValue())) ;
            num++;
        }

        String result = null;
        try {
            // 发送模板消息
          result =     WxOfficialConfig.getWxMaService().getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("推送失败==="+e.getMessage());
            e.printStackTrace();

        }
        return result;

    }

}
