package com.jt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


import java.util.List;

//@Mapper
//MP规则：BaseMapper<T> T MP要操作的表是谁？ T必须引入对象
public interface UserMapper extends BaseMapper<User> {

    List<User> getAll();

    @Insert("insert into demo_user(id,name,age,sex) value(null,#{name},#{age},#{sex})")
    void insertUser(User user);

    @Update("update demo_user set name=#{newname} where name=#{oldname}")
    void updateUser(String oldname,String newname);

    @Delete("delete from demo_user where id=#{id}")
    void deteleById(Integer id);

}
