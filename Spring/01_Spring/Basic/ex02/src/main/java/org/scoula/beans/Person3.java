package org.scoula.beans;
// ○ 빈으로 자동 등록 되도록 지정
//○ name을 "Ella"로 지정
//○ Parrot2를 생성자 주입을 통해 자동 설정

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person3 {
    private String name = "Ella";

    private final Parrot2 parrot;
    @Autowired
    public Person3(Parrot2 parrot) {
        this.parrot = parrot;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Parrot2 getParrot() {
        return parrot;
    }
    }
