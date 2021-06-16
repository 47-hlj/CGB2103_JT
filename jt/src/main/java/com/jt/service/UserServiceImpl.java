package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author 刘昱江
 * 时间 2021/5/11
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    /**
     * 实现用户登录操作
     * 1.将明文转化为密文 MD5加密
     * 2.通过username和password查询数据库.
     * 3.
     *     有数据: 用户名和密码正确  返回UUID token
     *     没有数据: 用户名和密码错误 返回null
     * @param user
     * @return
     */
    @Override
    public String login(User user) {
        String password = user.getPassword();
        //md5hash相对于md5更加"安全" 没有绝对安全的系统!!!
        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(md5Pass);
        //根据其中不为null的数据当做where条件 u/p
        //Sql: select * from user where u=#{u} and p=#{p}
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        User userDB = userMapper.selectOne(queryWrapper);
        if(userDB == null){
            //没有查询到数据
            return null;
        }
        String token = UUID.randomUUID().toString().replace("-","");
        return token;

    }

    /**
     * Sql: select * from user limit 数据起始位置,查询条数
     * 查询第1页 每页20条:
     *      select * from user limit 0,20
     * 查询第2页 每页20条:
     *      select * from user limit 20,20
     * 查询第3页 每页20条:
     *      select * from user limit 40,20
     * 查询第N页 每页20条:
     *      select * from user limit (n-1)20,20
     *  带查询条件的Sql:
     *       select * from user where name like "%#{query}%" limit (n-1)20,20
     * @param pageResult
     * @return
     *
     * 关于分页查询的说明
     *      userMapper.selectPage(分页对象,分页查询条件)
     */
    @Override
    public PageResult getUserList(PageResult pageResult) {
        //1.定义分页对象
        IPage<User> page = new Page<>(pageResult.getPageNum(),pageResult.getPageSize());
        //2.构建查询的条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        boolean flag = StringUtils.hasLength(pageResult.getQuery());
        //根据判断条件决定是否拼接where条件
        queryWrapper.like(flag, "username", pageResult.getQuery());
        //MP提供的分页查询的方法,返回值Page分页对象 其他包含分页的数据结果信息
        page = userMapper.selectPage(page,queryWrapper);
        long total = page.getTotal();
        List<User> list = page.getRecords(); //MP帮助实现了分页查询结果

        return pageResult.setRows(list).setTotal(total);
    }

    //user:{id:xxx,status:true/false}
    @Override
    public void updateStatus(User user) {

        userMapper.updateById(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteById(id);
    }


}
