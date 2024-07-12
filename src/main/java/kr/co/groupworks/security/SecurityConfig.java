package kr.co.groupworks.security;

import kr.co.groupworks.repository.cis.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    //    암호화 관련 빈
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("==================== Security Filter Chain ====================");

        http
                .csrf(auth -> auth.disable()); // CSRF 기능 해제

        http
                .sessionManagement(auth -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                );

//        첫 로그인 화면 말고는 로그인 인증 되어야 함.
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","employee/save").permitAll()
                        .anyRequest().authenticated()
                );

//        로그인
        http
                .formLogin(auth -> auth
                        .loginPage("/") // 커스텀 로그인 페이지
                        .loginProcessingUrl("/loginProc") // 로그인 인증 경로
                        .defaultSuccessUrl("/main") // 로그인 후 페이지
                        .failureUrl("/login?error=true") // 로그인 실패 시
                );

//        로그아웃
        http
                .logout(auth -> auth
                        .logoutUrl("/logout") // 로그아웃 URL
                        .logoutSuccessUrl("/login") // 로그아웃 후 URL
                        .permitAll()
                );

        return http.build();
    }

//    정적 자원을 필터를 통하지 않고 바로 사용할 수 있도록 무시 시켜주자
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web->
                web.ignoring().requestMatchers("/css/**","/js/**","/img/**","/fonts/**");
    }


}
