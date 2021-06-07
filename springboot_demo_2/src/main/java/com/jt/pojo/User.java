package com.jt.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("demo_user")//1.实现对象与表的映射
public class User {
    //规则：如果对象中的属性与表中的字段同名，则用户可以省略
    //     在编译时由框架自动添加

    @TableId(type = IdType.AUTO)   //2.标识主键 主键自增
//    UUID:hash算法 唯一性 hash碰撞问题！！！
    private Integer id;

    @TableField("name") //3.对象的属性与表中的字段关联
    private String name;
    private Integer age;
    private String sex;

//    MP会将dog属性当做数据库字段进行处理Sql必然报错
//    private Dog dog;
}
