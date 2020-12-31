package com.example.demo.formId.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.formId.domain.WxFormId;

import java.util.List;

public interface WxFormIdService extends IService<WxFormId> {

    public List<WxFormId> selectList(WxFormId wxFormId);
}
