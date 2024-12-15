package pe.edu.cibertec.spring_proyecto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/maintenance/login").permitAll()
                        .requestMatchers("/maintenance/start").hasAnyRole("ADMIN", "OPERATOR")
                        .anyRequest().authenticated()
                )

                //redireecionar a una pagina en caso no haya permiso
                .exceptionHandling(ex-> ex
                        .accessDeniedHandler((req, resp, excep) ->{
                            resp.sendRedirect("/maintenance/restricted");
                        })
                )

                //configurar formulario de inicio de sesion
                .formLogin( form -> form
                        .loginPage("/maintenance/login")
                                .defaultSuccessUrl("/maintenance/start", false)
                        .permitAll()
                )

                //configurar logout
                .logout(logout -> logout
                        .logoutUrl("/maintenance/logout")
                        .logoutSuccessUrl("/maintenance/login?logout")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        return username -> User.builder()
                .username("stephany")
                .password(passwordEncoder().encode("123456"))
                 .roles("ADMIN")
                .build();
    }*/
}
