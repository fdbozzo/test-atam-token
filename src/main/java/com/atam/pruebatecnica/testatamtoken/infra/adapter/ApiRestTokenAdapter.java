package com.atam.pruebatecnica.testatamtoken.infra.adapter;

import com.atam.pruebatecnica.testatamtoken.application.exception.ApiHttpClientErrorException;
import com.atam.pruebatecnica.testatamtoken.application.exception.ApiUserExceptionBadRequest;
import com.atam.pruebatecnica.testatamtoken.application.model.generated.TokenDtoApiRequest;
import com.atam.pruebatecnica.testatamtoken.application.model.generated.TokenDtoApiResponse;
import com.atam.pruebatecnica.testatamtoken.application.port.out.TokenServiceRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@Service
public class ApiRestTokenAdapter implements TokenServiceRequest {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${rest-client.token.url}")
    private String tokenUrl;

    @Value("${rest-client.token.username}")
    private String tokenUser;

    @Value("${rest-client.token.password}")
    private String tokenPwd;

    public ApiRestTokenAdapter(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Optional<TokenDtoApiResponse> getToken()  {
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(tokenUser, tokenPwd));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(new TokenDtoApiRequest().username(tokenUser).password(tokenPwd));
        } catch (JsonProcessingException e) {
            throw new ApiUserExceptionBadRequest("ApiRestTokenAdapter.getToken(): Error processing objectMapper", e);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<TokenDtoApiResponse> tokenDtoRequestResponseEntity = null;
        try {
            tokenDtoRequestResponseEntity = restTemplate.exchange(
                    tokenUrl,
                    HttpMethod.POST,
                    requestEntity,
                    TokenDtoApiResponse.class);

        } catch (RestClientException e) {
            throw new ApiHttpClientErrorException(e.getMessage());
        }

        return Optional.ofNullable(tokenDtoRequestResponseEntity.getBody());
    }

}
