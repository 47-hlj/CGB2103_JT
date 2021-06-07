package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("demo_user")
public class User implements Serializable {

    /**
     * 1.为什么序列化
     *需求：有时项目调用时需要进行“远程过程调用RPC”，A系统中需要B系统的User，由于时
     *     不同的2个系统，所以需要通讯协议（http、https、tcp、udp）的支持
     * 问题：由于user对象可以通过协议进行网络传输，如果保证B系统中传递的User数据
     *      到
     * 2. 序列化结构
     * 序列化：将对象按照特定的字节循序，进行排列
     * 反序列化：将一段字节信息，按照特定的格式顺序，反序列化为对象
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
}
