package net.takedata.spring.service;

import net.takedata.spring.model.User;
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
public class UserService extends AbstractService<User> {

    @Value("${rest.service-uri}")
    private String uri;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    private void init(){
        uri = uri + "/users/";
    }

    @Override
    public void add(User user) {
        restTemplate.exchange(uri,HttpMethod.POST,new HttpEntity<>(user,createHeaders()), User.class);
    }

    @Override
    public void delete(long id) {
        restTemplate.exchange(uri + "{id}",HttpMethod.DELETE,new HttpEntity<>(createHeaders()),User.class,id);
    }

    @Override
    public void update(User user) {
        long id = user.getId();
        String url = uri + id;
        restTemplate.exchange(url,HttpMethod.PUT,new HttpEntity<>(user,createHeaders()), User.class);
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.of(restTemplate.exchange(uri + "{id}",HttpMethod.GET,new HttpEntity<>(createHeaders()),User.class,id).getBody());
    }

    @Override
    public User get(String field) {
        String url = this.uri + "email/{field}";
        return restTemplate.exchange(url,HttpMethod.GET,new HttpEntity<>(createHeaders()),User.class,field).getBody();
    }

    @Override
    public List<User> getAll() {
        //TODO брать List из Json
        ResponseEntity<List<User>> userResponse = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(createHeaders()), new ParameterizedTypeReference<List<User>>() {
        });
        return userResponse.getBody();
    }
}
