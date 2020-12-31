package com.example.demo.wx.Applets;

import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;

import me.chanjar.weixin.common.error.WxErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WxAppletsPush {

    /**
     *
     * @param openId todo(小程序id或者用户id)
     * @param formId
     * @param templetId
     * @param url
     * @return
     */
    public static String AppletPush(String openId, String formId, String templetId, String url,
                             Map<String,String> map){
        List<WxMaTemplateData> templateDataList = new ArrayList<>(2);
//        WxMaTemplateData data1 = new WxMaTemplateData("keyword1", map.get("keyword1")+"");
//        WxMaTemplateData data2 = new WxMaTemplateData("keyword2", map.get("keyword2")+"");
//        WxMaTemplateData data3 = new WxMaTemplateData("keyword3", map.get("keyword3")+"");
//        WxMaTemplateData data4 = new WxMaTemplateData("keyword4", map.get("keyword4")+"");


        //todo(便利参数)
        int num = 1;
        for(Map.Entry<String, String> entry : map.entrySet()){
            templateDataList.add(new WxMaTemplateData("keyword"+num, entry.getValue())) ;
            num++;
        }
//        templateDataList.add(data1);
//        templateDataList.add(data2);
//        templateDataList.add(data3);
//        templateDataList.add(data4);

        //3，设置推送消息
        WxMaTemplateMessage templateMessage = WxMaTemplateMessage.builder()
                .toUser(openId)//要推送的用户openid
                .formId(formId)//收集到的formid
                .templateId(templetId)//推送的模版id（在小程序后台设置）
                .data(templateDataList)//模版信息
                .page(url)//要跳转到小程序那个页面
                .build();

        //4，发起推送
        try {
             WxAppletsConfig.getWxMaService().getMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            System.out.println("推送失败：" + e.getMessage());
            return e.getMessage();
        }
        return "推送成功";
    }
}
