package com.example.demo.message.domain;





import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("equipment_class")
@EqualsAndHashCode(callSuper = true)
public class EquipmentClass extends Model<EquipmentClass> {

    @TableId
    private String id;



    private String name;

    private String del;

    private Date createTime;

    private String createBy;



    @Override
    protected Serializable pkVal() {
        return null;
    }
}
