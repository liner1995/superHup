package cn.hups;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("cn.hups.*.dao")
@ComponentScan(basePackages = {"cn.hups","org.flowable.ui.modeler", "org.flowable.ui.common"})
public class HupsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HupsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(HupsApplication.class, args);
    }

}
