package org.scoula.main;
//org.scoula.main 패키지에 Main 클래스를 정의하세요.
//○ ProjectConfig를 설정으로 컨텍스트 생성
//○ Person과 Parrot 빈을 추출

//○ 추출된빈의속성출력
// Person's name
// Prrot's name
// Person's parrot


import org.scoula.beans.Parrot;
import org.scoula.beans.Person;
import org.scoula.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Person person = context.getBean(Person.class);
        Parrot Parrot = context.getBean(Parrot.class);

        System.out.println("Person's name : " + person.getName());
        System.out.println("Parrot's name : " + Parrot.getName());
        System.out.println("Person's parrot : " + person.getParrot());
    }
}
