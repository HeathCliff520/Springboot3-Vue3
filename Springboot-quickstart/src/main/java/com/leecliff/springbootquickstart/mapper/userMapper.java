package com.leecliff.springbootquickstart.mapper;

import com.leecliff.springbootquickstart.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userMapper {
    @Select("select * from user where id = #{idfromBrowser}")
    public User findById(Integer idfromBrowser);
}
