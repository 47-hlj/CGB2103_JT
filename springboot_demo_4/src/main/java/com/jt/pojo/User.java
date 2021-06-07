package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.lang.reflect.Type;

//为了以后扩展方便A系统 调用B系统中的数据要求这么做
//对象如果实现了序列化的接口之后,就会有对应的功能
@Data
@Accessors(chain = true)
@TableName("demo_user")
public class User implements Serializable {
    //默认的序列化的编号
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
}