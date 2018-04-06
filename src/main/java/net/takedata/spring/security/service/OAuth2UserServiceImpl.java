package net.takedata.spring.security.service;


import net.takedata.spring.model.Role;
import net.takedata.spring.model.User;
import net.takedata.spring.service.ServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

    private final ServiceI<User> userService;

    @Autowired
    public OAuth2UserServiceImpl(ServiceI<User> userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2User requestUser = (DefaultOAuth2User) super.loadUser(userRequest);
        String email = requestUser.getAttributes().get("email").toString();
        User user = userService.get(email);
        if(user == null){
            Map<String,Object> map = requestUser.getAttributes();
            Role role = new Role(2,"ROLE_USER");
            user = new User();
            user.setEmail(map.get("email").toString());
            user.setName(map.get("name").toString());
            user.setRole(role);
            userService.add(user);
        }
        user.setAttributes(requestUser.getAttributes());
        return user;
    }
}
