package com.jt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestMP {
    @Autowired
    private UserMapper userMapper;

    /**
     * 1. 根据id=23 数据
     */
    @Test
    public void select01(){
        User user=userMapper.selectById(23);
        System.out.println(user);
    }

    /**
     * 2. 查询name=“潘凤” sex="男"
     * 结果：一项：userMapper.selectOne()
     *      多项：userMapper.selectList()
     *
     *  queryWrapper: 条件构造器，拼接where条件
     *  如果遇到多条件查询，默认连接符and
     *  方式一：可以通过对象的方式进行控制，需要通过对象只能实现=条件
     */

    @Test
    public void select02(){
        User user=new User();
        user.setName("潘凤").setSex("男");
        QueryWrapper<User> queryWrapper=new QueryWrapper<>(user);
        //根据对象中不为null的属性，拼接where条件
        List<User> userList=userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }


    /**
     * 3. 要求: age>18岁  or sex=男的用户
     * 转义字符   > gt ,  < lt  ,  = eq
     *           >= ge ,  <= le
     */

    @Test
    public void select03(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        //编辑的时数据库表的字段信息
        queryWrapper.gt("age", 18)
                    .or()
                    .eq("sex", "男");

        List<User> userList=userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 要求：查询name中包含“精”  并且按照age降序排序
     * Sql: like  "%精%" 包含精
     *      like  “精%”  以精开头
     *      like  “%精”  以精结尾
     */
    @Test
    public void select04(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.likeLeft("name", "精")
                    .orderByDesc("age");
        List<User> userList=userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }


    /**
     * 5. 要求：查询多个数据
     *    查询id=1 3 6 7
     *    where id in(xx,xx,xx,xx)
     *    如果遇到多值传参，一般采用对象的方式封装数据
     */
    @Test
    public void select05(){
        Integer[] ids={1,3,6,7};
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.in("id", ids);
        List<User> userList=userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 6. 需求： 查询name为null的数据
     */
    @Test
    public void select06(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.isNull("name");
        List<User> userList=userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 7. 动态sql查询
     *    要求： 根据name属性与sex属性进行查询
     *           如果其中数据为null 则不参与where条件的拼接
     *           where age>18 and sex="男"
     *  错误的sql： ==>  Preparing: SELECT id,name,age,sex FROM demo_user WHERE (age > ? AND sex = ?)
     *          : ==> Parameters: 18(Integer), null
     *
     *  MP实现动态查询：
     *           参数1：condition boolean类型数据   true拼接条件  false不拼接
     *           参数2：字段名称
     *           参数3：字段值
     */
    @Test
    public void select07(){
        Integer age=18;
        String sex= "男";
//        boolean flag=sex !=null && sex.length()>0;
        boolean flag= StringUtils.hasLength(sex);

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.gt(age>0,"age", age)
                    .eq(flag,"sex", sex);
        List<User> userList=userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }


    /**
     * 8. demo1 要求：只查询name，age字段
     *  挑选查询的字段信息
     *     queryWrapper.select("name","age");
     *
     * demo2: 只要求返回name，age字段
     *     selectMaps()
     */
    @Test
    public void select08(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("name","age");
        //没有查询的数据以null返回
        List<User> userList=userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    @Test
    public void select09(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.select("name","age");
        List<Map<String ,Object>> list=userMapper.selectMaps(queryWrapper);
        System.out.println(list);
    }

    /**
     * 10 要求 返回第一列的数据
     *    如果包含了条件数据，则需要条件构造器
     */
    @Test
    public void select10(){
        List<Object> list=userMapper.selectObjs(null);
        System.out.println(list);
    }


    /**
     * 将id=523的用户名称，改为六一儿童节
     */
    @Test
    public void updateUser(){
        User user=new User();
        user.setId(523).setName("六一儿童节");
        //set name="xxx" where id = 523
        userMapper.updateById(user);
    }

}