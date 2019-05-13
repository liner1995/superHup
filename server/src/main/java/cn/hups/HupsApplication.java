package cn.hups;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("cn.hups.*.dao")
@ComponentScan(basePackages = {"cn.hups","org.flowable.ui.modeler", "org.flowable.ui.common"})
public class HupsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HupsApplication.class, args);
    }

}
