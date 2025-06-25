package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.scoula.security.filter.JwtUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@Log4j2
@MapperScan(basePackages = {"org.scoula.security.account.mapper"})
@ComponentScan(basePackages  = {"org.scoula.security"})
@RequiredArgsConstructor

public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final UserDetailsService userDetailsService;

    @Autowired
    private JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // 문자셋필터
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter= new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 한글인코딩필터설정
        http.addFilterBefore(encodingFilter(), CsrfFilter.class)
                // 로그인인증필터
                .addFilterBefore(jwtUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.httpBasic().disable() // 기본HTTP 인증비활성화
                .csrf().disable()  // CSRF 비활성화
                .formLogin().disable()  // formLogin 비활성화관련필터해제
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션 생성 모드 설정


// 경로별 접근권한설정
        http.authorizeRequests()
                .antMatchers("/security/all").permitAll()
                .antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/security/member").access("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')");

        http.formLogin() // form 기반로그인활성화, 나머지는모두디폴트
                .loginPage("/security/login")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/");

        http.logout()// 로그아웃설정시작
                .logoutUrl("/security/logout")         // POST: 로그아웃호출url
                .invalidateHttpSession(true)            // 세션 invalidate
                .deleteCookies("remember-me", "JSESSION-ID")  // 삭제할 쿠키목록
                .logoutSuccessUrl("/security/logout");// GET: 로그아웃 이후이동할페이지


    }
    // Authentication Manger  구성
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // in memory user 정보 삭제 → UserDetailsService와 같이사용불가
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        auth.inMemoryAuthentication()
                .withUser("admin")
                // .password("{noop}1234")
                .password("$2a$10$ahSYz8mFV9hUiZ.s3IV8U.OROOIBcwS/xt.sTgwLkvtbsMuk82dp2")
                .roles("ADMIN","MEMBER"); // ROLE_ADMIN, ROLE_MEMBER
        auth.inMemoryAuthentication()
                .withUser("member")
                // .password("{noop}1234")
                .password("$2a$10$mXLRysSOu4Nf1201CSlYr.kGsz685Dhtp45o1fxlU0BRf/qf2cCdC")
                .roles("MEMBER"); // ROLE_MEMBER
    }


    // AuthenticationManager 빈 등록
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    // cross origin 접근 허용
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    // 접근제한무시경로설정–resource
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/*", "/api/member/**");
    }
}