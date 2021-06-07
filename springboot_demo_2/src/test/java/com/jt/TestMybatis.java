package com.jt;


import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.LineSeparatorDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


//Springboot为了简化程序的测试，研发了一个注解@SpringbootTest
//该注解只能在测试包中使用
//作用：当程序执行@Test测试方法时，则会动态的启动整个Spring容器,
//从容器中获取实例化对象，之后完成业务的测试


@SpringBootTest
public class TestMybatis {

    //面向接口编程 扩展xing性好
    @Autowired
    private UserMapper userMapper;//JDK动态代理

    @Test
    public void testInster(){
        System.out.println(userMapper.getClass());
        System.out.println(userMapper.getAll());
    }


    @Test
    public void tsetFind(){
        List<User> userList=userMapper.selectList(null);
        System.out.println(userList);
    }


    @Test
    public void insert(){
        User user=new User();
        user.setName("星期五").setAge(18).setSex("男");
        userMapper.insertUser(user);
        System.out.println("增加成功");
    }


    @Test
    public void update(){
        String oldname="星期五";
        String newname="徐灵";
        userMapper.updateUser(oldname, newname);
        System.out.println("更新成功");
    }


    @Test
    public void delete(){
        userMapper.deteleById(524);
        System.out.println("删除成功");
    }

    /**
     * Mybatis-plus的工作原理 ，利用MP机制实现入库
     */

    @Test
    public void testInsert(){
        User user=new User();
        user.setName("嫦娥").setAge(20).setSex("女");
        userMapper.insert(user);
    }
}
