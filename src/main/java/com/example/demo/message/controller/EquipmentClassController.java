package com.example.demo.message.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.bean.BaseController;

import com.example.demo.common.bean.Result;
import com.example.demo.message.domain.AjaxResult;
import com.example.demo.message.domain.EquipmentClass;
import com.example.demo.message.service.EquipmentClassService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/equipmentClass")
@Slf4j
public class EquipmentClassController extends BaseController<EquipmentClass, EquipmentClassService, String> {

    @Autowired
     EquipmentClassService equipmentClassService;

    /**
     * 列表
     * @param name
     * @return
     */
    @GetMapping("newList")
    @ResponseBody
    public AjaxResult list(String name){

        QueryWrapper<EquipmentClass> query = new QueryWrapper<>();
//        query.eq("name",name);
        Map map = new HashMap();
        PageHelper.startPage(0,2);
        List<EquipmentClass> equipmentClassList  = equipmentClassService.list(query);
        PageInfo<EquipmentClass> pageInfo
                = new PageInfo<EquipmentClass>(equipmentClassList);
        map.put("list", pageInfo);
        return AjaxResult.success(map);
    }

    /**
     * todo(添加)
     * @param equipmentClass
     * @return
     */
    @PostMapping("addEquipmentClass")
    @ResponseBody
    public AjaxResult addEquipmentClass(EquipmentClass equipmentClass){

        equipmentClass.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
       Boolean flag = equipmentClassService.save(equipmentClass);
       if(flag){
           return AjaxResult.success();
       }else{
           return AjaxResult.error();
       }
    }
    /**
     * todo(删除)
     * @param id
     * @return
     */
    @PostMapping("deleteByIdss")
    @ResponseBody
    public AjaxResult delete(String id){
      Boolean  flag  =   equipmentClassService.removeById(id);

      if(flag){
          return AjaxResult.success();
      }else{
          return AjaxResult.error();
      }
    }
}
