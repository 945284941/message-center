package com.example.demo.message.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.demo.common.bean.BaseController;
import com.example.demo.formId.domain.WxFormId;
import com.example.demo.formId.service.WxFormIdService;
import com.example.demo.message.domain.AjaxResult;
import com.example.demo.message.domain.SendMessage;
import com.example.demo.message.service.SendMessageService;
import com.example.demo.rabbitmq.Send;
import com.example.demo.rabbitmq.SendUtil;
import com.example.demo.util.TIhuanUtil;
import com.example.demo.wx.Applets.WxAppletsConfig;
import com.example.demo.wx.Applets.WxAppletsPush;
import com.example.demo.wx.official.WxOfficialConfig;
import com.example.demo.wx.official.WxOfficialPush;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

import java.util.*;


@Controller
@RequestMapping("/message")
@Slf4j
public class SendMessageController extends BaseController
//        <SendMessage, SendMessageService, String>
 {


    private  static final  String userId = "UUID";
    @Resource
    private static RedisTemplate<String,String> redisTemplate;
    @Resource
    private WxFormIdService wxFormIdService;
    @Resource
    private SendMessageService sendMessageService;
    @Value("exchange")
    private static  String exchange;


    @GetMapping("sendMessageList")
    @ResponseBody
    public List<SendMessage> sendMessageList(){
        return sendMessageService.list();
    }
    /**
     * @title 公众号发送消息
     * @param sendMessage todo(url, formid,openid,url等参数)
     * @param map
     * @return
     */
      @PostMapping("/officialPush")

      public  AjaxResult officialPush(SendMessage sendMessage,
                                       Map<String,String> map){
          try{
           String result =  WxOfficialPush.SendWxMsg(sendMessage.getOpenId(),
                   sendMessage.getTempletId(),sendMessage.getUrl(),map);

              JSONObject jsonObject  =  JSONObject.parseObject(result);
              String errcode = jsonObject.get("errcode")+"";
              if(errcode.equals("0")){
                  //发送成功

                  String token = redisTemplate.opsForValue().get("token");
                  if(token == null || token.equals("")){
                      WxOfficialConfig.getAccessToken();
                      token =  redisTemplate.opsForValue().get("token");
                  }

                  String  templetContent
                          = WxOfficialConfig.getTemplates(token,sendMessage.getTempletId());

                   if(templetContent != null && !templetContent.equals("")){


                       Map tihuanMap = new HashMap();
                       //处理map
                       for(Map.Entry<String, String> entry : map.entrySet()){
//                           templateMessage.addData(new WxMpTemplateData(entry.getKey(), entry.getValue())) ;
//                           num++;
                           tihuanMap.put(entry.getKey()+".DATA",entry.getValue());
                       }

                        templetContent = TIhuanUtil.content(templetContent, tihuanMap);

                   }

                  //todo(替换)
                sendMessage.setCreateTime(new Date());
                sendMessage.setDelFlag("0");
                sendMessage.setContent(templetContent);
                sendMessage.setResult(errcode);
                Boolean insertResult =  sendMessage.insert();
                if(insertResult){
                    return AjaxResult.success("添加成功",null);
                }else{
                    return AjaxResult.error("添加失败",null);
                }
              }else{
                  //发送失败
                  return AjaxResult.error("公众号发送消息失败",null);

              }
           }catch(Exception e){
               e.printStackTrace();
               log.error(e.getMessage());
          }
            return null;
      }


    @PostMapping("/appletsPush")
    @ResponseBody
    @Transactional
    public  AjaxResult appletsPush(SendMessage sendMessage,
                                    Map<String,String> map){
          //获取formId
        WxFormId wxFormId = new WxFormId();
        wxFormId.setFormTime(new Date());
        wxFormId.setDelFlag("0");
        wxFormId.setOpenId(sendMessage.getOpenId());
        List<WxFormId> wxFormIdList = wxFormIdService.selectList(wxFormId);

        if(wxFormIdList != null && wxFormIdList.size() > 0){
          String fromId =     wxFormIdList.get(0).getFormId();

          //发送
           String message =  WxAppletsPush.AppletPush(sendMessage.getOpenId(),
                    fromId,sendMessage.getUrl(),sendMessage.getTempletId(),map);
           if(message.equals("推送成功")){
               //更改formId
              WxFormId wd = wxFormIdList.get(0);
              wd.setDelFlag("1");
              wd.updateById();

               String token = redisTemplate.opsForValue().get("token");
               if(token == null || token.equals("")){
                   WxAppletsConfig.getAccessToken();
                   token =  redisTemplate.opsForValue().get("token");
               }
               //todo 获取消息模板
               Map mesageMap
                       = WxAppletsConfig.getMessageContent(token,sendMessage.getTempletId());
               if(mesageMap == null){
                  return AjaxResult.error("获取消息模板异常====","");
               }else{
                   //todo 对消息模板进行替换解析
                   JSONArray jsonArray =  JSONArray.parseArray(mesageMap.get("keyword_list")+"");

                   String messageContent = mesageMap.get("title")+"";
// 重组消息模板
                   for (int  i = 0 ; i < jsonArray.size();i++){
                       JSONObject jsonObject = jsonArray.getJSONObject(i);
                       messageContent +=
                               jsonObject.get("name")+":"+
                                       map.get("keyword"+jsonObject.get("keyword_id"))+"";
                   }
                   sendMessage.setCreateTime(new Date());
                   sendMessage.setDelFlag("0");
                   sendMessage.setContent(messageContent);
                   sendMessage.setResult(mesageMap.get("errcode")+"");
                   //todo 添加操作
                   Boolean  insertResult  = sendMessage.insert();
                   if(insertResult){
                       return AjaxResult.success("推送成功",null);
                   }else{
                       return AjaxResult.error("推送失败",null);
                   }

               }



           }else{
               return AjaxResult.error("微信小程序推送消息失败","");
           }

          //获取模板id
        }else{
            return AjaxResult.error("没有可用的formId!!！");
        }


    }



    @PostMapping("/sendMessage")
    @ResponseBody
    public AjaxResult sendMessage(SendMessage sendMessage,
                                  @NotBlank(message = "类型不能为空") String type){

        Boolean result   = null ;
        SendMessage message = new SendMessage();
        message.setId(UUID.randomUUID().toString().replace("-",""));
        //todo(个人)
        if(type.equals("pc")){
            String[] userIds = userId.split(",");

            result =  SendUtil.pushMsg(exchange,"direct",userId,sendMessage.getContent());
        }
        //todo(所有)
        if(type.equals("pcAll")){
            result =  SendUtil.pushMsg(exchange,"fanout",userId,sendMessage.getContent());
        }
        if(result){

            message.setContent(sendMessage.getContent());
            message.setCreateBy(sendMessage.getCreateBy());
            message.setCreateTime(new Date());
            message.setDelFlag("0");
            message.setType("2");
            try {
                result = message.insert();
                if(result){
                    return AjaxResult.success("操作成功","");
                }else{
                    return AjaxResult.error("操作失败","");
                }
            }catch (Exception e){
                e.printStackTrace();
                return AjaxResult.error("");
            }
        }else{
            return AjaxResult.error("推送失败","");
        }

    }


}
