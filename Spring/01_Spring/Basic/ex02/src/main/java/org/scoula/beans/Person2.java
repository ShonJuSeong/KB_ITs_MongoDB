package org.scoula.beans;
//○ 빈으로 자동등록 되도록 지정
//○ name을"Ella"로 지정
//○ Parrot2를 오토와이어를 이용해 의존성 주입 지정

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person2 {
    private String name = "Ella";

    @Autowired
    private Parrot2 parrot;

    public String getName(){
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
    public Parrot2 getParrot() {
        return parrot;
    }
    public void setParrot(Parrot2 parrot) {
        this.parrot = parrot;
    }
}
