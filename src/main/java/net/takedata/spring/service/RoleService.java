package net.takedata.spring.service;

import net.takedata.spring.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService extends AbstractService<Role>{

    @Value("${rest.service-uri}")
    private String uri;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void init(){
        uri = uri + "/roles/";
    }

    @Override
    public void add(Role role) {
    }

    @Override
    public void delete(long id) {
    }

    @Override
    public void update(Role role) {
    }

    @Override
    public Optional<Role> get(long id) {
        return Optional.empty();
    }

    @Override
    public Role get(String field) {
        String uri = this.uri + field;
        return restTemplate.exchange(uri,HttpMethod.GET,new HttpEntity<>(createHeaders()),Role.class).getBody();
    }

    @Override
    public List<Role> getAll() {
        ResponseEntity<List<Role>> userResponse = restTemplate.exchange(uri, HttpMethod.GET,new HttpEntity<>(createHeaders()), new ParameterizedTypeReference<List<Role>>(){});
        return userResponse.getBody();
    }
}
