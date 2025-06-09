package org.scoula.beans;
//org.scoula.beans 패키지에 Person 클래스를 정의하세요
//○ String name 속성과 Getter/Setter 추가
//○ Parrot parrot 속성과 Getter/Setter 추가
import org.springframework.stereotype.Component;

@Component
public class Person {

    private String name;
    private Parrot parrot;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public Parrot getParrot(){
        return parrot;
    }
    public void setParrot(Parrot parrot){
        this.parrot = parrot;
    }
}
