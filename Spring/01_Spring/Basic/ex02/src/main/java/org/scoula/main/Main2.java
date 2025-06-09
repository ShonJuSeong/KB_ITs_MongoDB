package org.scoula.main;
// ○ ProjectConfig2를 설정으로 컨텍스트 생성
//○ Person2빈을 추출
//○ 추출된 빈의 속성 출력
// Person's name
//  Person's parrot


import org.scoula.beans.Person2;
import org.scoula.config.ProjectConfig2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig2.class);

        Person2 p = context.getBean(Person2.class);
        System.out.println(" Person's name : " + p.getName());

        System.out.println("Person's parrot : " +  p.getParrot());
    }
}
