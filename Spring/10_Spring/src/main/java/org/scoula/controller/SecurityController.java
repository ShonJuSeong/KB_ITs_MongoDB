package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.security.account.domain.CustomUser;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.security.Principal;

@Log4j2
@RequestMapping("/security")
@Controller
public class SecurityController extends WebSecurityConfigurerAdapter {


    // 문자셋필터
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);
    }

    @GetMapping("/login")
    public void login() {
        log.info("login page");
    }
        @GetMapping("/all")          // 모두 접근 가능
        public String doAll() {
            log.info("do all can access everybody");
            return "security/all";
        }


    @GetMapping("/member")
    public void doMember(Authentication authentication) {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        log.info("username = " + userDetails.getUsername());
    }


    @GetMapping("/admin")
    public void doAdmin(@AuthenticationPrincipal CustomUser customUser) {
        MemberVO member = customUser.getMember();
        log.info("username = " + member);
    }
    @GetMapping("/logout")
    public void logout() {
        log.info("logout page");
    }
    }
