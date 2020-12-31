package com.example.demo.formId.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.formId.domain.WxFormId;

import java.util.List;

public interface WxFormIdMapper extends BaseMapper<WxFormId> {

    List<WxFormId> selectListByNew(WxFormId wxFormId);
}
