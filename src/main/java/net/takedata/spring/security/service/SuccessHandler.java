package net.takedata.spring.security.service;

import net.takedata.spring.model.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class SuccessHandler implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        if(authentication.getAuthorities().contains(new Role("ROLE_ADMIN"))){
            httpServletResponse.sendRedirect("/admin");
        }else{
            httpServletResponse.sendRedirect("/user");
        }
    }
}
