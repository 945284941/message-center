package com.example.demo.formId.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.formId.domain.WxFormId;
import com.example.demo.formId.mapper.WxFormIdMapper;
import com.example.demo.formId.service.WxFormIdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WxFormIdServiceImpl extends ServiceImpl<WxFormIdMapper, WxFormId> implements WxFormIdService {

    @Resource
    WxFormIdMapper wxFormIdMapper;
    @Override
    public List<WxFormId> selectList(WxFormId wxFormId) {
        return wxFormIdMapper.selectListByNew(wxFormId);
    }
}
