package org.scoula.beans;

import org.springframework.stereotype.Component;
//
// ○ 자동등록 되도록 지정
// ○ name을"Koko"로지정mponent

@Component // 자동 등록하기 위해 필요한 어노테이션
public class Parrot2 {
    private String name = "Koko";

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Parrot2 : " + name;
    }
}
