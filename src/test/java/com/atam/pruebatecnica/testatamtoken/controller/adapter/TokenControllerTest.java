package com.atam.pruebatecnica.testatamtoken.controller.adapter;

import com.atam.pruebatecnica.testatamtoken.application.model.generated.TokenDtoApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class TokenControllerTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;



    private String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @SneakyThrows
    void getToken() {
        TokenDtoApiResponse tokenDtoApiResponse = new TokenDtoApiResponse();
        tokenDtoApiResponse.setToken("mocked-token");
        ResponseEntity<TokenDtoApiResponse> tokenDtoRequestResponseEntity =
                new ResponseEntity<>(tokenDtoApiResponse, HttpStatus.OK);
        given(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(TokenDtoApiResponse.class)))
                .willReturn(tokenDtoRequestResponseEntity);
        var result = mockMvc.perform(get("http://localhost:8081/get-token")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpectAll(
                        status().isOk()
                )
                .andReturn();
        log.info("result: {}", result.getResponse().getContentAsString());
    }

    @Test
    @SneakyThrows
    void getTokenReturnError401() {
        TokenDtoApiResponse tokenDtoApiResponse = new TokenDtoApiResponse();
        tokenDtoApiResponse.setToken("mocked-token");
        given(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(TokenDtoApiResponse.class)))
                .willThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));
        var result = mockMvc.perform(get("http://localhost:8081/get-token")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpectAll(
                        status().isUnauthorized()
                )
                .andReturn();
        log.info("result: {}", result.getResponse().getContentAsString());
    }

}