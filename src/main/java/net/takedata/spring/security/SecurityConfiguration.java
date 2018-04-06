package net.takedata.spring.security;



import net.takedata.spring.security.service.OAuth2UserServiceImpl;
import net.takedata.spring.security.service.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SuccessHandler successHandler;

    @Autowired
    private OAuth2UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login().loginPage("/").successHandler(successHandler)
                .userInfoEndpoint().userService(userService);
    }




    @SuppressWarnings("deprecation")
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**", "/css/**", "/js/**", "/img/**");
    }
}
