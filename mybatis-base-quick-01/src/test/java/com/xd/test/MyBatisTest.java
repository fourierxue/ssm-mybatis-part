package com.xd.test;

import com.xd.mapper.EmployeeMapper;
import com.xd.mapper.UserMapper;
import com.xd.pojo.Employee;
import com.xd.pojo.EmployeeIbatis;
import com.xd.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * projectName: com.atguigu.test
 *
 * description: 测试类
 */
public class MyBatisTest {
    private SqlSession session;

    @BeforeEach //每个test之前调用
    public void before() throws IOException {
        // 1.创建SqlSessionFactory对象
        // ①声明Mybatis全局配置文件的路径
        String mybatisConfigFilePath = "mybatis-config.xml";

        // ②以输入流的形式加载Mybatis配置文件
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);

        // ③基于读取Mybatis配置文件的输入流创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.使用SqlSessionFactory对象开启一个会话
        session = sessionFactory.openSession();

    }

    @AfterEach
    public void after() throws IOException {
        session.close();
    }

    @Test
    public void testSelectEmployee() throws IOException {

        // 3.根据EmployeeMapper接口的Class对象获取Mapper接口类型的对象(动态代理技术)
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        // 4. 调用代理类方法既可以触发对应的SQL语句
        Employee employee = employeeMapper.selectEmployee(1);

        System.out.println("employee = " + employee);

        // 4.关闭SqlSession
        session.commit(); //提交事务 [DQL不需要,其他需要]
        session.close(); //关闭会话

    }

    @Test
    public void testSelectEmployeeIbatis() throws IOException {
        /**
         * ibatis 缺点
         * 1、传字符串找sql语句，容易出错
         * 2、只能传1各参数，多个需要用对象封装
         * 3、返回值类型无提示，默认object
         */
        EmployeeIbatis employeeIbatis = session.selectOne("xxx.yyy", 1);

        System.out.println("employee = " + employeeIbatis);

        // 4.关闭SqlSession
        session.commit(); //提交事务 [DQL不需要,其他需要]
        session.close(); //关闭会话

    }
    @Test
    public void testQueryEmpNameAndSalary() throws IOException {
        // 1.创建SqlSessionFactory对象
        // ①声明Mybatis全局配置文件的路径
        String mybatisConfigFilePath = "mybatis-config.xml";

        // ②以输入流的形式加载Mybatis配置文件
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);

        // ③基于读取Mybatis配置文件的输入流创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.使用SqlSessionFactory对象开启一个会话
        SqlSession session = sessionFactory.openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        Map<String, Object> resultMap = employeeMapper.selectEmpNameAndMaxSalary();

        Set<Map.Entry<String, Object>> entrySet = resultMap.entrySet();

        for (Map.Entry<String, Object> entry : entrySet) {

            String key = entry.getKey();

            Object value = entry.getValue();

            System.out.println(key + "=" + value);

        }
    }
    @Test
    public void testSelectAll() throws IOException {
        // 1.创建SqlSessionFactory对象
        // ①声明Mybatis全局配置文件的路径
        String mybatisConfigFilePath = "mybatis-config.xml";

        // ②以输入流的形式加载Mybatis配置文件
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);

        // ③基于读取Mybatis配置文件的输入流创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.使用SqlSessionFactory对象开启一个会话
        SqlSession session = sessionFactory.openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        List<Employee> employeeList = employeeMapper.selectAll();
        for (Employee employee : employeeList) {
            System.out.println("employee = " + employee);
        }
    }
    @Test
    public void testSaveEmp() throws IOException {
        // 1.创建SqlSessionFactory对象
        // ①声明Mybatis全局配置文件的路径
        String mybatisConfigFilePath = "mybatis-config.xml";

        // ②以输入流的形式加载Mybatis配置文件
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);

        // ③基于读取Mybatis配置文件的输入流创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.使用SqlSessionFactory对象开启一个会话
        SqlSession session = sessionFactory.openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Employee employee = new Employee();
        employee.setEmpName("john");
        employee.setEmpSalary(666.66);
        employeeMapper.insertEmployee(employee);
        System.out.println("employee.getEmpId() = " + employee.getEmpId());
        // 默认开启事务，dml语句必须提交，否则插入不生效  或者 openSession方法传入true，表示自动提交
        session.commit();
    }
    @Test
    public void testSaveUser() throws IOException {
        // 1.创建SqlSessionFactory对象
        // ①声明Mybatis全局配置文件的路径
        String mybatisConfigFilePath = "mybatis-config.xml";

        // ②以输入流的形式加载Mybatis配置文件
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);

        // ③基于读取Mybatis配置文件的输入流创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.使用SqlSessionFactory对象开启一个会话
        SqlSession session = sessionFactory.openSession(true);
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("xxx");
        user.setPassword("xxx");
        int s = userMapper.insertUser(user);
        System.out.println("s = " + s);
        System.out.println("user.getId()=" + user.getId());
        // 默认开启事务，dml语句必须提交，否则插入不生效  或者 openSession方法传入true，表示自动提交
        //session.commit();
    }
}
