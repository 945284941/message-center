package com.example.demo.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.message.domain.EquipmentClass;
import com.example.demo.message.mapper.EquipmentClassMapper;
import com.example.demo.message.service.EquipmentClassService;
import org.springframework.stereotype.Service;

@Service
public class EquipmentClassServiceImpl
        extends ServiceImpl<EquipmentClassMapper, EquipmentClass> implements EquipmentClassService {
}
