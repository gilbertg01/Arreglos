//package laboratorio.Empleados.Seguridad;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurity {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity.authorizeHttpRequests(registry->{
//            registry.requestMatchers("/login").permitAll();
//            registry.requestMatchers("/index/**").hasRole("ADMIN");
//            registry.requestMatchers("/indexSecretaria/**").hasRole("SECRETARIA");
//            registry.requestMatchers("/indexBionalista/**").hasRole("BIONALISTA");
//            registry.anyRequest().authenticated();
//
//        })
//                .build();
//    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//    }
//}
