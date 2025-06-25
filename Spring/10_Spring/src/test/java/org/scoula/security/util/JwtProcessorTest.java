package org.scoula.security.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class, SecurityConfig.class })
@Log4j2
class JwtProcessorTest {
    @Autowired
    JwtProcessor jwtProcessor;

    @Test
    void generateToken() {
        String username = "user0";

        String token = jwtProcessor.generateToken(username);
        log.info(token);
        assertNotNull(token);
    }
    @Test
    void getUsername() {
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDgzNTk4NSwiZXhwIjoxNzUwODM2MTA1fQ.cOvqVc-RLiSLXRQ2pRqbhWsphNc2rSyhJIkxWjzCI4nlxQfgCC15y21dKA8oe082";
        String username = jwtProcessor.getUsername(token);
        log.info(username);
        assertNotNull(username);
    }
    @Test
    void validateToken() {
        // 5분경과후테스트
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDgzNTk4NSwiZXhwIjoxNzUwODM2MTA1fQ.cOvqVc-RLiSLXRQ2pRqbhWsphNc2rSyhJIkxWjzCI4nlxQfgCC15y21dKA8oe082";
        boolean isValid = jwtProcessor.validateToken(token); // 5분경과후면예외발생
        log.info(isValid);
        assertTrue(isValid);    // 5분전이면true
    }
}
