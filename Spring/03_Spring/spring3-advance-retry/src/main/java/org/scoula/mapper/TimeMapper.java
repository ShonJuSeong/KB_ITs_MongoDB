package org.scoula.mapper;

import org.apache.ibatis.annotations.Select;

//○ String getTime() 메서드에 현재시간을 리턴하는 쿼리를 연결하세요.
//public interface TimeMapper {
//    @Select("SELECT sysdate()")
//    public String getTime();
//}

public interface TimeMapper {
//    @Select("SELECT sysdate FROM dual")
    @Select("SELECT now();")
    public String getTime();
    public String getTime2();
}