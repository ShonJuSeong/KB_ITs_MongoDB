package org.scoula.beans;
// org.scoula.bean 패키지에 Parrot 클래스를 정의하세요
// String name 속성과 Getter/Setter 추가
// toString 추가

import org.springframework.stereotype.Component;

@Component
public class Parrot {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Parrot : " + name;
    }
}
