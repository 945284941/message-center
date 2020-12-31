package com.example.demo.formId.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("wx_formid" )
@EqualsAndHashCode(callSuper = true)
public class WxFormId  extends Model<WxFormId> {

    private String id;
    private String formId;
    private String startTime;
    private String endTime;
    private String createTime;
    private String delFlag;
    private String openId;

    @TableField(exist = false)
    private Date formTime;
}
