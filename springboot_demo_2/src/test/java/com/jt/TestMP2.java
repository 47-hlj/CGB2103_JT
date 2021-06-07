package com.jt;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestMP2 {

    @Autowired
    private UserMapper userMapper;

    /**
     * 将id=523的用户名称，改为六一儿童节
     */
    @Test
    public void updateUser1(){
        User user=new User();
        user.setId(523).setName("六一儿童节");
        //set name="xxx" where id = 523
        userMapper.updateById(user);
    }


    /**
     * 更新操作2
     *     将name=“六一儿童节”  改为   “端午节”
     *
     * 参数说明：
     *     1.实体对象  封装修改后的数据    set结构
     *     2.UpdateWrapper  修改的条件构造器
     * Sql: update demo_user set name="端午节“  where name=" 六一儿童节"
     */
    @Test
    public void updateUser2(){
        User user=new User();
        user.setName("端午节");
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.eq("name", "六一儿童节");
        userMapper.update(user,updateWrapper);
    }
}
