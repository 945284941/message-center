package com.example.demo.common.bean;




import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


import com.example.demo.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * BaseController类
 * @param <T> 实体类泛型
 * @param <S> 接口类型
 * @param <PK> 主键类型
 */
@Validated
public class BaseController<T extends Model<T>,S extends IService<T>,PK extends Serializable> {

    @Resource
    protected S server;


    /**
     * 获取列表信息
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/list")
    public Result list(T t, Integer page, Integer limit)  {
        QueryWrapper<T> queryWrapper = getQueryWrapper(t);
        if(page != null && limit != null && page > 0 && limit > 0){
            IPage<T> iPage = server.page(new Page<>(page, limit),queryWrapper);
            return  Result.success(iPage);
        }
        IPage<T> iPage = server.page(new Page<>(-1, -1),queryWrapper);
        return  Result.success(iPage);
    }


    /**
     * 添加数据
     * @param t
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@Valid T t){
        try {
            t.insert();
            return Result.success(t);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error();
        }
    }


    /**
     * 批量添加
     * @param list
     * @return
     */
    @PostMapping("batchInsert")
    public Result batchInsert(@RequestParam("list") List<T> list){
        if(list.size() == 0){
            return Result.error(Code.MISS_REQUIRED_PARAMETER);
        }
        return server.saveBatch(list,list.size()) ? Result.success() : Result.error();
    }

    @GetMapping("getOneByEntity")
    public Result getOneByEntity(T t){
        QueryWrapper<T> queryWrapper = getQueryWrapper(t);
        return Result.success(server.getOne(queryWrapper));
    }

    public QueryWrapper<T> getQueryWrapper(T t){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        Class cls = t.getClass();
        Field[] fields = cls.getDeclaredFields();
        List<Field> f = Arrays.asList(fields);
        // 剔除空值属性 -> 将剩余的加入查询条件
        f.stream().filter(field -> {
            if(field.getName().equals("serialVersionUID")){
                return false;
            }
          
            field.setAccessible(true);
            if(field.getAnnotation(TableField.class)!=null){
                try {
                    return field.get(t) != null && field.getAnnotation(TableField.class).exist();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return false;
                }
            }else{
                try {
                    return field.get(t)!=null;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }).forEach(field->{
            try {
                queryWrapper.eq(StringUtils.HumpToUnderline(field.getName()),field.get(t));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return queryWrapper;
    }
    /**
     * 修改方法
     * @return
     */
    @PostMapping("/update")
    public Result update(T t){
        if(t == null){
            return Result.error(Code.MISS_REQUIRED_PARAMETER);
        }
        return t.updateById() ? Result.success() : Result.error();
    }

    /**
     * 删除方法
     * @return
     */
    @PostMapping("/deleteById")
    public Result deleteById(@NotBlank(message = "ID不能为空") String id){
        return server.removeById(id) ?  Result.success() : Result.error();
    }

    /**
     * 查询方法
     * @return
     */
    @GetMapping("/selectById")
    public Result selectById(PK id){
        return Result.success(server.getById(id));
    }

    /**
     * 批量查询 格式 : 'x,x' 例子：'1,2,3,4'
     * @param ids
     * @return
     */
    @GetMapping("/selectByIds")
    public Result selectByIds(@NotBlank(message = "ID不能为空") String ids){
        List<String> list  = Arrays.asList(ids.split(","));
        return Result.success(server.listByIds(list));
    }

    /**
     * 批量删除 格式 : 'x,x' 例子：'1,2,3,4'
     * @param ids
     * @return
     */
    @PostMapping("/deleteByIds")
    public Result deleteByIds(String ids){
        List<String> list  = Arrays.asList(ids.split(","));
        return server.removeByIds(list) ?  Result.success() : Result.error();
    }

}
