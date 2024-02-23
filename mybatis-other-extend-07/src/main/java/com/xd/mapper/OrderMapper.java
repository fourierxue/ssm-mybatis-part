package com.xd.mapper;

import com.xd.pojo.Order;

/**
* @author xuedong
* @description 针对表【t_order】的数据库操作Mapper
* @createDate 2024-01-24 20:10:33
* @Entity com.xd.pojo.Order
*/
public interface OrderMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

}
