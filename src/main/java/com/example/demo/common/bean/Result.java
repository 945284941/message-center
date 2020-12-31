package com.example.demo.common.bean;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;


/**
 * 接口返回标准
 * @author lihai
 */
@Data
public class Result {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;
    /**
     * 分页预留
     */
    private PageInfo page;

    public Result(){}

    /**
     * 构造函数
     * @param code
     * @param msg
     * @param data
     * @param page
     */
    public Result(Integer code, String msg, Object data, PageInfo page) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.page = page;
    }

    public Result(Integer code, String msg, Object data, Integer total, Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.page = pageInfo;
    }

    /**
     * 成功返回且带数据分页
     * @param data
     * @return
     */
    public static Result success(Object data){
        return new Result(Code.SUCCESS.getCode(), Code.SUCCESS.getMsg(),data,null);
    }

    /**
     * 成功返回且自定义返回Code带数据分页
     * @param data
     * @return
     */
    public static Result success(Code code, Object data){
        return new Result(code.getCode(), code.getMsg(),  data,null);
    }


    /**
     * 返回空成功数据
     * @return
     */
    public static Result success(){
        return new Result(Code.SUCCESS.getCode(), Code.SUCCESS.getMsg(),null,null);
    }

    /**
     * 成功返回分页和数据
     * @param page
     * @return
     */
    public static Result success(IPage page){
        Integer total = (page.getRecords().size() > 0 && ((int)page.getTotal()) == 0) ? page.getRecords().size() : (int)page.getTotal();
        return new Result(Code.SUCCESS.getCode(), Code.SUCCESS.getMsg(),page.getRecords(),new PageInfo(total, (int)page.getCurrent(),(int)page.getSize()));
    }

    /**
     * 自定义Code 分页数据
     * @param code
     * @param page
     * @return
     */
    public static Result success(Code code, IPage page){
        Integer total = (page.getRecords().size() > 0 && ((int)page.getTotal()) == 0) ? page.getRecords().size() : (int)page.getTotal();
        return new Result(code.getCode(), code.getMsg(),page.getRecords(),new PageInfo(total, (int)page.getCurrent(),(int)page.getSize()));
    }


    /**
     * 返回空成功数据
     * @return
     */ 
    public static Result error(){
        return new Result(Code.ERROR.getCode(), Code.ERROR.getMsg(),null,null);
    }

    /**
     * 自定义带数据的错误返回值
     * @param data
     * @param code
     * @return
     */
    public static Result error(Code code, Object data){
        return new Result(code.getCode(), code.getMsg(), data,null);
    }

    public static Result error(Integer code, String msg){
        return new Result(code, msg, null,null);
    }

    public static Result error(Integer code, String msg, Object data){
        return new Result(code, msg, data,null);
    }

    /**
     * 返回状态
     * @param code
     * @return
     */
    public static Result error(Code code){
        return new Result(code.getCode(), code.getMsg(),null,null);
    }

    @Override
    public String toString() {
        return "ResponseUtils{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", page='" + page + '\'' +
                '}';
    }

}

