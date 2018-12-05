package cn.jxufe.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

@SpringBootApplication()
@MapperScan("cn.jxufe.dao.mysql")
public class ServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
