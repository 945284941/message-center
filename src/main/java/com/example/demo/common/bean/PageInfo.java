package com.example.demo.common.bean;

import lombok.Data;

@Data
public class PageInfo {

    /**
     * 总记录数
     */
    private Integer total;
    /**
     * 总页数
     */
    private Integer totalPages;
    /**
     * 当前页
     */
    private Integer pageNo;
    /**
     * 页记录数
     */
    private Integer pageSize;


    public PageInfo(Integer total) {
        this.total = total;
    }

    public PageInfo(Integer total, Integer pageNo,
                    Integer pageSize) {
        this.total = total;
        this.totalPages = pageSize == -1 ? 1 : ((total - 1) / pageSize + 1);
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public static PageInfo page(Integer total) {
        return new PageInfo(total);
    }

    public static PageInfo page( Integer total, Integer pageNo,
                                 Integer pageSize) {
        return new PageInfo(total, pageNo, pageSize);
    }

}
