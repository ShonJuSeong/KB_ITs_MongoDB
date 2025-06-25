package org.scoula.security.filter;

import lombok.extern.log4j.Log4j2;
import org.scoula.security.account.dto.LoginDTO;
import org.scoula.security.handler.LoginFailureHandler;
import org.scoula.security.handler.LoginSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Log4j2
@Component
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    // 스프링생성자주입을통해전달
    public JwtUsernamePasswordAuthenticationFilter(
            AuthenticationManager authenticationManager,
            LoginSuccessHandler loginSuccessHandler,
            LoginFailureHandler loginFailureHandler) {
        super(authenticationManager);
        setFilterProcessesUrl("/api/auth/login");
        // POST 로그인요청url
        setAuthenticationSuccessHandler(loginSuccessHandler); // 로그인 성공 핸들러등록
        setAuthenticationFailureHandler(loginFailureHandler);  // 로그인 실패 핸들러 등록
    }
    // 로그인요청URL인경우로그인작업처리
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 요청BODY의JSON에서username, password →LoginDTO
        LoginDTO login = LoginDTO.of(request);
        // 인증토큰(UsernamePasswordAuthenticationToken) 구성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        // AuthenticationManager에게 인증 요청
        return getAuthenticationManager().authenticate(authenticationToken);
    }
}