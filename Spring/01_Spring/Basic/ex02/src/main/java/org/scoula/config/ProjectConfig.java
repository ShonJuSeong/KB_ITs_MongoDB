package org.scoula.config;

import org.scoula.beans.Parrot;
import org.scoula.beans.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ProjectConfig {

    @Bean
    public Parrot parrot(){
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }
    @Bean
    public Person person(Parrot parrot){ // 의존성 주입
        Person p = new Person();
        p.setName("Ella");
        p.setParrot(parrot); // 의존성 주입
        return p;
    }
}
//ProjectConfig 를 수정하여 Person 빈의 Parrot을설정하세요.
// Main을 실행하여 설정 내용을 확인
//○ 메서드의 의존성 주입을 통해 Parrot을 주입하도록 설정

//구분	                의미 및 용도	                    대상
//@Bean(name = "Koko")	스프링이 관리할 때 사용하는 빈 이름	스프링 컨테이너
//setName("Koko")	    객체의 필드 값(name)을 설정	    객체(자바 인스턴스)