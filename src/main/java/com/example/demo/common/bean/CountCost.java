package com.example.demo.common.bean;

import lombok.Data;

/**
 * 消耗统计
 */
@Data
public class CountCost {

    /**
     * 日期
     */
    private String date;

    /**
     * 总消耗
     */
    private Double totalCost;

    /**
     * 日均总消耗
     */
    private Double dayAvgCost;

    /**
     * 户均总消耗
     */
    private Double accountAvgCost;

    /**
     * 日户均总消耗
     */
    private Double dayAccountAvgCost;

    /**
     * 点击数
     */
    private Integer clickNum;

}
