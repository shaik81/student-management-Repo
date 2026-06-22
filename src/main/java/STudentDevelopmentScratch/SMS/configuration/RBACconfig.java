package STudentDevelopmentScratch.SMS.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@Configuration
public class RBACconfig {

  @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{


      httpSecurity.
              csrf(csrf->csrf.disable())
              .authorizeHttpRequests(auth->
                      auth.requestMatchers(HttpMethod.POST,"/admin/postdata","/admin/postdata/").permitAll()
                      .requestMatchers("/Students/login/**").permitAll()
                              .anyRequest().authenticated()
              )
                  .httpBasic(Customizer.withDefaults());

      return httpSecurity.build();

  }

}
