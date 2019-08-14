package com.example.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.auth.model.OAuthToken;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth2")
public class Oauth2Controller {

    private final Gson gson;
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(OAuthToken.class);

    @GetMapping(value = "/callback")
    public OAuthToken callbackSocial(@RequestParam String code) {

        String credentials = "testClientId:testSecret";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + encodedCredentials);

        logger.info("33333" + headers);


        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", "http://localhost:8080/oauth2/callback");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        logger.info("44444" + request);

       ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/oauth/token?", request, String.class);
    //postForEntity(String url, @Nullable Object request,
        //			Class<T> responseType, Object... uriVariables)

        if (response.getStatusCode() == HttpStatus.OK) {
            return gson.fromJson(response.getBody(), OAuthToken.class);
        }
        return null;
    }
}