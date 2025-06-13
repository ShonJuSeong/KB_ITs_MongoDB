package org.scoula.config;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class RootConfigTest {
    @Autowired
    private SqlSessionFactory sqlsessionFactory;

    @Test
    public void sqlSessionFactory() {
        try (SqlSession session = sqlsessionFactory.openSession();
             Connection con = session.getConnection();) {
            log.info(session);
            log.info(con);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}