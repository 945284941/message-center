package com.example.demo.message.domain;

import java.util.HashMap;

public class AjaxResult extends HashMap<String,Object> {

    public static final String ICODE = "code";
    public static final String IMSGE = "msg";
    public static final String IDATA = "data";

    public AjaxResult(String msg , Object object, String code){
        super.put(ICODE,code);
        super.put(IMSGE,msg);
        super.put(IDATA,object);
    }
    public static AjaxResult successs(String msg , Object object){
//        super.put(ICODE,0);
//        super.put(IMSGE,msg);
//        super.put(IDATA,map);
        return new AjaxResult(msg,object,"0");
    }
    public static AjaxResult errorr(String msg , Object object){
//        super.put(ICODE,0);
//        super.put(IMSGE,msg);
//        super.put(IDATA,map);
        return new AjaxResult(msg,object,"1");
    }
   public  static  AjaxResult success(Object map){
        return AjaxResult.successs("操作成功",map);
   }
    public  static  AjaxResult success(String msg,Object map){
        return AjaxResult.successs(msg,map);
    }
    public  static  AjaxResult error(String msg,Object map){
        return AjaxResult.errorr(msg,map);
    }
    public  static  AjaxResult error(Object map){
        return AjaxResult.errorr("操作失败",map);
    }
}
