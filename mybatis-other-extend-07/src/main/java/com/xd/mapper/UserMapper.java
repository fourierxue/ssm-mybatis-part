package com.xd.mapper;

import com.xd.pojo.User;

/**
* @author xuedong
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-01-24 20:10:33
* @Entity com.xd.pojo.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
