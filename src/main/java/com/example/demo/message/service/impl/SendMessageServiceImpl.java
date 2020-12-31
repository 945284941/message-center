package com.example.demo.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.message.domain.SendMessage;
import com.example.demo.message.mapper.SendMessageMapper;
import com.example.demo.message.service.SendMessageService;
import org.springframework.stereotype.Service;

@Service
public class SendMessageServiceImpl
        extends ServiceImpl<SendMessageMapper, SendMessage> implements SendMessageService {
}
