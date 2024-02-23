package com.xd.mapper;

import com.xd.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> query(@Param("name")String name, @Param("salary") Double salary);
    List<Employee> queryTrim(@Param("name")String name, @Param("salary") Double salary);
    // name不为空，根据name查询，name为空使用薪水查询，都为空查询全部
    List<Employee> queryChoose(@Param("name")String name, @Param("salary") Double salary);
    int update(Employee employee);
    int updateTrim(Employee employee);

    List<Employee> queryBatch(@Param("ids") List<Integer> ids);
    int deleteBatch(@Param("ids")List<Integer> ids);
    int insertBatch(@Param("employeeList")List<Employee> employeeList);
    int updateBatch(@Param("employeeList")List<Employee> employeeList);
}
