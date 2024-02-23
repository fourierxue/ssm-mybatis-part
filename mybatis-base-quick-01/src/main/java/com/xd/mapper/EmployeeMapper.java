package com.xd.mapper;

import com.xd.pojo.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    /**
     * 根据员工id查询员工数据方法
     * @param empId  员工id
     * @return 员工实体对象
     */
    Employee selectEmployee(Integer empId);

    Map<String,Object> selectEmpNameAndMaxSalary();

    List<Employee> selectAll();

    int insertEmployee(Employee employee);
}
