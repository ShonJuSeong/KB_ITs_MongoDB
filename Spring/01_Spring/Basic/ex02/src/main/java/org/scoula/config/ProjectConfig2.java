package org.scoula.config;
//컴포넌트 스캔을 통해 자동으로 빈을 등록
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.scoula.beans")
public class ProjectConfig2 {

}
