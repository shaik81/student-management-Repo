package STudentDevelopmentScratch.SMS.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@Configuration
public class RBACconfig {

  @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

    httpSecurity.csrf(csrf->csrf.disable())
            .authorizeHttpRequests(auth->auth
                    .requestMatchers(HttpMethod.POST,"/course/post").permitAll()//Course Request Manager
                    .requestMatchers(HttpMethod.POST,"/admin/postdata").permitAll()//Admin Request Manager
                    .requestMatchers(HttpMethod.POST,"/students/login").permitAll()//Student Manager
                    .requestMatchers(HttpMethod.GET,"/students/search").permitAll()//StudentManager
                    .requestMatchers(HttpMethod.POST,"/{studentRollNumber}/postdata").permitAll()//Marks
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

    return httpSecurity.build();
  }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

         return new BCryptPasswordEncoder();
  }


}
