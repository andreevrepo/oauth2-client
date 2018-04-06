package net.takedata.spring.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import java.nio.charset.Charset;

abstract class AbstractService<T> implements ServiceI<T> {
    @Value("${rest.username}")
    String username;
    @Value("${rest.password}")
    String password;

    HttpHeaders createHeaders(){
        String username = "test";
        String password = "test";
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }

}
