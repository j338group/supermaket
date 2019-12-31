package org.forten;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackages = "org.forten")
public class core {
    //  装配德鲁伊数据源
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/game?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT%2b8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    //  装配事务管理器
    @Bean
    @Autowired
    public PlatformTransactionManager ptm(DataSource dataSource) {
        PlatformTransactionManager manager = new DataSourceTransactionManager(dataSource);
        return manager;
    }

    //  装配Mybatis
    @Bean
    @Autowired
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean ssq = new SqlSessionFactoryBean();
//      将DataSource放入到SqlSession的Bean工厂中
        ssq.setDataSource(dataSource);
//
        ssq.setConfigLocation(new ClassPathResource("123.xml"));
//      设置映射Mapper的文件路径
        ssq.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("Mapper/aa.xml"));
        return ssq;
    }

    //  配置dao层的扫描路径
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer aa = new MapperScannerConfigurer();
        aa.setBasePackage("org/forten/mybatis/dao");
        return aa;
    }
}
