package com.xd;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xd.mapper.EmployeeMapper;
import com.xd.pojo.Employee;
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

public class MybatisTest {
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
    public void test() {
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        // 调用之前，设置分页数据
        PageHelper.startPage(1, 2);
        List<Employee> query = mapper.queryList();
        // 将查询数据封装到一个PageInfo的分页实体类
        PageInfo<Employee> page = new PageInfo<>(query);
        System.out.println("page = " + page);
        System.out.println("query = " + query);
    }
}
