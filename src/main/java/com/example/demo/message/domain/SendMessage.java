package com.example.demo.message.domain;





import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("send_message")
@EqualsAndHashCode(callSuper = true)
public class SendMessage extends Model<SendMessage> {

    @TableId
    private String id;

    private String userId;

    private String content;

    private String type;

    private Date createTime;

    private String createBy;

    private String delFlag;

    private String result;

    private String templetId;

    private String messageType;

    private String openId;

    private String url;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}
